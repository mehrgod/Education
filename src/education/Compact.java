/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package education;

import static education.Education.count;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author mirza
 */
public class Compact {

//    static String path = "C:\\Project\\EDU\\files\\2013\\compact\\revised\\clean\\";
    static String path = "C:\\Project\\EDU\\files\\2013\\example\\topic\\compact\\";

    public static void main(String[] args) throws IOException {

//        dividDurationLabel(findMinMaxQuiz());
//        findSequence();
//        countSequence();
        compactSequence();
//        forCMspam();
//        SPMFtoTEXT();
//        createVectorUser();
//        createStatcluster();
//        filterTop();
//        findFrequencyPatternAll();
//        findFrequencyPattern();
//        findAvgStdPattern();
//        createVector(10);
//        performance();
//        compPerfCluster();
//        countFreqCluster();
//        cluster();

//=====================================
//        forCMspam();
//        SPMFtoTEXT();
//        createVectorUser();
//        createStatcluster();
//        findFrequencyPatternCluster();
//        findFrequencyPatternAll();
//        reorder();

    }

    public static HashMap findMinMaxQuiz() throws FileNotFoundException, IOException {
        File fileIn = new File(path + "Dataset.txt");
        BufferedReader br = new BufferedReader(new FileReader(fileIn));

        File fileOut = new File(path + "medianQuizDuration.txt");
        FileWriter fw = new FileWriter(fileOut);

        ArrayList<String> activityname = new ArrayList<String>();
        ArrayList<ArrayList<Double>> duration = new ArrayList<ArrayList<Double>>();

        HashMap<String, Double> mp = new HashMap<String, Double>();

        String line = "";
        br.readLine();
        while ((line = br.readLine()) != null) {
            String[] token = line.split("\t");
            String an = token[2];
            Double ds = Double.valueOf(token[4]);
            if (activityname.contains(an)) {
                int ix = activityname.indexOf(an);
                duration.get(ix).add(ds);
            } else {
                activityname.add(an);
                ArrayList<Double> temp = new ArrayList<Double>();
                temp.add(ds);
                duration.add(temp);
            }

        }

        for (int i = 0; i < activityname.size(); i++) {
            int s = duration.get(i).size();

            ArrayList<Double> sorted = new ArrayList<Double>();
            sorted = duration.get(i);
            Collections.sort(sorted);

            Double mid = (sorted.get(s / 2) + sorted.get(s / 2 - 1)) / 2;
            String quiz = activityname.get(i);
            System.out.println(quiz + " : " + mid);
            fw.write(quiz + "\t" + mid + "\n");
            //middle = (sets.get(sets.size()/2) + sets.get(sets.size()/2 - 1))/2;
            mp.put(quiz, mid);
        }

        br.close();
        fw.close();

        return mp;

    }

    public static void dividDurationLabel(HashMap<String, Double> hm) throws FileNotFoundException, IOException {
        File fileIn = new File(path + "Dataset.txt");
        BufferedReader br = new BufferedReader(new FileReader(fileIn));

        File fileOut = new File(path + "QuizLabelTime.txt");
        FileWriter fw = new FileWriter(fileOut);

        String line = "";
        br.readLine();
        while ((line = br.readLine()) != null) {
            String[] token = line.split("\t");
            String result = token[3];
            Double duration = Double.valueOf(token[4]);
            Double threashold = hm.get(token[2]);

            String label = "";
            if (duration < threashold) {
                if (result.equals("0")) {
                    label = "f";
                } else if (result.equals("1")) {
                    label = "s";
                }

            } else {
                if (result.equals("0")) {
                    label = "F";
                } else if (result.equals("1")) {
                    label = "S";
                }
            }
            fw.write(token[0] + "\t" + token[1] + "\t" + token[2] + "\t" + token[3] + "\t" + label + "\n");

        }

        br.close();
        fw.close();
    }

