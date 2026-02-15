import javax.swing.*;
import java.awt.*;

    public class Ejemplo08_ImagenFondo extends JFrame {

        public Ejemplo08_ImagenFondo() {
        setTitle("Ejemplo 8 - Imagen de Fondo");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        //-------------IMAGEN--------------
        setContentPane(new PanelConFondo());
        getContentPane().setLayout(null);

        JLabel lblTitulo = new JLabel("Imagen en el fondo");
        lblTitulo.setBounds(250, 50, 300, 30);
        lblTitulo.setFont(new Font( "Times New Roman ", Font.BOLD, 20));
        lblTitulo.setForeground(Color.black);
        add(lblTitulo);

        JButton btn = new JButton("Botón sobre imagen");
        btn.setBounds(220, 150, 160, 30);
        add(btn);

        JTextField txt = new JTextField("Campo de texto");
        txt.setBounds(220, 200, 160, 30);
        add(txt);
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Ejemplo08_ImagenFondo().setVisible(true);
            }
        });
    }
}
//------------AJUSTAR IMAGEN AL TAMAÑO DE LA VENTANA--------------------
class PanelConFondo extends JPanel {

    private Image imagen;

    public PanelConFondo() {
        imagen = new ImageIcon(
                getClass().getResource("fondo.jpg")
        ).getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Dibuja la imagen ajustándola al tamaño actual del panel
        g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
    }
}