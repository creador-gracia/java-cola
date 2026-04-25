public class Pila<T> {
    private Nodo<T> cima;
    private int tamaño;

    public Pila() {
        this.cima = null;
        this.tamaño = 0;
    }

    public void apilar(T dato) {
        Nodo<T> nuevoNodo = new Nodo<>(dato);
        nuevoNodo.setSiguiente(cima);
        cima = nuevoNodo;
        tamaño++;
    }

    public T desapilar() throws Exception {
        if (estaVacia()) {
            throw new Exception("La pila está vacía");
        }
        
        T dato = cima.getDato();
        cima = cima.getSiguiente();
        tamaño--;
        
        return dato;
    }

    public T cima() throws Exception {
        if (estaVacia()) {
            throw new Exception("La pila está vacía");
        }
        return cima.getDato();
    }

    public boolean estaVacia() {
        return cima == null;
    }

    public int getTamaño() {
        return tamaño;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Nodo<T> actual = cima;
        int posicion = 1;
        
        while (actual != null) {
            sb.append("[").append(posicion).append("] ").append(actual.getDato()).append("\n");
            actual = actual.getSiguiente();
            posicion++;
        }
        
        return sb.toString();
    }
}