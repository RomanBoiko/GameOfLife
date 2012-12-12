package boikoro.gameoflife;

import static boikoro.gameoflife.Cell.aliveCell;
import static boikoro.gameoflife.Cell.deadCell;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;


/**
 * @author boikoro [email:boiko.roman@gmail.com]
 */

public class CellTest {

	@Test
	public void deadCellWithLessThan2NeighboursShouldStayDead() {
		assertThat(deadCell().nextGenerationWithAliveNeighboursCountEqualTo(1).isAlive(), is(false));
	}

	@Test
	public void aliveCellWithLessThan2NeighboursShouldDie() {
		assertThat(aliveCell().nextGenerationWithAliveNeighboursCountEqualTo(1).isAlive(), is(false));
	}

	@Test
	public void aliveCellWith2NeighboursShouldStayAlive() {
		assertThat(aliveCell().nextGenerationWithAliveNeighboursCountEqualTo(2).isAlive(), is(true));
	}

	@Test
	public void aliveCellWith3NeighboursShouldStayAlive() {
		assertThat(aliveCell().nextGenerationWithAliveNeighboursCountEqualTo(3).isAlive(), is(true));
	}

	@Test
	public void aliveCellWithMoreThan3NeighboursShouldDie() {
		assertThat(aliveCell().nextGenerationWithAliveNeighboursCountEqualTo(4).isAlive(), is(false));
	}
	
	@Test
	public void deadCellWith3NeighboursShouldBecomeAlive() {
		assertThat(deadCell().nextGenerationWithAliveNeighboursCountEqualTo(3).isAlive(), is(true));
	}
	
	@Test
	public void deadCellWith2NeighboursShouldStayDead() {
		assertThat(deadCell().nextGenerationWithAliveNeighboursCountEqualTo(2).isAlive(), is(false));
	}
	
	

}
