package models;

import core.Model;
import core.View;
import guest.impl.Invitado;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GuestModel implements Model
{
    private static final String FILE = "invitados.txt";
    private final List<View> views = new ArrayList<>();
    private String notice;

    @Override
    public void attach(View view) {
        views.add(view);
    }

    @Override
    public void detach(View view) {
        views.remove(view);
    }

    @Override
    public void notifyViews() {
        for (View v : views) {
            v.update(this, notice);
        }
    }

    public void saveGuest(Invitado invitado) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE, true))) {
            writer.write(invitado.toString());
            writer.newLine();
            notice = "Invitado registrado con éxito.";
        } catch (IOException e) {
            notice = "Error al guardar el invitado.";
        }
        notifyViews();
    }
}
