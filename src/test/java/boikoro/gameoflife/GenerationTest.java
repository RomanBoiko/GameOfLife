package boikoro.gameoflife;

import static boikoro.gameoflife.Generation.initialGeneration;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.awt.Dimension;
import java.awt.Point;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * @author boikoro [email:boiko.roman@gmail.com]
 */

@RunWith(MockitoJUnitRunner.class)
public class GenerationTest {
	private static int WIDTH = 4;
	private static int HEIGHT = 3;
	private static Dimension DIMENSION = new Dimension(WIDTH, HEIGHT);
	
	@Mock Screen screenMock;
	
	@Test
	public void shouldCreateInitialGenerationWith2DStructure() {
		assertThat(initialGeneration(DIMENSION).numberOfCells(), is(HEIGHT*WIDTH));
	}

	@Test
	public void shouldCreateInitialGenerationWithoutLiveCells() {
		assertThat(initialGeneration(DIMENSION).aliveCellsCount(), is(0));
	}

	@Test
	public void shouldSetNumberOfAliveCellsToInitialGeneration() {
		assertThat(
			initialGeneration(DIMENSION).addAliveCellsToGeneration(
				cell(1,3),
				cell(1,2))
			.aliveCellsCount(),
			is(2));
	}

	@Test
	public void shouldIgnoreCellsWithPositionWhichIsNotAPartOfGeneration() {
		assertThat(
				initialGeneration(DIMENSION).addAliveCellsToGeneration(
					cell(WIDTH, HEIGHT),
					
					cell(WIDTH+1, HEIGHT),
					cell(0, HEIGHT),
					
					cell(WIDTH, HEIGHT+1),
					cell(WIDTH, 0))
				.aliveCellsCount(),
				is(1));
	}
	
	@Test
	public void shouldProduceNextGenerationWhichIsNewInstance() {
		Generation firstGeneration = initialGeneration(DIMENSION);
		Generation secondGeneration = firstGeneration.nextGeneration();
		assertNotSame(firstGeneration, secondGeneration);
	}

	@Test(expected = IllegalStateException.class)
	public void shouldNotAllowToAddNewAliveCellsToDescendantGeneration() {
		Generation secondGeneration = initialGeneration(DIMENSION).nextGeneration();
		secondGeneration.addAliveCellsToGeneration(cell(1,1));
	}
	
	@Test
	public void shouldNotDrawAnythingIfGenerationHasNoCells() {
		initialGeneration(DIMENSION).drawOn(screenMock);
		verify(screenMock, never()).drawAliveCell(any(Point.class));
	}


	@Test
	public void shouldResetScreenOnceAsEmtyGrid() {
		initialGeneration(DIMENSION)
			.addAliveCellsToGeneration(
					cell(WIDTH, HEIGHT),
					cell(1, 1))
			.drawOn(screenMock);
		verify(screenMock, times(1)).clearScreen();
		verify(screenMock, times(2)).drawAliveCell(any(Point.class));
	}

	@Test
	public void shouldFlushScreenOnceAfterDrawingOnIt() {
		initialGeneration(DIMENSION)
		.addAliveCellsToGeneration(
				cell(WIDTH, HEIGHT),
				cell(1, 1))
				.drawOn(screenMock);
		verify(screenMock, times(2)).drawAliveCell(any(Point.class));
		verify(screenMock, times(1)).flush();
	}
	
	@Test
	public void shouldResetDrawAndFlushScreenInGivenOrder() {
		initialGeneration(DIMENSION)
			.addAliveCellsToGeneration(cell(WIDTH, HEIGHT))
			.drawOn(screenMock);
		InOrder inOrder = inOrder(screenMock);
		inOrder.verify(screenMock).clearScreen();
		inOrder.verify(screenMock).drawAliveCell(any(Point.class));
		inOrder.verify(screenMock).flush();
	}

	@Test
	public void shouldBeAbleToDrawAliveCellsOnScreen() {
		initialGeneration(DIMENSION)
			.addAliveCellsToGeneration(
				cell(WIDTH, HEIGHT),
				cell(1, 1))
			.drawOn(screenMock);
		verify(screenMock).drawAliveCell(eq(cell(1, 1)));
		verify(screenMock).drawAliveCell(eq(cell(WIDTH, HEIGHT)));
	}

	@Test
	public void shouldCreateNextGenerationAsResultOfRulesApplicationToInitial() {
		initialGeneration(DIMENSION)
			.addAliveCellsToGeneration(
				cell(1, 1),
				cell(1, 2),
				cell(2, 1))
		.nextGeneration()
		.drawOn(screenMock);
		verify(screenMock).drawAliveCell(eq(cell(1, 1)));
		verify(screenMock).drawAliveCell(eq(cell(1, 2)));
		verify(screenMock).drawAliveCell(eq(cell(2, 1)));
		verify(screenMock).drawAliveCell(eq(cell(2, 2)));
	}

	private Point cell(int column, int row) {
		return new Point(column, row);
	}

}
