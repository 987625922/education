package com.project.gelingeducation.common.server;

import com.project.gelingeducation.common.config.GLConstant;
import com.project.gelingeducation.common.dto.VerifyCodeDto;
import com.project.gelingeducation.common.exception.AllException;
import com.project.gelingeducation.common.exception.StatusEnum;
import com.project.gelingeducation.common.utils.RedisTemplateUtil;
import com.project.gelingeducation.entity.ImageType;
import com.project.gelingeducation.entity.ValidateCodeProperties;
import com.wf.captcha.GifCaptcha;
import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.base.Captcha;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author LL
 * @Description:验证码服务
 */
@Service
public class ValidateCodeService {

    /**
     * redis封装service
     */
    @Autowired
    private RedisTemplateUtil redisService;

    /**
     * 创建验证码
     *
     * @return 验证码返回类
     */
    public VerifyCodeDto create() {
        //验证码基本配置
        ValidateCodeProperties code = new ValidateCodeProperties();
        //验证码图片和code生成类
        Captcha captcha = createCaptcha(code);
        //验证码
        String verCode = captcha.text().toLowerCase();
        //验证码redis保存的key
        String key = UUID.randomUUID().toString();
        //保存验证码
        redisService.set(GLConstant.CODE_PREFIX + key,
                verCode, code.getTime());
        return new VerifyCodeDto().setKey(key).setImageBase64(captcha.toBase64());
    }

    /**
     * 检查验证码
     *
     * @param key   redis中保存验证码的key
     * @param value 验证码
     */
    public void check(String key, String value) {
        //从redis中获取验证码
        Object codeInRedis = redisService.get(GLConstant.CODE_PREFIX + key);
        //判断输入的验证码是否为空
        if (StringUtils.isBlank(value)) {
            throw new AllException(StatusEnum.NO_LOGIN_INPUT_CODE);
        }
        //判断从redis获取的验证码是否为空
        if (codeInRedis == null) {
            throw new AllException(StatusEnum.LOGIN_INPUT_CODE_TIMEOUT);
        }
        //判断验证码和redis中的是否一致
        if (!StringUtils.equalsIgnoreCase(value, String.valueOf(codeInRedis))) {
            throw new AllException(StatusEnum.INPUT_CODE_CODE_ERROR);
        }
    }

    /**
     * 创建随机数
     *
     * @param code
     * @return
     */
    private Captcha createCaptcha(ValidateCodeProperties code) {
        Captcha captcha = null;
        if (StringUtils.equalsIgnoreCase(code.getType(), ImageType.GIF)) {
            captcha = new GifCaptcha(code.getWidth(), code.getHeight(), code.getLength());
        } else {
            captcha = new SpecCaptcha(code.getWidth(), code.getHeight(), code.getLength());
        }
        captcha.setCharType(code.getCharType());
        return captcha;
    }
}