    public static void findSequence() throws FileNotFoundException, IOException {
        File fileIn = new File(path + "QuizLabelTime.txt");
        BufferedReader br = new BufferedReader(new FileReader(fileIn));

        File fileOut = new File(path + "Sequence.txt");
        FileWriter fw = new FileWriter(fileOut);

        ArrayList<String> seq = new ArrayList<String>();

        String line = br.readLine();
        String[] token = line.split("\t");

        String user = token[0];
        String session = token[1];
        String activityname = token[2];
        String result = token[3];
        String durationseconds = token[4];

        String oldUser = user;
        String oldSession = session;
        String oldActivityname = activityname;

        int shortS = 0;
        int longS = 0;
        int shortF = 0;
        int longF = 0;

        switch (durationseconds) {
            case "s":
                shortS++;
                break;
            case "S":
                longS++;
                break;
            case "f":
                shortF++;
                break;
            case "F":
                longF++;
                break;
        }

        seq.add(durationseconds);

        while ((line = br.readLine()) != null) {
            oldUser = user;
            oldSession = session;
            oldActivityname = activityname;

            token = line.split("\t");
            user = token[0];
            session = token[1];
            activityname = token[2];
            result = token[3];
            durationseconds = token[4];

            switch (durationseconds) {
                case "s":
                    shortS++;
                    break;
                case "S":
                    longS++;
                    break;
                case "f":
                    shortF++;
                    break;
                case "F":
                    longF++;
                    break;
            }

            if (!user.equals(oldUser)) {
                seq.add("_");
                fw.write(oldUser + "\t_" + mergeSequence(seq) + "\n");
                seq = new ArrayList<String>();
            } else if (!session.equals(oldSession)) {
                seq.add("_");
            } else if (!activityname.equals(oldActivityname)) {
                seq.add("_");
            }

            seq.add(durationseconds);

        }

        fw.write(oldUser + "\t_" + mergeSequence(seq) + "_\n");

        System.out.println("Short success (s): " + shortS);
        System.out.println("Long success (S): " + longS);
        System.out.println("Short failure (f): " + shortF);
        System.out.println("Long failure (F): " + longF);

        br.close();
        fw.close();
    }

    public static String mergeSequence(ArrayList<String> array) {
        String seq = "";
        for (int i = 0; i < array.size(); i++) {
            seq = seq + array.get(i);
        }
        return seq.trim();
    }

    public static void countSequence() throws FileNotFoundException, IOException {
        File fileIn = new File(path + "SequenceCompact.txt");
        BufferedReader br = new BufferedReader(new FileReader(fileIn));

        File fileOut = new File(path + "SequenceCompactCountSorted.txt");
        FileWriter fw = new FileWriter(fileOut);

        HashMap<String, Integer> hm = new HashMap<String, Integer>();

        String line = "";

        while ((line = br.readLine()) != null) {
            String[] token = line.split("\t");
            String all = token[1];
            String[] seq = all.split("_");
            for (String s : seq) {
                if (s.length() > 0) {
                    if (hm.containsKey(s)) {
                        int n = hm.get(s);
                        n++;
                        hm.put(s, n);
                    } else {
                        hm.put(s, 1);
                    }
                }

            }
        }

        LinkedHashMap<String, Integer> sortHashMap = sortHashMapByValues(hm);

        for (Map.Entry<String, Integer> entry : sortHashMap.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            System.out.println(key + "\t" + value);
            fw.write(key + "\t" + value + "\n");
        }

        /*
        for (Map.Entry<String, Integer> entry : hm.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            System.out.println(key + "\t" + value);
            fw.write(key + "\t" + value + "\n");
        }
         */
        br.close();
        fw.close();
    }

    public static LinkedHashMap<String, Integer> sortHashMapByValues(HashMap<String, Integer> passedMap) {
        List<String> mapKeys = new ArrayList<>(passedMap.keySet());
        List<Integer> mapValues = new ArrayList<>(passedMap.values());
        Collections.sort(mapValues);
        Collections.reverse(mapValues);
        Collections.sort(mapKeys);
        Collections.reverse(mapKeys);

        LinkedHashMap<String, Integer> sortedMap = new LinkedHashMap<>();

        Iterator<Integer> valueIt = mapValues.iterator();

        while (valueIt.hasNext()) {
            int val = valueIt.next();
            Iterator<String> keyIt = mapKeys.iterator();

            while (keyIt.hasNext()) {
                String key = keyIt.next();
                int comp1 = passedMap.get(key);
                int comp2 = val;

                if (comp1 == comp2) {
                    keyIt.remove();
                    sortedMap.put(key, val);
                    break;
                }
            }
        }
        return sortedMap;
    }

    public static void compactSequence() throws FileNotFoundException, IOException {
        File fileIn = new File(path + "SequenceFilter.txt");
        BufferedReader br = new BufferedReader(new FileReader(fileIn));

        File fileOut = new File(path + "SequenceFilterCompact.txt");
        FileWriter fw = new FileWriter(fileOut);
        String line = "";

        while ((line = br.readLine()) != null) {
            String[] token = line.split("\t");
            String id = token[0];
            String seq = token[1];
            String[] seqs = seq.split(",");

            String out = "";
            for (String s : seqs) {
                out = out + "," + convert(s);
            }
            out = out.substring(1);

            fw.write(id + "\t" + out + "\n");
        }

        fw.close();
        br.close();

    }

