import java.nio.file.*;
import java.io.IOException;
class FileHelper {
    static String[] getLines(String path) {
        try {
            return Files.readAllLines(Paths.get(path)).toArray(String[]::new);
        }
        catch(IOException e) {
            System.err.println("Error reading file " + path + ": " + e);
            return new String[]{"Error reading file " + path + ": " + e};
        }
    }
}
class StringSearch{
    /*
 The main method should expect from 1 to 3 command-line arguments:

    $ java StringSearch "<file>" "<query>" "<transform>"
 The overall goal of StringSearch is to take a file of text, search for lines in the file based on some criteria, then print out the matching lines after transforming them somehow.

 The <thing> syntax means, as usual, that we will be describing what kinds of syntax can go in each position in more detail.

 <file> should be a path to a file. We’ve included two for you to test on with examples below. It is recommended to make a few of your own files and try them out as well.
  <query> describes criteria for which lines in the file to print.
 <transform> describes how to change each line in the file before printing.

 */
    public static void main(String[] args){
        if (args.length==0){ System.out.println("Not enough command inputted"); return; }

        FileHelper fileHelper = new FileHelper();
        for(String elem:fileHelper.getLines(args[0])){
            System.out.println(elem);
        }
    
    }
}
