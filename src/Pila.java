public class Pila<T> {
    private Nodo<T> cima;
    private int tamaño;

    /**
     * Constructor de la Pila
     */
    public Pila() {
        this.cima = null;
        this.tamaño = 0;
    }

    /**
     * Apila un elemento en la cima de la pila (O(1))
     * @param dato el elemento a apilar
     */
    public void apilar(T dato) {
        Nodo<T> nuevoNodo = new Nodo<>(dato);
        nuevoNodo.setSiguiente(cima);
        cima = nuevoNodo;
        tamaño++;
    }

    /**
     * Desapila y retorna el elemento de la cima (O(1))
     * @return el elemento de la cima
     * @throws Exception si la pila está vacía
     */
    public T desapilar() throws Exception {
        if (estaVacia()) {
            throw new Exception("La pila está vacía");
        }
        
        T dato = cima.getDato();
        cima = cima.getSiguiente();
        tamaño--;
        
        return dato;
    }

    /**
     * Obtiene el elemento de la cima sin removerlo (O(1))
     * @return el elemento de la cima
     * @throws Exception si la pila está vacía
     */
    public T cima() throws Exception {
        if (estaVacia()) {
            throw new Exception("La pila está vacía");
        }
        return cima.getDato();
    }

    /**
     * Verifica si la pila está vacía
     * @return true si la pila está vacía, false en caso contrario
     */
    public boolean estaVacia() {
        return cima == null;
    }

    /**
     * Obtiene el tamaño de la pila
     * @return el número de elementos
     */
    public int getTamaño() {
        return tamaño;
    }

    /**
     * Obtiene una representación en String de toda la pila
     * @return string con los elementos de la pila
     */
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