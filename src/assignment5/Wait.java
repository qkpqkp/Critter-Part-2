package assignment5;

public class Wait extends Thread {
	public void run() {
		System.out.print("Please wait");
		while(true) {
			for(int i = 0;i<20;i++) {
				System.out.print(".");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.out.println("");
		}
	}
}
