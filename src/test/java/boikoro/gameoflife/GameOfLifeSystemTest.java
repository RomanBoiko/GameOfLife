package boikoro.gameoflife;

import static boikoro.gameoflife.Generation.initialGeneration;

import java.awt.Dimension;
import java.awt.Point;

import org.junit.Test;

/**
 * @author boikoro [email:boiko.roman@gmail.com]
 */

public class GameOfLifeSystemTest {

	@Test
	public void shouldDrawEvolutionMatrixFigureBlockNumberOfTimes() {
		System.out.println("====BLOCK");
		Screen screen = PrintStreamScreen.screen(System.out);
		Generation initialGeneration = initialGeneration(new Dimension(4,4))
				.addAliveCellsToGeneration(
						new Point(2,2),
						new Point(3,2),
						new Point(3,3));
		initialGeneration.drawOn(screen)
			.nextGeneration().drawOn(screen)
			.nextGeneration().drawOn(screen);
	}

	@Test
	public void shouldDrawEvolutionMatrixFigureBeacon() {
		System.out.println("====BEACON");
		Screen screen = PrintStreamScreen.screen(System.out);
		Generation initialGeneration = initialGeneration(new Dimension(4,4))
				.addAliveCellsToGeneration(
						new Point(1,1),
						new Point(1,2),
						new Point(2,1),
						new Point(2,2),
						new Point(3,3),
						new Point(3,4),
						new Point(4,3),
						new Point(4,4));
		initialGeneration.drawOn(screen)
			.nextGeneration().drawOn(screen)
			.nextGeneration().drawOn(screen)
			.nextGeneration().drawOn(screen)
			.nextGeneration().drawOn(screen)
			.nextGeneration().drawOn(screen)
			.nextGeneration().drawOn(screen);
	}

}
