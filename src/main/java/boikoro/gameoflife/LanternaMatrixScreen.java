package boikoro.gameoflife;

import java.awt.Dimension;
import java.awt.Point;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.Charset;

import com.googlecode.lanterna.screen.ScreenCharacterStyle;
import com.googlecode.lanterna.screen.ScreenWriter;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.text.CygwinTerminal;
import com.googlecode.lanterna.terminal.text.UnixTerminal;

/**
 * @author boikoro [email:boiko.roman@gmail.com]
 */

public class LanternaMatrixScreen implements Screen {

	private static final InputStream IN_STREAM = System.in;
	private static final Charset CHARSET = Charset.forName(System.getProperty("file.encoding"));
	private static final String FULL_CELL = "x";
	
	private com.googlecode.lanterna.screen.Screen screen;
	private ScreenWriter writer;
	private PrintStream printStream;
	
	private LanternaMatrixScreen(PrintStream printStream){
		this.printStream = printStream;
	}

	public static Screen screen(PrintStream printStream) {
		return new LanternaMatrixScreen(printStream);
	}
	
	@Override
	public Screen open(Dimension dimension) {
		screen = new com.googlecode.lanterna.screen.Screen(terminal(), dimension.width, dimension.height);
		screen.startScreen();
		writer = new ScreenWriter(screen);
		writer.setForegroundColor(Terminal.Color.WHITE);
		writer.setBackgroundColor(Terminal.Color.BLACK);
		return this;
	}

	private Terminal terminal() {
		if (System.getProperty("os.name").toLowerCase().startsWith("windows"))
			return new CygwinTerminal(IN_STREAM, printStream, CHARSET);
		else
			return new UnixTerminal(IN_STREAM, printStream, CHARSET);
	}

	@Override
	public Screen clearScreen() {
		screen.clear();
		return this;
	}

	@Override
	public Screen drawAliveCell(Point cellPosition) {
		writer.drawString(cellPosition.x, cellPosition.y, FULL_CELL, ScreenCharacterStyle.Bold);
		return this;
	}

	@Override
	public Screen flush() {
		screen.refresh();
		return this;
	}

	@Override
	public Screen close() {
		screen.stopScreen();
		return this;
	}
}
