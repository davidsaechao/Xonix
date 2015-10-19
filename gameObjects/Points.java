package a4.gameObjects;

public class Points extends GameObject {
	private double x;
	private double y;
	public Points(double newX, double newY) {
		super.setRotation();
		super.setScale();
		super.setTranslation();
		x = newX;
		y = newY;
		super.translate(x, y);
	}

}
