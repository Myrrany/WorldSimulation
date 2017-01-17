package WorldSimulation.orderchaos;

public class Human extends Being {

	private boolean possessed;
	private Demon possesser;
	
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
	
	public void becomePossessed(Demon name) {
		this.possessed = true;
		this.possesser = name;
	}

	public void deposses(){
		this.possessed = false;
		this.possesser = null;
	}
	
	public String getPossessionStatus() {
		return "Possessed by " + (this.possessed == true ? this.possesser : "no one.");
	}
	
}
