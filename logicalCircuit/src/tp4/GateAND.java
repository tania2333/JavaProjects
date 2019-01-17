package tp4;

public class GateAND extends Gate2{
	public GateAND(Signal input1,Signal input2,Signal output,int delay)
	{
		super(input1,input2,output,delay);		
	}
	public void setOutput()
	{
		if((input1.IsUpdatedNow)||(input2.IsUpdatedNow))
		{
			boolean res = input1.getValue() & input2.getValue();
			Event e = new Event(Clock.currentTime()+delay,res);
			output.addEvent(e);
		}
	}
}
