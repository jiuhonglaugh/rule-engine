package aviator;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.Expression;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * nil是Aviator内置的常量，类似java中的null，表示空的值。nil跟null不同的在
 * 于，在java中null只能使用在==、!=的比较运算符，而nil还可以使用>、>=、
 * <、<=等比较运算符。Aviator规定，[任何对象都比nil大除了nil本身]。用户传入
 * 的变量如果为null，将自动以nil替代。
 * <p>
 * AviatorEvaluator.execute("nil == nil");  //true
 * AviatorEvaluator.execute(" 3> nil");    //true
 * AviatorEvaluator.execute(" true!= nil");    //true
 * AviatorEvaluator.execute(" ' '>nil ");  //true
 * AviatorEvaluator.execute(" a==nil ");   //true,a is null
 * nil与String相加的时候，跟java一样显示为null
 */
public class Test {
    public static void main(String[] args) {
//        test();
//        test2();
//        test3();
//        test4();
//        test5();
//        test6();
//        test7();
//        test8();
        test9();
    }

    /**
     * 日期比较
     */
    private static void test9() {
        Map<String, Object> env = new HashMap<String, Object>();
        final Date date = new Date();
        String dateStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SS").format(date);
        System.out.println(dateStr);
        env.put("date", "2020-10-15 23:27:20");
        env.put("dateStr", dateStr);

        Boolean result = (Boolean) AviatorEvaluator.execute("dateStr>=date",
                env);
        System.out.println(result);

//        result = (Boolean) AviatorEvaluator.execute("date > '2009-12-20 00:00:00:00' ", env);
//        System.out.println(result);
//
//        result = (Boolean) AviatorEvaluator.execute("date < '2200-12-20 00:00:00:00' ", env);
//        System.out.println(result);
//
//        result = (Boolean) AviatorEvaluator.execute("date ==date ", env);
//        System.out.println(result);
    }

    /**
     * 变量的语法糖衣
     */
    private static void test8() {
        User user = new User(1, "jack", "18");
        Map<String, Object> env = new HashMap<>();
        env.put("user", user);

        String result = (String) AviatorEvaluator.execute(" '[user id='+ user.id + ',name='+user.name + ',age=' +user.age +']' ", env);
        System.out.println(result);
    }

    /**
     * 正则表达式匹配
     */
    private static void test7() {
        String email = "hello2018@gmail.com";
        Map<String, Object> env = new HashMap<String, Object>();
        env.put("email", email);
        String username = (String) AviatorEvaluator.execute("email=~/([\\w0-8]+)@\\w+[\\.\\w+]+/ ? $1 : 'unknow' ", env);
        System.out.println(username);
    }

    /**
     * 三元比较符
     */
    private static void test6() {
        Map<String, Object> env = new HashMap<String, Object>();
        env.put("a", -5);
        String result = (String) AviatorEvaluator.execute("a>0? 'yes':'no'", env);
        System.out.println(result);
    }


    /**
     * 访问数组和集合
     * List和数组用list[0]和array[0]，Map用map.date
     * 访问时以map中key的名称进行访问
     */
    private static void test5() {
        final List<String> list = new ArrayList<>();
        list.add("hello");
        list.add(" world");

        final int[] array = new int[3];
        array[0] = 0;
        array[1] = 1;
        array[2] = 3;

        final Map<String, Date> map = new HashMap<>();
        map.put("date", new Date());

        Map<String, Object> env = new HashMap<>();
        env.put("list", list);
        env.put("array", array);
        env.put("map", map);

        System.out.println(AviatorEvaluator.execute(
                "list[0]+':'+array[0]+':'+'today is '+map.date", env));
    }

    /**
     * 表达式的方式来计算返回 boolean
     */
    private static void test4() {
        String expression = "a*(b+c)>100";
        // 编译表达式
        Expression compiledExp = AviatorEvaluator.compile(expression);

        Map<String, Object> env = new HashMap<>();
        env.put("a", 100.3);
        env.put("b", 45);
        env.put("c", -199.100);

        // 执行表达式
        Boolean result = (Boolean) compiledExp.execute(env);
        System.out.println(result);
    }


    private static void test3() {
        AviatorEvaluator.addFunction(new MultiplyFunction());
        // 方式1
        System.out.println(AviatorEvaluator.execute("multiply(12.23, -2.3, 2.3)"));
        // 方式2
        Map<String, Object> params = new HashMap<>();
        params.put("a", 12.23);
        params.put("b", -2.3);
        params.put("c", 2.3);
        System.out.println(AviatorEvaluator.execute("multiply(a, b, c)", params));
    }

    /**
     * s1   s2
     * s2 是否是  s1  的开头
     * return  boolean
     */
    private static void test2() {
        Map<String, Object> map = new HashMap<>();
        map.put("s1", "123qwer");
        map.put("s2", "123q");

        System.out.println(AviatorEvaluator.execute("string.startsWith(s1,s2)", map));
    }

    /**
     * exec     执行时参数不需要传递map格式
     * execute  执行时参数需传递map格式
     */
    private static void test() {
        // exec执行方式，无需传递Map格式
        String age = "18";
        System.out.println(AviatorEvaluator.exec("'His age is '+ age +'!'", age));
        // execute执行方式，需传递Map格式
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("age", "18");
        System.out.println(AviatorEvaluator.execute("'His age is '+ age +'!'", map));
    }
}
