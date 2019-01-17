package tp4;

public class Driver {
	private Event[] events;
	private int index;
	private boolean currentValue;
	
	public Driver() 
	{
		events = new Event[100] ;
		for(int i=0;i<100;i++)
		{
			events[i] = new Event();
		}
		index = 0;
		currentValue =false;
	}
	

	
	public void addEvent(Event e)
	{
		events[index++] = e;
	}
	
	public int timeOfNextEvent()
	{
		if(index!=0)
			return events[0].time();
		return 0;
	}
	public boolean valueOfNextEvent() {
		return events[0].value();
	}
	
	public boolean nextValue()
	{
		currentValue=events[0].value();
		if (index!=0) {
			events[0]=events[1];
		
			for(int i=1;i<index;i++) {
				events[i]=events[i+1];
				
			}
			index--;
		}
		return currentValue;
	}
	
}
