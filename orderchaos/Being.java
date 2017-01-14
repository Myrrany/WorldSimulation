package WorldSimulation.orderchaos;

public class Being {

	private String name;
	private int age;
	private World location;
	private Being mother;
	private Being father;
	private Being partner;
	private Gene[] genome;

	public Being(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public Being(String name) {
		this.name = name;
		this.age = 0;
	}

	public Being(int age) {
		this.name = "John Doe";
		this.age = age;
	}

	public Being() {
		this.name = "John Doe";
		this.age = 0;
	}

	public String getName() {
		return this.name;
	}

	public int getAge() {
		return this.age;
	}

	public Being getMother() {
		return this.mother;
	}

	public Being getFather() {
		return this.father;
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

	public void setName(String name) {
		this.name = name;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void setParents(Being mother, Being father) {
		this.mother = mother;
		this.father = father;
	}

	public void moveWorlds(World destination) {
		this.location = destination;
	}

	public void setPartner(Being partner) {
		this.partner = partner;
	}

	public void yearPassed() {
		this.age++;
	}

	public Being getChild(String name) {
		if (this.getPartner() != null) {
			Being child = new Being(name);
			child.setParents(this, this.getPartner());
			child.genome = GeneticsUtils.breedGenes(this.genome, this.getPartner().genome);
			return child;
		} else {
			return null;
		}
	}

	public static void main(String[] args) {
		Being jane = new Being("Jane Doe", 30);
		Being john = new Being("John Doe", 30);
		jane.setPartner(john);
		jane.genome = GeneticsUtils.generateGenome();
		john.genome = GeneticsUtils.generateGenome();
		System.out.println("This is " + jane.getName() + "'s genome:\n");
		System.out.println(GeneticsUtils.printGenes(jane.genome));
		System.out.println("This is " + john.getName() + "'s genome:\n");
		System.out.println(GeneticsUtils.printGenes(john.genome));
		Being baby = jane.getChild("Baby Doe");
		System.out.println("This is " + baby.getName() + "'s genome:\n");
		System.out.println(GeneticsUtils.printGenes(baby.genome));
	}

}
