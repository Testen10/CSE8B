import tester.*;

class WholeInteger implements Number{
    int n;
    WholeInteger(int n){
        this.n = n;
    }
    public int numerator(){
        return n;
    }
    public int denominator(){
        return 1;
    }
    public Number add(Number other){
        double temp = this.toDouble()+other.toDouble();
        Number ans;
        if(isInteger(temp)){ ans = new WholeInteger((int)temp); }
        else{  ans = new Fraction(this.numerator()+other.numerator(), other.denominator()); }

        return ans;
    }
    public Number multiply(Number other){
        double temp = this.toDouble()*other.toDouble();
        Number ans;
        if(isInteger(temp)){ ans = new WholeInteger((int)temp); }
        else{ 
            int temp_n = this.numerator()*other.numerator();
            int temp_d = this.denominator()*other.denominator();
            ans = new Fraction(
                temp_n/LCD(temp_n, temp_d),
                temp_d/LCD(temp_n, temp_d)); }

        return ans;
    }
    public Number getMax(Number other){
        if(this.toDouble()>=other.toDouble()){return this;}
        return other;
    }
    public String toString(){
        return Integer.toString(n);
    }
    public double toDouble(){
        return (double) n;
    }
    public boolean isInteger(double num) {
        return num % 1 == 0.0;
    }
    public int LCD(int num1, int num2){
        int ans = 1;
        for(int temp=2;temp<Math.min(num1, num2);temp++){
            if(num1%temp==0&&num2%temp==0){ ans = temp; }
        }
        return ans;
    }
}

class Fraction implements Number{
    int n;
    int d;
    Fraction(int n, int d){
        this.n = n;
        this.d = d;
    }
    public int numerator(){
        return n;
    }
    public int denominator(){
        return d;
    }
    public Number add(Number other){
        double temp = this.toDouble()+other.toDouble();
        Number ans;
        if(isInteger(temp)){ ans = new WholeInteger((int)temp); }
        else{  ans = new Fraction(this.numerator()+other.numerator(), other.denominator()); }

        return ans;
    }
    public Number multiply(Number other){
        double temp = this.toDouble()*other.toDouble();
        Number ans;
        if(isInteger(temp)){ ans = new WholeInteger((int)temp); }
        else{ 
            int temp_n = this.numerator()*other.numerator();
            int temp_d = this.denominator()*other.denominator();
            ans = new Fraction(
                temp_n/LCD(temp_n, temp_d),
                temp_d/LCD(temp_n, temp_d)); }

        return ans;
    }
    public Number getMax(Number other){
        if(this.toDouble()>=other.toDouble()){return this;}
        return other;
    }
    public String toString(){
        return Double.toString(this.toDouble());
    }
    public double toDouble(){
        return (double) n/d;
    }
    public boolean isInteger(double num) {
        return num % 1 == 0.0;
    }
    public int LCD(int num1, int num2){
        int ans = 1;
        for(int temp=2;temp<Math.min(num1, num2);temp++){
            if(num1%temp==0&&num2%temp==0){ ans = temp; }
        }
        return ans;
    }
}
interface Number {
    int numerator();
    int denominator();
    Number add(Number other);
    Number multiply(Number other);
    Number getMax(Number other);
    String toString();
    double toDouble();
  }

class ExamplesNumber{
    /*
        The result of 0.1 + 0.2 + 0.3 using built-in double arithmetic in Java
        The result of 0.1 + (0.2 + 0.3) using built-in double arithmetic in Java
        The result of (1) using your exact fractions, showing the result via toString()
        The result of (2) using your exact fractions, showing the result via toString()
    */

    Number num1 = new Fraction(1,10);
    Number num2 = new Fraction(2,10);
    Number num3 = new Fraction(3,10);

    double tst_1 = 0.1+0.2+0.3;
    double tst_2 = 0.1+(0.2+0.3);
    Number num4 = num1.add(num2).add(num3);
    String tst_3 = num4.toString();
    Number num5 = num1.add(num2.add(num3));
    String tst_4 = num5.toString();
}