package WorldSimulation.genetics;

import java.util.concurrent.ThreadLocalRandom;

public class Gene {

	// Variables
	private String geneName;
	private int chromA;
	private int chromB;
	private boolean isDom;

	// Constructors
	Gene(int chroma, int chromb, boolean dominance) {
		this.chromA = chroma;
		this.chromB = chromb;
		this.isDom = dominance;
	}

	Gene(boolean dominance, int lowerbound, int upperbound) {
		this.chromA = ThreadLocalRandom.current().nextInt(lowerbound, upperbound + 1);
		this.chromB = ThreadLocalRandom.current().nextInt(lowerbound, upperbound + 1);
		this.isDom = dominance;
	}

	public Gene(int chroma, int chromb) {
		this.chromA = chroma;
		this.chromB = chromb;
		this.isDom = false;
	}

	Gene() {
		this.chromA = ThreadLocalRandom.current().nextInt(0, 256);
		this.chromB = ThreadLocalRandom.current().nextInt(0, 256);
		this.isDom = (Math.random() > 0.5);
	}

	// Methods
	
	/**
	 * Main method to test out Gene generation
	 * @param args The arguments that are passed to the main method. Nothing is done with them.
	 */
	public static void main(String[] args) {
		Gene totalRand = new Gene();
		System.out.println(totalRand.toString() + "\n");
		totalRand.setChromA(17);
		System.out.println(totalRand.toString() + "\n");

	}

	/**
	 * General toString method.
	 */
	public String toString() {
		return (this.getGeneName() == null || this.getGeneName().equals("") ? "Unnamed Gene:\n"
				: this.getGeneName() + ":\n") + "Chromosome a: " + this.chromA + "\nChromosome b: " + this.chromB
				+ "\nDominant: " + this.isDom;
	}

	// Getters and Setters
	public String getGeneName() {
		return geneName;
	}

	void setGeneName(String gene) {
		this.geneName = gene;
	}

	public int getChromA() {
		return chromA;
	}

	private void setChromA(int chromA) {
		this.chromA = chromA;
	}

	public int getChromB() {
		return chromB;
	}

	public void setChromB(int chromB) {
		this.chromB = chromB;
	}

	boolean isDom() {
		return isDom;
	}

	public void setDom(boolean isDom) {
		this.isDom = isDom;
	}

}
