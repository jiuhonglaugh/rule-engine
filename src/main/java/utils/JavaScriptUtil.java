package utils;

import aviator.entity.MapJ;
import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;

import javax.script.*;
import java.util.*;

public class JavaScriptUtil {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    private static ScriptEngineManager factory;
    private static ScriptEngine engine;

    static {
        JavaScriptUtil.factory = new ScriptEngineManager();
        JavaScriptUtil.engine = JavaScriptUtil.factory.getEngineByName("JavaScript");
    }


    public ObjectToBaseType getMathValue(final List<MapJ<String, String>> listMapJ, String option) {
        try {
            for (MapJ<String, String> mapJ : listMapJ) {
                option = option.replaceAll(mapJ.getKey(), mapJ.getValue());
            }
            return new ObjectToBaseType(true, JavaScriptUtil.engine.eval(option));
        } catch (ScriptException e) {
            logger.warn("无法识别表达式:  " + option);
        }
        return new ObjectToBaseType(false, null);
    }

    public static void main(final String[] args) {
        JavaScriptUtil javaScriptUtil = new JavaScriptUtil();
        final String sbt = "(B+D-(A-C)*A)/F";
        final List<MapJ<String, String>> all = new ArrayList<MapJ<String, String>>();
        all.add(new MapJ("A", "2"));
        all.add(new MapJ("B", "3"));
        all.add(new MapJ("C", "4"));
        all.add(new MapJ("D", "5"));
        all.add(new MapJ("F", "24"));
        ObjectToBaseType mathValue = javaScriptUtil.getMathValue(all, sbt);
        if (mathValue.getStatus()) {
            System.out.println(mathValue.getDouble());
        }
        System.out.println();
    }
}
