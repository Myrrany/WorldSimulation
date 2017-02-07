package WorldSimulation.orderchaos;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class Simulation {

	public Set<Being> population;

	private static BufferedReader reader;

	public Simulation() {
		population = new HashSet<Being>();
	}

	public void makeMatches() {
		ArrayList<Being> single = new ArrayList<Being>();
		for (Being beings : population) {
			if (beings.getPartner() == null && beings.getAge() > 16) {
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
			System.out.println(single.get(i).getName() + " matched with " + single.get(i + 1).getName() + "\n");
		}
	}

	public boolean initialPopulation() throws IOException {
		reader = new BufferedReader(new FileReader(getClass().getResource("names.txt").getPath()));
		String name;
		while ((name = reader.readLine()) != null) {
			int age = ThreadLocalRandom.current().nextInt(0, 100);
			Being temp = new Being(name, age);
			temp.setGenome(GeneticsUtils.generateGenome());
			population.add(temp);
			System.out.println("Being " + temp.getName() + " succesfully created!\n");
		}
		return false;
	}

	public void findBeing(String name) {
		boolean found = false;
		Iterator<Being> it = population.iterator();
		while (it.hasNext() && !found){
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

}





//