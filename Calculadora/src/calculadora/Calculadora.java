package calculadora;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculadora extends JFrame {

    // Atributos de la calculadora
    private JTextField pantalla;  // Pantalla donde se muestra la operación y el resultado
    private String operacion = "";  // Variable que almacena la operación que el usuario escribe

    // Constructor
    public Calculadora() {
        setTitle("Calculadora");  // Título de la ventana
        setSize(400, 500);  // Tamaño de la ventana
        setDefaultCloseOperation(EXIT_ON_CLOSE);  // Cerrar la aplicación al cerrar la ventana
        setLocationRelativeTo(null);  // Centrar la ventana en la pantalla
        setLayout(new BorderLayout());  // Usamos BorderLayout para la disposición de los componentes

        // Crear la pantalla de la calculadora (un JTextField)
        pantalla = new JTextField();
        pantalla.setFont(new Font("Arial", Font.PLAIN, 24));  // Fuente para el texto
        pantalla.setHorizontalAlignment(JTextField.RIGHT);  // Alineación a la derecha
        pantalla.setEditable(false);  // No se puede editar directamente
        add(pantalla, BorderLayout.NORTH);  // Agregamos la pantalla en la parte superior

        // Panel para los botones de la calculadora
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(5, 4, 10, 10));  // Usamos un GridLayout para organizar los botones

        // Botones de la calculadora, los botones para números y operaciones
        String[][] botones = {
            {"7", "8", "9", "/"},
            {"4", "5", "6", "*"},
            {"1", "2", "3", "-"},
            {"0", ".", "=", "+"},
            {"C", "", "", ""}
        };

        // Añadir los botones al panel
        for (int i = 0; i < botones.length; i++) {
            for (int j = 0; j < botones[i].length; j++) {
                String texto = botones[i][j];
                if (!texto.equals("")) {
                    JButton boton = new JButton(texto);
                    boton.setFont(new Font("Arial", Font.PLAIN, 18));  // Ajuste de tamaño de fuente
                    boton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            // Acción cuando se presiona un botón
                            procesarBoton(texto);
                        }
                    });
                    panelBotones.add(boton);  // Agregar el botón al panel
                }
            }
        }

        // Agregar el panel de botones a la ventana
        add(panelBotones, BorderLayout.CENTER);
    }

    // Método para procesar los botones cuando se presionan
    private void procesarBoton(String texto) {
        if (texto.equals("=")) {
            // Al presionar "=" se evalúa la operación
            try {
                double resultado = evaluarOperacion(operacion);  // Evaluar la operación
                pantalla.setText(String.valueOf(resultado));  // Mostrar el resultado
                operacion = String.valueOf(resultado);  // Actualizar la operación para posibles cálculos adicionales
            } catch (Exception e) {
                pantalla.setText("Error");  // Si hay un error, mostrar "Error"
                operacion = "";  // Limpiar la operación
            }
        } else if (texto.equals("C")) {
            // Si se presiona "C", se limpia la operación y la pantalla
            operacion = "";  // Limpiar la operación almacenada
            pantalla.setText("");  // Limpiar la pantalla
        } else if (texto.equals(".")) {
            // Agregar punto decimal solo si no hay ya uno en la operación
            if (!pantalla.getText().contains(".")) {
                pantalla.setText(pantalla.getText() + ".");
                operacion += ".";
            }
        } else {
            // Si se presiona un número o un operador, lo agregamos a la operación
            operacion += texto;
            pantalla.setText(operacion);  // Actualizar la pantalla con la operación en curso
        }
    }

    // Método para evaluar la operación de manera manual
    private double evaluarOperacion(String operacion) {
        try {
            // Eliminar espacios y preparar la operación
            operacion = operacion.replace(" ", "");

            // Manejar operaciones básicas de forma manual
            if (operacion.contains("+")) {
                String[] partes = operacion.split("\\+");
                double num1 = Double.parseDouble(partes[0]);
                double num2 = Double.parseDouble(partes[1]);
                return num1 + num2;
            } else if (operacion.contains("-")) {
                String[] partes = operacion.split("-");
                double num1 = Double.parseDouble(partes[0]);
                double num2 = Double.parseDouble(partes[1]);
                return num1 - num2;
            } else if (operacion.contains("*")) {
                String[] partes = operacion.split("\\*");
                double num1 = Double.parseDouble(partes[0]);
                double num2 = Double.parseDouble(partes[1]);
                return num1 * num2;
            } else if (operacion.contains("/")) {
                String[] partes = operacion.split("/");
                double num1 = Double.parseDouble(partes[0]);
                double num2 = Double.parseDouble(partes[1]);
                if (num2 != 0) {
                    return num1 / num2;
                } else {
                    return Double.NaN; // Error de división por cero
                }
            }
            return Double.NaN;  // Si no se encuentra un operador, devolver NaN

        } catch (Exception e) {
            return Double.NaN; // Error si la operación no se puede evaluar
        }
    }

    // Método principal que ejecuta la calculadora
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Calculadora calculadora = new Calculadora();  // Crear la instancia de la calculadora
                calculadora.setVisible(true);  // Hacer visible la ventana de la calculadora
            }
        });
    }
}
