//https://howtodoinjava.com/java/io/java-read-file-to-string-examples/

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class MarkdownParse {

    public static ArrayList<String> getLinks(String markdown) {
        ArrayList<String> toReturn = new ArrayList<>();
        // find the next [, then find the ], then find the (, then read link upto next )
        int currentIndex = 0;
        while(currentIndex < markdown.length()) {
            int openBracket;
            //handle starting file with link
            if(markdown.charAt(0) == ('[') && currentIndex == 0){
                openBracket = 0;
            } else {
                openBracket = markdown.indexOf("[", currentIndex);

            }
            //handle images
            //if next open bracket is part of an image,
            if(openBracket>0 && markdown.charAt(openBracket-1) == '!'){
                //skip to next ')' character
                currentIndex = markdown.indexOf(")", currentIndex);
                continue;
            }
            int closeBracket = markdown.indexOf("]", openBracket);
            int openParen = markdown.indexOf("(", closeBracket);
            int closeParen = markdown.indexOf(")", openParen);
            toReturn.add(markdown.substring(openParen + 1, closeParen));
            currentIndex = closeParen + 1;
            //handle errors of forever loop with empty lines at the end
            if(markdown.indexOf("[", currentIndex) == -1){
                break;
            }

        }

        return toReturn;
    }


    public static void main(String[] args) throws IOException {
        Path fileName = Path.of(args[0]);
        String content = Files.readString(fileName);
        ArrayList<String> links = getLinks(content);
	    System.out.println(links);
    }
}