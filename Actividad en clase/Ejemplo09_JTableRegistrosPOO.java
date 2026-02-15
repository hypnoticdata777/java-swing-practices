import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class Ejemplo09_JTableRegistrosPOO extends JFrame {

    // Campos de captura (atributos del objeto)
    private JTextField txtId;
    private JTextField txtNombre;
    private JTextField txtSueldo;

    // Tabla y su modelo (los "datos" de la tabla)
    private JTable tabla;
    private DefaultTableModel modelo;

    public Ejemplo09_JTableRegistrosPOO() {

        setTitle("Ejemplo 8 - JTable (Registros POO)");
        setSize(650, 420);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        

        // Contenedor principal con BorderLayout
        JPanel cont = new JPanel(new BorderLayout(10, 10));
        cont.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setContentPane(cont);

        // ---------- PANEL FORMULARIO (NORTH) ----------
        JPanel form = new JPanel(new GridLayout(3, 2, 8, 8));
        form.setBorder(BorderFactory.createTitledBorder("Datos del Empleado"));
        form.setBackground(new Color(220, 240, 255)); // color didáctico

        form.add(new JLabel("ID:"));
        txtId = new JTextField();
        form.add(txtId);

        form.add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        form.add(txtNombre);

        form.add(new JLabel("Sueldo:"));
        txtSueldo = new JTextField();
        form.add(txtSueldo);

        cont.add(form, BorderLayout.NORTH);

        // ---------- TABLA (CENTER) ----------
        // Columnas que representan atributos del objeto Empleado
        String[] columnas = { "ID", "Nombre", "Sueldo" };

        // Modelo: donde realmente se guardan las filas/columnas
        modelo = new DefaultTableModel(columnas, 0);

        // JTable: componente visual que muestra el modelo
        tabla = new JTable(modelo);

        // Scroll para la tabla
        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBorder(BorderFactory.createTitledBorder("Registros"));
        cont.add(scroll, BorderLayout.CENTER);

        // ---------- PANEL BOTONES (SOUTH) ----------
        JPanel botones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        botones.setBorder(BorderFactory.createTitledBorder("Acciones"));
        botones.setBackground(new Color(200, 255, 200)); 

        JButton btnAgregar = new JButton("Agregar");
        JButton btnEliminar = new JButton("Eliminar seleccionado");
        JButton btnLimpiar = new JButton("Limpiar campos");

        botones.add(btnAgregar);
        botones.add(btnEliminar);
        botones.add(btnLimpiar);

        cont.add(botones, BorderLayout.SOUTH);

        // ---------- EVENTOS ----------
        btnAgregar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                agregarRegistro();
            }
        });

        btnEliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                eliminarSeleccionado();
            }
        });

        btnLimpiar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                limpiarCampos();
            }
        });
    }

    // Simula "crear un objeto" y guardarlo en la tabla
    private void agregarRegistro() {
        String id = txtId.getText();
        String nombre = txtNombre.getText();
        String sueldo = txtSueldo.getText();

        // Sin validaciones avanzadas: solo agrega lo que haya
        Object[] fila = { id, nombre, sueldo };
        modelo.addRow(fila);

        limpiarCampos();
    }

    private void eliminarSeleccionado() {
        int filaSeleccionada = tabla.getSelectedRow();
        if (filaSeleccionada != -1) {
            modelo.removeRow(filaSeleccionada);
        } else {
            JOptionPane.showMessageDialog(this, "Selecciona una fila para eliminar.");
        }
    }

    private void limpiarCampos() {
        txtId.setText("");
        txtNombre.setText("");
        txtSueldo.setText("");
        txtId.requestFocus();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Ejemplo09_JTableRegistrosPOO().setVisible(true);
            }
        });
    }
}