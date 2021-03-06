package assignment5;
//hi
import java.util.List;

import assignment5.Critter;
import assignment5.InvalidCritterException;
import assignment5.Params;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class Critter {
	/* NEW FOR PROJECT 5 */
	public enum CritterShape {
		CIRCLE,
		SQUARE,
		TRIANGLE,
		DIAMOND,
		STAR,
        RECTANGLE,
        OVAL,
        HEX



	}
	
	/* the default color is white, which I hope makes critters invisible by default
	 * If you change the background color of your View component, then update the default
	 * color to be the same as you background 
	 * 
	 * critters must override at least one of the following three methods, it is not 
	 * proper for critters to remain invisible in the view
	 * 
	 * If a critter only overrides the outline color, then it will look like a non-filled 
	 * shape, at least, that's the intent. You can edit these default methods however you 
	 * need to, but please preserve that intent as you implement them. 
	 */
	public javafx.scene.paint.Color viewColor() { 
		return javafx.scene.paint.Color.WHITE; 
	}
	
	public javafx.scene.paint.Color viewOutlineColor() { return viewColor(); }
	public javafx.scene.paint.Color viewFillColor() { return viewColor(); }
	
	public abstract CritterShape viewShape(); 
	
	private static String myPackage;
	private	static List<Critter> population = new java.util.ArrayList<Critter>();
	private static List<Critter> babies = new java.util.ArrayList<Critter>();
	protected boolean permit_to_move;
	private int energy = 0;
	protected int getX() { return x_coord;}
	protected int getY() { return y_coord;}


	// Gets the package name.  This assumes that Critter and its subclasses are all in the same package.
	static {
		myPackage = Critter.class.getPackage().toString().split(" ")[1];
	}
	
	protected final String look(int direction, boolean steps) {
		if(steps==false) {
			walk(direction);
			energy += Params.walk_energy_cost;
			for(int i = 0;i<population.size();i++) {
				if(!population.get(i).equals(this)) {
					if(population.get(i).x_coord==x_coord&&population.get(i).y_coord==y_coord) {
						walk((direction+4)%8);
						energy += Params.walk_energy_cost;
						energy -= Params.look_energy_cost;
						return population.get(i).toString();
					}
				}
			}
			walk((direction+4)%8);
			energy += Params.walk_energy_cost;
			energy -= Params.look_energy_cost;
			return null;
		}
		else{
			run(direction);
			energy += Params.run_energy_cost;
			for(int i = 0;i<population.size();i++) {
				if(!population.get(i).equals(this)) {
					if(population.get(i).x_coord==x_coord&&population.get(i).y_coord==y_coord) {
						run((direction+4)%8);
						energy += Params.run_energy_cost;
						energy -= Params.look_energy_cost;
						return population.get(i).toString();
					}
				}
			}
			run((direction+4)%8);
			energy += Params.run_energy_cost;
			energy -= Params.look_energy_cost;
			return null;
		}
	}
	
	/* rest is unchanged from Project 4 */
	
	
	private static java.util.Random rand = new java.util.Random();
	public static int getRandomInt(int max) {
		return rand.nextInt(max);
	}
	
	public static void setSeed(long new_seed) {
		rand = new java.util.Random(new_seed);
	}
	
	
	/* a one-character long string that visually depicts your critter in the ASCII interface */
	public String toString() { return ""; }
	
	//private int energy = 0;
	protected int getEnergy() { return energy; }
	
	private int x_coord;
	private int y_coord;
	
	protected final void walk(int direction) {
		if(permit_to_move==true) {
			switch(direction) {
				case 0: x_coord++;
					if (x_coord==Params.world_width){
						x_coord=0;
					}
						break;
				case 1: x_coord++;
						y_coord--;
					if (x_coord==Params.world_width){
						x_coord=0;
					}
					if (y_coord<0){
						y_coord=Params.world_height-1;
					}
						break;
				case 2: y_coord--;
					if (y_coord<0){
						y_coord=Params.world_height-1;
					}
						break;
				case 3: x_coord--;
						y_coord--;
					if (x_coord<0){
						x_coord=Params.world_width-1;
					}
					if (y_coord<0){
						y_coord=Params.world_height-1;
					}
						break;
				case 4: x_coord--;
						if (x_coord<0){
							x_coord=Params.world_width-1;
						}
						break;
				case 5: x_coord--;
						y_coord++;
					if (x_coord<0){
						x_coord=Params.world_width-1;
					}
					if (y_coord==Params.world_height){
						y_coord=0;
					}
						break;
				case 6: y_coord++;
					if (y_coord==Params.world_height){
						y_coord=0;
					}
						break;
				case 7: x_coord++;
						y_coord++;
					if (x_coord==Params.world_width){
						x_coord=0;
					}
					if (y_coord==Params.world_height){
						y_coord=0;
					}
						break;
				default: break;
			}
		}
		energy -= Params.walk_energy_cost;
	}
	
	protected final void run(int direction) {
		if(permit_to_move==true) {
			switch(direction) {
				case 0: x_coord+=2;
					if (x_coord>=Params.world_width){
						x_coord=x_coord-Params.world_width;
					}
						break;
				case 1: x_coord+=2;
						y_coord-=2;
					if (x_coord>=Params.world_width){
						x_coord=x_coord-Params.world_width;
					}
					if (y_coord<0){
						y_coord=y_coord+Params.world_height;
					}
						break;
				case 2: y_coord-=2;
					if (y_coord<0){
						y_coord=y_coord+Params.world_height;
					}
						break;
				case 3: x_coord-=2;
						y_coord-=2;
					if (x_coord<0){
						x_coord=x_coord+Params.world_width;
					}
					if (y_coord<0){
						y_coord=y_coord+Params.world_height;
					}
						break;
				case 4: x_coord-=2;
					if (x_coord<0){
						x_coord=x_coord+Params.world_width;
					}
						break;
				case 5: x_coord-=2;
						y_coord+=2;
					if (x_coord<0){
						x_coord=x_coord+Params.world_width;
					}
					if (y_coord>=Params.world_height){
						y_coord=y_coord-Params.world_height;
					}
						break;
				case 6: y_coord+=2;
					if (y_coord>=Params.world_height){
						y_coord=y_coord-Params.world_height;
					}
						break;
				case 7: x_coord+=2;
						y_coord+=2;
					if (x_coord>=Params.world_width){
						x_coord=x_coord-Params.world_width;
					}
					if (y_coord>=Params.world_height){
						y_coord=y_coord-Params.world_height;
					}
						break;
				default: break;
			}
		}
		energy -= Params.run_energy_cost;
	}
	
	protected final void reproduce(Critter offspring, int direction) {}

	public abstract void doTimeStep();
	public abstract boolean fight(String oponent);
	
	
	public static void worldTimeStep() {
		int roll1=0, roll2=0;
		//Add all babies to population
		for(int l = 0; l < babies.size(); l++) {
			population.add(babies.get(l));
		}
		babies.clear();
		//Do time step
		for(int i = 0;i<population.size();i++) {
			population.get(i).doTimeStep();
			population.get(i).energy = (population.get(i).getEnergy() - Params.rest_energy_cost);
			if(population.get(i).getEnergy()<=0) {
				population.remove(i);
				i--;
			}
		}
		//Check encountered,if two have same position
		for(int j = 0; j < population.size();j++) {
			for (int k = j + 1; k < population.size(); k++) {
				if (population.get(j).x_coord == population.get(k).x_coord) {
					if (population.get(j).y_coord == population.get(k).y_coord) {
						//decide to fight or move

						if (!population.get(j).fight(population.get(k).toString())){
							roll1=0;
						}
						else{
							if(population.get(j).getEnergy()>0) {
								roll1=(getRandomInt(population.get(j).getEnergy()));
							}
						}
						if (!population.get(k).fight(population.get(j).toString())){
							roll2=0;
						}
						else {
							if(population.get(k).getEnergy()>0) {
								roll2=getRandomInt(population.get(k).getEnergy());
							}
						}
					}
				}
				//Still need to check if they are still in the same position
				if (population.get(j).x_coord == population.get(k).x_coord) {
					if (population.get(j).y_coord == population.get(k).y_coord) {	
						//Not enough energy, die
						if (population.get(k).getEnergy() <= 0) {
							population.remove(k);
							k--;
							if (population.get(j).getEnergy() <= 0) {
								population.remove(j);
								j--;
								continue;
							}
							continue;
						}
						if (roll1>=roll2) {
							population.get(j).energy += 0.5 * population.get(k).energy;
							population.get(k).energy=0;
						}
						else {
							population.get(k).energy+=0.5*population.get(j).energy;
							population.get(j).energy=0;

						}
					}
				}
			}
		}
		for(int i = 0; i < Params.refresh_algae_count;i++) {
			try {
				makeCritter("Algae");
			} catch (InvalidCritterException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public static void displayWorld(GraphicsContext gc) {
		int width = Params.world_width*8;
		int height = Params.world_height*8;
		gc.clearRect(0,0,Params.world_width*8, Params.world_height*8);
		// vertical lines
				gc.setStroke(Color.BLUE);
				for(int i = 8 ; i <width ; i+=8){
					gc.strokeLine(i, 0, i, height );
				}
				// horizontal lines
				gc.setStroke(Color.BLUE);
				for(int i = 8;i<height;i+=8) {
					gc.strokeLine(0, i, width, i);
				}
		for(int i = 0;i<population.size();i++) {
			View.PrintCritter(gc, population.get(i));
		}
	} 
	/* Alternate displayWorld, where you use Main.<pane> to reach into your
	   display component.
	   // public static void displayWorld() {}
	*/
	
	/* create and initialize a Critter subclass
	 * critter_class_name must be the name of a concrete subclass of Critter, if not
	 * an InvalidCritterException must be thrown
	 */
	public static void makeCritter(String critter_class_name) throws InvalidCritterException {
		try {
			Class c = Class.forName("assignment5." + critter_class_name);
			Critter crit=(Critter) c.newInstance();


				crit.x_coord = getRandomInt(Params.world_width);
				crit.y_coord = getRandomInt(Params.world_height);
				crit.energy = Params.start_energy;
				crit.permit_to_move = true;

				population.add(crit);


		} catch (ClassNotFoundException e) {
			throw new InvalidCritterException(critter_class_name);

		}
		catch(InstantiationException e){

			throw new InvalidCritterException(critter_class_name);
		}

		catch(IllegalAccessException e){

			throw new InvalidCritterException(critter_class_name);

		}
	}
	
	public static List<Critter> getInstances(String critter_class_name) throws InvalidCritterException {
		List<Critter> result = new java.util.ArrayList<Critter>();
		for(Critter c: population) {
			if(c.getClass().getName().equals("assignment5." + critter_class_name)) {
				result.add(c);
			}
		}
		return result;
	}
	
	public static String runStats(List<Critter> critters) {
		String text=new String();
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

		for (String s : critter_count.keySet()) {
			if (critter_count.get(s)==null){
				continue;

			}
			String newLine = System.getProperty("line.separator");
		    text+="There are "+ critter_count.get(s) + " of " +s;


		}
		return text;
	}
	
	/* the TestCritter class allows some critters to "cheat". If you want to 
	 * create tests of your Critter model, you can create subclasses of this class
	 * and then use the setter functions contained here. 
	 * 
	 * NOTE: you must make sure thath the setter functions work with your implementation
	 * of Critter. That means, if you're recording the positions of your critters
	 * using some sort of external grid or some other data structure in addition
	 * to the x_coord and y_coord functions, then you MUST update these setter functions
	 * so that they correctup update your grid/data structure.
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

	}
	
	
}
