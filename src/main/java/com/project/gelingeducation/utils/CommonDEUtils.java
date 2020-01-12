package com.project.gelingeducation.utils;

import com.google.gson.JsonObject;
import com.project.gelingeducation.domain.JsonData;

public class CommonDEUtils {

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
