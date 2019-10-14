package com.qsmx.zww.demo.java8;


import java.io.FileOutputStream;
import java.io.FileReader;


public class Demo {


    public static void main(String[] args) throws Exception {

        FileReader file = new FileReader("C:\\Users\\ZHANGWEI\\Desktop\\csv\\csv.txt");
        FileOutputStream outputStream = new FileOutputStream("C:\\Users\\ZHANGWEI\\Desktop\\csv\\output.txt");
        int ch = 0;
        String result1 = new String();
        StringBuffer result2 = new StringBuffer("\"");
        char[] chars = new char[1024];
        while ((ch = file.read(chars)) != -1) {//单个字符进行读取
            String ss = new String(chars);
            for (int i = 0; i < ss.length() - 2; i++) {
                String s = ss.substring(i, i + 1);
                String snext = ss.substring(i + 1, i + 2);
                if (s.contains("\"") && snext.contains(",")) {
                    result1 += "]" + s;
                } else if (snext.contains("\"") && s.contains(",")) {
                    result1 += s + "[";
                } else {
                    result1 += s;
                }
            }
        }
        file.close();
        for (int i = 0; i < result1.length() - 2; i++) {
            String s2 = result1.substring(i, i + 1);
            String snext2 = result1.substring(i + 1, i + 2);
            if (s2.contains(",") && !snext2.contains("\"")) {
                result2.append(s2 + "\"");
            } else if (!s2.contains("\"") && snext2.contains(",")) {
                result2.append(s2 + "\"");
            } else {
                result2.append(s2);
            }
            if (snext2.contains("\n") || s2.contains("\n")) {
                result2.append("\"");
            }
        }
        result2.append("\"");
        outputStream.write(result2.toString().getBytes("UTF-8"));
    }
}
