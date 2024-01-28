class C1 {
    C2 other;

    C1(C2 other) {
      this.other = other;
    }
  }
  
class C2 {
    int x;
    double y;

    C2(int x, double y) {
        this.x = x;
        this.y = y;
    }  
}

class Drill2{
    /*
     A field named first of type C2
        with its x field equal to 10, and y field should be 3.5
     A field named second of type C1
        It’s value should be a reference to a C1 object with its other field set to any C2 object
        other than the one stored in first (you can create another C2 object for this).
     A field named third of type C1.
        It’s value should be a reference to a C1 object
        with its other field the same C2 object as the one stored in the first field.
     */

    C2 first = new C2(10,3.5);

    C2 temp = new C2(9,2.5);
    C1 second = new C1(temp);

    C1 third = new C1(temp);
}