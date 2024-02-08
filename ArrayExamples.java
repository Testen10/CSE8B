import tester.Tester;

class Pair{
    int a;
    int b;

    Pair(int a, int b){
        this.a = a;
        this.b = b;
    }
}

class ArrayExamples {
    String reverseJoinWith(String[] str_list, String sep){
        /*
        Input 
        String array / String separator
        Output
        String that contains the strings from the array separated by that separator, but in the reverse order
        */
        String[] reverse_list = new String[str_list.length];
        
        for(int idx = str_list.length-1;idx>=0;idx--){ reverse_list[idx] = str_list[str_list.length-1-idx];}
        
        String ans = String.join(sep, reverse_list);
        return ans;
    }
    boolean logicalOr(boolean[] bool_lst){
        /*
        Input:
         boolean array
        Output:
        false if all elements in the array are false
        else it true
        If the array is empty, the function should return false.
         */

        if(bool_lst.length == 0){return false;}
        for(boolean elem:bool_lst){
            if(!elem){return false;}
        }
        return true;
    }
    boolean allOutsideRange(double[] dbl_lst, double low, double high){
        /*
        Input:
        array of double
        two other doubles called low and high
        
        Output:
        true if all of the numbers in the array are either less than low (exclusive) or greater than high (exclusive)
        If the array is empty, this should produce true. You can assume that low ≤ high.
        */

        if(dbl_lst.length == 0){return true;}

        for(double elem:dbl_lst){
            if(elem<=high & elem>=low){ return false; }
        }
        return true;
    }
    Pair addMulti(int[] int_arr){
        /*
        Input:
        int array
        
        Output:
        a Pair obj
            a field is set to the sum of all integers in the array 
            b is set to the multiplication of all integers in the array
            
            Assume the array has at least one element, and integer sum/multiplication wouldn’t go beyond the range of an int.
        */

        int a=0;
        int b=1;
        for(int elem:int_arr){
            a += elem;
            b *= elem;
        }
        return new Pair(a,b);
    }    
    String lastSortedString(String[] str_arr){
        /*
        Input:
        str array

        Output:
            String that is the last when all strings are ordered alphabetically
        */

        String max_elem = str_arr[0];
        for(int loc=1;loc<str_arr.length;loc++){
            if(max_elem.compareTo(str_arr[loc])<0){ max_elem = str_arr[loc]; }
        }

        return max_elem;
    }
    int lookup(String[] keys, int[] values, String key){
        /*
        Input:
         an array of String called keys
         an array of int called values
         and a String called key 
         
         It should find the index in keys where the argument key appears
        Ouput:
         the int stored in values at that index
         If the key is not found, the method should return -1.
         
         You can assume that lookup will always be given two arrays of the same length, and that there are no duplicate strings in keys.
         */

        for(int loc=0;loc<keys.length;loc++){
            if(keys[loc].equals(key)){return values[loc];}
        }
        return -1;
    }
    void testMethods(Tester t) {
        // test for reverseJoinWith method
        t.checkExpect(reverseJoinWith(new String[]{"A","B","C"},"-"),"C-B-A"); // test1 - string[] with length 1
        t.checkExpect(reverseJoinWith(new String[]{"ABD","ASDFFF","EEE"}," "),"EEE ASDFFF ABD"); // test2 - string[] with length longer than 1
        t.checkExpect(reverseJoinWith(new String[]{"dasfafdasfad"},":"),"dasfafdasfad"); // test2 - string[] with single element

        // test for logicalOr method
        t.checkExpect(logicalOr(new boolean[]{}),false); // test1 - empty array
        t.checkExpect(logicalOr(new boolean[]{false,true,true,true}),false); // test2 - false included
        t.checkExpect(logicalOr(new boolean[]{true,true,true}),true); // test3 - false not included

        // test for allOutsideRange method
        t.checkExpect(allOutsideRange(new double[]{},0,1),true); // test1 - empty array
        t.checkExpect(allOutsideRange(new double[]{0.1,1.1,-1.1},-2,-1),false); // test2 - one elem is in the range
        t.checkExpect(allOutsideRange(new double[]{0.1,1.1,7},3,5),true); // test3 - all elem are out of range

        // test for addMulti method
        t.checkExpect(addMulti(new int[]{2}),new Pair(2,2)); // test1 - array with one elem
        t.checkExpect(addMulti(new int[]{2,3,5}),new Pair(10,30)); // test2 - array with only positive integer
        t.checkExpect(addMulti(new int[]{2,3,-5}),new Pair(0,-30)); // test3 - negative integer included
        t.checkExpect(addMulti(new int[]{2,3,0}),new Pair(5,0)); // test4 - 0 integer included

        // test for lastSortedString method
        t.checkExpect(lastSortedString(new String[]{"bye"}),"bye"); // test1 - array with one elem
        t.checkExpect(lastSortedString(new String[]{"bye", "hello", }),"hello"); // test2 - array with two elem
        t.checkExpect(lastSortedString(new String[]{"bye", "hello", "goodbye", "legend"}),"legend"); // test3 - array with more than two elem

        // test for lookup method
        t.checkExpect(lookup(new String[]{}, new int[]{},"UCSD"),-1); // test1 - empty
        t.checkExpect(lookup(new String[]{"UCSD", "CSE", "8B", "Assignment"}, new int[]{1,4,5,2},"UCSD"), 1); // test2 - array containing key
        t.checkExpect(lookup(new String[]{"UCSD", "CSE", "8B", "Assignment"}, new int[]{1,4,5,2},"temp"), -1); // test3 - array not containing key

    }
}