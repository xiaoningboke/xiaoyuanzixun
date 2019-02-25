package com.sunxiaoning.xiaoyuanfuwu.tools;

import com.google.common.collect.Lists;

import java.util.*;

public class Common {
    /**
     * 是否有效字符串
     * 1）不能为null
     * 2) 长度大于0
     *
     * @param str
     * @return
     */
    public static boolean validString(String str) {
        return str != null && str.length() > 0;
    }


    /**
     * 将HashMap中的多个数据，通过逗号(,)分隔符，组合为一个数组
     * 常见应用场景：以逗号分隔符存储了多个id，name，然后组装成 [{id:xxx, name:xxx}, {id:xxx, name:xxx}]的数组
     *
     * @param aMap
     * @param propNames
     * @return
     */
    public static ArrayList splitProperties(HashMap aMap, String[] propNames) {
        return splitProperties(aMap, propNames, propNames, ",");
    }

    /**
     * 将HashMap中的多个数据，通过逗号(,)分隔符，组合为一个数组
     * 常见应用场景：以逗号分隔符存储了多个id，name，然后组装成 [{id:xxx, name:xxx}, {id:xxx, name:xxx}]的数组
     *
     * @param aMap
     * @param propNames
     * @param aliases
     * @return
     */
    public static ArrayList splitProperties(HashMap aMap, String[] propNames, String[] aliases) {
        return splitProperties(aMap, propNames, aliases, ",");
    }

    /**
     * 将HashMap中的多个数据，通过分隔符，组合为一个数组
     * 常见应用场景：以逗号分隔符存储了多个id，name，然后组装成 [{id:xxx, name:xxx}, {id:xxx, name:xxx}]的数组
     *
     * @param aMap
     * @param propNames
     * @param aliases
     * @param splitChar
     * @return
     */
    public static ArrayList splitProperties(HashMap aMap, String[] propNames, String[] aliases, String splitChar) {
        ArrayList result = new ArrayList();
        for (int i = 0; i < propNames.length; i++) {
            String prop = propNames[i];

            // 值，转换为数组
            List propValues = Arrays.asList(aMap.get(prop).toString().split(splitChar));

            // 循环值数组，分散到result中，并以aliases对应的别名作为key
            for (int p = 0; p < propValues.size(); p++) {
                if (result.size() <= p || result.get(p) == null) {
                    result.add(new HashMap<>());
                }

                HashMap item = (HashMap) result.get(p);
                item.put(aliases[i], propValues.get(p));
            }
        }
        return result;
    }

    /**
     * 自动生成并返回返回id
     *
     * @return
     */
    public static String UUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");

    }

    public static void main(String[] args) {
        HashMap m = new HashMap();
        m.put("USERIDS", "1,2");
        m.put("user_names", "张三,李四");

        ArrayList userList = Common.splitProperties(
                m,
                new String[]{"USERIDS", "user_names"},
                new String[]{"USER_ID", "NAME"}
        );

        System.out.println(userList);
    }

    /**
     * 对字符转进行base64转码
     *
     * @param str 源字符串
     * @return
     */
    public static String base64(String str) {
        return new String(Base64.getEncoder().encode(str.getBytes()));
    }
}
