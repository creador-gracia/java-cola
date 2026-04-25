public class Cliente {
    private String id;
    private String nombre;
    private String servicio;
    private long horaLlegada;
    private long horaAtencion;
    private long horaFinalizacion;

    public Cliente(String id, String nombre, String servicio) {
        this.id = id;
        this.nombre = nombre;
        this.servicio = servicio;
        this.horaLlegada = System.currentTimeMillis();
        this.horaAtencion = 0;
        this.horaFinalizacion = 0;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getServicio() {
        return servicio;
    }

    public long getHoraLlegada() {
        return horaLlegada;
    }

    public void setHoraAtencion() {
        this.horaAtencion = System.currentTimeMillis();
    }

    public void setHoraFinalizacion() {
        this.horaFinalizacion = System.currentTimeMillis();
    }

    public long getTiempoEspera() {
        if (horaAtencion == 0) {
            return 0;
        }
        return horaAtencion - horaLlegada;
    }

    public long getTiempoAtencion() {
        if (horaFinalizacion == 0) {
            return 0;
        }
        return horaFinalizacion - horaAtencion;
    }

    public boolean fueAtendido() {
        return horaAtencion > 0;
    }

    public boolean atencionCompletada() {
        return horaFinalizacion > 0;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s - Servicio: %s", id, nombre, servicio);
    }

    public String getDetalles() {
        if (!atencionCompletada()) {
            return String.format("[%s] %s - Servicio: %s | No atendido", id, nombre, servicio);
        }
        return String.format("[%s] %s - Servicio: %s | Espera: %dms | Atención: %dms", 
            id, nombre, servicio, getTiempoEspera(), getTiempoAtencion());
    }
}