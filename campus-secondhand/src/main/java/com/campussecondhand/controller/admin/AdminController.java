package com.campussecondhand.controller.admin;

import com.campussecondhand.pojo.dto.Result;
import com.campussecondhand.pojo.entity.PageResult;
import com.campussecondhand.pojo.vo.BookVO;
import com.campussecondhand.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@Slf4j
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("listBook")
    public Result<PageResult> listBook(@RequestParam Integer pageNum) {
        log.info("管理员分页查询书籍：{}", pageNum);

        PageResult pageResult = adminService.listBook(pageNum);
        return Result.success(pageResult);
    }

    @PostMapping("passBook")
    private Result passBook(Integer bookId, boolean pass) {
        log.info("{}通过？{}", bookId, pass);

        adminService.passBook(bookId, pass);
        return Result.success();
    }
}
