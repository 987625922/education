package com.project.gelingeducation.common.server;

import com.project.gelingeducation.common.config.GLConstant;
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
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 验证码服务
 *
 * @author LL
 */
@Service
public class ValidateCodeService {

    @Autowired
    private RedisTemplateUtil redisService;


    public void create(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        String key = session.getId();
        ValidateCodeProperties code = new ValidateCodeProperties();
        setHeader(response, code.getType());

        Captcha captcha = createCaptcha(code);
        redisService.set(GLConstant.CODE_PREFIX + key,
                StringUtils.lowerCase(captcha.text()), code.getTime());
        captcha.out(response.getOutputStream());
    }


    public void check(String key, String value) throws Exception {
        Object codeInRedis = redisService.get(GLConstant.CODE_PREFIX + key);
        if (StringUtils.isBlank(value)) {
            throw new AllException(StatusEnum.NO_LOGIN_INPUT_CODE);
        }
        if (codeInRedis == null) {
            throw new AllException(StatusEnum.LOGIN_INPUT_CODE_TIMEOUT);
        }
        if (!StringUtils.equalsIgnoreCase(value, String.valueOf(codeInRedis))) {
            throw new AllException(StatusEnum.INPUT_CODE_CODE_ERROR);
        }
    }

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

    private void setHeader(HttpServletResponse response, String type) {
        if (StringUtils.equalsIgnoreCase(type, ImageType.GIF)) {
            response.setContentType(MediaType.IMAGE_GIF_VALUE);
        } else {
            response.setContentType(MediaType.IMAGE_PNG_VALUE);
        }
        response.setHeader(HttpHeaders.PRAGMA, "No-cache");
        response.setHeader(HttpHeaders.CACHE_CONTROL, "No-cache");
        response.setDateHeader(HttpHeaders.EXPIRES, 0L);
    }
}
