package vista;

import controller.Controlador;

import java.util.Scanner;

public class ConsoleView {

    private Controlador controlador;
    private Scanner scanner;

    public ConsoleView(Controlador controlador){
        this.controlador = controlador;
        this.scanner = new Scanner(System.in);
    }

    public void init(){
        int opcion = 0;
        do{
            mostrarMenu();
            try{
                opcion = Integer.parseInt(scanner.nextLine());
                procesarOpcion(opcion);
            } catch (NumberFormatException e) {
                System.out.println("Ingrese un numero valido");
            }
        }while (opcion != 6);
        scanner.close();
    }

    private void mostrarMenu(){
        System.out.println("\n=== SISTEMA BIBLIOTECA (MVC - LISTAS) ===");
        System.out.println("1. Prestar Libro");
        System.out.println("2. Devolver Libro");
        System.out.println("3. Reservar Libro");
        System.out.println("4. Consultar Multas");
        System.out.println("5. Reporte General");
        System.out.println("6. Guardar y Salir");
        System.out.print("Seleccione una opción: ");
    }

    private void procesarOpcion(int opcion){
        try{
            switch (opcion) {
                case 1:
                    System.out.print("Ingrese ID numérico del Libro (ej. 1): ");
                    int idL = Integer.parseInt(scanner.nextLine());
                    System.out.print("Ingrese ID numérico del Cliente (ej. 100): ");
                    int idC = Integer.parseInt(scanner.nextLine());
                    System.out.println(controlador.prestarLibro(idL, idC));
                    break;
                case 2:
                    System.out.print("Ingrese ID numérico del Libro a devolver: ");
                    System.out.println(controlador.devolverLibro(Integer.parseInt(scanner.nextLine())));
                    break;
                case 3:
                    System.out.print("Ingrese ID numérico del Libro a reservar: ");
                    int idLRes = Integer.parseInt(scanner.nextLine());
                    System.out.print("Ingrese ID numérico de su Cliente: ");
                    int idCRes = Integer.parseInt(scanner.nextLine());
                    System.out.println(controlador.reservarLibro(idLRes, idCRes));
                    break;
                case 4:
                    System.out.print("Ingrese ID numérico del Cliente: ");
                    System.out.println(controlador.consultarMultas(Integer.parseInt(scanner.nextLine())));
                    break;
                case 5:
                    System.out.println(controlador.generarReporte());
                    break;
                case 6:
                    controlador.guardarDatos();
                    System.out.println("Datos guardados. ¡Hasta pronto!");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }catch(NumberFormatException e){
            System.out.println("Error: Ingrese una opcion valida");
        }
    }

}
