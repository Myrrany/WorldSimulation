package WorldSimulation.orderchaos;

public class Human extends Being {

	private String name;
	private int age;
	private World location;
	private Being mother;
	private Being father;
	private Being partner;
	
	
	public Human(String name, int age) {
		super(name, age);
	}
	
	public Human(String name) {
		super(name);
	}
	
	public Human(int age) {
		super(age);
	}
	
	public Human() {
		super();
	}
	
	

	
}