    public static String convert(String s) {
        String out = "";
        String pattern1 = "s{2,}";
        Pattern ptrn1 = Pattern.compile(pattern1);
        Matcher matcher1 = ptrn1.matcher(s);
        out = matcher1.replaceAll("s+");

        String pattern2 = "S{2,}";
        Pattern ptrn2 = Pattern.compile(pattern2);
        Matcher matcher2 = ptrn2.matcher(out);
        out = matcher2.replaceAll("S+");

        String pattern3 = "f{2,}";
        Pattern ptrn3 = Pattern.compile(pattern3);
        Matcher matcher3 = ptrn3.matcher(out);
        out = matcher3.replaceAll("f+");

        String pattern4 = "F{2,}";
        Pattern ptrn4 = Pattern.compile(pattern4);
        Matcher matcher4 = ptrn4.matcher(out);
        out = matcher4.replaceAll("F+");

        String pattern5 = "e{2,}";
        Pattern ptrn5 = Pattern.compile(pattern5);
        Matcher matcher5 = ptrn5.matcher(out);
        out = matcher5.replaceAll("e+");

        String pattern6 = "E{2,}";
        Pattern ptrn6 = Pattern.compile(pattern6);
        Matcher matcher6 = ptrn6.matcher(out);
        out = matcher6.replaceAll("E+");

        return out;
    }

    public static void createVector(int minFreq) throws FileNotFoundException, IOException {
        File fileIns = new File(path + "SequenceCompactCountSorted.txt");
        BufferedReader brs = new BufferedReader(new FileReader(fileIns));

        File fileIn = new File(path + "SequenceCompact.txt");
        BufferedReader br = new BufferedReader(new FileReader(fileIn));

        File fileOut = new File(path + "SequenceCompactVectorNormalMax.txt");
        FileWriter fw = new FileWriter(fileOut);

        String line = "";

        ArrayList<String> topseq = new ArrayList<String>();

        while ((line = brs.readLine()) != null) {
            String[] token = line.split("\t");
            String pattern = token[0];
            int freq = Integer.valueOf(token[1]);
            if (freq > minFreq) {
                topseq.add(pattern);
            }
//            topseq.add(line.trim());
        }

        while ((line = br.readLine()) != null) {
            String[] token = line.split("\t");
            String id = token[0];
            String seq = token[1];
            String[] seqs = seq.split("_");
            ArrayList<Integer> vector = new ArrayList<Integer>();

            System.out.println(id);

            int number = 1;

            for (int i = 0; i < topseq.size(); i++) {
                int c = 0;
                for (String s : seqs) {
                    if (topseq.get(i).equals(s)) {
                        c++;
                        number++;
                    }
                }
                vector.add(c);
            }

            System.out.println(number);

            fw.write(id + "\t" + printVector(vector) + "\n");

        }

        brs.close();
        br.close();
        fw.close();

    }

    public static String printVector(ArrayList<Integer> a) {
        String out = "";

        int max = Collections.max(a);

        for (int i = 0; i < a.size(); i++) {
            out = out + "," + (float) a.get(i) / max;
        }

        return out.substring(1);
    }

    public static void performance() throws IOException {
        File fileIn = new File(path + "grades.txt");
        BufferedReader br = new BufferedReader(new FileReader(fileIn));

        String line = "";

        while ((line = br.readLine()) != null) {
            String[] token = line.split("\t");
            Double grade = Double.valueOf(token[1]);
            if (grade < 9) {
                System.out.println(token[0] + "\tl");
            } else if (grade > 14) {
                System.out.println(token[0] + "\th");
            } else {
                System.out.println(token[0] + "\tm");
            }

        }

        br.close();

    }

    public static void cluster() throws FileNotFoundException, IOException {
        File fileIn1 = new File(path + "perf.txt");
        BufferedReader br1 = new BufferedReader(new FileReader(fileIn1));

        File fileIn2 = new File(path + "cluster.txt");
        BufferedReader br2 = new BufferedReader(new FileReader(fileIn2));

        HashMap<String, String> hm1 = new HashMap<String, String>();
        HashMap<String, String> hm2 = new HashMap<String, String>();

        String line = "";
        while ((line = br1.readLine()) != null) {
            String[] token = line.split("\t");
            hm1.put(token[0], token[1]);
        }

        while ((line = br2.readLine()) != null) {
            String[] token = line.split("\t");
            hm2.put(token[0], token[1]);
        }

        for (String key : hm2.keySet()) {
            if (hm1.containsKey(key)) {
                System.out.println(key + "\t" + hm1.get(key) + "\t" + hm2.get(key));
            }
        }

        br1.close();
        br2.close();
    }

