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

/**
 *
 * @author mirza
 */
public class KDD {
    
    public static void main(String[] args) throws IOException{
        filterFiles();
    }

    public static void filterFiles() throws FileNotFoundException, IOException {
//        String dir = "C:\\Project\\EDU\\kdd10\\kddcup_challenge";
//        String dir = "C:\\Project\\EDU\\kdd15\\fromKDDSite\\train";
        String dir = "C:\\Project\\Dataset\\slepemapy\\2015-05-21";
        File folder = new File(dir);
        File[] listOfFiles = folder.listFiles();

        for (int i = 0; i < listOfFiles.length; i++) {
            File file = listOfFiles[i];
            if (file.isFile() && file.getName().endsWith("r.csv")) {
                File fileOut = new File(dir + "\\" + file.getName().split("\\.")[0] + "1000.txt");
                System.out.println(dir + "\\" + file.getName().split("\\.")[0] + "1000.txt");
                FileWriter fw = new FileWriter(fileOut);
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line = "";
                int c = 0;
                while ((line = br.readLine()) != null) {
                    fw.write(line + "\n");
                    if (c == 1000){
                        break;
                    }
                    c++;
                }
                fw.close();
                br.close();
                /* do somthing with content */
            }
        }
    }
    
}
