package assignment5;

public class Animate extends Thread {
	public void run() {
		while(true) {
			Critter.worldTimeStep();
			Critter.displayWorld(Main.gc);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
