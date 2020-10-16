package aviator;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.googlecode.aviator.AviatorEvaluator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;


public class AviatorUtils {
    public static Object execute(String expression, HashMap<String, Object> parms) {
        return AviatorEvaluator.execute(expression, parms);
    }


    public static void main(String[] args) {

        String cond = "user==zhangsan&&age==15";
        String a = "{\"user\":\"zhangsan\",\"age\":17}";

        HashMap<String, Object> aA = new HashMap<>();
        aA.put("user", "zhangsan");
        aA.put("age", 16);

        HashMap<String, Object> bB = new HashMap<String, Object>();
        bB.put("user", "zhangsan");
        bB.put("age", "15");

        ArrayList<HashMap> objects = new ArrayList<HashMap>();
        objects.add(aA);
        objects.add(bB);
        objects.stream().forEach(hashMap -> {
            System.out.println(hashMap);
            System.out.println(execute(cond, hashMap));
        });
    }

    /**
     * map 装换为 Json 字符串
     *
     * @param hashMap 传入的map
     * @return 返回 jsonStr
     */
    public static String mapToStrJson(HashMap<String, Object> hashMap) {
        return JSONObject.toJSONString(hashMap);
    }

    /**
     * map 装换为 Json 字符串
     *
     * @param hashMap 传入的map
     * @return 返回 jsonObj
     */
    public static JSONObject mapToJsonObj(HashMap<String, Object> hashMap) {
        return JSONObject.parseObject(mapToStrJson(hashMap));
    }

    public static HashMap<String, Object> jsonStrToHashMap(String jsonStr) {
        return JSON.parseObject(jsonStr, HashMap.class);
    }

    public static HashMap<String, Object> jsonObjectToHashMap(JSONObject jsonObject) {
        return jsonStrToHashMap(jsonObject.toJSONString());
    }
}
