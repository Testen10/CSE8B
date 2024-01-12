import tester.*;

class DesignRecipeExamples {
    public static int volume(int length, int width, int height){
        return length * width * height;
    }

    public static int quadraticEquation(int a, int b, int c, int x){
        return a * x * x + b * x + c;
    }

    public static double CtF_converter(double temp, boolean isCelcius){
        /* docstring
          Input
          double temp: the temperature to be converted
          boolean isCelcius: if variable temp is celcius, isCelcius is true, vice versa\
          
          Output:
          converted temperature

          How it works:
            if isCelcius is true, the method returns converted value of temp from celcius to fahrenheit using equation (9/5) * Celcius + 32
            else, it returns converted value of temp from fahrenheit to celcius using equation (Fahrenheit - 32) * 5/9
        */
        if (isCelcius){ return temp * 9 / 5 + 32; } 
        else { return (temp - 32) * 5 / 9; } 
    }

    public static int combiner(int inch, int foot){
        /* docstring
          Input
          int inch
          int foot
          
          Output:
          converted input value into sum of inches

          How it works:
            returns the combined int value of inch and foot using the fact foot = 12 inches

        */
        return inch + foot*12;
    }

    // problem1 test
    int vol_test1 = volume(2,2,2); // expected 8
    int vol_test2 = volume(20,15,6); // expected 1800

    // problem2 test
    int quad_test1 = quadraticEquation(1, 2, 1, 4); // expected 25
    int quad_test2 = quadraticEquation(4, 4, 1, 4); // expected 65

    // problem3 test
    double converter__test1 = CtF_converter(0, true); // expected 32
    double converter__test2 = CtF_converter(32, false); // expected 0

    // problem4 test
    int combiner_test1 = combiner(1, 5); // expected 61
    int combiner_test2 = combiner(0, 2); // expected 24

    // sample test where error occurs
    // int combiner_test3 = combiner(2.5, 3.0); // expected error since the type of parameter inch and foot are int, not double

}
