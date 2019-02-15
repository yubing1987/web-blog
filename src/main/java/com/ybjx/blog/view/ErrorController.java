package com.ybjx.blog.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 错误页面
 * create by YuBing at 2019/2/15
 */
@Controller
public class ErrorController {

    @RequestMapping({"/error", "/error/"})
    public String error() {
        return "error";
    }
}
