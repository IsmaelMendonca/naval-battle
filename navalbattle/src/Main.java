import base.Ocean;

public class Main {
	public static void main(String[] args) {
		try {
			Ocean ocean = Ocean.getInstance();
			ocean.drawCleanOcean();
		} catch (Exception e) {
			System.out.println(e.getMessage() + "Adeus T.T");
		}
	}
}
