import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Ejemplo03_TextFieldLeer extends JFrame {

    public Ejemplo03_TextFieldLeer() {
        setTitle("Ejemplo 3 - JTextField");
        setSize(450, 180);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        JLabel lbl = new JLabel("Nombre:");
        final JTextField txt = new JTextField(18);
        JButton btn = new JButton("Saludar");

        btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nombre = txt.getText();
                JOptionPane.showMessageDialog(null, "Hola " + nombre);
            }
        });

        panel.add(lbl);
        panel.add(txt);
        panel.add(btn);

        setContentPane(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Ejemplo03_TextFieldLeer().setVisible(true);
            }
        });
    }
}