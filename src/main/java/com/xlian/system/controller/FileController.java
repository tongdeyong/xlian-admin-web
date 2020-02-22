package com.xlian.system.controller;

import com.xlian.common.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@Slf4j
@RequestMapping("/file")
public class FileController {

    @Value("${static-resource.path")
    private String staticResourcePath;

    @PostMapping("/uploadFeFile")
    public Result uploadFeFile(@NotNull MultipartFile file, @NotNull String directory) {
        File fileObj = new File(staticResourcePath + directory);
        if (fileObj.exists()) {
            if (!fileObj.mkdirs()) {
                return Result.error("文件夹创建失败");
            }
        }
        try {
            byte[] fileBytes = file.getBytes();
            Path path = Paths.get(staticResourcePath + directory + "/" + file.getName() + "." + file.getOriginalFilename());
            Files.write(path, fileBytes);
            return Result.ok();
        } catch (IOException e) {
            log.error("文件{}读取失败", file.getName(), e);
            return Result.error("文件读取失败");
        }
    }
}
