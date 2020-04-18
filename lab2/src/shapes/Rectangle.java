package shapes;
public class Rectangle implements Polygon {

	private Point a;
	private Point b;
	private Point center;
	private int angle;
	
	public Rectangle (Point A, Point B)
	{
		a = A;
		b = B;
	}
	
	public float getArea()
	{
		return Math.abs(a.getX() - b.getX()) * Math.abs(a.getY() - b.getY());
	}
	public float getPerimeter()
	{
		return 2 * (Math.abs(a.getX() - b.getX()) + Math.abs(a.getY() - b.getY()));
	}
	
	public Point getCenter() 
	{
	   return center;
	}
	
	public int getRotation()
	{
		return angle;
	}
	 
	public void rotate(int rotationAngle) 
	{
		int angle = this.angle + rotationAngle;
	    var angleInRads = Math.toRadians(angle);

	    float x1 = (float) (center.getX() + (a.getX() - center.getX()) * Math.cos(angleInRads) - (a.getY()
	            - center.getY()) * Math.sin(angleInRads));
	    float y1 = (float) (center.getY() + (a.getY() - center.getY()) * Math.cos(angleInRads) + (a.getX()
	            - center.getX()) * Math.sin(angleInRads));
	    float x2 = (float) (center.getX() + (b.getX() - center.getX()) * Math.cos(angleInRads) - (b.getY()
	            - center.getY()) * Math.sin(angleInRads));
	    float y2 = (float) (center.getY() + (b.getY() - center.getY()) * Math.cos(angleInRads) + (b.getX()
	            - center.getX()) * Math.sin(angleInRads));

	    a = new pointt(x1, y1);
	    b = new pointt(x2, y2);

	    this.angle = angle;
	  }	
}