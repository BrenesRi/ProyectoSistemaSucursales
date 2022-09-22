package sistemaDeSucursales.presentation.sucursales;

import sistemaDeSucursales.Application;
import sistemaDeSucursales.logic.Sucursal;
import sistemaDeSucursales.logic.Service;

import java.util.List;
public class Controller {
    View view;
    Model model;

    public Controller(View view, Model model) {
    model.setSucursales(Service.instance().sucursalSearch(""));
    this.view = view;
    this.model = model;
    view.setController(this);
    view.setModel(model);
}
    public void buscar(String filtro){
        List<Sucursal> rows = Service.instance().sucursalSearch(filtro);
        model.setSucursales(rows);
        model.commit();
    }
   // public void preAgregar(){
      //  Application.empleadoController.preAgregar();
   // }
   public void editar(int row){
       String cedula = model.getSucursales().get(row).getCodigo();
       Sucursal e=null;
       try {
           e= Service.instance().sucursalGet(cedula);
         //  Application.sucursalController.editar(e);
       } catch (Exception ex) {}
   }
    public void show(){
        Application.window.setContentPane(view.getPanel());
    }

}
