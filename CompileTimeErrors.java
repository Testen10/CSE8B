class CompileTimeErrors{
    public static String checkNumber(double myNumber){
        if (myNumber > 0){
            return "The number " + myNumber + " is positive.";
        }
        else if (myNumber < 0){ 
            return "The number " + myNumber + " is negative."; 
        }
        return "The number is zero.";
    }

    public static void main(String[] args){

        // test1
        String answer1 = checkNumber(1);
        System.out.println(answer1);
        
        // test2
        String answer2 = checkNumber(0); 
        System.out.println(answer2);

        // test3
        String answer3 = checkNumber(-10.5); 
        System.out.println(answer3);
    }
    
}
