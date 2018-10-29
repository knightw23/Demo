package com.rock.Utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.*;

/**
*java 请求HTTP接口
*/
public class InterFaceCallUtil {

    public static void main(String[] args) {
        httpDownloadFile("http://127.0.0.1:8080/getImage", "D:\\iamge\\a.jpg");
    }

    /**
     * POST请求
     *
     * @param url        请求URL
     * @param parameJson 参数Json字符串
     * @return 响应结果JSON字符串
     * @throws Exception
     */
    public static String doPost(String url, String parameJson) throws Exception {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost(url);

        //设置参数格式
        httpPost.addHeader(HTTP.CONTENT_TYPE, "application/json");

        StringEntity entity;
        entity = new StringEntity(parameJson);

        httpPost.setEntity(entity);
        HttpResponse response;
        response = httpClient.execute(httpPost);
        HttpEntity entitys = response.getEntity();
        StringBuffer buffer = new StringBuffer();

        //读取响应回来的数据
        try (BufferedReader in = new BufferedReader(new InputStreamReader(entitys.getContent(), "UTF-8"))) {
            String line = null;
            while ((line = in.readLine()) != null) {
                buffer.append(line);
            }
        }
        return buffer.toString();
    }

    /**
     * GET方式请求
     *
     * @param url 请求URL注意拼接上参数
     * @return 响应回来的数据Json字符串
     * @throws Exception
     */
    public static String doGet(String url) throws Exception {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet httpGet = new HttpGet(url);

        HttpResponse response;
        response = httpClient.execute(httpGet);

        //获取返回
        HttpEntity entitys = response.getEntity();
        StringBuffer buffer = new StringBuffer();

        //读取数据
        try (BufferedReader in = new BufferedReader(new InputStreamReader(entitys.getContent(), "UTF-8"))) {
            String line = null;
            while ((line = in.readLine()) != null) {
                buffer.append(line);
            }
        }
        return buffer.toString();
    }

}

