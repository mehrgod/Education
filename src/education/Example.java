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
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 *
 * @author mirza
 */
public class Example {

    static String path = "C:\\Project\\EDU\\files\\2013\\example\\Topic\\stability\\complexity\\";

    public static void main(String[] args) throws IOException {
//        ArrayList<Double> temp = new ArrayList<Double>();
//        temp.add(5.0);
//        temp.add(2.0);
//        temp.add(4.0);
//        temp.add(1.0);
//        temp.add(6.0);
//        System.out.println(takeAverage(temp));
//        System.out.println(findMedian(temp));
//        ExampleMean();
//        ExampleMeanLine();
//        dividDurationLabelLine();
//        findSequenceLine();
//        dividDurationLabel();
//        findSequence();
//        findAverageExamplePerLine();
//        findAverageExamplePerExample();
//        dividDurationLabelLineTopic();
//        findSequenceTopic();
//        sortDiff();
//        mergeExample();

//        findSequenceSession();
        findSequenceTopicComplexity();
    }

    public static void ExampleMean() throws IOException {
        File fileIn = new File(path + "QuizjetWebex.txt");
        BufferedReader br = new BufferedReader(new FileReader(fileIn));

        File fileOutP = new File(path + "timesMeidanProblem.txt");
        FileWriter fwp = new FileWriter(fileOutP);

        File fileOutE = new File(path + "timesMeidanExample.txt");
        FileWriter fwe = new FileWriter(fileOutE);

        ArrayList<String> problem = new ArrayList<String>();
        ArrayList<ArrayList<Double>> ptime = new ArrayList<ArrayList<Double>>();

        ArrayList<String> example = new ArrayList<String>();
        ArrayList<ArrayList<Double>> etime = new ArrayList<ArrayList<Double>>();

        String line = "";
        while ((line = br.readLine()) != null) {
            String[] token = line.split("\t");
            String type = token[2];
            String expr = token[3];
            Double ti = Double.valueOf(token[5]);

            if (type.equals("QUIZJET")) {
                if (problem.contains(expr)) {
                    int ix = problem.indexOf(expr);
                    ptime.get(ix).add(ti);
                } else {
                    problem.add(expr);
                    ArrayList<Double> temp = new ArrayList<Double>();
                    temp.add(ti);
                    ptime.add(temp);
                }
            } else if (type.equals("WEBEX")) {
                if (example.contains(expr)) {
                    int ix = example.indexOf(expr);
                    etime.get(ix).add(ti);
                } else {
                    example.add(expr);
                    ArrayList<Double> temp = new ArrayList<Double>();
                    temp.add(ti);
                    etime.add(temp);
                }
            } else {
                System.out.println("ERROR");
                System.out.println(line);
            }
        }

        for (int i = 0; i < example.size(); i++) {
            fwe.write(example.get(i) + "\t" + findMedian(etime.get(i)) + "\n");
        }

        for (int i = 0; i < problem.size(); i++) {
            fwp.write(problem.get(i) + "\t" + findMedian(ptime.get(i)) + "\n");
        }

        br.close();
        fwp.close();
        fwe.close();
    }

    public static void ExampleMeanLine() throws IOException {
        File fileIn = new File(path + "WebexOrder.txt");
        BufferedReader br = new BufferedReader(new FileReader(fileIn));

        File fileOutE = new File(path + "WebexOrderMeidan.txt");
        FileWriter fwe = new FileWriter(fileOutE);

        ArrayList<String> example = new ArrayList<String>();
        ArrayList<ArrayList<Double>> etime = new ArrayList<ArrayList<Double>>();

        String line = "";
        while ((line = br.readLine()) != null) {
            String[] token = line.split("\t");
            String exLine = token[0];
            String exmp = token[1];
            Double ti = Double.valueOf(token[2]);
            String exmpLine = exmp + "@" + exLine;

            if (example.contains(exmpLine)) {
                int ix = example.indexOf(exmpLine);
                etime.get(ix).add(ti);
            } else {
                example.add(exmpLine);
                ArrayList<Double> temp = new ArrayList<Double>();
                temp.add(ti);
                etime.add(temp);
            }
        }

        for (int i = 0; i < example.size(); i++) {
            fwe.write(example.get(i) + "\t" + findMedian(etime.get(i)) + "\n");
        }

        br.close();
        fwe.close();
    }

