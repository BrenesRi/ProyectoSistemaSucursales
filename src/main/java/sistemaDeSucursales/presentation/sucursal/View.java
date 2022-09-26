package sistemaDeSucursales.presentation.sucursal;

import sistemaDeSucursales.Application;
import sistemaDeSucursales.logic.Empleado;
import sistemaDeSucursales.logic.Sucursal;

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

    private  int dirX;
    private  int dirY;
    private JPanel panel;
    private JLabel codigoLbl;
    private JTextField codigoFld;
    private JLabel referenciaLbl;
    private JTextField referenciaFld;
    private JLabel direccionLbl;
    private JTextField direccionFld;
    private JLabel zonajeLbl;
    private JTextField zonajeFld;
    private JButton guardarButt;
    private JButton cancelarButt;
    private JLabel CustMap;

    public View() {

        cargarImagen();

        guardarButt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validate()) {
                    Sucursal n = take();

                    try {
                        controller.guardar(n);
                        controller.hide();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(panel, ex.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        cancelarButt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.hide();
            }
        });
        CustMap.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
               dirX = e.getX();
               dirY = e.getY();

               JLabel pointer = new JLabel();
                Image blueP;
                try {
                   blueP = ImageIO.read(new File("resources/Sucursal.png"));
                } catch (IOException o) {
                    throw new RuntimeException(o);
                }
                blueP = blueP.getScaledInstance(20,20,1);
                pointer.setIcon(new ImageIcon(blueP));
                pointer.setBounds(dirX,dirY,20,20);
                CustMap.removeAll();
                CustMap.add(pointer);
                CustMap.repaint();
            }
        });
    }

    public JPanel getPanel() {
        return panel;
    }

    sistemaDeSucursales.presentation.sucursal.Controller controller;
    sistemaDeSucursales.presentation.sucursal.Model model;

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void setModel(Model model) {
        this.model = model;
        model.addObserver(this);
    }

    @Override
    public void update(Observable updatedModel, Object parametros) {
        Sucursal current = model.getCurrent();
        this.codigoFld.setEnabled(model.getModo() == Application.MODO_AGREGAR);
        this.codigoFld.setText(current.getCodigo());
        referenciaFld.setText(current.getReferencia());
        direccionFld.setText(current.getDireccion());
        zonajeFld.setText(String.valueOf(current.getZonaje()));
        this.panel.validate();
    }

    public Sucursal take() {
        Sucursal e = new Sucursal();
        e.setCodigo(codigoFld.getText());
        e.setReferencia(referenciaFld.getText());
        e.setDireccion(direccionFld.getText());
        e.setZonaje(Double.parseDouble(zonajeFld.getText()));
        e.setUbicacionX(dirX);
        e.setUbicacionY(dirY);
        return e;
    }

    private boolean validate() {
        boolean valid = true;
        if (codigoFld.getText().isEmpty()) {
            valid = false;
            codigoLbl.setBorder(Application.BORDER_ERROR);
            codigoLbl.setToolTipText("Codigo requerido");
        } else {
            codigoLbl.setBorder(null);
            codigoLbl.setToolTipText(null);
        }

        if (referenciaFld.getText().length() == 0) {
            valid = false;
            referenciaLbl.setBorder(Application.BORDER_ERROR);
            referenciaLbl.setToolTipText("Referencia requerida");
        } else {
            referenciaLbl.setBorder(null);
            referenciaLbl.setToolTipText(null);
        }

        if (direccionFld.getText().isEmpty()) {
            valid = false;
           direccionLbl.setBorder(Application.BORDER_ERROR);
           direccionLbl.setToolTipText("Dirección requerida");
        } else {
            direccionLbl.setBorder(null);
            direccionLbl.setToolTipText(null);
        }

        if (zonajeFld.getText().equals("0.0")) {
            valid = false;
           zonajeLbl.setBorder(Application.BORDER_ERROR);
           zonajeLbl.setToolTipText("Zonaje requerido");
        } else {
            zonajeLbl.setBorder(null);
            zonajeLbl.setToolTipText(null);
        }

        if (dirX==0) {
            valid = false;
            CustMap.setBorder(Application.BORDER_ERROR);
            CustMap.setToolTipText("Coordenadas requeridas");
        } else {
            CustMap.setBorder(null);
            CustMap.setToolTipText(null);
        }
        if (dirY==0) {
            valid = false;
            CustMap.setBorder(Application.BORDER_ERROR);
            CustMap.setToolTipText("Coordenadas requeridas");
        } else {
            CustMap.setBorder(null);
            CustMap.setToolTipText(null);
        }

        return valid;
    }

    private void cargarImagen(){
        Image mapa;
        try {
            mapa = ImageIO.read(new File("resources/mapa.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        mapa =  mapa.getScaledInstance(455,400,1);
        CustMap.setIcon(new ImageIcon(mapa));
        CustMap.setLayout(null);
    }
}

