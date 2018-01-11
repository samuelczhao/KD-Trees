import java.awt.Color;

public class PointSET implements PointContainer
{  
	private SET<Point2D> set;
	
	public PointSET()
	{
		set = new SET<Point2D>();
	}
	
    public boolean isEmpty()
    {
        return set.isEmpty();
    }
    
    public int size()
    {
        return set.size();
    }
    
    public void insert(Point2D p)
    {
        set.add(p);
    }
    
    public boolean contains(Point2D p)
    {
        return set.contains(p);
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
    	SET<Point2D> thing = new SET<Point2D>();
    	for (Point2D point : set)
    	{
    		if (rect.contains(point))
    		{
    			thing.add(point);
    		}
    	}
    	
    	return thing;
    }
    
    public Point2D nearest(Point2D p)
    {
    	if (isEmpty())
    	{
    		return null;
    	}
    	
    	Point2D nearest = null;
    	double distance = 1;
    	
    	for (Point2D point : set)
    	{
    		if ((p.x() - point.x()) * (p.x() - point.x()) + (p.y() - point.y()) * (p.y() - point.y()) < distance)
    		{
    			distance = (p.x() - point.x()) * (p.x() - point.x()) + (p.y() - point.y()) * (p.y() - point.y());
    			nearest = point;
    		}
    	}
    	
    	return nearest;
    }
}