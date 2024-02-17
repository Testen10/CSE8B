import tester.*;
import java.util.*;
class ShortStrings {
    /*
     write a method called shortStrings
        that takes an array of String and an integer n
        produces a new array of Strings that are just the strings in the array
            that have length n or lesser (the returned array might be shorter than the input array)
            
        Write at least 4 checkExpect tests for it to cover interesting cases.
     */
    String[] shortStrings(String[] str_arr, int n){
        ArrayList<String> temp_arrL = new ArrayList<String>();

        for(String elem:str_arr){
            if(elem.length()<=n){temp_arrL.add(elem);}
        }

        String[] ans_arr = new String[temp_arrL.size()];
        for(int loc=0;loc<temp_arrL.size();loc++){
            ans_arr[loc] = temp_arrL.get(loc);
        }

        return ans_arr;
    }
    void testMethods(Tester t) {
        t.checkExpect(this.shortStrings(new String[0],3),new String[0]); //1 - empty Triplet array
        t.checkExpect(this.shortStrings(new String[]{"hi", "Hello", "hihihihihihihihi"},3),new String[]{"hi"}); //2 - sample array1 with one elem satisfying requirement
        t.checkExpect(this.shortStrings(new String[]{"hi", "Hello", "12"},1),new String[0]); //3 - sample array2 with no elem satisfying requirement
        t.checkExpect(this.shortStrings(new String[]{"hi", "Hello", "12","ThatiswhyIgaveupmusic"},5),new String[]{"hi", "Hello", "12"}); //4 - sample array2 with more than one elem satisfying requirement
    }
 }
