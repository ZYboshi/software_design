package com.campussecondhand.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.campussecondhand.constant.MessageConstant;
import com.campussecondhand.constant.PageConstant;
import com.campussecondhand.constant.PasswordConstant;
import com.campussecondhand.context.BaseContext;
import com.campussecondhand.exception.AccountNotFoundException;
import com.campussecondhand.exception.PasswordErrorException;
import com.campussecondhand.exception.UserNameExistException;
import com.campussecondhand.mapper.BookMapper;
import com.campussecondhand.mapper.ImageMapper;
import com.campussecondhand.mapper.OrderMapper;
import com.campussecondhand.mapper.UserMapper;
import com.campussecondhand.pojo.dto.*;
import com.campussecondhand.pojo.entity.*;
import com.campussecondhand.pojo.vo.BookVO;
import com.campussecondhand.pojo.vo.OrderCreateVO;
import com.campussecondhand.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.HTML;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private ImageMapper imageMapper;

    @Autowired
    private OrderMapper orderMapper;
    @Override
    public User login(UserDTO userDTO) {
        //根据用户名查找数据库
        String userName = userDTO.getUserName();
        String password = userDTO.getPassword();

        //根据用户名查询数据库
        User user = userMapper.queryByUserName(userName);
        if(user == null) {
            //账号不存在
            throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);
        }

        //密码对比
        if(!password.equals(user.getPassword())) {
            throw new PasswordErrorException(MessageConstant.PASSWORD_ERROR);
        }

        return user;
    }

    @Override
    public Long register(UserDTO userDTO) {
        User user = BeanUtil.copyProperties(userDTO, User.class);
        //查询是否存在该用户名
        User user1 = userMapper.queryByUserName(user.getUserName());
        //存在则注册失败
        if(user1 != null) {
            throw new UserNameExistException("用户名已存在！");
        }
        //若用户未指定密码则设为默认值
        if(StrUtil.isBlank(user.getPassword())) {
            user.setPassword(PasswordConstant.DEFAULT_PASSWORD);
        }
        //注册用户
        save(user);
        //返回用户id
        return user.getUserId();
    }

    @Transactional
    @Override
    public void postBook(BookDTO bookDTO) {
        // 存书籍
        Book book = BeanUtil.copyProperties(bookDTO, Book.class);
        if(book.getUserId() == null) {
            book.setUserId(BaseContext.getCurrentId());
        }
        bookMapper.insert(book);

        //存图片
        List<String> imageFiles = bookDTO.getImageFile();
        List<Image> list = new ArrayList<>();
        for (String imageFile : imageFiles) {
            Image image = new Image(null, imageFile, book.getBookId());
            list.add(image);
        }
        imageMapper.addBatch(list);
    }

    @Override
    public PageResult queryBooks(BookPageQueryDTO bookPageQueryDTO) {
        if(bookPageQueryDTO.getPageSize() == null) {
            //默认每页展示10个
            bookPageQueryDTO.setPageSize(PageConstant.DEFAULT_PAGE_SIZE);
        }
        Page<Book> page = new Page<>(bookPageQueryDTO.getPageNum(), bookPageQueryDTO.getPageSize());
        //分页查询
        IPage<BookDetailDTO> result = bookMapper.selectBookWithCategory(page, bookPageQueryDTO.getCategory());
        return new PageResult(result.getTotal(), result.getRecords());
    }

    @Override
    public PageResult searchBooks(BookSearchDTO bookSearchDTO) {
        if(bookSearchDTO.getPageSize() == null) {
            //默认每页展示10个
            bookSearchDTO.setPageSize(PageConstant.DEFAULT_PAGE_SIZE);
        }
        Page<Book> page = new Page<>(bookSearchDTO.getPageNum(), bookSearchDTO.getPageSize());
        IPage<BookSearchDetailDTO> result;
        //分页查询
        if(bookSearchDTO.getSortOrder() != null && bookSearchDTO.getSortOrder() == 1) {
            //按照价格降序查询
            result = bookMapper.selectBookWithPriceDESC(page, bookSearchDTO.getTitle(), bookSearchDTO.getPriceMin(), bookSearchDTO.getPriceMax());
        } else {
            //按照价格升序查询
            result = bookMapper.selectBookWithPriceASC(page, bookSearchDTO.getTitle(), bookSearchDTO.getPriceMin(), bookSearchDTO.getPriceMax());
        }
        return new PageResult(result.getTotal(), result.getRecords());
    }

    @Override
    @Transactional
    public BookVO getBookDetail(String bookId) {
        //查询图片文件
        List<String> imageFiles = imageMapper.selectByBookId(bookId);
        //查询书籍信息
        Book book = bookMapper.selectById(bookId);
        BookVO bookVO = BeanUtil.copyProperties(book, BookVO.class);
        //查询用户名
        User user = userMapper.selectById(book.getUserId());
        bookVO.setUserName(user.getUserName());
        bookVO.setImageFile(imageFiles);
        return bookVO;
    }

    @Override
    public void updateProfile(UserDTO userDTO) {
        if (userDTO == null || userDTO.getUserId() == null) {
            throw new IllegalArgumentException("用户ID不能为空");
        }
        LambdaUpdateWrapper<User> wrapper = new LambdaUpdateWrapper<>();
        //id相同
        wrapper.eq(User::getUserId, userDTO.getUserId());
        //只修改非空字段
        if(StrUtil.isNotBlank(userDTO.getEmail())) {
            wrapper.set(User::getEmail, userDTO.getEmail());
        }
        if(StrUtil.isNotBlank(userDTO.getIdNumber())) {
            wrapper.set(User::getIdNumber, userDTO.getIdNumber());
        }
        if(StrUtil.isNotBlank(userDTO.getPhone())) {
            wrapper.set(User::getPhone, userDTO.getPhone());
        }
        if(StrUtil.isNotBlank(userDTO.getUserName())) {
            wrapper.set(User::getUserName, userDTO.getUserName());
        }
        update(wrapper);
    }

    @Override
    @Transactional
    public void changePassword(UserUPPasswordDTO upPasswordDTO) {
        User user = getById(BaseContext.getCurrentId());
        //当前修改的不是自己的账号
        if(!user.getUserName().equals(upPasswordDTO.getUserName())) {
            throw new PasswordErrorException("修改失败！");
        }
        //旧密码错误
        if(!user.getPassword().equals(upPasswordDTO.getOldPassword())) {
            throw new PasswordErrorException("密码错误！");
        }
        //修改密码
        user.setPassword(upPasswordDTO.getNewPassword());
        updateById(user);
    }

    @Override
    public OrderCreateVO orderCreate(OrderDTO orderDTO) {
        //查询书籍对应图片
        List<String> imageFiles = imageMapper.selectByBookId(orderDTO.getBookId());
        //查询书籍对应信息
        Book book = bookMapper.selectById(orderDTO.getBookId());

        OrderCreateVO orderCreateVO = new OrderCreateVO();
        orderCreateVO.setTitle(book.getTitle());
        orderCreateVO.setImageFile(imageFiles.get(0));
        return orderCreateVO;
    }
}