    public static void compPerfCluster() throws FileNotFoundException, IOException {
        File fileIn1 = new File(path + "perfPost.txt");
        BufferedReader br1 = new BufferedReader(new FileReader(fileIn1));

        File fileIn2 = new File(path + "kmeans3.txt");
        BufferedReader br2 = new BufferedReader(new FileReader(fileIn2));

        HashMap<String, String> hmPerf = new HashMap<String, String>();
        HashMap<String, String> hmCls = new HashMap<String, String>();

        String line = "";
        while ((line = br1.readLine()) != null) {
            String[] token = line.split("\t");
            String id = token[0];
            String perf = token[1];
            hmPerf.put(id, perf);
        }

        while ((line = br2.readLine()) != null) {
            String[] token = line.split("\t");
            String id = token[0];
            String cls = token[1];
            hmCls.put(id, cls);
        }

        for (String s : hmCls.keySet()) {
            if (hmPerf.containsKey(s)) {
                System.out.println(s + "\t" + hmCls.get(s) + "\t" + hmPerf.get(s));
            }
        }

        br1.close();
        br2.close();

    }

    public static void countFreqCluster() throws FileNotFoundException, IOException {
        File fileIn = new File("C:\\Project\\EDU\\files\\2013\\compact\\SequenceCompact.txt");
        BufferedReader br = new BufferedReader(new FileReader(fileIn));

        File fileIns = new File("C:\\Project\\EDU\\files\\2013\\compact\\Cluster.txt");
        BufferedReader brs = new BufferedReader(new FileReader(fileIns));

        File fileInp = new File("C:\\Project\\EDU\\files\\2013\\compact\\top30.txt");
        BufferedReader brp = new BufferedReader(new FileReader(fileInp));

        File fileOut = new File("C:\\Project\\EDU\\files\\2013\\compact\\stat.txt");
        FileWriter fw = new FileWriter(fileOut);

        HashMap<String, String> hm = new HashMap<String, String>();
        ArrayList<String> pattern = new ArrayList<String>();
        ArrayList<String> cluster0 = new ArrayList<String>();
        ArrayList<String> cluster1 = new ArrayList<String>();

        String line = "";

        while ((line = brs.readLine()) != null) {
            String id = line.split("\t")[0];
            String cl = line.split("\t")[1];
            hm.put(id, cl);
            System.out.println(id + "\t" + cl);
        }

        while ((line = brp.readLine()) != null) {
            pattern.add(line);
            System.out.println(line);
        }

        while ((line = br.readLine()) != null) {
            String id = line.split("\t")[0];
            String seq = line.split("\t")[1];
            String cluster = hm.get(id);
            if (cluster == null) {
                continue;
            }
            String out = "";
            for (int i = 0; i < pattern.size(); i++) {
                int c = count(seq, pattern.get(i));
                out = out + "," + c;
            }
            System.out.println(out);
            out = id + "\t" + cluster + "\t" + out.substring(1);
            fw.write(out + "\n");

        }

        br.close();
        brs.close();
        brp.close();
        fw.close();

    }

    public static int count(String str, String findStr) {

        int lastIndex = 0;
        int count = 0;

        while (lastIndex != -1) {

            lastIndex = str.indexOf(findStr, lastIndex);

            if (lastIndex != -1) {
                count++;
                lastIndex++;
            }
        }
        return count;
    }

    public static void forCMspam() throws FileNotFoundException, IOException {
        File fileIn = new File(path + "SequenceFilterCompact.txt");
        BufferedReader br = new BufferedReader(new FileReader(fileIn));

        File fileOut = new File(path + "SequenceFilterCompactCMSPAMdot.text");
        FileWriter fw = new FileWriter(fileOut);

        String line = "";
        while ((line = br.readLine()) != null) {
            String seq = line.split("\t")[1];
            seq = seq.replace("F", "e");
            seq = seq.replace("S", "b");
            seq = seq.replace("+", "p");

            String[] token = seq.split("_");

            for (String t : token) {
                if (t.length() > 0) {
                    String out = "x" + t.replace("", " ") + "x";
                    fw.write(out + ".\n");
                }
            }
        }

        br.close();
        fw.close();
    }

    public static void SPMFtoTEXT() throws FileNotFoundException, IOException {
        File fileIn = new File(path + "Patterns.txt");
        BufferedReader br = new BufferedReader(new FileReader(fileIn));

        File fileOut = new File(path + "PatternsSUP.txt");
        FileWriter fw = new FileWriter(fileOut);

        String line = "";
        while ((line = br.readLine()) != null) {
            fw.write(translate(line) + "\n");
        }

        br.close();
        fw.close();
    }

