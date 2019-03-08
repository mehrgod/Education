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
import education.Util;

/**
 *
 * @author mirza
 */
public class Stable {
    
//    static String path = "C:\\Project\\EDU\\files\\2013\\revised\\clean\\Stability\\midTerm\\";
    static String path = "C:\\Project\\EDU\\files\\2013\\example\\Topic\\stability\\complexity\\hard\\";
    static int min = 60;
    
    public static void main(String[] args) throws IOException{
//        checkLength();
//        removeLessThan(min);
//        testShuffle();
        shuffle();
//        splitHalf();
//        separateDistance();
    }

    public static void checkLength() throws FileNotFoundException, IOException {
        File fileIn = new File(path + "Sequence.txt");
        BufferedReader br = new BufferedReader(new FileReader(fileIn));

//        File fileOut = new File("");
//        FileWriter fw = new FileWriter(fileOut);

        String line = "";
        while ((line = br.readLine()) != null) {
            String[] token = line.split("\t")[1].split("_");
            System.out.println(token.length);
        }
        
        br.close();
    }
    
    public static void removeLessThan(int n) throws FileNotFoundException, IOException{
        File fileIn = new File(path + "Sequence.txt");
        BufferedReader br = new BufferedReader(new FileReader(fileIn));

        File fileOut = new File(path + "Sequence" + n + ".txt");
        FileWriter fw = new FileWriter(fileOut);

        String line = "";
        while ((line = br.readLine()) != null) {
            String[] token = line.split("\t")[1].split("_");
            int l = token.length;
            System.out.println(l);
            if (l > n){
                fw.write(line + "\n");
            }
        }

        br.close();
        fw.close();
    }
    
    public static void shuffle() throws FileNotFoundException, IOException{
        File fileIn = new File(path + "LabelSequenceHardFilter.txt");
        BufferedReader br = new BufferedReader(new FileReader(fileIn));

        File fileOut1 = new File(path + "SequenceP1.txt");
        FileWriter fw1 = new FileWriter(fileOut1);
        
        File fileOut2 = new File(path + "SequenceP2.txt");
        FileWriter fw2 = new FileWriter(fileOut2);
                
        String line = "";
        while ((line = br.readLine()) != null){
            String id = line.split("\t")[0];
            String[] token = line.split("\t")[1].split("_");
            String[] shuffled = Util.shuffleArray(token);
            
            String out = "";
            for (int i = 0; i < shuffled.length / 2; i++){
                out = out + "_" + shuffled[i];
            }
            fw1.write(id + "\t" + out + "\n");
            
            out = "";
            for (int i = shuffled.length / 2; i < shuffled.length; i++){
                out = out + "_" + shuffled[i];
            }
            fw2.write(id + "\t" + out + "\n");            
        }
        
        br.close();
        fw1.close();
        fw2.close();
        
    }
    
    public static void splitHalf() throws FileNotFoundException, IOException{
        File fileIn = new File(path + "Sequence.txt");
        BufferedReader br = new BufferedReader(new FileReader(fileIn));

        File fileOut1 = new File(path + "SequenceP1.txt");
        FileWriter fw1 = new FileWriter(fileOut1);
        
        File fileOut2 = new File(path + "SequenceP2.txt");
        FileWriter fw2 = new FileWriter(fileOut2);
                
        String line = "";
        while ((line = br.readLine()) != null){
            String id = line.split("\t")[0];
            String[] token = line.split("\t")[1].split("_");
            
            String out = "";
            for (int i = 1; i < token.length / 2; i++){
                out = out + "_" + token[i];
            }
            fw1.write(id + "\t" + out + "\n");
            
            out = "";
            for (int i = token.length / 2; i < token.length; i++){
                out = out + "_" + token[i];
            }
            fw2.write(id + "\t" + out + "\n");            
        }
        
        br.close();
        fw1.close();
        fw2.close();
        
    }
    
    public static void testShuffle(){
        String s = "1,2,3,4,5,6,7,8,9,0";
        String[] token = s.split(",");
        String[] shuffle = Util.shuffleArray(token);
        for (String str: shuffle){
            System.out.println(str);
        }
    }
    
    public static void separateDistance() throws FileNotFoundException, IOException{
        File fileIn = new File(path + "otherDistance.txt");
        BufferedReader br = new BufferedReader(new FileReader(fileIn));

        File fileOut = new File(path + "otherDistanceAll.txt");
        FileWriter fw = new FileWriter(fileOut);       
                
        String line = "";
        while ((line = br.readLine()) != null){
            String[] token = line.split(",");
            for (String s: token){
                fw.write(s + "\n");
            }
        }
        
        br.close();
        fw.close();
        
    }
}
