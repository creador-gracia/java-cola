public class Cliente {
    private String id;
    private String nombre;
    private String servicio;
    private long horaLlegada;
    private long horaAtencion;
    private long horaFinalizacion;

    /**
     * Constructor del Cliente
     * @param id identificador del cliente
     * @param nombre nombre del cliente
     * @param servicio tipo de servicio (Ventas/Soporte)
     */
    public Cliente(String id, String nombre, String servicio) {
        this.id = id;
        this.nombre = nombre;
        this.servicio = servicio;
        this.horaLlegada = System.currentTimeMillis();
        this.horaAtencion = 0;
        this.horaFinalizacion = 0;
    }

    /**
     * Obtiene el ID del cliente
     */
    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene el tipo de servicio
     */
    public String getServicio() {
        return servicio;
    }

    /**
     * Obtiene la hora de llegada
     */
    public long getHoraLlegada() {
        return horaLlegada;
    }

    /**
     * Establece la hora de atención (cuando inicia)
     */
    public void setHoraAtencion() {
        this.horaAtencion = System.currentTimeMillis();
    }

    /**
     * Establece la hora de finalización
     */
    public void setHoraFinalizacion() {
        this.horaFinalizacion = System.currentTimeMillis();
    }

    /**
     * Calcula el tiempo de espera en milisegundos
     * @return tiempo de espera desde llegada hasta atención
     */
    public long getTiempoEspera() {
        if (horaAtencion == 0) {
            return 0;
        }
        return horaAtencion - horaLlegada;
    }

    /**
     * Calcula el tiempo de atención en milisegundos
     * @return tiempo desde que inicia hasta que finaliza la atención
     */
    public long getTiempoAtencion() {
        if (horaFinalizacion == 0) {
            return 0;
        }
        return horaFinalizacion - horaAtencion;
    }

    /**
     * Verifica si el cliente ya fue atendido
     */
    public boolean fueAtendido() {
        return horaAtencion > 0;
    }

    /**
     * Verifica si la atención fue completada
     */
    public boolean atencionCompletada() {
        return horaFinalizacion > 0;
    }

    /**
     * Representación en string del cliente
     */
    @Override
    public String toString() {
        return String.format("[%s] %s - Servicio: %s", id, nombre, servicio);
    }

    /**
     * Obtiene información detallada del cliente
     */
    public String getDetalles() {
        if (!atencionCompletada()) {
            return String.format("[%s] %s - Servicio: %s | No atendido", id, nombre, servicio);
        }
        return String.format("[%s] %s - Servicio: %s | Espera: %dms | Atención: %dms", 
            id, nombre, servicio, getTiempoEspera(), getTiempoAtencion());
    }
}