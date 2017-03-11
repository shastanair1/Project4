package assignment4;
/* CRITTERS Critter.java
 * EE422C Project 4 submission by
 * Replace <...> with your actual data.
 * <Student1 Name>
 * <Student1 EID>
 * <Student1 5-digit Unique No.>
 * <Student2 Name>
 * <Student2 EID>
 * <Student2 5-digit Unique No.>
 * Slip days used: <0>
 * Fall 2016
 */


import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* see the PDF for descriptions of the methods and fields in this class
 * you may add fields, methods or inner classes to Critter ONLY if you make your additions private
 * no new public, protected or default-package code or data can be added to Critter
 */


public abstract class Critter {
	private static String myPackage;
	private	static List<Critter> population = new java.util.ArrayList<Critter>();
	private static List<Critter> babies = new java.util.ArrayList<Critter>();
	private static List<Critter> crit  = new java.util.ArrayList<Critter>();
	private boolean hasWalked = false;

	// Gets the package name.  This assumes that Critter and its subclasses are all in the same package.
	static {
		myPackage = Critter.class.getPackage().toString().split(" ")[1];
	}
	
	private static java.util.Random rand = new java.util.Random();
	public static int getRandomInt(int max) {
		return rand.nextInt(max);
	}
	
	public static void setSeed(long new_seed) {
		rand = new java.util.Random(new_seed);
	}
	
	//
	/* a one-character long string that visually depicts your critter in the ASCII interface */
	@Override
	public String toString() { return ""; }
	
	private int energy = 0;
	protected int getEnergy() { return energy; }
	
	private boolean walk_flag = false;
	private int x_coord;
	private int y_coord;
	
	protected final void walk(int direction) {
		//a. Params.walk_energy_cost;
		//a.energy--;
		energy-=Params.walk_energy_cost;
		//is each block just one?
		//how energy drain
		walk_flag = true;
		switch (direction) {
		case (0):
			x_coord++;
			break;
		case (1):
			x_coord++;
			y_coord++;
			break;
		case (2):
			y_coord++;
			break;
		case (3):
			x_coord--;
			y_coord++;
			break;
		case (4):
			x_coord--;
			break;
		case (5):
			x_coord--;
			y_coord--;
			break;
		case (6):
			y_coord--;
			break;
		case (7):
			x_coord++;
			y_coord--;
			break;
		}
		if (x_coord < 0) {
			x_coord = Params.world_width + x_coord;
		}
		if (y_coord < 0) {
			y_coord = Params.world_height +y_coord;
		}
		if(x_coord>Params.world_width){
			x_coord=x_coord-Params.world_width;
		}
		if(y_coord>Params.world_height){
			y_coord=y_coord-Params.world_height;
		}
		//check for other directions
	}
	
	protected final void run(int direction) {
		energy-=Params.run_energy_cost;
		for(int i=0; i<2; i++){
			switch (direction) {
			case (0):
				x_coord++;
				break;
			case (1):
				x_coord++;
				y_coord++;
				break;
			case (2):
				y_coord++;
				break;
			case (3):
				x_coord--;
				y_coord++;
				break;
			case (4):
				x_coord--;
				break;
			case (5):
				x_coord--;
				y_coord--;
				break;
			case (6):
				y_coord--;
				break;
			case (7):
				x_coord++;
				y_coord--;
				break;
			}   
			if (x_coord < 0) {
				x_coord = Params.world_width + x_coord;
			}
			if (y_coord < 0) {
				y_coord = Params.world_height +y_coord;
			}
			if(x_coord>Params.world_width){
				x_coord=x_coord-Params.world_width;
			}
			if(y_coord>Params.world_height){
				y_coord=y_coord-Params.world_height;
			}
		}
	}
	
