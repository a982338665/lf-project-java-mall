package com.bobo.controller;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;

/**
 * create by lishengbo on 2018-05-21 13:23
 * Ftp图片上传
 */
public class FTPTest {


    /**
     * ftp://192.168.134.128/images/hello1.jpg
     * @throws Exception
     */
    @Test
    public void testEtpClient() throws Exception{
        //创建一个FTPClient对象
        FTPClient ftpClient=new FTPClient();
        //创建ftp连接
//        ftpClient.connect("192.168.134.128",21);
        //端口默认为21，不写也可以
        ftpClient.connect("192.168.134.128");
        //登录ftp服务器
        ftpClient.login("ftpuser","ftpuser");
        //上传文件
        //参数1：服务器端的文档名字
        //参数2：上传文件的InputStream

        //读取本地文件
        FileInputStream fileInputStream = new FileInputStream(new File("C:\\Users\\cjh\\Desktop\\QQ图片20180509102810.jpg"));

        //设置上传服务器的路径
        ftpClient.changeWorkingDirectory("/home/ftpuser/images");
        //修改上传文件格式为二进制
        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

        ftpClient.storeFile("hello1.jpg",fileInputStream);
        //关闭连接
        ftpClient.logout();

    }


}
