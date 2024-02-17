class PositiveMinMaxAvg{
    /*
    write a class with a main method
     :that expects all the command-line arguments to be appropriate inputs to Double.parseDouble.
     : It should print the average of the maximum and the minimum positive arguments from the command-line arguments that,
        when parsed as doubles, are strictly greater than 0.
        If there are less than 2 numbers in the array that are greater than 0, it should produce 0.
        
        For example, let’s say your arguments are -1.5, 2.5, 3.5, 5, then your answer will be (2.5 + 5)/2 = 3.75 (2.5 is the minimum positive, and 5 is the maximum positive).

        Try out at least six different uses of PositiveMinMaxAvg at the command line with different lists of numbers that convince you it works for all of the important cases.

    Note: You can assume that the value you will get for autograder test cases are in the range -10 to 10.
 */
    public static void main(String[] args){
        System.out.println(getPosAvg(args));
    }

    public static double getPosAvg(String[] str_arr){
        double max_val = Double.MIN_VALUE;
        double min_val = Double.MAX_VALUE;
        int cnt = 0;

        for(String elem:str_arr){
            double temp = Double.parseDouble(elem);
            if(temp>0){
                max_val = Double.max(max_val, temp);
                min_val = Double.min(min_val, temp);
                cnt++;
            }
        }
        if(cnt<2){ return 0; }
        return (min_val+max_val)/2;
    }
}

/* test
    1. java PositiveMinMaxAvg
    --> 0.0
    2. java PositiveMinMaxAvg -1.5 2.5 3.5 5  
    --> 3.75
    3. java PositiveMinMaxAvg -2 -3 -5 0            
    --> 0.0
    4. java PositiveMinMaxAvg 10 -2 2
    --> 6.0
    5. java PositiveMinMaxAvg -10 -9 -8 -7 0 0.1 0.01 0.00001
    --> 0.050005
    6. java PositiveMinMaxAvg 2
    --> 0.0
 */