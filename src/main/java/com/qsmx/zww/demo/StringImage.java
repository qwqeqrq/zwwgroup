package com.qsmx.zww.demo;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by zww on 2019-04-01.灰度图生成字符串图片
 */
public class StringImage {
    //输出字符图片
    public static void main(String[] args) {
        BufferedImage image = null;
        File file = null;
        try {
            file = new File("C:\\Users\\Admin\\Desktop\\灰度.jpg");
            image = ImageIO.read(file);
            int width = image.getWidth();
            int height = image.getHeight();
            for (int j = 0; j < height; j++) {
                System.out.println();//换行
                for (int i = 0; i < width; i++) {
                    int p = image.getRGB(i, j);
                    int xiangsuzhi = p & 0x0FF;
                    if (xiangsuzhi < 70) {
                        System.out.print("e");
                    } else if (xiangsuzhi < 160) {
                        System.out.print("D");
                    } else {
                        System.out.print(" ");
                    }
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
