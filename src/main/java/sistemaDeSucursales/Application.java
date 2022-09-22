package sistemaDeSucursales;

import sistemaDeSucursales.presentation.sucursales.Controller;
import sistemaDeSucursales.presentation.sucursales.Model;
import sistemaDeSucursales.presentation.sucursales.View;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class Application {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception ex) {};

        sistemaDeSucursales.presentation.empleados.Model empleadoModel= new sistemaDeSucursales.presentation.empleados.Model();
        sistemaDeSucursales.presentation.empleados.View empleadoView = new sistemaDeSucursales.presentation.empleados.View();
        empleadoController = new sistemaDeSucursales.presentation.empleados.Controller(empleadoView,empleadoModel);

        sistemaDeSucursales.presentation.main.Model mainModel= new sistemaDeSucursales.presentation.main.Model();
        sistemaDeSucursales.presentation.main.View mainView = new sistemaDeSucursales.presentation.main.View();
        mainController = new sistemaDeSucursales.presentation.main.Controller(mainView, mainModel);

        Model sucursalesModel= new Model();
        View sucursalesView = new View();
        sucursalesController = new Controller(sucursalesView,sucursalesModel);

        mainView.getPanel().add("Empleados",empleadoView.getPanel());
        mainView.getPanel().add("Sucursales",sucursalesView.getPanel());

        window = new JFrame();
        window.setSize(1150,700);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setTitle("Sistema de Empleados y Sucursales S.E.S");
        window.setVisible(true);
        mainController.show();
    }
    public static Controller sucursalesController;
    public static sistemaDeSucursales.presentation.empleados.Controller empleadoController;
    public static sistemaDeSucursales.presentation.main.Controller mainController;
    public static JFrame window;
}
