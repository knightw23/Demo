package com.rock.filetest;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 下载文件流文件
 */
public class FileTest {

    public static void main(String[] args) {
        httpDownloadPost("http://127.0.0.1:8080/getImagePost", "D:\\iamge\\a.jpg");
    }

    /**
     * 下载文件
     *
     * @param url      接口地址
     * @param filePath 文件保存地址
     */
    private static void httpDownloadGet(String url, String filePath) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            HttpGet httpGet = new HttpGet(url);
            CloseableHttpResponse response = httpclient.execute(httpGet);
            try {
                HttpEntity httpEntity = response.getEntity();
                //获取响应中的文件流
                InputStream is = httpEntity.getContent();
                // 根据InputStream 下载文件
                ByteArrayOutputStream output = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int r = 0;
                long totalRead = 0;
                while ((r = is.read(buffer)) > 0) {
                    output.write(buffer, 0, r);
                    totalRead += r;
                }
                FileOutputStream fos = new FileOutputStream(filePath);
                output.writeTo(fos);
                output.flush();
                output.close();
                fos.close();
                EntityUtils.consume(httpEntity);
            } finally {
                response.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 下载文件
     *
     * @param url      接口地址
     * @param filePath 文件保存地址
     */
    private static void httpDownloadPost(String url, String filePath) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            HttpPost httpGet = new HttpPost(url);
            CloseableHttpResponse response = httpclient.execute(httpGet);
            try {
                HttpEntity httpEntity = response.getEntity();
                long contentLength = httpEntity.getContentLength();
                InputStream is = httpEntity.getContent();
                // 根据InputStream 下载文件
                ByteArrayOutputStream output = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int r = 0;
                long totalRead = 0;
                while ((r = is.read(buffer)) > 0) {
                    output.write(buffer, 0, r);
                    totalRead += r;
                }
                FileOutputStream fos = new FileOutputStream(filePath);
                output.writeTo(fos);
                output.flush();
                output.close();
                fos.close();
                EntityUtils.consume(httpEntity);
            } finally {
                response.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
