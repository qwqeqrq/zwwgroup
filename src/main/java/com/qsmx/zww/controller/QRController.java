package com.qsmx.zww.controller;

import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.qsmx.zww.uitil.QRCodeUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by zww on 2019-04-12.
 * 生成二维码
 */
@Controller
@RequestMapping(value = "QR")
public class QRController {

    @RequestMapping(value = "QRcode")
    public void getQRcdoe(HttpServletResponse httpServletResponse) {
        try {
            BitMatrix QRimage = QRCodeUtil.generateQRCodeStream("https://www.baidu.com", httpServletResponse);
            OutputStream outputStream = new FileOutputStream("D:\\1.jpg");//用输入流创建一个路径，一会吧二维码数据流写入该文件
            MatrixToImageWriter.writeToStream(QRimage, "jpg", httpServletResponse.getOutputStream());
            MatrixToImageWriter.writeToStream(QRimage, "jpg", outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
