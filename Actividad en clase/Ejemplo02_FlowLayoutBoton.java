import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Ejemplo02_FlowLayoutBoton extends JFrame {

    public Ejemplo02_FlowLayoutBoton() {
        setTitle("Ejemplo 2 - FlowLayout");
        setSize(420, 160);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 50));
        panel.setBackground(Color.CYAN);

        final JLabel lbl = new JLabel("Estado: esperando...");
        JButton btn = new JButton("Cambiar");

        btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                lbl.setText("Estado: botón presionado");
            }
        });

        //panel.add(lbl);
        panel.add(btn);

        setContentPane(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Ejemplo02_FlowLayoutBoton().setVisible(true);
            }
        });
    }
}