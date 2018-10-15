package com.rock.Utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TxtUtils {

    /**
     * 获取文件编码格式
     *
     * @param file TXT文件路径
     * @return 文件编码格式
     * @throws IOException
     */
    public static String getChatset(String file) throws IOException {
        BufferedInputStream bin = new BufferedInputStream(new FileInputStream(file));

        int p = (bin.read() << 8) + bin.read();
        bin.close();
        String code = null;
        switch (p) {
            case 0xefbb:
            case 0xe6b5:
                code = "UTF-8";
                break;
            case 0xfffe:
                code = "Unicode";
                break;
            case 0xfeff:
                code = "UTF-16BE";
                break;
            default:
                code = "GBK";
        }
        return code;
    }

    /**
     * 一行一行读取文件
     *
     * @param txtFile TXT文件路径
     * @return 结果集
     */
    public static List<String> readLine(String txtFile) {
        List<String> result = new ArrayList<String>();
        BufferedReader reader = null;
        FileInputStream fileInputStream = null;
        InputStreamReader isr = null;
        try {
            fileInputStream = new FileInputStream(txtFile);
            isr = new InputStreamReader(fileInputStream, getChatset(txtFile));
            reader = new BufferedReader(isr);

            //读取Txt文件内容
            String tempString = null;
            //一行一行读
            while ((tempString = reader.readLine()) != null) {
                result.add(tempString);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                if (reader != null) {
                    reader.close();
                }
                if (isr != null) {
                    isr.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 写TXT文件
     *
     * @param contentList 内容集合，一个为一行
     * @param filePath    存储路径
     * @param fileName    文件名，带后缀
     * @param ChangeLine  是否换行
     * @param encoding    编码格式
     * @throws IOException
     */
    public static void writeLine(List<String> contentList, String filePath, String fileName, boolean ChangeLine, String encoding) throws IOException {

        //文件路径
        File file = new File(filePath + "/" + fileName);

        //是否存在，不存在则创建
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile(); // 创建新文件
        }

        OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(file), encoding);
        BufferedWriter out = new BufferedWriter(write);
        //一行一行写
        if (ChangeLine) {
            //换行
            for (String content : contentList) {
                out.write(content + "\r\n");
            }
        } else {
            for (String content : contentList) {
                out.write(content);
            }
        }

        out.flush(); // 把缓存区内容压入文件
        out.close(); // 最后记得关闭文件
    }

}
