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

        sistemaDeSucursales.presentation.empleados.Model empleadosModel= new sistemaDeSucursales.presentation.empleados.Model();
        sistemaDeSucursales.presentation.empleados.View empleadosView = new sistemaDeSucursales.presentation.empleados.View();
        empleadosController = new sistemaDeSucursales.presentation.empleados.Controller(empleadosView,empleadosModel);

        sistemaDeSucursales.presentation.empleado.Model empleadoModel= new sistemaDeSucursales.presentation.empleado.Model();
        sistemaDeSucursales.presentation.empleado.View empleadoView = new sistemaDeSucursales.presentation.empleado.View();
        empleadoController = new sistemaDeSucursales.presentation.empleado.Controller(empleadoView,empleadoModel);

        sistemaDeSucursales.presentation.main.Model mainModel= new sistemaDeSucursales.presentation.main.Model();
        sistemaDeSucursales.presentation.main.View mainView = new sistemaDeSucursales.presentation.main.View();
        mainController = new sistemaDeSucursales.presentation.main.Controller(mainView, mainModel);

        Model sucursalesModel= new Model();
        View sucursalesView = new View();
        sucursalesController = new Controller(sucursalesView,sucursalesModel);

        sistemaDeSucursales.presentation.sucursal.Model sucursalModel= new sistemaDeSucursales.presentation.sucursal.Model();
        sistemaDeSucursales.presentation.sucursal.View sucursalView = new sistemaDeSucursales.presentation.sucursal.View();
        sucursalController = new sistemaDeSucursales.presentation.sucursal.Controller(sucursalView,sucursalModel);

        sistemaDeSucursales.presentation.acercaDe.View acercaDeView = new sistemaDeSucursales.presentation.acercaDe.View();

        mainView.getPanel().add("Acerca de:",acercaDeView.getPanel());
        mainView.getPanel().add("Empleados",empleadosView.getPanel());
        mainView.getPanel().add("Sucursales",sucursalesView.getPanel());

        window = new JFrame();
        window.setSize(1000,560);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setTitle("Sistema de Empleados y Sucursales S.E.S");
        window.setVisible(true);
        mainController.show();
    }

    public static Controller sucursalesController;
    public static sistemaDeSucursales.presentation.sucursal.Controller sucursalController;
    public static sistemaDeSucursales.presentation.empleado.Controller empleadoController;
    public static sistemaDeSucursales.presentation.empleados.Controller empleadosController;
    public static sistemaDeSucursales.presentation.main.Controller mainController;
    public static JFrame window;

    public static  final int  MODO_AGREGAR=0;
    public static final int MODO_EDITAR=1;

    public static Border BORDER_ERROR = BorderFactory.createMatteBorder(0, 0, 2, 0, Color.RED);
}
