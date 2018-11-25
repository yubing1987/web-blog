package com.ybjx.blog.views;

import com.ybjx.blog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * create by YuBing at 2018/11/25
 */
@Controller
public class ArticleView {

    @Autowired
    private ArticleService articleService;

    @RequestMapping(value = {"", "/"})
    public String index(Model model){
        return "index";
    }

    /**
     * 文章页面
     * @param model -
     * @param uuid 文章的uuid
     * @return -
     */
    @RequestMapping(value = "/article/{uuid}", method = RequestMethod.GET)
    public String article(Model model, @PathVariable("uuid") String uuid){
        model.addAttribute("article", articleService.getArticleDto(uuid));

        return "article";
    }
}
