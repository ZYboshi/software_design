package com.campussecondhand.controller.user;

import cn.hutool.core.bean.BeanUtil;
import com.campussecondhand.constant.JwtClaimsConstant;
import com.campussecondhand.context.BaseContext;
import com.campussecondhand.pojo.dto.*;
import com.campussecondhand.pojo.entity.PageResult;
import com.campussecondhand.pojo.entity.User;
import com.campussecondhand.pojo.vo.BookVO;
import com.campussecondhand.pojo.vo.OrderCreateVO;
import com.campussecondhand.pojo.vo.UserProfileVO;
import com.campussecondhand.pojo.vo.UserVO;
import com.campussecondhand.properties.JwtProperties;
import com.campussecondhand.service.UserService;
import com.campussecondhand.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtProperties jwtProperties;

    @PostMapping("/login")
    public Result<UserVO> login(@RequestBody UserDTO userDTO){
        log.info("用户登录：{}", userDTO.getUserName());
        User user = userService.login(userDTO);
        //登录成功后生成jwt
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.USER_ID, user.getUserId());
        String token = JwtUtils.createJWT(jwtProperties.getUserSecretKey(),
                jwtProperties.getUserTtl(),
                claims);
        UserVO userVO = new UserVO(user.getUserId(), token);

        return Result.success(userVO);
    }
    
    @PostMapping("/register")
    public Result<Long> register(@RequestBody UserDTO userDTO) {
        log.info("用户注册：{}", userDTO);

       Long userId = userService.register(userDTO);
       return Result.success(userId);
    }

    @PostMapping("/logout")
    public Result<String> logout() {
        log.info("用户退出：{}", BaseContext.getCurrentId());
        return Result.success();
    }


    @PostMapping("/upload")
    public Result upload(MultipartFile bookPic){
        log.info("文件上传：{}", bookPic);

        //原始文件名
        String originalFilename = bookPic.getOriginalFilename();
        //原始文件名扩展名
        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        //构造新文件名称
        String objectName = UUID.randomUUID().toString() + extension;

        //文件的请求路径
        //TODO 上传到云服务器
        //String url = aliOssUtil.upload(file.getBytes(), objectName);
        //return Result.success(url);
        return Result.success();

    }

    @PostMapping("/postbook")
    public Result postBook(@RequestBody BookDTO bookDTO) {
        log.info("发布书籍：{}", bookDTO);

        userService.postBook(bookDTO);

        return Result.success();
    }

    @PostMapping("/home")
    public Result<PageResult> queryBooks(@RequestBody BookPageQueryDTO bookPageQueryDTO) {
        log.info("书籍分页查询：{}", bookPageQueryDTO);

        PageResult pageResult = userService.queryBooks(bookPageQueryDTO);
        return Result.success(pageResult);
    }

    @GetMapping("/search")
    public Result<PageResult> searchBooks(@RequestBody BookSearchDTO bookSearchDTO) {
        log.info("书籍搜索：{}", bookSearchDTO);

        PageResult pageResult = userService.searchBooks(bookSearchDTO);
        return Result.success(pageResult);
    }

    @GetMapping("/book")
    public Result<BookVO> getBookDetail(@RequestParam String bookId) {
        log.info("查询书籍详细信息：{}", bookId);

        BookVO bookVO = userService.getBookDetail(bookId);
        return Result.success(bookVO);
    }


    @PostMapping("/cartAdd")
    public Result cartAdd(String bookId) {
        //TODO 没有购物车表
        return Result.success();
    }

    @GetMapping("/profile")
    public Result<UserProfileVO> searchProfile() {
        log.info("用户查询个人信息");

        User user = userService.getById(BaseContext.getCurrentId());
        UserProfileVO userProfileVO = BeanUtil.copyProperties(user, UserProfileVO.class);
        return Result.success(userProfileVO);
    }

    @PostMapping("/profile")
    public Result updateProfile(UserDTO userDTO) {
        log.info("修改个人信息");

        userService.updateProfile(userDTO);
        return Result.success();
    }

    @GetMapping("/changePassword")
    public Result changePassword(UserUPPasswordDTO upPasswordDTO) {
        log.info("用户修改密码；{}", BaseContext.getCurrentId());

        userService.changePassword(upPasswordDTO);
        return Result.success();
    }

    @GetMapping("/cart")
    public Result searchCart(){
        //TODO 没有购物车
        return Result.error("功能尚待开发！");
    }

    @GetMapping("orderCreate")
    public Result<OrderCreateVO> orderCreate(OrderDTO orderDTO) {
        log.info("创建订单：{}", orderDTO);

        OrderCreateVO orderCreateVO = userService.orderCreate(orderDTO);
        return Result.success(orderCreateVO);
    }

    @PostMapping("/buy")
    public Result Buy(){
        // TODO 没有购物车目前实现不了
        return Result.success();
    }


}
