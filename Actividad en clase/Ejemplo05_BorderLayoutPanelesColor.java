import javax.swing.*;
import java.awt.*;

public class Ejemplo05_BorderLayoutPanelesColor extends JFrame {

    public Ejemplo05_BorderLayoutPanelesColor() {
        setTitle("Ejemplo 5 - BorderLayout con Paneles");
        setSize(500, 320);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel cont = new JPanel(new BorderLayout(0, 10));
        cont.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel norte = new JPanel();
        norte.setBackground(new Color(255, 200, 200));
        norte.setBorder(BorderFactory.createTitledBorder("NORTE"));
        norte.add(new JLabel("NORTH"));

        JPanel centro = new JPanel();
        centro.setBackground(new Color(200, 255, 200));
        centro.setBorder(BorderFactory.createTitledBorder("CENTRO"));
        centro.add(new JLabel("CENTER"));

        JPanel sur = new JPanel();
        sur.setBackground(new Color(200, 200, 255));
        sur.setBorder(BorderFactory.createTitledBorder("SUR"));
        sur.add(new JLabel("SOUTH"));

        cont.add(centro, BorderLayout.CENTER);
        cont.add(norte, BorderLayout.NORTH);
        cont.add(sur, BorderLayout.SOUTH);
 
        setContentPane(cont);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Ejemplo05_BorderLayoutPanelesColor().setVisible(true);
            }
        });
    }
}