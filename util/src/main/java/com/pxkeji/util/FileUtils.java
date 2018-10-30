package com.pxkeji.util;

import java.io.File;
import java.text.DecimalFormat;

public class FileUtils {

    // 递归方式 计算文件的大小
    public static long getTotalSizeOfFilesInDir(final File file) {
        if (file.isFile())
            return file.length();
        final File[] children = file.listFiles();
        long total = 0;
        if (children != null && children.length > 0)
            for (final File child : children)
                total += getTotalSizeOfFilesInDir(child);
        return total;
    }

    // 内存格式化
    public final static String formatFileSize(long bytes) {
        if (bytes < 1024)
            return FileUtils.formatFileSize2(bytes) + " B";

        double kb = bytes / 1024;

        if (kb < 1024)
            return formatFileSize2(kb) + " KB";

        double mb = kb / 1024;

        if (mb < 1024)
            return formatFileSize2(mb) + " MB";

        double gb = mb / 1024;

        return formatFileSize2(gb) + " GB";
    }

    private static String formatFileSize2(double size) {
        return new DecimalFormat("0.0").format(size);
    }

    public static void deleteDir(String path){
        File file = new File(path);
        if(!file.exists()){
            //判断是否待删除目录是否存在
             	System.err.println("The dir are not exists!");
             	return;
        }

        String[] content = file.list();//取得当前目录下所有文件和文件夹

        for(String name : content){
            File temp = new File(path, name);
            if(temp.isDirectory()){ //判断是否是目录

                deleteDir(temp.getAbsolutePath()); //递归调用，删除目录里的内容

                temp.delete();//删除空目录
            }else{
                if(!temp.delete()){//直接删除文件
                    System.err.println("Failed to delete " + name);
                }
            }
        }


    }

}
