public class Cajero {
    private int fondo;
    private static final int LIMITE_DEPOSITO = 19000;

    public Cajero(int fondoInicial) {
        fondo = fondoInicial;
    }

    public int getFondo() {
        return fondo;
    }

    public boolean retirar(int cantidad) {
    if (cantidad <= fondo && cantidad >= 0) {
        fondo -= cantidad;
        return true;
    }
    return false;
}

    public boolean depositar(String clave, int cantidad) {
        if (clave.equals("9999") && cantidad <= LIMITE_DEPOSITO) {
            fondo += cantidad;
            return true;
        }
        return false;
    }
}