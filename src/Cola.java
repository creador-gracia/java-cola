public class Cola<T> {
    private Nodo<T> inicio;
    private Nodo<T> fin;
    private int tamaño;

    /**
     * Constructor de la Cola
     */
    public Cola() {
        this.inicio = null;
        this.fin = null;
        this.tamaño = 0;
    }

    /**
     * Encola un elemento al final de la cola (O(1))
     * @param dato el elemento a encolar
     */
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

    /**
     * Desencola y retorna el primer elemento de la cola (O(1))
     * @return el primer elemento
     * @throws Exception si la cola está vacía
     */
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

    /**
     * Obtiene el primer elemento sin removerlo (O(1))
     * @return el primer elemento
     * @throws Exception si la cola está vacía
     */
    public T frente() throws Exception {
        if (estaVacia()) {
            throw new Exception("La cola está vacía");
        }
        return inicio.getDato();
    }

    /**
     * Verifica si la cola está vacía
     * @return true si la cola está vacía, false en caso contrario
     */
    public boolean estaVacia() {
        return inicio == null;
    }

    /**
     * Obtiene el tamaño de la cola
     * @return el número de elementos
     */
    public int getTamaño() {
        return tamaño;
    }

    /**
     * Obtiene una representación en String de toda la cola
     * @return string con los elementos de la cola
     */
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