package com.instrument.cms.forum.comment.service.impl;

import com.instrument.cms.forum.comment.domain.ForumComment;
import com.instrument.cms.forum.comment.domain.ForumCommentLike;
import com.instrument.cms.forum.comment.mapper.ForumCommentLikeMapper;
import com.instrument.cms.forum.comment.mapper.ForumCommentMapper;
import com.instrument.cms.forum.comment.service.IForumCommentService;
import com.instrument.cms.forum.domain.CmsForum;
import com.instrument.cms.forum.mapper.CmsForumMapper;
import com.instrument.common.core.domain.entity.SysUser;
import com.instrument.common.utils.DateUtils;
import com.instrument.system.mapper.SysUserMapper;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 评论管理Service业务层处理
 * 
 * @author ning
 * @date 2022-01-21
 */
@Service
public class ForumCommentServiceImpl implements IForumCommentService
{
    @Autowired
    private ForumCommentMapper forumCommentMapper;

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private ForumCommentLikeMapper forumCommentLikeMapper;

    @Autowired
    private CmsForumMapper cmsForumMapper;


    /**
     * 首页查询评论列表
     */
    @Override
    public List<ForumComment> selectCommentList(ForumComment forumComment) {
        //判断是否登录
        Long logUserUserId = null;
        String createBy = forumComment.getCreateBy();
        if (createBy!=null&&!"".equals(createBy)){
            SysUser logUser = sysUserMapper.selectUserByUserName(createBy);
            logUserUserId = logUser.getUserId();
        }
        forumComment.setCreateBy(null);
        forumComment.setType("0");
        forumComment.setDelFlag("0");
        List<ForumComment> ForumCommentList = forumCommentMapper.selectForumCommentList(forumComment);
        for (ForumComment comment : ForumCommentList) {
            //添加头像
            Long userId = comment.getUserId();
            if (userId!=null){
                SysUser user = sysUserMapper.selectUserById(userId);
                comment.setAvatar(user.getAvatar());
            }
            //添加是否被点赞
            if (logUserUserId!=null){
                ForumCommentLike commentLike = new ForumCommentLike();
                commentLike.setCommentId(comment.getId());
                commentLike.setUserId(logUserUserId);
                List<ForumCommentLike> likeList = forumCommentLikeMapper.selectForumCommentLikeList(commentLike);
                if (likeList.size()>0){
                    comment.setIsLike(true);
                }else {
                    comment.setIsLike(false);
                }
            }
            //添加子评论(回复)
            ForumComment childComment = new ForumComment();
            childComment.setType("1");
            childComment.setMainId(comment.getId());
            List<ForumComment> childCommentList = forumCommentMapper.selectChildCommentList(childComment);
            if (childCommentList.size()>0){
                for (ForumComment childListComment : childCommentList) {
                    //添加头像
                    Long childUserId = childListComment.getUserId();
                    if (childUserId!=null){
                        SysUser user = sysUserMapper.selectUserById(childUserId);
                        childListComment.setAvatar(user.getAvatar());
                    }
                    //添加是否被点赞
                    if (logUserUserId!=null){
                        ForumCommentLike commentLike = new ForumCommentLike();
                        commentLike.setCommentId(comment.getId());
                        commentLike.setUserId(logUserUserId);
                        List<ForumCommentLike> likeList = forumCommentLikeMapper.selectForumCommentLikeList(commentLike);
                        if (likeList.size()>0){
                            comment.setIsLike(true);
                        }else {
                            comment.setIsLike(false);
                        }
                    }
                    //添加父评论信息
                    ForumComment byId = forumCommentMapper.selectForumCommentById(childListComment.getParentId());
                    childListComment.setPCreateBy(byId.getCreateBy());
                }
                comment.setChildren(childCommentList);
            }
        }
        return ForumCommentList;
    }

    @Override
    public int addForumCommentLike(ForumCommentLike ForumCommentLike) {
        int result = -1;
        String createBy = ForumCommentLike.getCreateBy();
        if (!"".equals(createBy)&&createBy!=null){
            SysUser user = sysUserMapper.selectUserByUserName(createBy);
            if (user!=null){
                ForumCommentLike.setUserId(user.getUserId());
                forumCommentLikeMapper.addForumCommentLike(ForumCommentLike);
            }
        }
        //修改点赞数量
        ForumComment ForumComment = new ForumComment();
        ForumComment.setId(ForumCommentLike.getCommentId());
        ForumComment.setLikeNum(ForumCommentLike.getLikeNum());
        result = forumCommentMapper.updateForumComment(ForumComment);
        return result;
    }

