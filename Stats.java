public class Stats {
    public static void main(String[] args){
        if(args.length>0){
            String command=args[0];
            
            switch (command) {  // 입력 변수의 자료형은 byte, short, char, int, enum, String만 가능하다.
                case "--product":  
                    double ans = 1;
                    for(int idx=1;idx<args.length;idx++){ ans *= Double.parseDouble(args[idx]);}
                    System.out.println(ans);
                    break;
                case "--mean":  
                    double sum_val = 0;
                    for(int idx=1;idx<args.length;idx++){ sum_val += Double.parseDouble(args[idx]);}
                    double avg_val = sum_val/(args.length-1);
                    System.out.println(avg_val);
                    break;
                case "--total":  
                    sum_val = 0;
                    for(int idx=1;idx<args.length;idx++){ sum_val += Double.parseDouble(args[idx]);}
                    System.out.println(sum_val);
                    break;
                case "--max":  
                    double max_val = Integer.MIN_VALUE;
                    double temp;
                    for(int idx=1;idx<args.length;idx++){
                        temp = Double.parseDouble(args[idx]);
                        if(max_val<temp){max_val = temp;}}
                    System.out.println(max_val);
                    break;
                case "--min":  
                    double min_val = Integer.MAX_VALUE;
                    for(int idx=1;idx<args.length;idx++){
                        temp = Double.parseDouble(args[idx]);
                        if(min_val>temp){min_val = temp;}}
                    System.out.println(min_val);
                    break;
                default: System.out.println("Bad option "+command);
                         break;
        
            }
        }
    }
}
