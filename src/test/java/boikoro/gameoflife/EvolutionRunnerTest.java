package boikoro.gameoflife;

import static boikoro.gameoflife.EvolutionRunner.evolutionRunner;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.awt.Dimension;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * @author boikoro [email:boiko.roman@gmail.com]
 */

@RunWith(MockitoJUnitRunner.class)
public class EvolutionRunnerTest {

	private static final long TIME_TO_LIVE_MILLIS = 1000;
	private static final Dimension DIMENSION = new Dimension(0,0);

	@Mock Generation generation;
	@Mock Screen screen;
	@Mock SystemClock systemClock;

			
	@Before
	public void setup() {
		when(generation.getDimension()).thenReturn(DIMENSION);
		when(generation.nextGeneration()).thenReturn(Generation.initialGeneration(DIMENSION));
	}
	
	@Test
	public void shouldExecuteEvolutionLifecycleInCorrectOrder() {
		int numberOfGenerations = 1;
		
		performEvolution(numberOfGenerations);
		
		InOrder inOrder = inOrder(generation, screen, systemClock);
		inOrder.verify(generation).getDimension();
		inOrder.verify(screen).open(DIMENSION);
		inOrder.verify(generation).drawOn(screen);
		inOrder.verify(systemClock).pause(TIME_TO_LIVE_MILLIS);
		inOrder.verify(screen).close();
	}
	
	@Test
	public void shouldGenerateSequenceOfGenerationsAndPlayThemWithPauses() {
		int numberOfGenerations = 12;
		
		performEvolution(numberOfGenerations);

		verify(screen, times(1)).open(Mockito.any(Dimension.class));
		verify(generation, times(1)).getDimension();
		verify(generation, times(1)).drawOn(screen);
		verify(generation, times(1)).nextGeneration();
		verify(screen, times(numberOfGenerations-1)).flush();//-1 because it is not invoked 
															//in first generation mock
		verify(systemClock, times(numberOfGenerations)).pause(TIME_TO_LIVE_MILLIS);
		verify(screen, times(1)).close();
	}

	private void performEvolution(int numberOfGenerations) {
		evolutionRunner()
			.withInitialGeneration(generation)
			.withScreen(screen)
			.withNumberOfGenerations(numberOfGenerations)
			.withGenerationTimeToLiveInMillis(TIME_TO_LIVE_MILLIS)
			.withSystemClock(systemClock)
			.run();
	}
}
