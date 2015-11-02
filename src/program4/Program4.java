package program4;

import java.io.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Program4 {

    double avgCompsFound;
    double avgCompsNotFound;
    int wordsFound;
    int wordsNotFound;
    int comparisonsFound;
    int comparisonsNotFound;
    
     
     MyLinkedList[] list = new MyLinkedList[26];


    /**pre: none
     * post: none
     * @param args
     */
    public static void main(String[] args) {
        Program4 Test = new Program4();

        Test.readDictionary();
        Test.readAnalyzeFile();
        Test.output();
    }

    /**pre: none
     * post: none
     * Reads dictionary file and adds each word to appropriate linked list
     * based off the first letter.
     */
    public void readDictionary() {
        File d = new File("dictionary.txt");
        for (int i = 0; i < list.length; i++) {
            list[i] = new MyLinkedList<String>();
        } 
        try {
            Scanner input = new Scanner(d);
            while (input.hasNext()) {
                String s = input.next();
                s = s.trim();
                s.toLowerCase();
                list[(int)s.charAt(0) - 97].add(s);
            }
            input.close();
        } catch (IOException e) {
        }
    }

    /**pre: none
     * post: none
     *reads the target file and compares it against the words in the appropriate
     * linked list. 
     */
    public void readAnalyzeFile() {

        try {
            boolean temp = false;
            int comparisons = 0;

            FileInputStream inf = new FileInputStream(new File("oliver.txt"));
            char let;
            String str = "";
            int n = 0;
            while ((n = inf.read()) != -1) {
                let = (char) n;
                if (Character.isLetter(let)) {
                    str += Character.toLowerCase(let);
                }
                if ((Character.isWhitespace(let) || let == '-') && !str.isEmpty()) {
                    str = "";
                }
                temp = list[(int)str.charAt(0) - 97].contains(str);
                comparisons = list[(int)str.charAt(0) - 97].comps(str);
            }
            inf.close();
            int wordsFound = 0;
            int wordsNotFound = 0;
            int comparisonsFound = 0;
            int comparisonsNotFound = 0;

            if (temp == true) {
                wordsFound++;
                comparisonsFound += comparisons;
            } else {
                wordsNotFound++;
                comparisonsNotFound += comparisons;
            }

            avgCompsFound = wordsFound / comparisonsFound;
            avgCompsNotFound = wordsFound / comparisonsNotFound;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**pre: none
     * post: none
     * outputs data
     */
    public void output() {
        System.out.println("Number of words found: " + wordsFound);
        System.out.println("Number of words found: " + wordsNotFound);
        System.out.println("----------");
        System.out.println("Number of words found: " + comparisonsFound);
        System.out.println("Number of words found: " + comparisonsNotFound);
        System.out.println("----------");
        System.out.println("Average number of string comparisons for words that were found: " + avgCompsFound);
        System.out.println("Average number of string comparisons for words that were not found: " + avgCompsNotFound);
    }

}
