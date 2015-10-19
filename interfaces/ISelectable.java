package a4.interfaces;

import java.awt.geom.Point2D;

public interface ISelectable {
	public void setSelected(boolean yesNo);
	public boolean isSelected();
	public boolean contains(Point2D p);
}
