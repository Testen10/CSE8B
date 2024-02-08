class Debug{
    public static void main(String[] args){
        if (args.length <= 0) System.out.println("No keyword is given"); 
        else if (args[0].substring(0,2).equals("--")) {
            String prefix = args[0].substring(2,args[0].length());
            

            int count = 0;
            for (int i = 1; i < args.length; i++) {
                if(args[i].length()>=prefix.length()){
                    if(prefix.equals(args[i].substring(0,prefix.length()))) count += 1;
                }
                
            }
            System.out.println("The answer is " + count);
        }
        else System.out.println("Write a keyword with the prefix --");
    }
}
