package lab2;
import shapes.*;

public class lab2 {
	  private static int index;


	    private static double maxArea(Shape[] sh) {
	        double max = sh[0].getArea();
	        int ind = 0;
	        for (int i = 1; i < sh.length; i++) {
	            if (sh[i].getArea() > max) {
	                max = sh[i].getArea();
	                ind = i;
	            }
	        }
	        index = ind;
	        return sh[ind].getArea();
	    }


	    public static void main(String[] args) {
	        Shape[] shapes;
	        shapes = new Shape[10];

	        shapes[0] = new Circle(new pointt(20, 10), 7);
	        shapes[1] = new Circle(new pointt(20, 10), 4);
	        shapes[2] = new Circle(new pointt(20, 10), 18);

	        shapes[3] = new Rectangle(new pointt(3, 7), new pointt(4, 3));
	        shapes[4] = new Rectangle(new pointt(5, 4), new pointt(5, 3));
	        shapes[5] = new Rectangle(new pointt(2, 1), new pointt(4, 7));
	        shapes[6] = new Rectangle(new pointt(11, 98), new pointt(96, 11));

	        shapes[7] = new Triangle(new pointt(5, 7), new pointt(9, 21), new pointt(12, 8));
	        shapes[8] = new Triangle(new pointt(1, 1), new pointt(5, 10), new pointt(10, 7));
	        shapes[9] = new Triangle(new pointt(5, 5), new pointt(10, 8), new pointt(15, 5));
	        double size = maxArea(shapes);

	        System.out.println("The maximum figure has index: " + index + ", its size is = " + size);
	    }
}
