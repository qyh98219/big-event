package com.github.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @ClassName FileConfigProperties
 * @Description 文件保存属性类
 * @Author qyh
 * @Date 2024/1/30 18:29
 * @Version 1.0
 **/
@Data
@Component
@ConfigurationProperties(prefix = "file")
public class FileConfigProperties {
    private String saveRootPath;
    private String httpRootPath;
}
