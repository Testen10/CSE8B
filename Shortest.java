public class Shortest {
    public static void main(String[] args){
        if(args.length>0){
            String temp=args[0];
            for(int loc=1;loc<args.length;loc++){
                if(temp.length()>args[loc].length()){temp = args[loc];}
            }
            System.out.println(temp);
        }
    }
}
