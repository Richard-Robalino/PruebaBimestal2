import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Insertar extends JFrame {
    private JTextField txtNombre;
    private JTextField txtApellido;
    private JTextField txtEdad;
    private JButton btnInsertar;

    public Insertar() {
        super("Insertar");
        setLayout(new FlowLayout());

        JLabel lblNombre = new JLabel("Nombre:");
        add(lblNombre);

        txtNombre = new JTextField(20);
        add(txtNombre);

        JLabel lblApellido = new JLabel("Apellido:");
        add(lblApellido);

        txtApellido = new JTextField(20);
        add(txtApellido);

        JLabel lblEdad = new JLabel("Edad:");
        add(lblEdad);

        txtEdad = new JTextField(20);
        add(txtEdad);

        btnInsertar = new JButton("Insertar");
        add(btnInsertar);

        btnInsertar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nombre = txtNombre.getText();
                String apellido = txtApellido.getText();
                int edad = Integer.parseInt(txtEdad.getText());

                try {
                    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/midb", "root", "password");
                    Statement stmt = conn.createStatement();
                    stmt.executeUpdate("INSERT INTO personas (nombre, apellido, edad) VALUES ('" + nombre + "', '" + apellido + "', " + edad + ")");

                    JOptionPane.showMessageDialog(null, "Datos insertados correctamente");
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error al insertar datos");
                }
            }
        });

        JButton btnVolver = new JButton("Volver");
        add(btnVolver);

        btnVolver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Seleccion ventanaSeleccion = new Seleccion();
                ventanaSeleccion.setVisible(true);
                dispose();
            }
        });
        setSize(300, 200);
    }

    public static void main(String[] args) {
        Insertar ventanaInsertar = new Insertar();
        ventanaInsertar.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventanaInsertar.pack();
        ventanaInsertar.setVisible(true);
    }
}