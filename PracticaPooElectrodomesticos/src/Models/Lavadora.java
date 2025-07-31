package Models;

public class Lavadora extends Electrodomesticos {
    private final double CARGA_DEFAULT = 5.0;
    protected double carga;

    public Lavadora() {
        super();
        this.carga = CARGA_DEFAULT;
    }

    public Lavadora(double precioBase, double peso) {
        super(precioBase, peso);
        this.carga = CARGA_DEFAULT;
    }

    public Lavadora(double carga, double precioBase, String color, char consumoEnergetico, double peso) {
        super(precioBase, color, consumoEnergetico, peso);
        this.carga = carga;
    }

    public double getCarga() {
        return carga;
    }

    @Override
    public double precioFinal() {
        double precio = super.precioFinal();
        if (carga > 30) {
            precio += 50;
        }
        return precio;
    }
}