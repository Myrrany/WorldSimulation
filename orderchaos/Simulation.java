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

/**
 * This class has all the methods needed for a simulation.
 */
public class Simulation {

	private Set<Being> population;
	private List<Relationship> ships;
	private int year;

	/**
	 * This method constructs a new instance of the class, using empty lists of population and ships, and the year 0.
	 */
	Simulation() {
		population = new HashSet<>();
		ships = new ArrayList<>();
		year = 0;
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
				Being child = new Being(generateName(ship));
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

	/**
	 * This method will randomly generate a name using two predefined files of names.
	 *
	 * @return A first name and a surname.
	 * @throws IOException Because of reading from files, this method might throw an I/O exception.
	 */
	public static String generateName() throws IOException {
		BufferedReader firstNameReader = new BufferedReader(new FileReader
				("firstnames.txt"));
		String firstName;
		BufferedReader lastNameReader = new BufferedReader(new FileReader
				("surnames.txt"));
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

	/**
	 * This method will randomly generate a name using a predefined file and a Relationship where it gets the surname
	 * from.
	 *
	 * @param ship The Relationship to get the surname from.
	 * @return A first name and a surname.
	 * @throws IOException Because of reading from a file, this method might throw an I/O exception.
	 */
	private static String generateName(Relationship ship) throws IOException {
		BufferedReader firstNameReader = new BufferedReader(new FileReader
				("firstnames.txt"));
		String firstName;
		int front = ThreadLocalRandom.current().nextInt(0, 5162);
		for (int i = 0; i < front; i++) {
			firstNameReader.readLine();
		}
		firstName = firstNameReader.readLine();
		String lastName = ship.getFirstPerson().getName().split(" ")[1];
		return firstName + " " + lastName;
	}

	/**
	 * This method will remove a Relationship from the set of Relationships.
	 *
	 * @param ship The Relationship to be removed.
	 */
	private void removeRelationship(Relationship ship) {
		ships.remove(ships.indexOf(ship));
	}


	/**
	 * This method will match single Beings in the population with other singles of their approximate age.
	 */
	//TODO: fix the thing where incest keeps happening
	void makeMatches() {
		ArrayList<Being> single = population.stream().filter(beings -> beings.getShip() == null && beings.getAge() >
				16 && beings.getStatus()).collect(Collectors.toCollection(ArrayList::new));
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

	/**
	 * This method will generate an initial population.
	 *
	 * @param amount The size of the initial population.
	 * @throws IOException Because of generating names from files, this method might throw an I/O exception.
	 */
	void initialPopulation(int amount) throws IOException {
		for (int i = 0; i < amount; i++) {
			int age = ThreadLocalRandom.current().nextInt(0, 100);
			Being temp = new Being(age);
			temp.setGenome(GeneticsUtils.generateGenome());
			population.add(temp);
			System.out.println("Being " + temp.getName() + " succesfully created!\n");
		}
	}

	/**
	 * This being will try to find a Being based on the name.
	 *
	 * @param name The name that should be searched for.
	 */
	void findBeing(String name) {
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

	/**
	 * This method will add a year for every Being in the population.
	 */
	private void addYear() {
		for (Being person : population
				) {
			{
				if (person.getStatus()) {
					person.yearPassed();
					if (!person.getStatus() && person.getShip() != null) {
						removeRelationship(person.getShip());
					}
				}
			}
		}

	}

	/**
	 * This method will make sure the right things happen when a year passes, such as matching singles, procreating and
	 * aging people.
	 *
	 * @throws IOException Because names are generated using a file, this method might throw an I/O exception.
	 */
	void time() throws IOException {
		System.err.println("\nCurrent year: " + year);
		makeMatches();
		makeBabies();
		addYear();
		year++;

	}

	/**
	 * This method will make multiple years pass.
	 *
	 * @param amount The amount of years that need to pass.
	 * @throws IOException Because names are generated using a file, this method might throw an I/O exception.
	 */
	void multipleYears(int amount) throws IOException {
		for (int i = 0; i < amount; i++) {
			time();
		}
	}

	/**
	 * This method will try to get couples to get a baby. Like, all of them.
	 *
	 * @throws IOException Because names are generated using a file, this method might throw an I/O exception.
	 */
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

	/**
	 * This method will print the entire population, including if they are alive or not.
	 */
	void getPopulation() {
		for (Being person : population) {
			System.out.println(person.getName() + ", " + person.getAge() + ", "
					+ (person.getStatus() ? "alive" : "deceased"));
		}
		System.err.println("Total: " + population.size());
	}

	/**
	 * This method will print all alive Beings in the population.
	 */
	void getLivingPopulation() {
		int total = 0;
		for (Being person : population) {
			{
				if (person.getStatus()) {
					System.out.println(person.getName() + ", " + person.getAge());
					total++;
				}
			}
		}
		System.err.println("Total: " + total);
	}

	/**
	 * This method will print all dead Beings in the population.
	 */
	void getDeadPopulation() {
		int total = 0;
		for (Being person : population) {
			{
				if (!person.getStatus()) {
					System.out.println(person.getName() + ", " + person.getAge());
					total++;
				}
			}
		}
		System.err.println("Total: " + total);
	}
}

//