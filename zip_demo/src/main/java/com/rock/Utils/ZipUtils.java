package com.rock.Utils;


import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.model.FileHeader;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ZipUtils {

    public static void main(String[] args) {
        unzip("D:\\uploads\\20181101_ztry.zip", "D:\\uploadssss");
    }

    /**
     * 解压文件
     *
     * @param fileSource        压缩包路径
     * @param decompressionFile 解压后文件的目录，
     */
    public static void unzip(String fileSource, String decompressionFile) {

        try {
            File zipFile = new File(fileSource);
            ZipFile zFile = new ZipFile(zipFile);  // 首先创建ZipFile指向磁盘上的.zip文件
            zFile.setFileNameCharset("GBK");
            File destDir = new File(decompressionFile);// 解压目录
            zFile.extractAll(decompressionFile);// 将文件抽出到解压目录(解压)
            
            //region 输出解压文件里面每个文件的文件名
           /* List<FileHeader> headerList = zFile.getFileHeaders();
            List<File> extractedFileList = new ArrayList<File>();
            for (FileHeader fileHeader : headerList) {

                if (!fileHeader.isDirectory()) {
                    extractedFileList.add(new File(destDir, fileHeader.getFileName()));
                }
            }

            File[] extractedFiles = new File[extractedFileList.size()];

            extractedFileList.toArray(extractedFiles);

            for (File f : extractedFileList) {

                System.out.println(f.getAbsolutePath() + "....");

            }*/
            //endregion
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
