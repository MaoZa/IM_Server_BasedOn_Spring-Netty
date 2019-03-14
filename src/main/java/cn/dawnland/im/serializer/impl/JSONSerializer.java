package cn.dawnland.im.serializer.impl;

import cn.dawnland.im.serializer.Serializer;
import cn.dawnland.im.serializer.SerializerAlgorithm;
import com.alibaba.fastjson.JSONObject;

/**
 * @author Cap_Sub
 */
public class JSONSerializer implements Serializer {

    @Override
    public byte getSerializerAlgorithm() {
        return SerializerAlgorithm.JSON;
    }

    @Override
    public byte[] serialize(Object object) {

        return JSONObject.toJSONBytes(object);
    }

    @Override
    public <T> T deserialize(Class<T> clazz, byte[] bytes) {

        return JSONObject.parseObject(bytes, clazz);
    }
}
