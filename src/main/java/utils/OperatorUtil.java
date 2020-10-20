package utils;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.Expression;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.Map;

public class OperatorUtil {
    private static ScriptEngine engine;

    public static ScriptEngine getEngine() {
        if (OperatorUtil.engine == null) {
            try {
                final ScriptEngineManager manager = new ScriptEngineManager();
                (engine = manager.getEngineByName("JavaScript")).getBindings(100);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            engine.createBindings();
        }
        return engine;
    }

    public static Boolean getExp(final String expression, final Map<String, Object> map) {
        try {
            Boolean result = false;
            final Expression compiledExp = AviatorEvaluator.compile(expression);
            result = (Boolean) compiledExp.execute(map);
            return result;
        } catch (Exception ex) {
            System.out.println("====getExp()====");
            return false;
        }
    }

    public static Boolean getExp(final String expression) {
        try {
            final Expression compiledExp = AviatorEvaluator.compile(expression);
            final Boolean result = (Boolean) compiledExp.execute();
            return result;
        } catch (Exception ex) {
            return false;
        }
    }

    public static void main(final String[] args) throws ScriptException {
        final String topicEventList = "a==1&&b==2&&user_name like '%h%'";
        final Expression compiledExp = AviatorEvaluator.compile("string.contains('hello','h')");
        final Boolean execute = (Boolean) compiledExp.execute();
        System.out.println("execute = " + execute);
        System.out.println(getEngine().eval("print(1111*10);"));
    }
}