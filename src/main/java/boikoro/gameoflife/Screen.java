package boikoro.gameoflife;

import java.awt.Dimension;
import java.awt.Point;

/**
 * @author boikoro [email:boiko.roman@gmail.com]
 */

public interface Screen {
	void resetScreenAsEmptyGridWithDimension(Dimension dimension);
	void drawAliveCell(Point cellPosition);
	void flush();
}
