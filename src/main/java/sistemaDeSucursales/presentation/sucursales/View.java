package sistemaDeSucursales.presentation.sucursales;

import sistemaDeSucursales.Application;
import sistemaDeSucursales.data.Data;
import sistemaDeSucursales.logic.Service;
import sistemaDeSucursales.logic.Sucursal;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
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
    private JPanel panelImage;

    public View() {
        cargarImagen();
        buscarFld.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                controller.buscar(referenciaFld.getText());
                actCustomMap();
            }
        });
        agregarFld.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.preAgregar();
            }
        });

        sucursalesFld.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                if(e.getClickCount()==1){
                   int key = sucursalesFld.getSelectedRow();
                   String codigo = model.getSucursales().get(key).getCodigo();
                   interactCustomMap(codigo);
                }

                if (e.getClickCount() ==2){
                    int row = sucursalesFld.getSelectedRow();
                    controller.editar(row);
                }
                super.mouseClicked(e);
            }
        });

        borrarFld.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = sucursalesFld.getSelectedRow();
                controller.borrar(row);
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

    private void cargarImagen(){
        Image mapa;
        try {
            mapa = ImageIO.read(new File("resources/mapa.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        mapa =  mapa.getScaledInstance(455,400,1);
        CustomMap.setIcon(new ImageIcon(mapa));
        actCustomMap();
        interactCustomMap("");
    }

    private void actCustomMap(){
        CustomMap.removeAll();
        int x = 0;
        int y = 0;
        Image blueP;
        try {
            blueP = ImageIO.read(new File("resources/Sucursal.png"));
        } catch (IOException o) {
            throw new RuntimeException(o);
        }
        List<Sucursal> temp = Service.instance().sucursalSearch("");
        CustomMap.setLayout(null);
        for (Sucursal sucursal : temp) {
            JLabel pointer = new JLabel();
            x = sucursal.getUbicacionX();
            y = sucursal.getUbicacionY();
            blueP = blueP.getScaledInstance(20, 20, 1);
            pointer.setIcon(new ImageIcon(blueP));
            pointer.setBounds(x, y, 20, 20);
            CustomMap.add(pointer);
        }
        CustomMap.repaint();
        temp = Service.instance().sucursalSearch("");
    }

    private void interactCustomMap(String cod){
        CustomMap.removeAll();
        int x = 0;
        int y = 0;
        Image blueP;
        Image redP;
        try {
            blueP = ImageIO.read(new File("resources/Sucursal.png"));
            redP = ImageIO.read(new File("resources/SucursalSel.png"));
        } catch (IOException o) {
            throw new RuntimeException(o);
        }
        List<Sucursal> temp = Service.instance().sucursalSearch("");
        CustomMap.setLayout(null);
        for (Sucursal sucursal : temp) {
            JLabel pointer = new JLabel();
            pointer.setToolTipText(sucursal.getReferencia()+" | "+sucursal.getDireccion());
            pointer.putClientProperty("codigo",sucursal.getCodigo());
            pointer.putClientProperty("refer",sucursal.getReferencia());
            x = sucursal.getUbicacionX();
            y = sucursal.getUbicacionY();
            if(sucursal.getCodigo().equals(cod)){
                redP = redP.getScaledInstance(20, 20, 1);
                pointer.setIcon(new ImageIcon(redP));
            }else{
                blueP = blueP.getScaledInstance(20, 20, 1);
                pointer.setIcon(new ImageIcon(blueP));
            }
            pointer.setBounds(x, y, 20, 20);
            CustomMap.add(pointer);
        }
        CustomMap.repaint();
    }
}
