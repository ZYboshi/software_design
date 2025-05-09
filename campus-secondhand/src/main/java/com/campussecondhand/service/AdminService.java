package com.campussecondhand.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.campussecondhand.pojo.entity.Admin;
import com.campussecondhand.pojo.entity.PageResult;
import org.springframework.stereotype.Service;


public interface AdminService extends IService<Admin> {
    PageResult listBook(Integer pageNum);

    void passBook(Integer bookId, boolean pass);
}
