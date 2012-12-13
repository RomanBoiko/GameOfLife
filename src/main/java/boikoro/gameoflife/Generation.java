package boikoro.gameoflife;

import static com.google.common.collect.Ranges.closed;

import java.awt.Dimension;
import java.awt.Point;
import java.util.HashSet;
import java.util.Set;

import com.google.common.collect.Range;

/**
 * @author boikoro [email:boiko.roman@gmail.com]
 */

public class Generation {

	private static final boolean FIRST_GENERATION = true;
	private static final boolean DESCENDANT_GENERATION = false;

	private final boolean isFirstGeneration;
	private final Range<Integer> allowedXPositions;
	private final Range<Integer> allowedYPositions;
	private Set<Point> aliveCells = new HashSet<Point>();
	
	private Generation(int width, int height, boolean isFirstGeneration) {
		this.isFirstGeneration = isFirstGeneration;
		allowedXPositions = closed(1, width);
		allowedYPositions = closed(1, height);
	}

	public static Generation initialGeneration(Dimension dimension) {
		return new Generation(dimension.width, dimension.height, FIRST_GENERATION);
	}

	public Integer numberOfCells() {
		return allowedXPositions.upperEndpoint() * allowedYPositions.upperEndpoint();
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
		if(allowedXPositions.contains(aliveCellPosition.x) && allowedYPositions.contains(aliveCellPosition.y)) {
			this.aliveCells.add(aliveCellPosition);
		}
	}

	public Generation nextGeneration() {
		//TODO: not finished
		return new Generation(allowedXPositions.upperEndpoint(), allowedYPositions.upperEndpoint(), DESCENDANT_GENERATION);
	}

	public void drawOn(Screen screen) {
		for(Point aliveCell: aliveCells) {
			screen.drawAliveCell(aliveCell);
		}
	}
}
