import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class MostrarTodos extends JFrame {
    private JTextArea txtResultados;

    public MostrarTodos() {
        super("Mostrar todos los datos");
        setLayout(new BorderLayout());

        txtResultados = new JTextArea(10, 20);
        add(new JScrollPane(txtResultados), BorderLayout.CENTER);

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/midb", "root", "123456");
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM personas");

            txtResultados.setText("");
            while (rs.next()) {
                txtResultados.append("ID: " + rs.getInt("id") + "\n");
                txtResultados.append("Nombre: " + rs.getString("nombre") + "\n");
                txtResultados.append("Apellido: " + rs.getString("apellido") + "\n");
                txtResultados.append("Edad: " + rs.getInt("edad") + "\n\n");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al mostrar datos");
        } /*finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }*/

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(1, 2)); // Usar GridLayout para alinear botones horizontalmente

        JButton btnVolver = new JButton("Volver");
        btnVolver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Buscar ventanaBuscar = new Buscar();
                ventanaBuscar.setVisible(true);
                dispose();
            }
        });

        JButton btnSeleccion = new JButton("Seleccion");
        btnSeleccion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Seleccion ventanaSeleccion = new Seleccion();
                ventanaSeleccion.setVisible(true);
                dispose();
            }
        });

        panelBotones.add(btnVolver);
        panelBotones.add(btnSeleccion);

        add(panelBotones, BorderLayout.SOUTH);

        setSize(500, 400);
    }

    public static void main(String[] args) {
        MostrarTodos ventanaMostrarTodos = new MostrarTodos();
        ventanaMostrarTodos.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventanaMostrarTodos.setVisible(true);
    }
}

