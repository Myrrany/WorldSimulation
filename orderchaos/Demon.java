package WorldSimulation.orderchaos;

import java.io.IOException;

public class Demon extends Being {

	private boolean possessing;
	private Human possessee;

	public Demon(String name, int age) {
		super(name, age);
	}

	public Demon(String name) {
		super(name);
	}

	public Demon(int age) throws IOException {
		super(age);
	}

	public Demon() {
		super();
	}

	public void posses(Human being) {
		being.becomePossessed(this);
		this.possessee = being;
		this.possessing = true;
	}

	public void stopPossessing() {
		this.possessing = false;
		possessee.deposses();
		this.possessee = null;
	}

	public String getPossessionStatus() {
		return "Possessing " + (this.possessing ? this.possessee : "no one.");
	}

}
