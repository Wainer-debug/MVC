
package Controlador;

import Modelo.Conexion;
import Modelo.Empleados;
import Modelo.Empleados;
import Vista.Ventana;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

public class Controlador implements ActionListener {

    Conexion conectar = new Conexion();
    Connection connect = conectar.getConexion();

    private Empleados mod; 
    private Ventana vista;

    public Controlador(Empleados mod, Ventana vista) {
        this.mod = mod;
        this.vista = vista;

        //agregar los botones para que esten escuchando
        this.vista.btnconsultar.addActionListener(this);
        this.vista.btnguardar.addActionListener(this);
        this.vista.btneliminar.addActionListener(this);
        this.vista.btnmodificar.addActionListener(this);
    }

    public void iniciar() {
        vista.setTitle("Reto 5 - MVC");
        vista.setLocationRelativeTo(null); //centro de nuestra pantalla
        vista.setVisible(true);
        vista.getContentPane().setBackground(Color.lightGray);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //eventos de los botones
        //tabla
        DefaultTableModel tablamodelo = (DefaultTableModel) vista.tablap.getModel();

        if (e.getSource() == vista.btnconsultar) {
            try {
                tablamodelo.setRowCount(0);
                ResultSet rs = null;
                PreparedStatement ps = connect.prepareStatement("SELECT id_empleados,tipo_trabajo,nombres,apellidos,antiguedad,tipo_documento,documento FROM empleados"); //consulta a la bd
                rs = ps.executeQuery(); //ejecutar la consulta
                while (rs.next()) {
                    tablamodelo.addRow(new Object[]{rs.getInt("id_empleados"), rs.getString("tipo_trabajo"), rs.getString("nombres"),
                    rs.getString("apellidos"),rs.getInt("antiguedad"),rs.getString("tipo_documento"),rs.getInt("documento")});
                }
            } catch (Exception ee) {
                JOptionPane.showMessageDialog(null, ee.getMessage().toString());
            }
        } else if (e.getSource() == vista.btnguardar) {
            try {
                tablamodelo.setRowCount(0);
                PreparedStatement ps = connect.prepareStatement("INSERT INTO empleados (tipo_trabajo,nombres,apellidos,antiguedad,tipo_documento,documento) VALUES (?,?,?,?,?,?)");
                ps.setString(1, vista.cbTipoTrabajo.getSelectedItem().toString());
                ps.setString(2, vista.txtNombres.getText());
                ps.setString(3, vista.txtApellidos.getText());
                ps.setInt(4, Integer.parseInt(vista.txtAntiguedad.getText()));
                ps.setString(5, vista.cbTipoDoc.getSelectedItem().toString());
                ps.setString(6, vista.txtNumDoc.getText());
                ps.execute();
                JOptionPane.showMessageDialog(null, "Datos guardados.");
            } catch (Exception ee) {
                JOptionPane.showMessageDialog(null, "Ingrese los datos correctos.");
            }
        } else if (e.getSource() == vista.btneliminar) {
            try {
                tablamodelo.setRowCount(0);
                PreparedStatement ps = connect.prepareStatement("DELETE FROM empleados WHERE id_empleados=?");
                ps.setInt(1, Integer.parseInt(vista.txtId.getText()));
                ps.execute();
                JOptionPane.showMessageDialog(null, "Datos eliminados.");
            } catch (Exception ee) {
                JOptionPane.showMessageDialog(null, "Ingrese ID a eliminar.");
            }
        } else if (e.getSource() == vista.btnmodificar) {
            try {
                tablamodelo.setRowCount(0);
                PreparedStatement ps = connect.prepareStatement("UPDATE empleados SET tipo_trabajo=?,nombres=?,apellidos=?,antiguedad=?,tipo_documento=?,documento=? WHERE id_empleados=?");
                ps.setString(1, vista.cbTipoTrabajo.getSelectedItem().toString());
                ps.setString(2, vista.txtNombres.getText());
                ps.setString(3, vista.txtApellidos.getText());
                ps.setInt(4, Integer.parseInt(vista.txtAntiguedad.getText()));
                ps.setString(5, vista.cbTipoDoc.getSelectedItem().toString());
                ps.setInt(6, Integer.parseInt(vista.txtNumDoc.getText()));
                ps.setInt(7, Integer.parseInt(vista.txtId.getText()));
                
                ps.execute();
                JOptionPane.showMessageDialog(null, "Datos actualizados.");
            } catch (Exception ee) {
                JOptionPane.showMessageDialog(null, "Ingrese datos a actualizar.");
            }
        }
    }
}

