package WorldSimulation.orderchaos;

import WorldSimulation.creatures.Being;

/**
 * This class keeps track of a relationship, who is in it et cetera.
 *
 * Created by Myrthe on 15-4-17.
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
