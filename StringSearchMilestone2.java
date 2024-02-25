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

class ContainsQuery{
    String s;
    ContainsQuery(String s){
        this.s = s;
    }

    boolean matches(String compare){
        if(compare.contains(s)){return true;}
        return false;
    }
}


class StringSearchMilestone2{
    public static void main(String[] args){
        if(args.length==0){ System.out.println("Not enough command inputted"); return; }

        ContainsQuery cQ = null;
        String q_command = "";
        int q_val_int = -1;

        if(args.length == 2){ 
            if(args[1].contains("contains")){
                q_command = "contains";
                cQ = new ContainsQuery(args[1].substring(q_command.length()+1,args[1].length()));
            }
            else{System.out.println("Unvalid command"); return;}

        }
    
        FileHelper fileHelper = new FileHelper();
        for(String elem:fileHelper.getLines(args[0])){
            if(cQ!=null){
                switch(q_command){
                    case "contains":
                        if(!cQ.matches(elem)){continue;}
                        break;
                    default:
                        return;
                    }
                }
                
            System.out.println(elem);
        }
    }
}