    public static String translate(String s) {
        String sup = s.split(":")[1];
        String t = s.split("#")[0];
        t = t.replace("x", "_");
        t = t.replace("e", "F");
        t = t.replace("b", "S");
        t = t.replace("p", "+");
        System.out.println(t.replaceAll(" -1 ", ""));
        return t.replaceAll(" -1 ", "") + "\t" + sup.trim();
    }

    public static void createVectorUser() throws FileNotFoundException, IOException {
        File fileIn1 = new File(path + "SequenceFilterCompact.txt");
        BufferedReader br1 = new BufferedReader(new FileReader(fileIn1));

        File fileIn2 = new File(path + "PatternsSUPfilter.txt");
        BufferedReader br2 = new BufferedReader(new FileReader(fileIn2));

        File fileOut = new File(path + "Vector.txt");
        FileWriter fw = new FileWriter(fileOut);

        ArrayList<String> ptrn = new ArrayList<String>();

        String line = "";

        while ((line = br2.readLine()) != null) {
            line = line.split("\t")[0];
            if (line.replace("_", "").length() > 1) {
                System.out.println(line);
                ptrn.add(line.trim());
            }
        }

        while ((line = br1.readLine()) != null) {
            String seq = line.split("\t")[1].trim();
            String id = line.split("\t")[0];
            String vector = "";
            for (String s : ptrn) {
                int c = count(seq, s);
                vector = vector + "," + c;
            }

            System.out.println(vector);
            fw.write(id + "\t" + vector.substring(1) + "\n");

        }

        br1.close();
        br2.close();
        fw.close();

    }

    public static void createStatcluster() throws FileNotFoundException, IOException {
        File fileInp = new File(path + "perfAll.txt");
        BufferedReader brp = new BufferedReader(new FileReader(fileInp));

        File fileInc = new File(path + "VectorNormalCluster.txt");
        BufferedReader brc = new BufferedReader(new FileReader(fileInc));

        File fileInv = new File(path + "VectorNormal.txt");
        BufferedReader brv = new BufferedReader(new FileReader(fileInv));

        File fileOut = new File(path + "statNormal.txt");
        FileWriter fw = new FileWriter(fileOut);

        String line = "";

        HashMap<String, String> hmperf = new HashMap<String, String>();
        HashMap<String, String> hmcls = new HashMap<String, String>();

        while ((line = brp.readLine()) != null) {
            String[] token = line.split("\t");
            String id = token[0];
            hmperf.put(id, line);
        }

        while ((line = brc.readLine()) != null) {
            String[] token = line.split("\t");
            String id = token[0];
            String cls = token[1];
            hmcls.put(id, cls);
        }

        while ((line = brv.readLine()) != null) {
            String[] token = line.split("\t");
            String id = token[0];
            String vec = token[1];
            if (hmperf.containsKey(id) && hmcls.containsKey(id)) {
                fw.write(hmperf.get(id) + "\t" + hmcls.get(id) + "\t" + vec + "\n");
            }
        }

        brp.close();
        brc.close();
        brv.close();
        fw.close();
    }

    public static void filterTop() throws FileNotFoundException, IOException {
        File fileT = new File(path + "Top30.txt");
        BufferedReader brt = new BufferedReader(new FileReader(fileT));

        File fileP = new File(path + "PatternsSUPFilter.txt");
        BufferedReader brp = new BufferedReader(new FileReader(fileP));

        File fileC = new File(path + "Cluster1.txt");
        BufferedReader brc = new BufferedReader(new FileReader(fileC));

//        File fileOut = new File(path + "Cluster1Top30.txt");
//        FileWriter fw = new FileWriter(fileOut);
        ArrayList<String> top30 = new ArrayList<String>();
        ArrayList<String> all = new ArrayList<String>();

        String line = "";
        while ((line = brt.readLine()) != null) {
            top30.add(line.split("\t")[0]);
        }

        while ((line = brp.readLine()) != null) {
            all.add(line.split("\t")[0]);
        }

        while ((line = brc.readLine()) != null) {
            String id = line.split("\t")[0];
            String vec = line.split("\t")[1];

            String[] vecStr = vec.split(",");
            Double[] vecDouble = new Double[30];

            int j = 0;

            for (int i = 0; i < vecStr.length; i++) {
                if (top30.contains(all.get(i))) {
                    System.out.println(all.get(i));
                    vecDouble[j] = Double.parseDouble(vecStr[i]);
                    j++;
                }
            }
            System.out.println("=================");

            String out = "";

            for (int i = 0; i < vecDouble.length; i++) {
                out = out + "," + vecDouble[i];
            }

//            fw.write(id + "\t" + out.substring(1) + "\n");
        }

        brt.close();
        brp.close();
        brc.close();
//        fw.close();

    }

