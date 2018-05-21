package com.bobo.controller;

import com.bobo.common.pojo.ImageResult;
import com.bobo.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * create by lishengbo on 2018-05-21 15:21
 * 图片上传处理
 */
@Controller
public class PictureController {


    @Autowired
    private PictureService pictureService;

    /**
     * 参数名称一定要是 uploadFile
     * @param uploadFile
     * @return
     */
    @RequestMapping("/pic/upload")
    @ResponseBody
    private ImageResult pictureUpload(MultipartFile uploadFile) {

        ImageResult imageResult = pictureService.uploadPicture(uploadFile);

        System.err.println(imageResult.toString());

//        Integer error = imageResult.getError();
//        Map map=new HashMap();
//        map.put("error",error);
//        map.put("url",imageResult.getUrl());
//        return  map;
        return imageResult;
    }
}
