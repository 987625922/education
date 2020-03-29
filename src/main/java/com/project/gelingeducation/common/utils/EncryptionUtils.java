package com.project.gelingeducation.common.utils;


import com.google.gson.JsonObject;
import com.project.gelingeducation.common.dto.JsonData;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.util.Base64;

/**
 * base64 加密与解密
 */
public final class EncryptionUtils {

    /**
     * 进行MD5加密
     *
     * @param text 要加密的字符串
     * @return 加密后的字符串，处理失败返回原本文字
     */
    public static String MD5(String text) {
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            byte[] btInput = text.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (byte byte0 : md) {
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str).toLowerCase();
        } catch (Exception e) {
            return text;
        }
    }

    /**
     * base64解密
     * @param encodedText
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String encodeBASE64(String encodedText) throws UnsupportedEncodingException {
        final Base64.Decoder decoder = Base64.getDecoder();
        return new String(decoder.decode(encodedText), "UTF-8");
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


    /**
     * 加密字符串输出
     *
     * @param jsonData bean
     * @return 加密后的字符串
     */
    public static String getEncodedPostString(JsonData jsonData) throws Exception {

        JsonObject resultObject;
        String data;
        String sign;
        String objectJson;
        String signStr;

        objectJson = GsonUtils.GsonString(jsonData);

        signStr = objectJson + ",bLsBMeMaN10pN8z64TQ0fC3fztDlRsPn";


        //加密处理
        data = EncryptionUtils.decodeBASE64(objectJson);
        sign = EncryptionUtils.MD5(signStr);

        resultObject = new JsonObject();
        resultObject.addProperty("data", data);
        resultObject.addProperty("sign", sign);

        return resultObject.toString();
    }

}