package com.instrument.music.comment.service.impl;

import com.instrument.common.core.domain.entity.SysUser;
import com.instrument.common.utils.DateUtils;
import com.instrument.music.comment.domain.CurriculumComment;
import com.instrument.music.comment.domain.CurriculumCommentLike;
import com.instrument.music.comment.mapper.CurriculumCommentLikeMapper;
import com.instrument.music.comment.mapper.CurriculumCommentMapper;
import com.instrument.music.comment.service.ICurriculumCommentService;
import com.instrument.music.domain.MusicCurriculum;
import com.instrument.music.mapper.MusicCurriculumMapper;
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
public class CurriculumCommentServiceImpl implements ICurriculumCommentService 
{
    @Autowired
    private CurriculumCommentMapper curriculumCommentMapper;

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private CurriculumCommentLikeMapper curriculumCommentLikeMapper;

    @Autowired
    private MusicCurriculumMapper curriculumMapper;

    /**
     * 首页查询评论列表
     */
    @Override
    public List<CurriculumComment> selectCommentList(CurriculumComment CurriculumComment) {
        //判断是否登录
        Long logUserUserId = null;
        String createBy = CurriculumComment.getCreateBy();
        if (createBy!=null&&!"".equals(createBy)){
            SysUser logUser = sysUserMapper.selectUserByUserName(createBy);
            logUserUserId = logUser.getUserId();
        }
        CurriculumComment.setCreateBy(null);
        CurriculumComment.setType("0");
        CurriculumComment.setDelFlag("0");
        List<CurriculumComment> CurriculumCommentList = curriculumCommentMapper.selectCurriculumCommentList(CurriculumComment);
        for (CurriculumComment comment : CurriculumCommentList) {
            //添加头像
            Long userId = comment.getUserId();
            if (userId!=null){
                SysUser user = sysUserMapper.selectUserById(userId);
                comment.setAvatar(user.getAvatar());
            }
            //添加是否被点赞
            if (logUserUserId!=null){
                CurriculumCommentLike commentLike = new CurriculumCommentLike();
                commentLike.setCommentId(comment.getId());
                commentLike.setUserId(logUserUserId);
                List<CurriculumCommentLike> likeList = curriculumCommentLikeMapper.selectcurriculumCommentLikeList(commentLike);
                if (likeList.size()>0){
                    comment.setIsLike(true);
                }else {
                    comment.setIsLike(false);
                }
            }
            //添加子评论(回复)
            CurriculumComment childComment = new CurriculumComment();
            childComment.setType("1");
            childComment.setMainId(comment.getId());
            List<CurriculumComment> childCommentList = curriculumCommentMapper.selectChildCommentList(childComment);
            if (childCommentList.size()>0){
                for (CurriculumComment childListComment : childCommentList) {
                    //添加头像
                    Long childUserId = childListComment.getUserId();
                    if (childUserId!=null){
                        SysUser user = sysUserMapper.selectUserById(childUserId);
                        childListComment.setAvatar(user.getAvatar());
                    }
                    //添加是否被点赞
                    if (logUserUserId!=null){
                        CurriculumCommentLike commentLike = new CurriculumCommentLike();
                        commentLike.setCommentId(comment.getId());
                        commentLike.setUserId(logUserUserId);
                        List<CurriculumCommentLike> likeList = curriculumCommentLikeMapper.selectcurriculumCommentLikeList(commentLike);
                        if (likeList.size()>0){
                            comment.setIsLike(true);
                        }else {
                            comment.setIsLike(false);
                        }
                    }
                    //添加父评论信息
                    CurriculumComment byId = curriculumCommentMapper.selectCurriculumCommentById(childListComment.getParentId());
                    childListComment.setPCreateBy(byId.getCreateBy());
                }
                comment.setChildren(childCommentList);
            }
        }
        return CurriculumCommentList;
    }

    @Override
    public int addCurriculumCommentLike(CurriculumCommentLike CurriculumCommentLike) {
        int result = -1;
        String createBy = CurriculumCommentLike.getCreateBy();
        if (!"".equals(createBy)&&createBy!=null){
            SysUser user = sysUserMapper.selectUserByUserName(createBy);
            if (user!=null){
                CurriculumCommentLike.setUserId(user.getUserId());
                curriculumCommentLikeMapper.addcurriculumCommentLike(CurriculumCommentLike);
            }
        }
        //修改点赞数量
        CurriculumComment CurriculumComment = new CurriculumComment();
        CurriculumComment.setId(CurriculumCommentLike.getCommentId());
        CurriculumComment.setLikeNum(CurriculumCommentLike.getLikeNum());
        result = curriculumCommentMapper.updateCurriculumComment(CurriculumComment);
        return result;
    }

    @Override
    public int delCurriculumCommentLike(CurriculumCommentLike CurriculumCommentLike) {
        int result = -1;
        String createBy = CurriculumCommentLike.getCreateBy();
        if (!"".equals(createBy)&&createBy!=null){
            SysUser user = sysUserMapper.selectUserByUserName(createBy);
            if (user!=null){
                CurriculumCommentLike.setUserId(user.getUserId());
                curriculumCommentLikeMapper.deletecurriculumCommentLike(CurriculumCommentLike);
            }
        }
        //修改点赞数量
        CurriculumComment CurriculumComment = new CurriculumComment();
        CurriculumComment.setId(CurriculumCommentLike.getCommentId());
        CurriculumComment.setLikeNum(CurriculumCommentLike.getLikeNum());
        result = curriculumCommentMapper.updateCurriculumComment(CurriculumComment);
        return result;
    }

