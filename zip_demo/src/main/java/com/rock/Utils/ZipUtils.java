package com.rock.Utils;


import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.model.FileHeader;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Expand;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ZipUtils {

    public static void main(String[] args) {

        List<String> unzip = unzip("D:\\uploads\\20181101_ztry.zip", "D:\\uploadssss");

    }

    /**
     * 解压文件
     *
     * @param fileSource        压缩包路径
     * @param decompressionFile 解压后文件的目录，
     */
    public static List<String> unzip(String fileSource, String decompressionFile) {
        ArrayList<String> result = new ArrayList<String>();
        try {
            File zipFile = new File(fileSource);
            ZipFile zFile = new ZipFile(zipFile);  // 首先创建ZipFile指向磁盘上的.zip文件
            zFile.setFileNameCharset("GBK");
            File destDir = new File(decompressionFile);// 解压目录
            zFile.extractAll(decompressionFile);// 将文件抽出到解压目录(解压)

            //region 输出解压文件里面每个文件的文件名
            List<FileHeader> headerList = zFile.getFileHeaders();
            List<File> extractedFileList = new ArrayList<File>();
            for (FileHeader fileHeader : headerList) {

                if (!fileHeader.isDirectory()) {
                    extractedFileList.add(new File(destDir, fileHeader.getFileName()));
                }
            }

            File[] extractedFiles = new File[extractedFileList.size()];

            extractedFileList.toArray(extractedFiles);

            for (File f : extractedFileList) {
                result.add(f.getName());
            }
            //endregion
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 解压文件
     *
     * @param zipFilepath 需要被解压的zip文件路径
     * @param destDir     解压到哪个文件夹
     * @return
     */
    public static List<String> unzipAnt(String zipFilepath, String destDir) {
        if (!new File(zipFilepath).exists()) {
            System.out.println("文件夹不存在");
        }

        Project proj = new Project();
        Expand expand = new Expand();
        expand.setProject(proj);
        expand.setTaskType("unzip");
        expand.setTaskName("unzip");
        expand.setEncoding("GBK");

        expand.setSrc(new File(zipFilepath));
        expand.setDest(new File(destDir));
        expand.execute();
        List<String> allFileName = getAllFileName(destDir);
        return allFileName;
    }


    /**
     * 获取文件夹下面所有文件的名称
     *
     * @param path 路径
     * @return 文件名的集合
     */
    public static List<String> getAllFileName(String path) {
        List<String> fileNames = new ArrayList<String>();
        File f = new File(path);
        if (!f.exists()) {
            return null;
        }
        File fa[] = f.listFiles();
        for (int i = 0; i < fa.length; i++) {
            File fs = fa[i];
            if (fs.isDirectory()) {
                getAllFileName(path + "/" + fs.getName());
            } else {
                fileNames.add(fs.getName());
            }
        }
        return fileNames;
    }

}
