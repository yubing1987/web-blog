package com.ybjx.blog.views;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * create by YuBing at 2018/11/28
 */
@Controller
public class ListView {

    /**
     * 扩展阅读页面
     * @param model -
     * @param uuid 文章的uuid
     * @return -
     */
    @RequestMapping(value = "/list/{uuid}", method = RequestMethod.GET)
    public String article(Model model, @PathVariable("uuid") String uuid){
        return "article";
    }
}
