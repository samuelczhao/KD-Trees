import java.awt.Color;

public class KdTree implements PointContainer
{
    public boolean isEmpty()
    {
    	throw new UnsupportedOperationException();
    }
    
    public int size()
    {
    	throw new UnsupportedOperationException();
    }
    
    public void insert(Point2D p)
    {
    	throw new UnsupportedOperationException();
    }
    
    public boolean contains(Point2D p)
    {
    	throw new UnsupportedOperationException();
    }

    public void draw(Canvas canvas)
    {
    	// Use canvas to draw your points and dividing lines
    	//
    	// For points, use these calls:
        //    canvas.setPenRadius(.01);
    	//    canvas.setPenColor(Color.BLACK);
    	//    canvas.point(put your parameters here)
    	//
    	// For dividing lines, use these calls:
    	//    canvas.setPenRadius(.002);
    	//	  canvas.setPenColor(Color.RED); (for vertical dividing lines)
    	//	  canvas.setPenColor(Color.BLUE); (for horizontal dividing lines)
    	//    canvas.line(put your parameters here)


        // Don't forget to remove this!
    	throw new UnsupportedOperationException();
    }
    
    public Iterable<Point2D> range(RectHV rect)
    {
    	throw new UnsupportedOperationException();
    }      
    
    public Point2D nearest(Point2D p)
    {
    	throw new UnsupportedOperationException();
    }     
}