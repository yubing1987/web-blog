package com.ybjx.blog.views;

import com.ybjx.blog.entity.ArticleDO;
import com.ybjx.blog.entity.RelateArticleDO;
import com.ybjx.blog.service.RelateArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * create by YuBing at 2018/11/28
 */
@Controller
public class ListView {

    @Autowired
    private RelateArticleService relateArticleService;

    /**
     * 扩展阅读页面
     * @param model -
     * @param uuid 文章的uuid
     * @return -
     */
    @RequestMapping(value = "/list/{uuid}", method = RequestMethod.GET)
    public String article(Model model, @PathVariable("uuid") String uuid){

        List<ArticleDO> list = relateArticleService.getRelateArticle(uuid);
        model.addAttribute("article_list", list);
        return "list";
    }
}
