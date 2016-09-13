package engine;

import java.awt.Shape;

public interface SimDrawable extends Shape
{
	public void tick(double delta);
}
