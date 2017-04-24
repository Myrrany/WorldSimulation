package WorldSimulation.genetics;

import java.util.concurrent.ThreadLocalRandom;


/**
 * This class stores genes and creates them if needed, using Random generation. It also has the function to print the
 * gene information.
 *
 * @author Myrthe Hultermans
 * @version 1.0
 */
public class Gene {

	// Variables
	private String geneName;
	private int chromA;
	private int chromB;
	private boolean isDom;

	// Constructors

	/**
	 * This method constructs a new instance of the Gene class, using predefined chromosomes.
	 *
	 * @param chroma    First chromosome of the gene.
	 * @param chromb    Second chromosome of the gene.
	 * @param dominance Whether or not this gene has a dominant chromosome.
	 */
	Gene(int chroma, int chromb, boolean dominance) {
		this.chromA = chroma;
		this.chromB = chromb;
		this.isDom = dominance;
	}


	/**
	 * This method constructs a new instance of the Gene class, using random generation with predefined bounds.
	 *
	 * @param dominance  Whether or not this gene has a dominant chromosome.
	 * @param lowerbound The lower bound of the random generation.
	 * @param upperbound The upper bound of the random generation.
	 */
	Gene(boolean dominance, int lowerbound, int upperbound) {
		this.chromA = ThreadLocalRandom.current().nextInt(lowerbound, upperbound + 1);
		this.chromB = ThreadLocalRandom.current().nextInt(lowerbound, upperbound + 1);
		this.isDom = dominance;
	}

	/**
	 * This method constructs a new instance of the Gene class without dominant chromosomes, using predefined
	 * chromosomes.
	 *
	 * @param chroma First chromosome of the gene.
	 * @param chromb Second chromosome of the gene.
	 */
	public Gene(int chroma, int chromb) {
		this.chromA = chroma;
		this.chromB = chromb;
		this.isDom = false;
	}

	/**
	 * This method constructs a new instance of the Gene class totally at random.
	 */
	Gene() {
		this.chromA = ThreadLocalRandom.current().nextInt(0, 256);
		this.chromB = ThreadLocalRandom.current().nextInt(0, 256);
		this.isDom = (Math.random() > 0.5);
	}

	// Methods

	/**
	 * Main method to test out Gene generation
	 *
	 * @param args The arguments that are passed to the main method. Nothing is done with them.
	 */
	public static void main(String[] args) {
		Gene totalRand = new Gene();
		System.out.println(totalRand.toString() + "\n");
		totalRand.setChromA(17);
		System.out.println(totalRand.toString() + "\n");
		totalRand.setChromB(17);
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

	/**
	 * This method gets the gene name.
	 *
	 * @return The gene name.
	 */
	public String getGeneName() {
		return geneName;
	}

	/**
	 * This method sets the gene name.
	 *
	 * @param gene The new gene name.
	 */
	void setGeneName(String gene) {
		this.geneName = gene;
	}

	/**
	 * This method gets the first chromosome of a gene.
	 *
	 * @return The first chromosome of a gene.
	 */
	public int getChromA() {
		return chromA;
	}

	/**
	 * This method sets the first chromosome of a gene.
	 *
	 * @param chromA The new first chromosome of a gene.
	 */
	private void setChromA(int chromA) {
		this.chromA = chromA;
	}

	/**
	 * This method gets the second chromosome of a gene.
	 *
	 * @return The second chromosome of a gene.
	 */
	public int getChromB() {
		return chromB;
	}

	/**
	 * This method sets the second chromosome of a gene.
	 *
	 * @param chromB The new second chromosome of a gene.
	 */
	private void setChromB(int chromB) {
		this.chromB = chromB;
	}

	/**
	 * This method gets whether the gene has a dominant chromosome or not.
	 *
	 * @return The dominance of a gene.
	 */
	boolean isDom() {
		return isDom;
	}

	/**
	 * This method sets whether the gene has a dominant chromosome or not.
	 *
	 * @param isDom The new dominance state of a gene.
	 */
	public void setDom(boolean isDom) {
		this.isDom = isDom;
	}

}
