import tester.*;

class Triplet{
    /*
    In a file called TripletSelect.java,
    add a class Triplet with fields a, b, and c of type int (add a constructor to initalize those fields).
 */
    int a,b,c;
    Triplet(int a, int b, int c){
        this.a = a;
        this.b = b;
        this.c = c;
    }

    int getB(){
        return b;
    }
}

class TripletSelect{
    /*
     In a class called TripletSelect,
     write a method getBs that takes an array of Triplet
     returns an array of int that contains the b field of all the Triplets in the array given as an argument
     
     Write at least 4 checkExpect tests for it to cover interesting cases.
     */
    int[] getBs(Triplet[] trip_arr){
        int[] ans_arr = new int[trip_arr.length];

        for(int loc = 0;loc<trip_arr.length;loc++){
            ans_arr[loc] = trip_arr[loc].getB();
        }

        return ans_arr;
    }

    void testMethods(Tester t) {
        Triplet temp1 = new Triplet(0, 0, 0);
        Triplet temp2 = new Triplet(1, 2, 4);
        Triplet temp3 = new Triplet(1, 2, 5);
        t.checkExpect(this.getBs(new Triplet[0]),new int[0]); //1 - empty Triplet array
        t.checkExpect(this.getBs(new Triplet[]{temp1}),new int[]{0}); //2 - Triplet array length 1
        t.checkExpect(this.getBs(new Triplet[]{temp1, temp2}),new int[]{0,2}); //3 - Triplet array length 2
        t.checkExpect(this.getBs(new Triplet[]{temp1, temp2, temp3}),new int[]{0,2,2}); //4 - Triplet array length 3

    }
}