package com.ybjx.blog.service;

import com.ybjx.blog.common.BlogException;
import com.ybjx.blog.common.ErrorCode;
import com.ybjx.blog.config.FileConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.*;

/**
 * 文件相关的服务
 * @author ybjx
 * @date 2019/2/17
 */
@Service
public class FileService {

    /**
     * 日志
     */
    private static Logger LOGGER = LoggerFactory.getLogger(FileService.class);

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

    /**
     * 保存文件
     * @param type 文件类型
     * @param uuid 文件uuid
     * @param content 文件内容
     */
    public void saveFile(String type, String uuid, InputStream content) {
        String path = fileConfig.getLocation() + "/" + type;
        File f = new File(path);
        if (!f.exists()) {
            if (!f.mkdirs()) {
                LOGGER.error("创建文件夹不成功，文件夹地址：" + path);
                throw new BlogException(ErrorCode.FILE_SAVE_ERROR);
            }
        }
        path += "/" + uuid;
        FileOutputStream outputStream = null;
        try {
            f = new File(path);
            if (!f.exists()) {
                if (!f.createNewFile()) {
                    LOGGER.error("创建文件出错，文件路径：" + path);
                    throw new BlogException(ErrorCode.FILE_SAVE_ERROR);
                }
            }
            outputStream = new FileOutputStream(path);
            byte[] buf = new byte[1024];
            while (true) {
                int r = content.read(buf);
                if (r == -1) {
                    break;
                }
                outputStream.write(buf, 0, r);
            }
        } catch (Exception e) {
            throw new BlogException(ErrorCode.FILE_SAVE_ERROR, e);
        }
        finally {
            if(outputStream != null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    LOGGER.error("关闭输出流出错", e);
                }
            }
        }
    }

    /**
     * 读取文件内容
     * @param type 文件类型
     * @param uuid 文件uuid
     * @param outputStream 输出流
     */
    public void readFile(String type, String uuid, OutputStream outputStream) {
        String path = fileConfig.getLocation() + "/" + type + "/" + uuid;
        File f = new File(path);
        if (!f.exists()) {
            LOGGER.error("文件不存在：" + path);
            throw new BlogException(ErrorCode.FILE_READ_ERROR);
        }
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(path);
            byte[] buf = new byte[1024];
            while (true) {
                int r = inputStream.read(buf);
                if (r == -1) {
                    break;
                }
                outputStream.write(buf, 0, r);
            }
        } catch (Exception e) {
            throw new BlogException(ErrorCode.FILE_READ_ERROR, e);
        }
        finally {
            if(inputStream != null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    LOGGER.error("关闭输入流出错", e);
                }
            }
        }
    }
}
