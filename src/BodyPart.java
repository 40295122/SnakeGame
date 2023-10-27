import java.awt.Color;
import java.awt.Graphics;

public class BodyPart {

	private int xCoOr, yCoOr, width, height;

	public BodyPart(int xCoOr, int yCoOr, int tileSize) {
		this.xCoOr = xCoOr;
		this.yCoOr = yCoOr;
		width = tileSize;
		height = tileSize;
	}

	public void tick() {

	}

	public void draw(Graphics g) {
		g.setColor(Color.YELLOW);
		g.fillRect(xCoOr * width, yCoOr * height, width, height);
	}

	public int getxCoOr() {
		return xCoOr;
	}

	public void setxCoOr(int xCoOr) {
		this.xCoOr = xCoOr;
	}

	public int getyCoOr() {
		return yCoOr;
	}

	public void setyCoOr(int yCoOr) {
		this.yCoOr = yCoOr;
	}

}