    public static Double takeAverage(ArrayList<Double> arr) {
        Double avg = 0.0;
        for (Double a : arr) {
            avg += a;
        }
        if (arr.size() > 0) {
            avg = avg / arr.size();
        }
        return avg;
    }

    public static Double findMedian(ArrayList<Double> arr) {
        Double median = arr.get(0);
        Collections.sort(arr);
        median = (arr.get(arr.size() / 2) + arr.get(arr.size() / 2 - 1)) / 2;
        return median;
    }

    public static void dividDurationLabel() throws FileNotFoundException, IOException {
        File fileIn = new File(path + "QuizjetWebex.txt");
        BufferedReader br = new BufferedReader(new FileReader(fileIn));

        File fileInp = new File(path + "timesMeidanProblem.txt");
        BufferedReader brp = new BufferedReader(new FileReader(fileInp));

        File fileIne = new File(path + "timesMeidanExample.txt");
        BufferedReader bre = new BufferedReader(new FileReader(fileIne));

        File fileOut = new File(path + "label.txt");
        FileWriter fw = new FileWriter(fileOut);

        HashMap<String, Double> hmp = new HashMap<String, Double>();
        HashMap<String, Double> hme = new HashMap<String, Double>();

        String line = "";

        while ((line = brp.readLine()) != null) {
            String[] token = line.split("\t");
            hmp.put(token[0], Double.valueOf(token[1]));
        }

        while ((line = bre.readLine()) != null) {
            String[] token = line.split("\t");
            hme.put(token[0], Double.valueOf(token[1]));
        }

        while ((line = br.readLine()) != null) {
            String[] token = line.split("\t");
            String type = token[2];
            String item = token[3];
            String result = token[4];
            Double duration = Double.valueOf(token[5]);

            Double threashold = 0.0;
            if (type.equals("QUIZJET")) {
                threashold = hmp.get(item);
            } else if (type.equals("WEBEX")) {
                threashold = hme.get(item);
            } else {
                System.out.println("Error in type: " + type);
            }

            String label = "";

            if (type.equals("QUIZJET")) {
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
            } else if (type.equals("WEBEX")) {
                if (duration < threashold) {
                    label = "e";
                } else {
                    label = "E";
                }
            }

            fw.write(token[0] + "\t" + token[1] + "\t" + token[2] + "\t" + token[3] + "\t" + label + "\n");

        }

        br.close();
        brp.close();
        bre.close();
        fw.close();
    }

    public static void dividDurationLabelLine() throws FileNotFoundException, IOException {
        File fileIn = new File(path + "QuizjetWebexLine.txt");
        BufferedReader br = new BufferedReader(new FileReader(fileIn));

        File fileInp = new File(path + "timesMeidanProblem.txt");
        BufferedReader brp = new BufferedReader(new FileReader(fileInp));

        File fileIne = new File(path + "WebexMeidan.txt");
        BufferedReader bre = new BufferedReader(new FileReader(fileIne));

        File fileOut = new File(path + "label.txt");
        FileWriter fw = new FileWriter(fileOut);

        HashMap<String, Double> hmp = new HashMap<String, Double>();
        HashMap<String, Double> hme = new HashMap<String, Double>();

        String line = "";

        while ((line = brp.readLine()) != null) {
            String[] token = line.split("\t");
            hmp.put(token[0], Double.valueOf(token[1]));
        }

        while ((line = bre.readLine()) != null) {
            String[] token = line.split("\t");
            hme.put(token[0], Double.valueOf(token[1]));
        }

        while ((line = br.readLine()) != null) {
            String[] token = line.split("\t");
            String user = token[0];
            String sesn = token[1];
            String type = token[2];
            String khat = token[3];
            String activityName = token[4];
            String result = token[5];
            Double duration = Double.valueOf(token[6]);

            Double threashold = 0.0;
            if (type.equals("QUIZJET")) {
                threashold = hmp.get(activityName);
            } else if (type.equals("WEBEX")) {
                threashold = hme.get(activityName + "@" + khat);
            } else {
                System.out.println("Error in type: " + type);
            }

            String label = "";

            if (type.equals("QUIZJET")) {
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
            } else if (type.equals("WEBEX")) {
                if (duration < threashold) {
                    label = "e";
                } else {
                    label = "E";
                }
            }

            fw.write(user + "\t" + sesn + "\t" + type + "\t" + khat + "\t" + activityName + "\t" + label + "\n");

        }

        br.close();
        brp.close();
        bre.close();
        fw.close();
    }

