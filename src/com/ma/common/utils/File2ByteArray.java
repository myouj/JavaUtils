package com.ma.common.utils;

import java.io.*;

public class File2ByteArray {

    /**
     * 把文件写入到byte[]数组中
     * @param filePath
     * @return
     * @throws IOException
     */
    static byte[] inputStream2ByteArray(String filePath) throws IOException {
        InputStream is = new FileInputStream(filePath);
        byte[] data = toByteArray(is);
        is.close();
        return data;

    }

    static byte[] toByteArray(InputStream in) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[in.available()];
        int n = 0;
        while ((n = in.read(buffer)) != -1) {
            baos.write(buffer, 0, n);
        }
        return baos.toByteArray();
    }

    /**
     * 把byte[] 数组的内容写到文件中
     * @param code
     * @throws IOException
     */
    static void writer(byte[] code) throws IOException {
        File file =  new File("abc.txt");
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(code);
        fos.close();
    }

}
