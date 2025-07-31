import Models.Electrodomesticos;
import Models.Lavadora;
import Models.Television;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Electrodomesticos[] electrodomesticos = new Electrodomesticos[10];

        System.out.println("Ingrese los datos para 10 electrodomésticos:");

        for (int i = 0; i < electrodomesticos.length; i++) {
            System.out.println("\nElectrodoméstico #" + (i + 1));
            System.out.println("Seleccione el tipo:");
            System.out.println("1. Electrodoméstico básico");
            System.out.println("2. Lavadora");
            System.out.println("3. Televisión");
            System.out.print("Opción: ");
            int tipo = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Precio base: ");
            double precioBase = scanner.nextDouble();
            System.out.print("Peso (kg): ");
            double peso = scanner.nextDouble();
            scanner.nextLine();

            System.out.print("Color (Blanco, Negro, Rojo, Azul, Gris): ");
            String color = scanner.nextLine();
            System.out.print("Consumo energético (A-F): ");
            char consumo = scanner.nextLine().toUpperCase().charAt(0);

            switch (tipo) {
                case 1:
                    electrodomesticos[i] = new Electrodomesticos(precioBase, color, consumo, peso);
                    break;
                case 2:
                    System.out.print("Carga de la lavadora (kg): ");
                    double carga = scanner.nextDouble();
                    electrodomesticos[i] = new Lavadora(carga, precioBase, color, consumo, peso);
                    break;
                case 3:
                    System.out.print("Resolución de la TV (pulgadas): ");
                    int resolucion = scanner.nextInt();
                    System.out.print("¿Tiene sintonizador TDT? (true/false): ");
                    boolean tdt = scanner.nextBoolean();
                    electrodomesticos[i] = new Television(resolucion, tdt, precioBase, color, consumo, peso);
                    break;
                default:
                    System.out.println("Opción no válida, se creará un electrodoméstico básico");
                    electrodomesticos[i] = new Electrodomesticos(precioBase, color, consumo, peso);
            }
        }

        scanner.close();

        double precioElectrodomesticos = 0;
        double precioLavadoras = 0;
        double precioTelevisiones = 0;

        for (Electrodomesticos e : electrodomesticos) {
            double precio = e.precioFinal();
            precioElectrodomesticos += precio;

            if (e instanceof Lavadora) {
                precioLavadoras += precio;
            } else if (e instanceof Television) {
                precioTelevisiones += precio;
            }
        }

        System.out.println("\nResumen de precios:");
        System.out.println("Precio total de electrodomésticos: " + precioElectrodomesticos);
        System.out.println("Precio total de lavadoras: " + precioLavadoras);
        System.out.println("Precio total de televisiones: " + precioTelevisiones);
    }
}