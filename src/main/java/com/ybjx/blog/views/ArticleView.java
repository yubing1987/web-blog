package com.ybjx.blog.views;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * create by YuBing at 2018/11/25
 */
@Controller
public class ArticleView {

    @RequestMapping(value = {"", "/"})
    public String index(Model model){
        return "index";
    }
}
