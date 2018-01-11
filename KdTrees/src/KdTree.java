import java.awt.Color;
import java.util.ArrayList;

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
			size++;
			return;
		}

		insert(point, root, 0);
	}

	private boolean atLeft(Node n, Point2D point, int l)
	{
		if (l % 2 == 0)
		{
			return point.x() < n.point.x();
		}

		return point.y() < n.point.y();
	}

	private void insert(Point2D point, Node root, int level)
	{
		if (root.point.equals(point))
		{
			return;
		}
		if (atLeft(root, point, level))
		{
			if (root.left == null)
			{
				RectHV rect, r = root.rect;
				if (level % 2 == 0)
				{
					rect = new RectHV(r.xmin(), r.ymin(), root.point.x(), r.ymax());
				}
				else
				{
					rect = new RectHV(r.xmin(), r.ymin(), r.xmax(), root.point.y());
				}
				root.left = new Node(point, rect);
				size++;
			}
			else
			{
				insert(point, root.left, ++level);
			}
		}
		else
		{
			if (root.right == null)
			{

				RectHV rect, r = root.rect;
				if (level % 2 == 0)
				{
					rect = new RectHV(root.point.x(), r.ymin(), r.xmax(), r.ymax());
				}
				else
				{
					rect = new RectHV(r.xmin(), root.point.y(), r.xmax(), r.ymax());
				}
				root.right = new Node(point, rect);
				size++;
			}
			else
				insert(point, root.right, ++level);

		}
	}

	public boolean contains(Point2D p)
	{
		return contains(root, p, 0);
	}

	private boolean contains(Node root, Point2D p, int l)
	{
		if (root == null)
		{
			return false;
		}

		if (root.point.equals(p))
		{
			return true;
		}

		if (atLeft(root, p, l))
		{
			return contains(root.left, p, ++l);
		}
		else
		{
			return contains(root.right, p, ++l);
		}
	}

	public void draw(Canvas canvas)
	{
		canvas.setPenColor(Color.BLACK);
		canvas.setPenRadius(.01);
		drawV(canvas, root);
	}

	private void drawH(Canvas canvas, Node root)
	{
		if (root == null)
			return;
		canvas.setPenColor(Color.BLACK);
		canvas.setPenRadius(.01);
		canvas.point(root.point.x(), root.point.y());
		canvas.setPenRadius(.002);
		canvas.setPenColor(Color.BLUE);
		canvas.line(root.rect.xmin(), root.point.y(), root.rect.xmax(), root.point.y());
		drawV(canvas, root.left);
		drawV(canvas, root.right);
	}

	private void drawV(Canvas canvas, Node root)
	{
		if (root == null)
			return;
		canvas.setPenColor(Color.BLACK);
		canvas.setPenRadius(.01);
		canvas.point(root.point.x(), root.point.y());
		canvas.setPenRadius(.002);
		canvas.setPenColor(Color.RED);
		canvas.line(root.point.x(), root.rect.ymin(), root.point.x(), root.rect.ymax());
		drawH(canvas, root.left);
		drawH(canvas, root.right);
	}

	public Iterable<Point2D> range(RectHV rect)
	{
		ArrayList<Point2D> thing = new ArrayList<Point2D>();
		range(root, rect, thing);
		return thing;
	}

	private void range(Node node, RectHV rect, ArrayList<Point2D> thing)
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
		if (isEmpty())
		{
			return null;
		}

		return nearest(p, root, new Point2D(100, 100), 0);
	}

	private Point2D nearest(Point2D p, Node root, Point2D nearest, int l)
	{
		if (root == null)
		{
			return nearest;
		}

		if (root.rect.distanceSquaredTo(p) < nearest.distanceSquaredTo(p))
		{
			if (root.point.distanceSquaredTo(p) < nearest.distanceSquaredTo(p))
			{
				nearest = root.point;
			}
			if (atLeft(root, p, l++))
			{
				nearest = nearest(p, root.left, nearest, l);
				nearest = nearest(p, root.right, nearest, l);
			}
			else
			{
				nearest = nearest(p, root.right, nearest, l);
				nearest = nearest(p, root.left, nearest, l);
			}
		}

		return nearest;
	}
}