    public static void findFrequencyPattern() throws FileNotFoundException, IOException {
        File fileIn = new File(path + "statNormal.txt");
        BufferedReader br = new BufferedReader(new FileReader(fileIn));

        File fileOut = new File(path + "statNormalAvg.txt");
        FileWriter fw = new FileWriter(fileOut);

        ArrayList<String> cls1 = new ArrayList<String>();
        ArrayList<String> cls0 = new ArrayList<String>();

        String line = "";

        while ((line = br.readLine()) != null) {
            String[] token = line.split("\t");
            String cluster = token[4];
            String vec = token[5];

            if (cluster.equals("0")) {
                cls0.add(vec);
            } else if (cluster.equals("1")) {
                cls1.add(vec);
            }
        }

        Double[] avg = new Double[cls0.get(0).length()];
        ArrayList<Double> std = new ArrayList<Double>();

        String avgStr = "";
        String stdStr = "";

        avg = avgVec(cls0);
        std = findSD(cls0);

        fw.write("cluster 0: \n");
        for (int i = 0; i < avg.length; i++) {
            avgStr = avgStr + "," + avg[i];
            stdStr = stdStr + "," + std.get(i);
        }
        fw.write(avgStr.substring(1) + "\n" + stdStr.substring(1) + "\n\n");
        System.out.println(avgStr.substring(1) + "\n" + stdStr.substring(1) + "\n");

        avgStr = "";
        stdStr = "";

        avg = avgVec(cls1);
        std = findSD(cls1);
        fw.write("cluster 1: \n");
        for (int i = 0; i < avg.length; i++) {
            avgStr = avgStr + "," + avg[i];
            stdStr = stdStr + "," + std.get(i);

        }
        fw.write(avgStr.substring(1) + "\n" + stdStr.substring(1) + "\n\n");
        System.out.println(avgStr.substring(1) + "\n" + stdStr.substring(1));

        fw.close();
        br.close();
    }

    public static void findAvgStdPattern() throws FileNotFoundException, IOException {
        File fileIn = new File(path + "Cluster1Top30.txt");
        BufferedReader br = new BufferedReader(new FileReader(fileIn));

        File fileOut = new File(path + "Cluster1Top30AvgStd.txt");
        FileWriter fw = new FileWriter(fileOut);

        ArrayList<String> cls = new ArrayList<String>();

        String line = "";

        while ((line = br.readLine()) != null) {
            String[] token = line.split("\t");
            String vec = token[1];
            cls.add(vec);
        }

        Double[] avg = new Double[cls.get(0).length()];
        ArrayList<Double> std = new ArrayList<Double>();

        String avgStr = "";
        String stdStr = "";

        avg = avgVec(cls);
        std = findSD(cls);

        for (int i = 0; i < avg.length; i++) {
            avgStr = avgStr + "," + avg[i];
            stdStr = stdStr + "," + std.get(i);
        }
        fw.write(avgStr.substring(1) + "\n" + stdStr.substring(1) + "\n\n");
        System.out.println(avgStr.substring(1) + "\n" + stdStr.substring(1) + "\n");

        fw.close();
        br.close();
    }

    public static Double[] avgVec(ArrayList<String> arr) {

        String temp = arr.get(0);
        String[] tk = temp.split(",");
        int sz = tk.length;
        Double[] out = new Double[sz];
        for (int i = 0; i < sz; i++) {
            out[i] = 0.0;
        }
        for (String s : arr) {
            String[] token = s.split(",");
            for (int i = 0; i < token.length; i++) {
                Double n = Double.valueOf(token[i]);
                out[i] += n;
            }
        }

        for (int i = 0; i < out.length; i++) {
            out[i] = out[i] / arr.size();
        }

        return out;
    }

    public static ArrayList<Double> findSD(ArrayList<String> arr) {

        ArrayList<ArrayList<Double>> vecs = new ArrayList<ArrayList<Double>>();

        String a = arr.get(0);
        String[] token = a.split(",");
        for (int i = 0; i < token.length; i++) {
            ArrayList<Double> temp = new ArrayList<Double>();
            temp.add(Double.valueOf(token[i]));
            vecs.add(temp);
        }

        for (int i = 1; i < arr.size(); i++) {
            String s = arr.get(i);
            String[] tk = s.split(",");
            for (int j = 0; j < tk.length; j++) {
                vecs.get(j).add(Double.valueOf(tk[j]));
            }
        }

        ArrayList<Double> res = new ArrayList<Double>();

        for (ArrayList vec : vecs) {
            res.add(SD(vec));
            System.out.println(SD(vec));
        }

        System.out.println("length of res: " + res.size());

        return res;
    }

