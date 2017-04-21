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
	public Being(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public Being(String name) {
		this.name = name;
		this.age = 0;
	}

	public Being(int age) throws IOException {
		this.name = Simulation.generateName();
		this.age = age;
	}

	Being() {
		this.name = "John Doe";
		this.age = 0;
	}

	/**
	 * Main method for testing out the getChild method
	 *
	 * @param args any arguments you might want to give this method. Nothing is done with them, but still.
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
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return this.age;
	}

	public Relationship getShip() {
		return ship;
	}

	public void setShip(Relationship ship) {
		this.ship = ship;
	}

	private Set<Being> getParents() {
		return this.parents;
	}

	private World getLocation() {
		return this.location;
	}

	public Gene[] getGeneArray() {
		return this.genome;
	}

	public Set<Being> getChildren() {
		return this.children;
	}

	public boolean getStatus() {
		return this.alive;
	}

	private void die() {
		this.alive = false;
	}

	// Both parents are set at the same time
	public void setParents(Being mother, Being father) {
		parents.add(mother);
		parents.add(father);
	}

	public void addChild(Being kid) {
		this.children.add(kid);
	}

	public void moveWorlds(World destination) {
		this.location = destination;
	}


	/**
	 * Different from a simple setter, this increments the age of the being by 1. It might also kill the Being.
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

	public void setGenome(Gene[] genome) {
		this.genome = genome;

	}


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
		sb.append((this.getShip() != null ? this.getShip().getFirstPerson().getName() + " and " + this.getShip().getSecondPerson().getName() : "None"));
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
