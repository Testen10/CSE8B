class Drill4{
    /*
     phaseOfWater
        takes an int
        returns "vapor" if the number is greater than or equal to 100
                "liquid" if the number is less than 100 and greater than 0
                "solid" if the number is less than or equal to 0.
     */

    String phaseOfWater(int num){
        if(num>=100){return "vapor";}
        else if(num>0){return "liquid";}
        else{return "solid";}
    }

    /*
     maxProduct
        takes three ints
        returns the largest product between any two of them
     */

    int maxProduct(int num1, int num2, int num3){
        return Math.max(Math.max(num1*num2,num1*num3),num2*num3);
    }

    /*
     pipeVolume
        takes three doubles
            inner radius of a cylindrical pipe
            the outer radius
            the height of the pipe

        It returns the volume of the pipe as a double.
        
        Assume both inputs are positive and that the first number is smaller than the second (inner radius always smaller).
        Recall that the volume of a cylinder is πr2h.
        You can use Math.PI, a field conveniently defined for us by Java, as a constant for the value of π.
     */

    double pipeVolume(double innerR, double outerR, double height){
        return height*Math.PI*(Math.pow(outerR, 2)-Math.pow(innerR, 2));
    }

    String pOW_test1 = phaseOfWater(-100); // expected: "solid"
    String pOW_test2 = phaseOfWater(99); // expected: "liquid"
    String pOW_test3 = phaseOfWater(101); // expected: "vapor"

    int mP_test1 = maxProduct(-20,4,5); // expected 20
    int mP_test2 = maxProduct(0,45,-2); // expected 0
    int mP_test3 = maxProduct(-1,-5,-2); // expected 10

    double pV_test1 = pipeVolume(5,20,10); // expected: 3750*pi (= 11780.972451)
    double pV_test2 = pipeVolume(10,11,5); // expected: 105*pi (= 329.867228627)
    double pV_test3 = pipeVolume(1.5,3.4,23); // expected: 214.13*pi (= 672.709234913)
}