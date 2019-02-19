package com.ybjx.blog.controller;

import com.ybjx.blog.common.BlogException;
import com.ybjx.blog.common.ErrorCode;
import com.ybjx.blog.common.result.PojoResult;
import com.ybjx.blog.service.FileService;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

/**
 * 文件上传下载的接口
 * create by YuBing at 2019/2/17
 */
@RequestMapping(value = "/api/")
@RestController
public class FileController {

    private FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @RequestMapping(value = "/image/upload", method = RequestMethod.POST)
    public PojoResult<String> uploadPicture(
            @RequestParam("picture") MultipartFile file,
            @RequestParam(value = "fileId", required = false) String fileId) throws IOException {
        if (StringUtils.isEmpty(fileId)) {
            fileId = UUID.randomUUID().toString();
            fileId = fileId.replaceAll("-", "");
        }
        if (fileService.saveFile("image", fileId, file.getInputStream())) {
            return new PojoResult<>(fileId);
        } else {
            throw new BlogException(ErrorCode.FILE_SAVE_ERROR);
        }
    }

    @RequestMapping(value = "/image/{fileId}", method = RequestMethod.GET)
    public void loadPicture(@PathVariable("fileId") String fileId, HttpServletResponse response) throws IOException {
        response.setContentType("image/png;image/jpeg");
        fileService.readFile("image", fileId, response.getOutputStream());
    }
}
