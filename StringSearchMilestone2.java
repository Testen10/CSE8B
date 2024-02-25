import java.nio.file.*;
import java.io.IOException;
import java.util.*;
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

// contains query
class ContainsQuery{
    String s;
    boolean not;
    ContainsQuery(String s){
        this.s = s.replace("'","");
    }

    boolean matches(String compare){
        if(compare.contains(s)){return true;}
        return false;
    }
}

class StringSearch{
    public static void main(String[] args){
        if(args.length==0){ System.out.println("Not enough command inputted"); return; }
        
        ContainsQuery cQ = null;
        String q_command = "";

        //query exist
        if(args.length == 2){ 
            if(args[1].substring(0,"contains=".length()).equals("contains=")){
                q_command = "contains";
                cQ = new ContainsQuery(args[1].substring("contains=".length()+1,args[1].length()));
            }
        }

        FileHelper fileHelper = new FileHelper();
        for(String elem:fileHelper.getLines(args[0])){
            if(cQ!=null){ if(!cQ.matches(elem)){continue;} }
            System.out.println(elem);
        }
    }
}
