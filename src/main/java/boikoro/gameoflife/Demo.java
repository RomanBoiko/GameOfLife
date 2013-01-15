package boikoro.gameoflife;

import static boikoro.gameoflife.EvolutionRunner.evolutionRunner;
import static boikoro.gameoflife.Generation.initialGeneration;
import static boikoro.gameoflife.LanternaMatrixScreen.screen;

import java.awt.Dimension;
import java.awt.Point;

/**
 * @author boikoro [email:boiko.roman@gmail.com]
 */

public class Demo {
	public static void main(String[] args) {
			Dimension dimension = new Dimension(5,5);
			Screen screen = screen(System.out);
			Generation initialGeneration = initialGeneration(dimension)
					.addAliveCellsToGeneration(
							new Point(1,1),
							new Point(1,2),
							new Point(2,1),
							new Point(2,2),
							new Point(3,3),
							new Point(3,4),
							new Point(4,3),
							new Point(4,4));
			evolutionRunner()
				.withInitialGeneration(initialGeneration)
				.withScreen(screen)
				.withNumberOfGenerations(10)
				.withGenerationTimeToLiveInMillis(1000)
				.withSystemClock(SystemClock.DEFAULT)
				.run();
	}
}
