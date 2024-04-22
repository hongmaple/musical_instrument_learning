package com.instrument.cms.fileInfo.service.impl;
/**
 * @program: RuoYi-Vue
 * @Author: WangNing
 * @Description: 〈〉
 * @Date: 2021/12/31 23:33
 */

import com.instrument.cms.fileInfo.domain.FileNoticeInfo;
import com.instrument.cms.fileInfo.mapper.FileNoticeInfoMapper;
import com.instrument.cms.fileInfo.service.IFileNoticeInfoService;
import com.instrument.common.config.RuoYiConfig;
import com.instrument.common.constant.Constants;
import com.instrument.common.utils.StringUtils;
import com.instrument.common.utils.file.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: WangNing
 * @Description:〈〉
 * @Date: 2021/12/31 23:33
 **/
@Service
public class FileNoticeInfoServiceImpl implements IFileNoticeInfoService {

    @Autowired
    private FileNoticeInfoMapper fileNoticeInfoMapper;

    @Override
    public int batchFileNotice(List<FileNoticeInfo> fileNoticeList) {
        return fileNoticeInfoMapper.batchFileNotice(fileNoticeList);
    }

    @Override
    public int deleteFileNoticeByNoticeId(Long noticeId) {
        return fileNoticeInfoMapper.deleteFileNoticeByNoticeId(noticeId);
    }

    @Override
    public int deleteFileNotice(Long[] ids) {
        return fileNoticeInfoMapper.deleteFileNotice(ids);
    }

    @Override
    public List<FileNoticeInfo> selectFileNoticeList(Long noticeId) {
        return fileNoticeInfoMapper.selectFileNoticeList(noticeId);
    }
}
