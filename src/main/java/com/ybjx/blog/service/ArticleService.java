package com.ybjx.blog.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ybjx.blog.checker.ParameterCheck;
import com.ybjx.blog.checker.group.CreateCheck;
import com.ybjx.blog.checker.group.UpdateCheck;
import com.ybjx.blog.common.BlogException;
import com.ybjx.blog.common.ErrorCode;
import com.ybjx.blog.common.OperationTyp;
import com.ybjx.blog.common.UserHolder;
import com.ybjx.blog.common.result.Page;
import com.ybjx.blog.common.result.PageResult;
import com.ybjx.blog.dao.ArticleMapper;
import com.ybjx.blog.dao.ArticleTagMapper;
import com.ybjx.blog.dao.ArticleTagRefMapper;
import com.ybjx.blog.dto.ArticleDTO;
import com.ybjx.blog.dto.ArticleTagDTO;
import com.ybjx.blog.entity.ArticleDO;
import com.ybjx.blog.entity.ArticleTagDO;
import com.ybjx.blog.entity.ArticleTagRefDO;
import com.ybjx.blog.entity.UserInfoDO;
import com.ybjx.blog.query.ArticleQuery;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 文章相关的接口
 * @author ybjx
 * @date 2019/5/3 9:45
 */
@Service
public class ArticleService {

    /**
     * 文章数据库操作
     */
    private final ArticleMapper articleMapper;

    /**
     * 文章标签信息数据库操作
     */
    private final ArticleTagMapper articleTagMapper;

    /**
     * 文章标签信息数据库操作
     */
    private final ArticleTagRefMapper articleTagRefMapper;

    /**
     * 操作日志服务
     */
    private final OperationLogService logService;

    /**
     * 构造方法
     */
    @Autowired
    public ArticleService(ArticleMapper articleMapper,
                          OperationLogService logService,
                          ArticleTagMapper articleTagMapper,
                          ArticleTagRefMapper articleTagRefMapper) {
        this.articleMapper = articleMapper;
        this.logService = logService;
        this.articleTagMapper = articleTagMapper;
        this.articleTagRefMapper = articleTagRefMapper;
    }

    /**
     * 添加文章
     * @param articleDTO 文章内容
     */
    @ParameterCheck(CreateCheck.class)
    @Transactional(rollbackFor = Exception.class)
    public void addArticle(ArticleDTO articleDTO){
        ArticleDO articleDO = new ArticleDO();
        articleDO.setTitle(articleDTO.getTitle());
        articleDO.setIsDeleted(false);
        if(articleMapper.selectCount(articleDO) > 0){
            throw new BlogException(ErrorCode.OBJECT_EXIST, "标题已经被使用过了");
        }
        BeanUtils.copyProperties(articleDTO, articleDO);
        articleDO.setId(null);
        articleDO.setIsDeleted(false);
        articleDO.setModifyDate(new Date());
        articleDO.setCreateDate(new Date());
        articleDO.setViewCount(0);
        articleDO.setOwner(UserHolder.getUser().getId());
        try{
            articleMapper.insert(articleDO);
            logService.addLog("新建文章", articleDO.getId(),
                    OperationTyp.ARTICLE, UserHolder.getUser().getId());
        }
        catch (Exception e){
            throw new BlogException(ErrorCode.DATABASE_INSERT, "保存文章出错");
        }
    }

    /**
     * 删除文章
     * @param id 文章ID
     */
    @Transactional(rollbackFor = Exception.class)
    public void delArticle(int id){
        ArticleDO articleDO = getArticleById(id);
        UserInfoDO user = UserHolder.getUser();
        if(!articleDO.getOwner().equals(user.getId())){
            throw new BlogException(ErrorCode.PERMISSION_DENIED, new Exception(user.getId() + "无权限删除文章【" + articleDO.getId()+ "】"));
        }
        articleDO.setIsDeleted(true);
        articleDO.setModifyDate(new Date());
        try{
            articleMapper.updateByPrimaryKey(articleDO);
            logService.addLog("删除文章", articleDO.getId(), OperationTyp.ARTICLE, user.getId());
        }
        catch (Exception e){
            throw new BlogException(ErrorCode.OBJECT_DELETE_ERROR, "删除文章出粗", e);
        }
    }

