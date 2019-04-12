package com.qsmx.zww.uitil;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import java.io.*;

/**
 * Created by zww on 2019-04-12.
 */
public class FtpImageUploadUtil {
    /**
     * @描述：
     * @参数： [url 资源服务器ip地址, ftp port 端口, username 用户名, password 密码, path 文件保存路径(ftp相对路径), filename 文件名, input 文件流]
     * @返回值： boolean
     * @创建人： zhangww
     * @创建时间： 2019-04-10
     * @修改人和其它信息：
     */
    public static String uploadFile(String url, int port, String username, String password, String path, String filename, InputStream input) {

        boolean success = false;
        FTPClient ftp = new FTPClient();
        // ftp.setControlEncoding("UTF-8");//设置编码
        try {
            ftp.connect(url, port);//连接FTP服务器
            //如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器
            ftp.login(username, password);//登录
            int reply = ftp.getReplyCode();
            System.out.println("登录状态码：" + reply);//打印连接状态
            if (!FTPReply.isPositiveCompletion(reply)) {//连接ftp失败
                ftp.disconnect();
                return null;
            }
            ftp.changeWorkingDirectory(path);
            //ftp.enterLocalPassiveMode();//设置被动模式
            ftp.enterLocalActiveMode();   //设置主动模式
            ftp.setFileType(FTP.BINARY_FILE_TYPE);//设置为二进制上传，以免图片失真
            success = ftp.storeFile(filename, input);
            if (success) {
                String imageUrl = url + "/picture/" + filename;//picture为nginx映射路径（指向path）
                return imageUrl;
            }
            input.close();
            ftp.logout();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
        }
        return null;//上传失败
    }

    /**
     * @描述： 下载
     * @参数： [url 静态服务器地址, port 端口, username 用户名, password 密码, remotePath 远端路径, fileName 文件名, localPath 本地路径]
     * @返回值： boolean
     * @创建人： zhangww
     * @创建时间： 2019-04-10
     * @修改人和其它信息：
     */
    public static boolean downFile(String url, int port, String username, String password, String remotePath, String fileName, String localPath) {
        boolean success = false;
        FTPClient ftp = new FTPClient();
        try {
            int reply;
            ftp.connect(url, port);
            //如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器
            ftp.login(username, password);//登录
            reply = ftp.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftp.disconnect();
                return success;
            }
            ftp.changeWorkingDirectory(remotePath);//转移到FTP服务器目录
            FTPFile[] fs = ftp.listFiles();
            for (FTPFile ff : fs) {
                if (ff.getName().equals(fileName)) {
                    File localFile = new File(localPath + "/" + ff.getName());

                    OutputStream is = new FileOutputStream(localFile);
                    ftp.retrieveFile(ff.getName(), is);
                    is.close();
                }
            }

            ftp.logout();
            success = true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return success;


    }
}
