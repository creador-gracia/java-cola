import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Simulador {
    private Cola<Cliente> colaClientes;
    private Pila<Cliente> historicoAtenciones;
    private int clientesAtendidos;

    public Simulador() {
        this.colaClientes = new Cola<>();
        this.historicoAtenciones = new Pila<>();
        this.clientesAtendidos = 0;
    }

    public void cargarClientes() {
        try (BufferedReader br = new BufferedReader(new FileReader("data/clientes.txt"))) {
            String linea;
            boolean primeraLinea = true;

            while ((linea = br.readLine()) != null) {
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
            System.out.println("Clientes cargados exitosamente: " + colaClientes.getTamaño());
        } catch (IOException e) {
            System.out.println("Error al cargar el archivo: " + e.getMessage());
        }
    }

    public void agregarClienteManual() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingresa el ID del cliente: ");
        String id = scanner.nextLine().trim();
        System.out.print("Ingresa el nombre del cliente: ");
        String nombre = scanner.nextLine().trim();
        System.out.print("Ingresa el servicio (Ventas/Soporte): ");
        String servicio = scanner.nextLine().trim();
        
        Cliente cliente = new Cliente(id, nombre, servicio);
        colaClientes.encolar(cliente);
        System.out.println("Cliente agregado a la cola.");
    }

    public void atenderSiguiente() {
        if (colaClientes.estaVacia()) {
            System.out.println("\n✗ No hay clientes en la cola\n");
            return;
        }

        try {
            Cliente cliente = colaClientes.desencolar();
            cliente.setHoraAtencion();
            
            System.out.println("Atendiendo a: " + cliente);
            System.out.println("Tiempo de espera: " + cliente.getTiempoEspera() + " ms");
            System.out.println("Procesando...");
            
            Thread.sleep(500 + (long)(Math.random() * 1500));
            
            cliente.setHoraFinalizacion();
            historicoAtenciones.apilar(cliente);
            clientesAtendidos++;
            
            System.out.println("Listo.");
            System.out.println("Clientes en cola: " + colaClientes.getTamaño());
            System.out.println("  Clientes atendidos: " + clientesAtendidos + "\n");
            
        } catch (Exception e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }

    public void atenderTodos() {
        if (colaClientes.estaVacia()) {
            System.out.println("No hay clientes en la cola.");
            return;
        }

        while (!colaClientes.estaVacia()) {
            try {
                Cliente cliente = colaClientes.desencolar();
                cliente.setHoraAtencion();
                
                System.out.print("Atendiendo a " + cliente.getNombre() + "... ");
                
                Thread.sleep(300 + (long)(Math.random() * 1000));
                
                cliente.setHoraFinalizacion();
                historicoAtenciones.apilar(cliente);
                clientesAtendidos++;
                
                System.out.println("Atendido.");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        
        System.out.println("Atendidos: " + clientesAtendidos);
    }

    public void consultarUltimoAtendido() {
        if (historicoAtenciones.estaVacia()) {
            System.out.println("No hay clientes atendidos aún.");
            return;
        }

        try {
            Cliente ultimo = historicoAtenciones.cima();
            System.out.println("Último cliente atendido:");
            System.out.println(ultimo.getDetalles());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void mostrarHistorial() {
        if (historicoAtenciones.estaVacia()) {
            System.out.println("No hay clientes atendidos aún.");
            return;
        }

        System.out.println("Historial de atenciones:");
        System.out.println(historicoAtenciones);
    }

    public void mostrarCola() {
        if (colaClientes.estaVacia()) {
            System.out.println("No hay clientes en la cola.");
            return;
        }

        System.out.println("Cola actual de clientes:");
        System.out.println(colaClientes);
    }

    public void mostrarEstadisticas() {
        System.out.println("Estadísticas:");
        System.out.println("Clientes en cola: " + colaClientes.getTamaño());
        System.out.println("Clientes atendidos: " + clientesAtendidos);
        System.out.println("Total procesados: " + (colaClientes.getTamaño() + clientesAtendidos));
    }

    public void iniciar() {
        Scanner scanner = new Scanner(System.in);

        boolean salir = false;
        while (!salir) {
            System.out.println("Simulador de Cola de Atención");
            System.out.println();
            System.out.println("1. Cargar clientes desde archivo");
            System.out.println("2. Agregar cliente manualmente");
            System.out.println("3. Atender siguiente cliente");
            System.out.println("4. Ver cola de espera");
            System.out.println("5. Ver historial de atenciones");
            System.out.println("6. Consultar último atendido");
            System.out.println("7. Salir");
            System.out.println();
            System.out.print("Selecciona una opción: ");

            try {
                int opcion = scanner.nextInt();
                System.out.println();

                switch (opcion) {
                    case 1:
                        cargarClientes();
                        break;
                    case 2:
                        agregarClienteManual();
                        break;
                    case 3:
                        atenderSiguiente();
                        break;
                    case 4:
                        mostrarCola();
                        break;
                    case 5:
                        mostrarHistorial();
                        break;
                    case 6:
                        consultarUltimoAtendido();
                        break;
                    case 7:
                        System.out.println("Gracias por usar el simulador.");
                        salir = true;
                        break;
                    default:
                        System.out.println("Opción no válida. Intenta de nuevo.");
                }
            } catch (Exception e) {
                System.out.println("Error: Ingresa un número válido");
                scanner.nextLine();
            }
        }
        scanner.close();
    }

    public static void main(String[] args) {
        Simulador simulador = new Simulador();
        simulador.iniciar();
    }
}