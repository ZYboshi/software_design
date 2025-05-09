package com.campussecondhand.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.campussecondhand.constant.MessageConstant;
import com.campussecondhand.constant.PageConstant;
import com.campussecondhand.mapper.AdminMapper;
import com.campussecondhand.mapper.BookMapper;
import com.campussecondhand.mapper.ImageMapper;
import com.campussecondhand.pojo.dto.BookSearchDetailDTO;
import com.campussecondhand.pojo.entity.Admin;
import com.campussecondhand.pojo.entity.Book;
import com.campussecondhand.pojo.entity.Image;
import com.campussecondhand.pojo.entity.PageResult;
import com.campussecondhand.pojo.vo.BookVO;
import com.campussecondhand.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {

    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private ImageMapper imageMapper;

    @Override
    public PageResult listBook(Integer pageNum) {
        Page<Book> page = new Page<>(pageNum, PageConstant.DEFAULT_PAGE_SIZE);
        //分页查询书籍
        IPage<BookVO> result = bookMapper.listBookByStatus(page, MessageConstant.WAIT_EXAMINE);
        List<BookVO> records = result.getRecords();
        for (BookVO record : records) {
            String bookId = record.getBookId();
            //根据书籍id查询图片
            List<String> list = imageMapper.selectByBookId(bookId);
            record.setImageFile(list);
        }
        return new PageResult(result.getTotal(), records);
    }

    @Override
    public void passBook(Integer bookId, boolean pass) {
        Book book = new Book();
        book.setBookId(Long.valueOf(bookId));
        if(pass) book.setStatus("审核通过");
        else book.setStatus("审核不通过");
        bookMapper.updateById(book);
    }
}
