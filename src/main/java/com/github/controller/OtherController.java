package com.github.controller;

import com.github.pojo.Result;
import com.github.properties.FileConfigProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @ClassName OtherController
 * @Description 其他接口
 * @Author qyh
 * @Date 2024/1/30 18:20
 * @Version 1.0
 **/
@RestController
public class OtherController {

    private final FileConfigProperties fileConfigProperties;

    @Autowired
    public OtherController(FileConfigProperties fileConfigProperties){
        this.fileConfigProperties = fileConfigProperties;
    }

    @PostMapping("/upload")
    public Result<String> upload(@RequestPart("file")MultipartFile multipartFile) throws IOException {
        String fileName = multipartFile.getOriginalFilename();
        fileName = UUID.randomUUID().toString().replaceAll("-", "").toLowerCase() + fileName;
        String savePath = fileConfigProperties.getSaveRootPath() + fileName;
        String httpPath = fileConfigProperties.getHttpRootPath() + fileName;
        multipartFile.transferTo(new File(savePath));
        return Result.success(httpPath);
    }
}
