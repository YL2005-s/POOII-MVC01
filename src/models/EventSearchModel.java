package models;

import core.Model;
import core.View;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EventSearchModel implements Model {
    private static final String FILE = "events.txt";
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
        for (View v : views) v.update(this, notice);
    }

    public SchedulerEvent findEventByDescription(String description) {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(";");
                if (tokens.length >= 5 && tokens[1].equalsIgnoreCase(description)) {
                    SchedulerEvent event = new SchedulerEvent();
                    event.setDate(SchedulerUtil.getDateFromString(tokens[0]));
                    event.setEventDesc(tokens[1]);
                    event.setFrequency(Frequency.valueOf(tokens[2]));
                    event.setFwdEmail(tokens[3]);
                    event.setAlarm(tokens[4].equals("1"));

                    return event;
                }
            }

            notice = "Evento no encontrado.";
            notifyViews();
        } catch (IOException e) {
            notice = "Error al leer el archivo.";
            notifyViews();
        } catch (Exception e) {
            notice = "Error al procesar los datos del evento.";
            notifyViews();
        }

        return null;
    }
}