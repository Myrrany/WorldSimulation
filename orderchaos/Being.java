package WorldSimulation.orderchaos;

import java.util.HashSet;
import java.util.Set;

import WorldSimulation.converter.GenomeToHtml;

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

	public Being(int age) {
		this.name = "John Doe";
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

	
	// Obligatory getters and setters
	public String getName() {
		return this.name;
	}

	public int getAge() {
		return this.age;
	}

	public Set<Being> getParents() {
		return this.parents;
	}

	public Being getPartner() {
		return this.partner;
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

	public void setName(String name) {
		this.name = name;
	}

	public void setAge(int age) {
		this.age = age;
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

	public void setPartner(Being partner) {
		this.partner = partner;
	}

	// Different from a simple setter, this increments the age of the being by 1. It might also kill the Being.
	public void yearPassed() {
		double rand = Math.random();
		double var = (1.0d/12000) * this.getAge() * this.getAge();
		if (rand >= var) {
			this.age++;
		} else {
			this.die();
			System.out.println("Being " + this.getName() + " has died! (" + rand + ", " + var + ")\n");
		}
	}
	
	/**
	 * Method to let two Beings conceive a child.
	 * @param name name for the new being
	 * @return Child if there are two parents, nothing if only one.
	 */
	public Being getChild(String name) {
		// Checks if the parent has a partner
		if (this.getStatus() && this.getPartner() != null && this.getPartner().getStatus()) {
			Being child = new Being(name);
			child.setParents(this, this.getPartner());
			// Child's genome is determined by the parents' genome
			child.genome = GeneticsUtils.breedGenes(this.genome, this.getPartner().genome);
			this.children.add(child);
			this.getPartner().children.add(child);
			return child;
		} else {
			return null;
		}
	}
	
	public void setGenome(Gene[] genome) {
		this.genome = genome;
		
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Name: " + this.getName());
		sb.append("\nAge: " + this.getAge());
		sb.append("\nStatus: " + (this.getStatus() ? "Alive" : "Deceased"));
		sb.append("\nLocation: " + (this.getLocation() != null ? this.getLocation() : "Unknown"));
		sb.append("\nPartner: " + (this.getPartner() != null ? this.getPartner().getName() : "None"));
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

	/**
	 * Main method for testing out the getChild method
	 * @param args
	 */
	public static void main(String[] args) {
		// Create parents
		Being beitske = new Being("Beitske Flake", 20);
		Being tim = new Being("Tim Kerkhoven", 22);
		// Make parents fall in love
		beitske.setPartner(tim);
		// Give parents genes
		beitske.genome = GeneticsUtils.generateGenome();
		tim.genome = GeneticsUtils.generateGenome();
		// Conceive a child
		Being eraxa = beitske.getChild("Eraxa Roscha Flake-Kerkhoven");
		// Conceive a child
		Being arthion = beitske.getChild("Arthion Tanach Flake-Kerkhoven");
		System.out.println(GeneticsUtils.genesToString(arthion.genome));
		GenomeToHtml.showInbrowser(GenomeToHtml.toHtml(beitske, true));
		GenomeToHtml.showInbrowser(GenomeToHtml.toHtml(tim, true));
		GenomeToHtml.showInbrowser(GenomeToHtml.toHtml(eraxa, true));
		GenomeToHtml.showInbrowser(GenomeToHtml.toHtml(arthion, true));
	}

}
