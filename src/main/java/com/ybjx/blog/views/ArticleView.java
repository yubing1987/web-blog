package com.ybjx.blog.views;

import com.ybjx.blog.dto.ArticleDTO;
import com.ybjx.blog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
        ArticleDTO articleDTO = articleService.getArticleDto(uuid);
        if(articleDTO != null){
            String content = articleDTO.getContent().replaceAll("\r", "");
            content = content.replaceAll("\\n", "\\\\n");
            articleDTO.setContent(content);
        }
        model.addAttribute("article", articleDTO);

        return "article";
    }
}
