package com.project.gelingeducation.common.utils;

import com.project.gelingeducation.common.exception.AllException;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

/**
 * base64 加密与解密
 *
 * @author LL
 */
public final class EncryptionUtil {

    /**
     * base64解密
     *
     * @param encodedText
     * @return
     */
    public static String encodeBASE64(String encodedText) {
        final Base64.Decoder decoder = Base64.getDecoder();
        try {
            return new String(decoder.decode(encodedText), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new AllException(-103, "数据出错");
        }
    }

    /**
     * base64加密
     *
     * @param decodeText
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String decodeBASE64(String decodeText) throws UnsupportedEncodingException {
        final Base64.Encoder encoder = Base64.getEncoder();
        final byte[] textByte = decodeText.getBytes("UTF-8");
        //编码
        final String encodedText = encoder.encodeToString(textByte);
        return encodedText;
    }

}