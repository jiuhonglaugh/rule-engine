package utils;

/**
 * zhu
 * 2020-10-17
 * 用来做执行表达式的返回值
 * isSuccess   为执行是否成功
 * result      为执行结果，但不直接提供使用，通过方法调用来做为返回值
 */
public class ObjectToBaseType {

    private boolean isSuccess;
    private Object result;

    public ObjectToBaseType(boolean isSuccess, Object result) {
        this.isSuccess = isSuccess;
        this.result = result;
    }

    public boolean getStatus() {
        return this.isSuccess;
    }

    public String getString() {
        if (result instanceof String)
            return (String) this.result;
        return "";
    }

    public Integer getInteger() {
        return (Integer) this.result;
    }

    public Long getLong() {
        return (Long) this.result;
    }

    public Double getDouble() {
        return (Double) this.result;
    }

    public Boolean getBoolean() {
        return (Boolean) result;
    }

    public static void main(String[] args) {

    }
}
