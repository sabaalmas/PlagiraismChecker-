package new_helpers;

import java.io.*;
import java.nio.file.Path;
import java.util.Collections;
import java.util.TreeMap;

public class HelperFunctions {

    public static String readFile(String fileName) {
        // TODO Auto-generated method stub
        FileInputStream filestream;

        String result = "";

        try {
            filestream = new FileInputStream(fileName);
            BufferedReader br = new BufferedReader(new InputStreamReader(filestream));
            String str = br.readLine();

            while (str != null) {
                result = result + str;
                str = br.readLine();
            }

            br.close();

        } catch (FileNotFoundException e) {
             // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;
    }

    /**
     * lcsLength - calculates the lcs_length of the two programs
     * @param s1 file 1 in one String
     * @param s2 file 2 in one String
     * @return length of of longest common string found
     */
    private static int lcsLength(String s1, String s2) {
        // if either string is blank, return 0
        if (s1.length() == 0 || s2.length() == 0) {
            return 0;
        }

        int[][] lcsArr = new int[2][s2.length() + 1];    // instantiate lcsArr with 2 rows
        char[] charArr1 = s1.toCharArray();     // s1 converted to char array
        char[] charArr2 = s2.toCharArray();     // s2 converted to char array

        for (int i = 1; i <= charArr1.length; i++) {
            for (int j = 1; j <= charArr2.length; j++) {
                // if two chars are the same:
                if (charArr1[i - 1] == charArr2[j - 1]) {
                    if (i % 2 == 0) // row is even (row 1 is previous row)
                        lcsArr[0][j] = lcsArr[1][j - 1] + 1;
                    else  // row is odd (row 0 is previous row)
                        lcsArr[1][j] = lcsArr[0][j - 1] + 1;
                } else {
                    if (i % 2 == 0) // row is even (row 1 is previous row)
                        lcsArr[0][j] = Integer.max(lcsArr[1][j], lcsArr[0][j - 1]);
                    else  // row is odd (row 0 is previous row)
                        lcsArr[1][j] = Integer.max(lcsArr[0][j], lcsArr[1][j - 1]);
                }
            }
        }
        // return last position in arr
        return lcsArr[1][s2.length()];
    }

    /**
     * plagiarismScore - uses lcsLength() to calculate the plagiarism score for the two files
     * @param filename1 file 1
     * @param filename2 file 2
     * @return percentage of plagiarism between two files
     */
    private static double plagiarismScore(String filename1, String filename2) {
        // read both files into Strings
        String s1 = readFile(filename1);
        String s2 = readFile(filename2);
        // return plagiarismScore as a double percentage
        return 200.0 * lcsLength(s1, s2) / (s1.length() + s2.length());
    }

    /**
     * plagiarismChecker - should do a pairwise comparison of all the files in the array
     * and print a neatly formatted report listing any suspicious pairs. The report should
     * include their plagiarism score. For purposes of this assignment, any pair of programs
     * is suspicious if the plagiarism score is greater than or equal to the specified threshold.
     * @param filenames array of files to search through
     * @param threshold value at which to report plagiarism
     */
    public static void plagiarismChecker(String[] filenames, double threshold) {
        // Scores and corresponding files are stored in reverse order TreeMap (reverse so largest score is first)
        TreeMap<Double, String[]> map = new TreeMap<>(Collections.reverseOrder());

        for (int i = 0; i < filenames.length; i++) {
            // compare to rest of arr
            for (int j = i + 1; j < filenames.length; j++) {
                if (!filenames[i].equals(filenames[j])) {
                    // get plagiarism score between two files
                    double score = plagiarismScore(filenames[i], filenames[j]);
                    if (score >= threshold) {
                        // create list of both files
                        String[] list = {filenames[i], filenames[j]};
                        // add plagiarism score and list of files to map
                        map.put(score, list);
                    }
                }
            }
        }

        // if map is empty, all plagiarism scores found were less than the threshold
        if (map.isEmpty()){
            System.out.println();
            System.out.println("No plagiarism scores found above threshold of " + threshold);
        }
        else {
            // print formatted header
            System.out.printf("%n%-15s%-15s%-15s%n", "File1", "File2", "Score");
            // print formatted table
            for (Double score : map.keySet()) {
                System.out.printf("%-15s", map.get(score)[0]);
                System.out.printf("%-15s", map.get(score)[1]);
                System.out.printf("%-2.2f%n", score);
            }
        }
    }


}
