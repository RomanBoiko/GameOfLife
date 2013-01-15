package boikoro.gameoflife;

/**
 * @author boikoro [email:boiko.roman@gmail.com]
 */

public class EvolutionRunner {

	private EvolutionRunner(){}
	
	private Generation generation;
	private Screen screen;
	private int numberOfGenerations;
	private long generationTimeToLiveInMillis;
	private SystemClock systemClock;

	public static EvolutionRunner evolutionRunner() {
		return new EvolutionRunner();
	}

	public EvolutionRunner withInitialGeneration(Generation generation) {
		this.generation = generation;
		return this;
	}

	public EvolutionRunner withScreen(Screen screen) {
		this.screen = screen;
		return this;
	}

	public EvolutionRunner withNumberOfGenerations(int numberOfGenerations) {
		this.numberOfGenerations = numberOfGenerations;
		return this;
	}

	public EvolutionRunner withGenerationTimeToLiveInMillis(long generationTimeToLiveInMillis) {
		this.generationTimeToLiveInMillis = generationTimeToLiveInMillis;
		return this;
	}

	public EvolutionRunner withSystemClock(SystemClock systemClock) {
		this.systemClock = systemClock;
		return this;
	}

	public void run() {
		screen.open(generation.getDimension());
		drawGenerationAndPause();
		for (int i = 1; i < numberOfGenerations; i++) {
			generation = generation.nextGeneration();
			drawGenerationAndPause();
		}
		screen.close();
	}

	private void drawGenerationAndPause() {
		generation.drawOn(screen);
		systemClock.pause(generationTimeToLiveInMillis);
	}

}
