package com.ybjx.blog.query;

import com.ybjx.blog.checker.group.PagingCheck;
import com.ybjx.blog.common.Constant;
import org.hibernate.validator.constraints.Range;

import java.io.Serializable;

/**
 * 基础查询信息
 * @author ybjx
 * @date 2019/2/15
 */
public class BaseQuery implements Serializable {
    /**
     * 页码
     */
    @Range(min = 1, message = "页码不能小于0", groups = {PagingCheck.class})
    private int page;

    /**
     * 每一页大小
     */
    @Range(min = 1, max = 1000, message = "每一页大小必须在[1,1000]之间", groups = {PagingCheck.class})
    private int size;

    /**
     * 搜索关键字
     */
    private String key;

    public int getPage() {
        if(page < 1){
            return 1;
        }
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        if(size < 1){
            return 1;
        }
        if(size > Constant.MAX_PAGE_SIZE){
            return Constant.MAX_PAGE_SIZE;
        }
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "BaseQuery{" +
                "page=" + page +
                ", size=" + size +
                ", key='" + key + '\'' +
                '}';
    }
}
