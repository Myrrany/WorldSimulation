package WorldSimulation.orderchaos;

import java.util.concurrent.ThreadLocalRandom;

public class Gene {

	// Variables
	public String geneName;
	public int chromA;
	public int chromB;
	public boolean isDom;

	// Constructors
	public Gene(int chroma, int chromb, boolean dominance) {
		this.chromA = chroma;
		this.chromB = chromb;
		this.isDom = dominance;
	}

	public Gene(boolean dominance, int lowerbound, int upperbound) {
		this.chromA = ThreadLocalRandom.current().nextInt(lowerbound, upperbound + 1);
		this.chromB = ThreadLocalRandom.current().nextInt(lowerbound, upperbound + 1);
		this.isDom = dominance;
	}

	public Gene(int chroma, int chromb) {
		this.chromA = chroma;
		this.chromB = chromb;
		this.isDom = false;
	}

	public Gene() {
		this.chromA = ThreadLocalRandom.current().nextInt(0, 256);
		this.chromB = ThreadLocalRandom.current().nextInt(0, 256);
		this.isDom = (Math.random() > 0.5);
	}

	// Methods
	public String toString() {
		return (this.getGeneName() == null || this.getGeneName().equals("") ? "Unnamed Gene:\n"
				: this.getGeneName() + ":\n") + "Chromosome a: " + this.chromA + "\nChromosome b: " + this.chromB
				+ "\nDominant: " + this.isDom;
	}

	public String getGeneName() {
		return geneName;
	}

	public void setGeneName(String gene) {
		this.geneName = gene;
	}

	public int getChromA() {
		return chromA;
	}

	public void setChromA(int chromA) {
		this.chromA = chromA;
	}

	public int getChromB() {
		return chromB;
	}

	public void setChromB(int chromB) {
		this.chromB = chromB;
	}

	public boolean isDom() {
		return isDom;
	}

	public void setDom(boolean isDom) {
		this.isDom = isDom;
	}

	public static void main(String[] args) {
		Gene totalRand = new Gene();
		System.out.println(totalRand.toString() + "\n");
		totalRand.setChromA(17);
		System.out.println(totalRand.toString() + "\n");

	}

}