	protected final void reproduce(Critter offspring, int direction) {
		
		if(offspring.energy >= Params.min_reproduce_energy){
			
			try {
				Critter newCrit = makeCritter(offspring.toString());
				if(newCrit != null && newCrit.equals(population.get(population.size() - 1))){
					population.remove(population.size() - 1);          //Removes the impicit addition to the makeCritteer
					babies.add(newCrit);
					
				newCrit.energy =  offspring.energy / 2;
				offspring.energy = (int) Math.ceil((offspring.energy / 2));
				switch (direction) {
				case (0):
					newCrit.x_coord++;
					break;
				case (1):
					newCrit.x_coord++;
				newCrit.y_coord++;
					break;
				case (2):
					newCrit.y_coord++;
					break;
				case (3):
					newCrit.x_coord--;
				newCrit.y_coord++;
					break;
				case (4):
					newCrit.x_coord--;
					break;
				case (5):
					newCrit.x_coord--;
				newCrit.y_coord--;
					break;
				case (6):
					newCrit.y_coord--;
					break;
				case (7):
					newCrit.x_coord++;
				newCrit.y_coord--;
					break;
				}   
				if (x_coord < 0) {
					x_coord = Params.world_width + x_coord;
				}
				if (y_coord < 0) {
					y_coord = Params.world_height +y_coord;
				}
				if(x_coord>Params.world_width){
					x_coord=x_coord-Params.world_width;
				}
				if(y_coord>Params.world_height){
					y_coord=y_coord-Params.world_height;
				}
				
				babies.add(newCrit);
				}
			} catch (InvalidCritterException e) {
			// TODO Auto-generated catch block
				System.out.println("error processing: ");
			}
		}
		
		
		
	}

	public abstract void doTimeStep();
	public abstract boolean fight(String oponent);
	
