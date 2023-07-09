import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {
    private Cajero cajero;
    private JTextField cantidadRetiroField;
    private JTextField claveDepositoField;
    private JTextField cantidadDepositoField;
    private JLabel fondoCajeroLabel;
    private JLabel mensajeLabel;

    public Main() {
        cajero = new Cajero(10000); // Inicializamos el cajero con 10,000 pesos

        // Configuración de la interfaz
        setTitle("Cajero Automático");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 2));

        // Componentes de la interfaz
        JLabel cantidadRetiroLabel = new JLabel("Cantidad de retiro:");
        cantidadRetiroField = new JTextField();
        JButton botonRetirar = new JButton("Retirar");
        JLabel claveDepositoLabel = new JLabel("Clave de depósito:");
        claveDepositoField = new JTextField();
        JLabel cantidadDepositoLabel = new JLabel("Cantidad de depósito:");
        cantidadDepositoField = new JTextField();
        JButton botonDepositar = new JButton("Depositar");
        fondoCajeroLabel = new JLabel("Fondo en el cajero: $" + cajero.getFondo());
        mensajeLabel = new JLabel();

        // Acciones del botón "Retirar"
        botonRetirar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int cantidad = Integer.parseInt(cantidadRetiroField.getText());
                if (cantidad <= cajero.getFondo()) {
                    boolean exito = cajero.retirar(cantidad);
                    if (exito) {
                        mensajeLabel.setText("Retiro exitoso de $" + cantidad);
                    } else {
                        mensajeLabel.setText("No se pudo realizar el retiro.");
                    }
                } else {
                    mensajeLabel.setText("No hay más dinero para retirar.");
                }
                fondoCajeroLabel.setText("Fondo en el cajero: $" + cajero.getFondo());
            }
        });

        // Acciones del botón "Depositar"
        botonDepositar.addActionListener((ActionEvent e) -> {
            String clave = claveDepositoField.getText();
            int cantidad = Integer.parseInt(cantidadDepositoField.getText());
            if (cantidad <= 19000) {
                boolean exito = cajero.depositar(clave, cantidad);
                if (exito) {
                    mensajeLabel.setText("Depósito exitoso de $" + cantidad);
                } else {
                    mensajeLabel.setText("No se pudo realizar el depósito. Clave incorrecta.");
                }
            } else {
                mensajeLabel.setText("Límite de depósito excedido. Máximo: $19,000");
            }
            fondoCajeroLabel.setText("Fondo en el cajero: $" + cajero.getFondo());
        });

        // Agregar componentes a la interfaz
        add(cantidadRetiroLabel);
        add(cantidadRetiroField);
        add(botonRetirar);
        add(claveDepositoLabel);
        add(claveDepositoField);
        add(cantidadDepositoLabel);
        add(cantidadDepositoField);
        add(botonDepositar);
        add(fondoCajeroLabel);
        add(mensajeLabel);

        setVisible(true);
    }

    public static void main(String[] args) {
        Main main = new Main();
    }
}

class Cajero {
    private int fondo;

    public Cajero(int fondoInicial) {
        fondo = fondoInicial;
    }

    public int getFondo() {
        return fondo;
    }

    public boolean retirar(int cantidad) {
        if (cantidad % 50 == 0 && cantidad <= fondo) {
            fondo -= cantidad;
            return true;
        }
        return false;
    }

    public boolean depositar(String clave, int cantidad) {
        // Verificar la clave (puedes agregar tu lógica de validación aquí)
        if (clave.equals("1234")) {
            fondo += cantidad;
            return true;
        }
        return false;
    }
}