    @Override
    public int delForumCommentLike(ForumCommentLike ForumCommentLike) {
        int result = -1;
        String createBy = ForumCommentLike.getCreateBy();
        if (!"".equals(createBy)&&createBy!=null){
            SysUser user = sysUserMapper.selectUserByUserName(createBy);
            if (user!=null){
                ForumCommentLike.setUserId(user.getUserId());
                forumCommentLikeMapper.deleteForumCommentLike(ForumCommentLike);
            }
        }
        //修改点赞数量
        ForumComment ForumComment = new ForumComment();
        ForumComment.setId(ForumCommentLike.getCommentId());
        ForumComment.setLikeNum(ForumCommentLike.getLikeNum());
        result = forumCommentMapper.updateForumComment(ForumComment);
        return result;
    }

    /**
     * 查询评论管理
     * 
     * @param id 评论管理主键
     * @return 评论管理
     */
    @Override
    public ForumComment selectForumCommentById(Long id)
    {
        return forumCommentMapper.selectForumCommentById(id);
    }

    /**
     * 查询评论管理列表
     * 
     * @param ForumComment 评论管理
     * @return 评论管理
     */
    @Override
    public List<ForumComment> selectForumCommentList(ForumComment ForumComment)
    {
        List<ForumComment> ForumCommentList = new ArrayList<>();
        //判断用户权限
        String createBy = ForumComment.getCreateBy();
        if (createBy!=null&&!"".equals(createBy)){
            SysUser user = sysUserMapper.selectUserByUserName(createBy);
            if (user!=null){
                List<ForumComment> CommentList = forumCommentMapper.selectForumCommentList(ForumComment);
                for (ForumComment comment : CommentList) {
                    //查询子评论(回复)
                    ForumComment childComment = new ForumComment();
                    childComment.setType("1");
                    childComment.setParentId(comment.getId());
                    List<ForumComment> childCommentList = forumCommentMapper.selectForumCommentList(childComment);
                    if (childCommentList.size()>0){
                        ForumCommentList.addAll(childCommentList);
                    }
                }
                ForumCommentList.addAll(CommentList);
            }
        }else {
            ForumCommentList = forumCommentMapper.selectForumCommentList(ForumComment);
        }
        for (ForumComment comment : ForumCommentList) {
            //添加头像
            Long userId = comment.getUserId();
            if (userId!=null){
                SysUser user = sysUserMapper.selectUserById(userId);
                comment.setAvatar(user.getAvatar());
            }
            //添加父评论信息
            Long parentId = comment.getParentId();
            if (parentId!=null){
                ForumComment parentComment = forumCommentMapper.selectForumCommentById(parentId);
                comment.setPCreateBy(parentComment.getCreateBy());
            }
            Long forumId = comment.getForumId();
            if (forumId!=null){
                CmsForum forum = cmsForumMapper.selectCmsForumById(forumId);
                if(ObjectUtils.isNotEmpty(forum)) {
                    comment.setForumTitle(forum.getTitle());
                }
            }
        }
        //排序
//        String[] sortNameArr1 = {"createTime"};
//        //true升序，false降序
//        boolean[] isAscArr1 = {false};
//        ListSortUtils.sort(cmsMessageList, sortNameArr1, isAscArr1);
//        cmsMessageList.sort((a,b)->a.getCreateBy().compareTo(b.getCreateBy()));
//        Collections.sort(cmsMessageList, new Comparator<CmsMessage>() {
//            @Override
//            public int compare(CmsMessage o1, CmsMessage o2) {
//                //升序
//                //return o1.getCreateBy().compareTo(o2.getCreateBy());
//                //降序
//                return o2.getCreateBy().compareTo(o1.getCreateBy());
//            }
//        });
        return ForumCommentList;
    }

    /**
     * 新增评论管理
     * 
     * @param ForumComment 评论管理
     * @return 结果
     */
    @Override
    public int insertForumComment(ForumComment ForumComment)
    {
        String createBy = ForumComment.getCreateBy();
        if (createBy!=null&&!"".equals(createBy)){
            SysUser user = sysUserMapper.selectUserByUserName(createBy);
            if (user!=null){
                ForumComment.setUserId(user.getUserId());
            }
        }
        ForumComment.setCreateTime(DateUtils.getNowDate());
        return forumCommentMapper.insertForumComment(ForumComment);
    }

    /**
     * 修改评论管理
     * 
     * @param ForumComment 评论管理
     * @return 结果
     */
    @Override
    public int updateForumComment(ForumComment ForumComment)
    {
        ForumComment.setUpdateTime(DateUtils.getNowDate());
        return forumCommentMapper.updateForumComment(ForumComment);
    }

    /**
     * 批量删除评论管理
     * 
     * @param ids 需要删除的评论管理主键
     * @return 结果
     */
    @Override
    public int deleteForumCommentByIds(Long[] ids)
    {
        return forumCommentMapper.updateDelFlagByIds(ids);
    }

    /**
     * 删除评论管理信息
     * 
     * @param id 评论管理主键
     * @return 结果
     */
    @Override
    public int deleteForumCommentById(Long id)
    {
        return forumCommentMapper.updateDelFlagById(id);
    }
}
