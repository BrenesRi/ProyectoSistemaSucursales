package sistemaDeSucursales.data;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import sistemaDeSucursales.logic.Empleado;
import sistemaDeSucursales.logic.Sucursal;
import java.util.ArrayList;
import java.util.List;
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Data {

    private List<Sucursal> sucursales;
    private List<Empleado> empleados;
    public Data() {
        //Datos quemados para probar funcionalidad
       sucursales = new ArrayList<>();

        sucursales.add(new Sucursal("001","Limón","Limón 2000, Limón Centro",4.0,12,13));
        sucursales.add(new Sucursal("002","San José","Barrio Corazón de Jesús, San José Centro",8.0,40,90));
        sucursales.add(new Sucursal("003","Heredia","Belén, Contiguo a el MxM",1.0,18,63));
        sucursales.add(new Sucursal());
        empleados = new ArrayList<>();

        empleados.add(new Empleado("111","Juan","11111111",
                40000,(new Sucursal("001","Limón","Limón 2000, Limón Centro",4.0,12,13))));
    }
    public List<Sucursal> getSucursales(){return sucursales;}

    public List<Empleado> getEmpleados(){return  empleados;}

    public void setSucursales(List<Sucursal> sucursales){this.sucursales = sucursales;}

    public void setEmpleados(List<Empleado> empleados){this.empleados = empleados;}
}
