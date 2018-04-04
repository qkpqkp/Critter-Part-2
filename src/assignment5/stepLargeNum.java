package assignment5;

public class stepLargeNum implements Runnable{
	private int times;
	public stepLargeNum(int num) {
		times = num;
	}
	public void run() {
		Thread Wait = new Wait();
		Wait.start();
		for(int i = 0;i<times;i++) {
			Critter.worldTimeStep();
		}
		Wait.stop();
		System.out.println("Done for " + times + " steps!");
	}
}
