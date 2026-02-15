import javax.swing.*;
import java.awt.*;

public class Ejemplo04_GridLayoutFormulario extends JFrame {

    public Ejemplo04_GridLayoutFormulario() {
        setTitle("Ejemplo 4 - GridLayout");
        setSize(420, 160);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel form = new JPanel(new GridLayout(2, 2, 8, 8));
        form.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));

        form.add(new JLabel("Usuario:"));
        form.add(new JTextField());

        form.add(new JLabel("Password:"));
        form.add(new JTextField());

        setContentPane(form);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Ejemplo04_GridLayoutFormulario().setVisible(true);
            }
        });
    }
}