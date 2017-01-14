package WorldSimulation.orderchaos;

public class Being {

	private String name;
	private int age;
	private World location;
	private Being mother;
	private Being father;
	private Being partner;
	
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
	
	public void getChild(String name) {
		
		
		
	}
	
}
