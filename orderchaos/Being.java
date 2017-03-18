package WorldSimulation.orderchaos;

import WorldSimulation.converter.GenomeToHtml;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class Being {

	// The variables each being has, might be up for extension at some point
	private String name;
	private int age;
	private World location;
	private Set<Being> parents;
	private Being partner;
	private Gene[] genome;
	private Set<Being> children;
	private boolean alive;

	// The different constructors
	public Being(String name, int age) {
		this.name = name;
		this.age = age;
		this.children = new HashSet<Being>();
		this.parents = new HashSet<Being>();
		this.alive = true;
	}

	public Being(String name) {
		this.name = name;
		this.age = 0;
		this.children = new HashSet<Being>();
		this.parents = new HashSet<Being>();
		this.alive = true;
	}

	public Being(int age) throws IOException {
		this.name = generateName();
		this.age = age;
		this.children = new HashSet<Being>();
		this.parents = new HashSet<Being>();
		this.alive = true;
	}

	public Being() {
		this.name = "John Doe";
		this.age = 0;
		this.children = new HashSet<Being>();
		this.parents = new HashSet<Being>();
		this.alive = true;
	}

	/**
	 * Main method for testing out the getChild method
	 *
	 * @param args
	 */
	public static void main(String[] args) throws IOException {
		// Create parents
		Being jane = new Being("Jane Doe", 30);
		Being john = new Being("John Doe", 35);
		// Make parents fall in love
		jane.setPartner(john);
		// Give parents genes
		jane.genome = GeneticsUtils.generateGenome();
		john.genome = GeneticsUtils.generateGenome();
		// Conceive a child
		Being jimmy = jane.getChild();
		// Conceive a child
		Being jasmine = jane.getChild();
		// Conceive a child
		Being joyce = jane.getChild();
		// Conceive a child
		Being jarl = jane.getChild();
		// Conceive a child
		Being jill = jane.getChild();
		// Conceive a child
		Being jesper = jane.getChild();
		// Conceive a child
		Being joanna = jane.getChild();
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

	public void setAge(int age) {
		this.age = age;
	}

	public Set<Being> getParents() {
		return this.parents;
	}

	public Being getPartner() {
		return this.partner;
	}

	public void setPartner(Being partner) {
		this.partner = partner;
	}

	public World getLocation() {
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

	public void die() {
		this.alive = false;
	}

	// Both parents are set at the same time
	public void setParents(Being mother, Being father) {
		parents.add(mother);
		parents.add(father);
	}

	public void moveWorlds(World destination) {
		this.location = destination;
	}

	// Different from a simple setter, this increments the age of the being by
	// 1. It might also kill the Being.
	public void yearPassed() {
		double rand = Math.random();
		double var = (1.0d / 12000) * this.getAge() * this.getAge();
		if (rand >= var) {
			this.age++;
		} else {
			this.die();
			System.out.println("Being " + this.getName() + " has died! \n");
		}
	}

	/**
	 * Method to let two Beings conceive a child.
	 *
	 * @return Child if there are two parents, nothing if only one.
	 */
	public Being getChild() throws IOException {
		// Checks if the parent has a partner
		if (this.getStatus() && this.getPartner() != null && this.getPartner().getStatus()) {
			int children;
			if (this.getChildren().size() == 0) {
				children = 1;
			} else {
				children = this.getChildren().size();
			}

			double curve = ((-((this.getAge() - 35) * (this.getAge() - 35)) / 400d) + 0.75)
					/ children;
			double secondCurve = ((-((this.getPartner().getAge() - 35)
					* (this.getPartner().getAge() - 35)) / 400d) + 0.75) / children;
			double rand = Math.random();
			if (rand <= curve + secondCurve) {
				Being child = new Being(generateName());
				child.setParents(this, this.getPartner());
				// Child's genome is determined by the parents' genome
				child.genome = GeneticsUtils.breedGenes(this.genome, this.getPartner().genome);
				this.children.add(child);
				this.getPartner().children.add(child);
				System.out.println(this.getName() + " and " + this.getPartner().getName()
						+ " conceived a child named " + child.getName() + ".");
				return child;
			} else {
				System.out.println(this.getName() + " and " + this.getPartner().getName()
						+ " could not conceive a child.");
				return null;
			}
		} else {
			return null;
		}
	}

	public void setGenome(Gene[] genome) {
		this.genome = genome;

	}

	public String generateName() throws IOException {
		BufferedReader firstNameReader = new BufferedReader(new FileReader(getClass().getResource("firstnames.txt").getPath()));
		String firstName;
		BufferedReader lastNameReader = new BufferedReader(new FileReader(getClass().getResource("surnames.txt").getPath()));
		String lastName;
		int front = ThreadLocalRandom.current().nextInt(0, 5162);
		int back = ThreadLocalRandom.current().nextInt(0, 151670);
		for (int i = 0; i < front; i++) {
			firstNameReader.readLine();
		}
		firstName = firstNameReader.readLine();
		for (int i = 0; i < back; i++) {
			lastNameReader.readLine();
		}
		lastName = lastNameReader.readLine();
		StringBuilder sb = new StringBuilder();
		sb.append(firstName + " " + lastName);
		return sb.toString();
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Name: " + this.getName());
		sb.append("\nAge: " + this.getAge());
		sb.append("\nStatus: " + (this.getStatus() ? "Alive" : "Deceased"));
		sb.append("\nLocation: " + (this.getLocation() != null ? this.getLocation() : "Unknown"));
		sb.append(
				"\nPartner: " + (this.getPartner() != null ? this.getPartner().getName() : "None"));
		sb.append("\nParents: ");
		for (Being parent : this.getParents()) {
			sb.append(("\n") + parent.getName());
		}
		sb.append("\nChildren: ");
		for (Being child : this.getChildren()) {
			sb.append(("\n") + child.getName());
		}
		return sb.toString();
	}

}
