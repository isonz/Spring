package cn.ptp;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Ison on 2016/10/21.
 */
public class Test
{
    public static void main(String[] args) {
        //JsonObject和JsonArray区别就是JsonObject是对象形式，JsonArray是数组形式
        //创建JsonObject第一种方法
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("UserName", "ZHULI");
        jsonObject.put("age", "30");
        jsonObject.put("workIn", "ALI");
        System.out.println("jsonObject1：" + jsonObject);



        //创建一个JsonArray方法1
        JSONArray jsonArray = new JSONArray();
        jsonArray.add(0, "ZHULI");
        jsonArray.add(1, "30");
        jsonArray.add(2, "ALI");
        System.out.println("jsonArray1：" + jsonArray);

    }
}
