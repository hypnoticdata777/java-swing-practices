import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Ejemplo07_ComboBoxSeleccion extends JFrame {

    public Ejemplo07_ComboBoxSeleccion() {
        setTitle("Ejemplo 7 - JComboBox");
        setSize(460, 170);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panel.setBackground(getForeground());

        final JComboBox<String> combo = new JComboBox<String>(
                new String[] { "Coche", "Trailer", "Moto", "Avion" }
        );

        final JLabel lbl = new JLabel("Selecciona un vehículo");
        JButton btn = new JButton("Aceptar");

        btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                lbl.setText("Elegiste: " + combo.getSelectedItem());
            }
        });

        panel.add(combo);
        panel.add(btn);
        panel.add(lbl);

        setContentPane(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Ejemplo07_ComboBoxSeleccion().setVisible(true);
            }
        });
    }
}