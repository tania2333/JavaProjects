package tp4;

public abstract class Gate2 extends Gate{
	protected Signal input1;
	protected Signal input2;
	
	public Gate2(Signal input1,Signal input2,Signal output,int delay)
	{
		super(output,delay);
		this.input1 = input1;
		this.input2 = input2;		
	}
}
