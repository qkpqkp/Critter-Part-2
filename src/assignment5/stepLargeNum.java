package assignment5;

public class stepLargeNum implements Runnable{
	private int times;
	public stepLargeNum(int num) {
		times = num;
	}
	public void run() {
		for(int i = 0;i<times;i++) {
			Critter.worldTimeStep();
		}
		System.out.println("Done for " + times + " steps!");
	}
}
