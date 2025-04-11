package guest.impl;

import guest.Persona;

import java.util.Date;

public class Invitado extends Persona {
    protected boolean aceptarTerminano;

    public Invitado(String nombre, String celular, String genero, Date fechaNacimiento, String direccion, boolean aceptarTerminano) {
        super(nombre, celular, genero, fechaNacimiento, direccion);
        this.aceptarTerminano = aceptarTerminano;
    }


    public void setAceptarTerminano(boolean aceptarTerminano) {
        this.aceptarTerminano = aceptarTerminano;
    }

    public boolean isAceptarTerminano() {
        return aceptarTerminano;
    }

    @Override
    public String toString() {
        return nombre + ";" + celular + ";" + genero + ";" + fechaNac + ";" + direccion;
    }
}
