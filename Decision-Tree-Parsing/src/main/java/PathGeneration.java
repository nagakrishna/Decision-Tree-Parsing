import com.google.common.base.Joiner;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Naga on 18-10-2016.
 */
public class PathGeneration {
    public static void main(String args[]) throws IOException {
        String label = "4.0";
        String inputPath = "data/DT.txt"; //Input path of decision tree model from Spark-ML-Lib
        String outputPath = "data/output.txt"; //Output path for each class label
        GeneratePathForClass(inputPath, outputPath, label);
    }

    public static void GeneratePathForClass(String inputPath, String outputPath, String label) throws IOException {
        Boolean check = false;
        List<String> tree = new ArrayList<String>();
        String sCurrentLine;
        BufferedReader br = new BufferedReader(new FileReader(inputPath));
        while ((sCurrentLine = br.readLine()) != null) {
            /*
            Check for the leaf nodes
             */
            if (sCurrentLine.contains("return")) {
                /*
                Modify the ELSE-IF to IF if we find the desired class label as return for the path
                 */
                if (sCurrentLine.contains("return " + label + ";")) {
                    if (check == true) {
                        String l = tree.get(tree.size()-1);
                        tree.remove(tree.size()-1);
                        String newl = l.replaceAll("else if", "if");
                        tree.add(newl);
                        check = false;
                    }
                    tree.add(sCurrentLine);
                }
                /*
                If leaf node belongs to other class, then remove it and corresponding path
                 */
                else {
                    tree.remove(tree.size() - 1);
                    check = true;
                }
            }
            /*
            Making output compatiable to use directly in Java methods
             */
            else {
                if (check == true) {
                    String temp = sCurrentLine.replaceAll("else if", "if");
                    tree.add(temp);
                }
                else{
                    tree.add(sCurrentLine);
                }
            }
        }
        br.close();

        Joiner joiner = Joiner.on("\n").useForNull("null");
        String output = joiner.join(tree);
        System.out.println(output);

        /*
        Saving output to a File
         */
        FileWriter fw = new FileWriter(new File(outputPath));
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(output);
        bw.close();
    }
}
