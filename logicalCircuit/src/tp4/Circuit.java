package tp4;

public class Circuit {
	private Signal[] signals;
	private Gate[] gates;
	private int nums;
	private int numg;
	
	public Circuit()
	{
		signals = new Signal[100];	
		gates = new Gate[100];
		nums = 0;
		numg = 0;
	}
	public void addSignal(Signal s)
	{
		signals[nums++] = s;
	}
	public void addGate(Gate g)
	{
		gates[numg++] = g;
	}
	public int nbreSignaux()
	{
		return nums;
	}
	public Signal getSignal(int index)
	{
		return signals[index];
	}
	public void actualiseSignaux()
	{
		for(int i=0;i<nums;i++)
		{
			signals[i].actualise();
		}
	}
	public void activation()
	{
		for(int i=0;i<numg;i++)
		{
			//if((gates[i].input1.driver.timeOfNextEvent()==Clock.currentTime())||(gates[i].input2.driver.timeOfNextEvent()==Clock.currentTime()))
			gates[i].setOutput();
		}
	}
	public void simule(int tmax)
	{
		Clock.reset();
		for (int i=0; i<tmax;i++)
		{
			actualiseSignaux(); // updating of all signals of the circuit
			activation(); // activation of all the gates of the circuit
			System.out.print(Clock.currentTime());
			for (int j=0; j<nbreSignaux();j++)
				System.out.print("\t"+getSignal(j).getValue());
			System.out.println();
			Clock.increment();
		}
	}
	
	public void readSignal(String filename) {
		FileHandler fh=new FileHandler();
		String []lines=fh.extract(filename);
		for(int i=0; i<lines.length; i++) {
			String []tokens=lines[i].split("\t");
			int index = Integer.parseInt(tokens[0]);
			if (index == nums) {
				boolean b = false;
				if (tokens[1].equals("true")) b=true;
				signals[nums] = new Signal(b);
				nums++;
			}
			else {
				boolean b= false;
				if (tokens[1].equals("true")) b=true;
				Event e = new Event(Integer.parseInt(tokens[2]), b);
				signals[nums-1].addEvent(e);
			}
		}
	}
	
	public void readGate(String filename) {
		FileHandler fh=new FileHandler();
		String []lines=fh.extract(filename);
		for(int i=0; i<lines.length; i++) {
			String []tokens=lines[i].split("\t");
			int index = Integer.parseInt(tokens[0]);
			Signal a = signals[Integer.parseInt(tokens[2])];
			Signal b = signals[Integer.parseInt(tokens[3])];
			Signal c = signals[Integer.parseInt(tokens[4])];
			int d = Integer.parseInt(tokens[5]);
			if (tokens[1].equals("AND"))
			{	
				gates[index] = new GateAND(a,b,c,d);
				numg++;
			}
			else if(tokens[1].equals("OR"))
			{	
				gates[index] = new GateOR(a,b,c,d);
				numg++;
			}
		}
	}
	
	public static void main(String arg[])
	{
		long pre=System.nanoTime();
		Circuit c = new Circuit();
		
		/*
		Signal s1=new Signal(false);
		Signal s2=new Signal(false);
		Signal s3=new Signal(false);
		Signal s4=new Signal(false);
		Signal s5=new Signal(false);
		
		s1.addEvent(new Event(3,true));
		s1.addEvent(new Event(5,false));
		s1.addEvent(new Event(9,true));
		
		s2.addEvent(new Event(4,true));
		s2.addEvent(new Event(8,false));
		s2.addEvent(new Event(20,true));
		
		s4.addEvent(new Event(3,true));
		s4.addEvent(new Event(15,false));
		s4.addEvent(new Event(40,true));
		
		c.addSignal(s1); 
		c.addSignal(s2); 
		c.addSignal(s3);
		c.addSignal(s4); 
		c.addSignal(s5);
		*/
		
		c.readSignal("src/data/signal.txt");
		c.readGate("src/data/gate.txt");
		/*Gate p1=new GateAND(c.signals[0], c.signals[1],c.signals[2],5);
		Gate p2=new GateOR(c.signals[2],c.signals[3],c.signals[4],3);
		
		c.addGate(p1);
		c.addGate(p2);*/
		
		int tmax = 100;
		c.simule(tmax);
		long post=System.nanoTime();
		long temp = post-pre;
		System.out.println("La dur¨¦e du programme:"+temp+"ns");
	}
}
