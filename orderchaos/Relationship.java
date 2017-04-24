package WorldSimulation.orderchaos;

import WorldSimulation.creatures.Being;

/**
 * This class keeps track of a relationship, who is in it etcetera.
 *
 * @author Myrthe Hultermans
 * @version 1.0
 */
public class Relationship {

	private Being firstPerson;
	private Being secondPerson;

	/**
	 * This method constructs a new Relationship. If only real life was this easy.
	 *
	 * @param first  The first Being in the Relationship.
	 * @param second The second Being in the Relationship.
	 */
	public Relationship(Being first, Being second) {
		this.firstPerson = first;
		this.secondPerson = second;
	}

	/**
	 * This method gets the first Being in a Relationship.
	 *
	 * @return The first Being.
	 */
	public Being getFirstPerson() {
		return firstPerson;
	}

	/**
	 * This method gets the second Being in a Relationship.
	 *
	 * @return The second Being.
	 */
	public Being getSecondPerson() {
		return secondPerson;
	}


}
