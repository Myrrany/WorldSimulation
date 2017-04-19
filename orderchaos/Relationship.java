package WorldSimulation.orderchaos;

import WorldSimulation.creatures.Being;

/**
 * Created by myrthe on 15-4-17.
 */
public class Relationship {

	private Being firstPerson;
	private Being secondPerson;

	public Relationship(Being first, Being second) {
		this.firstPerson = first;
		this.secondPerson = second;
	}

	public Being getFirstPerson() {
		return firstPerson;
	}

	public Being getSecondPerson() {
		return secondPerson;
	}


}
