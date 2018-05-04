package com.sic777.utils.probality;

import com.sic777.utils.container.ContainerGetter;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Map.Entry;

/**
 * <p>权重随机</p>
 *
 * @author Zhengzhenxie<br>
 *         <br>2017-12-09 17:55
 * @version v1.0
 * @since 2018-03-03 17:15
 */
public class ProbabilityUtils {

    /**
     * 根据权重返回
     *
     * @param map
     * @return
     */
    public static <T> T randProbability(Map<T, Double> map) {
        int multiple = 1000; // 放大位数
        // 求和
        int sum = 0;
        Iterator<Entry<T, Double>> iter = map.entrySet().iterator();
        while (iter.hasNext()) {
            Entry<T, Double> entry = iter.next();
            Double v = entry.getValue();
            sum += v * multiple;
        }
        // 产生0-sum的整数随机
        int luckNum = new Random().nextInt(sum) + 1;
        int tmp = 0;
        iter = map.entrySet().iterator();
        while (iter.hasNext()) {
            Entry<T, Double> entry = iter.next();
            Double v = entry.getValue();
            tmp += v * multiple;
            if (luckNum <= tmp) {
                return entry.getKey();
            }
        }
        return null;
    }

    public static <T> List<T> randProbability(Map<T, Double> map, int n) {
        int multiple = 1000; // 放大位数
        // 求和
        int sum = 0;
        Iterator<Entry<T, Double>> iter = map.entrySet().iterator();
        while (iter.hasNext()) {
            Entry<T, Double> entry = iter.next();
            Double v = entry.getValue();
            sum += v * multiple;
        }
        Random random = new Random();
        List<T> list = ContainerGetter.arrayList();
        for (int ix = 0, len = n; ix < len; ++ix) {
            // 产生0-sum的整数随机
            int luckNum = random.nextInt(sum) + 1;
            int tmp = 0;
            iter = map.entrySet().iterator();
            while (iter.hasNext()) {
                Entry<T, Double> entry = iter.next();
                Double v = entry.getValue();
                tmp += v * multiple;
                if (luckNum <= tmp) {
                    list.add(entry.getKey());
                    break;
                }
            }
        }
        return list;
    }
}
