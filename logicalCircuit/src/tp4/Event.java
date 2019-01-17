package tp4;

public class Event {
	private boolean value;
	private int time;
	public Event()
	{
		value = false;
		time = 0;
	}
	public Event(int time, boolean value)
	{
		this.time = time;
		this.value = value;
	}
	public int time() 
	{
		return time;
	}
	public boolean value() 
	{
		return value;
	}
}
