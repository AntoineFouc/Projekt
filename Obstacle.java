import java.awt.*;
import javax.swing.*;
import java.util.*;

public abstract class Obstacle implements Comparable<Obstacle> {
	protected String name = "default";
	protected int x;
	protected int y;
	protected Route Route;
	protected double position;
	protected int number;

	public Obstacle(int uneposx, int uneposy, Route route) {
		x = uneposx;
		y = uneposy;
		Route = route;

	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public void draw(Graphics g) {
	}

	public double getPosition() {
		position = (x - Route.getStartingPoint()[0]) * Math.cos(Route.getOrientation() * Math.PI / 180)
				+ (Route.getStartingPoint()[1] - y) * Math.sin(Route.getOrientation() * Math.PI / 180);
		return position;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Route getRoute() {
		return Route;
	}

	public void setRoute(Route Route) {
		this.Route = Route;
	}

	public int compareTo(Obstacle obs) {
		int res;
		if (this.getPosition() > obs.getPosition()) {
			res = 1;
		} else if (this.getPosition() == obs.getPosition()) {
			res = 0;
		} else {
			res = -1;
		}
		return res;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPosition(double position) {
		this.position = position;
	}
}
