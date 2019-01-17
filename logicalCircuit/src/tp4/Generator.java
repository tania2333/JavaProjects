package tp4;

public class Generator extends Gate{
	protected int period;

	public Generator(Signal output,int delay,int period)
	{
		super(output,delay);
		this.period=period;
	}
	
	public void setOutput()
	{
		if(output.getValue())
		{
			Event e1 = new Event(Clock.currentTime()+period+delay, false);
			output.addEvent(e1);
		}
		else 
		{
			Event e2 = new Event(Clock.currentTime()+period+delay, true);
			output.addEvent(e2);
		}
	}
}