    /**
     * 查询评论管理
     * 
     * @param id 评论管理主键
     * @return 评论管理
     */
    @Override
    public CurriculumComment selectCurriculumCommentById(Long id)
    {
        return curriculumCommentMapper.selectCurriculumCommentById(id);
    }

    /**
     * 查询评论管理列表
     * 
     * @param CurriculumComment 评论管理
     * @return 评论管理
     */
    @Override
    public List<CurriculumComment> selectCurriculumCommentList(CurriculumComment CurriculumComment)
    {
        List<CurriculumComment> CurriculumCommentList = new ArrayList<>();
        //判断用户权限
        String createBy = CurriculumComment.getCreateBy();
        if (createBy!=null&&!"".equals(createBy)){
            SysUser user = sysUserMapper.selectUserByUserName(createBy);
            if (user!=null){
                List<CurriculumComment> CommentList = curriculumCommentMapper.selectCurriculumCommentList(CurriculumComment);
                for (CurriculumComment comment : CommentList) {
                    //查询子评论(回复)
                    CurriculumComment childComment = new CurriculumComment();
                    childComment.setType("1");
                    childComment.setParentId(comment.getId());
                    List<CurriculumComment> childCommentList = curriculumCommentMapper.selectCurriculumCommentList(childComment);
                    if (childCommentList.size()>0){
                        CurriculumCommentList.addAll(childCommentList);
                    }
                }
                CurriculumCommentList.addAll(CommentList);
            }
        }else {
            CurriculumCommentList = curriculumCommentMapper.selectCurriculumCommentList(CurriculumComment);
        }
        for (CurriculumComment comment : CurriculumCommentList) {
            //添加头像
            Long userId = comment.getUserId();
            if (userId!=null){
                SysUser user = sysUserMapper.selectUserById(userId);
                comment.setAvatar(user.getAvatar());
            }
            //添加父评论信息
            Long parentId = comment.getParentId();
            if (parentId!=null){
                CurriculumComment parentComment = curriculumCommentMapper.selectCurriculumCommentById(parentId);
                comment.setPCreateBy(parentComment.getCreateBy());
            }
            Long curriculumId = comment.getCurriculumId();
            if (curriculumId!=null){
                MusicCurriculum curriculum = curriculumMapper.selectMusicCurriculumById(curriculumId);
                if(ObjectUtils.isNotEmpty(curriculum)) {
                    comment.setTitle(curriculum.getTitle());
                }
            }
        }
        //排序
//        String[] sortNameArr1 = {"createTime"};
//        //true升序，false降序
//        boolean[] isAscArr1 = {false};
//        ListSortUtils.sort(CurriculumMessageList, sortNameArr1, isAscArr1);
//        CurriculumMessageList.sort((a,b)->a.getCreateBy().compareTo(b.getCreateBy()));
//        Collections.sort(CurriculumMessageList, new Comparator<CurriculumMessage>() {
//            @Override
//            public int compare(CurriculumMessage o1, CurriculumMessage o2) {
//                //升序
//                //return o1.getCreateBy().compareTo(o2.getCreateBy());
//                //降序
//                return o2.getCreateBy().compareTo(o1.getCreateBy());
//            }
//        });
        return CurriculumCommentList;
    }

    /**
     * 新增评论管理
     * 
     * @param CurriculumComment 评论管理
     * @return 结果
     */
    @Override
    public int insertCurriculumComment(CurriculumComment CurriculumComment)
    {
        String createBy = CurriculumComment.getCreateBy();
        if (createBy!=null&&!"".equals(createBy)){
            SysUser user = sysUserMapper.selectUserByUserName(createBy);
            if (user!=null){
                CurriculumComment.setUserId(user.getUserId());
            }
        }
        CurriculumComment.setCreateTime(DateUtils.getNowDate());
        return curriculumCommentMapper.insertCurriculumComment(CurriculumComment);
    }

    /**
     * 修改评论管理
     * 
     * @param CurriculumComment 评论管理
     * @return 结果
     */
    @Override
    public int updateCurriculumComment(CurriculumComment CurriculumComment)
    {
        CurriculumComment.setUpdateTime(DateUtils.getNowDate());
        return curriculumCommentMapper.updateCurriculumComment(CurriculumComment);
    }

    /**
     * 批量删除评论管理
     * 
     * @param ids 需要删除的评论管理主键
     * @return 结果
     */
    @Override
    public int deleteCurriculumCommentByIds(Long[] ids)
    {
        return curriculumCommentMapper.updateDelFlagByIds(ids);
    }

    /**
     * 删除评论管理信息
     * 
     * @param id 评论管理主键
     * @return 结果
     */
    @Override
    public int deleteCurriculumCommentById(Long id)
    {
        return curriculumCommentMapper.updateDelFlagById(id);
    }
}
