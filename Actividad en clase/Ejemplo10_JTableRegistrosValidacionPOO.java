package Clase;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class Ejemplo10_JTableRegistrosValidacionPOO extends JFrame {

    private JTextField txtId;
    private JTextField txtNombre;
    private JTextField txtSueldo;

    private JTable tabla;
    private DefaultTableModel modelo;

    private JButton btnAgregar;
    private JButton btnEditar;
    private JButton btnEliminar;
    private JButton btnLimpiar;

    public Ejemplo10_JTableRegistrosValidacionPOO() {
        setTitle("Ejemplo 8 - JTable (Registros POO) + Validación + Editar");
        setSize(720, 460);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel cont = new JPanel(new BorderLayout(10, 10));
        cont.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setContentPane(cont);

        // ---------- PANEL FORMULARIO (NORTH) ----------
        JPanel form = new JPanel(new GridLayout(3, 2, 8, 8));
        form.setBorder(BorderFactory.createTitledBorder("Datos del Empleado"));
        form.setBackground(new Color(220, 240, 255));

        form.add(new JLabel("ID (entero):"));
        txtId = new JTextField();
        form.add(txtId);

        form.add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        txtNombre.addKeyListener(new KeyAdapter() {
        @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();

                if (!Character.isLetter(c) && c != ' ') {
                    e.consume(); // bloquea el carácter
                }
            }
        });
        form.add(txtNombre);

        form.add(new JLabel("Sueldo (decimal):"));
        txtSueldo = new JTextField();
        form.add(txtSueldo);

        cont.add(form, BorderLayout.NORTH);

        // ---------- TABLA (CENTER) ----------
        String[] columnas = {"ID", "Nombre", "Sueldo"};

        // Modelo NO editable directamente (para obligar a usar botón Editar)
        modelo = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tabla = new JTable(modelo);
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBorder(BorderFactory.createTitledBorder("Registros"));
        cont.add(scroll, BorderLayout.CENTER);

        // ---------- PANEL BOTONES (SOUTH) ----------
        JPanel botones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        botones.setBorder(BorderFactory.createTitledBorder("Acciones"));
        botones.setBackground(new Color(200, 255, 200));

        btnAgregar = new JButton("Agregar");
        btnEditar = new JButton("Editar seleccionado");
        btnEliminar = new JButton("Eliminar seleccionado");
        btnLimpiar = new JButton("Limpiar campos");

        botones.add(btnAgregar);
        botones.add(btnEditar);
        botones.add(btnEliminar);
        botones.add(btnLimpiar);

        cont.add(botones, BorderLayout.SOUTH);

        // ---------- EVENTOS ----------
        btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarRegistro();
            }
        });

        btnEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editarSeleccionado();
            }
        });

        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarSeleccionadoConConfirmacion();
            }
        });

        btnLimpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarCampos();
            }
        });

        // Doble clic en tabla -> cargar datos a campos (útil para editar)
        tabla.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    cargarFilaSeleccionadaEnCampos();
                }
            }
        });
    }

    // -------------------- LÓGICA --------------------

    private void agregarRegistro() {
        EmpleadoDTO emp = leerCamposYValidar();
        if (emp == null) return;

        // Validación adicional: ID no repetido
        if (existeId(emp.id)) {
            JOptionPane.showMessageDialog(this, "El ID ya existe. Usa otro ID.", "ID duplicado", JOptionPane.WARNING_MESSAGE);
            txtId.requestFocus();
            return;
        }

        modelo.addRow(new Object[]{emp.id, emp.nombre, emp.sueldo});
        limpiarCampos();
    }

    private void editarSeleccionado() {
        int fila = tabla.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona una fila para editar.", "Sin selección", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        EmpleadoDTO emp = leerCamposYValidar();
        if (emp == null) return;

        // Si cambian el ID, verificar que no choque con otro registro
        int idActual = Integer.parseInt(String.valueOf(modelo.getValueAt(fila, 0)));
        if (emp.id != idActual && existeId(emp.id)) {
            JOptionPane.showMessageDialog(this, "Ese nuevo ID ya existe en otra fila.", "ID duplicado", JOptionPane.WARNING_MESSAGE);
            txtId.requestFocus();
            return;
        }

        // Confirmación antes de aplicar cambios
        int r = JOptionPane.showConfirmDialog(
                this,
                "¿Deseas guardar los cambios de la fila seleccionada?",
                "Confirmar edición",
                JOptionPane.YES_NO_OPTION
        );

        if (r != JOptionPane.YES_OPTION) return;

        modelo.setValueAt(emp.id, fila, 0);
        modelo.setValueAt(emp.nombre, fila, 1);
        modelo.setValueAt(emp.sueldo, fila, 2);

        limpiarCampos();
        tabla.clearSelection();
    }

    private void eliminarSeleccionadoConConfirmacion() {
        int fila = tabla.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona una fila para eliminar.", "Sin selección", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        Object id = modelo.getValueAt(fila, 0);
        Object nombre = modelo.getValueAt(fila, 1);

        int r = JOptionPane.showConfirmDialog(
                this,
                "¿Estás seguro de eliminar este registro?\nID: " + id + "\nNombre: " + nombre,
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
        );

        if (r == JOptionPane.YES_OPTION) {
            modelo.removeRow(fila);
            limpiarCampos();
            tabla.clearSelection();
        }
    }

    private void cargarFilaSeleccionadaEnCampos() {
        int fila = tabla.getSelectedRow();
        if (fila == -1) return;

        txtId.setText(String.valueOf(modelo.getValueAt(fila, 0)));
        txtNombre.setText(String.valueOf(modelo.getValueAt(fila, 1)));
        txtSueldo.setText(String.valueOf(modelo.getValueAt(fila, 2)));
    }

    private void limpiarCampos() {
        txtId.setText("");
        txtNombre.setText("");
        txtSueldo.setText("");
        txtId.requestFocus();
    }

    // -------------------- VALIDACIONES --------------------

    /**
     * Lee campos y valida:
     * - no vacíos
     * - ID entero
     * - Sueldo decimal
     * - Nombre con longitud mínima
     */
    private EmpleadoDTO leerCamposYValidar() {
        String idTxt = txtId.getText().trim();
        String nombre = txtNombre.getText().trim();
        String sueldoTxt = txtSueldo.getText().trim();

        if (idTxt.isEmpty() || nombre.isEmpty() || sueldoTxt.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Completa todos los campos.", "Campos vacíos", JOptionPane.WARNING_MESSAGE);
            return null;
        }

        // Validar ID entero
        int id;
        try {
            id = Integer.parseInt(idTxt);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "El ID debe ser un número entero.", "ID inválido", JOptionPane.ERROR_MESSAGE);
            txtId.requestFocus();
            return null;
        }

        // Validar nombre (simple)
        if (nombre.length() < 3) {
            JOptionPane.showMessageDialog(this, "El nombre debe tener al menos 3 caracteres.", "Nombre inválido", JOptionPane.WARNING_MESSAGE);
            txtNombre.requestFocus();
            return null;
        }

        // Validar sueldo decimal
        double sueldo;
        try {
            sueldo = Double.parseDouble(sueldoTxt);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "El sueldo debe ser un número (puede tener decimales).", "Sueldo inválido", JOptionPane.ERROR_MESSAGE);
            txtSueldo.requestFocus();
            return null;
        }

        if (sueldo < 0) {
            JOptionPane.showMessageDialog(this, "El sueldo no puede ser negativo.", "Sueldo inválido", JOptionPane.WARNING_MESSAGE);
            txtSueldo.requestFocus();
            return null;
        }

        return new EmpleadoDTO(id, nombre, sueldo);
    }

    private boolean existeId(int id) {
        for (int i = 0; i < modelo.getRowCount(); i++) {
            int idFila = Integer.parseInt(String.valueOf(modelo.getValueAt(i, 0)));
            if (idFila == id) return true;
        }
        return false;
    }

    // DTO simple para pasar datos validados (sin listas/BD)
    private static class EmpleadoDTO {
        int id;
        String nombre;
        double sueldo;

        EmpleadoDTO(int id, String nombre, double sueldo) {
            this.id = id;
            this.nombre = nombre;
            this.sueldo = sueldo;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Ejemplo10_JTableRegistrosValidacionPOO().setVisible(true);
            }
        });
    }
}
