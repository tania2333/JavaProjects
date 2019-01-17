package banc;

public class Banque {
	protected ComptePrelev []co;
	protected int c;
	boolean departInclus;// juger si le compte qui accepte un prélèvement appartient à la banque
	boolean destInclus;// juger si le compte qui reçoit un transfert appartient à la banque
	
	public Banque(){
		co=new ComptePrelev[100];
		for(int i=1;i<100;i++)
		{
			co[i]=new ComptePrelev();
		}
		c=0;
		departInclus=false;
		destInclus=false;
	}
	
	public void addCompte(ComptePrelev m){ //ajouter un compte
		c++;
		co[c]=new ComptePrelev(m);
	}
	
	public void transf(int depart, int dest, int arg,int aa,int bb, int cc){//un transfert d'argent
		int cn1=0,cn2=0;
		for(int i=1;i<c+1;i++){
			if(depart==co[i].getIdentifiant()) {
				departInclus=true;
				cn1=i;
			}
			if(dest==co[i].getIdentifiant()) {
				destInclus=true;
				cn2=i;
			}
		}
		if(departInclus && destInclus)
			co[cn1].transferer(co[cn2], arg, aa, bb, cc);
	}
	
	public static void main(String[] args) {
		ComptePrelev a=new ComptePrelev();
		a.setIdentifiant(1022);
		
		Operation r1=new Operation("DAB LCL",false,1200,2016,9,5);
		Operation r2=new Operation("DAB LCL",true,300,2016,9,25);
		a.p=new Operations();
		a.p.add(r1);
		a.p.add(r2);
		Operation r3=new Operation("DAB LCL","consultation",2016,9,26);
	    a.consultation(r3);
	
	    ComptePrelev b=new ComptePrelev();
	    b.setIdentifiant(1023);
		Operation r4=new Operation("DAB LCL",false,800,2016,10,3);
		Operation r5=new Operation("DAB LCL",true,400,2016,10,17);
		b.p=new Operations();
		b.p.add(r4);
		b.p.add(r5);
		Operation r6=new Operation("DAB LCL","consultation",2016,11,18);	
		b.consultation(r6);
		
		Banque bq=new Banque();
		bq.addCompte(a);
		bq.addCompte(b);
		
		bq.transf(1022,1023,100,2016,11,25);
		System.out.println("transferer:"); 
		System.out.println("id=1022"); 
		Operation r7=new Operation("DAB LCL","consultation",2016,11,26);
	    a.consultation(r7);
	    a.OperationsEffectuees();
	    
	    System.out.println("id=1023");
	    Operation r8=new Operation("DAB LCL","consultation",2016,11,26);	
		b.consultation(r8);
		b.OperationsEffectuees();
	
	}
}
