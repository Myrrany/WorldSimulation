package WorldSimulation.orderchaos;

import java.io.IOException;
import java.util.Scanner;

public class SimulationTUI extends Thread {

	private String help = "Possible commands: \n"
			+ "\nINITIATE [amount]: Initiates a population of [amount] people with random names."
			+ "\nROMANCE: Gives singles a partner." + "\nFIND [name]: Finds a specific being." +
			"\nAGE: Lets a year pass." + "\nALL [ALIVE/DEAD]: Gives a list of all [ALIVE/DEAD] beings in the simulation.";

	private Simulation simul;

	public SimulationTUI() {

	}

	public void run() {

		Scanner s = new Scanner(System.in);
		System.out.println("Welcome!");
		System.out.println("Starting simulation!");
		this.simul = new Simulation();
		System.out.println("Simulation started!");
		System.out.println(help);
		try {
			while (true) {
				String com = s.next();
				String rest = s.nextLine();
				// parse(com, rest);

				if (parse(com, rest)) {
					break;
				}
			}
		} catch (InterruptedException | IOException e) {
			s.close();
			e.printStackTrace();
		}
	}

	private boolean parse(String com, String rest) throws InterruptedException, IOException {
		if (com.equalsIgnoreCase("INITIATE")) {
			simul.initialPopulation(Integer.parseInt(rest.substring(1)));
		} else if (com.equalsIgnoreCase("ROMANCE")) {
			simul.makeMatches();
		} else if (com.equalsIgnoreCase("FIND")) {
			simul.findBeing(rest.substring(1));
		} else if (com.equalsIgnoreCase("AGE")) {
			simul.time();
		} else if (com.equalsIgnoreCase("ALL")) {
			if (rest.equalsIgnoreCase(" ALIVE")) {
				simul.getLivingPopulation();
			} else if (rest.equalsIgnoreCase(" DEAD")) {
				simul.getDeadPopulation();
			} else {
				simul.getPopulation();
			}
		} else if (com.equalsIgnoreCase("EXIT")) {
			System.out.println("Goodbye system");
			this.interrupt();
		} else if (com.equalsIgnoreCase("HELP")) {
			System.out.println(help);
		}
		return false;

	}

	public static void main(String[] args) {
		SimulationTUI tui = new SimulationTUI();
		Thread simulation = new Thread(tui);
		simulation.start();

	}
}
