package com.ma.common.utils;

import java.io.File;

public class GetAllFiles {

    /**
     * 获取目录下的所有文件，包括文件夹
     * @param dirPath
     * @return
     * @throws Exception
     */
    static File[] getFiles(String dirPath) throws Exception {
        File dir = new File(dirPath);
        File[] files;
        if(!dir.exists()){
            System.out.println("目录不存在！");
        }
        if(dir.isDirectory()){
            files = dir.listFiles();
        }else{
            System.out.println("请输入目录路径！");
            throw new Exception();
        }
        return files;
    }

    public static void main(String[] args) throws Exception {
        File[] files = getFiles("C:\\Users\\youj\\Desktop\\智能软件开发");
        for (File file : files ) {
            if(file.isFile()){
                System.out.println(file.getAbsolutePath());
            }

        }
    }

}
