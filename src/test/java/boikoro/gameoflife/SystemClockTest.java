package boikoro.gameoflife;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

/**
 * @author boikoro [email:boiko.roman@gmail.com]
 */

public class SystemClockTest {

	@Test
	public void shouldHaveDefaultImplementation() {
		assertNotNull(SystemClock.DEFAULT);
	}

}
