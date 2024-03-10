import tester.*;
import java.util.*;


class Point {
  int x, y;
  Point(int x, int y) {
    this.x = x;
    this.y = y;
  }
  double distance(Point other) {
    return Math.sqrt(Math.pow(this.x - other.x, 2) + Math.pow(this.y - other.y, 2));
  }
}

class PointCompare implements Comparator<Point>{
  public int compare(Point p1, Point p2){
    if(p1.x>p2.x){return 1;}
    else if(p1.x<p2.x){return -1;}
    else{
      if(p1.y>p2.y){return 1;}
      else if(p1.y<p2.y){return -1;}
      else{return 0;}
    }
  }
}

class PointDistanceCompare implements Comparator<Point>{
  public int compare(Point p1, Point p2){
    if(getDist(p1)>getDist(p2)){return 1;}
    else if(getDist(p1)<getDist(p2)){return -1;}
    else{return 0;}
  }
  public double getDist(Point p){
    return Math.pow(p.x, 2)+Math.pow(p.y, 2);
  }
}

class StringCompare implements Comparator<String>{
  public int compare(String s1, String s2){
    return s1.compareTo(s2);
  }
}

class StringLengthCompare implements Comparator<String>{
  public int compare(String s1, String s2){
    if(s1.length()>s2.length()){return 1;}
    else if(s1.length()<s2.length()){return -1;}
    else{return 0;}
  }
}

class BooleanCompare implements Comparator<Boolean>{
  public int compare(Boolean b1, Boolean b2){
    if(b1&&!b2){return 1;}
    if(!b1&&b2){return -1;}
    return 0;
  }
}

class CompareLists{
  <E> E maximum(List<E> lst,  Comparator<E> comp){
    E max = lst.get(0);
    for(E elem:lst){
      if(comp.compare(elem,max)==1){
        max = elem;
      }
    }

    return max;
  }

  <E> E maximum(E[] lst,  Comparator<E> comp){
    E max = lst[0];
    for(E elem:lst){
      if(comp.compare(elem,max)==1){
        max = elem;
      }
    }

    return max;
  }

  <E> List<E> lesserthan(List<E> lst, Comparator<E> comp,E standard){
    List<E> ans = new ArrayList<>();
    for(E elem:lst){
      if(comp.compare(elem, standard)<0){ans.add(elem);}
    }

    return ans;
  }

  <E> boolean inOrder(List<E> lst, Comparator<E> comp){
    for(int loc=0;loc<lst.size()-1;loc++){
      if(lst.get(loc)==null||
         lst.get(loc+1)==null){
          throw new IllegalArgumentException("null value in list");
      }
      if(comp.compare(lst.get(loc), lst.get(loc+1))>0){return false;}
    }
    return true;
  }

  <E> boolean inOrder(E[] lst, Comparator<E> comp){
    for(int loc=0;loc<lst.length-1;loc++){
      if(lst[loc]==null||
         lst[loc+1]==null){
          throw new IllegalArgumentException("null value in list");
      }
      if(comp.compare(lst[loc], lst[loc+1])>0){return false;}
    }
    return true;
  }

  <E> List<E> merge(Comparator<E> comp, List<E> lst1,List<E> lst2){
    List<E> ans = new ArrayList<E>();
    int loc1 = 0;
    int loc2 = 0;
    while(ans.size()<lst1.size()+lst2.size() && loc1<lst1.size() && loc2<lst2.size()){
      if(lst1.get(loc1)==null){throw new IllegalArgumentException("null value in first list");}
      if(lst2.get(loc2)==null){throw new IllegalArgumentException("null value in second list");}

      if(comp.compare(lst1.get(loc1), lst2.get(loc2))<0){ // lst1's elem is smaller than lst2's
        ans.add(lst1.get(loc1));
        loc1++;
      }
      else{
        ans.add(lst2.get(loc2));
        loc2++;
      }
    }

    while(loc1<lst1.size()){
      if(lst1.get(loc1)==null){throw new IllegalArgumentException("null value in first list");}
      ans.add(lst1.get(loc1));
      loc1++;
    }

    while(loc2<lst2.size()){
      if(lst2.get(loc2)==null){throw new IllegalArgumentException("null value in second list");}
      ans.add(lst2.get(loc2));
      loc2++;
    }

    return ans;
  }
  
  void testMethods(Tester t) {
    // maximum test
    t.checkExpect(maximum(Arrays.asList("a", "b", "c") ,new StringCompare()), "c"); //1
    t.checkExpect(maximum(Arrays.asList("a", "ab", "abc") ,new StringLengthCompare()), "abc"); //2
    t.checkExpect(maximum(Arrays.asList(new Point(0,0), new Point(1,2), new Point(2,2)) ,new PointCompare()), new Point(2,2)); //3

    // maximum for E[] test
    t.checkExpect(maximum(new String[]{"a", "b", "c"} ,new StringCompare()), "c"); //1
    t.checkExpect(maximum(new Boolean[]{true, false} ,new BooleanCompare()), true); //2
    t.checkExpect(maximum(new Point[]{new Point(0,0), new Point(2,2), new Point(2,4)} ,new PointDistanceCompare()), new Point(2,4)); //3

    // lesserthan test
    t.checkExpect(lesserthan(Arrays.asList("a", "b", "c"), new StringCompare(), "c"), Arrays.asList("a", "b")); //1
    t.checkExpect(lesserthan(Arrays.asList("a", "ab", "abc"),new StringLengthCompare(), "a"), new ArrayList<String>()); //2
    t.checkExpect(lesserthan(Arrays.asList(new Point(0,0), new Point(1,2), new Point(3,2), new Point(2,2)), new PointCompare(), new Point(1,3)), Arrays.asList(new Point(0,0), new Point(1,2))); //3

    // inOrder test
    t.checkExpect(inOrder(Arrays.asList("c", "b", "a") ,new StringCompare()), false); //1
    t.checkExpect(inOrder(Arrays.asList(false, true) ,new BooleanCompare()), true); //2
    t.checkException(new IllegalArgumentException("null value in list"),
                this, "inOrder", Arrays.asList(null, "ab", "abc"),new StringLengthCompare()); //3

    // inOrder for E[] test
    t.checkExpect(inOrder(new String[]{"a", "b", "c"} ,new StringCompare()), true); //1
    t.checkExpect(inOrder(new Point[]{new Point(3,0),new Point(2,1),new Point(2,1)} ,new PointCompare()), false); //2
    t.checkException(new IllegalArgumentException("null value in list"),
                this, "inOrder", new String[]{null, "ab", "abc"},new StringLengthCompare()); //3

    // merge test
    t.checkExpect(merge(new StringCompare(), Arrays.asList("a", "v", "z"), Arrays.asList("a", "u", "y") ), Arrays.asList("a", "a", "u", "v", "y", "z")); //1
    t.checkExpect(merge(new PointCompare(), Arrays.asList(new Point(2,1),new Point(3,0)), Arrays.asList(new Point(0,0),new Point(1,0),new Point(4,4)) ), 
                          Arrays.asList(new Point(0,0),new Point(1,0),new Point(2,1),new Point(3,0),new Point(4,4))); //2
    t.checkException(new IllegalArgumentException("null value in first list"),
                this, "merge", new StringLengthCompare(),Arrays.asList(null, "ab", "abc"),Arrays.asList("aasdfdasfb", "abc")); //3
    t.checkException(new IllegalArgumentException("null value in second list"),
                this, "merge", new StringLengthCompare(),Arrays.asList("ab", "abc"),Arrays.asList(null,"aasdfdasfb", "abc")); //4
  }
}