package new_helpers;

import java.util.Locale;

public class Ngrams {

    public static String[] sanitiseToWords(String text)
    {
        String[] characters = text.split("");

        String sanitised = "";

        boolean onSpace = true;

        int xLastCharacter = text.length() - 1;
        for(int i = 0; i <= xLastCharacter; i++) {
            if(characters[i].matches("^[A-Za-z0-9$£%]$"))
            {
                sanitised += characters[i].toLowerCase(Locale.ROOT);
                onSpace = false;
            }
            //lowercase,split, remove -, ;,
            else if(characters[i].equals("'") && i > 0 && i < xLastCharacter) {
                String surrounding = characters[i - 1] + characters[i + 1];
                if(surrounding.matches("^[A-Za-z]{2}$"))
                {
                    sanitised +="'";
                    onSpace = false;
                }
            }
            else if(!onSpace && i != xLastCharacter)
            {
                sanitised +=" ";
                onSpace = true;
            }
        }

        return sanitised.split("\\s+");
        //return new ArrayList<String>(Arrays.asList(sanitised.split("\\s+")));
    }

    public static String tokenGeneration(String content){
        String[] characters = content.split(" ");

        for(int i = 0; i < characters.length; i++){
            characters[i] = characters[i].toLowerCase();
        }

        return String.join("", characters);
    }
}
