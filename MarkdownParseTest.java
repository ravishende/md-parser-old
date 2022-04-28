import static org.junit.Assert.*; //imports all of the Assert JUnit functions

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.junit.*; //imports JUnit
public class MarkdownParseTest { //defines the MarkdownParseTest class
    @Test //marks code as a test for compiler to run
    public void addition() { //creates function for test
        assertEquals(2, 1 + 1); //checks that 2 = 1+1 using function from JUnit
    }

    @Test
    public void checkBasic() throws IOException{
        Path fileName = Path.of("test-file.md");
        String content = Files.readString(fileName);
        ArrayList<String> links = MarkdownParse.getLinks(content);
        assertEquals(List.of("https://something.com", "some-page.html"), links);
    }

    @Test
    public void image1() throws IOException{
        Path fileName = Path.of("test1.md");
        String content = Files.readString(fileName);
        ArrayList<String> links = MarkdownParse.getLinks(content);
        assertEquals(List.of("https://google.com", "https://apple.com"), links);
    }

    @Test
    public void image2() throws IOException{
        Path fileName = Path.of("test3.md");
        String content = Files.readString(fileName);
        ArrayList<String> links = MarkdownParse.getLinks(content);
        assertEquals(List.of("https://drive.google.com", "https://www.microsoft.com/en-us/"), links);
    }

    @Test
    public void lineAtEnd() throws IOException{
        Path fileName = Path.of("test2.md");
        String content = Files.readString(fileName);
        ArrayList<String> links = MarkdownParse.getLinks(content);
        assertEquals(List.of("https://java.com/en/"), links);
    }

    @Test
    public void linkAtStart() throws IOException{
        Path fileName = Path.of("test4.md");
        String content = Files.readString(fileName);
        ArrayList<String> links = MarkdownParse.getLinks(content);
        assertEquals(List.of("https://google.com", "https://apple.com", "https://www.microsoft.com/en-us/"), links);
    }
}

