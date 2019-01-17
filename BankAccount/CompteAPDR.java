package banc;

public class CompteAPDR extends Compte{//Compte ¨¤ Plafond de Retrait
    private int sommefix=0;//une somme fix¨¦e
    
	public CompteAPDR(int som){
		super();
		sommefix=som;
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
				else 
					solde-=p.get(i).getNb();
				if(solde<sommefix){				
					solde+=p.get(i).getNb();
					p.set(i);//l'op¨¦ration n'est pas ex¨¦cut¨¦ quand le solde est n¨¦gatif sup¨¦rieur ¨¤ une somme fix¨¦e
				}
			}
		}
		argent[nbConsult]=solde; 
		pre[nbConsult++]=op;		
	}
	
	public void OperationsEffectuees(){//les op¨¦rations effectu¨¦es sur le compte depuis la derni¨¨re consultation
		System.out.println("La consultation concrete:");
		System.out.println("DAB LCL,solde,"+argent[nbConsult-2]+","+dateDerniere());
		for(int n=pre[nbConsult-2]+1;n<p.size()+1;n++){
			if(p.get(n).date().compareTo(str[nbConsult-1])<0){
				if(p.get(n).getNb()!=0)
					p.get(n).OperationOut();
			}
		}
		System.out.println("DAB LCL,solde,"+argent[nbConsult-1]+","+str[nbConsult-1]);
	}
	
	public static void main(String[] args) {
		CompteAPDR ct=new CompteAPDR(-1000);
		ct.setIdentifiant(122);
		System.out.println("id="+ct.getIdentifiant());
		
		Operation r1=new Operation("DAB LCL",false,200,2016,9,5);
		Operation r2=new Operation("DAB LCL",true,1300,2016,9,25);
		ct.p=new Operations();
		ct.p.add(r1);
		ct.p.add(r2);
		Operation r3=new Operation("DAB LCL","consultation",2016,9,26);
	    ct.consultation(r3);
	    
		Operation r4=new Operation("DAB LCL",true,800,2016,10,3);
		Operation r5=new Operation("DAB LCL",true,900,2016,10,17);
		ct.p.add(r4);
		ct.p.add(r5);
		Operation r6=new Operation("DAB LCL","consultation",2016,11,18);	
		ct.consultation(r6);
		ct.OperationsEffectuees();

		String date=new String ();
		date=ct.dateDerniere();
		System.out.println("dateDerniere="+date);
	}
}
