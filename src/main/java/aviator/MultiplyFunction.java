package aviator;

import com.googlecode.aviator.runtime.function.AbstractFunction;
import com.googlecode.aviator.runtime.function.FunctionUtils;
import com.googlecode.aviator.runtime.type.AviatorDouble;
import com.googlecode.aviator.runtime.type.AviatorObject;

import java.util.Map;

/**
 * 注册自定义函数 继承 AbstractFunction 类
 * 重新 call 和 getName 方法
 */

public class MultiplyFunction extends AbstractFunction {
    /**
     * @param env  传入的 map
     * @param arg1 map 中的 key
     * @param arg2 map 中的 key
     * @param arg3 map 中的 key
     * @return
     */
    @Override
    public AviatorObject call(Map<String, Object> env, AviatorObject arg1, AviatorObject arg2, AviatorObject arg3) {
        double num1 = FunctionUtils.getNumberValue(arg1, env).doubleValue();
        double num2 = FunctionUtils.getNumberValue(arg2, env).doubleValue();
        double num3 = FunctionUtils.getNumberValue(arg3, env).doubleValue();
        return new AviatorDouble(num1 * num2 * num3);
    }

    /**
     * @return 注册的方法名称
     */
    @Override
    public String getName() {
        return "multiply";
    }

}