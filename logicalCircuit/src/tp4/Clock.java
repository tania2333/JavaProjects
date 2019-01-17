package tp4;

public class Clock {
	private static int i;
	
	
	public static void reset() {
		i=0;
	}
	public static void increment() {
		i++;
	}
	public static int currentTime() {
		return i;
	}
}
