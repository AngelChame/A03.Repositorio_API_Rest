package Models;

public class Electrodomesticos {
    private final String COLOR_DEFAULT = "blanco";
    private final char CONSUMO_DEFAULT = 'F';
    private final double PRECIO_BASE = 100.0;
    private final double PRECIO_DEFAULT = 5.0;

    private final String[] colores = {"Blanco", "Negro", "Rojo", "Azul", "Gris"};
    private final char[] CONSUMO_ENERGETICO = {'A', 'B', 'C', 'D', 'E', 'F'};

    protected double precioBase;
    protected String color;
    protected char consumoEnergetico;
    protected double peso;

    public Electrodomesticos() {
        this.precioBase = PRECIO_BASE;
        this.color = COLOR_DEFAULT;
        this.consumoEnergetico = CONSUMO_DEFAULT;
        this.peso = PRECIO_DEFAULT;
    }

    public Electrodomesticos(double precioBase, double peso) {
        this.precioBase = precioBase;
        this.peso = peso;
        this.color = COLOR_DEFAULT;
        this.consumoEnergetico = CONSUMO_DEFAULT;
    }

    public Electrodomesticos(double precioBase, String color, char consumoEnergetico, double peso) {
        this.precioBase = precioBase;
        this.color = comprobarColor(color);
        this.consumoEnergetico = comprobarConsumoEnergetico(consumoEnergetico);
        this.peso = peso;
    }

    public double getPrecioBase() {
        return precioBase;
    }

    public String getColor() {
        return color;
    }

    public char getConsumoEnergetico() {
        return consumoEnergetico;
    }

    public double getPeso() {
        return peso;
    }

    private char comprobarConsumoEnergetico(char letra) {
        for (char c : CONSUMO_ENERGETICO) {
            if (c == letra) {
                return letra;
            }
        }
        return CONSUMO_DEFAULT;
    }

    private String comprobarColor(String color) {
        for (String c : colores) {
            if (c.equalsIgnoreCase(color)) {
                return color;
            }
        }
        return COLOR_DEFAULT;
    }

    public double precioFinal() {
        double precio = precioBase;
        switch (consumoEnergetico) {
            case 'A':
                precio += 100;
                break;
            case 'B':
                precio += 80;
                break;
            case 'C':
                precio += 60;
                break;
            case 'D':
                precio += 50;
                break;
            case 'E':
                precio += 30;
                break;
            case 'F':
                precio += 10;
                break;
        }

        if (peso >= 0 && peso <= 19) {
            precio += 10;
        } else if (peso >= 20 && peso <= 49) {
            precio += 50;
        } else if (peso >= 50 && peso <= 79) {
            precio += 80;
        } else if (peso >= 80) {
            precio += 100;
        }

        return precio;
    }

    public String toString() {
        return "Electrodomestico{" +
                "precioBase=" + precioBase +
                ", color='" + color + '\'' +
                ", consumoEnergetico=" + consumoEnergetico +
                ", peso=" + peso +
                ", precioFinal=" + precioFinal() +
                '}';
    }
}