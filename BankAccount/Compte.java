package banc;

public class Compte {
	protected Operations p;
	protected int id;//un identifiant
	protected int solde=0;//le solde disponible sur le compte
	protected int nbConsult=1;//le nombre de fois en consultation
	protected int []pre;//réserver le nombre de fois en retrait et dépôt quand la consultation est exécutée
	protected int []argent;//réserver le solde quand la consultation est exécuté
	protected String []str;//réserver la date de consultation
	protected int op=0;//le nombre de fois en retrait et dépôt quand la consultation présente est exécutée
	
	public Compte(){
		pre=new int[100];
		for(int i=1;i<100;i++)
		{
			pre[i]=0;
		}
		argent=new int[100];
		for(int k=0;k<100;k++)
		{
			argent[k]=0;
		}
		str=new String[100];
		for(int j=1;j<100;j++)
		{
			str[j]="";
		}
		id=0;
		solde=0;
		nbConsult=1;
	}
	
	public Compte(Compte a){
		id=a.id;
		p=a.p;
		solde=a.solde;
		nbConsult=a.nbConsult;
		pre=a.pre;
		argent=a.argent;
		str=a.str;
		op=a.op;
	}
	
	public void setIdentifiant(int num){ 
		id=num;
	}
	
	public int getIdentifiant(){
		return id;
	}
	
	public void consultation(Operation m){// la consultation
		solde=0;
		op=0;
		str[nbConsult]=m.date();
		for(int i=1;i<p.size()+1;i++){
			if(p.get(i).date().compareTo(m.date())<0){
				op++;
				if(!p.get(i).getRetrait())
				solde+=p.get(i).getNb();
				else {
					solde-=p.get(i).getNb();
				}
			}
		}
		argent[nbConsult]=solde; 
		pre[nbConsult++]=op;
	}
	
	public int obtenirSolde(){
		return solde;
	}
		
	public void OperationsEffectuees(){//les opérations effectuées sur le compte depuis la dernière consultation
		System.out.println("La consultation concrete:");
		System.out.println("DAB LCL,solde,"+argent[nbConsult-2]+","+dateDerniere());
		for(int n=pre[nbConsult-2]+1;n<p.size()+1;n++){
			if(p.get(n).date().compareTo(str[nbConsult-1])<0){
				p.get(n).OperationOut();	
			}
		}
		System.out.println("DAB LCL,solde,"+argent[nbConsult-1]+","+str[nbConsult-1]);
	}
	
	public String dateDerniere(){  // la date de la dernière consultation
		return str[nbConsult-2];
	}
		
	public static void main(String[] args) {
		Compte ct=new Compte();
		ct.setIdentifiant(122);
		System.out.println("id="+ct.getIdentifiant());
		
		Operation r1=new Operation("DAB LCL",false,3200,2016,9,5);
		Operation r2=new Operation("DAB LCL",true,2100,2016,9,25);
		ct.p=new Operations();
		ct.p.add(r1);
		ct.p.add(r2);
		Operation r3=new Operation("DAB LCL","consultation",2016,9,26);
	    ct.consultation(r3);
	    
		Operation r4=new Operation("DAB LCL",true,800,2016,10,23);
		Operation r5=new Operation("DAB LCL",true,800,2016,10,17);
		ct.p.add(r4);
		ct.p.add(r5);
		Operation r6=new Operation("DAB LCL","consultation",2016,10,18);	
		ct.consultation(r6);
		ct.OperationsEffectuees();
		
		Operation r7=new Operation("DAB LCL","consultation",2016,10,25);	
		ct.consultation(r7);
		ct.OperationsEffectuees();

		String date=new String ();
		date=ct.dateDerniere();
		System.out.println("dateDerniere="+date);
	}
}





