package WorldSimulation.orderchaos;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Simulation {

	public Set<Being> population;
	public Map<Being, Being> couples;

	public Simulation() {
		population = new HashSet<Being>();
		couples = new HashMap<Being, Being>();
	}

	public void makeMatches() {
		ArrayList<Being> single = new ArrayList<Being>();
		for (Being beings : population) {
			if (beings.getPartner() == null && beings.getAge() > 16 && beings.getStatus()) {
				single.add(beings);
			}
		}
		single.sort(new Comparator<Being>() {
			@Override
			public int compare(Being b1, Being b2) {
				return b1.getAge() - b2.getAge();
			}
		});

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

	public void addYear() {
		for (Being person : population
				) {
			{
				if (person.getStatus()) {
					person.yearPassed();
				}
			}
		}

	}

	public void time() throws IOException {
		makeMatches();
		makeBabies();
		addYear();

	}

	public void makeBabies() throws IOException {
		for (Map.Entry<Being, Being> entry : couples.entrySet()) {
			Being tempKid = entry.getKey().getChild();
			if (tempKid != null) {
				population.add(tempKid);
			}
		}
	}

	public void getPopulation() {
		for (Being person : population) {
			System.out.println(person.getName() + ", " + person.getAge() + ", "
					+ (person.getStatus() ? "alive" : "deceased"));
		}
	}

	public void getLivingPopulation() {
		for (Being person : population) { {
			if (person.getStatus()) {
				System.out.println(person.getName() + ", " + person.getAge());
			}
		}}
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