package boikoro.gameoflife;

import static boikoro.gameoflife.Cell.aliveCell;
import static boikoro.gameoflife.Cell.deadCell;

import java.awt.Dimension;
import java.awt.Point;
import java.util.HashSet;
import java.util.Set;

/**
 * @author boikoro [email:boiko.roman@gmail.com]
 */

public class Generation {

	private static final boolean FIRST_GENERATION = true;
	private static final boolean DESCENDANT_GENERATION = false;
	private static final int MIN_X = 1;
	private static final int MIN_Y = 1;

	private final boolean isFirstGeneration;
	private final int MAX_X;
	private final int MAX_Y;
	private Set<Point> aliveCells = new HashSet<Point>();
	
	private Generation(int width, int height, boolean isFirstGeneration) {
		this.isFirstGeneration = isFirstGeneration;
		MAX_X = width;
		MAX_Y = height;
	}

	public static Generation initialGeneration(Dimension dimension) {
		return new Generation(dimension.width, dimension.height, FIRST_GENERATION);
	}

	public Integer numberOfCells() {
		return MAX_X * MAX_Y;
	}

	public Integer aliveCellsCount() {
		return aliveCells.size();
	}

	public Generation addAliveCellsToGeneration(Point... aliveCells) {
		if(!isFirstGeneration) {
			throw new IllegalStateException("Generation is descendant, new cells can't be added");
		}
		for(Point aliveCellPosition: aliveCells) {
			addAliveCell(aliveCellPosition);
		}
		return this;
	}

	private void addAliveCell(Point aliveCellPosition) {
		if(cellPositionIsInValidBoundaries(aliveCellPosition)) {
			this.aliveCells.add(aliveCellPosition);
		}
	}

	private boolean cellPositionIsInValidBoundaries(Point cellPosition) {
		return (MIN_X<=cellPosition.x && cellPosition.x<=MAX_X) 
				&& 
				(MIN_Y<=cellPosition.y && cellPosition.y<=MAX_Y);
	}

	public Generation nextGeneration() {
		Generation nextGeneration = new Generation(MAX_X, MAX_Y, DESCENDANT_GENERATION);
		for (int x = MIN_X; x <= MAX_X; x++) {
			for (int y = MIN_Y; y <= MAX_Y; y++) {
				Cell cell = oneIfCellIsAlive(x, y) == 1 ? aliveCell() : deadCell();
				cell.nextGenerationWithAliveNeighboursCountEqualTo(getNumberOfAliveNeighbours(x, y));
				if(cell.isAlive()) {
					nextGeneration.addAliveCell(point(x, y));
				}
				
			}
		}
		return nextGeneration;
	}
	
	private Point point(int x, int y) {
		return new Point(x,y);
	}

	private int getNumberOfAliveNeighbours(int x, int y) {
		return oneIfCellIsAlive(x-1, y) +
				oneIfCellIsAlive(x-1, y-1) +
				oneIfCellIsAlive(x-1, y+1) +
				
				oneIfCellIsAlive(x, y-1) +
				oneIfCellIsAlive(x, y+1) +
				
				oneIfCellIsAlive(x+1, y) +
				oneIfCellIsAlive(x+1, y-1) +
				oneIfCellIsAlive(x+1, y+1);
	}

	private int oneIfCellIsAlive(int x, int y) {
		return aliveCells.contains(point(x,y)) ? 1 : 0;
	}

	public Generation drawOn(Screen screen) {
		screen.resetScreenAsEmptyGridWithDimension(new Dimension(MAX_X, MAX_Y));
		for(Point aliveCell: aliveCells) {
			screen.drawAliveCell(aliveCell);
		}
		screen.flush();
		return this;
	}
}
