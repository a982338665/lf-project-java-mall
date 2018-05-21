package com.bobo.service.impl;

import com.bobo.common.pojo.ImageResult;
import com.bobo.common.utils.FtpUtil;
import com.bobo.common.utils.IDUtils;
import com.bobo.service.PictureService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

/**
 * create by lishengbo on 2018-05-21 14:36
 */
@Service
public class PictureServiceImpl implements PictureService {


    @Value("${FTP_ADDRESS}")
    private String FTP_ADDRESS;
    @Value("${FTP_PORT}")
    private Integer FTP_PORT;
    @Value("${FTP_USERNAME}")
    private String FTP_USERNAME;
    @Value("${FTP_PASSWORD}")
    private String FTP_PASSWORD;
    @Value("${FTP_BASE_PATH}")
    private String FTP_BASE_PATH;
    @Value("${IMAGE_BASE_URL}")
    private String IMAGE_BASE_URL;

    @Override
    public ImageResult uploadPicture(MultipartFile upload) {
        try {
            //生成新的文件名-要从传来的参数确定其文件拓展名
            String originalFilename = upload.getOriginalFilename();
            //生成新文件名--uuid或者时间
            //UUID uuid = UUID.randomUUID();
            String name = IDUtils.genImageName();
            name = name + originalFilename.substring(originalFilename.lastIndexOf("."));
            //图片上传
            InputStream inputStream = null;
            inputStream = upload.getInputStream();
            String imagePath = new DateTime().toString("/yyyy/MM/dd");
            //
            boolean b = FtpUtil.uploadFile(FTP_ADDRESS, FTP_PORT, FTP_USERNAME, FTP_PASSWORD, FTP_BASE_PATH
                    , imagePath, name, inputStream);
            if(!b){
                return new ImageResult(null,"文件上传失败");
            }else {
                return new ImageResult(IMAGE_BASE_URL+imagePath+"/"+name,null);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return new ImageResult(null,"文件上传异常"+e.getMessage());
        }

    }


}
