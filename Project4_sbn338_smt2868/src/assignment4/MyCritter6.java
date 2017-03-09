package assignment4;

import assignment4.Critter.TestCritter;

public class MyCritter6 extends TestCritter {
	
	@Override
	public void doTimeStep() {
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
}