    public static void dividDurationLabelLineTopic() throws FileNotFoundException, IOException {
        File fileIn = new File(path + "DatasetMerged.txt");
        BufferedReader br = new BufferedReader(new FileReader(fileIn));

        File fileInp = new File(path + "ProblemMedian.txt");
        BufferedReader brp = new BufferedReader(new FileReader(fileInp));

        File fileIne = new File(path + "ExampleMean.txt");
        BufferedReader bre = new BufferedReader(new FileReader(fileIne));

        File fileOut = new File(path + "label.txt");
        FileWriter fw = new FileWriter(fileOut);

        HashMap<String, Double> hmp = new HashMap<String, Double>();
        HashMap<String, Double> hme = new HashMap<String, Double>();

        String line = "";

        while ((line = brp.readLine()) != null) {
            String[] token = line.split("\t");
            hmp.put(token[0], Double.valueOf(token[1]));
        }

        while ((line = bre.readLine()) != null) {
            String[] token = line.split("\t");
            hme.put(token[0], Double.valueOf(token[1]));
        }

        while ((line = br.readLine()) != null) {
            String[] token = line.split("\t");
            String user = token[0];
            String smstr = token[1];
            String sesn = token[2];
            String type = token[3];
            String activityName = token[4];
            String topic = token[5];
            String result = token[6];
            Double duration = Double.valueOf(token[7]);

            Double threashold = 0.0;
            if (type.equals("QUIZJET")) {
                threashold = hmp.get(activityName);
            } else if (type.equals("WEBEX")) {
                threashold = hme.get(activityName);
            } else {
                System.out.println("Error in type: " + type);
            }

            String label = "";

            if (type.equals("QUIZJET")) {
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
            } else if (type.equals("WEBEX")) {
                if (duration < threashold) {
                    label = "e";
                } else {
                    label = "E";
                }
            }

            fw.write(user + "\t" + smstr + "\t" + sesn + "\t" + type + "\t" + activityName + "\t" + topic + "\t" + label + "\n");

        }

        br.close();
        brp.close();
        bre.close();
        fw.close();
    }

