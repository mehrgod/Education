/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package education;

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

/**
 *
 * @author mirza
 */
public class Education {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
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
//==========================================
//        forCMspam();
        //CMSPAM
//        SPMFtoTEXT();
        //remove length less than two
        //Create Top 30
        createVectorUser();
        //normalize using python
        //cluster using python
//        createStatcluster();
//        findFrequencyPatternCluster();
        //Sort difference of averages and order the patterns
        //Plot 2 clusters using pyhton
//        findFrequencyPatternAll();
//        reorder();

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
        File fileIn = new File("C:\\Project\\EDU\\files\\2013\\revised\\clean\\Stability\\Sequence.txt");
        BufferedReader br = new BufferedReader(new FileReader(fileIn));

//        File fileOut = new File("C:\\Project\\EDU\\files\\2013\\QuizLabelTimeSequenceCMSPAMdot.text");
//        File fileOut = new File("C:\\Project\\EDU\\files\\2013\\revised\\clean\\SequenceCMSPAM.text");
        File fileOut = new File("C:\\Project\\EDU\\files\\2013\\revised\\clean\\Stability\\Sequence60P1CMSPAM.text");
        FileWriter fw = new FileWriter(fileOut);

        String line = "";
        while ((line = br.readLine()) != null) {
            String seq = line.split("\t")[1];
//            seq = seq.replace("_", "x");
            seq = seq.replace("F", "e");
            seq = seq.replace("S", "b");

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
        File fileIn = new File("C:\\Project\\EDU\\files\\2013\\revised\\clean\\Stability\\Patterns.txt");
        BufferedReader br = new BufferedReader(new FileReader(fileIn));

//        File fileOut = new File("C:\\Project\\EDU\\files\\2013\\QuizLabelTimeSequenceCMSPAMpatternsAllTranslateSUP.text");
        File fileOut = new File("C:\\Project\\EDU\\files\\2013\\revised\\clean\\Stability\\PatternsTranslate.txt");
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
        File fileIn1 = new File("C:\\Project\\EDU\\files\\2013\\revised\\clean\\Stability\\Sequence60P2.txt");
        BufferedReader br1 = new BufferedReader(new FileReader(fileIn1));

//        File fileIn2 = new File("C:\\Project\\EDU\\files\\2013\\QuizLabelTimeSequenceCMSPAMpatternsTranslate.text");
        File fileIn2 = new File("C:\\Project\\EDU\\files\\2013\\revised\\clean\\Stability\\PatternsTranslateFilter.txt");
        BufferedReader br2 = new BufferedReader(new FileReader(fileIn2));

//        File fileOut = new File("C:\\Project\\EDU\\files\\2013\\QuizLabelTimeSequenceVectorNormal.txt");
        File fileOut = new File("C:\\Project\\EDU\\files\\2013\\revised\\clean\\Stability\\Sequence60P2Vector.txt");
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
            System.out.println(vector);
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
        File fileInp = new File("C:\\Project\\EDU\\files\\2013\\revised\\clean\\perfAll.txt");
        BufferedReader brp = new BufferedReader(new FileReader(fileInp));

//        File fileInc = new File("C:\\Project\\EDU\\files\\2013\\QuizLabelTimeSequenceVectorNormal1Cluster.txt");
        File fileInc = new File("C:\\Project\\EDU\\files\\2013\\revised\\clean\\SequenceVectorNormalCluster.txt");
        BufferedReader brc = new BufferedReader(new FileReader(fileInc));

//        File fileInv = new File("C:\\Project\\EDU\\files\\2013\\QuizLabelTimeSequenceVectorNormal1.txt");
        File fileInv = new File("C:\\Project\\EDU\\files\\2013\\revised\\clean\\SequenceVectorNormal.txt");
        BufferedReader brv = new BufferedReader(new FileReader(fileInv));

//        File fileOut = new File("C:\\Project\\EDU\\files\\2013\\statNormal1.txt");
        File fileOut = new File("C:\\Project\\EDU\\files\\2013\\revised\\clean\\statNormal.txt");
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
        File fileIn = new File("C:\\Project\\EDU\\files\\2013\\revised\\complexity\\problems.txt");
        BufferedReader br = new BufferedReader(new FileReader(fileIn));

        File fileOut = new File("C:\\Project\\EDU\\files\\2013\\revised\\complexity\\problemsComplexity.txt");
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
        File fileIn = new File("C:\\Project\\EDU\\files\\2013\\revised\\clean\\statNormal.txt");
        BufferedReader br = new BufferedReader(new FileReader(fileIn));

        File fileT = new File("C:\\Project\\EDU\\files\\2013\\revised\\clean\\Top30.txt");
        BufferedReader brt = new BufferedReader(new FileReader(fileT));

        File fileP = new File("C:\\Project\\EDU\\files\\2013\\revised\\clean\\PatternsTranslateFilter.txt");
        BufferedReader brp = new BufferedReader(new FileReader(fileP));

        File fileOut = new File("C:\\Project\\EDU\\files\\2013\\revised\\clean\\statNormalAllStdv.txt");
        FileWriter fw = new FileWriter(fileOut);

        ArrayList<String> top30 = new ArrayList<String>();
        ArrayList<String> all = new ArrayList<String>();

        String line = "";
        while ((line = brt.readLine()) != null) {
            top30.add(line.split("\t")[0]);
        }

        while ((line = brp.readLine()) != null) {
            all.add(line.split("\\s+")[0]);
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

    public static void findFrequencyPatternCluster() throws FileNotFoundException, IOException {
        File fileIn = new File("C:\\Project\\EDU\\files\\2013\\revised\\clean\\statNormal.txt");
        BufferedReader br = new BufferedReader(new FileReader(fileIn));

        File fileT = new File("C:\\Project\\EDU\\files\\2013\\revised\\clean\\Top30.txt");
        BufferedReader brt = new BufferedReader(new FileReader(fileT));

        File fileP = new File("C:\\Project\\EDU\\files\\2013\\revised\\clean\\PatternsTranslateFilter.txt");
        BufferedReader brp = new BufferedReader(new FileReader(fileP));

        File fileOut = new File("C:\\Project\\EDU\\files\\2013\\revised\\clean\\statNormalClusterStdv.txt");
        FileWriter fw = new FileWriter(fileOut);

        File fileOrd = new File("C:\\Project\\EDU\\files\\2013\\revised\\clean\\Order.txt");
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

//        ArrayList<String> cls1low = new ArrayList<String>();
//        ArrayList<String> cls1hi = new ArrayList<String>();
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
                System.out.println(all.get(i));
                order = order + "," + all.get(i);
                avgStr = avgStr + "," + avg[i];
                stdStr = stdStr + "," + std.get(i);
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

        avg = avgVec(cls1);
        std = findSD(cls1);

        fw.write("cluster 1: \n");
        for (int i = 0; i < avg.length; i++) {
            if (top30.contains(all.get(i))) {
                System.out.println(all.get(i));
                avgStr = avgStr + "," + avg[i];
                stdStr = stdStr + "," + std.get(i);
            }
        }

        fw.write(avgStr.substring(1) + "\n" + stdStr.substring(1) + "\n\n");
////////////////////////////////////////////////////////////////////////////

        fw.close();
        fwOrd.close();
        br.close();
        brt.close();
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

    public static void reorder() throws FileNotFoundException, IOException {
        File fileIn = new File("C:\\Project\\EDU\\files\\2013\\revised\\clean\\C0hi.txt");
        BufferedReader br = new BufferedReader(new FileReader(fileIn));

        File fileO = new File("C:\\Project\\EDU\\files\\2013\\revised\\clean\\Order.txt");
        BufferedReader bro = new BufferedReader(new FileReader(fileO));

        File fileOn = new File("C:\\Project\\EDU\\files\\2013\\revised\\clean\\OrderNew.txt");
        BufferedReader bron = new BufferedReader(new FileReader(fileOn));

        File fileOut = new File("C:\\Project\\EDU\\files\\2013\\revised\\clean\\C0hiOrder.txt");
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
                for (String s: token) {
                    mean.add(s);
                }
                f = 1;
            } else {
                for (String s: token){
                    stdv.add(s);
                }
            }
        }
        
        String avg = "";
        String std = "";
        
        for (int i = 0; i < newOrder.size(); i++){
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
