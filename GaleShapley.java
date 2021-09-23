
public class GaleShapley {

	public static void main(String[] args) {
		String[][] hombres = {{"Victor","Bertha","Amy","Diane","Erika","Claire"},
							{"William","Diane","Bertha","Amy","Claire","Erika"},
							{"Xavier","Bertha","Erika","Claire","Diane","Amy"},
							{"Yancey","Amy","Diane","Claire","Bertha","Erika"},
							{"Zeus","Bertha","Diane","Amy","Erika","Claire"}
							};
		String[][] mujeres = {{"Amy","Zeus","Victor","William","Yancey","Xavier"},
							{"Bertha","Xavier","William","Yancey","Victor","Zeus"},
							{"Claire","William","Xavier","Yancey","Zeus","Victor"},
							{"Diane","Victor","Zeus","Yancey","Xavier","William"},
							{"Erika","Yancey","William","Zeus","Xavier","Victor"}
							};
		Celda[][] H = crearTabla(hombres);
		Celda[][] M = crearTabla(mujeres);
		galeShapley(H,M);
		System.out.println();
		mostrarParejas(H);
		System.out.println();
		mostrarParejas(M);
		
		H = crearTabla(hombres);
		M = crearTabla(mujeres);
		galeShapley(M,H);
		System.out.println();
		mostrarParejas(H);
		System.out.println();
		mostrarParejas(M);
	}
		
	public static Celda[][] crearTabla(String[][]x) {
		Celda[][]tabla = new Celda[x.length][x[0].length];
		
		for(int i=0;i<x.length;i++) {
			for(int j=0;j<x[0].length;j++) {
				tabla[i][j] = new Celda(x[i][j],0,x.length-1-j);
			}
		}
		return tabla;
	}
	
	public static void  galeShapley(Celda[][]A, Celda[][]B) {
		
		int i = 0;
		Celda h = A[i][0];//hombre de la tabla A
		while(i < 5) {
			
			Celda m = buscarPretendiente(A,h); 
			
			Celda M = buscarVertical(B,m);//mujer de la tabla B
			Celda H = buscarHorizontal(B,M,h);
			
			//se busca m en la tabla de celdas B
			if(M.getPareja() == 0) {//M esta sin pareja
				//se empareja 0:sin pareja; 1:emparejado; 2:rechazado
				
				m.setPareja(1);
				H.setPareja(1);
				
				h.setPareja(1);
				M.setPareja(1);
				i++;
				if(i<5)
					h = A[i][0];
			}else if(M.getPareja() == 1 || M.getPareja() == 2) {
				Celda P = buscarParejaActual(B,M);//pareja actual en la tabla b;
				if(H.getPreferencia()>P.getPreferencia()) {
					P.setPareja(2);
					H.setPareja(1);
					
					h.setPareja(1);
					m.setPareja(1);
					
					h = buscarVertical(A,P);
					Celda p = buscarParejaActual(A,h);
					h.setPareja(2);
					p.setPareja(2);
					
				}else {
					H.setPareja(2);
					m.setPareja(2);
					h = buscarVertical(A,H);
				}
			}
		}
	}
	
	public static void mostrarParejas(Celda[][]X) {
		for(int i=0;i<X.length;i++) {
			System.out.print(X[i][0] + " -> ");
			for(int j=1;j<X[i].length;j++)
				if(X[i][j].getPareja() == 1)
					System.out.println(X[i][j]);
		}
	}
	
	public static Celda buscarPretendiente(Celda[][]X,Celda elejido) {
		int x=0;
		for(int i=0;i<X.length;i++)
			if(elejido.equals(X[i][0]))
				x = i;
		for(int i=1;i<X[x].length;i++)//se comienza en 1 por que no cuentan los que escogen, que estarian en la posicion 0
			if(X[x][i].getPareja() == 0)
				return X[x][i];
		return null;
	}
	
	public static Celda buscarParejaActual(Celda[][]X,Celda elejido){
		int x=0;
		for(int i=0;i<X.length;i++)
			if(elejido.equals(X[i][0]))
				x = i;
				
		for(int i=1;i<X[x].length;i++)//se comienza en 1 por que no cuentan los que escogen, que estarian en la posicion 0
			if(X[x][i].getPareja() == 1)
				return X[x][i];
		return null;
	}
	
	public static void mostrarTodo(Celda[][]X) {
		for(int i=0;i<X.length;i++) {
			for(int j=0;j<X[i].length;j++) {
				System.out.print(X[i][j] + "," + X[i][j].getPareja() +"|");
			}
			System.out.println();
		}
	}
	
	public static Celda buscarVertical(Celda[][]X,Celda x) {
		for(int i=0;i<X.length;i++)
			if(x.equals(X[i][0]))
				return X[i][0];
		return null;
	}

	public static Celda buscarHorizontal(Celda[][]X,Celda a,Celda b) {
		int x=0;
		for(int i=0;i<X.length;i++)
			if(a.equals(X[i][0]))
				x = i;
				
		for(int i=0;i<X[x].length;i++)
			if(b.equals(X[x][i]))
				return X[x][i];
		return null;
	}
}
