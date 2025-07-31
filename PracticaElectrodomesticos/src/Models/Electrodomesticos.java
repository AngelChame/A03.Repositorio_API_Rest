package Models;

public class Electrodomesticos {

    private final String COLOR = "Blanco";

    private final char CONSUMO_ENERGETICO = 'F';

    private final int PRECIO_BASE = 100;

    final int PESO = 5;

    private String[] colors = {"Blanco", "Negro", "Rojo", "Azul", "Gris"};

    protected int precioBase;

    protected String color;

    protected char consumoEnergetico;

    protected int peso;

    public Electrodomesticos() {
        this.precioBase = PRECIO_BASE;
        this.color = COLOR;
        this.consumoEnergetico = CONSUMO_ENERGETICO;
        this.peso = PESO;
    }

    public Electrodomesticos(String nombre) {
        this.precioBase = peso;
        this.peso = peso;
        this.color = COLOR;
        this.consumoEnergetico = CONSUMO_ENERGETICO;
    }

    public Electrodomesticos(String[] colors, int precioBase, String color, char consumoEnergetico, int peso) {
        this.colors = colors;
        this.precioBase = precioBase;
        this.color = comprobarColor(color);
        this.consumoEnergetico = comprobarConsumoEnergetico(consumoEnergetico);
        this.peso = peso;
    }

    public int getPrecioBase() {
        return precioBase;
    }

    public String getColor() {
        return color;
    }

    public char getConsumoEnergetico() {
        return consumoEnergetico;
    }

    public int getPeso() {
        return peso;
    }

    private char comprobarConsumoEnergetico(char letra) {
        char[] letras = {'A', 'B', 'C', 'D', 'E', 'F'};
        boolean flag = false;

        for (int i = 0; i < letras.length && !flag; i++) {
            if (letras[i] == letra) {
                flag = true;
            }
        }
        return (flag) ? letra : CONSUMO_ENERGETICO;
    }

    private String comprobarColor(String color) {
        String[] colors = {"Blanco", "negro", "Rojo", "Azul", "Gris"};
        boolean flag = false;

        for (int i = 0; i < colors.length && !flag; i++) {
            if (colors[i].contains(color.toLowerCase())) {
                flag = true;
            }
        }
        return (flag) ? color : COLOR;
    }

    public precioFinal(char consumoEnergetico){
        int precioFinal = 0;
        switch (consumoEnergetico){
            case 'A' -> precioFinal = 100;
            case 'B' -> precioFinal = 200;
        }
    }

}


