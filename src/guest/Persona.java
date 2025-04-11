package guest;


import java.util.Date;

public abstract class Persona {
    protected String nombre;
    protected String celular;
    protected String genero;
    protected Date fechaNac;
    protected String direccion;

    public Persona(String nombre, String celular, String genero, Date fechaNac, String direccion) {
        this.nombre = nombre;
        this.celular = celular;
        this.genero = genero;
        this.fechaNac = fechaNac;
        this.direccion = direccion;
    }

}