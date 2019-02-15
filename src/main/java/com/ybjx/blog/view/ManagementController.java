package com.ybjx.blog.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 管理端入口
 * create by YuBing at 2019/2/15
 */
@Controller
public class ManagementController {
    @RequestMapping({"/management/", "/management"})
    public String management() {
        return "management";
    }
}