    public static void findSequence() throws FileNotFoundException, IOException {
        File fileIn = new File(path + "label.txt");
        BufferedReader br = new BufferedReader(new FileReader(fileIn));

        File fileOut = new File(path + "LabelSequence.txt");
        FileWriter fw = new FileWriter(fileOut);

        ArrayList<String> seq = new ArrayList<String>();

        String line = br.readLine();
        String[] token = line.split("\t");

        String user = token[0].trim();
        String session = token[1].trim();
        String type = token[2].trim();
        String activityname = token[3].trim();
        String durationseconds = token[4].trim();

        String oldUser = user;
        String oldSession = session;
        String oldActivityname = activityname;

        seq.add(durationseconds);

        while ((line = br.readLine()) != null) {
            oldUser = user;
            oldSession = session;
            oldActivityname = activityname;

            token = line.split("\t");
            user = token[0].trim();
            session = token[1].trim();
            type = token[2].trim();
            activityname = token[3].trim();
            durationseconds = token[4].trim();

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

        br.close();
        fw.close();
    }

    public static void findSequenceTopic() throws FileNotFoundException, IOException {
        File fileIn = new File(path + "label.txt");
        BufferedReader br = new BufferedReader(new FileReader(fileIn));

        File fileOut = new File(path + "LabelSequence.txt");
        FileWriter fw = new FileWriter(fileOut);

        ArrayList<String> seq = new ArrayList<String>();

        String line = br.readLine();
        String[] token = line.split("\t");

        String user = token[0].trim();
//        String smstr = token[1].trim();
        String session = token[2].trim();
        String type = token[3].trim();
        String activityname = token[4].trim();
        String topic = token[5].trim();
        String durationseconds = token[6].trim();

        String oldUser = user;
        String oldSession = session;
        String oldActivityname = activityname;
        String oldTopic = topic;

        seq.add(durationseconds);

        while ((line = br.readLine()) != null) {
            oldUser = user;
            oldSession = session;
            oldActivityname = activityname;
            oldTopic = topic;

            token = line.split("\t");
            user = token[0].trim();
            session = token[2].trim();
            type = token[3].trim();
            activityname = token[4].trim();
            topic = token[5].trim();
            durationseconds = token[6].trim();

            if (!user.equals(oldUser)) {
                seq.add("_");
                fw.write(oldUser + "\t_" + mergeSequence(seq) + "\n");
                seq = new ArrayList<String>();
            } else if (!session.equals(oldSession)) {
                seq.add("_");
            } else if (!activityname.equals(oldActivityname) && type.equals("QUIZJET")) {
                seq.add("_");
            } else if (!topic.equals(oldTopic)) {
                seq.add("_");
            }

            seq.add(durationseconds);

        }

        fw.write(oldUser + "\t_" + mergeSequence(seq) + "_\n");

        br.close();
        fw.close();
    }

    public static void findSequenceLine() throws FileNotFoundException, IOException {
        File fileIn = new File(path + "label.txt");
        BufferedReader br = new BufferedReader(new FileReader(fileIn));

        File fileOut = new File(path + "LabelSequencePlus.txt");
        FileWriter fw = new FileWriter(fileOut);

        ArrayList<String> seq = new ArrayList<String>();

        String line = br.readLine();
        String[] token = line.split("\t");

        String user = token[0].trim();
        String session = token[1].trim();
        String type = token[2].trim();
        String khat = token[3].trim();
        String activityname = token[4].trim();
        String durationseconds = token[5].trim();

        String oldUser = user;
        String oldSession = session;
        String oldType = type;
        String oldKhat = khat;
        String oldActivityname = activityname;

        seq.add(durationseconds);

        while ((line = br.readLine()) != null) {
            oldUser = user;
            oldSession = session;
            oldType = type;
            oldKhat = khat;
            oldActivityname = activityname;

            token = line.split("\t");
            user = token[0].trim();
            session = token[1].trim();
            type = token[2].trim();
            khat = token[3].trim();
            activityname = token[4].trim();
            durationseconds = token[5].trim();

            if (!user.equals(oldUser)) {
                seq.add("_");
                fw.write(oldUser + "\t_" + mergeSequence(seq) + "\n");
                seq = new ArrayList<String>();
            } else if (!session.equals(oldSession)) {
                seq.add("_");
            } else if (!type.equals(oldType)) {
                seq.add("+");
            } else if (!activityname.equals(oldActivityname) && type.equals("QUIZJET")) {
                seq.add("+");
            } else if (activityname.equals(oldActivityname) && type.equals("WEBEX")) {
                continue;
            }

            seq.add(durationseconds);
        }

        fw.write(oldUser + "\t_" + mergeSequence(seq) + "_\n");

        br.close();
        fw.close();
    }

    public static void findSequenceSession() throws FileNotFoundException, IOException {
        File fileIn = new File(path + "label.txt");
        BufferedReader br = new BufferedReader(new FileReader(fileIn));

        File fileOut = new File(path + "LabelSequence.txt");
        FileWriter fw = new FileWriter(fileOut);

        ArrayList<String> seq = new ArrayList<String>();

        String line = br.readLine();
        String[] token = line.split("\t");

        String user = token[0].trim();
        String session = token[2].trim();
        String type = token[3].trim();
        String activityname = token[4].trim();
        String topic = token[5].trim();
        String duration = token[6].trim();

        String oldUser = user;
        String oldSession = session;

        seq.add(duration);

        while ((line = br.readLine()) != null) {
            oldUser = user;
            oldSession = session;

            token = line.split("\t");

            user = token[0].trim();
            session = token[2].trim();
            type = token[3].trim();
            activityname = token[4].trim();
            topic = token[5].trim();
            duration = token[6].trim();

            if (!user.equals(oldUser)) {
                seq.add("_");
                fw.write(oldUser + "\t_" + mergeSequence(seq) + "\n");
                seq = new ArrayList<String>();
            } else if (!session.equals(oldSession)) {
                seq.add("_");
            }

            seq.add(duration);
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

    public static void findSequenceTopicComplexity() throws FileNotFoundException, IOException {
        File fileIn = new File(path + "label.txt");
        BufferedReader br = new BufferedReader(new FileReader(fileIn));

        File fileQ = new File(path + "questionFilter.txt");
        BufferedReader brq = new BufferedReader(new FileReader(fileQ));

        File fileE = new File(path + "example.txt");
        BufferedReader bre = new BufferedReader(new FileReader(fileE));

        File fileEasy = new File(path + "LabelSequenceEasy.txt");
        FileWriter fwe = new FileWriter(fileEasy);

        File fileMedium = new File(path + "LabelSequenceMedium.txt");
        FileWriter fwm = new FileWriter(fileMedium);

        File fileHard = new File(path + "LabelSequenceHard.txt");
        FileWriter fwh = new FileWriter(fileHard);

        HashMap<String, String> hmq = new HashMap<String, String>();
        HashMap<String, String> hme = new HashMap<String, String>();

        ArrayList<String> seqe = new ArrayList<String>();
        ArrayList<String> seqm = new ArrayList<String>();
        ArrayList<String> seqh = new ArrayList<String>();

        String line = "";

        while ((line = bre.readLine()) != null) {
            String[] token = line.split("\t");
            hme.put(token[0].toLowerCase(), token[2].toLowerCase());
        }

        while ((line = brq.readLine()) != null) {
            String[] token = line.split("\t");
            hmq.put(token[0].toLowerCase(), token[2].toLowerCase());
        }

        line = br.readLine();
        String[] token = line.split("\t");

        String user = token[0].trim();
        String session = token[2].trim();
        String type = token[3].trim();
        String activityname = token[4].trim();
        String topic = token[5].trim();
        String durationseconds = token[6].trim();

        String oldUser = user;
        String oldSession = session;
        String oldActivityname = activityname;
        String oldTopic = topic;

        String dif = "";

        if (type.startsWith("W")) {
            dif = hme.get(activityname.toLowerCase());
        } else if (type.startsWith("Q")) {
            dif = hmq.get(activityname.toLowerCase());
        } else {
            System.out.println("Invalid type in: " + type);
        }

        if (dif.startsWith("l")) {
            seqe.add(durationseconds);
        } else if (dif.startsWith("m")) {
            seqm.add(durationseconds);
        } else if (dif.startsWith("h")) {
            seqh.add(durationseconds);
        } else {
            System.out.println("Invalid complexity in: " + dif);
        }

        while ((line = br.readLine()) != null) {
            oldUser = user;
            oldSession = session;
            oldActivityname = activityname;
            oldTopic = topic;

            token = line.split("\t");
            user = token[0].trim();
            session = token[2].trim();
            type = token[3].trim();
            activityname = token[4].trim();
            topic = token[5].trim();
            durationseconds = token[6].trim();

            if (!user.equals(oldUser)) {
                seqe.add("_");
                seqm.add("_");
                seqh.add("_");
                fwe.write(oldUser + "\t_" + mergeSequence(seqe) + "\n");
                fwm.write(oldUser + "\t_" + mergeSequence(seqm) + "\n");
                fwh.write(oldUser + "\t_" + mergeSequence(seqh) + "\n");
                seqe = new ArrayList<String>();
                seqm = new ArrayList<String>();
                seqh = new ArrayList<String>();
            } else if (!session.equals(oldSession)) {
                if (dif.startsWith("l")) {
                    seqe.add("_");
                }
                if (dif.startsWith("m")) {
                    seqm.add("_");
                }
                if (dif.startsWith("h")) {
                    seqh.add("_");
                }

            } else if (!activityname.equals(oldActivityname) && type.equals("QUIZJET")) {
                if (dif.startsWith("l")) {
                    seqe.add("_");
                }
                if (dif.startsWith("m")) {
                    seqm.add("_");
                }
                if (dif.startsWith("h")) {
                    seqh.add("_");
                }

            } else if (!topic.equals(oldTopic)) {
                if (dif.startsWith("l")) {
                    seqe.add("_");
                }
                if (dif.startsWith("m")) {
                    seqm.add("_");
                }
                if (dif.startsWith("h")) {
                    seqh.add("_");
                }
            }

            if (type.startsWith("W")) {
                dif = hme.get(activityname.toLowerCase());
            } else if (type.startsWith("Q")) {
                dif = hmq.get(activityname.toLowerCase());
            } else {

                System.out.println("Invalid type in: " + type);
            }

            
            if (dif.startsWith("l")) {
                seqe.add(durationseconds);
            } else if (dif.startsWith("m")) {
                seqm.add(durationseconds);
            } else if (dif.startsWith("h")) {
                seqh.add(durationseconds);
            } else {
                System.out.println(line);
                System.out.println("Invalid complexity in: " + dif);
            }

        }

        fwe.write(oldUser + "\t_" + mergeSequence(seqe) + "_\n");
        fwm.write(oldUser + "\t_" + mergeSequence(seqm) + "_\n");
        fwh.write(oldUser + "\t_" + mergeSequence(seqh) + "_\n");

        br.close();
        brq.close();
        bre.close();
        fwe.close();
        fwm.close();
        fwh.close();
    }

    public static void findAverageExamplePerLine() throws FileNotFoundException, IOException {
        File fileIn = new File(path + "exampleLineTime.txt");
        BufferedReader br = new BufferedReader(new FileReader(fileIn));

        File fileOut = new File(path + "exampleLineTimeAvg.txt");
        FileWriter fw = new FileWriter(fileOut);

        ArrayList<String> exmp = new ArrayList<String>();
        ArrayList<ArrayList<Double>> time = new ArrayList<ArrayList<Double>>();

        String line = "";

        while ((line = br.readLine()) != null) {
            String[] token = line.split("\t");
            String exLine = token[1] + "@" + token[0];
            Double tm = Double.valueOf(token[2]);

            if (exmp.contains(exLine)) {
                int ix = exmp.indexOf(exLine);
                time.get(ix).add(tm);
            } else {
                exmp.add(exLine);
                ArrayList<Double> temp = new ArrayList<Double>();
                temp.add(tm);
                time.add(temp);
            }
        }

        for (int i = 0; i < exmp.size(); i++) {
            Double avg = Util.takeAverage(time.get(i));
            fw.write(exmp.get(i) + "\t" + avg + "\n");
        }

        br.close();
        fw.close();
    }

    public static void findAverageExamplePerExample() throws FileNotFoundException, IOException {
        File fileIn = new File(path + "exampleLineTimeAvg.txt");
        BufferedReader br = new BufferedReader(new FileReader(fileIn));

        File fileOut = new File(path + "exampleLineTimeAvgPerExample.txt");
        FileWriter fw = new FileWriter(fileOut);

        ArrayList<String> exmp = new ArrayList<String>();
        ArrayList<ArrayList<Double>> time = new ArrayList<ArrayList<Double>>();

        String line = "";
        while ((line = br.readLine()) != null) {
            String[] token = line.split("\t");
            Double tm = Double.valueOf(token[1]);
            String example = token[0].split("@")[0];

            if (exmp.contains(example)) {
                int ix = exmp.indexOf(example);
                time.get(ix).add(tm);
            } else {
                exmp.add(example);
                ArrayList<Double> temp = new ArrayList<Double>();
                temp.add(tm);
                time.add(temp);
            }
        }

        for (int i = 0; i < exmp.size(); i++) {
            Double sum = Util.takeSum(time.get(i));
            fw.write(exmp.get(i) + "\t" + sum + "\n");
        }

        br.close();
        fw.close();
    }

    public static void mergeExample() throws FileNotFoundException, IOException {
        File fileIn = new File(path + "Dataset.txt");
        BufferedReader br = new BufferedReader(new FileReader(fileIn));

        File fileOut = new File(path + "DatasetMerged.txt");
        FileWriter fw = new FileWriter(fileOut);

        ArrayList<String> exmp = new ArrayList<String>();
        ArrayList<ArrayList<Double>> time = new ArrayList<ArrayList<Double>>();

        String line = "";

        line = br.readLine();
        System.out.println("LINE 1: " + line);

        String[] token = line.split("\t");
        String oldType = token[3];
        String oldActivity = token[4];

        String type = "";
        String activity = "";

        exmp.add(line);

        while ((line = br.readLine()) != null) {
            token = line.split("\t");
            type = token[3];
            activity = token[4];

            System.out.println(line);

            if (type.equals("WEBEX")) {
                if (oldType.equals("WEBEX")) {
                    if (oldActivity.equals(activity)) {
                        exmp.add(line);
                    } else {
                        fw.write(Util.Merge(exmp) + "\n");
                        exmp = new ArrayList<String>();
                        exmp.add(line);
                    }
                } else {
                    exmp.add(line);
                }
            } else if (type.equals("QUIZJET") && oldType.equals("WEBEX") && exmp.size() > 0) {
                fw.write(Util.Merge(exmp) + "\n");
                exmp = new ArrayList<String>();
                fw.write(line + "\n");
            } else {
                fw.write(line + "\n");
            }

            oldType = type;
            oldActivity = activity;
        }

        br.close();
        fw.close();
    }

    public static void sortDiff() throws FileNotFoundException, IOException {
        File fileIn = new File("C:\\Project\\EDU\\files\\2013\\example\\Topic\\3\\statNormal1ClusterStdv20new.txt");
        BufferedReader br = new BufferedReader(new FileReader(fileIn));

        File fileInO = new File("C:\\Project\\EDU\\files\\2013\\example\\Topic\\3\\Order20.txt");
        BufferedReader bro = new BufferedReader(new FileReader(fileInO));

        File fileOutO = new File("C:\\Project\\EDU\\files\\2013\\example\\Topic\\3\\Order20New.txt");
        FileWriter fwo = new FileWriter(fileOutO);

        File fileOut1 = new File("C:\\Project\\EDU\\files\\2013\\example\\Topic\\3\\C0.txt");
        FileWriter fw1 = new FileWriter(fileOut1);

        File fileOut2 = new File("C:\\Project\\EDU\\files\\2013\\example\\Topic\\3\\C1.txt");
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

        line = br.readLine();
        token = line.split("\t");
        System.out.println(token.length);
        for (int i = 0; i < token.length; i++) {
            avg1.add(Double.valueOf(token[i]));
        }

        line = br.readLine();
        token = line.split("\t");
        for (int i = 0; i < token.length; i++) {
            stdv1.add(Double.valueOf(token[i]));
        }
        line = br.readLine();
        token = line.split("\t");
        for (int i = 0; i < token.length; i++) {
            avg2.add(Double.valueOf(token[i]));
        }
        line = br.readLine();
        token = line.split("\t");
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

}
