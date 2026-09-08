import java.util.ArrayList;

public class TablaHashSondeoLineal{

    private ArrayList<Nodo>[] tabla;
    private int m = 7;
    private int n = 0;
    
    private class Nodo{
	private int llave;
	private String valor;
	private boolean borrado;

	private Nodo(int llave, String valor){
	    this.llave = llave;
	    this.valor = valor;
	    this.borrado = false;
	}

	public int getLlave(){
	    return llave;
	}

	public String getValor(){
	    return valor;
	}

	public boolean isDeleted(){
	    return borrado;
	}

	@Override
	public String toString(){
	    return "(" + llave + ", " + valor.toString() + ")";
	}
    }

    @SuppressWarnings("unchecked")
    public TablaHashSondeoLineal(){
        tabla = new ArrayList[m];
        for (int i = 0; i < m; i++) {
            tabla[i] = null;
        }
    }

    private int hash(int l){
	return Math.abs(l) % m;
    }

    public void insertar(int llave, String valor) {
	int indice = hash(llave);
	int primerBorrado = -1;
	for (int i = 0; i < m; i++) {
            int posicion = (indice + i) % m;
	    if (tabla[posicion] != null) {
                Nodo nodo = tabla[posicion].get(0);
		if (nodo.isDeleted()) {
                    if (primerBorrado == -1) {
                        primerBorrado = posicion;
                    }
		} else if (nodo.getLlave() == llave) {
		    nodo.valor = valor;
		    return;
		}
	    } else {
		int destino = posicion;
		if (primerBorrado != -1) {
		    destino = primerBorrado;
		}
		tabla[destino] = new ArrayList<>();
		tabla[destino].add(new Nodo(llave, valor));
		n++;
		return;
	    }
	}
	if (primerBorrado != -1) {
            tabla[primerBorrado] = new ArrayList<>();
            tabla[primerBorrado].add(new Nodo(llave, valor));
            n++;
            return;
        }
        System.out.println("ERROR: Tabla llena");
    }
	    
    public String buscar(int llave){
	int busca = hash(llave);
	for (int i = 0; i < m; i++) {
            int posicion = (busca + i) % m;
	    if (tabla[posicion] == null) {
                System.out.println("NOT_FOUND");
                return null;
            }
	    Nodo nodo = tabla[posicion].get(0);
	    if (nodo.isDeleted()) {
                continue;
            }
	    if (nodo.getLlave() == llave) {
                System.out.println(posicion + " -> " + nodo.getValor());
                return nodo.getValor();
            }
        }
	System.out.println("NOT_FOUND");
	return null;
    }

    public int contiene(int llave){
	int busca = hash(llave);
	for (int i = 0; i < m; i++) {
            int posicion = (busca + i) % m;
	    if (tabla[posicion] == null) {
                return -1;
            }            
            Nodo nodo = tabla[posicion].get(0);
	    if (nodo.isDeleted()) {
                continue;
            }
	    if (nodo.getLlave() == llave) {
                return posicion;
            }
        }
	return -1;
    }


    public void eliminar(int llave){
        int posicion = contiene(llave);
        if (posicion != -1) {
	    tabla[posicion].get(0).borrado = true;
            n--;
        }
    }

    public double factorCarga() {
	return (double) n / m;
    }

    @Override
    public String toString(){
	String s = "";
	for(int i = 0; i < m; i++){
	    s += i + " -> ";
	    if(tabla[i] == null) {
		s += "\n";
	    } else {
	        Nodo nodo = tabla[i].get(0);
                if (nodo.isDeleted()) {
                    s += "DELETED\n";
                } else {
                    s += nodo.toString() + "\n";
                }
            }
        }
        return s;
    }
}
