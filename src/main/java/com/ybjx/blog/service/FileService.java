package com.ybjx.blog.service;

import com.ybjx.blog.config.FileConfig;
import org.springframework.stereotype.Service;

import java.io.*;

/**
 * create by YuBing at 2019/2/17
 */
@Service
public class FileService {

    /**
     * 文件配置
     */
    private FileConfig fileConfig;

    /**
     * 构造方法
     * @param fileConfig 注入的文件配置
     */
    public FileService(FileConfig fileConfig) {
        this.fileConfig = fileConfig;
    }

    public boolean saveFile(String type, String uuid, InputStream content) {
        String path = fileConfig.getLocation() + "/" + type;
        File f = new File(path);
        if (!f.exists()) {
            if (!f.mkdirs()) {
                return false;
            }
        }
        path += "/" + uuid;
        try {
            f = new File(path);
            if (!f.exists()) {
                if (!f.createNewFile()) {
                    return false;
                }
            }
            FileOutputStream outputStream = new FileOutputStream(path);
            byte[] buf = new byte[1024];
            while (true) {
                int r = content.read(buf);
                if (r == -1) {
                    break;
                }
                outputStream.write(buf, 0, r);
            }
            outputStream.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean readFile(String type, String uuid, OutputStream outputStream) {
        String path = fileConfig.getLocation() + "/" + type + "/" + uuid;
        File f = new File(path);
        if (!f.exists()) {
            return false;
        }
        try {
            FileInputStream inputStream = new FileInputStream(path);
            byte[] buf = new byte[1024];
            while (true) {
                int r = inputStream.read(buf);
                if (r == -1) {
                    break;
                }
                outputStream.write(buf, 0, r);
            }
            inputStream.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
