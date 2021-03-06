/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package education;

import com.sun.xml.internal.ws.util.StringUtils;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.text.DecimalFormat;
import java.util.LinkedHashMap;

/**
 *
 * @author mirza
 */
public class Education {

//    static String path = "C:\\Project\\EDU\\files\\2013\\example\\Topic\\cluster";
    static String path = "C:\\Project\\EDU\\files\\2013\\example\\Topic\\60";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
//        mergeThree();
//        mergeThreeAll();
//        forCMspam();
//        CMSPAM
//        SPMFtoTEXT();
//        remove length less than two
//        Create Top 30
//        findTop30();
//        findTop30Remove2();
//        createVectorUser();
//        normalize using python
//        readyNormal();
//        cluster using python
//        addCluster();
        createStatcluster();
//        findFrequencyPatternCluster();
//        findFrequencyPatternClusterAll();
//        sortDiff();
//        sortDiff3();
//        Plot 2 clusters using pyhton
//        String perf = "Pre";
//        perfCount2(perf);
//        perf = "Post";
//        perfCount2(perf);
//        perf = "LG";
//        perfCount2(perf);
//        findFrequencyPatternPerf(perf);
//        findFrequencyPatternPerfAll(perf);
//        reorder(perf);
//        perfCount2(perf);
//        findFrequencyPatternPerf3(perf);
//examplePerf("LG");
//        HashMap mp = findMinMaxQuiz();
//        dividDuration(mp);//old
//        dividDurationLabel(mp);
//        findSequence();
//        countSequence();
//        test();
//        countCompactSequence();
//        findSequenceProblem();
//        countCompactSequenceProblem();
//        readyforspmf();
//        readyforspmfnoComma();
//        readFile();
//        compactSequence();
//        createVector();
//        filterSeq();
//        testCM();
//        forCMspam();
//        translate();
//        SPMFtoTEXT();
//        count("", "");
//        createVectorUser();
//        createVectorUser();
//        countFreqCluster();
//        readStat();
//        readStatLowHi();
//        separate();
//        filterPGG();
//        normalizeStat();
//        createStat();
//        vec2normal();
//        createStatcluster();
//        findComplexity();
//        findFrequencyPattern();
//        findFrequencyPatternAll();
//        findFrequencyPatternFromCompact();
//        filterMapPerf();
//        findStudentSemester();
//        System.out.println(normal("4,0,0,1,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,2,6,0,3,0,0,1,6,12,1,0,0,0,1,2,10,0,0,0,0,2,0,0,0,12,0,0,0,0,0,4,0,6,4,0,0,0,0,0,0,0,0,0,0,4,0,0,0,0,0,0,0,0,0,0,0,0,0"));
//        findGenomeSequence();
//        filterComplexity();
//        findCommon();
//        findCourse();
//        statRaw();
//        findFrequencyPatternAll();
//        removeNoE();
//        reverse("OrderNew");
//        matchPerfSeq();
//==========================================
    }

    public static HashMap findMinMaxQuiz() throws FileNotFoundException, IOException {
//        File fileIn = new File("C:\\Project\\EDU\\Dataset.txt");
        File fileIn = new File("C:\\Project\\EDU\\files\\2013\\Dataset.txt");
        BufferedReader br = new BufferedReader(new FileReader(fileIn));

//        File fileOut = new File("C:\\Project\\EDU\\files\\medianQuizDuration.txt");
        File fileOut = new File("C:\\Project\\EDU\\files\\2013\\medianQuizDuration.txt");
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
//            Double min = Collections.min(duration.get(i));
//            Double max = Collections.max(duration.get(i));
//            Double mid = (max + min) / 2;
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

    public static void dividDuration(HashMap<String, Double> hm) throws FileNotFoundException, IOException {
        File fileIn = new File("C:\\Project\\EDU\\Dataset_sorted.txt");
        BufferedReader br = new BufferedReader(new FileReader(fileIn));

        File fileOut = new File("C:\\Project\\EDU\\files\\QuizLabel.txt");
        FileWriter fw = new FileWriter(fileOut);

        String line = "";
        while ((line = br.readLine()) != null) {
            String[] token = line.split("\t");
            Double duration = Double.valueOf(token[4]);
            Double threashold = hm.get(token[2]);
            String label = "l";
            if (duration < threashold) {
                label = "s";
            }
            fw.write(token[0] + "\t" + token[1] + "\t" + token[2] + "\t" + token[3] + "\t" + label + "\n");

        }

        br.close();
        fw.close();
    }

    public static void dividDurationLabel(HashMap<String, Double> hm) throws FileNotFoundException, IOException {
//        File fileIn = new File("C:\\Project\\EDU\\Dataset.txt");
        File fileIn = new File("C:\\Project\\EDU\\files\\2013\\Dataset.txt");
        BufferedReader br = new BufferedReader(new FileReader(fileIn));

//        File fileOut = new File("C:\\Project\\EDU\\files\\QuizLabelTime.txt");
        File fileOut = new File("C:\\Project\\EDU\\files\\2013\\QuizLabelTime.txt");
        FileWriter fw = new FileWriter(fileOut);

        String line = "";
        br.readLine();
        while ((line = br.readLine()) != null) {
            String[] token = line.split("\t");
            String result = token[3];
//            System.out.println(line);
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
//        File fileIn = new File("C:\\Project\\EDU\\files\\QuizLabelTime.txt");
        File fileIn = new File("C:\\Project\\EDU\\files\\2013\\QuizLabelTime.txt");
        BufferedReader br = new BufferedReader(new FileReader(fileIn));

//        File fileOut = new File("C:\\Project\\EDU\\files\\QuizLabelTimeSequenceActivity2.txt");
        File fileOut = new File("C:\\Project\\EDU\\files\\2013\\QuizLabelTimeSequence.txt");
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

            /*
            if (user.equals(oldUser)) {
                if (session.equals(oldSession)) {

                    if (activityname.equals(oldActivityname)) {

                    } else {
                        seq.add("_");
                    }
                } else {
                    seq.add("_");
                }
            } else {

                seq.add("_");
                fw.write(oldUser + "\t" + mergeSequence(seq) + "\n");
                seq = new ArrayList<String>();
            }

            seq.add(durationseconds);
             */
        }

        fw.write(oldUser + "\t_" + mergeSequence(seq) + "_\n");

        br.close();
        fw.close();
    }

    public static void findSequenceProblem() throws FileNotFoundException, IOException {
//        File fileIn = new File("C:\\Project\\EDU\\files\\QuizLabelTime.txt");
        File fileIn = new File("C:\\Project\\EDU\\files\\QuizLabelTime.txt");
        BufferedReader br = new BufferedReader(new FileReader(fileIn));

//        File fileOut = new File("C:\\Project\\EDU\\files\\QuizLabelTimeSequenceActivity3.txt");
        File fileOut = new File("C:\\Project\\EDU\\files\\QuizLabelTimeSequence.txt");
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

            if (!user.equals(oldUser)) {
                seq.add("_");
                fw.write(oldUser + "\t_" + mergeSequence(seq) + "\n");
                seq = new ArrayList<>();
            } else if (!session.equals(oldSession)) {
                seq.add("_");
            } else if (!activityname.equals(oldActivityname)) {
                seq.add(",");
            }

            seq.add(durationseconds);

        }

        fw.write(oldUser + "\t_" + mergeSequence(seq) + "_\n");

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
//        File fileIn = new File("C:\\Project\\EDU\\files\\QuizLabelTimeSequenceActivity2.txt");
        File fileIn = new File("C:\\Project\\EDU\\files\\2013\\revised\\SequenceFilter.txt");
        BufferedReader br = new BufferedReader(new FileReader(fileIn));

//        File fileOut = new File("C:\\Project\\EDU\\files\\SequenceCount.txt");
        File fileOut = new File("C:\\Project\\EDU\\files\\2013\\revised\\SequenceCount.txt");
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

        for (Map.Entry<String, Integer> entry : hm.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            System.out.println(key + "\t" + value);
            fw.write(key + "\t" + value + "\n");
            // do stuff
        }

        br.close();
        fw.close();
    }

    public static void countCompactSequence() throws FileNotFoundException, IOException {
        File fileIn = new File("C:\\Project\\EDU\\files\\QuizLabelTimeSequenceActivity2.txt");
        BufferedReader br = new BufferedReader(new FileReader(fileIn));

        File fileOut = new File("C:\\Project\\EDU\\files\\CompactSequenceCount.txt");
        FileWriter fw = new FileWriter(fileOut);

        HashMap<String, Integer> hm = new HashMap<String, Integer>();

        String line = "";

        while ((line = br.readLine()) != null) {
            String[] token = line.split("\t");
            String all = token[1];
            String[] seq = all.split("_");
            for (String s : seq) {
                s = convert(s);
                if (hm.containsKey(s)) {
                    int n = hm.get(s);
                    n++;
                    hm.put(s, n);
                } else {
                    hm.put(s, 1);
                }
            }
        }

        for (Map.Entry<String, Integer> entry : hm.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            System.out.println(key + "\t" + value);
            fw.write(key + "\t" + value + "\n");
            // do stuff
        }

        br.close();
        fw.close();
    }

    public static void countCompactSequenceProblem() throws FileNotFoundException, IOException {
        File fileIn = new File("C:\\Project\\EDU\\files\\QuizLabelTimeSequenceActivity3.txt");
        BufferedReader br = new BufferedReader(new FileReader(fileIn));

        File fileOut = new File("C:\\Project\\EDU\\files\\CompactSequenceActivityCount3.txt");
        FileWriter fw = new FileWriter(fileOut);

        HashMap<String, Integer> hm = new HashMap<String, Integer>();

        String line = "";

        while ((line = br.readLine()) != null) {
            String[] token = line.split("\t");
            String all = token[1];

            String[] session = all.split("_");
            for (String sesn : session) {
                if (sesn.length() < 1) {
                    continue;
                }
                sesn = "_" + sesn + "_";
//                System.out.println(sesn);
                String[] seq = sesn.split(",");
                for (String s : seq) {
                    s = convert(s);
                    if (hm.containsKey(s)) {
                        int n = hm.get(s);
                        n++;
                        hm.put(s, n);
                    } else {
                        hm.put(s, 1);
                    }
                }
            }

//            String[] seq = all.split(",");
//            for (String s : seq) {
//                s = convert(s);
//                if (hm.containsKey(s)) {
//                    int n = hm.get(s);
//                    n++;
//                    hm.put(s, n);
//                } else {
//                    hm.put(s, 1);
//                }
//            }
        }

        for (Map.Entry<String, Integer> entry : hm.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
//            System.out.println(key + "\t" + value);
            fw.write(key + "\t" + value + "\n");
            // do stuff
        }

        br.close();
        fw.close();
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

        return out;
    }

    public static void test() {
        String out = "";
        String s = "FFFFssfSSSFfsSSSSsfffFfFsss";
        String pattern1 = "s{2,}";
        Pattern ptrn1 = Pattern.compile(pattern1);
        Matcher matcher1 = ptrn1.matcher(s);
        out = matcher1.replaceAll("s+");
//        System.out.println(out);

        String pattern2 = "S{2,}";
        Pattern ptrn2 = Pattern.compile(pattern2);
        Matcher matcher2 = ptrn2.matcher(out);
        out = matcher2.replaceAll("S+");
//        System.out.println(out);

        String pattern3 = "f{2,}";
        Pattern ptrn3 = Pattern.compile(pattern3);
        Matcher matcher3 = ptrn3.matcher(out);
        out = matcher3.replaceAll("f+");
//        System.out.println(out);

        String pattern4 = "F{2,}";
        Pattern ptrn4 = Pattern.compile(pattern4);
        Matcher matcher4 = ptrn4.matcher(out);
        out = matcher4.replaceAll("F+");
//        System.out.println(out);

    }

    public static void readyforspmf() throws FileNotFoundException, IOException {
        File fileIn = new File("C:\\Project\\EDU\\files\\QuizLabelTimeSequenceActivity3.txt");
        BufferedReader br = new BufferedReader(new FileReader(fileIn));

        File fileOut = new File("C:\\Project\\EDU\\files\\QuizLabelTimeSequenceActivitySPMF3.txt");
        FileWriter fw = new FileWriter(fileOut);
        String line = "";

        while ((line = br.readLine()) != null) {
            String out = "5 ";

            String sequence = line.split("\t")[1];

            for (int i = 1; i < sequence.length() - 1; i++) {
                if (sequence.charAt(i) == '_') {
                    out += "5 -1 5 ";
                } else if (sequence.charAt(i) == ',') {
                    out += "-1 ";
                } else {
                    out += coding(sequence.charAt(i)) + " ";
                }
            }
            System.out.println(out + "5 -2");
            fw.write(out + "5 -2\n");

        }

        br.close();
        fw.close();
    }

    public static void readyforspmfnoComma() throws FileNotFoundException, IOException {
        File fileIn = new File("C:\\Project\\EDU\\files\\2013\\QuizLabelTimeSequence.txt");
        BufferedReader br = new BufferedReader(new FileReader(fileIn));

        File fileOut = new File("C:\\Project\\EDU\\files\\2013\\QuizLabelTimeSequenceSPMF.txt");
        FileWriter fw = new FileWriter(fileOut);
        String line = "";

        while ((line = br.readLine()) != null) {
            String out = "5 ";

            String sequence = line.split("\t")[1];

            for (int i = 1; i < sequence.length() - 1; i++) {
                if (sequence.charAt(i) == '_') {
                    out += "5 -1 5 ";
                } else {
                    out += coding(sequence.charAt(i)) + " ";
                }
            }
            System.out.println(out + "5 -2");
            fw.write(out + "5 -2\n");

        }

        br.close();
        fw.close();
    }

    public static String coding(char c) {
        String out = "";

        if (c == 's') {
            out = "1";
        }
        if (c == 'S') {
            out = "2";
        }
        if (c == 'f') {
            out = "3";
        }
        if (c == 'F') {
            out = "4";
        }

        return out;

    }

    static void readFile() throws FileNotFoundException, IOException {
        String folderName = "C:\\Project\\EDU\\Canvas\\CNCAU_1403-1509_R_v1_03-03-2016\\";
        File folder = new File(folderName);

        for (File fileEntry : folder.listFiles()) {
            String fileName = fileEntry.getName();

            System.out.println(fileEntry.getName());

            String filePath = folderName + fileName;

            File fileIn = new File(filePath);
            BufferedReader br = new BufferedReader(new FileReader(fileIn));

            File fileOut = new File(filePath + ".txt");
            FileWriter fw = new FileWriter(fileOut);

            String line = "";
            int c = 0;
            while ((line = br.readLine()) != null) {
//                System.out.println(line);
                fw.write(line + "\n");
                c++;
                if (c == 1000) {
                    break;
                }
            }
            br.close();
            fw.close();
        }

    }

    public static void compactSequence() throws FileNotFoundException, IOException {
        File fileIn = new File("C:\\Project\\EDU\\files\\QuizLabelTimeSequenceActivity3.txt");
        BufferedReader br = new BufferedReader(new FileReader(fileIn));

        File fileOut = new File("C:\\Project\\EDU\\files\\QuizLabelTimeSequenceActivityCompact3.txt");
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

    public static void createVector() throws FileNotFoundException, IOException {
        File fileIns = new File("C:\\Project\\EDU\\files\\TopSequences.txt");
        BufferedReader brs = new BufferedReader(new FileReader(fileIns));

        File fileIn = new File("C:\\Project\\EDU\\files\\QuizLabelTimeSequenceActivityCompact3.txt");
        BufferedReader br = new BufferedReader(new FileReader(fileIn));

        File fileOut = new File("C:\\Project\\EDU\\files\\QuizLabelTimeSequenceActivityCompactVectorNormal.txt");
        FileWriter fw = new FileWriter(fileOut);

        String line = "";

        ArrayList<String> topseq = new ArrayList<String>();
//        ArrayList<HashMap<String, String>> profile = new ArrayList<HashMap<String, String>>();
//        ArrayList<ArrayList<String>> vector = new ArrayList<ArrayList<String>>();

        while ((line = brs.readLine()) != null) {
            topseq.add(line.trim());
        }

        while ((line = br.readLine()) != null) {
            String[] token = line.split("\t");
            String id = token[0];
            String seq = token[1];
            String[] seqs = seq.split(",");
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

            fw.write(id + "\t" + printVector(vector, number) + "\n");

        }

        brs.close();
        br.close();
        fw.close();

    }

    public static String printVector(ArrayList<Integer> a, int number) {
        String out = "";

        for (int i = 0; i < a.size(); i++) {
            out = out + "," + (float) a.get(i) / number;
        }

        return out.substring(1);
    }

    public static void filterSeq() throws FileNotFoundException, IOException {
        File fileIn = new File("C:\\Project\\EDU\\files\\2013\\QuizLabelTimeSequenceSPMFfrequent2.txt");
        BufferedReader br = new BufferedReader(new FileReader(fileIn));

        File fileOut = new File("C:\\Project\\EDU\\files\\2013\\QuizLabelTimeSequenceSPMFfrequentNumber.txt");
        FileWriter fw = new FileWriter(fileOut);
        String line = "";

        int c = 0;

        while ((line = br.readLine()) != null) {
            line = line.split("#")[0];
            String[] token = line.split("-1");
            c++;
//            System.out.print(c + "\t");
//            fw.write(c + "\t");
            for (String s : token) {
                int l = s.length();
                if (l > 5) {

//                    System.out.print(l + ",");
                    fw.write("line " + c + "\tLenght " + l + "\tSequence " + s + "\n");
                }
//                System.out.print(s.length() + ",");
            }
//            System.out.println("");
//            fw.write("\n");
        }

        br.close();
        fw.close();

    }

    public static void forCMspam() throws FileNotFoundException, IOException {
//        File fileIn = new File("C:\\Project\\EDU\\files\\2013\\QuizLabelTimeSequence.txt");
//        File fileIn = new File("C:\\Project\\EDU\\files\\2013\\revised\\clean\\Sequence.txt");
//        File fileIn = new File("C:\\Project\\EDU\\files\\2013\\revised\\clean\\Stability\\Sequence.txt");
//        File fileIn = new File("C:\\Project\\EDU\\files\\2013\\revised\\complexity\\QuizLabelTimeGenomeSequenceFilter.txt");
//        File fileIn = new File("C:\\Project\\EDU\\files\\2013\\example\\LabelSequenceFilter.txt");
//        File fileIn = new File("C:\\Project\\EDU\\files\\2013\\example\\Line\\LabelSequencePlusFilter.txt");
//        File fileIn = new File("C:\\Project\\EDU\\files\\2013\\example\\Topic\\LabelSequenceFilter.txt");
//        File fileIn = new File(path + "\\LabelSequenceFilter.txt");
        File fileIn = new File(path + "\\Sequence.txt");
        BufferedReader br = new BufferedReader(new FileReader(fileIn));

//        File fileOut = new File("C:\\Project\\EDU\\files\\2013\\QuizLabelTimeSequenceCMSPAMdot.text");
//        File fileOut = new File("C:\\Project\\EDU\\files\\2013\\revised\\clean\\SequenceCMSPAM.text");
//        File fileOut = new File("C:\\Project\\EDU\\files\\2013\\revised\\clean\\Stability\\Sequence60P1CMSPAM.text");
//        File fileOut = new File("C:\\Project\\EDU\\files\\2013\\revised\\complexity\\SequenceCMSPAM.text");
//        File fileOut = new File("C:\\Project\\EDU\\files\\2013\\example\\SequenceCMSPAM.text");
//        File fileOut = new File("C:\\Project\\EDU\\files\\2013\\example\\Line\\SequencePlusCMSPAM.text");
//        File fileOut = new File("C:\\Project\\EDU\\files\\2013\\example\\Topic\\SequenceCMSPAM.text");
        File fileOut = new File(path + "\\SequenceFilterCMSPAM.text");
        FileWriter fw = new FileWriter(fileOut);

        String line = "";
        while ((line = br.readLine()) != null) {
            String seq = line.split("\t")[1];
//            seq = seq.replace("_", "x");
            seq = seq.replace("F", "h");
            seq = seq.replace("S", "b");
            seq = seq.replace("+", "p");
            seq = seq.replace("E", "i");

            String[] token = seq.split("_");

            for (String t : token) {
                if (t.length() > 0) {
//                    String out = "x" + t.replace("", " ") + "_";
                    String out = "x" + t.replace("", " ") + "x";
                    fw.write(out + ".\n");
                }

            }
        }

        br.close();
        fw.close();
    }

    public static void testCM() {
        String s = "ffFSs";
        System.out.println("_ " + s.replace("", " ") + " _");
    }

    public static void SPMFtoTEXT() throws FileNotFoundException, IOException {
//        File fileIn = new File("C:\\Project\\EDU\\files\\2013\\QuizLabelTimeSequenceCMSPAMpatternsAll.txt");
//        File fileIn = new File("C:\\Project\\EDU\\files\\2013\\revised\\clean\\Stability\\Patterns.txt");
//        File fileIn = new File("C:\\Project\\EDU\\files\\2013\\revised\\complexity\\SequenceCMSPAM.txt");
//        File fileIn = new File("C:\\Project\\EDU\\files\\2013\\example\\Line\\pattern301Plus.txt");
//        File fileIn = new File("C:\\Project\\EDU\\files\\2013\\example\\Topic\\patterns3.txt");
        File fileIn = new File(path + "\\patterns.txt");
        BufferedReader br = new BufferedReader(new FileReader(fileIn));

//        File fileOut = new File("C:\\Project\\EDU\\files\\2013\\QuizLabelTimeSequenceCMSPAMpatternsAllTranslateSUP.text");
//        File fileOut = new File("C:\\Project\\EDU\\files\\2013\\revised\\clean\\Stability\\PatternsTranslate.txt");
//        File fileOut = new File("C:\\Project\\EDU\\files\\2013\\revised\\complexity\\SequenceCMSPAMtranslate.txt");
//        File fileOut = new File("C:\\Project\\EDU\\files\\2013\\example\\Line\\pattern301PlusTranslate.txt");
//        File fileOut = new File("C:\\Project\\EDU\\files\\2013\\example\\Topic\\3\\patternsTranslate.txt");
        File fileOut = new File(path + "\\patternsTranslate.txt");
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
        t = t.replace("h", "F");
        t = t.replace("b", "S");
        t = t.replace("i", "E");
        t = t.replace("p", "+");
        System.out.println(t.replaceAll(" -1 ", ""));
        return t.replaceAll(" -1 ", "") + "\t" + sup.trim();
    }

    public static int count(String str, String findStr) {
//        String str = "_s_S_S_S_ssss_ss_FSSs_ssss_SSS_";
//        String findStr = "ss";
        int lastIndex = 0;
        int count = 0;

        while (lastIndex != -1) {

            lastIndex = str.indexOf(findStr, lastIndex);

            if (lastIndex != -1) {
                count++;
                lastIndex++;
//                lastIndex += findStr.length();
            }
        }
//        System.out.println(count);
        return count;
    }

    public static void createVectorUser() throws FileNotFoundException, IOException {
//        File fileIn1 = new File("C:\\Project\\EDU\\files\\2013\\QuizLabelTimeSequence.txt");
//        File fileIn1 = new File("C:\\Project\\EDU\\files\\2013\\revised\\complexity\\QuizLabelTimeGenomeSequenceFilter.txt");
//        File fileIn1 = new File("C:\\Project\\EDU\\files\\2013\\example\\LabelSequenceFilter.txt");
//        File fileIn1 = new File("C:\\Project\\EDU\\files\\2013\\example\\Topic\\LabelSequenceFilter.txt");
        File fileIn1 = new File(path + "\\Sequence.txt");

        BufferedReader br1 = new BufferedReader(new FileReader(fileIn1));

//        File fileIn2 = new File("C:\\Project\\EDU\\files\\2013\\QuizLabelTimeSequenceCMSPAMpatternsTranslate.text");
//        File fileIn2 = new File("C:\\Project\\EDU\\files\\2013\\revised\\clean\\Stability\\midTerm\\PatternsTranslateFilter.txt");
//        File fileIn2 = new File("C:\\Project\\EDU\\files\\2013\\revised\\complexity\\SequenceCMSPAMtranslateFilter.txt");
//        File fileIn2 = new File("C:\\Project\\EDU\\files\\2013\\example\\patternTranslateFilter.txt");
//        File fileIn2 = new File("C:\\Project\\EDU\\files\\2013\\example\\Topic\\3\\patternsTranslate.txt");
        File fileIn2 = new File(path + "\\patternsTranslateFilter.txt");

        BufferedReader br2 = new BufferedReader(new FileReader(fileIn2));

//        File fileOut = new File("C:\\Project\\EDU\\files\\2013\\QuizLabelTimeSequenceVectorNormal.txt");
//        File fileOut = new File("C:\\Project\\EDU\\files\\2013\\revised\\clean\\Stability\\midTerm\\Sequence60P1Vector.txt");
//        File fileOut = new File("C:\\Project\\EDU\\files\\2013\\revised\\complexity\\SequenceVector.txt");
//        File fileOut = new File("C:\\Project\\EDU\\files\\2013\\example\\SequenceVector.txt");
//        File fileOut = new File("C:\\Project\\EDU\\files\\2013\\example\\Topic\\3\\SequenceVector.txt");
        File fileOut = new File(path + "\\SequenceVector.txt");
        FileWriter fw = new FileWriter(fileOut);

//        HashMap<String, Integer> hm = new HashMap<String, Integer>();
        ArrayList<String> ptrn = new ArrayList<String>();

        String line = "";

        while ((line = br2.readLine()) != null) {
            line = line.split("\\s+")[0];
//            System.out.println(line);
            if (line.replace("_", "").length() > 1) {
                System.out.println(line);
//                hm.put(line.trim(), 0);
                ptrn.add(line.trim());
            }
        }

        while ((line = br1.readLine()) != null) {
            String seq = line.split("\t")[1].trim();
            String id = line.split("\t")[0];
            String vector = "";
            for (String s : ptrn) {
                int c = count(seq, s);
//                System.out.println("s: " + s);
//                System.out.println("c: " + c);
                vector = vector + "," + c;
            }
            System.out.println(vector.substring(1));
//            fw.write(id + "\t" + normal(vector.substring(1)) + "\n");
            fw.write(id + "\t" + vector.substring(1) + "\n");
//            for (String s : hm.keySet()) {
//                hm.put(s, 0);
//            }
        }

        br1.close();
        br2.close();
        fw.close();

    }

    public static String normal(String str) {
        String[] token = str.split(",");
        int min = Integer.valueOf(token[0]);
        int max = min;

        for (String s : token) {
            int n = Integer.valueOf(s);
            if (n > max) {
                max = n;
            }
            if (n < min) {
                min = n;
            }
        }

        String out = "";

        for (String s : token) {
            int n = Integer.valueOf(s);
            Double d = (double) (n - min) / (double) (max - min);
            out = out + "," + d;
        }

        return out.substring(1);
    }

    public static String normalMax(String str) {
        String[] token = str.split(",");
        int max = Integer.valueOf(token[0]);
//        int max = min;

        for (String s : token) {
            int n = Integer.valueOf(s);
            if (n > max) {
                max = n;
            }
        }

        String out = "";

        for (String s : token) {
            int n = Integer.valueOf(s);
            double value = (double) n / (double) max;
            value = Double.parseDouble(new DecimalFormat("##.##").format(value));
            out = out + "," + value;
        }

        return out.substring(1);
    }

    public static String normal1(String str) {
        String[] token = str.split(",");
        int sum = 0;

        for (String s : token) {
            int n = Integer.valueOf(s);
            sum += n;
        }

        String out = "";

        for (String s : token) {
            int n = Integer.valueOf(s);
            double value = (double) n / (double) sum;
            value = Double.parseDouble(new DecimalFormat("##.##").format(value));
            out = out + "," + value;
        }

        return out.substring(1);
    }

    public static void countFreqCluster() throws FileNotFoundException, IOException {
        File fileIn = new File("C:\\Project\\EDU\\files\\2013\\QuizLabelTimeSequence.txt");
        BufferedReader br = new BufferedReader(new FileReader(fileIn));

        File fileIns = new File("C:\\Project\\EDU\\files\\2013\\studentCluster.txt");
        BufferedReader brs = new BufferedReader(new FileReader(fileIns));

        File fileInp = new File("C:\\Project\\EDU\\files\\2013\\top30.txt");
        BufferedReader brp = new BufferedReader(new FileReader(fileInp));

        File fileOut = new File("C:\\Project\\EDU\\files\\2013\\stat3.txt");
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

    public static void readStat() throws FileNotFoundException, IOException {
        File fileIns = new File("C:\\Project\\EDU\\files\\2013\\stat3.txt");
        BufferedReader brs = new BufferedReader(new FileReader(fileIns));

//        File fileInp = new File("C:\\Project\\EDU\\files\\2013\\top30.txt");
//        BufferedReader brp = new BufferedReader(new FileReader(fileInp));
        File fileOut = new File("C:\\Project\\EDU\\files\\2013\\stat3countMinMaxAve.txt");
        FileWriter fw = new FileWriter(fileOut);

        String line = "";

//        HashMap<String, ArrayList<Integer>> hm0 = new HashMap<String, ArrayList<Integer>>();
//        HashMap<String, ArrayList<Integer>> hm1 = new HashMap<String, ArrayList<Integer>>();
        int[] c0 = new int[30];
        int[] c0min = new int[30];
        int[] c0max = new int[30];

        int[] c1 = new int[30];
        int[] c1min = new int[30];
        int[] c1max = new int[30];

        for (int i = 0; i < c0min.length; i++) {
            c0min[i] = 10000;
        }

        for (int i = 0; i < c1min.length; i++) {
            c1min[i] = 10000;
        }

        for (int i = 0; i < c0max.length; i++) {
            c0max[i] = 0;
        }

        for (int i = 0; i < c1max.length; i++) {
            c1max[i] = 0;
        }

//        ArrayList<String> pattern = new ArrayList<String>();
//        while ((line = brp.readLine()) != null){
////            pattern.add(line);
//            ArrayList<Integer> empty = new ArrayList<Integer>();
//            hm0.put(line, empty);
//            hm1.put(line, empty);
//        }
        int c0l = 0;
        int c1l = 0;

        while ((line = brs.readLine()) != null) {
            String[] token = line.split("\t");
            String cl = token[1];
            String vec = token[2];

            if (cl.equals("0")) {
                c0l++;
                int[] temp = new int[30];
                temp = Arrays.stream(vec.split(",")).mapToInt(Integer::parseInt).toArray();
                for (int i = 0; i < c0.length; i++) {
//                    int min = 10000;
//                    int max = 0;
                    c0[i] += temp[i];
                    if (temp[i] > c0max[i]) {
                        c0max[i] = temp[i];
                    }
                    if (temp[i] < c0min[i]) {
                        c0min[i] = temp[i];
                    }
                }
                for (int n : c0) {
                    System.out.print(n + ",");
                }
                System.out.println("");
            } else if (cl.equals("1")) {
                c1l++;
                int[] temp = new int[30];
                temp = Arrays.stream(vec.split(",")).mapToInt(Integer::parseInt).toArray();
                for (int i = 0; i < c1.length; i++) {
                    c1[i] += temp[i];
                    if (temp[i] > c1max[i]) {
                        c1max[i] = temp[i];
                    }
                    if (temp[i] < c1min[i]) {
                        c1min[i] = temp[i];
                    }
                }
//                for (int n : c1) {
//                    System.out.print(n + ",");
//                }
//                System.out.println("");
            }

        }
        String out = "";
        String min = "";
        String max = "";
        for (int i = 0; i < c0.length; i++) {
            out = out + "," + c0[i] / c0l;
            min = min + "," + c0min[i];
            max = max + "," + c0max[i];

        }
        fw.write("0:\t" + out.substring(1) + "\n");
        fw.write("min:\t" + min.substring(1) + "\n");
        fw.write("max:\t" + max.substring(1) + "\n");

        out = "";
        min = "";
        max = "";
        for (int i = 0; i < c1.length; i++) {
            out = out + "," + c1[i] / c1l;
            min = min + "," + c1min[i];
            max = max + "," + c1max[i];

        }
        fw.write("1:\t" + out.substring(1) + "\n");
        fw.write("min:\t" + min.substring(1) + "\n");
        fw.write("max:\t" + max.substring(1) + "\n");

        brs.close();
//        brp.close();
        fw.close();
    }

    public static void readStatLowHi() throws FileNotFoundException, IOException {
        File fileIns = new File("C:\\Project\\EDU\\files\\2013\\stat3.txt");
        BufferedReader brs = new BufferedReader(new FileReader(fileIns));

        File fileInp = new File("C:\\Project\\EDU\\files\\2013\\LearningGainPerf.txt");
        BufferedReader brp = new BufferedReader(new FileReader(fileInp));

        File fileOut = new File("C:\\Project\\EDU\\files\\2013\\stat3countMinMaxAveLowHiC0.txt");
        FileWriter fw = new FileWriter(fileOut);

        String line = "";

        HashMap<String, String> hm = new HashMap<String, String>();

        int[] l = new int[30];
        int[] lmin = new int[30];
        int[] lmax = new int[30];

        int[] h = new int[30];
        int[] hmin = new int[30];
        int[] hmax = new int[30];

        for (int i = 0; i < l.length; i++) {
            lmin[i] = 10000;
            lmax[i] = 0;
            hmin[i] = 10000;
            hmax[i] = 0;
        }

        brp.readLine();
        while ((line = brp.readLine()) != null) {
            String[] token = line.split("\t");
            hm.put(token[0], token[1]);
        }

        int ll = 0;
        int hl = 0;

        while ((line = brs.readLine()) != null) {
            String[] token = line.split("\t");
            String id = token[0];
            String clstr = token[1];
            String vec = token[2];

            if (clstr.equals("0")) {
                if (hm.get(id).equals("l")) {
                    ll++;
                    int[] temp = new int[30];
                    temp = Arrays.stream(vec.split(",")).mapToInt(Integer::parseInt).toArray();
                    for (int i = 0; i < l.length; i++) {
                        l[i] += temp[i];
                        if (temp[i] > lmax[i]) {
                            lmax[i] = temp[i];
                        }
                        if (temp[i] < lmin[i]) {
                            lmin[i] = temp[i];
                        }
                    }
                    for (int n : l) {
                        System.out.print(n + ",");
                    }
                    System.out.println("");
                } else if (hm.get(id).equals("h")) {
                    hl++;
                    int[] temp = new int[30];
                    temp = Arrays.stream(vec.split(",")).mapToInt(Integer::parseInt).toArray();
                    for (int i = 0; i < h.length; i++) {
                        h[i] += temp[i];
                        if (temp[i] > hmax[i]) {
                            hmax[i] = temp[i];
                        }
                        if (temp[i] < hmin[i]) {
                            hmin[i] = temp[i];
                        }
                    }
                }
            }
        }

        String out = "";
        String min = "";
        String max = "";

        for (int i = 0; i < l.length; i++) {
            double value = (double) h[i] / (double) ll;
            value = Double.parseDouble(new DecimalFormat("##.##").format(value));
            out = out + "," + value;
            min = min + "," + lmin[i];
            max = max + "," + lmax[i];
        }

        fw.write("l:\t" + out.substring(1) + "\n");
        fw.write("min:\t" + min.substring(1) + "\n");
        fw.write("max:\t" + max.substring(1) + "\n");

        out = "";
        min = "";
        max = "";

        for (int i = 0; i < h.length; i++) {
            double value = (double) h[i] / (double) hl;
            value = Double.parseDouble(new DecimalFormat("##.##").format(value));
            out = out + "," + value;
            min = min + "," + hmin[i];
            max = max + "," + hmax[i];
        }
        System.out.println(out);
        fw.write("h:\t" + out.substring(1) + "\n");
        fw.write("min:\t" + min.substring(1) + "\n");
        fw.write("max:\t" + max.substring(1) + "\n");

        brs.close();
        brp.close();
        fw.close();
    }

    public static void separate() throws FileNotFoundException, IOException {
        File fileInp = new File("C:\\Project\\EDU\\files\\2013\\LearningGainPerf.txt");
        BufferedReader brp = new BufferedReader(new FileReader(fileInp));

        File fileOutl = new File("C:\\Project\\EDU\\files\\2013\\LowLG.txt");
        FileWriter fwl = new FileWriter(fileOutl);

        File fileOuth = new File("C:\\Project\\EDU\\files\\2013\\HiLG.txt");
        FileWriter fwh = new FileWriter(fileOuth);

        ArrayList<String> L = new ArrayList<String>();
        ArrayList<String> H = new ArrayList<String>();

        String line = "";

        brp.readLine();
        while ((line = brp.readLine()) != null) {
            String[] token = line.split("\t");
            String id = token[0];
            String cls = token[1];
            if (cls.equals("l")) {
                L.add(id);

            } else if (cls.equals("h")) {
                H.add(id);
            }
        }
        System.out.println("LOW");
        for (String s : L) {
            System.out.println(s);
            fwl.write(s + "\n");
        }
        System.out.println("===============\nHIGH");
        ;
        for (String s : H) {
            fwh.write(s + "\n");
            System.out.println(s);
        }

        brp.close();
        fwl.close();
        fwh.close();

    }

    public static void filterPGG() throws FileNotFoundException, IOException {
        File fileIn = new File("C:\\Project\\EDU\\files\\2013\\stat3.txt");
        BufferedReader br = new BufferedReader(new FileReader(fileIn));

        File fileInlg = new File("C:\\Project\\EDU\\files\\2013\\HiLG.txt");
        BufferedReader brlg = new BufferedReader(new FileReader(fileInlg));

        File fileOut = new File("C:\\Project\\EDU\\files\\2013\\HighStat.txt");
        FileWriter fw = new FileWriter(fileOut);

        ArrayList<String> lgid = new ArrayList<String>();

        String line = "";

        while ((line = brlg.readLine()) != null) {
            lgid.add(line.trim());
        }

        while ((line = br.readLine()) != null) {
            String[] token = line.split("\t");
            String id = token[0];
            String cls = token[1];
            String vec = token[2];

            if (lgid.contains(id)) {
                fw.write(line + "\n");
            }
        }

        br.close();
        brlg.close();
        fw.close();
    }

    public static void normalizeStat() throws FileNotFoundException, IOException {
        File fileIn = new File("C:\\Project\\EDU\\files\\2013\\stat3.txt");
        BufferedReader br = new BufferedReader(new FileReader(fileIn));

        File fileInp = new File("C:\\Project\\EDU\\files\\2013\\LearningGainPerf.txt");
        BufferedReader brp = new BufferedReader(new FileReader(fileInp));

        File fileOut = new File("C:\\Project\\EDU\\files\\2013\\StatNormal.txt");
        FileWriter fw = new FileWriter(fileOut);

        HashMap<String, String> hm = new HashMap<String, String>();

        String line = "";

        while ((line = brp.readLine()) != null) {
            String[] token = line.split("\t");
            hm.put(token[0].trim(), token[1].trim());
        }

        while ((line = br.readLine()) != null) {
            String[] token = line.split("\t");
            String id = token[0];
            String cls = token[1];
            String vec = token[2];
            String lg = hm.get(id);

            fw.write(id + "\t" + cls + "\t" + lg + "\t" + normalMax(vec) + "\n");

        }

        br.close();
        brp.close();
        fw.close();
    }

    public static void createStat() throws FileNotFoundException, IOException {
        File fileIn1 = new File("C:\\Project\\EDU\\files\\2013\\QuizLabelTimeSequenceVectorNorml1.txt");
        BufferedReader br1 = new BufferedReader(new FileReader(fileIn1));

        File fileIn2 = new File("C:\\Project\\EDU\\files\\2013\\StatNormal.txt");
        BufferedReader br2 = new BufferedReader(new FileReader(fileIn2));

        File fileOut = new File("C:\\Project\\EDU\\files\\2013\\StatNormal2.txt");
        FileWriter fw = new FileWriter(fileOut);

        String line = "";

        HashMap<String, String> hm = new HashMap<String, String>();

        while ((line = br1.readLine()) != null) {
            String id = line.split("\t")[0];
            String vec = line.split("\t")[1];
            hm.put(id, vec);
        }

        while ((line = br2.readLine()) != null) {
            String[] token = line.split("\t");
            String id = token[0];
            String cls = token[1];
            String perf = token[2];
            String vec = token[3];

            if (hm.containsKey(id)) {
                fw.write(id + "\t" + cls + "\t" + perf + "\t" + hm.get(id) + "\n");
            }
        }

        br1.close();
        br2.close();
        fw.close();
    }

    public static void vec2normal() throws FileNotFoundException, IOException {
        File fileIn = new File("C:\\Project\\EDU\\files\\2013\\QuizLabelTimeSequenceVector.txt");
        BufferedReader br = new BufferedReader(new FileReader(fileIn));

        File fileOut = new File("C:\\Project\\EDU\\files\\2013\\QuizLabelTimeSequenceVectorNormal1.txt");
        FileWriter fw = new FileWriter(fileOut);

        String line = "";

        while ((line = br.readLine()) != null) {
//            System.out.println(line);
            String[] token = line.split("\t");
            String id = token[0];
            String vec = token[1];

            fw.write(id + "\t" + normal1(vec) + "\n");
        }

        br.close();
        fw.close();
    }

    public static void createStatcluster() throws FileNotFoundException, IOException {
//        File fileInp = new File("C:\\Project\\EDU\\files\\2013\\perf.txt");
        File fileInp = new File(path + "\\perfAll.txt");
        BufferedReader brp = new BufferedReader(new FileReader(fileInp));

//        File fileInc = new File("C:\\Project\\EDU\\files\\2013\\QuizLabelTimeSequenceVectorNormal1Cluster.txt");
//        File fileInc = new File("C:\\Project\\EDU\\files\\2013\\revised\\clean\\SequenceVectorNormalCluster.txt");
//        File fileInc = new File("C:\\Project\\EDU\\files\\2013\\example\\VectorNormal1Cluster.txt");
//        File fileInc = new File("C:\\Project\\EDU\\files\\2013\\example\\Topic\\3\\VectorNormal1Cluster.txt");
        File fileInc = new File(path + "\\VectorNormal1Cluster.txt");
        BufferedReader brc = new BufferedReader(new FileReader(fileInc));

//        File fileInv = new File("C:\\Project\\EDU\\files\\2013\\QuizLabelTimeSequenceVectorNormal1.txt");
//        File fileInv = new File("C:\\Project\\EDU\\files\\2013\\revised\\clean\\SequenceVectorNormal.txt");
//        File fileInv = new File("C:\\Project\\EDU\\files\\2013\\example\\VectorNormal1.txt");
//        File fileInv = new File("C:\\Project\\EDU\\files\\2013\\example\\Topic\\3\\VectorNormal1.txt");
        File fileInv = new File(path + "\\VectorNormal1.txt");
        BufferedReader brv = new BufferedReader(new FileReader(fileInv));

//        File fileOut = new File("C:\\Project\\EDU\\files\\2013\\statNormal1.txt");
//        File fileOut = new File("C:\\Project\\EDU\\files\\2013\\revised\\clean\\statNormal.txt");
//        File fileOut = new File("C:\\Project\\EDU\\files\\2013\\example\\statNormal.txt");
//        File fileOut = new File("C:\\Project\\EDU\\files\\2013\\example\\Topic\\3\\statNormal.txt");
        File fileOut = new File(path + "\\statNormal.txt");
        FileWriter fw = new FileWriter(fileOut);

        String line = "";

        HashMap<String, String> hmperf = new HashMap<String, String>();
        HashMap<String, String> hmcls = new HashMap<String, String>();

        while ((line = brp.readLine()) != null) {
            String[] token = line.split("\t");
            String id = token[0];
//            String prf = token[1];
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

    public static void filterMapPerf() throws FileNotFoundException, IOException {
        File fileInp = new File("C:\\Project\\EDU\\files\\2013\\revised\\perf.txt");
        BufferedReader brp = new BufferedReader(new FileReader(fileInp));

        File fileIn = new File("C:\\Project\\EDU\\files\\2013\\revised\\QuizLabelTimeSequenceFilter.txt");
        BufferedReader br = new BufferedReader(new FileReader(fileIn));

        File fileOut = new File("C:\\Project\\EDU\\files\\2013\\revised\\QuizLabelTimeSequenceFilterPerf.txt");
        FileWriter fw = new FileWriter(fileOut);

        File fileOutf = new File("C:\\Project\\EDU\\files\\2013\\revised\\QuizLabelTimeSequenceFilterPerfFilter.txt");
        FileWriter fwf = new FileWriter(fileOutf);

        String line = "";

        HashMap<String, String> hmp = new HashMap<String, String>();
        HashMap<String, String> hms = new HashMap<String, String>();

        brp.readLine();
        while ((line = brp.readLine()) != null) {
            String[] token = line.split("\t");
            String id = token[0];
            hmp.put(id, line);
        }

        while ((line = br.readLine()) != null) {
            String[] token = line.split("\t");
            String id = token[0];
            hms.put(id, line);
        }

        for (String id : hms.keySet()) {
            if (hmp.containsKey(id)) {
                fwf.write(hms.get(id) + "\n");
            }
        }

        for (String id : hmp.keySet()) {
            if (hms.containsKey(id)) {
                fw.write(hmp.get(id) + "\n");
            }
        }

        brp.close();
        br.close();
        fw.close();
        fwf.close();

    }

    public static void findStudentSemester() throws FileNotFoundException, IOException {
        File fileInp = new File("C:\\Project\\EDU\\files\\2013\\revised\\perfFilter.txt");
        BufferedReader brp = new BufferedReader(new FileReader(fileInp));

        File fileIns = new File("C:\\Project\\EDU\\files\\2013\\revised\\studentSemester.txt");
        BufferedReader brs = new BufferedReader(new FileReader(fileIns));

        File fileOut = new File("C:\\Project\\EDU\\files\\2013\\revised\\perfFilterSemester.txt");
        FileWriter fw = new FileWriter(fileOut);

        HashMap<String, String> hms = new HashMap<String, String>();

        String line = "";

        while ((line = brs.readLine()) != null) {
            String[] token = line.split("\t");
            String id = token[0];
            String smstr = token[1];
            hms.put(id, smstr);
        }

        while ((line = brp.readLine()) != null) {
            String id = line.split("\t")[0];
            if (hms.containsKey(id)) {
                fw.write(line + "\t" + hms.get(id) + "\n");
            }
        }

        brp.close();
        brs.close();
        fw.close();
    }

    public static void findComplexity() throws FileNotFoundException, IOException {
        File fileIn = new File("C:\\Project\\EDU\\files\\2013\\revised\\complexity\\examples.txt");
        BufferedReader br = new BufferedReader(new FileReader(fileIn));

        File fileOut = new File("C:\\Project\\EDU\\files\\2013\\revised\\complexity\\examplesComplexity.txt");
        FileWriter fw = new FileWriter(fileOut);

        String line = "";

        HashMap<String, Integer> hm = new HashMap<String, Integer>();

        while ((line = br.readLine()) != null) {
            String[] token = line.split("\t");
            String q = token[0];

            if (hm.containsKey(q)) {
                int n = hm.get(q);
                n++;
                hm.put(q, n);
            } else {
                hm.put(q, 1);
            }
        }

        for (String s : hm.keySet()) {
            System.out.println(s + "\t" + hm.get(s));
            fw.write(s + "\t" + hm.get(s) + "\n");
        }

        br.close();
        fw.close();
    }

    public static void findFrequencyPattern() throws FileNotFoundException, IOException {
        File fileIn = new File("C:\\Project\\EDU\\files\\2013\\revised\\statNormalL1.txt");
//        File fileIn = new File("C:\\Project\\EDU\\files\\2013\\revised\\testSum.txt");
        BufferedReader br = new BufferedReader(new FileReader(fileIn));

        File fileOut = new File("C:\\Project\\EDU\\files\\2013\\revised\\statNormalL1stdv.txt");
        FileWriter fw = new FileWriter(fileOut);

        ArrayList<String> cls0low = new ArrayList<String>();
        ArrayList<String> cls0hi = new ArrayList<String>();

        ArrayList<String> cls1low = new ArrayList<String>();
        ArrayList<String> cls1hi = new ArrayList<String>();

        String line = "";

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

        Double[] out = new Double[cls0low.get(0).length()];
        Double[] min = new Double[cls0low.get(0).length()];
        Double[] max = new Double[cls0low.get(0).length()];
        ArrayList<Double> std = new ArrayList<Double>();

        String outStr = "";
        String minStr = "";
        String maxStr = "";
        String stdStr = "";

        out = addVec(cls0low);
        min = findMin(cls0low);
        max = findMax(cls0low);
        std = findSD(cls0low);

        fw.write("cluster 0 low performance: \n");
        for (int i = 0; i < out.length; i++) {
            outStr = outStr + "," + out[i];
            minStr = minStr + "," + min[i];
            maxStr = maxStr + "," + max[i];
            stdStr = stdStr + "," + std.get(i);

        }
//        fw.write(minStr.substring(1) + "\n" + maxStr.substring(1) + "\n" + outStr.substring(1) + "\n\n");
        fw.write(outStr.substring(1) + "\n" + stdStr.substring(1) + "\n\n");
        System.out.println(minStr.substring(1) + "\n" + maxStr.substring(1) + "\n" + outStr.substring(1));

        outStr = "";
        minStr = "";
        maxStr = "";
        stdStr = "";

        out = addVec(cls1low);
        min = findMin(cls1low);
        max = findMax(cls1low);
        std = findSD(cls1low);
        fw.write("cluster 1 low performance: \n");
        for (int i = 0; i < out.length; i++) {
            outStr = outStr + "," + out[i];
            minStr = minStr + "," + min[i];
            maxStr = maxStr + "," + max[i];
            stdStr = stdStr + "," + std.get(i);

        }
//        fw.write(minStr.substring(1) + "\n" + maxStr.substring(1) + "\n" + outStr.substring(1) + "\n\n");
        fw.write(outStr.substring(1) + "\n" + stdStr.substring(1) + "\n\n");
        System.out.println(minStr.substring(1) + "\n" + maxStr.substring(1) + "\n" + outStr.substring(1));

        outStr = "";
        minStr = "";
        maxStr = "";
        stdStr = "";

        out = addVec(cls0hi);
        min = findMin(cls0hi);
        max = findMax(cls0hi);
        std = findSD(cls0hi);
        fw.write("cluster 0 hi performance: \n");
        for (int i = 0; i < out.length; i++) {
            outStr = outStr + "," + out[i];
            minStr = minStr + "," + min[i];
            maxStr = maxStr + "," + max[i];
            stdStr = stdStr + "," + std.get(i);

        }
//        fw.write(minStr.substring(1) + "\n" + maxStr.substring(1) + "\n" + outStr.substring(1) + "\n\n");
        fw.write(outStr.substring(1) + "\n" + stdStr.substring(1) + "\n\n");
        System.out.println(minStr.substring(1) + "\n" + maxStr.substring(1) + "\n" + outStr.substring(1));

        outStr = "";
        minStr = "";
        maxStr = "";
        stdStr = "";

        out = addVec(cls1hi);
        min = findMin(cls1hi);
        max = findMax(cls1hi);
        std = findSD(cls1hi);
        fw.write("cluster 1 hi performance: \n");
        for (int i = 0; i < out.length; i++) {
            outStr = outStr + "," + out[i];
            minStr = minStr + "," + min[i];
            maxStr = maxStr + "," + max[i];
            stdStr = stdStr + "," + std.get(i);

        }
//        fw.write(minStr.substring(1) + "\n" + maxStr.substring(1) + "\n" + outStr.substring(1));
        fw.write(outStr.substring(1) + "\n" + stdStr.substring(1) + "\n\n");
        System.out.println(minStr.substring(1) + "\n" + maxStr.substring(1) + "\n" + outStr.substring(1));

        fw.close();
        br.close();
    }

    public static Double[] addVec(ArrayList<String> arr) {
//        float[] out = new float[arr.get(0).length()];
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

//        for (String s : arr) {
//            String[] token = s.split(",");
//            for (int i = 0; i < token.length; i++) {
//                ArrayList<Double> temp = new ArrayList<Double>();
//                temp.add(Double.valueOf(token[i]));
//                vecs.add(temp);
//            }
//            for (int i = 0; i < token.length; i++) {
//                vecs.get(i).add(Double.valueOf(token[i]));
//            }
//        }
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
//            System.out.println(SD(vec));
        }

//        System.out.println("length of res: " + res.size());
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

    public static Double[] findMin(ArrayList<String> arr) {
        String temp = arr.get(0);
        String[] tk = temp.split(",");
        int sz = tk.length;
        Double[] out = new Double[sz];

        for (int i = 0; i < sz; i++) {
            out[i] = Double.valueOf(tk[i]);
        }

        for (String s : arr) {
            String[] token = s.split(",");
            for (int i = 0; i < token.length; i++) {
                Double n = Double.valueOf(token[i]);
                if (n < out[i]) {
                    out[i] = n;
                }
            }
        }
        return out;
    }

    public static Double[] findMax(ArrayList<String> arr) {
        String temp = arr.get(0);
        String[] tk = temp.split(",");
        int sz = tk.length;
        Double[] out = new Double[sz];

        for (int i = 0; i < sz; i++) {
            out[i] = Double.valueOf(tk[i]);
        }

        for (String s : arr) {
            String[] token = s.split(",");
            for (int i = 0; i < token.length; i++) {
                Double n = Double.valueOf(token[i]);
                if (n > out[i]) {
                    out[i] = n;
                }
            }
        }
        return out;
    }

    public static void findFrequencyPatternAll() throws FileNotFoundException, IOException {
//        File fileIn = new File("C:\\Project\\EDU\\files\\2013\\revised\\clean\\statNormal.txt");
//        File fileIn = new File("C:\\Project\\EDU\\files\\2013\\example\\statNormal.txt");
//        File fileIn = new File("C:\\Project\\EDU\\files\\2013\\example\\Topic\\statNormal.txt");
        File fileIn = new File("C:\\Project\\EDU\\files\\2013\\example\\session\\statNormal.txt");
        BufferedReader br = new BufferedReader(new FileReader(fileIn));

//        File fileT = new File("C:\\Project\\EDU\\files\\2013\\revised\\clean\\Top30.txt");
//        File fileT = new File("C:\\Project\\EDU\\files\\2013\\example\\Top30.txt");
//        File fileT = new File("C:\\Project\\EDU\\files\\2013\\example\\Topic\\Top30.txt");
        File fileT = new File("C:\\Project\\EDU\\files\\2013\\example\\session\\Top30.txt");
        BufferedReader brt = new BufferedReader(new FileReader(fileT));

//        File fileP = new File("C:\\Project\\EDU\\files\\2013\\revised\\clean\\PatternsTranslateFilter.txt");
//        File fileP = new File("C:\\Project\\EDU\\files\\2013\\example\\PatternTranslateFilter.txt");
//        File fileP = new File("C:\\Project\\EDU\\files\\2013\\example\\Topic\\PatternsTranslate.txt");
        File fileP = new File("C:\\Project\\EDU\\files\\2013\\example\\session\\PatternsTranslate.txt");
        BufferedReader brp = new BufferedReader(new FileReader(fileP));

//        File fileOut = new File("C:\\Project\\EDU\\files\\2013\\revised\\clean\\statNormalAllStdvPost.txt");
//        File fileOut = new File("C:\\Project\\EDU\\files\\2013\\example\\statNormalAllStdvLG.txt");
//        File fileOut = new File("C:\\Project\\EDU\\files\\2013\\example\\Topic\\statNormalAllStdvLG.txt");
        File fileOut = new File("C:\\Project\\EDU\\files\\2013\\example\\session\\statNormalAllStdvPost.txt");
        FileWriter fw = new FileWriter(fileOut);

        ArrayList<String> top30 = new ArrayList<String>();
        ArrayList<String> all = new ArrayList<String>();

        String line = "";
        while ((line = brt.readLine()) != null) {
            top30.add(line.split("\t")[0]);
        }

        while ((line = brp.readLine()) != null) {
            all.add(line.split("\t+")[0]);
        }

        ArrayList<String> cls0low = new ArrayList<String>();
        ArrayList<String> cls0hi = new ArrayList<String>();

        ArrayList<String> cls1low = new ArrayList<String>();
        ArrayList<String> cls1hi = new ArrayList<String>();

        while ((line = br.readLine()) != null) {
            String[] token = line.split("\t");
            //pre
//            String perf = token[1];
            //post
            String perf = token[2];
            //learning gain
//            String perf = token[3];
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
        System.out.println("avgStr" + avgStr);
        System.out.println("stdStr" + stdStr);

        fw.write(avgStr.substring(1) + "\n" + stdStr.substring(1) + "\n\n");
////////////////////////////////////////////////////////////////////////////
        avgStr = "";
        stdStr = "";

        avg = avgVec(cls1low);
        std = findSD(cls1low);

        fw.write("cluster 1 low performance: \n");
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

        avg = avgVec(cls0hi);
        std = findSD(cls0hi);

        fw.write("cluster 0 hi performance: \n");
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

        avg = avgVec(cls1hi);
        std = findSD(cls1hi);

        fw.write("cluster 1 hi performance: \n");
        for (int i = 0; i < avg.length; i++) {
            if (top30.contains(all.get(i))) {
                System.out.println(all.get(i));
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

    public static void findFrequencyPatternPerf(String performance) throws FileNotFoundException, IOException {
//        File fileIn = new File("C:\\Project\\EDU\\files\\2013\\revised\\clean\\statNormal.txt");
//        File fileIn = new File("C:\\Project\\EDU\\files\\2013\\example\\statNormal.txt");
//        File fileIn = new File("C:\\Project\\EDU\\files\\2013\\example\\Topic\\statNormal.txt");

        File fileIn = new File(path + "\\statNormal.txt");
        BufferedReader br = new BufferedReader(new FileReader(fileIn));

//        File fileT = new File("C:\\Project\\EDU\\files\\2013\\revised\\clean\\Top30.txt");
//        File fileT = new File("C:\\Project\\EDU\\files\\2013\\example\\Top30.txt");
//        File fileT = new File("C:\\Project\\EDU\\files\\2013\\example\\Topic\\Top30.txt");
        File fileT = new File(path + "\\Top30.txt");
        BufferedReader brt = new BufferedReader(new FileReader(fileT));

//        File fileP = new File("C:\\Project\\EDU\\files\\2013\\revised\\clean\\PatternsTranslateFilter.txt");
//        File fileP = new File("C:\\Project\\EDU\\files\\2013\\example\\PatternTranslateFilter.txt");
//        File fileP = new File("C:\\Project\\EDU\\files\\2013\\example\\Topic\\PatternsTranslate.txt");
        File fileP = new File(path + "\\PatternsTranslateFilter.txt");
        BufferedReader brp = new BufferedReader(new FileReader(fileP));

//        File fileOut = new File("C:\\Project\\EDU\\files\\2013\\revised\\clean\\statNormalAllStdvPost.txt");
//        File fileOut = new File("C:\\Project\\EDU\\files\\2013\\example\\statNormalAllStdvLG.txt");
//        File fileOut = new File("C:\\Project\\EDU\\files\\2013\\example\\Topic\\statNormalAllStdvLG.txt");
//        File fileOut = new File("C:\\Project\\EDU\\files\\2013\\example\\session\\statNormalAllStdvPost.txt");
//        FileWriter fw = new FileWriter(fileOut);
        File fileOutC0l = new File(path + "\\C0low" + performance + ".txt");
        FileWriter fwC0l = new FileWriter(fileOutC0l);

        File fileOutC0h = new File(path + "\\C0hi" + performance + ".txt");
        FileWriter fwC0h = new FileWriter(fileOutC0h);

        File fileOutC1l = new File(path + "\\C1low" + performance + ".txt");
        FileWriter fwC1l = new FileWriter(fileOutC1l);

        File fileOutC1h = new File(path + "\\C1hi" + performance + ".txt");
        FileWriter fwC1h = new FileWriter(fileOutC1h);

        ArrayList<String> top30 = new ArrayList<String>();
        ArrayList<String> all = new ArrayList<String>();

        String line = "";
        while ((line = brt.readLine()) != null) {
            top30.add(line.split("\t")[0]);
        }

        while ((line = brp.readLine()) != null) {
            all.add(line.split("\t+")[0]);
        }

        ArrayList<String> cls0low = new ArrayList<String>();
        ArrayList<String> cls0hi = new ArrayList<String>();

        ArrayList<String> cls1low = new ArrayList<String>();
        ArrayList<String> cls1hi = new ArrayList<String>();

        while ((line = br.readLine()) != null) {
            String[] token = line.split("\t");
            int index = 0;

            if (performance.equals("Pre")) {
                index = 1;
            } else if (performance.equals("Post")) {
                index = 2;
            } else if (performance.equals("LG")) {
                index = 3;
            } else {
                System.out.println("Enter Pre/Post/LG");
                break;
            }
            String perf = token[index];
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

//        fwC0l.write("cluster 0 low performance: \n");
        for (int i = 0; i < avg.length; i++) {
            if (top30.contains(all.get(i))) {
                System.out.println(all.get(i));
                avgStr = avgStr + "," + avg[i];
                stdStr = stdStr + "," + std.get(i);
            }
        }
        System.out.println("avgStr" + avgStr);
        System.out.println("stdStr" + stdStr);

        fwC0l.write(avgStr.substring(1) + "\n" + stdStr.substring(1) + "\n\n");
////////////////////////////////////////////////////////////////////////////
        avgStr = "";
        stdStr = "";

        avg = avgVec(cls1low);
        std = findSD(cls1low);

//        fwC1l.write("cluster 1 low performance: \n");
        for (int i = 0; i < avg.length; i++) {
            if (top30.contains(all.get(i))) {
                System.out.println(all.get(i));
                avgStr = avgStr + "," + avg[i];
                stdStr = stdStr + "," + std.get(i);
            }
        }

        fwC1l.write(avgStr.substring(1) + "\n" + stdStr.substring(1) + "\n\n");
////////////////////////////////////////////////////////////////////////////
        avgStr = "";
        stdStr = "";

        avg = avgVec(cls0hi);
        std = findSD(cls0hi);

//        fw.write("cluster 0 hi performance: \n");
        for (int i = 0; i < avg.length; i++) {
            if (top30.contains(all.get(i))) {
                System.out.println(all.get(i));
                avgStr = avgStr + "," + avg[i];
                stdStr = stdStr + "," + std.get(i);
            }
        }

        fwC0h.write(avgStr.substring(1) + "\n" + stdStr.substring(1) + "\n\n");
////////////////////////////////////////////////////////////////////////////
        avgStr = "";
        stdStr = "";

        avg = avgVec(cls1hi);
        std = findSD(cls1hi);

//        fwC1h.write("cluster 1 hi performance: \n");
        for (int i = 0; i < avg.length; i++) {
            if (top30.contains(all.get(i))) {
                System.out.println(all.get(i));
                avgStr = avgStr + "," + avg[i];
                stdStr = stdStr + "," + std.get(i);
            }
        }

        fwC1h.write(avgStr.substring(1) + "\n" + stdStr.substring(1) + "\n\n");

        fwC0l.close();
        fwC0h.close();
        fwC1l.close();
        fwC1h.close();
        br.close();
        brt.close();
        brp.close();
    }

    public static void findFrequencyPatternPerf3(String performance) throws FileNotFoundException, IOException {
//        File fileIn = new File("C:\\Project\\EDU\\files\\2013\\revised\\clean\\statNormal.txt");
//        File fileIn = new File("C:\\Project\\EDU\\files\\2013\\example\\statNormal.txt");
//        File fileIn = new File("C:\\Project\\EDU\\files\\2013\\example\\Topic\\statNormal.txt");

        File fileIn = new File(path + "\\statNormal.txt");
        BufferedReader br = new BufferedReader(new FileReader(fileIn));

//        File fileT = new File("C:\\Project\\EDU\\files\\2013\\revised\\clean\\Top30.txt");
//        File fileT = new File("C:\\Project\\EDU\\files\\2013\\example\\Top30.txt");
//        File fileT = new File("C:\\Project\\EDU\\files\\2013\\example\\Topic\\Top30.txt");
        File fileT = new File(path + "\\Top30.txt");
        BufferedReader brt = new BufferedReader(new FileReader(fileT));

//        File fileP = new File("C:\\Project\\EDU\\files\\2013\\revised\\clean\\PatternsTranslateFilter.txt");
//        File fileP = new File("C:\\Project\\EDU\\files\\2013\\example\\PatternTranslateFilter.txt");
//        File fileP = new File("C:\\Project\\EDU\\files\\2013\\example\\Topic\\PatternsTranslate.txt");
        File fileP = new File(path + "\\PatternsTranslateFilter.txt");
        BufferedReader brp = new BufferedReader(new FileReader(fileP));

//        File fileOut = new File("C:\\Project\\EDU\\files\\2013\\revised\\clean\\statNormalAllStdvPost.txt");
//        File fileOut = new File("C:\\Project\\EDU\\files\\2013\\example\\statNormalAllStdvLG.txt");
//        File fileOut = new File("C:\\Project\\EDU\\files\\2013\\example\\Topic\\statNormalAllStdvLG.txt");
//        File fileOut = new File("C:\\Project\\EDU\\files\\2013\\example\\session\\statNormalAllStdvPost.txt");
//        FileWriter fw = new FileWriter(fileOut);
        File fileOutC0l = new File(path + "\\C0low" + performance + ".txt");
        FileWriter fwC0l = new FileWriter(fileOutC0l);

        File fileOutC0h = new File(path + "\\C0hi" + performance + ".txt");
        FileWriter fwC0h = new FileWriter(fileOutC0h);

        File fileOutC1l = new File(path + "\\C1low" + performance + ".txt");
        FileWriter fwC1l = new FileWriter(fileOutC1l);

        File fileOutC1h = new File(path + "\\C1hi" + performance + ".txt");
        FileWriter fwC1h = new FileWriter(fileOutC1h);

        File fileOutC2l = new File(path + "\\C2low" + performance + ".txt");
        FileWriter fwC2l = new FileWriter(fileOutC2l);

        File fileOutC2h = new File(path + "\\C2hi" + performance + ".txt");
        FileWriter fwC2h = new FileWriter(fileOutC2h);

        ArrayList<String> top30 = new ArrayList<String>();
        ArrayList<String> all = new ArrayList<String>();

        String line = "";
        while ((line = brt.readLine()) != null) {
            top30.add(line.split("\t")[0]);
        }

        while ((line = brp.readLine()) != null) {
            all.add(line.split("\t+")[0]);
        }

        ArrayList<String> cls0low = new ArrayList<String>();
        ArrayList<String> cls0hi = new ArrayList<String>();

        ArrayList<String> cls1low = new ArrayList<String>();
        ArrayList<String> cls1hi = new ArrayList<String>();

        ArrayList<String> cls2low = new ArrayList<String>();
        ArrayList<String> cls2hi = new ArrayList<String>();

        while ((line = br.readLine()) != null) {
            String[] token = line.split("\t");
            int index = 0;

            if (performance.equals("Pre")) {
                index = 1;
            } else if (performance.equals("Post")) {
                index = 2;
            } else if (performance.equals("LG")) {
                index = 3;
            } else {
                System.out.println("Enter Pre/Post/LG");
                break;
            }
            String perf = token[index];
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
            } else if (cluster.equals("2")) {
                if (perf.equals("l")) {
                    cls2low.add(vec);
                } else if (perf.equals("h")) {
                    cls2hi.add(vec);
                }
            }
        }

        Double[] avg = new Double[cls0low.get(0).length()];
        ArrayList<Double> std = new ArrayList<Double>();

        String avgStr = "";
        String stdStr = "";

        avg = avgVec(cls0low);
        std = findSD(cls0low);

//        fwC0l.write("cluster 0 low performance: \n");
        for (int i = 0; i < avg.length; i++) {
            if (top30.contains(all.get(i))) {
                System.out.println(all.get(i));
                avgStr = avgStr + "," + avg[i];
                stdStr = stdStr + "," + std.get(i);
            }
        }
        System.out.println("avgStr" + avgStr);
        System.out.println("stdStr" + stdStr);

        fwC0l.write(avgStr.substring(1) + "\n" + stdStr.substring(1) + "\n\n");
////////////////////////////////////////////////////////////////////////////
        avgStr = "";
        stdStr = "";

        avg = avgVec(cls1low);
        std = findSD(cls1low);

//        fwC1l.write("cluster 1 low performance: \n");
        for (int i = 0; i < avg.length; i++) {
            if (top30.contains(all.get(i))) {
                System.out.println(all.get(i));
                avgStr = avgStr + "," + avg[i];
                stdStr = stdStr + "," + std.get(i);
            }
        }

        fwC1l.write(avgStr.substring(1) + "\n" + stdStr.substring(1) + "\n\n");
////////////////////////////////////////////////////////////////////////////        
        avgStr = "";
        stdStr = "";

        avg = avgVec(cls2low);
        std = findSD(cls2low);

//        fwC1l.write("cluster 1 low performance: \n");
        for (int i = 0; i < avg.length; i++) {
            if (top30.contains(all.get(i))) {
                System.out.println(all.get(i));
                avgStr = avgStr + "," + avg[i];
                stdStr = stdStr + "," + std.get(i);
            }
        }

        fwC2l.write(avgStr.substring(1) + "\n" + stdStr.substring(1) + "\n\n");
////////////////////////////////////////////////////////////////////////////
        avgStr = "";
        stdStr = "";

        avg = avgVec(cls0hi);
        std = findSD(cls0hi);

//        fw.write("cluster 0 hi performance: \n");
        for (int i = 0; i < avg.length; i++) {
            if (top30.contains(all.get(i))) {
                System.out.println(all.get(i));
                avgStr = avgStr + "," + avg[i];
                stdStr = stdStr + "," + std.get(i);
            }
        }

        fwC0h.write(avgStr.substring(1) + "\n" + stdStr.substring(1) + "\n\n");
////////////////////////////////////////////////////////////////////////////
        avgStr = "";
        stdStr = "";

        avg = avgVec(cls1hi);
        std = findSD(cls1hi);

//        fwC1h.write("cluster 1 hi performance: \n");
        for (int i = 0; i < avg.length; i++) {
            if (top30.contains(all.get(i))) {
                System.out.println(all.get(i));
                avgStr = avgStr + "," + avg[i];
                stdStr = stdStr + "," + std.get(i);
            }
        }

        fwC1h.write(avgStr.substring(1) + "\n" + stdStr.substring(1) + "\n\n");
////////////////////////////////////////////////////////////////////////////
        avgStr = "";
        stdStr = "";

        avg = avgVec(cls2hi);
        std = findSD(cls2hi);

//        fwC1h.write("cluster 1 hi performance: \n");
        for (int i = 0; i < avg.length; i++) {
            if (top30.contains(all.get(i))) {
                System.out.println(all.get(i));
                avgStr = avgStr + "," + avg[i];
                stdStr = stdStr + "," + std.get(i);
            }
        }

        fwC2h.write(avgStr.substring(1) + "\n" + stdStr.substring(1) + "\n\n");

        fwC0l.close();
        fwC0h.close();
        fwC1l.close();
        fwC1h.close();
        fwC2l.close();
        fwC2h.close();
        br.close();
        brt.close();
        brp.close();
    }

    public static void findFrequencyPatternPerfAll(String performance) throws FileNotFoundException, IOException {

        File fileIn = new File(path + "\\statNormal.txt");
        BufferedReader br = new BufferedReader(new FileReader(fileIn));

        File fileP = new File(path + "\\PatternsTranslateFilter.txt");
        BufferedReader brp = new BufferedReader(new FileReader(fileP));

        File fileOutC0l = new File(path + "\\C0lowAll" + performance + ".txt");
        FileWriter fwC0l = new FileWriter(fileOutC0l);

        File fileOutC0h = new File(path + "\\C0hiAll" + performance + ".txt");
        FileWriter fwC0h = new FileWriter(fileOutC0h);

        File fileOutC1l = new File(path + "\\C1lowAll" + performance + ".txt");
        FileWriter fwC1l = new FileWriter(fileOutC1l);

        File fileOutC1h = new File(path + "\\C1hiAll" + performance + ".txt");
        FileWriter fwC1h = new FileWriter(fileOutC1h);

        File fileOutC2l = new File(path + "\\C2lowAll" + performance + ".txt");
        FileWriter fwC2l = new FileWriter(fileOutC2l);

        File fileOutC2h = new File(path + "\\C2hiAll" + performance + ".txt");
        FileWriter fwC2h = new FileWriter(fileOutC2h);

        File fileOutOrder = new File(path + "\\OrderAll" + performance + ".txt");
        FileWriter fwOrd = new FileWriter(fileOutOrder);

        ArrayList<String> all = new ArrayList<String>();

        String line = "";

        String out = "";
        while ((line = brp.readLine()) != null) {
            String ptrn = line.split("\t+")[0];
            all.add(ptrn);
            out = out + "," + ptrn;

        }
        fwOrd.write(out.substring(1));

        ArrayList<String> cls0low = new ArrayList<String>();
        ArrayList<String> cls0hi = new ArrayList<String>();

        ArrayList<String> cls1low = new ArrayList<String>();
        ArrayList<String> cls1hi = new ArrayList<String>();

        ArrayList<String> cls2low = new ArrayList<String>();
        ArrayList<String> cls2hi = new ArrayList<String>();

        while ((line = br.readLine()) != null) {
            String[] token = line.split("\t");
            int index = 0;

            if (performance.equals("Pre")) {
                index = 1;
            } else if (performance.equals("Post")) {
                index = 2;
            } else if (performance.equals("LG")) {
                index = 3;
            } else {
                System.out.println("Enter Pre/Post/LG");
                break;
            }
            String perf = token[index];
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
            } else if (cluster.equals("2")) {
                if (perf.equals("l")) {
                    cls2low.add(vec);
                } else if (perf.equals("h")) {
                    cls2hi.add(vec);
                }
            }
        }

        Double[] avg = new Double[cls0low.get(0).length()];
        ArrayList<Double> std = new ArrayList<Double>();

        String avgStr = "";
        String stdStr = "";

        avg = avgVec(cls0low);
        std = findSD(cls0low);

        for (int i = 0; i < avg.length; i++) {
            System.out.println(all.get(i));
            avgStr = avgStr + "," + avg[i];
            stdStr = stdStr + "," + std.get(i);
        }
        System.out.println("avgStr" + avgStr);
        System.out.println("stdStr" + stdStr);

        fwC0l.write(avgStr.substring(1) + "\n" + stdStr.substring(1));
////////////////////////////////////////////////////////////////////////////
        avgStr = "";
        stdStr = "";

        avg = avgVec(cls1low);
        std = findSD(cls1low);

        for (int i = 0; i < avg.length; i++) {
            System.out.println(all.get(i));
            avgStr = avgStr + "," + avg[i];
            stdStr = stdStr + "," + std.get(i);
        }

        fwC1l.write(avgStr.substring(1) + "\n" + stdStr.substring(1));
////////////////////////////////////////////////////////////////////////////        
        avgStr = "";
        stdStr = "";
        /*
        avg = avgVec(cls2low);
        std = findSD(cls2low);

        for (int i = 0; i < avg.length; i++) {
                System.out.println(all.get(i));
                avgStr = avgStr + "," + avg[i];
                stdStr = stdStr + "," + std.get(i);
        }

        fwC2l.write(avgStr.substring(1) + "\n" + stdStr.substring(1));*/
////////////////////////////////////////////////////////////////////////////
        avgStr = "";
        stdStr = "";

        avg = avgVec(cls0hi);
        std = findSD(cls0hi);

        for (int i = 0; i < avg.length; i++) {
            System.out.println(all.get(i));
            avgStr = avgStr + "," + avg[i];
            stdStr = stdStr + "," + std.get(i);
        }

        fwC0h.write(avgStr.substring(1) + "\n" + stdStr.substring(1));
////////////////////////////////////////////////////////////////////////////
        avgStr = "";
        stdStr = "";

        avg = avgVec(cls1hi);
        std = findSD(cls1hi);

        for (int i = 0; i < avg.length; i++) {
            System.out.println(all.get(i));
            avgStr = avgStr + "," + avg[i];
            stdStr = stdStr + "," + std.get(i);
        }

        fwC1h.write(avgStr.substring(1) + "\n" + stdStr.substring(1));
////////////////////////////////////////////////////////////////////////////
        avgStr = "";
        stdStr = "";
        /*
        avg = avgVec(cls2hi);
        std = findSD(cls2hi);

        for (int i = 0; i < avg.length; i++) {
                System.out.println(all.get(i));
                avgStr = avgStr + "," + avg[i];
                stdStr = stdStr + "," + std.get(i);
        }

        fwC2h.write(avgStr.substring(1) + "\n" + stdStr.substring(1));
         */
        fwC0l.close();
        fwC0h.close();
        fwC1l.close();
        fwC1h.close();
        fwC2l.close();
        fwC2h.close();
        fwOrd.close();
        br.close();
        brp.close();
    }

    public static void findFrequencyPatternCluster() throws FileNotFoundException, IOException {
//        File fileIn = new File("C:\\Project\\EDU\\files\\2013\\revised\\clean\\statNormal.txt");
//        File fileIn = new File("C:\\Project\\EDU\\files\\2013\\example\\statNormal.txt");
//        File fileIn = new File("C:\\Project\\EDU\\files\\2013\\example\\Topic\\3\\statNormal.txt");
        File fileIn = new File(path + "\\statNormal.txt");
        BufferedReader br = new BufferedReader(new FileReader(fileIn));

//        File fileT = new File("C:\\Project\\EDU\\files\\2013\\revised\\clean\\Top30.txt");
//        File fileT = new File("C:\\Project\\EDU\\files\\2013\\example\\Top30.txt");
//        File fileT = new File("C:\\Project\\EDU\\files\\2013\\example\\Topic\\3\\Top40.txt");
        File fileT = new File(path + "\\Top30.txt");
        BufferedReader brt = new BufferedReader(new FileReader(fileT));

//        File fileP = new File("C:\\Project\\EDU\\files\\2013\\revised\\clean\\PatternsTranslateFilter.txt");
//        File fileP = new File("C:\\Project\\EDU\\files\\2013\\example\\PatternTranslateFilter.txt");
//        File fileP = new File("C:\\Project\\EDU\\files\\2013\\example\\Topic\\3\\PatternsTranslate.txt");
        File fileP = new File(path + "\\PatternsTranslateFilter.txt");
        BufferedReader brp = new BufferedReader(new FileReader(fileP));

//        File fileOut = new File("C:\\Project\\EDU\\files\\2013\\revised\\clean\\statNormalClusterStdv.txt");
//        File fileOut = new File("C:\\Project\\EDU\\files\\2013\\example\\statNormal1ClusterStdv.txt");
//        File fileOut = new File("C:\\Project\\EDU\\files\\2013\\example\\Topic\\3\\statNormal1ClusterStdv40.txt");
        File fileOut = new File(path + "\\statNormal1ClusterStdv.txt");
        FileWriter fw = new FileWriter(fileOut);

//        File fileOrd = new File("C:\\Project\\EDU\\files\\2013\\revised\\clean\\Order.txt");
//        File fileOrd = new File("C:\\Project\\EDU\\files\\2013\\example\\Order.txt");
//        File fileOrd = new File("C:\\Project\\EDU\\files\\2013\\example\\Topic\\3\\Order40.txt");
        File fileOrd = new File(path + "\\Order.txt");
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

        System.out.println("All: " + all.size());

        ArrayList<String> cls0 = new ArrayList<String>();
        ArrayList<String> cls1 = new ArrayList<String>();
        ArrayList<String> cls2 = new ArrayList<String>();

        while ((line = br.readLine()) != null) {
            String[] token = line.split("\t");
            String perf = token[3];
            String cluster = token[4];
            String vec = token[5];

            if (cluster.equals("0")) {
                cls0.add(vec);
            } else if (cluster.equals("1")) {
                cls1.add(vec);
            } else if (cluster.equals("2")) {
                cls2.add(vec);
            }
        }

        String temp = cls0.get(0);
        String[] tk = temp.split(",");
        int sz = tk.length;

        Double[] avg0 = new Double[sz];
        ArrayList<Double> std0 = new ArrayList<Double>();

        String avgStr = "";
        String stdStr = "";

        avg0 = avgVec(cls0);
        std0 = findSD(cls0);

        String order = "";

        fw.write("cluster 0: \n");
        for (int i = 0; i < avg0.length; i++) {
            if (top30.contains(all.get(i))) {
                System.out.println("E " + all.get(i));
//                System.out.println(all.get(i));
                order = order + "," + all.get(i);
                avgStr = avgStr + "," + avg0[i];
                stdStr = stdStr + "," + std0.get(i);
            } else {
                System.out.println("N " + all.get(i));
            }
        }

        fwOrd.write(order.substring(1));

        System.out.println("avgStr" + avgStr);
        System.out.println("stdStr" + stdStr);

        fw.write(avgStr.substring(1) + "\n" + stdStr.substring(1) + "\n\n");
////////////////////////////////////////////////////////////////////////////
        System.out.println("======================");
        avgStr = "";
        stdStr = "";

        temp = cls1.get(0);
        tk = temp.split(",");
        sz = tk.length;

        Double[] avg1 = new Double[sz];
        ArrayList<Double> std1 = new ArrayList<Double>();

        avg1 = avgVec(cls1);
        std1 = findSD(cls1);

        fw.write("cluster 1: \n");
        for (int i = 0; i < avg1.length; i++) {
            if (top30.contains(all.get(i))) {
                System.out.println(all.get(i));
                avgStr = avgStr + "," + avg1[i];
                stdStr = stdStr + "," + std1.get(i);
            }
        }

        fw.write(avgStr.substring(1) + "\n" + stdStr.substring(1) + "\n\n");
////////////////////////////////////////////////////////////////////////////
        System.out.println("======================");
        avgStr = "";
        stdStr = "";

        temp = cls2.get(0);
        tk = temp.split(",");
        sz = tk.length;

        Double[] avg2 = new Double[sz];
        ArrayList<Double> std2 = new ArrayList<Double>();

        avg2 = avgVec(cls2);
        std2 = findSD(cls2);

        fw.write("cluster 2: \n");
        for (int i = 0; i < avg2.length; i++) {
            if (top30.contains(all.get(i))) {
                System.out.println(all.get(i));
                avgStr = avgStr + "," + avg2[i];
                stdStr = stdStr + "," + std2.get(i);
            }
        }

        fw.write(avgStr.substring(1) + "\n" + stdStr.substring(1));
////////////////////////////////////////////////////////////////////////////

        fw.close();
        fwOrd.close();
        br.close();
        brt.close();
        brp.close();

    }

    public static void findFrequencyPatternClusterAll() throws FileNotFoundException, IOException {
        File fileIn = new File(path + "\\statNormal.txt");
        BufferedReader br = new BufferedReader(new FileReader(fileIn));

//        File fileP = new File(path + "\\PatternsTranslateFilter.txt");
        File fileP = new File(path + "\\PatternsTranslateFilter.txt");
        BufferedReader brp = new BufferedReader(new FileReader(fileP));

        File fileOut = new File(path + "\\statNormal1ClusterStdvA.txt");
        FileWriter fw = new FileWriter(fileOut);

        File fileOrd = new File(path + "\\OrderA.txt");
        FileWriter fwOrd = new FileWriter(fileOrd);

        ArrayList<String> all = new ArrayList<String>();

        String line = "";

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
            System.out.println(all.get(i));
            order = order + "," + all.get(i);
            avgStr = avgStr + "," + avg[i];
            stdStr = stdStr + "," + std.get(i);
        }

        fwOrd.write(order.substring(1));

        System.out.println("avgStr" + avgStr);
        System.out.println("stdStr" + stdStr);

        fw.write(avgStr.substring(1) + "\n" + stdStr.substring(1) + "\n\n");
////////////////////////////////////////////////////////////////////////////
        System.out.println("======================");
        avgStr = "";
        stdStr = "";

        avg = avgVec(cls1);
        std = findSD(cls1);

        fw.write("cluster 1: \n");
        for (int i = 0; i < avg.length; i++) {
            System.out.println(all.get(i));
            avgStr = avgStr + "," + avg[i];
            stdStr = stdStr + "," + std.get(i);
        }

        fw.write(avgStr.substring(1) + "\n" + stdStr.substring(1));
////////////////////////////////////////////////////////////////////////////

        fw.close();
        fwOrd.close();
        br.close();
        brp.close();

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

    public static void sortDiff() throws FileNotFoundException, IOException {
//        File fileIn = new File("C:\\Project\\EDU\\files\\2013\\example\\Topic\\3\\statNormal1ClusterStdv20new.txt");
        File fileIn = new File(path + "\\statNormal1ClusterStdvA.txt");
        BufferedReader br = new BufferedReader(new FileReader(fileIn));

//        File fileInO = new File("C:\\Project\\EDU\\files\\2013\\example\\Topic\\3\\Order20.txt");
        File fileInO = new File(path + "\\OrderA.txt");
        BufferedReader bro = new BufferedReader(new FileReader(fileInO));

//        File fileOutO = new File("C:\\Project\\EDU\\files\\2013\\example\\Topic\\3\\Order20New.txt");
        File fileOutO = new File(path + "\\OrderNewA.txt");
        FileWriter fwo = new FileWriter(fileOutO);

//        File fileOut1 = new File("C:\\Project\\EDU\\files\\2013\\example\\Topic\\3\\C0.txt");
        File fileOut1 = new File(path + "\\C0A.txt");
        FileWriter fw1 = new FileWriter(fileOut1);

//        File fileOut2 = new File("C:\\Project\\EDU\\files\\2013\\example\\Topic\\3\\C1.txt");
        File fileOut2 = new File(path + "\\C1A.txt");
        FileWriter fw2 = new FileWriter(fileOut2);

        HashMap<Double, item> hm = new HashMap<Double, item>();
        HashMap<String, Double> hmTemp = new HashMap<String, Double>();

        ArrayList<String> pattern = new ArrayList<String>();
        ArrayList<Double> avg1 = new ArrayList<Double>();
        ArrayList<Double> stdv1 = new ArrayList<Double>();
        ArrayList<Double> avg2 = new ArrayList<Double>();
        ArrayList<Double> stdv2 = new ArrayList<Double>();

        String line = "";
        line = bro.readLine();

        String[] token = line.split(",");
        for (int i = 0; i < token.length; i++) {
            pattern.add(token[i]);
        }

        br.readLine();
        line = br.readLine();
        token = line.split(",");
        for (int i = 0; i < token.length; i++) {
            avg1.add(Double.valueOf(token[i]));
        }

        line = br.readLine();
        token = line.split(",");
        for (int i = 0; i < token.length; i++) {
            stdv1.add(Double.valueOf(token[i]));
        }
        br.readLine();
        br.readLine();
        line = br.readLine();
        token = line.split(",");
        for (int i = 0; i < token.length; i++) {
            avg2.add(Double.valueOf(token[i]));
        }
        line = br.readLine();
        token = line.split(",");
        for (int i = 0; i < token.length; i++) {
            stdv2.add(Double.valueOf(token[i]));
        }

        for (int i = 0; i < pattern.size(); i++) {
            item obj = new item();
            obj.pattern = pattern.get(i);
            obj.avg1 = avg1.get(i);
            obj.stdv1 = stdv1.get(i);
            obj.avg2 = avg2.get(i);
            obj.stdv2 = stdv2.get(i);

            Double dif = avg1.get(i) - avg2.get(i);

            hm.put(dif, obj);
            hmTemp.put(pattern.get(i), dif);
        }

        LinkedHashMap<String, Double> lhm = Util.sortHashMapByValues(hmTemp);

        String out1 = "";
        String out2 = "";
        String out3 = "";
        String out4 = "";
        String order = "";
        for (String s : lhm.keySet()) {
            Double diff = lhm.get(s);
            item j = hm.get(diff);
            order = order + "," + j.pattern;
            out1 = out1 + "," + j.avg1;
            out2 = out2 + "," + j.stdv1;
            out3 = out3 + "," + j.avg2;
            out4 = out4 + "," + j.stdv2;
        }

        fwo.write(order.substring(1));
        fw1.write(out1.substring(1) + "\n" + out2.substring(1));
        fw2.write(out3.substring(1) + "\n" + out4.substring(1));

        br.close();
        bro.close();
        fw1.close();
        fw2.close();
        fwo.close();
    }

    public static void sortDiff3() throws FileNotFoundException, IOException {
//        File fileIn = new File("C:\\Project\\EDU\\files\\2013\\example\\Topic\\3\\statNormal1ClusterStdv20new.txt");
        File fileIn = new File(path + "\\statNormal1ClusterStdv.txt");
        BufferedReader br = new BufferedReader(new FileReader(fileIn));

//        File fileInO = new File("C:\\Project\\EDU\\files\\2013\\example\\Topic\\3\\Order20.txt");
        File fileInO = new File(path + "\\Order.txt");
        BufferedReader bro = new BufferedReader(new FileReader(fileInO));

//        File fileOutO = new File("C:\\Project\\EDU\\files\\2013\\example\\Topic\\3\\Order20New.txt");
        File fileOutO = new File(path + "\\OrderNew.txt");
        FileWriter fwo = new FileWriter(fileOutO);

//        File fileOut1 = new File("C:\\Project\\EDU\\files\\2013\\example\\Topic\\3\\C0.txt");
        File fileOut1 = new File(path + "\\C0.txt");
        FileWriter fw1 = new FileWriter(fileOut1);

//        File fileOut2 = new File("C:\\Project\\EDU\\files\\2013\\example\\Topic\\3\\C1.txt");
        File fileOut2 = new File(path + "\\C1.txt");
        FileWriter fw2 = new FileWriter(fileOut2);

        File fileOut3 = new File(path + "\\C2.txt");
        FileWriter fw3 = new FileWriter(fileOut3);

        HashMap<Double, item> hm = new HashMap<Double, item>();
        HashMap<String, Double> hmTemp = new HashMap<String, Double>();

        ArrayList<String> pattern = new ArrayList<String>();
        ArrayList<Double> avg1 = new ArrayList<Double>();
        ArrayList<Double> stdv1 = new ArrayList<Double>();
        ArrayList<Double> avg2 = new ArrayList<Double>();
        ArrayList<Double> stdv2 = new ArrayList<Double>();
        ArrayList<Double> avg3 = new ArrayList<Double>();
        ArrayList<Double> stdv3 = new ArrayList<Double>();

        String line = "";
        line = bro.readLine();

        String[] token = line.split(",");
        for (int i = 0; i < token.length; i++) {
            pattern.add(token[i]);
        }

        br.readLine();
        line = br.readLine();
        token = line.split(",");
        for (int i = 0; i < token.length; i++) {
            avg1.add(Double.valueOf(token[i]));
        }

        line = br.readLine();
        token = line.split(",");
        for (int i = 0; i < token.length; i++) {
            stdv1.add(Double.valueOf(token[i]));
        }
        br.readLine();
        br.readLine();
        line = br.readLine();
        token = line.split(",");
        for (int i = 0; i < token.length; i++) {
            avg2.add(Double.valueOf(token[i]));
        }
        line = br.readLine();
        token = line.split(",");
        for (int i = 0; i < token.length; i++) {
            stdv2.add(Double.valueOf(token[i]));
        }
        br.readLine();
        br.readLine();
        line = br.readLine();
        token = line.split(",");
        for (int i = 0; i < token.length; i++) {
            avg3.add(Double.valueOf(token[i]));
        }
        line = br.readLine();
        token = line.split(",");
        for (int i = 0; i < token.length; i++) {
            stdv3.add(Double.valueOf(token[i]));
        }

        for (int i = 0; i < pattern.size(); i++) {
            item obj = new item();
            obj.pattern = pattern.get(i);
            obj.avg1 = avg1.get(i);
            obj.stdv1 = stdv1.get(i);
            obj.avg2 = avg2.get(i);
            obj.stdv2 = stdv2.get(i);
            obj.avg3 = avg3.get(i);
            obj.stdv3 = stdv3.get(i);

//            Double dif = avg1.get(i) - avg2.get(i);
//            Double d12 = Math.abs(avg1.get(i) - avg2.get(i));
//            Double d13 = Math.abs(avg1.get(i) - avg3.get(i));
//            Double d23 = Math.abs(avg2.get(i) - avg3.get(i));
//            Double d12 = (avg1.get(i) - avg2.get(i));
//            Double d13 = (avg1.get(i) - avg3.get(i));
//            Double d23 = (avg2.get(i) - avg3.get(i));
            Double min = Math.min(avg1.get(i), Math.min(avg2.get(i), avg3.get(i)));
            Double max = Math.max(avg1.get(i), Math.max(avg2.get(i), avg3.get(i)));

//            Double dif = Math.max(d12, Math.max(d13, d23));
            Double dif = max - min;

            hm.put(dif, obj);
            hmTemp.put(pattern.get(i), dif);
        }

        LinkedHashMap<String, Double> lhm = Util.sortHashMapByValues(hmTemp);

        String out1 = "";
        String out2 = "";
        String out3 = "";
        String out4 = "";
        String out5 = "";
        String out6 = "";
        String order = "";
        for (String s : lhm.keySet()) {
            Double diff = lhm.get(s);
            item j = hm.get(diff);
            order = order + "," + j.pattern;
            out1 = out1 + "," + j.avg1;
            out2 = out2 + "," + j.stdv1;
            out3 = out3 + "," + j.avg2;
            out4 = out4 + "," + j.stdv2;
            out5 = out5 + "," + j.avg3;
            out6 = out6 + "," + j.stdv3;
        }

        fwo.write(order.substring(1));
        fw1.write(out1.substring(1) + "\n" + out2.substring(1));
        fw2.write(out3.substring(1) + "\n" + out4.substring(1));
        fw3.write(out5.substring(1) + "\n" + out6.substring(1));

        br.close();
        bro.close();
        fw1.close();
        fw2.close();
        fw3.close();
        fwo.close();
    }

    public static void sortDiffAll() throws FileNotFoundException, IOException {
        File fileIn = new File(path + "\\statNormal1ClusterStdv.txt");
        BufferedReader br = new BufferedReader(new FileReader(fileIn));

        File fileInO = new File(path + "\\Order.txt");
        BufferedReader bro = new BufferedReader(new FileReader(fileInO));

        File fileOutO = new File(path + "\\OrderNew.txt");
        FileWriter fwo = new FileWriter(fileOutO);

        File fileOut1 = new File(path + "\\C0.txt");
        FileWriter fw1 = new FileWriter(fileOut1);

        File fileOut2 = new File(path + "\\C1.txt");
        FileWriter fw2 = new FileWriter(fileOut2);

        File fileOut3 = new File(path + "\\C2.txt");
        FileWriter fw3 = new FileWriter(fileOut3);

        HashMap<Double, item> hm = new HashMap<Double, item>();
        HashMap<String, Double> hmTemp = new HashMap<String, Double>();

        ArrayList<String> pattern = new ArrayList<String>();
        ArrayList<Double> avg1 = new ArrayList<Double>();
        ArrayList<Double> stdv1 = new ArrayList<Double>();
        ArrayList<Double> avg2 = new ArrayList<Double>();
        ArrayList<Double> stdv2 = new ArrayList<Double>();
        ArrayList<Double> avg3 = new ArrayList<Double>();
        ArrayList<Double> stdv3 = new ArrayList<Double>();

        String line = "";
        line = bro.readLine();

        String[] token = line.split(",");
        for (int i = 0; i < token.length; i++) {
            pattern.add(token[i]);
        }

        br.readLine();
        line = br.readLine();
        token = line.split(",");
        for (int i = 0; i < token.length; i++) {
            avg1.add(Double.valueOf(token[i]));
        }

        line = br.readLine();
        token = line.split(",");
        for (int i = 0; i < token.length; i++) {
            stdv1.add(Double.valueOf(token[i]));
        }
        br.readLine();
        br.readLine();
        line = br.readLine();
        token = line.split(",");
        for (int i = 0; i < token.length; i++) {
            avg2.add(Double.valueOf(token[i]));
        }
        line = br.readLine();
        token = line.split(",");
        for (int i = 0; i < token.length; i++) {
            stdv2.add(Double.valueOf(token[i]));
        }
        br.readLine();
        br.readLine();
        line = br.readLine();
        token = line.split(",");
        for (int i = 0; i < token.length; i++) {
            avg3.add(Double.valueOf(token[i]));
        }
        line = br.readLine();
        token = line.split(",");
        for (int i = 0; i < token.length; i++) {
            stdv3.add(Double.valueOf(token[i]));
        }

        for (int i = 0; i < pattern.size(); i++) {
            item obj = new item();
            obj.pattern = pattern.get(i);
            obj.avg1 = avg1.get(i);
            obj.stdv1 = stdv1.get(i);
            obj.avg2 = avg2.get(i);
            obj.stdv2 = stdv2.get(i);
            obj.avg3 = avg3.get(i);
            obj.stdv3 = stdv3.get(i);

            Double min = Math.min(avg1.get(i), Math.min(avg2.get(i), avg3.get(i)));
            Double max = Math.max(avg1.get(i), Math.max(avg2.get(i), avg3.get(i)));

            Double dif = max - min;

            hm.put(dif, obj);
            hmTemp.put(pattern.get(i), dif);
        }

        LinkedHashMap<String, Double> lhm = Util.sortHashMapByValues(hmTemp);

        String out1 = "";
        String out2 = "";
        String out3 = "";
        String out4 = "";
        String out5 = "";
        String out6 = "";
        String order = "";
        for (String s : lhm.keySet()) {
            Double diff = lhm.get(s);
            item j = hm.get(diff);
            order = order + "," + j.pattern;
            out1 = out1 + "," + j.avg1;
            out2 = out2 + "," + j.stdv1;
            out3 = out3 + "," + j.avg2;
            out4 = out4 + "," + j.stdv2;
            out5 = out5 + "," + j.avg3;
            out6 = out6 + "," + j.stdv3;
        }

        fwo.write(order.substring(1));
        fw1.write(out1.substring(1) + "\n" + out2.substring(1));
        fw2.write(out3.substring(1) + "\n" + out4.substring(1));
        fw3.write(out5.substring(1) + "\n" + out6.substring(1));

        br.close();
        bro.close();
        fw1.close();
        fw2.close();
        fw3.close();
        fwo.close();
    }

    public static void sortDiffSignificant() throws FileNotFoundException, IOException {
//        File fileIn = new File("C:\\Project\\EDU\\files\\2013\\example\\Topic\\3\\statNormal1ClusterStdv20new.txt");
        File fileIn = new File(path + "\\statNormal1ClusterStdvA.txt");
        BufferedReader br = new BufferedReader(new FileReader(fileIn));

//        File fileInO = new File("C:\\Project\\EDU\\files\\2013\\example\\Topic\\3\\Order20.txt");
        File fileInO = new File(path + "\\OrderA.txt");
        BufferedReader bro = new BufferedReader(new FileReader(fileInO));

//        File fileOutO = new File("C:\\Project\\EDU\\files\\2013\\example\\Topic\\3\\Order20New.txt");
        File fileOutO = new File(path + "\\OrderNewA.txt");
        FileWriter fwo = new FileWriter(fileOutO);

//        File fileOut1 = new File("C:\\Project\\EDU\\files\\2013\\example\\Topic\\3\\C0.txt");
        File fileOut1 = new File(path + "\\C0A.txt");
        FileWriter fw1 = new FileWriter(fileOut1);

//        File fileOut2 = new File("C:\\Project\\EDU\\files\\2013\\example\\Topic\\3\\C1.txt");
        File fileOut2 = new File(path + "\\C1A.txt");
        FileWriter fw2 = new FileWriter(fileOut2);

        HashMap<Double, item> hm = new HashMap<Double, item>();
        HashMap<String, Double> hmTemp = new HashMap<String, Double>();

        ArrayList<String> pattern = new ArrayList<String>();
        ArrayList<Double> avg1 = new ArrayList<Double>();
        ArrayList<Double> stdv1 = new ArrayList<Double>();
        ArrayList<Double> avg2 = new ArrayList<Double>();
        ArrayList<Double> stdv2 = new ArrayList<Double>();

        String line = "";
        line = bro.readLine();

        String[] token = line.split(",");
        for (int i = 0; i < token.length; i++) {
            pattern.add(token[i]);
        }

        br.readLine();
        line = br.readLine();
        token = line.split(",");
//        System.out.println(token.length);
        for (int i = 0; i < token.length; i++) {
            avg1.add(Double.valueOf(token[i]));
        }

        line = br.readLine();
        token = line.split(",");
        for (int i = 0; i < token.length; i++) {
            stdv1.add(Double.valueOf(token[i]));
        }
        br.readLine();
        br.readLine();
        line = br.readLine();
        token = line.split(",");
        for (int i = 0; i < token.length; i++) {
            avg2.add(Double.valueOf(token[i]));
        }
        line = br.readLine();
        token = line.split(",");
        for (int i = 0; i < token.length; i++) {
            stdv2.add(Double.valueOf(token[i]));
        }

        for (int i = 0; i < pattern.size(); i++) {
            item obj = new item();
            obj.pattern = pattern.get(i);
            obj.avg1 = avg1.get(i);
            obj.stdv1 = stdv1.get(i);
            obj.avg2 = avg2.get(i);
            obj.stdv2 = stdv2.get(i);

        }

        LinkedHashMap<String, Double> lhm = Util.sortHashMapByValues(hmTemp);

        String out1 = "";
        String out2 = "";
        String out3 = "";
        String out4 = "";
        String order = "";
        for (String s : lhm.keySet()) {
            Double diff = lhm.get(s);
            item j = hm.get(diff);
            order = order + "," + j.pattern;
            out1 = out1 + "," + j.avg1;
            out2 = out2 + "," + j.stdv1;
            out3 = out3 + "," + j.avg2;
            out4 = out4 + "," + j.stdv2;
        }

        fwo.write(order.substring(1));
        fw1.write(out1.substring(1) + "\n" + out2.substring(1));
        fw2.write(out3.substring(1) + "\n" + out4.substring(1));

        br.close();
        bro.close();
        fw1.close();
        fw2.close();
        fwo.close();
    }

    public static void reorder(String performance) throws FileNotFoundException, IOException {
//        File fileIn = new File("C:\\Project\\EDU\\files\\2013\\revised\\clean\\C0hiPost.txt");
//        File fileIn = new File("C:\\Project\\EDU\\files\\2013\\example\\C0lowLG.txt");
//        String cl = "C0low";
//        String cl = "C1low";
//        String cl = "C2low";
//        String cl = "C0hi";
//        String cl = "C1hi";
        String cl = "C2hi";

        File fileIn = new File(path + "\\" + cl + performance + ".txt");
        BufferedReader br = new BufferedReader(new FileReader(fileIn));

//        File fileO = new File("C:\\Project\\EDU\\files\\2013\\revised\\clean\\Order.txt");
//        File fileO = new File("C:\\Project\\EDU\\files\\2013\\example\\Order.txt");
        File fileO = new File(path + "\\Order.txt");
        BufferedReader bro = new BufferedReader(new FileReader(fileO));

//        File fileOn = new File("C:\\Project\\EDU\\files\\2013\\revised\\clean\\OrderNew.txt");
//        File fileOn = new File("C:\\Project\\EDU\\files\\2013\\example\\OrderNew.txt");
        File fileOn = new File(path + "\\OrderNew.txt");
        BufferedReader bron = new BufferedReader(new FileReader(fileOn));

//        File fileOut = new File("C:\\Project\\EDU\\files\\2013\\revised\\clean\\C0hiPostOrder.txt");
//        File fileOut = new File("C:\\Project\\EDU\\files\\2013\\example\\C0lowLGOrder.txt");
        File fileOut = new File(path + "\\" + cl + performance + "Order.txt");
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

    public static void findGenomeSequence() throws FileNotFoundException, IOException {
//        File fileIn = new File("C:\\Project\\EDU\\files\\QuizLabelTime.txt");
        File fileIn = new File("C:\\Project\\EDU\\files\\2013\\QuizLabelTimeGenome.txt");
        BufferedReader br = new BufferedReader(new FileReader(fileIn));

//        File fileOut = new File("C:\\Project\\EDU\\files\\QuizLabelTimeSequenceActivity2.txt");
        File fileOut = new File("C:\\Project\\EDU\\files\\2013\\QuizLabelTimeGenomeSequence.txt");
        FileWriter fw = new FileWriter(fileOut);

        ArrayList<String> seq = new ArrayList<String>();

        String line = br.readLine();
        String[] token = line.split("\t");

        String user = token[0];
//        String session = token[1];
        String activityname = token[2];
        String result = token[3];
        String durationseconds = token[4];

        String oldUser = user;
//        String oldSession = session;
        String oldActivityname = activityname;

        seq.add(durationseconds);

        while ((line = br.readLine()) != null) {
            oldUser = user;
//            oldSession = session;
            oldActivityname = activityname;

            token = line.split("\t");
            user = token[0];
//            session = token[1];
            activityname = token[2];
            result = token[3];
            durationseconds = token[4];

            if (!activityname.equals(oldActivityname)) {
//            if (!user.equals(oldUser)) {
                seq.add("_");
                fw.write(oldActivityname + "\t_" + mergeSequence(seq) + "\n");
                seq = new ArrayList<String>();
            } else if (!user.equals(oldUser)) {
//            } else if (!activityname.equals(oldActivityname)) {
                seq.add("_");
            }

            seq.add(durationseconds);

        }

        fw.write(oldUser + "\t_" + mergeSequence(seq) + "_\n");

        br.close();
        fw.close();
    }

    public static void filterComplexity() throws FileNotFoundException, IOException {
        File fileInC = new File("C:\\Project\\EDU\\files\\2013\\revised\\complexity\\complexity.txt");
        BufferedReader brc = new BufferedReader(new FileReader(fileInC));

        File fileInV = new File("C:\\Project\\EDU\\files\\2013\\revised\\complexity\\VectorNormal1.txt");
        BufferedReader brv = new BufferedReader(new FileReader(fileInV));

        File fileOutE = new File("C:\\Project\\EDU\\files\\2013\\revised\\complexity\\easy.txt");
        FileWriter fwe = new FileWriter(fileOutE);

        File fileOutH = new File("C:\\Project\\EDU\\files\\2013\\revised\\complexity\\hard.txt");
        FileWriter fwh = new FileWriter(fileOutH);

        ArrayList<String> easy = new ArrayList<String>();
        ArrayList<String> hard = new ArrayList<String>();

        String line = "";
        while ((line = brc.readLine()) != null) {
            String[] token = line.split("\t");
            String problem = token[0];
            String comp = token[1];
            if (comp.equals("h")) {
                hard.add(problem);
            }
            if (comp.equals("l")) {
                easy.add(problem);
            }
        }

        line = "";
        while ((line = brv.readLine()) != null) {
            String[] token = line.split("\t");
            String problem = token[0];
            if (easy.contains(problem)) {
                fwe.write(line + "\n");
            }
            if (hard.contains(problem)) {
                fwh.write(line + "\n");
            }
        }

        brc.close();
        brv.close();
        fwe.close();
        fwh.close();
    }

    public static void findCommon() throws FileNotFoundException, IOException {
        File fileIn1 = new File("C:\\Project\\EDU\\files\\2013\\revised\\complexity\\f1.txt");
        BufferedReader br1 = new BufferedReader(new FileReader(fileIn1));

        File fileIn2 = new File("C:\\Project\\EDU\\files\\2013\\revised\\complexity\\f2.txt");
        BufferedReader br2 = new BufferedReader(new FileReader(fileIn2));

        ArrayList<String> easy = new ArrayList<String>();
        ArrayList<String> hard = new ArrayList<String>();

        String line = "";
        while ((line = br1.readLine()) != null) {
            easy.add(line.trim());
        }

        line = "";
        while ((line = br2.readLine()) != null) {
            hard.add(line.trim());
        }

        for (String s : easy) {
            if (hard.contains(s)) {
                System.out.println(s);
            }
        }

        br1.close();
        br2.close();
    }

    public static void readyNormal() throws FileNotFoundException, IOException {
//        File fileInV = new File("C:\\Project\\EDU\\files\\2013\\example\\normalVec.txt");
//        File fileInV = new File("C:\\Project\\EDU\\files\\2013\\example\\Topic\\3\\normalVec.txt");
        File fileInV = new File(path + "\\normalVec.txt");
        BufferedReader brv = new BufferedReader(new FileReader(fileInV));

//        File fileInS = new File("C:\\Project\\EDU\\files\\2013\\example\\SequenceVector.txt");
//        File fileInS = new File("C:\\Project\\EDU\\files\\2013\\example\\Topic\\3\\SequenceVector.txt");
        File fileInS = new File(path + "\\SequenceVector.txt");
        BufferedReader brs = new BufferedReader(new FileReader(fileInS));

//        File fileOut = new File("C:\\Project\\EDU\\files\\2013\\example\\VectorNormal1.txt");
//        File fileOut = new File("C:\\Project\\EDU\\files\\2013\\example\\Topic\\3\\VectorNormal1.txt");
        File fileOut = new File(path + "\\VectorNormal1.txt");
        FileWriter fw = new FileWriter(fileOut);

        ArrayList<String> ids = new ArrayList<String>();

        String line = "";
        while ((line = brs.readLine()) != null) {
            String id = line.split("\t")[0];
            ids.add(id);
        }

        int c = 0;
        while ((line = brv.readLine()) != null) {
            line = line.replaceAll("\t", ",");
            fw.write(ids.get(c) + "\t" + line + "\n");
            c++;
        }

        brs.close();
        brv.close();
        fw.close();

    }

    public static void addCluster() throws FileNotFoundException, IOException {
//        File fileInC = new File("C:\\Project\\EDU\\files\\2013\\example\\Cluster.txt");
//        File fileInC = new File("C:\\Project\\EDU\\files\\2013\\example\\Topic\\3\\Cluster.txt");
        File fileInC = new File(path + "\\Cluster3.txt");
        BufferedReader brc = new BufferedReader(new FileReader(fileInC));

//        File fileInS = new File("C:\\Project\\EDU\\files\\2013\\example\\SequenceVector.txt");
//        File fileInS = new File("C:\\Project\\EDU\\files\\2013\\example\\Topic\\3\\SequenceVector.txt");
        File fileInS = new File(path + "\\SequenceVector.txt");
        BufferedReader brs = new BufferedReader(new FileReader(fileInS));

//        File fileOut = new File("C:\\Project\\EDU\\files\\2013\\example\\VectorNormal1Cluster.txt");
//        File fileOut = new File("C:\\Project\\EDU\\files\\2013\\example\\Topic\\3\\VectorNormal1Cluster.txt");
        File fileOut = new File(path + "\\VectorNormal1Cluster.txt");
        FileWriter fw = new FileWriter(fileOut);

        ArrayList<String> ids = new ArrayList<String>();

        String line = "";
        while ((line = brs.readLine()) != null) {
            String id = line.split("\t")[0];
            ids.add(id);
        }

        int c = 0;
        while ((line = brc.readLine()) != null) {
            fw.write(ids.get(c) + "\t" + line + "\n");
            c++;
        }

        brc.close();
        brs.close();
        fw.close();

    }

    public static void findCourse() throws FileNotFoundException, IOException {
        File fileIn = new File("C:\\Project\\EDU\\kdd10\\kddcup_challenge\\algebra_2008_2009_train.txt");
        BufferedReader br = new BufferedReader(new FileReader(fileIn));

        ArrayList<String> prob = new ArrayList<String>();

        String line = "";
        while ((line = br.readLine()) != null) {
            String problem = line.split("\t")[2];
//            System.out.println(problem);
            if (!prob.contains(problem)) {
                prob.add(problem);
//                System.out.println(problem);
            }
        }
        System.out.println(prob.size());
    }

    public static void statRaw() throws FileNotFoundException, IOException {
        File file = new File("C:\\Project\\EDU\\files\\2013\\example\\LabelSequenceFilter.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));

        String line = "";

        int s = 0;
        int f = 0;
        int S = 0;
        int F = 0;
        int e = 0;
        int E = 0;

        while ((line = br.readLine()) != null) {
            for (int i = 0; i < line.length(); i++) {
                if (line.charAt(i) == 's') {
                    s++;
                } else if (line.charAt(i) == 'S') {
                    S++;
                } else if (line.charAt(i) == 'f') {
                    f++;
                } else if (line.charAt(i) == 'F') {
                    F++;
                } else if (line.charAt(i) == 'e') {
                    e++;
                } else if (line.charAt(i) == 'E') {
                    E++;
                }
            }
        }

        System.out.println("s: " + s);
        System.out.println("S: " + S);
        System.out.println("f: " + f);
        System.out.println("F: " + F);
        System.out.println("e: " + e);
        System.out.println("E: " + E);

        br.close();

    }

    public static void findTop30() throws FileNotFoundException, IOException {
        File fileIn = new File(path + "\\patternsTranslate.txt");
        BufferedReader br = new BufferedReader(new FileReader(fileIn));

        File fileOut = new File(path + "\\Top30.txt");
        FileWriter fw = new FileWriter(fileOut);

        ArrayList<String> ptrn = new ArrayList<String>();
        ArrayList<Integer> freq = new ArrayList<Integer>();
        ArrayList<Integer> rank = new ArrayList<Integer>();

        String line = "";
        while ((line = br.readLine()) != null) {
            String[] token = line.split("\t");
            ptrn.add(token[0]);
            freq.add(Integer.valueOf(token[1]));
            rank.add(Integer.valueOf(token[1]));
        }

        Collections.sort(rank, Collections.reverseOrder());
        int c = rank.get(30);

        for (int i = 0; i < freq.size(); i++) {
            if (freq.get(i) > c) {
                System.out.println(ptrn.get(i) + "\t" + freq.get(i));
                fw.write(ptrn.get(i) + "\t" + freq.get(i) + "\n");
            }
        }

        br.close();
        fw.close();

    }

    public static void findTop30Remove2() throws FileNotFoundException, IOException {
        File fileIn = new File(path + "\\patternsTranslate.txt");
        BufferedReader br = new BufferedReader(new FileReader(fileIn));

        File fileOut = new File(path + "\\Top30.txt");
        FileWriter fw = new FileWriter(fileOut);

        File fileOutF = new File(path + "\\patternsTranslateFilter.txt");
        FileWriter fwf = new FileWriter(fileOutF);

        ArrayList<String> ptrn = new ArrayList<String>();
        ArrayList<Integer> freq = new ArrayList<Integer>();
        ArrayList<Integer> rank = new ArrayList<Integer>();

        String line = "";
        while ((line = br.readLine()) != null) {
            String[] token = line.split("\t");
            if (token[0].trim().replaceAll("_", "").length() > 1) {
                ptrn.add(token[0]);
                freq.add(Integer.valueOf(token[1]));
                rank.add(Integer.valueOf(token[1]));
            }
        }

        Collections.sort(rank, Collections.reverseOrder());
        int c = rank.get(30);

        for (int i = 0; i < freq.size(); i++) {
            if (freq.get(i) > c) {
                System.out.println(ptrn.get(i) + "\t" + freq.get(i));
                fw.write(ptrn.get(i) + "\t" + freq.get(i) + "\n");
                fwf.write(ptrn.get(i) + "\t" + freq.get(i) + "\n");
            }
        }

        br.close();
        fw.close();
        fwf.close();

    }

    public static void removeNoE() throws FileNotFoundException, IOException {
        File fileIn = new File(path + "\\LabelSequenceFilter.txt");
        BufferedReader br = new BufferedReader(new FileReader(fileIn));

        File fileOut = new File(path + "\\LabelSequenceFilterE.txt");
        FileWriter fw = new FileWriter(fileOut);

        String line = "";
        while ((line = br.readLine()) != null) {
            String[] t = line.split("\t");
            String id = t[0];
            String seq = t[1];
            String[] token = seq.split("_");
            String out = "";
            for (int i = 0; i < token.length; i++) {
                if (token[i].toLowerCase().contains("e")) {
                    out = out + "_" + token[i];
                }
            }
            if (out.length() > 0) {
                fw.write(id + "\t" + out + "_\n");
            }

        }

        br.close();
        fw.close();
    }

    public static void perfCount2(String perf) throws FileNotFoundException, IOException {
        File fileIn = new File(path + "\\statNormal.txt");
        BufferedReader br = new BufferedReader(new FileReader(fileIn));

        File fileOut = new File(path + "\\statNormal" + perf + ".txt");
        FileWriter fw = new FileWriter(fileOut);

        int[] low = new int[3];
        int[] med = new int[3];
        int[] hi = new int[3];

        String prf = "";
        int clstr = -1;
        String line = "";

        while ((line = br.readLine()) != null) {
            String[] token = line.split("\t");
            clstr = Integer.valueOf(token[4]);

            if (perf.equals("Pre")) {
                prf = token[1];
            } else if (perf.equals("Post")) {
                prf = token[2];
            } else if (perf.equals("LG")) {
                prf = token[3];
            } else {
                System.out.println("Wrong Pefromance");
                break;
            }

            if (prf.equals("l")) {
                low[clstr]++;
            } else if (prf.equals("m")) {
                med[clstr]++;
            } else if (prf.equals("h")) {
                hi[clstr]++;
            } else {
                System.out.println("Invalid Peformance");
                break;
            }

//            System.out.println(prf);
        }

        fw.write(low[0] + "," + low[1] + "," + low[2] + "\n");
        fw.write(med[0] + "," + med[1] + "," + med[2] + "\n");
        fw.write(hi[0] + "," + hi[1] + "," + hi[2]);

        for (int i : low) {
            System.out.println(i);
        }

        for (int i : med) {
            System.out.println(i);
        }

        for (int i : hi) {
            System.out.println(i);
        }

        br.close();
        fw.close();
    }

    public static void examplePerf(String perf) throws FileNotFoundException, IOException {
        File fileInSeq = new File(path + "\\LabelSequenceFilter.txt");
        BufferedReader brs = new BufferedReader(new FileReader(fileInSeq));

        File fileInPerf = new File(path + "\\perfAll.txt");
        BufferedReader brp = new BufferedReader(new FileReader(fileInPerf));

//        File fileOutPr = new File(path + "\\perfStat" + perf + ".txt");
//        FileWriter fwr = new FileWriter(fileOutPr);
        HashMap<String, String> hm = new HashMap<String, String>();

        String line = "";
        while ((line = brp.readLine()) != null) {
            String[] token = line.split("\t");
            String user = token[0];
            String prf = "";

            if (perf.equals("Pre")) {
                prf = token[1];
            } else if (perf.equals("Post")) {
                prf = token[2];
            } else if (perf.equals("LG")) {
                prf = token[3];
            }
            hm.put(user, prf);
        }

        int low = 0;
        int med = 0;
        int hi = 0;

        int lc = 0;
        int mc = 0;
        int hc = 0;

        while ((line = brs.readLine()) != null) {
            String[] token = line.split("\t");
            String user = token[0];
            String seq = token[1];
            int e = 0;

            if (hm.containsKey(user)) {
                for (int i = 0; i < seq.length(); i++) {
                    if (seq.charAt(i) == 'e') {
                        e++;
                    }
                }
                System.out.println(user + "\t" + e);

                String p = hm.get(user);
//                System.out.println("P " + p);
                if (p.equals("l")) {
                    lc++;
                    low += e;
                } else if (p.equals("m")) {
                    mc++;
                    med += e;
                } else if (p.equals("h")) {
                    hc++;
                    hi += e;
                }

            }

        }
        System.out.println("low avg: " + low / lc + " count: " + lc);
        System.out.println("med avg: " + med / mc + " count: " + mc);
        System.out.println("hi avg:  " + hi / hc + " count: " + hc);

        brs.close();
        brp.close();
    }

    public static void reverse(String fileName) throws FileNotFoundException, IOException {
        File fileInSeq = new File(path + "\\" + fileName + ".txt");
        BufferedReader br = new BufferedReader(new FileReader(fileInSeq));

        File fileOut = new File(path + "\\" + fileName + "R.txt");
        FileWriter fw = new FileWriter(fileOut);

        String line = "";

        while ((line = br.readLine()) != null) {
            ArrayList<String> ar = new ArrayList<String>();

            String[] token = line.split(",");
            for (int i = 0; i < token.length; i++) {
                ar.add(token[i]);
            }
            Collections.reverse(ar);

            String out = "";
            for (int i = 0; i < ar.size(); i++) {
                out = out + "," + ar.get(i);
            }
            fw.write(out.substring(1) + "\n");
        }

        br.close();
        fw.close();
    }

    public static void matchPerfSeq() throws FileNotFoundException, IOException {
        File fileInPerf = new File(path + "\\perfAll.txt");
        BufferedReader brp = new BufferedReader(new FileReader(fileInPerf));

        File fileInSeq = new File(path + "\\LabelSequenceFilter.txt");
        BufferedReader brs = new BufferedReader(new FileReader(fileInSeq));

        File fileOutPreL = new File(path + "\\pre\\seqPreLow.txt");
        FileWriter fwPreL = new FileWriter(fileOutPreL);
        File fileOutPreM = new File(path + "\\pre\\seqPreMed.txt");
        FileWriter fwPreM = new FileWriter(fileOutPreM);
        File fileOutPreH = new File(path + "\\pre\\seqPreHi.txt");
        FileWriter fwPreH = new FileWriter(fileOutPreH);
        File fileOutPostL = new File(path + "\\post\\seqPostLow.txt");
        FileWriter fwPostL = new FileWriter(fileOutPostL);
        File fileOutPostM = new File(path + "\\post\\seqPostMed.txt");
        FileWriter fwPostM = new FileWriter(fileOutPostM);
        File fileOutPostH = new File(path + "\\post\\seqPostHi.txt");
        FileWriter fwPostH = new FileWriter(fileOutPostH);
        File fileOutlgL = new File(path + "\\lg\\seqlgLow.txt");
        FileWriter fwlgL = new FileWriter(fileOutlgL);
        File fileOutlgM = new File(path + "\\lg\\seqlgMed.txt");
        FileWriter fwlgM = new FileWriter(fileOutlgM);
        File fileOutlgH = new File(path + "\\lg\\seqlgHi.txt");
        FileWriter fwlgH = new FileWriter(fileOutlgH);

        ArrayList<String> ids = new ArrayList<String>();
        ArrayList<String> seqs = new ArrayList<String>();

        String line = "";

        while ((line = brs.readLine()) != null) {
            String[] token = line.split("\t");
            ids.add(token[0]);
            seqs.add(token[1]);
        }

        while ((line = brp.readLine()) != null) {
            String[] token = line.split("\t");
            String id = token[0];
            int ix = ids.indexOf(id);
            String pre = token[1];
            String post = token[2];
            String lg = token[3];
            if (ids.contains(id)) {
                if (pre.equals("l")) {
                    fwPreL.write(id + "\t" + seqs.get(ix) + "\n");
                }
                if (pre.equals("m")) {
                    fwPreM.write(id + "\t" + seqs.get(ix) + "\n");
                }
                if (pre.equals("h")) {
                    fwPreH.write(id + "\t" + seqs.get(ix) + "\n");
                }

                if (post.equals("l")) {
                    fwPostL.write(id + "\t" + seqs.get(ix) + "\n");
                }
                if (post.equals("m")) {
                    fwPostM.write(id + "\t" + seqs.get(ix) + "\n");
                }
                if (post.equals("h")) {
                    fwPostH.write(id + "\t" + seqs.get(ix) + "\n");
                }

                if (lg.equals("l")) {
                    fwlgL.write(id + "\t" + seqs.get(ix) + "\n");
                }
                if (lg.equals("m")) {
                    fwlgM.write(id + "\t" + seqs.get(ix) + "\n");
                }
                if (lg.equals("h")) {
                    fwlgH.write(id + "\t" + seqs.get(ix) + "\n");
                }
            }
        }

        brp.close();
        brs.close();
        fwPreL.close();
        fwPreM.close();
        fwPreH.close();
        fwPostL.close();
        fwPostM.close();
        fwPostH.close();
        fwlgL.close();
        fwlgM.close();
        fwlgH.close();

    }

    public static void mergeThree() throws FileNotFoundException, IOException {
        File fileInl = new File(path + "\\Low.txt");
        BufferedReader brl = new BufferedReader(new FileReader(fileInl));

        File fileInm = new File(path + "\\Med.txt");
        BufferedReader brm = new BufferedReader(new FileReader(fileInm));

        File fileInh = new File(path + "\\Hi.txt");
        BufferedReader brh = new BufferedReader(new FileReader(fileInh));

//        File fileOut = new File(path + "\\lowmedhi.txt");
//        FileWriter fw = new FileWriter(fileOut);
        ArrayList<String> ptrnlow = new ArrayList<String>();
        ArrayList<String> ptrnmed = new ArrayList<String>();
        ArrayList<String> ptrnhi = new ArrayList<String>();
        ArrayList<String> low = new ArrayList<String>();
        ArrayList<String> med = new ArrayList<String>();
        ArrayList<String> hi = new ArrayList<String>();

        String line = "";

        while ((line = brl.readLine()) != null) {
            String[] token = line.split("\t");
            ptrnlow.add(token[0]);
            low.add(token[1]);
        }

        while ((line = brm.readLine()) != null) {
            String[] token = line.split("\t");
            ptrnmed.add(token[0]);
            med.add(token[1]);
        }

        while ((line = brh.readLine()) != null) {
            String[] token = line.split("\t");
            ptrnhi.add(token[0]);
            hi.add(token[1]);
        }

        String pattern = "";
        String outl = "";
        String outm = "";
        String outh = "";
        for (int i = 0; i < low.size(); i++) {
            String pt = ptrnlow.get(i);
            pattern = pattern + "," + pt;
            outl = outl + "," + low.get(i);
            int ix = ptrnmed.indexOf(pt);
            if (ix > -1) {
                outm = outm + "," + med.get(ix);
            } else {
                outm = outm + ",0";
            }

            int iy = ptrnhi.indexOf(pt);
            if (iy > -1) {
                outh = outh + "," + hi.get(iy);
            } else {
                outh = outh + ",0";
            }

        }

        System.out.println(pattern.substring(1));
        System.out.println(outl.substring(1));
        System.out.println(outm.substring(1));
        System.out.println(outh.substring(1));

        brl.close();
        brm.close();
        brh.close();
    }

    public static void mergeThreeAll() throws FileNotFoundException, IOException {
        File fileInl = new File(path + "\\Low.txt");
        BufferedReader brl = new BufferedReader(new FileReader(fileInl));

        File fileInm = new File(path + "\\Med.txt");
        BufferedReader brm = new BufferedReader(new FileReader(fileInm));

        File fileInh = new File(path + "\\Hi.txt");
        BufferedReader brh = new BufferedReader(new FileReader(fileInh));

        ArrayList<String> ptrn = new ArrayList<String>();
        ArrayList<String> ptrnlow = new ArrayList<String>();
        ArrayList<String> ptrnmed = new ArrayList<String>();
        ArrayList<String> ptrnhi = new ArrayList<String>();
        ArrayList<String> low = new ArrayList<String>();
        ArrayList<String> med = new ArrayList<String>();
        ArrayList<String> hi = new ArrayList<String>();

        String line = "";

        while ((line = brl.readLine()) != null) {
            String[] token = line.split("\t");
            String p = token[0];
            String f = token[1];
            ptrnlow.add(p);
            low.add(f);
            if (!ptrn.contains(p)) {
                ptrn.add(p);
            }
        }

        while ((line = brm.readLine()) != null) {
            String[] token = line.split("\t");
            String p = token[0];
            String f = token[1];
            ptrnmed.add(p);
            med.add(f);
            if (!ptrn.contains(p)) {
                ptrn.add(p);
            }
        }

        while ((line = brh.readLine()) != null) {
            String[] token = line.split("\t");
            String p = token[0];
            String f = token[1];
            ptrnhi.add(p);
            hi.add(f);
            if (!ptrn.contains(p)) {
                ptrn.add(p);
            }
        }

        String pattern = "";
        String outl = "";
        String outm = "";
        String outh = "";
        for (int i = 0; i < ptrn.size(); i++) {
            String pt = ptrn.get(i);
            pattern = pattern + "," + pt;

            int iz = ptrnlow.indexOf(pt);
            if (iz > -1) {
                outl = outl + "," + low.get(iz);
            } else {
                outl = outl + ",0";
            }

            int ix = ptrnmed.indexOf(pt);
            if (ix > -1) {
                outm = outm + "," + med.get(ix);
            } else {
                outm = outm + ",0";
            }

            int iy = ptrnhi.indexOf(pt);
            if (iy > -1) {
                outh = outh + "," + hi.get(iy);
            } else {
                outh = outh + ",0";
            }

        }

        System.out.println(pattern.substring(1));
        System.out.println(outl.substring(1));
        System.out.println(outm.substring(1));
        System.out.println(outh.substring(1));

        brl.close();
        brm.close();
        brh.close();
    }

    public static void separateLowHiMed(String p) throws FileNotFoundException, IOException {
        File fileInPerf = new File(path + "\\perfAll.txt");
        BufferedReader brperf = new BufferedReader(new FileReader(fileInPerf));

        File fileInPatt = new File(path + "\\PatternsTranslateFilter.txt");
        BufferedReader brpatt = new BufferedReader(new FileReader(fileInPatt));

        File fileInVec = new File(path + "\\VectorNormal1.txt");
        BufferedReader brvec = new BufferedReader(new FileReader(fileInVec));

        File fileOutL = new File(path + "\\low.txt");
        FileWriter fwlow = new FileWriter(fileOutL);
        
        File fileOutM = new File(path + "\\low.txt");
        FileWriter fwmed = new FileWriter(fileOutM);
        
        File fileOutH = new File(path + "\\low.txt");
        FileWriter fwhi = new FileWriter(fileOutH);
        
        HashMap<String, String> vecMap = new HashMap<String, String>();
        
        String line = "";
        while ((line = brvec.readLine()) != null) {
            String[] token = line.split("\t");
            String id = token [0].trim();
            String vec = token [1].trim();
            vecMap.put(id, vec);
        }
        
        while ((line = brperf.readLine()) != null) {
            String[] token = line.split("\t");
            String id = token[0];
            String prf = "";
            if (p.equals("pre")){
                prf = token[1];
            } else if (p.equals("post")){
                prf = token[2];
            } else if (p.equals("lg")){
                prf = token[3];
            }
        }
    }

}
