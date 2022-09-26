package sistemaDeSucursales.presentation.sucursal;

import sistemaDeSucursales.logic.Sucursal;
import sistemaDeSucursales.logic.Sucursal;

public class Model extends java.util.Observable{
    Sucursal current;
    int modo;

    public Model() {
    }

    public int getModo() {
        return modo;
    }

    public void setModo(int modo) {
        this.modo = modo;
    }

    public Sucursal getCurrent() {
        return current;
    }

    public void setCurrent(Sucursal current) {
        this.current = current;
    }

    @Override
    public void addObserver(java.util.Observer o) {
        super.addObserver(o);
        this.commit();
    }

    public void commit(){
        setChanged();
        notifyObservers(null);
    }
}
