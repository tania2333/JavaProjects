package banc;

class CompteDif extends Compte{
	public CompteDif(){
		super();
	}
	
	public void consultation(Operation m){
		solde=0;
		op=0;
		str[nbConsult]=m.date();
		for(int i=1;i<p.size()+1;i++){
			if(p.get(i).date().compareTo(m.date())<0){
				op++;
				if(!p.get(i).getRetrait()){
				solde+=p.get(i).getNb();
				}
				else {
					if(p.get(i).getAetM().compareTo(m.getAetM())<0){
						solde-=p.get(i).getNb();//En cas de retrait, le solde peut ¨ºtre actualis¨¦ uniquement ¨¤ la fin du mois 
					}	
				}
			}
		}
		argent[nbConsult]=solde; 
		pre[nbConsult++]=op;
	}

	public static void main(String[] args) {
		CompteDif ct=new CompteDif();
		ct.setIdentifiant(122);
		System.out.println("id="+ct.getIdentifiant());
		
		Operation r1=new Operation("DAB LCL",false,3200,2016,9,5);
		Operation r2=new Operation("DAB LCL",true,100,2016,9,27);
		ct.p=new Operations();
		ct.p.add(r1);
		ct.p.add(r2);
		Operation r3=new Operation("DAB LCL","consultation",2016,9,28);
	    ct.consultation(r3);
	
		Operation r4=new Operation("DAB LCL",true,200,2016,10,27);
		Operation r5=new Operation("DAB LCL",false,300,2016,10,28);
		ct.p.add(r4);
		ct.p.add(r5);
		Operation r6=new Operation("DAB LCL","consultation",2016,10,29);	
		ct.consultation(r6);
		ct.OperationsEffectuees();
	
		String date=new String ();
		date=ct.dateDerniere();
		System.out.println("dateDerniere="+date);
	}
}

