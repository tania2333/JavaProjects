package banc;

public class Operations {

	private Operation []c;	//tableau des opérations
	private int count;// le nombre d’opérations dans le tableau c 
	
	public Operations(){  //constructeur
		c=new Operation[20];
		for(int j=0;j<20;j++)
		{
			c[j]=new Operation("DAB LCL",true,0,0,0,0);
		}
		count=0;
	}

	public void add(Operation m){ //ajouter une opération		
		if(m.operationOut){		
			count++;		
			c[count]=new Operation(m);
		}
		for(int k=1;k<count;k++)
			for(int j=1;j<count;j++)
				if(c[j].date().compareTo(c[j+1].date())>0){
					Operation temp=c[j];
					c[j]=c[j+1];
					c[j+1]=temp;
				}
    }
	
	public int size(){  //le nombre de fois en retrait et dépôt
		return count;
	}

	public Operation get(int i){   //obtenir l'opération courante
		return c[i];
	}
	
	public Operation set(int i){   //changer l'opération quand il ne s'accorde pas avec les demandes   
		c[i]=new Operation("DAB LCL",true,0,0,0,0);
		return c[i];
	}

	public static void main(String[] args) {
		Operation r1=new Operation("DAB LCL",false,1200,2016,9,5);
		Operation r2=new Operation("DAB LCL",true,100,2016,9,25);
		Operations p=new Operations();
		p.add(r1);
		p.add(r2);
		int ss=p.size();
		System.out.println("size="+ss);
		p.get(1).OperationOut();
	}

}
