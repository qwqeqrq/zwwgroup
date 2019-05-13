package com.qsmx.zww.controller;

import com.qsmx.zww.uitil.FtpImageUploadUtil;
import com.qsmx.zww.uitil.UuidUtil;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * Created by zww on 2019-04-10.图片上传服务器测试
 */
@RestController
@RequestMapping(value = "image")
public class ImageUploadController {


    /**
     *@描述： 上传文件  path 是在ftp服务器内部能看到的路径（pub就是ftp服务器默认根目录） 不是云服务器的路径
     *@参数：  [file]
     *@返回值： java.lang.String
     *@创建人： zhangww
     *@创建时间：  2019-05-13
     *@修改人和其它信息：
     */
    @RequestMapping(value = "up")
    public String up(@RequestBody MultipartFile file) {
        InputStream inputStream = null;
        try {
            inputStream = file.getInputStream();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String fileName = UuidUtil.getUuid() + ".jpg";
        String url = FtpImageUploadUtil.uploadFile("47.104.175.49", 21,
                "anonymous", "", "pub/picture", fileName, inputStream);
        return url;
    }
}