	/**
	 * create and initialize a Critter subclass.
	 * critter_class_name must be the unqualified name of a concrete subclass of Critter, if not,
	 * an InvalidCritterException must be thrown.
	 * (Java weirdness: Exception throwing does not work properly if the parameter has lower-case instead of
	 * upper. For example, if craig is supplied instead of Craig, an error is thrown instead of
	 * an Exception.)
	 * @param critter_class_name
	 * @return 
	 * @throws InvalidCritterException
	 */
	public static Critter makeCritter(String critter_class_name) throws InvalidCritterException {
		Critter a;
		critter_class_name = myPackage + "." + critter_class_name;
		/*String value = myPackage + "." + critter_class_name;
		String name = "0";
		try {
			Class<?> d_critter = Class.forName(critter_class_name);
			Class<?> b_critter = Class.forName(critter_class_name);
			
			//Class<Critter> c = b_critter.getCl;*/
		
		Class<?> myCritter = null;
		Constructor<?> constructor = null;
		Object instanceOfMyCritter = null;

		try {
			myCritter = Class.forName(critter_class_name); 	// Class object of specified name
		} catch (ClassNotFoundException e) {
			throw new InvalidCritterException(critter_class_name);
		}
			try {
				constructor = myCritter.getConstructor();
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		// No-parameter constructor object
			try {
				instanceOfMyCritter = constructor.newInstance();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	// Create new object using constructor
			Critter me = (Critter)instanceOfMyCritter;
			population.add(me);
			return me;
		//return null;
	}
	
	
	/**
	 * Gets a list of critters of a specific type.
	 * @param critter_class_name What kind of Critter is to be listed.  Unqualified class name.
	 * @return List of Critters.
	 * @throws InvalidCritterException
	 */
	public static List<Critter> getInstances(String critter_class_name) throws InvalidCritterException {
		try{
		List<Critter> result = new java.util.ArrayList<Critter>();
		Class<?> critterType = Class.forName(critter_class_name);
		Class classType = critterType.getClass();
		//add all instances of the given class to the list
		for(Critter c : Critter.population){
			if (classType.isInstance(c))
		          result.add(c);
		     }
		
	 return result;
		}
		catch (ClassNotFoundException e) {
			throw new InvalidCritterException(critter_class_name);
		}
		//return result;
	}
	
	/**
	 * Prints out how many Critters of each type there are on the board.
	 * @param critters List of Critters.
	 */
	public static void runStats(List<Critter> critters) {
		System.out.print("" + critters.size() + " critters as follows -- ");
		java.util.Map<String, Integer> critter_count = new java.util.HashMap<String, Integer>();
		for (Critter crit : critters) {
			String crit_string = crit.toString();
			Integer old_count = critter_count.get(crit_string);
			if (old_count == null) {
				critter_count.put(crit_string,  1);
			} else {
				critter_count.put(crit_string, old_count.intValue() + 1);
			}
		}
		String prefix = "";
		for (String s : critter_count.keySet()) {
			System.out.print(prefix + s + ":" + critter_count.get(s));
			prefix = ", ";
		}
		System.out.println();		
	}
	
	/* the TestCritter class allows some critters to "cheat". If you want to 
	 * create tests of your Critter model, you can create subclasses of this class
	 * and then use the setter functions contained here. 
	 * 
	 * NOTE: you must make sure that the setter functions work with your implementation
	 * of Critter. That means, if you're recording the positions of your critters
	 * using some sort of external grid or some other data structure in addition
	 * to the x_coord and y_coord functions, then you MUST update these setter functions
	 * so that they correctly update your grid/data structure.
	 */
	static abstract class TestCritter extends Critter {
		protected void setEnergy(int new_energy_value) {
			super.energy = new_energy_value;
		}
		
		protected void setX_coord(int new_x_coord) {
			super.x_coord = new_x_coord;
		}
		
		protected void setY_coord(int new_y_coord) {
			super.y_coord = new_y_coord;
		}
		
		protected int getX_coord() {
			return super.x_coord;
		}
		
		protected int getY_coord() {
			return super.y_coord;
		}
		

		/*
		 * This method getPopulation has to be modified by you if you are not using the population
		 * ArrayList that has been provided in the starter code.  In any case, it has to be
		 * implemented for grading tests to work.
		 */
		protected static List<Critter> getPopulation() {
			return population;
		}
		
		/*
		 * This method getBabies has to be modified by you if you are not using the babies
		 * ArrayList that has been provided in the starter code.  In any case, it has to be
		 * implemented for grading tests to work.  Babies should be added to the general population 
		 * at either the beginning OR the end of every timestep.
		 */
		protected static List<Critter> getBabies() {
			return babies;
		}
	}

	/**
	 * Clear the world of all critters, dead and alive
	 */
	public static void clearWorld() {
		population.clear();
		babies.clear();
	}
	
	public static void worldTimeStep() {
		//List<Critter> Crits = getInstances("Craig");
		List<Critter> Crits = population;
		Critter currentCritter;
		Iterator<Critter> iterate = population.iterator();
		clear_hasWalked();
		for(int i = 0; i<population.size(); i++){
			while(iterate.hasNext()){
				currentCritter = iterate.next();
				
				currentCritter.doTimeStep();
			}
			
		}
		remove_Dead();
		Encounter();
		for(int i = 0; i < Params.refresh_algae_count; i++){
			try {
				Critter.makeCritter("@");
			} catch (InvalidCritterException e) {
				// TODO Auto-generated catch block
				System.out.println("Invalid Critter");
			}
		}
		
		
		// Complete this method.
	}
	private static void clear_hasWalked(){
		Critter myCritter;
		for(int i = 0; i < population.size() - 1; i++){
			myCritter = population.get(i);
			myCritter.hasWalked = false;
		}
	}
	
	private int CheckCritter(Critter a1, Critter a2){
		//a1.fight(a2.toString)
		
		return 0;
		
	}
	private static void remove_Dead(){
		for(int i = 0; i > population.size(); i++){
			if(population.get(i).energy <= 0){
				population.remove(i);
			}
		}
	}
	
	public static void displayWorld() {
		System.out.print("+");
		int[] x_coord = new int[population.size()];
		int[] y_coord = new int[population.size()];
		
		int grid_x = 0;
		int grid_y = 0;
		Iterator<Critter> iterate = population.iterator();
		
		for(int i = 0; i < population.size(); i++){
			while(iterate.hasNext()){
				x_coord[i] = iterate.next().x_coord;
				y_coord[i] = iterate.next().y_coord;
			}
		}
		
		
		for(int i = 0; i < Params.world_width-1; i++){
			System.out.print("-");
		}
		System.out.print("+");
		for(int i = 0; i < Params.world_height-1; i++){
			System.out.print("|");
			grid_y++;
			grid_x = 0;
			//Make a critter and algae location array
			for(int j = 1; j < Params.world_width-1; j++){
				for(int k = 0; k < population.size(); k++){
					if(x_coord[k] == grid_x && y_coord[k]== grid_y){
						System.out.print(population.get(k).toString());
					}
					else{
						System.out.print(" ");
					}
				}
				grid_x++;
			}
			System.out.print("+");
		}
		
	}
	//THis function will go through the total list of critters and determine whether or not there are any in the smae position
	private static void Encounter(){
		Critter currentCritter = null;
		Critter checkCritter = null;
		ArrayList<ArrayList<Critter>> CritterList = new ArrayList<ArrayList<Critter>>();
		Set<Integer> value_x = new HashSet<Integer>();
		Set<Integer> value_y = new HashSet<Integer>();
		//ArrayList<Critter> sameLocation = new ArrayList<Critter>();
		//Iterator<Critter> iterate = sameLocation.iterator();
		for(int i = 0; i < population.size(); i++){
			ArrayList<Critter> sameLocation = new ArrayList<Critter>();
			Iterator<Critter> iterate = population.iterator();
			//this puts us in the right position
			for(int j  = 0; j< i; j++){
				if(iterate.hasNext()){
					iterate.next();
				}
			}
			while(iterate.hasNext()){
				currentCritter = iterate.next();
				if(value_x.contains(currentCritter.x_coord) && value_y.contains(currentCritter.y_coord)){
					
				}else{
					value_x.add(currentCritter.x_coord);
					value_y.add(currentCritter.y_coord);
				sameLocation.add(currentCritter);
				for(int j = i; j < population.size(); j++){
					//
					//If we were going ot add the babie to the population before this, we can write the code write here, but i think
					//thats whythey have a list dedicated to babies
					//
					checkCritter = iterate.next();
					if(checkCritter.x_coord == currentCritter.x_coord && checkCritter.y_coord == currentCritter.y_coord){
						sameLocation.add(checkCritter);
						//Adda  set.contains fucntion to the parameters to check that some set doesn not contain x, y location
					}
				}
				}
			}
			//Add the completed list to the list of all coordinate areas
			if(sameLocation.size() > 1){
				CritterList.add(sameLocation);
			}
		}
		
		
		//THis part will take all the lists and implelment the fight sequences for all
		Iterator<ArrayList<Critter>> critterIterate = CritterList.iterator();
		for(int i = 0; i < CritterList.size(); i++){
			ArrayList<Critter> sameLocation2 = new ArrayList<Critter>();
			if(critterIterate.hasNext()){
				sameLocation2 = critterIterate.next();
			}
			//ask Shasta to send me the Critter that wins the fight, or we can define a global variable
			Iterator<Critter> iterate = sameLocation2.iterator();
			while(iterate.hasNext()){
				//Iterator<Critter> iterate = population.iterator();
				if(iterate.hasNext()){
					currentCritter = iterate.next();
				}
				for(int k = (1); k < sameLocation2.size(); k++){
					boolean winner = true;
					if(iterate.hasNext()){
						checkCritter = iterate.next();
					}
					winner = currentCritter.fight(checkCritter.toString());
					if(winner == true){
						iterate = sameLocation2.iterator();
						sameLocation2.remove(checkCritter);
						if(iterate.hasNext()){
							currentCritter = iterate.next();
						}
					}else{
						iterate = sameLocation2.iterator();
						sameLocation2.remove(currentCritter);
						if(iterate.hasNext()){
							currentCritter = iterate.next();
						}
					}
				}
			}
			
			
		}
	}
}