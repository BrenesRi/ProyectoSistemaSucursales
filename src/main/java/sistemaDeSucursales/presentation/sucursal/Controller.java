package sistemaDeSucursales.presentation.sucursal;

import javax.swing.*;
import java.awt.*;
import sistemaDeSucursales.Application;
import sistemaDeSucursales.logic.Empleado;
import sistemaDeSucursales.logic.Service;
import sistemaDeSucursales.logic.Sucursal;
import sistemaDeSucursales.presentation.sucursal.Model;
import sistemaDeSucursales.presentation.sucursal.View;

public class Controller {
    View view;
    sistemaDeSucursales.presentation.sucursal.Model model;

    public Controller(View view, Model model) {
        model.setCurrent(new Sucursal());

        this.view = view;
        this.model = model;
        view.setController(this);
        view.setModel(model);
    }

    public void preAgregar(){
        model.setModo(Application.MODO_AGREGAR);
        model.setCurrent(new Sucursal());
        model.commit();
        this.show();
    }

    JDialog dialog;
    public void show(){
        dialog = new JDialog(Application.window,"Sucursal", true);
        dialog.setSize(800,620);
        dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        dialog.setContentPane(view.getPanel());
        Point location = Application.window.getLocation();
        dialog.setLocation( location.x+400,location.y+100);
        dialog.setVisible(true);
    }

    public void hide(){
        dialog.dispose();
    }

    public void show1(){
        Application.window.setContentPane(view.getPanel());
        Application.window.revalidate();
    }
    public void hide1(){
        Application.mainController.show();
    }

    public void guardar(Sucursal e) throws Exception {
        switch (model.getModo()) {
            case Application.MODO_AGREGAR:
                Service.instance().sucursalAdd(e);
                model.setCurrent(new Sucursal());
                Application.sucursalesController.buscar("");
                break;
            case Application.MODO_EDITAR:
                Service.instance().sucursalUpdate(e);
                model.setCurrent(e);
                break;
        }
        Application.empleadosController.buscar("");
        model.commit();
        Application.empleadosController.buscar("");
    }

    public void editar(Sucursal e){
        model.setModo(Application.MODO_EDITAR);
        model.setCurrent(e);
        model.commit();
        this.show();
    }
}
