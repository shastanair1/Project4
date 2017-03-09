package assignment4;

import java.util.*;

public class MyCritter1 extends Critter.TestCritter {

	@Override
	public void doTimeStep() {
		int a= this.getEnergy();
		if(a>Params.run_energy_cost){
			this.run(1);
		}
		else if(a>Params.walk_energy_cost){
			this.walk(1);
		}
		else{
			this.setEnergy(0);
		}
		
	}

	@Override
	public boolean fight(String opponent) {
		if (getEnergy() > 10) return true;
		return false;
	}
	
	public String toString() {
		return "1";
	}
	
	public void test (List<Critter> l) {
		
	}
}
