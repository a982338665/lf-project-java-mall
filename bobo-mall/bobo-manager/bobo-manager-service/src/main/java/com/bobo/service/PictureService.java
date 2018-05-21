package com.bobo.service;

import com.bobo.common.pojo.ImageResult;
import org.springframework.web.multipart.MultipartFile;

/**
 * create by lishengbo on 2018-05-21 14:36
 */
public interface PictureService {

    /**
     * 图片上传
     * @param upload
     * @return
     */
    ImageResult uploadPicture(MultipartFile upload);

}
