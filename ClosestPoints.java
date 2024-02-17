class Point {
    int x;
    int y;
    Point(int x, int y) {
      this.x = x;
      this.y = y;
    }
    boolean belowLeftOf(Point other) {
      return this.x <= other.x && this.y <= other.y;
    }
    boolean aboveRightOf(Point other) {
      return this.x >= other.x && this.y >= other.y;
    }
    double distance(Point other) {
      return Math.sqrt(Math.pow(this.x - other.x, 2) + Math.pow(this.y - other.y, 2));
    }
    String printPoint(){
        return "("+Integer.toString(x)+", "+Integer.toString(y)+")";
    }
}

public class ClosestPoints {
    /*
     Write a class called ClosestPoints
        with a main method that expects to get six command line arguments,
        representing the x and y coordinates of three points.
        
    For example:
        $ java ClosestPoints 0 0 3 4 3 900
    
        Would represent the points (0, 0), (3, 4), and (3, 900).
    Your program should print out the pair of points that are closest to one another out of the three points, using the format below:
    $ java ClosestPoints 0 0 3 4 3 900
    The closest points are (0, 0) and (3, 4) at distance 5.0
     */
    public static void main(String[] args){
        if(args.length!=6){return;}
        Point p1 = new Point(Integer.parseInt(args[0]),Integer.parseInt(args[1]));
        Point p2 = new Point(Integer.parseInt(args[2]),Integer.parseInt(args[3]));
        Point p3 = new Point(Integer.parseInt(args[4]),Integer.parseInt(args[5]));

        double dist_12 = p1.distance(p2);
        double dist_23 = p2.distance(p3);
        double dist_13 = p1.distance(p3);

        String message = "";
        if(dist_12<=dist_13&&dist_12<=dist_23){
            message = "The closest points are "
                        + p1.printPoint()
                        + " and "
                        + p2.printPoint()
                        + " at distance "
                        + Double.toString(dist_12);
                    }
        else if(dist_23<=dist_12&&dist_23<=dist_13){
            message = "The closest points are "
                        + p2.printPoint()
                        + " and "
                        + p3.printPoint()
                        + " at distance "
                        + Double.toString(dist_23);
        }
        else{
            message = "The closest points are "
                        + p1.printPoint()
                        + " and "
                        + p3.printPoint()
                        + " at distance "
                        + Double.toString(dist_13);
        }

        System.out.println(message);
    }
}
