package banc;

public class Operation {
	private boolean retrait;//si l'opération est "retrait" ou "dépôt" 
	private String o;//un descriptif donnant des renseignement sur le contexte
	private int nb; // le montant de la transaction en euros
	private int an; //l'année
	private int mo;// le mois
	private int da;// le jour
	private String consultation; //l'opération est "consultation"
	protected boolean operationOut;//si opérationOut==true,les opérations de la liste courante 
	                                    //sont transférées dans la liste d'archive

	public Operation(String s,boolean re,int n,int a,int b,int c){
		o=s;                      //constructeur quand l'opération est "retrait" ou "dépôt"  
		type(re);
		nb=n;
		an=a;
		mo=b;
		da=c;
		if(retrait && nb>2000) {//plafond de retrait:2000 euros
			System.out.println("Erreur:retrait superieur!");
			operationOut=false;
		}
		else operationOut=true;
	}
		
	public Operation(String s,String con,int a,int b,int c){
		o=s;          //constructeur quand l'opération est "consultation"
		if(con.equals("consultation"))
				consultation="consultation";
		else System.out.println("Type d'entree est faux");
		an=a;
		mo=b;
		da=c;
	}
	
	public Operation(Operation m){//initialisation en utilisant une autre opération
		o=m.o;
		type(m.retrait);
		nb=m.nb;
		an=m.an;
		mo=m.mo;
		da=m.da;
	}
	
	public void OperationOut(){ //l'affichage d'une opération
		if(mo<10 && da<10) 
			System.out.println(o+","+type(retrait)+","+nb+","+an+"/"+"0"+mo+"/"+"0"+da);
		else if(mo<10 && da>9)
			System.out.println(o+","+type(retrait)+","+nb+","+an+"/"+"0"+mo+"/"+da);
		else if(mo>9 && da<10)
			System.out.println(o+","+type(retrait)+","+nb+","+an+"/"+mo+"/"+"0"+da);
		else  
			System.out.println(o+","+type(retrait)+","+nb+","+an+"/"+mo+"/"+da);
	}
	
	public String type(boolean re){  //l'affichage du type d'opération
		retrait=re;
		if(retrait) return"RETRAIT";
		else return"DEPOT";
	}
	
	public int getNb(){  //obtenir le montant de la transaction en euros
		return nb;
	}
	
	public boolean getRetrait(){ //obtenir le type d'opération
		return retrait;
	}
	
	public String date(){  //obtenir la date de la transaction
		if(mo<10 && da<10) return an+"/"+"0"+mo+"/"+"0"+da;
		else if(mo<10 && da>9) return an+"/"+"0"+mo+"/"+da;
		else if(mo>9 && da<10) return an+"/"+mo+"/"+"0"+da;
		return an+"/"+mo+"/"+da;
	}
	
	public String getAetM(){ //obtenir l'année et le mois 
		if(mo<10) return an+"/"+"0"+mo;//normaliser leur formule
		return an+"/"+mo;
	}
		
	public int getDate(){ //obtenir justement le jour
		return da;
	}
	
	public static void main(String[] args) {
		Operation r=new Operation("DAB LCL",true,300,2015,9,2);
		r.OperationOut();	
	}	
}
