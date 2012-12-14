package boikoro.gameoflife;

import java.awt.Dimension;
import java.awt.Point;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @author boikoro [email:boiko.roman@gmail.com]
 */

public class PrintStreamScreen implements Screen {
	private static final char EMPTY_CELL = ' ';
	private static final char FULL_CELL = 'x';
	
	private PrintStream printStream;
	private Map<Integer, StringBuffer> matrixRows;
	
	private PrintStreamScreen(PrintStream printStream){
		this.printStream = printStream;
		this.matrixRows = new HashMap<Integer, StringBuffer>();
	}
	public static PrintStreamScreen screen(PrintStream printStream) {
		return new PrintStreamScreen(printStream);
	}

	public void resetScreenAsEmptyGridWithDimension(Dimension dimension) {
		for(int y = 1; y<=dimension.height; y++) {
			StringBuffer buffer = new StringBuffer();
			for(int x = 1; x<=dimension.width; x++) {
				buffer.append(EMPTY_CELL);
			}
			buffer.append('\n');
			matrixRows.put(y, buffer);
		}
		
	}

	public void drawAliveCell(Point cellPosition) {
		matrixRows.get(cellPosition.y).setCharAt(cellPosition.x-1, FULL_CELL);
	}

	public void flush() {
		for (int i = 0; i < matrixRows.size(); i++) {
			printStream.append(matrixRows.get(i+1).toString());
		}
		printStream.flush();
	}

}
