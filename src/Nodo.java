public class Nodo<T> {
    private T dato;
    private Nodo<T> siguiente;

    /**
     * Constructor del Nodo
     * @param dato El dato a almacenar en el nodo
     */
    public Nodo(T dato) {
        this.dato = dato;
        this.siguiente = null;
    }

    /**
     * Obtiene el dato almacenado en el nodo
     * @return el dato
     */
    public T getDato() {
        return dato;
    }

    /**
     * Establece el dato del nodo
     * @param dato el nuevo dato
     */
    public void setDato(T dato) {
        this.dato = dato;
    }

    /**
     * Obtiene el siguiente nodo
     * @return el nodo siguiente
     */
    public Nodo<T> getSiguiente() {
        return siguiente;
    }

    /**
     * Establece el siguiente nodo
     * @param siguiente el nodo siguiente
     */
    public void setSiguiente(Nodo<T> siguiente) {
        this.siguiente = siguiente;
    }

    /**
     * Verifica si este nodo es el último (siguiente es null)
     * @return true si es el último nodo, false en caso contrario
     */
    public boolean esUltimo() {
        return siguiente == null;
    }
}