package Models;

public class Television extends Electrodomesticos {
    private final int RESOLUCION_DEFAULT = 20;
    private final boolean sintonizadorTDT_default = false;

    protected int resolucion;
    protected boolean sintonizadorTDT;

    public Television() {
        super();
        this.resolucion = RESOLUCION_DEFAULT;
        this.sintonizadorTDT = sintonizadorTDT_default;
    }

    public Television(double precioBase, double peso) {
        super(precioBase, peso);
        this.resolucion = RESOLUCION_DEFAULT;
        this.sintonizadorTDT = sintonizadorTDT_default;
    }

    public Television(int resolucion, boolean sintonizadorTDT, double precioBase, String color, char consumoEnergetico, double peso) {
        super(precioBase, color, consumoEnergetico, peso);
        this.resolucion = resolucion;
        this.sintonizadorTDT = sintonizadorTDT;
    }

    public int getResolucion() {
        return resolucion;
    }

    public boolean isSintonizadorTDT() {
        return sintonizadorTDT;
    }

    @Override
    public double precioFinal() {
        double precio = super.precioFinal();
        if (resolucion > 40) {
            precio *= 1.3;
        }
        if (sintonizadorTDT) {
            precio += 50;
        }
        return precio;
    }
}