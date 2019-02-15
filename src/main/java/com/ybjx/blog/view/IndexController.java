package com.ybjx.blog.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 主页面接口
 * create by YuBing at 2019/2/15
 */
@Controller
public class IndexController {

    @RequestMapping({"/", ""})
    public String index() {
        return "index";
    }
}
