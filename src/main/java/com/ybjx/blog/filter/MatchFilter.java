package com.ybjx.blog.filter;

import com.ybjx.blog.common.Constant;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * 可以进行路径匹配的过滤器
 * @author ybjx
 * @date 2019/5/1 23:46
 */
public class MatchFilter implements Filter {

    /**
     * url比对正则表达式
     */
    private List<Pattern> patterns = new ArrayList<>();

    @Override
    public void  init(FilterConfig filterConfig) throws ServletException {
        String ignoreUrl = filterConfig.getInitParameter("ignore-url");
        if(!StringUtils.isEmpty(ignoreUrl)){
            String[] urls = ignoreUrl.split(Constant.COMMA);
            for(String url: urls){
                if(!StringUtils.isEmpty(url)){
                    patterns.add(Pattern.compile(url));
                }
            }
        }
    }

    @Override
    public final void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String uri = request.getRequestURI().trim();

        for(Pattern pattern: patterns){
            if(pattern.matcher(uri).matches()){
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            }
        }

        filter(servletRequest, servletResponse, filterChain);
    }

    /**
     * 过滤函数
     * @param servletRequest -
     * @param servletResponse -
     * @param filterChain -
     * @throws IOException -
     * @throws ServletException -
     */
    public void filter(ServletRequest servletRequest,
                                 ServletResponse servletResponse,
                                 FilterChain filterChain) throws IOException, ServletException{
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
