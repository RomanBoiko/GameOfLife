package boikoro.gameoflife;

import java.awt.Dimension;
import java.awt.Point;

/**
 * @author boikoro [email:boiko.roman@gmail.com]
 */

public interface Screen {
	Screen open(Dimension dimension);
	Screen clearScreen();
	Screen drawAliveCell(Point cellPosition);
	Screen flush();
	Screen close();
}
