package com.qsmx.zww.demo;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by zww on 2019-04-01. 图片灰度处理
 */
public class ImageGrayscale {
    public static void main(String args[]) throws IOException {
        BufferedImage image = null;
        File file = null;

        try {
            file = new File("C:\\Users\\Admin\\Desktop\\t01b39f6abc5a410de4.jpg");
            image = ImageIO.read(file);
            int width = image.getWidth();
            int height = image.getHeight();

            for (int j = 0; j < height; j++) {
                for (int i = 0; i < width; i++) {
                    int p = image.getRGB(i, j);

                    int a = (p >> 24) & 0xff;
                    int r = (p >> 16) & 0xff;
                    int g = (p >> 8) & 0xff;
                    int b = p & 0xff;
                    int avg = (r + g + b) / 3;
                    p = (a << 24) | (avg << 16) | (avg << 8) | avg;
                    image.setRGB(i, j, p);
                }
            }
            file = new File("C:\\Users\\Admin\\Desktop\\灰度.jpg");
            System.out.println("灰度图片完成去看看吧");
            ImageIO.write(image, "jpg", file);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}

