import java.awt.Color;

public class PointSET implements PointContainer
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
        canvas.setPenColor(Color.BLACK);
        canvas.setPenRadius(.01);
        
        // TODO: Insert code here to call the point() method on canvas
        // for each point that has been inserted into your PointSET

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