package boikoro.gameoflife;

import static boikoro.gameoflife.PrintStreamScreen.screen;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.awt.Dimension;
import java.awt.Point;
import java.io.PrintStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * @author boikoro [email:boiko.roman@gmail.com]
 */

@RunWith(MockitoJUnitRunner.class)
public class PrintStreamScreenTest {

	private static final String EMPTY_CELL = " ";
	private static final String FULL_CELL = "x";

	@Mock
	private PrintStream printStream;
	
	@Test
	public void shouldGenerateInstanceOfTypeScreenUsingGivenPrintStream() {
		assertThat(screen(System.out), instanceOf(Screen.class));
	}

	@Test
	public void shouldResetScreenAsEmptyGridWithGivenDimensionAndPrintAsEmptyMatrix() {
		Screen screen = screen(printStream);
		screen.resetScreenAsEmptyGridWithDimension(dimension(2,2));
		screen.flush();
		verify(printStream, times(2)).append(eq(EMPTY_CELL+EMPTY_CELL+"\n"));
		verify(printStream).flush();
	}
	
	@Test
	public void shouldDrawOnScreen() {
		Screen screen = screen(printStream);
		screen.resetScreenAsEmptyGridWithDimension(dimension(2,1));
		screen.drawAliveCell(new Point(2,1));
		screen.flush();
		verify(printStream).append(eq(EMPTY_CELL+FULL_CELL+"\n"));
		verify(printStream).flush();
	}

	private Dimension dimension(int width, int height) {
		return new Dimension(width, height);
	}

}
