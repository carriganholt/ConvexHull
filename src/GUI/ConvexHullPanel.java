package GUI;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Line2D;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

public class ConvexHullPanel extends JPanel implements MouseListener {
	
	private ArrayList<Point> hullFrame = new ArrayList<Point>();
	private ArrayList<Point> points = new ArrayList<Point>();
	private ArrayList<Integer> actions = new ArrayList<Integer>();
	
	private ArrayList<ArrayList<Point>> oldInfo = new ArrayList<ArrayList<Point>>();
	
	final private int POINT = 1;
	final private int DRAW = 2;
	final private int CLEAR = 3;

	public ConvexHullPanel() {
		super();
		
		this.addMouseListener(this);
		setKeys();
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		
		// Draw points
		for (Point p : points) {
			int size = 4;
			g.drawOval(p.x - size/2, p.y - size/2, size, size);
			g.fillOval(p.x - size/2, p.y - size/2, size, size);
		}
		
		// Draw lines
		for (int i = 0; i < hullFrame.size(); i++) {
			int j = i+1;
			if (j == hullFrame.size())
				j = 0;
			g.drawLine(hullFrame.get(i).x, hullFrame.get(i).y, hullFrame.get(j).x, hullFrame.get(j).y);
		}
	}
	
	public void addPoint(double x, double y) {
		Point temp = new Point();
		temp.setLocation(x, y);
		points.add(temp);
	}

	public void clearField() {
		// Save old frame
		ArrayList<Point> oldHullFrame = new ArrayList<Point>();
		for (Point p : hullFrame) {
			oldHullFrame.add(p);
		}
		oldInfo.add(oldHullFrame);
		
		// Save old points
		ArrayList<Point> oldPoints = new ArrayList<Point>();
		for (Point p : points) {
			oldPoints.add(p);
		}
		oldInfo.add(oldPoints);
		
		// Clear old lists
		hullFrame.clear();
		points.clear();
		actions.add(CLEAR);
		repaint();
	}
	
	public void drawConvexHull() {
		for (Point p : points) {
			hullFrame.add(p);
		}
		actions.add(DRAW);
		repaint();
	}
	
	private void undo() {
		// Check if an action has been performed
		int action;
		System.out.println(actions.size());
		if (actions.size() == 0)
			return;
		else
			action = actions.remove(actions.size()-1).intValue();
		
		// Undo the last action performed
		if (action == DRAW) {
			hullFrame.clear();
		}
		else if (action == POINT) {
			points.remove(points.size()-1);
		} else if (action == CLEAR) {
			points = oldInfo.remove(oldInfo.size()-1);
			hullFrame = oldInfo.remove(oldInfo.size()-2);
		}
	}
	
	//
	// Set MouseEvents
	//

	@Override
	public void mouseClicked(MouseEvent e) { }

	@Override
	public void mouseEntered(MouseEvent e) { }

	@Override
	public void mouseExited(MouseEvent e) { }

	@Override
	public void mousePressed(MouseEvent e) {
		addPoint(e.getX(), e.getY());
		actions.add(POINT);
		repaint();
	}

	@Override
	public void mouseReleased(MouseEvent e) { }
	
	//
	// Set key bindings
	//
	
	private void setKeys() {
		this.getInputMap().put(KeyStroke.getKeyStroke("control Z"), "undo");
		AbstractAction undo = new AbstractAction() {
			public void actionPerformed(ActionEvent arg0) {
				undo();
				repaint();
			}
		};
		this.getActionMap().put("undo", undo);
	}
	
}
