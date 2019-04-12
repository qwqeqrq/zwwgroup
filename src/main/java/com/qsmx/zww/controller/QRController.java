package com.qsmx.zww.controller;

import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.qsmx.zww.uitil.QRCodeUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
            BitMatrix QRimage = QRCodeUtil.generateQRCodeStream("https://www.csdn.net/", httpServletResponse);
            MatrixToImageWriter.writeToStream(QRimage, "jpg", httpServletResponse.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
