package sistemaDeSucursales.presentation.sucursales;

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
    private JLabel referenciaLbl;
    private JTextField referenciaFld;
    private JButton buscarFld;
    private JButton agregarFld;
    private JButton borrarFld;
    private JButton reporteFld;
    private JLabel CustomMap;
    private JTable sucursalesFld;
    private JPanel panel;

    public View() {

        buscarFld.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
             controller.buscar(referenciaFld.getText());
            }
        });
        agregarFld.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //controller.preAgregar();
            }
        });

        sucursalesFld.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() ==2){
                    int row = sucursalesFld.getSelectedRow();
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
    public void update(Observable updatedModel, Object parametros) {
        int[] cols = {TableModel.CODIGO, TableModel.REFERENCIA, TableModel.DIRECCION, TableModel.ZONAJE};
        sucursalesFld.setModel(new TableModel(model.getSucursales(),cols));
        sucursalesFld.setRowHeight(30);
        this.panel.revalidate();
    }

    private void createUIComponents() {
        CustomMap = new JLabel(new ImageIcon("mapa.png"));
    }
}