    /**
     * 编辑文章
     * @param articleDTO 新的文章信息
     */
    @ParameterCheck(UpdateCheck.class)
    @Transactional(rollbackFor = Exception.class)
    public void editArticle(ArticleDTO articleDTO){
        ArticleDO articleDO = getArticleById(articleDTO.getId());
        UserInfoDO user = UserHolder.getUser();
        if(!articleDO.getOwner().equals(user.getId())){
            throw new BlogException(ErrorCode.PERMISSION_DENIED, new Exception(user.getId() + "无权限编辑文章【" + articleDO.getId()+ "】"));
        }

        boolean needSave = false;
        if(articleDTO.getTitle() != null
                && !articleDTO.getTitle().equals(articleDO.getTitle())){
            ArticleDO temp = new ArticleDO();
            temp.setTitle(articleDTO.getTitle());
            temp.setIsDeleted(false);
            if(articleMapper.selectCount(articleDO) > 0){
                throw new BlogException(ErrorCode.OBJECT_EXIST, "标题已经被使用过了");
            }
            articleDO.setTitle(articleDTO.getTitle());
            needSave = true;
        }

        if(articleDTO.getContent() != null
                && !articleDO.getContent().equals(articleDTO.getContent())){
            articleDO.setContent(articleDTO.getContent());
            needSave = true;
        }

        if(articleDTO.getAbstractContent() != null
                && !articleDTO.getAbstractContent().equals(articleDO.getAbstractContent())){
            articleDO.setAbstractContent(articleDTO.getAbstractContent());
            needSave = true;
        }

        if(articleDTO.getPicture() != null
                && !articleDTO.getPicture().equals(articleDO.getPicture())){
            articleDO.setPicture(articleDTO.getPicture());
            needSave = true;
        }
        if(needSave){
            try{
                articleMapper.updateByPrimaryKey(articleDO);
                logService.addLog("编辑文章", articleDO.getId(), OperationTyp.ARTICLE, user.getId());
            }
            catch (Exception e){
                throw new BlogException(ErrorCode.OBJECT_UPDATE_ERROR, "保存文章出错", e);
            }
        }
    }

    /**
     * 通过ID查找文章信息
     * @param id 文章ID
     * @return 文章信息
     */
    public ArticleDO getArticleById(int id){
        ArticleDO articleDO = articleMapper.selectByPrimaryKey(id);
        if(articleDO == null || articleDO.getIsDeleted()){
            throw new BlogException(ErrorCode.OBJECT_NOT_FOUND, "文章不存在");
        }
        return articleDO;
    }

    /**
     * 查找文章列表
     * @param query 查询条件
     * @return 查询到的数据
     */
    public PageResult<ArticleDTO> getArticleList(ArticleQuery query){
        PageHelper.startPage(query.getPage(), query.getSize(), "modify_date desc");
        List<ArticleDO> list = articleMapper.getArticleList(query.getUserId(), query.getTagId(), query.getKey());

        PageInfo<ArticleDO> pageInfo = new PageInfo<>(list);

        PageResult<ArticleDTO> result = new PageResult<>();
        Page<ArticleDTO> page = new Page<>();
        result.setContent(page);
        page.setTotal(pageInfo.getTotal());
        page.setCurrent(query.getPage());
        page.setLimit(query.getSize());

        List<Integer> articleIds = new ArrayList<>();
        List<ArticleDTO> listDto = new ArrayList<>();
        Map<Integer, ArticleDTO> cache = new HashMap<>(8);
        for(ArticleDO articleDO: list){
            ArticleDTO articleDTO = new ArticleDTO();
            BeanUtils.copyProperties(articleDO, articleDTO);
            listDto.add(articleDTO);
            articleDTO.setTags(new ArrayList<>());
            articleIds.add(articleDO.getId());
            cache.put(articleDTO.getId(), articleDTO);
        }
        page.setItems(listDto);
        if(articleIds.size() > 0) {
            List<ArticleTagDO> tags = articleTagMapper.getTagByArticleIds(articleIds);

            List<ArticleTagRefDO> tagRefs = articleTagRefMapper.getTagByArticleIds(articleIds);
            Map<Integer, ArticleTagDTO> tagCache = new HashMap<>(8);
            for (ArticleTagDO tagDO : tags) {
                ArticleTagDTO tagDTO = new ArticleTagDTO();
                BeanUtils.copyProperties(tagDO, tagDTO);
                tagCache.put(tagDTO.getId(), tagDTO);
            }

            for (ArticleTagRefDO refDO : tagRefs) {
                ArticleDTO articleDTO = cache.get(refDO.getArticleId());
                if (articleDTO == null) {
                    continue;
                }
                ArticleTagDTO tagDTO = tagCache.get(refDO.getTagId());
                if (tagDTO == null) {
                    continue;
                }
                articleDTO.getTags().add(tagDTO);
            }
        }

        return result;
    }

    /**
     * 通过ID列表查询文章信息
     * @param ids 文章ID列表
     * @return 文章列表
     */
    public List<ArticleDO> getArticleList(List<Integer> ids){
        return articleMapper.getArticlesByIds(ids);
    }
}
