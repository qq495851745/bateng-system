package com.bateng.utils;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class ToJsonString {
    public static String toDisableCircularReferenceDetectString(Object object){

        return JSONObject.toJSONString(object, SerializerFeature.DisableCircularReferenceDetect);
    }
}
