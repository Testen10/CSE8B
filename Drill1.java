/*
    A class named X with two fields, int f1 and double f2
    A class named Y with two fields, X field1 and String field2
    A class named Z with three fields, Y fieldY, X fieldX, double field3
 */

class X{
    int f1;
    double f2;
}

class Y{
    X field1;
    String field2;
}

class Z{
    Y fieldY;
    X fieldX;
    double field3;
}

