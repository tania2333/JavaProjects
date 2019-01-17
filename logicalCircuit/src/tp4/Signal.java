package tp4;

public class Signal {
	private boolean value;
	Driver driver;
	protected boolean IsUpdatedNow;
	
	public Signal(boolean value)
	{
		this.value = value;
		driver = new Driver();
		IsUpdatedNow = false;
	}
	
	public Signal()
	{
		this.value = false;
		driver = new Driver();
	}
	
	
	public boolean getValue()
	{
		return value;
	}
	public void addEvent(Event e) {
		driver.addEvent(e);
	}
	public void actualise() {
		if(driver.timeOfNextEvent()==Clock.currentTime())
		{	
			value=driver.nextValue();
			IsUpdatedNow = true;
		}
		else IsUpdatedNow=false;
	}
	public static void main(String []arg) {
		
		
		Signal s = new Signal(false);
		s.addEvent(new Event(3,true));
		s.addEvent(new Event(5,false));
		s.addEvent(new Event(9,true));
		s.addEvent(new Event(50,false));
		
		Clock.reset();
		int n=100;
		
		for(int i=0; i<n;i++) {
			s.actualise();
			System.out.println("au temps "+Clock.currentTime()+", la valeur du signal est "+s.getValue());
			Clock.increment();
		}
		
	}
}
