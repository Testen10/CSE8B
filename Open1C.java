class Open1C{

    Open1C(){}
    int sum_num(int num1, int num2){
        int ans = num1+num2;
        return ans;
    }

    int subtract_num(int num1, int num2){
        int ans = num1-num2;
        return ans;
    }

    int arg1 = 3;
    int arg2 = 5;
    int test1 = sum_num(arg1,arg2); // expected 8
    int test2 = subtract_num(arg1=arg1+3,arg2=arg2+3); // expected -2
}