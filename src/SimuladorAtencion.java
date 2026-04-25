import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class SimuladorAtencion {
    private Cola<Cliente> colaClientes;
    private Pila<Cliente> historicoAtenciones;
    private int clientesAtendidos;

    /**
     * Constructor del Simulador
     */
    public SimuladorAtencion() {
        this.colaClientes = new Cola<>();
        this.historicoAtenciones = new Pila<>();
        this.clientesAtendidos = 0;
    }

    /**
     * Carga los clientes desde el archivo clientes.txt
     */
    public void cargarClientes() {
        try (BufferedReader br = new BufferedReader(new FileReader("clientes.txt"))) {
            String linea;
            boolean primeraLinea = true;

            while ((linea = br.readLine()) != null) {
                // Saltar la cabecera
                if (primeraLinea) {
                    primeraLinea = false;
                    continue;
                }

                String[] partes = linea.split(",");
                if (partes.length == 3) {
                    String id = partes[0].trim();
                    String nombre = partes[1].trim();
                    String servicio = partes[2].trim();
                    
                    Cliente cliente = new Cliente(id, nombre, servicio);
                    colaClientes.encolar(cliente);
                }
            }
            System.out.println("✓ Se cargaron " + colaClientes.getTamaño() + " clientes exitosamente\n");
        } catch (IOException e) {
            System.out.println("✗ Error al cargar el archivo: " + e.getMessage());
        }
    }

    /**
     * Atiende al siguiente cliente en la cola
     */
    public void atenderSiguiente() {
        if (colaClientes.estaVacia()) {
            System.out.println("\n✗ No hay clientes en la cola\n");
            return;
        }

        try {
            Cliente cliente = colaClientes.desencolar();
            cliente.setHoraAtencion();
            
            // Simular atención
            System.out.println("\n╔════════════════════════════════════╗");
            System.out.println("║      ATENDIENDO CLIENTE             ║");
            System.out.println("╚════════════════════════════════════╝");
            System.out.println("  " + cliente);
            System.out.println("  Tiempo de espera: " + cliente.getTiempoEspera() + " ms");
            System.out.println("  Procesando...");
            
            // Simular tiempo de atención (500-2000 ms)
            Thread.sleep(500 + (long)(Math.random() * 1500));
            
            cliente.setHoraFinalizacion();
            historicoAtenciones.apilar(cliente);
            clientesAtendidos++;
            
            System.out.println("  ✓ ¡Listo!");
            System.out.println("\n  Clientes en cola: " + colaClientes.getTamaño());
            System.out.println("  Clientes atendidos: " + clientesAtendidos + "\n");
            
        } catch (Exception e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }

    /**
     * Atiende a todos los clientes automáticamente
     */
    public void atenderTodos() {
        if (colaClientes.estaVacia()) {
            System.out.println("\n✗ No hay clientes en la cola\n");
            return;
        }

        System.out.println("\n╔════════════════════════════════════╗");
        System.out.println("║   ATENDIENDO A TODOS LOS CLIENTES   ║");
        System.out.println("╚════════════════════════════════════╝\n");

        while (!colaClientes.estaVacia()) {
            try {
                Cliente cliente = colaClientes.desencolar();
                cliente.setHoraAtencion();
                
                System.out.print("Atendiendo a " + cliente.getNombre() + "... ");
                
                // Simular tiempo de atención
                Thread.sleep(300 + (long)(Math.random() * 1000));
                
                cliente.setHoraFinalizacion();
                historicoAtenciones.apilar(cliente);
                clientesAtendidos++;
                
                System.out.println("✓");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            } catch (Exception e) {
                System.out.println("✗ Error: " + e.getMessage());
            }
        }
        
        System.out.println("\n✓ Se atendieron " + clientesAtendidos + " clientes en total\n");
    }

    /**
     * Consulta el último cliente atendido
     */
    public void consultarUltimoAtendido() {
        if (historicoAtenciones.estaVacia()) {
            System.out.println("\n✗ No hay clientes atendidos aún\n");
            return;
        }

        try {
            Cliente ultimo = historicoAtenciones.cima();
            System.out.println("\n╔════════════════════════════════════╗");
            System.out.println("║    ÚLTIMO CLIENTE ATENDIDO          ║");
            System.out.println("╚════════════════════════════════════╝");
            System.out.println("  " + ultimo.getDetalles() + "\n");
        } catch (Exception e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }

    /**
     * Muestra el historial completo de atenciones (pila)
     */
    public void mostrarHistorial() {
        if (historicoAtenciones.estaVacia()) {
            System.out.println("\n✗ No hay clientes atendidos aún\n");
            return;
        }

        System.out.println("\n╔════════════════════════════════════╗");
        System.out.println("║   HISTORIAL DE ATENCIONES (PILA)    ║");
        System.out.println("╚════════════════════════════════════╝\n");
        System.out.println(historicoAtenciones);
    }

    /**
     * Muestra la cola actual de clientes
     */
    public void mostrarCola() {
        if (colaClientes.estaVacia()) {
            System.out.println("\n✗ No hay clientes en la cola\n");
            return;
        }

        System.out.println("\n╔════════════════════════════════════╗");
        System.out.println("║      COLA ACTUAL DE CLIENTES        ║");
        System.out.println("╚════════════════════════════════════╝\n");
        System.out.println(colaClientes);
    }

    /**
     * Muestra estadísticas del sistema
     */
    public void mostrarEstadisticas() {
        System.out.println("\n╔════════════════════════════════════╗");
        System.out.println("║         ESTADÍSTICAS DEL SISTEMA    ║");
        System.out.println("╚════════════════════════════════════╝");
        System.out.println("  Clientes en cola: " + colaClientes.getTamaño());
        System.out.println("  Clientes atendidos: " + clientesAtendidos);
        System.out.println("  Total cargados: " + (colaClientes.getTamaño() + clientesAtendidos) + "\n");

        if (clientesAtendidos > 0) {
            // Calcular promedios
            long tiempoEsperaTotal = 0;
            long tiempoAtencionTotal = 0;
            
            Nodo<Cliente> actual = historicoAtenciones.cima;
            int contador = 0;
            
            // Nota: Esto es una simplificación. En un caso real, querrías guardar referencias
            System.out.println("  Estadísticas de atención:");
            System.out.println("    - Clientes completamente atendidos: " + clientesAtendidos + "\n");
        }
    }

    /**
     * Inicia el simulador con menú interactivo
     */
    public void iniciar() {
        Scanner scanner = new Scanner(System.in);
        cargarClientes();

        boolean salir = false;
        while (!salir) {
            System.out.println("╔════════════════════════════════════╗");
            System.out.println("║  SIMULADOR DE COLA DE ATENCIÓN      ║");
            System.out.println("║         BANCO PRINCIPAL             ║");
            System.out.println("╚════════════════════════════════════╝");
            System.out.println();
            System.out.println("  1. Atender siguiente cliente");
            System.out.println("  2. Atender a todos los clientes");
            System.out.println("  3. Ver último cliente atendido");
            System.out.println("  4. Ver historial de atenciones");
            System.out.println("  5. Ver cola actual de clientes");
            System.out.println("  6. Ver estadísticas");
            System.out.println("  7. Salir");
            System.out.println();
            System.out.print("  Selecciona una opción: ");

            try {
                int opcion = scanner.nextInt();
                System.out.println();

                switch (opcion) {
                    case 1:
                        atenderSiguiente();
                        break;
                    case 2:
                        atenderTodos();
                        break;
                    case 3:
                        consultarUltimoAtendido();
                        break;
                    case 4:
                        mostrarHistorial();
                        break;
                    case 5:
                        mostrarCola();
                        break;
                    case 6:
                        mostrarEstadisticas();
                        break;
                    case 7:
                        System.out.println("  ¡Gracias por usar el simulador! 👋\n");
                        salir = true;
                        break;
                    default:
                        System.out.println("  ✗ Opción no válida. Intenta de nuevo.\n");
                }
            } catch (Exception e) {
                System.out.println("  ✗ Error: Ingresa un número válido\n");
                scanner.nextLine(); // Limpiar buffer
            }
        }
        scanner.close();
    }

    /**
     * Método principal
     */
    public static void main(String[] args) {
        SimuladorAtencion simulador = new SimuladorAtencion();
        simulador.iniciar();
    }
}