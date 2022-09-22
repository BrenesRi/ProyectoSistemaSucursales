package sistemaDeSucursales.presentation.empleados;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;


public class View implements Observer {
    private JLabel nombreLbl;
    private JTextField textField1;
    private JButton buscarFld;
    private JButton agregarFld;
    private JButton borrarFld;
    private JButton reporteFld;
    private JTable empleadosFld;
    private JPanel panel;

    public View() {

        buscarFld.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.buscar(nombreLbl.getText());
            }
        });
        agregarFld.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //controller.preAgregar();
            }
        });
        empleadosFld.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() ==2){
                    int row = empleadosFld.getSelectedRow();
                    controller.editar(row);
                }
                super.mouseClicked(e);
            }
        });
    }
    public JPanel getPanel() {
        return panel;
    }

    Controller controller;

    Model model;

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void setModel(Model model) {
        this.model = model;
        model.addObserver(this);
    }

    @Override
    public void update(Observable updatedModel, Object parametros) {
        int[] cols = {TableModel.CEDULA, TableModel.NOMBRE, TableModel.TELEFONO, TableModel.SUCURSAL, TableModel.ZONAJE, TableModel.SALARIOTOTAL};
        empleadosFld.setModel(new TableModel(model.getEmpleados(), cols));
        empleadosFld.setRowHeight(30);
        this.panel.revalidate();
    }

}