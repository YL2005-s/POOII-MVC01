package models;

import core.Model;
import core.View;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class NewEventModel implements Model {
    //-----------------------------------------------------------------------
    //		Attributes
    //-----------------------------------------------------------------------
    private static final String DIRECTORY = ".";
    private static final String FILE = "events.txt"; // conexion //service API
    private final List<View> views = new ArrayList<>();
    private String notice;


    //-----------------------------------------------------------------------
    //		Methods
    //-----------------------------------------------------------------------
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

    /**
     * Saves a {@link SchedulerEvent} in disk in {@link #DIRECTORY}.{@link #FILE}.
     *
     * @param event {@link SchedulerEvent Event} to be saved
     * @throws Exception If it can't save the event
     */
    public void saveEvent(SchedulerEvent event) throws Exception {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(new File(DIRECTORY, FILE), true));
            writer.write(event.toString(), 0, event.toString().length());
            writer.newLine();
            writer.close();
        } catch (FileNotFoundException fnfe) {
            notice = "File not found";
            notifyViews();
        } catch (Exception ex) {
            notice = "Error while writing the file";
            notifyViews();
        }
    }
}