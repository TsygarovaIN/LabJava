package shapes;

public class pointt implements Point {
      private float x;
      private float y;
      
     public pointt(float xx, float yy)
      {
    	  x = xx;
    	  y = yy;
      }
     public pointt() 
     {
    	 x = 0;
    	 y = 0;
     }
     public pointt(pointt p)
     {
    	 x = p.x;
    	 y = p.y;
     }
      
      public float getX() { return x;}
      public float getY() { return y;}
}
