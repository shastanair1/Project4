package assignment4;

import assignment4.Critter.TestCritter;

public class MyCritter7 extends TestCritter {
	
	@Override
	public void doTimeStep() {
		int a= this.getEnergy();
		if(a>Params.run_energy_cost){
			this.run(7);
		}
		else if(a>Params.walk_energy_cost){
			this.walk(7);
		}
		else{
			this.setEnergy(0);
		}
		
	}

	@Override
	public boolean fight(String opponent) {

		return true;
	}

	@Override
	public String toString () {
		return "7";
	}
}
