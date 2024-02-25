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

interface Transform{
    String transform(String s);
}

class upperTransform implements Transform{
    public String transform(String s){ return s.toUpperCase(); }
}

class lowerTransform implements Transform{
    public String transform(String s){ return s.toLowerCase(); }
}

class firstTransform implements Transform{
    int number;
    firstTransform(int number){
        this.number = number;
    }
    public String transform(String s){
        if(s.length()<number){return s;}
        return s.substring(0, number);
    }
}

class lastTransform implements Transform{
    int number;
    lastTransform(int number){
        this.number = number;
    }
    public String transform(String s){
        if(s.length()<number){return s;}
        return s.substring(s.length()-number, s.length());
    }
}

class replaceTransform implements Transform{
    String init;
    String replace;
    replaceTransform(String init,String replace){
        this.init = init.replace("'","");
        this.replace= replace.replace("'","");
    }
    public String transform(String s){
        return s.replace(init, replace);
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
    
    static Transform readTransform(String t){
        Transform transform = null;
        String t_command = "";
        String[] t_command_list = new String[]{"upper", // 0
                                               "lower", // 1
                                               "first=", // 2
                                               "last=", // 3 
                                               "replace=" //4
                                            };

                                            //t.substring(t_command_list.length(),t.length())
        //upper
        if(t.substring(0,Math.min(t_command_list[0].length(),t.length())).equals(t_command_list[0])){
            t_command = t_command_list[0];
            transform = new upperTransform();
        }
        //lower
        else if(t.substring(0,Math.min(t_command_list[1].length(),t.length())).equals(t_command_list[1])){
            t_command = t_command_list[1];
            transform = new lowerTransform();
        }
        //first
        else if(t.substring(0,Math.min(t_command_list[2].length(),t.length())).equals(t_command_list[2])){
            t_command = t_command_list[2];
            transform = new firstTransform(Integer.parseInt(t.substring(t_command.length(),t.length())));
        }
        //last
        else if(t.substring(0,Math.min(t_command_list[3].length(),t.length())).equals(t_command_list[3])){
            t_command = t_command_list[3];
            transform = new lastTransform(Integer.parseInt(t.substring(t_command.length(),t.length())));
        }
        //replace
        else if(t.substring(0,Math.min(t_command_list[4].length(),t.length())).equals(t_command_list[4])){
            //check if its format is correct
            if(!t.contains(";")){System.out.println("Missing elements"); return transform;}
            
            String t_init = t.substring(t_command_list[4].length(),t.indexOf(";"));
            String t_replace = t.substring(t.indexOf(";")+1,t.length());
            
            transform = new replaceTransform(t_init, t_replace);
        }
        else{System.out.println("Unvalid command in transform"); }
        return transform;
    }

    static boolean matchesAll(Query[] qs, String s){
        for(Query query:qs){
            if(query==null
            || !query.matches(s) ){
                return false;
            }
        }
        return true;
    }

    static String applyAll(Transform[] ts, String s){
        String output = s;
        for(Transform transform:ts){
            if(transform == null){
                return "Unvalid transform";
            }
            output = transform.transform(output);
        }
        return output;
    }


    public static void main(String[] args){
        if(args.length==0){ System.out.println("Not enough command inputted"); return; }
        
        boolean q_not = false;
        Query[] qcommand_list = new Query[0];
        Transform[] tcommand_list = new Transform[0];;

        //query exist
        if(args.length > 1){ 
            // check if there is not statement
            String[] queryInput_arr = args[1].split("&");
            qcommand_list = new Query[queryInput_arr.length];
            for(int loc = 0;loc<queryInput_arr.length;loc++){
                qcommand_list[loc]=readQuery(queryInput_arr[loc]);
            }
        }
        //transform exist
        if(args.length > 2){ 
            // check if there is not statement
            String[] transformInput_arr = args[2].split("&");
            tcommand_list = new Transform[transformInput_arr.length];
            for(int loc = 0;loc<transformInput_arr.length;loc++){ 
                tcommand_list[loc]=readTransform(transformInput_arr[loc]);
            }
        }

        FileHelper fileHelper = new FileHelper();
        for(String elem:fileHelper.getLines(args[0])){
            if(matchesAll(qcommand_list, elem)){
                String result = applyAll(tcommand_list,elem);
                if(!(result.equals("Unvalid transform"))){
                    System.out.println(result);
                }
            }
        }
    }
}

