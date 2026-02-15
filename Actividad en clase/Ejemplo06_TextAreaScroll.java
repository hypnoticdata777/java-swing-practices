import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Ejemplo06_TextAreaScroll extends JFrame {

    public Ejemplo06_TextAreaScroll() {
        setTitle("Ejemplo 6 - JTextArea + Scroll");
        setSize(520, 360);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        final JTextArea area = new JTextArea();
        area.setEditable(true);

        JScrollPane scroll = new JScrollPane(area);

        JButton btn = new JButton("Agregar línea");
        btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                area.append("\nNueva línea...\n");
            }
        });

        JPanel cont = new JPanel(new BorderLayout(10, 10));
        cont.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        cont.add(scroll, BorderLayout.CENTER);
        cont.add(btn, BorderLayout.SOUTH);

        setContentPane(cont);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Ejemplo06_TextAreaScroll().setVisible(true);
            }
        });
    }
}