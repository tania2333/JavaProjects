package banc;

public class ComptePrelev extends Compte{
	private Compte autreComp;//on fait un tranfert vers ce compte
	private int nombre;  //le montant de le transfert en euros
	private int d;  // la date d'un pr¨¦l¨¨vement
	private String aujour;//le jour d'aujourd'hui
	boolean estExecute;  //juger si le pr¨¦l¨¨vement doit ¨ºtre ex¨¦cut¨¦
	private String []s;//r¨¦server les dates (pas r¨¦p¨¦titives) qui sont tap¨¦es
	private int count2=0;//le nombre de fois en l¡¯entr¨¦e de la date pas r¨¦p¨¦titive
	
	public ComptePrelev(){
		super();
		Compte autreComp=new Compte();
		nombre=0;
		aujour=new String();
		estExecute=false;
		s=new String[100];
		for(int i=0;i<100;i++)
		{
			s[i]="";
		}
	}
	
	public ComptePrelev(ComptePrelev m){
		super(m);
		autreComp=m.autreComp; 
		nombre=m.nombre;
		d=m.d;
	}
	
	public void prelevement(Compte numero,int rs,int dat){
		autreComp=numero; //un pr¨¦l¨¨vement mensuel
		nombre=rs;
		d=dat;
	}
	
	public void transferer(Compte numero, int rs, int a, int b, int c){//transfert d'argent entre deux comptes
		Operation m=new Operation("DAB LCL",true,rs,a,b,c);
		p.add(m);
		autreComp=numero;
		Operation n=new Operation("DAB LCL",false,rs,a,b,c);
		autreComp.p.add(n);
	}
	
	public void actualisation(String t){ //taper la date d'aujourd'hui et decider si on peut ajouter l'op¨¦ration relative au pr¨¦l¨¨vement 
		boolean estExiste=false;
		for(int i=0;i<count2;i++){
			if(t==s[i]){
				estExiste=true;
			}
		}		
		if(!estExiste){
			s[count2++]=t;
			aujour=t;
			if(d==Integer.parseInt(aujour.substring(8,10))){
				Operation o=new Operation("DAB LCL",true,nombre,getAnnee(),getMois(),getJour());
				p.add(o);
				solde-=nombre;
				autreComp.solde+=nombre;
				estExecute=true;
			}
		}
		if(estExecute){
			System.out.println("actualisation:");
			System.out.println("id="+id);
			System.out.println("DAB LCL,solde,"+solde+","+t);
			System.out.println("id="+autreComp.getIdentifiant());
			System.out.println("DAB LCL,solde,"+autreComp.solde+","+t);
		}
		estExecute=false;		
	}
			
	public int getAnnee(){
		return Integer.parseInt(aujour.substring(0,4));
	}
	
	public int getMois(){
		return Integer.parseInt(aujour.substring(5,7));
	}
	
	public int getJour(){
		return Integer.parseInt(aujour.substring(8,10));
	}
	
	public static void main(String[] args) {
		ComptePrelev ct=new ComptePrelev();
		ct.setIdentifiant(1022);
		
		Operation r1=new Operation("DAB LCL",false,3200,2016,9,5);
		Operation r2=new Operation("DAB LCL",true,100,2016,9,25);
		ct.p=new Operations();
		ct.p.add(r1);
		ct.p.add(r2);
		Operation r3=new Operation("DAB LCL","consultation",2016,9,26);
		ct.consultation(r3);		
		System.out.println("le solde de le compte "+ct.getIdentifiant()+":"+ct.obtenirSolde());
		
		ComptePrelev cp=new ComptePrelev();
		cp.setIdentifiant(1023);
	
		Operation r11=new Operation("DAB LCL",false,3200,2016,9,5);
		Operation r21=new Operation("DAB LCL",true,100,2016,9,25);
		cp.p=new Operations();
		cp.p.add(r11);
		cp.p.add(r21);
		Operation r31=new Operation("DAB LCL","consultation",2016,10,1);
		cp.consultation(r31);
		System.out.println("le solde de le compte "+cp.getIdentifiant()+":"+cp.obtenirSolde());
		
		ct.prelevement(cp,80,21);
		ct.actualisation("2016/11/21");		
		ct.actualisation("2016/12/21");
		ct.actualisation("2016/12/21");
		ct.actualisation("2017/01/01");
	}
}
