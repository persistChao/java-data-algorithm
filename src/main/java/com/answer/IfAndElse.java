package com.answer;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 *
 * if else 优化
 * 1 表驱动 ： 适用于 if value==1 else if value==2 else value==3 逻辑表达模式固定的 if...else 也就是 value是明确的某个值
 * 2
 * @author suchao
 * @date 2022/9/26 14:57
 **/
public class IfAndElse {

    public static void main(String[] args) {
        //对于逻辑表达模式固定的 if...else 代码，可以通过某种映射关系，将逻辑表达式用表格的方式表示；
        // 再使用表格查找的方式，找到某个输入所对应的处理函数，使用这个处理函数进行运算。
        String param = "";
        String value1 = "value1";
        String value2 = "value2";
        String value3 = "value3";

        if (param.equals(value1)) {
            doAction1(value1);
        } else if (param.equals(value2)) {
            doAction2(value2);
        } else if (param.equals(value3)) {
            doAction3(value3);
        }
// ...
        Map<String, Function<String , String>> actionMappings = new HashMap<>();
        // When init
        actionMappings.put(value1, IfAndElse::doAction1);
        actionMappings.put(value2, (someParams) ->  doAction2(someParams));
        actionMappings.put(value3, (someParams) ->  doAction3(someParams));


        judgeAgeArea(10,"张三");
    }

    private static  String doAction3(String str){
        System.out.println(str);
        return str;
    }

    private static  String doAction1(String str){
        System.out.println(str);
        return str;
    }

    private static  String doAction2(String str){
        System.out.println(str);
        return str;
    }

    private static Map<Byte, Function<JSONObject , JSONObject>> actionMappings = new HashMap<>(16);
    static {

//        Function<JSONObject , JSONObject> function = j -> {j.put("optType","conn");return j;};
        actionMappings.put((byte) 0x01, j -> {j.put("optType","conn");return j;});
        actionMappings.put((byte) 0x02, j -> {j.put("optType","asr");return j;});
        actionMappings.put((byte) 0x03, j -> {j.put("optType","tts");return j;});
        actionMappings.put((byte) 0x04, j -> {j.put("optType","heart");return j;});
    }


    /**
     * 这个实例 只适用于 有具体值的 如上面 value1 value2 value3 如果是区间 大于等于 之类的不适用与此类
     *
     * 判断年龄区间
     *
     * @param age 年龄
     */
    public static void judgeAgeArea(Integer age , String name){
        Map<Integer, Function<String , String>> actionMappings = new HashMap<>(16);
        actionMappings.put(10,(param)->doTen(param));
        actionMappings.put(20,(param)->doTwenty(param));
        actionMappings.put(30,(param)->doThirty(param));
        // 省略 null 判断
        actionMappings.get(age).apply(name);

    }

    private static String doTen(String param){

        System.out.println("执行Ten方法——>" + param);
        return param;
    }

    private static String doTwenty(String param){
        System.out.println("执行Twenty方法——>" + param);
        return param;
    }

    private static String doThirty(String param){
        System.out.println("执行Thirty方法——>" + param);
        return param;
    }


    /**
     * 测试 case1 : 真实代码
     *
     */
    @Test
    public void testIfElse1(){
        JSONObject jsonParam = new JSONObject();

        jsonParam.put("name", "answer");
        jsonParam.put("age", "1991");
        jsonParam.put("city", "beijnig");

        byte aByte = 0x001;
        checkOptType(aByte, jsonParam);
    }

    private void checkOptType( byte byteType, JSONObject jsonParam){
        byte[] byteSrcData = new byte[32];
        if ((null != byteSrcData) && (byteSrcData.length > 0)) {
            for (int iLoop = 0; iLoop < byteSrcData.length; iLoop++) {
                byteSrcData[iLoop] = 0x00;
            }
        }


        //优化前代码
//        if ((byteSrcData[0] & 0xff) == 0x01) {
//            jsonParam.put("optType", "conn");
//        } else if ((byteSrcData[0] & 0xff) == 0x02) {
//            jsonParam.put("optType", "asr");
//        } else if ((byteSrcData[0] & 0xff) == 0x03) {
//            jsonParam.put("optType", "tts");
//        } else if ((byteSrcData[0] & 0xff) == 0x04) {
//            jsonParam.put("optType", "heart");
//        }
//        Map<Byte, Function<JSONObject , JSONObject>> actionMappings = new HashMap<>(16);
////        Function<JSONObject , JSONObject> function = j -> {j.put("optType","conn");return j;};
//        actionMappings.put((byte) 0x01, j -> {j.put("optType","conn");return j;});
//        actionMappings.put((byte) 0x02, j -> {j.put("optType","asr");return j;});
//        actionMappings.put((byte) 0x03, j -> {j.put("optType","tts");return j;});
//        actionMappings.put((byte) 0x04, j -> {j.put("optType","heart");return j;});

        actionMappings.get(byteType).apply(jsonParam);
        System.out.println(jsonParam.toJSONString());
    }

}
