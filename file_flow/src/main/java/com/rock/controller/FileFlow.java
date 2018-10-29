package com.rock.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.OutputStream;

@RestController
public class FileFlow {

    /**
     * 返回文件流接口
     *
     * @param response
     */
    @RequestMapping(value = "/getImageGet", method = RequestMethod.GET)
    public void downImageGet(HttpServletResponse response) {

        OutputStream output = null;
        FileInputStream input = null;
        try {
            output = response.getOutputStream();
            //文件地址
            input = new FileInputStream("D:\\Software\\IDEAImage\\wallhaven-700058.jpg");

            byte[] bts = new byte[1024];
            int len = -1;
            while ((len = input.read(bts)) != -1) {
                output.write(bts, 0, len);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @RequestMapping(value = "/getImagePost", method = RequestMethod.POST)
    public void downImage(HttpServletResponse response) {

        OutputStream output = null;
        FileInputStream input = null;
        try {
            output = response.getOutputStream();
            //文件地址
            input = new FileInputStream("D:\\Software\\IDEAImage\\wallhaven-700058.jpg");

            byte[] bts = new byte[1024];
            int len = -1;
            while ((len = input.read(bts)) != -1) {
                output.write(bts, 0, len);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
