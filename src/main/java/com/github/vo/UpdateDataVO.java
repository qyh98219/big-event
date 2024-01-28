package com.github.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @ClassName UpdateDataVO
 * @Description 修改密码前端上传数据
 * @Author qyh
 * @Date 2024/1/28 18:04
 * @Version 1.0
 **/
@Data
public class UpdateDataVO {
    @JsonProperty("old_pwd")
    private String oldPwd;
    @JsonProperty("new_pwd")
    private String newPwd;
    @JsonProperty("re_pwd")
    private String rePwd;
}
