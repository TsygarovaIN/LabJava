package shapes;

public class Triangle implements Polygon {

	private Point A;
	private Point B;
	private Point C;

	private Point center;
	private int angle;

	public Triangle(Point a, Point b, Point c) {
	    A = a;
	    B = b;
	    C = c;
	    this.angle = 0;

	    setCenter();
	  }
	  
	  private void setCenter() {
		    this.center = new pointt((A.getX() + B.getX() + C.getX()) / 3,
		        (A.getY() + B.getY() + C.getY()) / 3);
		  }

	  public Point getCenter() {
	    return center;
	  }

	  public float getPerimeter() {
	    var p1 = (Math.sqrt(Math.pow(A.getX() - B.getX(), 2) + Math.pow(A.getY() - B.getY(), 2)));
	    var p2 = (Math.sqrt(Math.pow(B.getX() - C.getX(), 2) + Math.pow(B.getY() - C.getY(), 2)));
	    var p3 = (Math.sqrt(Math.pow(C.getX() - A.getX(), 2) + Math.pow(C.getY() - A.getY(), 2)));
	    var p = p1 + p2 + p3;

	    return (float) p;
	  }

	  public float getArea() {
	    double p = this.getPerimeter();
	    p = p / 2;

	    var p1 = (Math.sqrt(Math.pow(A.getX() - B.getX(), 2) + Math.pow(A.getY() - B.getY(), 2)));
	    var p2 = (Math.sqrt(Math.pow(B.getX() - C.getX(), 2) + Math.pow(B.getY() - C.getY(), 2)));
	    var p3 = (Math.sqrt(Math.pow(C.getX() - A.getX(), 2) + Math.pow(C.getY() - A.getY(), 2)));

	    return (float) (Math.sqrt(p - p1) * (p - p2) * (p - p3));
	  }

	  public int getRotation() {
	    return angle;
	  }

	  public void rotate(int rotationAngle) {
	    int newAngle = angle + rotationAngle;
	    var angleInRads = Math.toRadians(newAngle);

	    float newCentreX1 = (float) (center.getX() + (A.getX() - center.getX()) * Math.cos(angleInRads) - (A.getY() - center.getY()) * Math.sin(angleInRads));
	    float newCentreY1 = (float) (center.getY() + (A.getY() - center.getY()) * Math.cos(angleInRads) + (A.getX() - center.getX()) * Math.sin(angleInRads));

	    float newCentreX2 = (float)(center.getX() + (B.getX() - center.getX()) * Math.cos(angleInRads) - (B.getY() - center.getY()) * Math.sin(angleInRads));
	    float newCentreY2 = (float) (center.getY() + (B.getY() - center.getY()) * Math.cos(angleInRads) + (B.getX() - center.getX()) * Math.sin(angleInRads));

	    float newCentreX3 = (float) (center.getX() + (C.getX() - center.getX()) * Math.cos(angleInRads) - (C.getY() - center.getY()) * Math.sin(angleInRads));
	    float newCentreY3 = (float) (center.getY() + (C.getY() - center.getY()) * Math.cos(angleInRads) + (C.getX() - center.getX()) * Math.sin(angleInRads));


	    A = new pointt(newCentreX1, newCentreY1);
	    B = new pointt(newCentreX2, newCentreY2);
	    C = new pointt(newCentreX3, newCentreY3);

	    angle = newAngle;
	  }
}