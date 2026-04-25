# Simulador de Cola de Atención al Cliente

Este proyecto implementa un simulador de cola de atención al cliente para una sucursal bancaria utilizando pilas y colas implementadas desde cero.

## Funcionalidades
- Carga de clientes desde archivo data/clientes.txt.
- Cola de atención: encolar, desencolar, ver cola.
- Pila de historial: push, pop, peek, ver historial.
- Menú interactivo para gestionar operaciones.

## Requisitos
- JDK 11 o superior.

## Compilación y Ejecución
Compilar:
```
javac src/*.java
```
Ejecutar:
```
java -cp src Simulador
```

## Estructura
- src/Nodo.java: Clase genérica de nodo.
- src/Cola.java: Implementación de cola.
- src/Pila.java: Implementación de pila.
- src/Cliente.java: Clase cliente.
- src/Simulador.java: Clase principal.
- data/clientes.txt: Archivo de clientes.