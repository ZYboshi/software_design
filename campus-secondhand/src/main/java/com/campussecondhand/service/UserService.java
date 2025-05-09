package com.campussecondhand.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.campussecondhand.pojo.dto.*;
import com.campussecondhand.pojo.entity.PageResult;
import com.campussecondhand.pojo.entity.User;
import com.campussecondhand.pojo.vo.BookVO;
import com.campussecondhand.pojo.vo.OrderCreateVO;

public interface UserService extends IService<User> {
    User login(UserDTO userDTO);

    Long register(UserDTO userDTO);

    void postBook(BookDTO bookDTO);

    PageResult queryBooks(BookPageQueryDTO bookPageQueryDTO);

    PageResult searchBooks(BookSearchDTO bookSearchDTO);

    BookVO getBookDetail(String bookId);

    void updateProfile(UserDTO userDTO);

    void changePassword(UserUPPasswordDTO upPasswordDTO);

    OrderCreateVO orderCreate(OrderDTO orderDTO);
}