    public static Double SD(ArrayList<Double> arr) {
        Double sd = 0.0;
        Double sum = 0.0;

        int length = arr.size();

        for (Double num : arr) {
            sum += num;
        }

        Double mean = sum / length;

        for (Double num : arr) {
            sd += Math.pow(num - mean, 2);
        }

        return Math.sqrt(sd / (length));

    }

    public static void findFrequencyPatternAll() throws FileNotFoundException, IOException {
        File fileIn = new File(path + "statNormal.txt");
        BufferedReader br = new BufferedReader(new FileReader(fileIn));

        File fileT = new File(path + "Top30.txt");
        BufferedReader brt = new BufferedReader(new FileReader(fileT));

        File fileP = new File(path + "PatternsSUPfilter.txt");
        BufferedReader brp = new BufferedReader(new FileReader(fileP));

        File fileOut = new File(path + "statNormalAllStdv.txt");
        FileWriter fw = new FileWriter(fileOut);

        ArrayList<String> top30 = new ArrayList<String>();
        ArrayList<String> all = new ArrayList<String>();

        String line = "";
        while ((line = brt.readLine()) != null) {
            top30.add(line.split("\t")[0]);
        }

        while ((line = brp.readLine()) != null) {
            all.add(line.split("\t")[0]);
        }

        ArrayList<String> cls0low = new ArrayList<String>();
        ArrayList<String> cls0hi = new ArrayList<String>();

        ArrayList<String> cls1low = new ArrayList<String>();
        ArrayList<String> cls1hi = new ArrayList<String>();

        while ((line = br.readLine()) != null) {
            String[] token = line.split("\t");
            String perf = token[3];
            String cluster = token[4];
            String vec = token[5];

            if (cluster.equals("0")) {
                if (perf.equals("l")) {
                    cls0low.add(vec);
                } else if (perf.equals("h")) {
                    cls0hi.add(vec);
                }
            } else if (cluster.equals("1")) {
                if (perf.equals("l")) {
                    cls1low.add(vec);
                } else if (perf.equals("h")) {
                    cls1hi.add(vec);
                }
            }
        }

        Double[] avg = new Double[cls0low.get(0).length()];
        ArrayList<Double> std = new ArrayList<Double>();

        String avgStr = "";
        String stdStr = "";

        avg = avgVec(cls0low);
        std = findSD(cls0low);

        fw.write("cluster 0 low performance: \n");
        for (int i = 0; i < avg.length; i++) {
            if (top30.contains(all.get(i))) {
                System.out.println(all.get(i));
                avgStr = avgStr + "," + avg[i];
                stdStr = stdStr + "," + std.get(i);
            }
        }

        fw.write(avgStr.substring(1) + "\n" + stdStr.substring(1) + "\n\n");
////////////////////////////////////////////////////////////////////////////
        avgStr = "";
        stdStr = "";

        avg = avgVec(cls1low);
        std = findSD(cls1low);

        fw.write("cluster 1 low performance: \n");
        for (int i = 0; i < avg.length; i++) {
            if (top30.contains(all.get(i))) {
                avgStr = avgStr + "," + avg[i];
                stdStr = stdStr + "," + std.get(i);
            }
        }

        fw.write(avgStr.substring(1) + "\n" + stdStr.substring(1) + "\n\n");
////////////////////////////////////////////////////////////////////////////
        avgStr = "";
        stdStr = "";

        avg = avgVec(cls0hi);
        std = findSD(cls0hi);

        fw.write("cluster 0 hi performance: \n");
        for (int i = 0; i < avg.length; i++) {
            if (top30.contains(all.get(i))) {
                avgStr = avgStr + "," + avg[i];
                stdStr = stdStr + "," + std.get(i);
            }
        }

        fw.write(avgStr.substring(1) + "\n" + stdStr.substring(1) + "\n\n");
////////////////////////////////////////////////////////////////////////////
        avgStr = "";
        stdStr = "";

        avg = avgVec(cls1hi);
        std = findSD(cls1hi);

        fw.write("cluster 1 hi performance: \n");
        for (int i = 0; i < avg.length; i++) {
            if (top30.contains(all.get(i))) {
                avgStr = avgStr + "," + avg[i];
                stdStr = stdStr + "," + std.get(i);
            }
        }

        fw.write(avgStr.substring(1) + "\n" + stdStr.substring(1) + "\n\n");

        fw.close();
        br.close();
        brt.close();
        brp.close();
    }

