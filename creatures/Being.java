package WorldSimulation.creatures;

import WorldSimulation.converter.GenomeToHtml;
import WorldSimulation.exceptions.NoPartnerException;
import WorldSimulation.genetics.Gene;
import WorldSimulation.genetics.GeneticsUtils;
import WorldSimulation.locations.World;
import WorldSimulation.orderchaos.Relationship;
import WorldSimulation.orderchaos.Simulation;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * This class contains all information about a Being, including ways to construct a new one.
 *
 * @author Myrthe Hultermans
 * @version 1.0
 */
public class Being {

	private String name;
	private int age;
	private World location;
	private Set<Being> parents = new HashSet<>();
	//	private Being partner;
	private Gene[] genome;
	private Set<Being> children = new HashSet<>();
	private boolean alive = true;
	private Relationship ship;

	// The different constructors

	/**
	 * This method constructs a new Being with a given name and age.
	 *
	 * @param name The name of the Being.
	 * @param age  The age ofo the Being.
	 */
	public Being(String name, int age) {
		this.name = name;
		this.age = age;
	}

	/**
	 * This method constructs a new Being with a given name and age 0.
	 *
	 * @param name The name of the Being.
	 */
	public Being(String name) {
		this.name = name;
		this.age = 0;
	}

	/**
	 * This method constructs a new Being with a random name and a given age.
	 *
	 * @param age The age of the Being.
	 * @throws IOException As the name generation is partially dependent on reading a file, there might be I/O
	 *                     exceptions.
	 */
	public Being(int age) throws IOException {
		this.name = Simulation.generateName();
		this.age = age;
	}

	/**
	 * This method constructs a new Being with the name John Doe and age 0.
	 */
	Being() {
		this.name = "John Doe";
		this.age = 0;
	}

	/**
	 * Main method for testing out the getChild method.
	 *
	 * @param args Any arguments you might want to give this method. Nothing is done with them, but still.
	 */
	public static void main(String[] args) throws IOException, NoPartnerException {
		// Create parents
		Being jane = new Being("Jane Doe", 30);
		Being john = new Being("John Doe", 35);
		// Make parents fall in love
		jane.setShip(new Relationship(jane, john));
		// Give parents genes
		jane.genome = GeneticsUtils.generateGenome();
		john.genome = GeneticsUtils.generateGenome();
		// Conceive a child
		Simulation.getChild(jane.getShip());
		// Conceive a child
		Simulation.getChild(jane.getShip());
		// Conceive a child
		Simulation.getChild(jane.getShip());
		// Conceive a child
		Simulation.getChild(jane.getShip());
		// Conceive a child
		Simulation.getChild(jane.getShip());
		// Conceive a child
		Simulation.getChild(jane.getShip());
		// Conceive a child
		Simulation.getChild(jane.getShip());
		// System.out.println(GeneticsUtils.genesToString(jasmine.genome));
		GenomeToHtml.showInbrowser(GenomeToHtml.toHtml(jane, true));
		GenomeToHtml.showInbrowser(GenomeToHtml.toHtml(john, true));
		for (Being kid : jane.getChildren()) {
			GenomeToHtml.showInbrowser(GenomeToHtml.toHtml(kid, true));
		}
	}

	// Obligatory getters and setters

	/**
	 * This method gets the name of a Being.
	 *
	 * @return The name of the Being.
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * This method sets the name of a Being.
	 *
	 * @param name The new name of the Being.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * This method gets the age of a Being.
	 *
	 * @return The age of the Being.
	 */
	public int getAge() {
		return this.age;
	}

	/**
	 * This method gets the Relationship a Being is in, if any.
	 *
	 * @return The Relationship of a Being, or null.
	 */
	public Relationship getShip() {
		return ship;
	}

	/**
	 * This method sets the Relationship of a Being.
	 *
	 * @param ship The new Relationship of the Being. Can also be null.
	 */
	public void setShip(Relationship ship) {
		this.ship = ship;
	}

