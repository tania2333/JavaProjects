package tp4;

public abstract class Gate {
	protected Signal output;
	protected int delay;
	
	public Gate(Signal output,int delay)
	{
		this.output=output; //new Signal(output.getValue());
		this.delay=delay;
	}
	
	public abstract void setOutput();
}
