import new_helpers.HelperFunctions;
import new_helpers.LongestCommonSubsequence;
import new_helpers.Ngrams;

import java.util.Locale;
import java.util.Map;

public class MyPlagiarismChecker {
    public static void main(String[] args){
        String[] file1_list = {
                "/Users/farheenjamadar/Documents/Concordia/COMP6651/Project_NM/sample_data_and_submission/data/okay01/1.txt",
                "/Users/farheenjamadar/Documents/Concordia/COMP6651/Project_NM/sample_data_and_submission/data/okay02/1.txt",
                "/Users/farheenjamadar/Documents/Concordia/COMP6651/Project_NM/sample_data_and_submission/data/okay03/1.txt",
                "/Users/farheenjamadar/Documents/Concordia/COMP6651/Project_NM/sample_data_and_submission/data/okay04/1.txt",
                "/Users/farheenjamadar/Documents/Concordia/COMP6651/Project_NM/sample_data_and_submission/data/okay05/1.txt",
                "/Users/farheenjamadar/Documents/Concordia/COMP6651/Project_NM/sample_data_and_submission/data/okay06/1.txt",
                "/Users/farheenjamadar/Documents/Concordia/COMP6651/Project_NM/sample_data_and_submission/data/plagiarism01/1.txt",
                "/Users/farheenjamadar/Documents/Concordia/COMP6651/Project_NM/sample_data_and_submission/data/plagiarism02/1.txt",
                "/Users/farheenjamadar/Documents/Concordia/COMP6651/Project_NM/sample_data_and_submission/data/plagiarism03/1.txt",
                "/Users/farheenjamadar/Documents/Concordia/COMP6651/Project_NM/sample_data_and_submission/data/plagiarism04/1.txt",
                "/Users/farheenjamadar/Documents/Concordia/COMP6651/Project_NM/sample_data_and_submission/data/plagiarism05/1.txt",
                "/Users/farheenjamadar/Documents/Concordia/COMP6651/Project_NM/sample_data_and_submission/data/plagiarism06/1.txt",
                "/Users/farheenjamadar/Documents/Concordia/COMP6651/Project_NM/sample_data_and_submission/data/plagiarism07/1.txt"
        };

        String[] file2_list = {
                "/Users/farheenjamadar/Documents/Concordia/COMP6651/Project_NM/sample_data_and_submission/data/okay01/2.txt",
                "/Users/farheenjamadar/Documents/Concordia/COMP6651/Project_NM/sample_data_and_submission/data/okay02/2.txt",
                "/Users/farheenjamadar/Documents/Concordia/COMP6651/Project_NM/sample_data_and_submission/data/okay03/2.txt",
                "/Users/farheenjamadar/Documents/Concordia/COMP6651/Project_NM/sample_data_and_submission/data/okay04/2.txt",
                "/Users/farheenjamadar/Documents/Concordia/COMP6651/Project_NM/sample_data_and_submission/data/okay05/2.txt",
                "/Users/farheenjamadar/Documents/Concordia/COMP6651/Project_NM/sample_data_and_submission/data/okay06/2.txt",
                "/Users/farheenjamadar/Documents/Concordia/COMP6651/Project_NM/sample_data_and_submission/data/plagiarism01/2.txt",
                "/Users/farheenjamadar/Documents/Concordia/COMP6651/Project_NM/sample_data_and_submission/data/plagiarism02/2.txt",
                "/Users/farheenjamadar/Documents/Concordia/COMP6651/Project_NM/sample_data_and_submission/data/plagiarism03/2.txt",
                "/Users/farheenjamadar/Documents/Concordia/COMP6651/Project_NM/sample_data_and_submission/data/plagiarism04/2.txt",
                "/Users/farheenjamadar/Documents/Concordia/COMP6651/Project_NM/sample_data_and_submission/data/plagiarism05/2.txt",
                "/Users/farheenjamadar/Documents/Concordia/COMP6651/Project_NM/sample_data_and_submission/data/plagiarism06/2.txt",
                "/Users/farheenjamadar/Documents/Concordia/COMP6651/Project_NM/sample_data_and_submission/data/plagiarism07/2.txt"
        };

        for(int i = 0; i < file1_list.length; i++) {
           // Scanner scan = new Scanner(System.in);
            System.out.println("Path 1: " + file1_list[i]);
            String first_file =   file1_list[i];       //scan.nextLine();
            System.out.println("Path 2: " + file2_list[i]);
            String second_file =  file2_list[i];      //scan.nextLine();

            String first_file_content = String.join("", HelperFunctions.readFile(first_file).toLowerCase().split(" "));
            String second_file_content = String.join("", HelperFunctions.readFile(second_file).toLowerCase().split(" "));

            LongestCommonSubsequence lcs = new LongestCommonSubsequence();

            int length = second_file_content.length();
            if(first_file_content.length() < second_file_content.length()){
                length = first_file_content.length();
            }


            System.out.println("Similarity : With Preprocessing: " +  (lcs.lcs(first_file_content.toCharArray(), second_file_content.toCharArray(), first_file_content.toCharArray().length, second_file_content.toCharArray().length) * 100)/length);
            System.out.println("------------------------------------------------");
        }
    }
}