	/**
	 * This method gets the parents of a Being.
	 *
	 * @return A set of Beings that are the parents of the Being.
	 */
	private Set<Being> getParents() {
		return this.parents;
	}

	/**
	 * This method gets the location of a Being. (Not implemented yet.)
	 *
	 * @return A World.
	 */
	private World getLocation() {
		return this.location;
	}

	/**
	 * This method gets the genome of a Being.
	 *
	 * @return An array of Genes.
	 */
	public Gene[] getGeneArray() {
		return this.genome;
	}

	/**
	 * This method gets the children of a Being. (No, not in that way.)
	 *
	 * @return A set of Beings that ara the children of the Being.
	 */
	public Set<Being> getChildren() {
		return this.children;
	}

	/**
	 * This method gets the status of a Being.
	 *
	 * @return A boolean: True is alive, False is dead.
	 */
	public boolean getStatus() {
		return this.alive;
	}

	/**
	 * This method sets the status of a Being to dead.
	 */
	private void die() {
		this.alive = false;
	}

	/**
	 * This method sets the parents of a Being.
	 *
	 * @param mother The first parent (called mother here for convenience)
	 * @param father The second parent (called father here for convenience)
	 */
	public void setParents(Being mother, Being father) {
		parents.add(mother);
		parents.add(father);
	}

	/**
	 * This method adds a child to the set of children a Being has.
	 *
	 * @param kid The new child a Being has.
	 */
	public void addChild(Being kid) {
		this.children.add(kid);
	}

	/**
	 * This method moves a Being from one World to the next. (Not in use yet.)
	 *
	 * @param destination The destination World.
	 */
	public void moveWorlds(World destination) {
		this.location = destination;
	}


	/**
	 * Different from a simple setter, this method increments the age of the being by 1. It might also kill the Being.
	 */
	public void yearPassed() {
		double rand = Math.random();
		double var = (2.0d / 12000) * this.getAge();
		if (rand >= var) {
			this.age++;
		} else {
			this.die();
			if (this.getShip() != null) {
				if (this.getShip().getFirstPerson().equals(this)) {
					this.getShip().getSecondPerson().setShip(null);
				} else {
					this.getShip().getFirstPerson().setShip(null);
				}
			}

			System.out.println("Being " + this.getName() + " has died! \n");
		}
	}

	/**
	 * This method compares two Beings to see if they are equal.
	 *
	 * @param obj The object to compare with this Being.
	 * @return Whether they are equal or not.
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Being) {
			Being o = (Being) obj;
			if (this.getName().equals(o.getName())) {
				if (Arrays.equals(this.getGeneArray(), o.getGeneArray())) {
					if (this.getAge() == o.getAge()) {
						return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * This method sets the genome of a Being.
	 *
	 * @param genome The new genome of the Being.
	 */
	public void setGenome(Gene[] genome) {
		this.genome = genome;

	}

	/**
	 * This method will magically transform a Being into a String representation of one.
	 *
	 * @return A String containing all important information of a Being.
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Name: ");
		sb.append(this.getName());
		sb.append("\nAge: ");
		sb.append(this.getAge());
		sb.append("\nStatus: ");
		sb.append((this.getStatus() ? "Alive" : "Deceased"));
		sb.append("\nLocation: ");
		sb.append((this.getLocation() != null ? this.getLocation() : "Unknown"));
		sb.append("\nRelationship: ");
		sb.append((this.getShip() != null ? this.getShip().getFirstPerson().getName() + " and " + this.getShip()
				.getSecondPerson().getName() : "None"));
		sb.append("\nParents: ");
		for (Being parent : this.getParents()) {
			sb.append("\n");
			sb.append(parent.getName());
		}
		sb.append("\nChildren: ");
		for (Being child : this.getChildren()) {
			sb.append("\n");
			sb.append(child.getName());
		}
		return sb.toString();
	}

}
