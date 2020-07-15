package com.project.gelingeducation.common.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @Author: LL
 * @Date: 2020/7/15 22:56
 * @Description:验证码输出前端类
 * @备注： 1.
 */
@Getter
@Setter
@Accessors(chain = true)
public class VerifyCodeDto {
    /**
     * 验证码图片的base64
     */
    String imageBase64;
    /**
     * 验证码在redis中的key
     */
    String key;
}
