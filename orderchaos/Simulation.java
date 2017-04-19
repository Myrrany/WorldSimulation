package WorldSimulation.orderchaos;

import WorldSimulation.creatures.Being;
import WorldSimulation.exceptions.NoPartnerException;
import WorldSimulation.genetics.GeneticsUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class Simulation {

	private Set<Being> population;
	private List<Relationship> ships;

	Simulation() {
		population = new HashSet<>();
		ships = new ArrayList<>();
	}
//TODO: add Javadoc

	/**
	 * Method to let two Beings conceive a child.
	 *
	 * @param ship The relationship of which the kid should be made
	 * @return Child if there are two parents, nothing if only one.
	 */
	public static Being getChild(Relationship ship) throws IOException, NoPartnerException {
		int children;

		if (ship != null) {
			Being mom = ship.getFirstPerson();
			Being dad = ship.getSecondPerson();
			if (mom.getChildren().size() == 0) {
				children = 1;
			} else {
				children = mom.getChildren().size();
			}

			double curve = ((-((mom.getAge() - 35) * (mom.getAge() - 35)) / 400d) + 0.75)
					/ children;
			double secondCurve = ((-((dad.getAge() - 35)
					* (dad.getAge() - 35)) / 400d) + 0.75) / children;
			double rand = Math.random();
			if (rand <= curve + secondCurve) {
				Being child = new Being(generateName());
				child.setParents(mom, dad);
				// Child's genome is determined by the parents' genome
				child.setGenome(GeneticsUtils.breedGenes(mom.getGeneArray(), dad.getGeneArray()));
				mom.addChild(child);
				dad.addChild(child);
				System.out.println(mom.getName() + " and " + dad.getName()
						+ " conceived a child named " + child.getName() + ".");
				return child;
			} else {
				System.out.println(mom.getName() + " and " + dad.getName()
						+ " could not conceive a child.");
				return null;
			}
		} else {
			return null;
		}
	}

	public static String generateName() throws IOException {
		BufferedReader firstNameReader = new BufferedReader(new FileReader("/home/myrthe/workspace/RandomBuilds/src/WorldSimulation/orderchaos/firstnames.txt"));
		String firstName;
		BufferedReader lastNameReader = new BufferedReader(new FileReader("/home/myrthe/workspace/RandomBuilds/src/WorldSimulation/orderchaos/surnames.txt"));
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
		return firstName + " " + lastName;
	}


	public void removeRelationship(Relationship ship) {
		ships.remove(ships.indexOf(ship));
	}


	public void makeMatches() {
		ArrayList<Being> single = population.stream().filter(beings -> beings.getShip() == null && beings.getAge() > 16 && beings.getStatus()).collect(Collectors.toCollection(ArrayList::new));
		single.sort((b1, b2) -> b1.getAge() - b2.getAge());

		for (int i = 0; i < single.size(); i += 2) {
			if (i + 1 != single.size()) {
				Relationship temp = new Relationship(single.get(i), single.get(i + 1));
				ships.add(temp);
				single.get(i).setShip(temp);
				single.get(i + 1).setShip(temp);
			} else {
				break;
			}
			System.out.println(single.get(i).getName() + " matched with "
					+ single.get(i + 1).getName() + "\n");
		}
	}

	public boolean initialPopulation(int amount) throws IOException {
		for (int i = 0; i < amount; i++) {
			int age = ThreadLocalRandom.current().nextInt(0, 100);
			Being temp = new Being(age);
			temp.setGenome(GeneticsUtils.generateGenome());
			population.add(temp);
			System.out.println("Being " + temp.getName() + " succesfully created!\n");
		}
		return false;
	}

	public void findBeing(String name) {
		boolean found = false;
		Iterator<Being> it = population.iterator();
		while (it.hasNext() && !found) {
			Being person = it.next();
			if (person.getName().equalsIgnoreCase(name)) {
				System.out.println(person.toString());
				found = true;
			}
		}
		if (!found) {
			System.out.println("Being " + name + " not found");
		}
	}

	//TODO: Make a method to add multiple years at once
	private void addYear() {
		for (Being person : population
				) {
			{
				if (person.getStatus()) {
					person.yearPassed();
					if (person.getStatus()) {
						removeRelationship(person.getShip());
					}
				}
			}
		}

	}

	public void time() throws IOException {
		makeMatches();
		makeBabies();
		addYear();

	}

	private void makeBabies() throws IOException {
		try {
			for (Relationship ship : ships) {
				Being tempKid = getChild(ship);
				if (tempKid != null) {
					population.add(tempKid);
				}
			}
		} catch (NoPartnerException e) {
			System.err.println(e.getMessage());
		}
	}

	public void getPopulation() {
		for (Being person : population) {
			System.out.println(person.getName() + ", " + person.getAge() + ", "
					+ (person.getStatus() ? "alive" : "deceased"));
		}
	}

	public void getLivingPopulation() {
		for (Being person : population) {
			{
				if (person.getStatus()) {
					System.out.println(person.getName() + ", " + person.getAge());
				}
			}
		}
	}

	public void getDeadPopulation() {
		for (Being person : population
				) {
			{
				if (!person.getStatus()) {
					System.out.println(person.getName() + ", " + person.getAge());
				}
			}
		}
	}
}

//