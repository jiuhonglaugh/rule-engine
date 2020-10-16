package utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;

public class JsonMapUtils<K, V> {
    private HashMap<K, V> hashMap;
    private String jsonStr;
    private JSONObject jsonObject;

    public JsonMapUtils(HashMap<K, V> hashMap) {
        this.hashMap = hashMap;
        this.jsonObject = mapToJsonObj(hashMap);
        this.jsonStr = mapToStrJson(hashMap);
    }

    public JsonMapUtils(String jsonStr) {
        this.jsonStr = jsonStr;
        this.hashMap = jsonStrToHashMap(jsonStr);
        this.jsonObject = JSONObject.parseObject(jsonStr);
    }

    public JsonMapUtils(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
        this.jsonStr = jsonObject.toJSONString();
        this.hashMap = jsonObjectToHashMap(jsonObject);
    }

    public String getString(K key) {
        return hashMap.get(key).toString();
    }


    public Integer getInteger(K key) {
        return Integer.valueOf(hashMap.get(key).toString());
    }

    public Long getLong(K key) {
        return Long.valueOf(hashMap.get(key).toString());
    }

    public Double getDouble(K key) {
        return Double.valueOf(hashMap.get(key).toString());
    }

    /**
     * map 装换为 Json 字符串
     *
     * @param hashMap 传入的map
     * @return 返回 jsonStr
     */
    public String mapToStrJson(HashMap<K, V> hashMap) {
        return JSONObject.toJSONString(hashMap);
    }

    /**
     * map 装换为 Json 字符串
     *
     * @param hashMap 传入的map
     * @return 返回 jsonObj
     */
    public JSONObject mapToJsonObj(HashMap<K, V> hashMap) {
        return JSONObject.parseObject(mapToStrJson(hashMap));
    }

    /**
     * json 字符串转 HashMap
     *
     * @param jsonStr   json格式字符串
     * @return          返回map
     */
    public HashMap<K, V> jsonStrToHashMap(String jsonStr) {
        return JSON.parseObject(jsonStr, HashMap.class);
    }

    /**
     * jsonobject 转 hashmap
     *
     * @param jsonObject    json对象
     * @return              返回hashmap
     *
     */
    public HashMap<K, V> jsonObjectToHashMap(JSONObject jsonObject) {
        return jsonStrToHashMap(jsonObject.toJSONString());
    }

    public HashMap<K, V> getHashMap() {
        return hashMap;
    }

    public String getJsonStr() {
        return jsonStr;
    }

    public JSONObject getJsonObject() {
        return jsonObject;
    }
}
