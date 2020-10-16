package aviator;

import com.alibaba.fastjson.JSONObject;
import com.googlecode.aviator.AviatorEvaluator;
import utils.JsonMapUtils;

import java.util.ArrayList;
import java.util.HashMap;

public class AviatorUtils {
    public static Object execute(String expression, HashMap<String, Object> parms) {
        return AviatorEvaluator.execute(expression, parms);
    }


    public static void main(String[] args) {

        //success > 20 && success / count * 100 < 40

//        String cond = "user==\"zhangsan\"&&age>=15||age<16";
        String cond = "success/1/2<40";

        HashMap<String, Object> aA = new HashMap<String, Object>();
        aA.put("success", 30);
        aA.put("count", 100);
        HashMap<String, Object> bB = new HashMap<String, Object>();
        bB.put("success", 100);
        bB.put("count", 100);

        ArrayList<HashMap> objects = new ArrayList<>();
        objects.add(aA);
        objects.add(bB);
        objects.stream().forEach(hashMap ->
                System.out.println(execute(cond, hashMap))
        );
    }
}
