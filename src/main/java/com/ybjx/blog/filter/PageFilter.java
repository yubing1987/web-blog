package com.ybjx.blog.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * @author ybjx
 * @date 2019/5/18 18:44
 */
public class PageFilter extends MatchFilter {

    private static char[] indexStr = new char[100 * 1024];
    private static int indexStrCount = 0;

    private static Logger LOGGER = LoggerFactory.getLogger(PageFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        super.init(filterConfig);

        String indexPath = filterConfig.getInitParameter("management-index-path");

        try (FileReader reader = new FileReader(indexPath);
             BufferedReader br = new BufferedReader(reader)
        ) {
            indexStrCount = br.read(indexStr);
        } catch (IOException e) {
            LOGGER.error("Load index error!", e);
        }
    }

    @Override
    public void filter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException {
        HttpServletResponse response = (HttpServletResponse)servletResponse;
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write(indexStr, 0, indexStrCount);
    }
}
