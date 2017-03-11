package assignment4;

import assignment4.Critter.TestCritter;

public class MyCritter6 extends TestCritter {
	private static final int GENE_TOTAL = 24;
	private int[] genes = new int[8];
	private int dir;
	
	public MyCritter6() {
		for (int k = 0; k < 8; k += 1) {
			genes[k] = GENE_TOTAL / 8;
		}
		dir = Critter.getRandomInt(8);
	}

	@Override
	public void doTimeStep() {
		/*if (getEnergy() > 150) {
			MyCritter6 child = new MyCritter6();
			for (int k = 0; k < 8; k += 1) {
				child.genes[k] = this.genes[k];
			}
			int g = Critter.getRandomInt(8);
			while (child.genes[g] == 0) {
				g = Critter.getRandomInt(8);
			}
			child.genes[g] -= 1;
			g = Critter.getRandomInt(8);
			child.genes[g] += 1;
			reproduce(child, Critter.getRandomInt(8));
		}
		
		/* pick a new direction based on our genes 
		int roll = Critter.getRandomInt(GENE_TOTAL);
		int turn = 0;
		while (genes[turn] <= roll) {
			roll = roll - genes[turn];
			turn = turn + 1;
		}
		assert(turn < 8);
		
		dir = (dir + turn) % 8;
		*/
		int a= this.getEnergy();
		if(a>Params.run_energy_cost){
			this.run(6);
		}
		else if(a>Params.walk_energy_cost){
			this.walk(6);
		}
		else{
			this.setEnergy(0);
		}
		
	}

	@Override
	public boolean fight(String opponent) {
		run(getRandomInt(8));
		return false;
	}

	@Override
	public String toString () {
		return "6";
	}
	 
	public static void runStats(java.util.List<Critter> myCritter6) {
		int total_straight = 0;
		int total_left = 0;
		int total_right = 0;
		int total_back = 0;
		for (Object obj : myCritter6) {
			MyCritter6 c = (MyCritter6) obj;
			total_straight += c.genes[0];
			total_right += c.genes[1] + c.genes[2] + c.genes[3];
			total_back += c.genes[4];
			total_left += c.genes[5] + c.genes[6] + c.genes[7];
		}
		System.out.print("" + myCritter6.size() + " total Craigs    ");
		System.out.print("" + total_straight / (GENE_TOTAL * 0.01 * myCritter6.size()) + "% straight   ");
		System.out.print("" + total_back / (GENE_TOTAL * 0.01 * myCritter6.size()) + "% back   ");
		System.out.print("" + total_right / (GENE_TOTAL * 0.01 * myCritter6.size()) + "% right   ");
		System.out.print("" + total_left / (GENE_TOTAL * 0.01 * myCritter6.size()) + "% left   ");
		System.out.println();
	}
}
