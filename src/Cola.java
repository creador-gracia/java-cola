public class Cola<T> {
    private Nodo<T> inicio;
    private Nodo<T> fin;
    private int tamaño;

    public Cola() {
        this.inicio = null;
        this.fin = null;
        this.tamaño = 0;
    }

    public void encolar(T dato) {
        Nodo<T> nuevoNodo = new Nodo<>(dato);
        
        if (estaVacia()) {
            inicio = nuevoNodo;
        } else {
            fin.setSiguiente(nuevoNodo);
        }
        fin = nuevoNodo;
        tamaño++;
    }

    public T desencolar() throws Exception {
        if (estaVacia()) {
            throw new Exception("La cola está vacía");
        }
        
        T dato = inicio.getDato();
        inicio = inicio.getSiguiente();
        tamaño--;
        
        if (estaVacia()) {
            fin = null;
        }
        
        return dato;
    }

    public T frente() throws Exception {
        if (estaVacia()) {
            throw new Exception("La cola está vacía");
        }
        return inicio.getDato();
    }

    public boolean estaVacia() {
        return inicio == null;
    }

    public int getTamaño() {
        return tamaño;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Nodo<T> actual = inicio;
        int posicion = 1;
        
        while (actual != null) {
            sb.append("[").append(posicion).append("] ").append(actual.getDato()).append("\n");
            actual = actual.getSiguiente();
            posicion++;
        }
        
        return sb.toString();
    }
}