    public static void findFrequencyPatternCluster() throws FileNotFoundException, IOException {
        File fileIn = new File(path + "statNormal.txt");
        BufferedReader br = new BufferedReader(new FileReader(fileIn));

        File fileT = new File(path + "Top30.txt");
        BufferedReader brt = new BufferedReader(new FileReader(fileT));

        File fileP = new File(path + "PatternsSUPFilter.txt");
        BufferedReader brp = new BufferedReader(new FileReader(fileP));

        File fileOut = new File(path + "statNormalClusterStdv.txt");
        FileWriter fw = new FileWriter(fileOut);

        File fileOrd = new File(path + "Order.txt");
        FileWriter fwOrd = new FileWriter(fileOrd);

        ArrayList<String> top30 = new ArrayList<String>();
        ArrayList<String> all = new ArrayList<String>();

        String line = "";
        while ((line = brt.readLine()) != null) {
            top30.add(line.split("\t")[0]);
        }

        while ((line = brp.readLine()) != null) {
            all.add(line.split("\t")[0]);
        }

        ArrayList<String> cls0 = new ArrayList<String>();
        ArrayList<String> cls1 = new ArrayList<String>();

        while ((line = br.readLine()) != null) {
            String[] token = line.split("\t");
            String perf = token[3];
            String cluster = token[4];
            String vec = token[5];

            if (cluster.equals("0")) {
                cls0.add(vec);
            } else if (cluster.equals("1")) {
                cls1.add(vec);
            }
        }

        Double[] avg = new Double[cls0.get(0).length()];
        ArrayList<Double> std = new ArrayList<Double>();

        String avgStr = "";
        String stdStr = "";

        avg = avgVec(cls0);
        std = findSD(cls0);

        String order = "";

        fw.write("cluster 0: \n");
        for (int i = 0; i < avg.length; i++) {
            if (top30.contains(all.get(i))) {
                order = order + "," + all.get(i);
                System.out.println(all.get(i));
                avgStr = avgStr + "," + avg[i];
                stdStr = stdStr + "," + std.get(i);
            }
        }

        fwOrd.write(order.substring(1));

        fw.write(avgStr.substring(1) + "\n" + stdStr.substring(1) + "\n\n");
////////////////////////////////////////////////////////////////////////////
        avgStr = "";
        stdStr = "";

        avg = avgVec(cls1);
        std = findSD(cls1);

        fw.write("cluster 1: \n");
        for (int i = 0; i < avg.length; i++) {
            if (top30.contains(all.get(i))) {
                avgStr = avgStr + "," + avg[i];
                stdStr = stdStr + "," + std.get(i);
            }
        }

        fw.write(avgStr.substring(1) + "\n" + stdStr.substring(1) + "\n\n");

        fw.close();
        br.close();
        brt.close();
        brp.close();
        fwOrd.close();
    }

    public static void reorder() throws FileNotFoundException, IOException {
        File fileIn = new File(path + "C0low.txt");
        BufferedReader br = new BufferedReader(new FileReader(fileIn));

        File fileO = new File(path + "Order.txt");
        BufferedReader bro = new BufferedReader(new FileReader(fileO));

        File fileOn = new File(path + "OrderNew.txt");
        BufferedReader bron = new BufferedReader(new FileReader(fileOn));

        File fileOut = new File(path + "C0lowOrder.txt");
        FileWriter fw = new FileWriter(fileOut);

        ArrayList<String> order = new ArrayList<String>();
        ArrayList<String> newOrder = new ArrayList<String>();
        ArrayList<String> mean = new ArrayList<String>();
        ArrayList<String> stdv = new ArrayList<String>();

        String line = "";

        while ((line = bro.readLine()) != null) {
            String[] token = line.split(",");
            for (String s : token) {
                order.add(s);
            }
        }

        while ((line = bron.readLine()) != null) {
            String[] token = line.split(",");
            for (String s : token) {
                newOrder.add(s);
            }
        }

        int f = 0;

        while ((line = br.readLine()) != null) {
            String[] token = line.split(",");
            if (f == 0) {
                for (String s : token) {
                    mean.add(s);
                }
                f = 1;
            } else {
                for (String s : token) {
                    stdv.add(s);
                }
            }
        }

        String avg = "";
        String std = "";

        for (int i = 0; i < newOrder.size(); i++) {
            String ptrn = newOrder.get(i);
            int ix = order.indexOf(ptrn);
            avg = avg + "," + mean.get(ix);
            std = std + "," + stdv.get(ix);
        }

        fw.write(avg.substring(1) + "\n" + std.substring(1));

        br.close();
        bro.close();
        bron.close();
        fw.close();

    }

}
