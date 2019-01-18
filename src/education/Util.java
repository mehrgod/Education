/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package education;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Random;

/**
 *
 * @author mirza
 */
public class Util {

    public static String[] shuffleArray(String[] array) {
        int index;
        String temp;
        Random random = new Random();
        for (int i = array.length - 1; i > 0; i--) {
            index = random.nextInt(i + 1);
            temp = array[index];
            array[index] = array[i];
            array[i] = temp;
        }
        return array;
    }

    public static Double takeAverage(ArrayList<Double> arr) {
        Double avg = 0.0;
        for (Double a : arr) {
            avg += a;
        }
        if (arr.size() > 0) {
            avg = avg / arr.size();
        } else {
            return -1.0;
        }
        return avg;
    }

    public static Double takeSum(ArrayList<Double> arr) {
        Double sum = 0.0;
        for (Double a : arr) {
            sum += a;
        }
        return sum;
    }

    public static String Merge(ArrayList<String> lines) {
        Double res = 0.0;
        String[] token = lines.get(0).split("\t");
        for (String line : lines) {
            token = line.split("\t");
            Double val = Double.valueOf(token[7]);
            res += val;
        }

        return token[0] + "\t" + token[1] + "\t" + token[2] + "\t" + token[3] + "\t" + token[4] + "\t" + token[5] + "\t" + token[6] + "\t" + res;
    }

    public static LinkedHashMap<String, Double> sortHashMapByValues(HashMap<String, Double> passedMap) {
        List<String> mapKeys = new ArrayList<>(passedMap.keySet());
        List<Double> mapValues = new ArrayList<>(passedMap.values());
        Collections.sort(mapValues);
        Collections.sort(mapKeys);

        LinkedHashMap<String, Double> sortedMap = new LinkedHashMap<>();

        Iterator<Double> valueIt = mapValues.iterator();
        while (valueIt.hasNext()) {
            Double val = valueIt.next();
            Iterator<String> keyIt = mapKeys.iterator();

            while (keyIt.hasNext()) {
                String key = keyIt.next();
                Double comp1 = passedMap.get(key);
                Double comp2 = val;

                if (comp1.equals(comp2)) {
                    keyIt.remove();
                    sortedMap.put(key, val);
                    break;
                }
            }
        }
        return sortedMap;
    }

}
