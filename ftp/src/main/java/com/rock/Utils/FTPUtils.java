package com.rock.Utils;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class FTPUtils {

    private static Logger LOG = LogManager.getLogger(FTPUtils.class);

    /**
     * 获取FTPClient对象
     *
     * @param host     连接地址
     * @param port     端口
     * @param userName 用户名
     * @param password 密码
     * @return
     * @throws IOException
     */
    public static FTPClient getConnect(String host, int port, String userName, String password) throws IOException {
        FTPClient ftpClient = new FTPClient();
        ftpClient.connect(host, port);
        ftpClient.login(userName, password);
        ftpClient.enterLocalPassiveMode();
        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
        return ftpClient;
    }


    /**
     * 下载文件
     *
     * @param ftpClient ftpClient
     * @param filename  文件名
     * @param localpath 本地存储地址
     * @return
     */
    public static boolean downloadFile(FTPClient ftpClient, String filename, String localpath) {
        boolean status = false;
        OutputStream os = null;
        try {
            LOG.info("开始下载文件,文件名为:{}", filename);
            //判断文件夹是否存在
            File file = new File(localpath);
            if (!file.isDirectory()) {
                file.mkdirs();
            }

            File localFile = new File(localpath + "/" + filename);
            os = new FileOutputStream(localFile);
            status = ftpClient.retrieveFile(filename, os);
        } catch (Exception e) {
            LOG.error("下载文件出错");
            e.printStackTrace();
        } finally {
            if (null != os) {
                try {
                    os.close();
                } catch (IOException e) {
                    LOG.error("关闭流失败，{}", e.getMessage());
                }
            }
        }
        return status;
    }
}
