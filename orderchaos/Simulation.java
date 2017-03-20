package WorldSimulation.orderchaos;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

class Simulation {

	private Set<Being> population;
	private Map<Being, Being> couples;

	Simulation() {
		population = new HashSet<>();
		couples = new HashMap<>();
	}

	void makeMatches() {
		ArrayList<Being> single = population.stream().filter(beings -> beings.getPartner() == null && beings.getAge() > 16 && beings.getStatus()).collect(Collectors.toCollection(ArrayList::new));
		single.sort((b1, b2) -> b1.getAge() - b2.getAge());

		for (int i = 0; i < single.size(); i += 2) {
			if (i + 1 != single.size()) {
				single.get(i).setPartner(single.get(i + 1));
				single.get(i + 1).setPartner(single.get(i));
			} else {
				break;
			}
			System.out.println(single.get(i).getName() + " matched with "
					+ single.get(i + 1).getName() + "\n");
			couples.put(single.get(i), single.get(i + 1));
		}
	}

	boolean initialPopulation(int amount) throws IOException {
		for (int i = 0; i < amount; i++) {
			int age = ThreadLocalRandom.current().nextInt(0, 100);
			Being temp = new Being(age);
			temp.setGenome(GeneticsUtils.generateGenome());
			population.add(temp);
			System.out.println("Being " + temp.getName() + " succesfully created!\n");
		}
		return false;
	}

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

	private void addYear() {
		for (Being person : population
				) {
			{
				if (person.getStatus()) {
					person.yearPassed();
				}
			}
		}

	}

	void time() throws IOException {
		makeMatches();
		makeBabies();
		addYear();

	}

	private void makeBabies() throws IOException {
		try {
			for (Map.Entry<Being, Being> entry : couples.entrySet()) {
				Being tempKid = entry.getKey().getChild();
				if (tempKid != null) {
					population.add(tempKid);
				}
			}
		} catch (NoPartnerException e) {
			System.err.println(e.getMessage());
		}
	}

	void getPopulation() {
		for (Being person : population) {
			System.out.println(person.getName() + ", " + person.getAge() + ", "
					+ (person.getStatus() ? "alive" : "deceased"));
		}
	}

	void getLivingPopulation() {
		for (Being person : population) { {
			if (person.getStatus()) {
				System.out.println(person.getName() + ", " + person.getAge());
			}
		}}
	}

	void getDeadPopulation() {
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