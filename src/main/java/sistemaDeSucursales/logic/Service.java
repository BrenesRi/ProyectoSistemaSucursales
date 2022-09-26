package sistemaDeSucursales.logic;
import sistemaDeSucursales.Application;
import sistemaDeSucursales.data.Data;
import sistemaDeSucursales.data.XmlPersister;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
public class Service {
    private static Service theInstance;

    public static Service instance(){
        if (theInstance == null){
            theInstance = new Service();
        }
        return theInstance;
    }

    private Data data;
    private Service(){
        //data = new Data();
        try{
            data= XmlPersister.instance().load();
        }
        catch(Exception e){
            data =  new Data();
        }
    }

    public List<Sucursal> sucursalSearch(String filtro){
        return data.getSucursales().stream()
                .filter(e->e.getReferencia().contains(filtro))
                .sorted(Comparator.comparing(e -> e.getCodigo()))
                .collect(Collectors.toList());
    }
    public List<Empleado> empleadoSearch(String filtro){
        return data.getEmpleados().stream()
                .filter(e->e.getNombre().contains(filtro))
                .sorted(Comparator.comparing(e -> e.getCedula()))
                .collect(Collectors.toList());
    }
    public Sucursal sucursalGet(String codigo) throws Exception{
        Sucursal result = data.getSucursales().stream().filter(e->e.getCodigo().equals(codigo)).findFirst().orElse(null);
        if (result!=null) return result;
        else throw new Exception("Sucursal no existe");
    }
    public Sucursal sucursalGet2(String ref) {
        return data.getSucursales().stream().filter(e->e.getReferencia().equals(ref)).findFirst().orElse(null);
    }
    public Empleado empleadoGet(String cedula) throws Exception{
        Empleado result = data.getEmpleados().stream().filter(e->e.getCedula().equals(cedula)).findFirst().orElse(null);
        if (result!=null) return result;
        else throw new Exception("Empleado no existe");
    }

    public void sucursalAdd(Sucursal sucursal) throws Exception{
        Sucursal result = data.getSucursales().stream().filter(e->e.getCodigo().equals(sucursal.getCodigo())).findFirst().orElse(null);
        if (result==null) data.getSucursales().add(sucursal);
        else throw new Exception("Sucursal ya existe");
    }

    public void empleadoAdd(Empleado empleado) throws Exception{
        Empleado result = data.getEmpleados().stream().filter(e->e.getCedula().equals(empleado.getCedula())).findFirst().orElse(null);
        if (result==null) data.getEmpleados().add(empleado);
        else throw new Exception("Empleado ya existe");
    }
    public void empleadoDelete(Empleado empleado) throws Exception{
        Empleado result = data.getEmpleados().stream().filter(e->e.getCedula().equals(empleado.getCedula())).findFirst().orElse(null);
        if (result!=null) data.getEmpleados().remove(empleado);
        else throw new Exception("Empleado no existe");
    }
    public void sucursalDelete(Sucursal sucursal) throws Exception{
        Sucursal result = data.getSucursales().stream().filter(e->e.getCodigo().equals(sucursal.getCodigo())).findFirst().orElse(null);
        if (result!=null) data.getSucursales().remove(sucursal);
        else throw new Exception("Sucursal no existe");
    }
    public void empleadoUpdate(Empleado empleado) throws Exception{
        Empleado result;
        try{
            result = this.empleadoGet(empleado.cedula);
            data.getEmpleados().remove(result);
            data.getEmpleados().add(empleado);
        }catch (Exception e) {
            throw new Exception("Empleado no existe");
        }
    }
    public void sucursalUpdate(Sucursal sucursal) throws Exception{
        Sucursal result;
        try{
            result = this.sucursalGet(sucursal.codigo);
            data.getSucursales().remove(result);
            data.getSucursales().add(sucursal);
        }catch (Exception e) {
            throw new Exception("Sucursal no existe");
        }
    }

    public void store(){
        try {
            XmlPersister.instance().store(data);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
