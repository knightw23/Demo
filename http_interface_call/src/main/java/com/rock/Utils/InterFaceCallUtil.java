package com.rock.Utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * java 请求HTTP接口
 */
public class InterFaceCallUtil {

    /**
     * POST请求Json格式
     *
     * @param url 请求URL
     * @param obj 参数对象
     * @return 响应结果JSON字符串
     * @throws Exception
     */
    public static String doPost(String url, Object obj) {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost(url);

        //将对象转为Json字符串
        String parameJson = JSONObject.toJSONString(obj);

        //设置参数格式
        httpPost.addHeader(HTTP.CONTENT_TYPE, "application/json");

        StringEntity entity;

        try {
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
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            httpPost.releaseConnection();
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 表单方式提交
     *
     * @param url 请求路径
     * @param map 参数
     * @return
     */
    public static String doPost(String url, Map<String, String> map) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpost = new HttpPost(url);
        httpost.setHeader("Content-type", "application/x-www-form-urlencoded");
        if (map != null) {
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            for (String key : map.keySet()) {
                nvps.add(new BasicNameValuePair(key, map.get(key)));
            }
            httpost.setEntity(new UrlEncodedFormEntity(nvps, Consts.UTF_8));
        }
        try {
            CloseableHttpResponse response = httpClient.execute(httpost);
            String strResult = EntityUtils.toString(response.getEntity());
            return strResult;
        } catch (Exception ex) {

        } finally {
            httpost.releaseConnection();
            try {
                httpClient.close();
            } catch (IOException e) {

            }
        }
        return null;
    }

    /**
     * GET方式请求
     *
     * @param url 请求URL注意拼接上参数
     * @return 响应回来的数据Json字符串
     * @throws Exception
     */
    public static String doGet(String url) {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet httpGet = new HttpGet(url);

        HttpResponse response;
        try {
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
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            httpGet.releaseConnection();
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    /**
     * 下载文件
     *
     * @param url      接口地址
     * @param filePath 文件保存地址
     */
    public static boolean httpDownloadGet(String url, String filePath, String imageName) {
        CloseableHttpClient httpclient = HttpClients.createDefault();

        File file = new File(filePath);
        if (!file.exists()) {
            file.mkdirs();
        }
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
                FileOutputStream fos = new FileOutputStream(filePath + imageName);
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
            return false;
        } finally {
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }


    /**
     * 下载文件
     *
     * @param url      接口地址
     * @param filePath 文件保存地址
     */
    public static void httpDownloadPost(String url, String filePath) {
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

