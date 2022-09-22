package sistemaDeSucursales.logic;

public class Sucursal {
    String codigo;
    String referencia;
    String direccion;
    double zonaje;
    int ubicacionX;
    int ubicacionY;

    public Sucursal(String codigo, String referencia, String direccion, double zonaje, int ubicacionX, int ubicacionY) {
        this.codigo = codigo;
        this.referencia = referencia;
        this.direccion = direccion;
        this.zonaje = zonaje;
        this.ubicacionX = ubicacionX;
        this.ubicacionY = ubicacionY;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public double getZonaje() {
        return zonaje;
    }

    public void setZonaje(double zonaje) {
        this.zonaje = zonaje;
    }

    public int getUbicacionX() {
        return ubicacionX;
    }

    public void setUbicacionX(int ubicacionX) {
        this.ubicacionX = ubicacionX;
    }

    public int getUbicacionY() {
        return ubicacionY;
    }

    public void setUbicacionY(int ubicacionY) {
        this.ubicacionY = ubicacionY;
    }
}
