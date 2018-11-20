package com.ybjx.blog.controller;

import com.ybjx.blog.common.result.PageResult;
import com.ybjx.blog.dto.ArticleDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 文章相关的接口
 * Created by YuBing on 2018/11/19.
 */
@RequestMapping("/article/")
@RestController
public class ArticleController {

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public PageResult<ArticleDTO> getArticlePage() {
        return null;
    }
}
