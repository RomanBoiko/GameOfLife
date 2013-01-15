package boikoro.gameoflife;

/**
 * @author boikoro [email:boiko.roman@gmail.com]
 */

public interface SystemClock {

	void pause(long millis);

	SystemClock DEFAULT = new SystemClock() {
		@Override
		public void pause(long millis) {
			try {
				Thread.sleep(millis);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
	};
}
