package a4.interfaces;

public interface ICollider {
	public boolean collidesWith(ICollider obj);
	public void handleCollision(ICollider obj, boolean isSoundOn);
}
