import javax.swing.*; //Esto importa la biblioteca Swing, que contiene componentes gráficos ya construidos.
//JFrame → ventana, JLabel → texto, JButton → botón, JTextField → caja de texto

public class Ejemplo01_JFrameBasico extends JFrame {

    public Ejemplo01_JFrameBasico() {
        setTitle("Ejemplo 1 - JFrame + JLabel");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);//se ejecuta en el centro de la ventana de la pc
        setResizable(true);//Sirve para poder cambiarle el tamaño a la ventana

        JLabel lbl = new JLabel("Hola Swing (JLabel)");
        add(lbl); // agrega el JLabel dentro del JFrame
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Ejemplo01_JFrameBasico().setVisible(true);
            }
        });
    }
}