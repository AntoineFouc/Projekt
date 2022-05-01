import java.awt.*;
import javax.swing.*;
import java.util.*;

public abstract class obstacle implements Comparable<obstacle> {
	protected String name = "default";
	protected int x;
	protected int y;
	protected Road road;
	protected double position;
	protected int number;

	public obstacle(int uneposx, int uneposy, Road route) {
		x = uneposx;
		y = uneposy;
		road = route;

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
		position = (x - road.getStartingPoint()[0]) * Math.cos(road.getOrientation() * Math.PI / 180)
				+ (road.getStartingPoint()[1] - y) * Math.sin(road.getOrientation() * Math.PI / 180);
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

	public Road getRoad() {
		return road;
	}

	public void setRoad(Road road) {
		this.road = road;
	}

	public int compareTo(obstacle obs) {
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
