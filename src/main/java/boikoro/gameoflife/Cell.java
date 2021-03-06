package boikoro.gameoflife;


/**
 * @author boikoro [email:boiko.roman@gmail.com]
 */

public class Cell {

	private boolean isAlive;

	private Cell(boolean isAlive) {
		this.isAlive = isAlive;
	}

	public static Cell deadCell() {
		return new Cell(false);
	}

	public static Cell aliveCell() {
		return new Cell(true);
	}

	public Cell nextGenerationWithAliveNeighboursCountEqualTo(int aliveNeighboursCount) {
		if(aliveNeighboursCount < 2) {
			isAlive = false;
		} else if(aliveNeighboursCount > 3) {
			isAlive = false;
		} else if(!isAlive && aliveNeighboursCount == 3) {
			isAlive = true;
		}
		return this;
	}

	public Boolean isAlive() {
		return isAlive;
	}


}
