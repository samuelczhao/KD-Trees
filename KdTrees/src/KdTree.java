import java.awt.Color;
import java.awt.Point;
import java.util.logging.Level;

public class KdTree implements PointContainer
{
	private Node root;
	private int size;

	private class Node
	{
		public Point2D point;
		public RectHV rect;
		public Node left;
		public Node right;

		public Node(Point2D point, RectHV rect)
		{
			this.point = point;
			this.rect = rect;
		}
	}

	public KdTree()
	{
		size = 0;
	}

	public boolean isEmpty()
	{
		return size == 0;
	}

	public int size()
	{
		return size;
	}

	public void insert(Point2D point)
	{
		if (root == null)
		{
			root = new Node(point, new RectHV(0.0, 0.0, 1.0, 1.0));
			return;
		}

		insert(point, root, 1);
	}

	private boolean atLeft(Node n, Point2D point, int l)
	{
		if (l % 2 == 0)
			return point.x() <= n.point.x();
		return point.y() <= n.point.y();
	}

	private void insert(Point2D point, Node root, int level)
	{
		if (atLeft(root, point, level))
		{
			if (root.left == null && !root.point.equals(point))
			{
				RectHV rect, r = root.rect;
				if (level % 2 == 0)
					rect = new RectHV(r.xmin(), r.ymin(), root.point.x(), r.ymax());
				else
					rect = new RectHV(r.xmin(), r.ymin(), r.xmax(), root.point.y());
				root.left = new Node(point, rect);
			}
			else
				insert(point, root.left, ++level);

		}
		else
		{
			if (root.right == null && !root.point.equals(point))
			{
				RectHV rect, r = root.rect;
				if (level % 2 == 0)
					rect = new RectHV(root.point.x(), r.ymin(), r.xmax(), r.ymax());
				else
					rect = new RectHV(r.xmin(), root.point.y(), r.xmax(), r.ymax());
				root.right = new Node(point, rect);
			}
			else
				insert(point, root.right, ++level);

		}
	}

	public boolean contains(Point2D p)
	{
		throw new UnsupportedOperationException();
	}

	public void draw(Canvas canvas)
	{
		canvas.setPenColor(Color.BLACK);
		canvas.setPenRadius(.01);

		// For points, use these calls:
		//    canvas.point(put your parameters here)
		//
		// For dividing lines, use these calls:
		//    canvas.setPenRadius(.002);
		//	  canvas.setPenColor(Color.RED); (for vertical dividing lines)
		//	  canvas.setPenColor(Color.BLUE); (for horizontal dividing lines)
		//    canvas.line(put your parameters here)
	}

	private void draw(Node root)
	{

	}

	public Iterable<Point2D> range(RectHV rect)
	{
		SET<Point2D> thing = new SET<Point2D>();
		range(root, rect, thing);
		return thing;
	}

	private void range(Node node, RectHV rect, SET<Point2D> thing)
	{
		if (node != null && node.rect.intersects(rect))
		{
			if (rect.contains(node.point))
			{
				thing.add(node.point);
			}

			range(node.left, rect, thing);
			range(node.right, rect, thing);
		}
	}

	public Point2D nearest(Point2D p)
	{
		throw new UnsupportedOperationException();
	}
}