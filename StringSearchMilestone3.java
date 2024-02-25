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

interface Query{
    boolean matches(String s);
}

//length query
class LengthQuery implements Query{
    int num;
    boolean not;
    LengthQuery(String s, boolean not){
        num = Integer.parseInt(s);
        this.not = not;
    }

    public boolean matches(String compare){
        if(compare.length()==num&&!not
        || compare.length()!=num&&not){return true;}
        return false;
    }
}
// greater query
class GreaterQuery implements Query{
    int num;
    boolean not;
    GreaterQuery(String s,boolean not){
        num = Integer.parseInt(s);
        this.not = not;
    }

    public boolean matches(String compare){
        if(compare.length()>num&&!not
        || !(compare.length()>num)&&not){return true;}
        return false;
    }
}
// less query
class LessQuery implements Query{
    int num;
    boolean not;
    LessQuery(String s, boolean not){
        num = Integer.parseInt(s);
        this.not = not;
    }

    public boolean matches(String compare){
        if(compare.length()<num&&!not
        ||!(compare.length()<num)&&not){return true;}
        return false;
    }
}
// contains query
class ContainsQuery implements Query{
    String s;
    boolean not;
    ContainsQuery(String s,boolean not){
        this.s = s.replace("'","");
        this.not = not;
    }

    public boolean matches(String compare){
        if(compare.contains(s)&&!not
        ||!compare.contains(s)&&not){return true;}
        return false;
    }
}
// starts query
class StartsQuery implements Query{
    String s;
    boolean not;
    StartsQuery(String s,boolean not){
        this.s = s.replace("'","");
        this.not = not;
    }

    public boolean matches(String compare){
        if(compare.substring(0,s.length()).equals(s)&&!not
        ||!(compare.substring(0,s.length()).equals(s))&&not){return true;}
        return false;
    }
}
// ends query
class EndsQuery implements Query{
    String s;
    boolean not;
    EndsQuery(String s,boolean not){
        this.s = s.replace("'","");
        this.not = not;
    }

    public boolean matches(String compare){
        if(compare.substring(compare.length()-s.length(),compare.length()).equals(s)&&!not
        ||!(compare.substring(compare.length()-s.length(),compare.length()).equals(s))&&not){return true;}
        
        return false;
    }
}


class StringSearch{
    static Query readQuery(String q){
        Query query = null;
        String q_command = "";
        boolean querynot = false;
        String[] q_command_list = new String[]{"length=", // 0
                                               "greater=", // 1
                                               "less=", // 2
                                               "contains=", // 3 
                                               "starts=", // 4
                                               "ends="}; //5

        
        if(q.substring(0,4).equals("not(")
            && q.substring(q.length()-1,q.length()).equals(")")){
                querynot = true;
                q = q.substring(4,q.length()-1);
        }

        //length
        if(q.substring(0,Math.min(q_command_list[0].length(),q.length())).equals(q_command_list[0])){
            q_command = q_command_list[0];
            query = new LengthQuery(q.substring(q_command.length(),q.length()),querynot);
        }
        //greater
        else if(q.substring(0,Math.min(q_command_list[1].length(),q.length())).equals(q_command_list[1])){
            q_command = q_command_list[1];
            query = new GreaterQuery(q.substring(q_command.length(),q.length()),querynot);
        }
        //less
        else if(q.substring(0,Math.min(q_command_list[2].length(),q.length())).equals(q_command_list[2])){
            q_command = q_command_list[2];
            query = new LessQuery(q.substring(q_command.length(),q.length()),querynot);
        }
        //contains
        else if(q.substring(0,Math.min(q_command_list[3].length(),q.length())).equals(q_command_list[3])){
            q_command = q_command_list[3];
            query = new ContainsQuery(q.substring(q_command.length(),q.length()),querynot);
        }
        //starts
        else if(q.substring(0,Math.min(q_command_list[4].length(),q.length())).equals(q_command_list[4])){
            q_command = q_command_list[4];
            query = new StartsQuery(q.substring(q_command.length(),q.length()),querynot);
        }
        //ends
        else if(q.substring(0,Math.min(q_command_list[5].length(),q.length())).equals(q_command_list[5])){
            q_command = q_command_list[5];
            query = new EndsQuery(q.substring(q_command.length(),q.length()),querynot);
        }
        else{System.out.println("Unvalid command in query"); }
        return query;
    }
    public static void main(String[] args){
        if(args.length==0){ System.out.println("Not enough command inputted"); return; }
        
        boolean q_not = false;
        Query query = null;
        boolean query_exist = false;

        if(args.length == 2){ 
            if(args[1].substring(0,4).equals("not(")
                && args[1].substring(args[1].length()-1,args[1].length()).equals(")")){
                    q_not = true;
                    args[1] = args[1].substring(4,args[1].length()-1);
                }
            query_exist = true;
            query = readQuery(args[1]);
        }
    
        FileHelper fileHelper = new FileHelper();
        for(String elem:fileHelper.getLines(args[0])){
            if(query==null&&query_exist){return;}
            if(query!=null){
                if(!query.matches(elem)&&!q_not || query.matches(elem)&&q_not){continue;}
            }    
            System.out.println(elem);
        }
    }
}
