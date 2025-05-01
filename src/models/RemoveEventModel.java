package models;

import core.Model;
import core.View;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class RemoveEventModel implements Model
{
    //-----------------------------------------------------------------------
    //		Attributes
    //-----------------------------------------------------------------------
    private static final String DIRECTORY = ".";
    private static final String FILE = "events.txt";
    private final List<View> views = new ArrayList<>();
    private String notice;

    //-----------------------------------------------------------------------
    //		Methods
    //-----------------------------------------------------------------------
    @Override
    public void attach(View view)
    {
        views.add(view);
    }

    @Override
    public void detach(View view)
    {
        views.remove(view);
    }

    @Override
    public void notifyViews()
    {
        for (View v : views) {
            v.update(this, notice);
        }
    }

    /**
     */
    public void removeEvent(String description) throws Exception {
        List<String> remainingEvents = new ArrayList<>();
        boolean eventFound = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(new File(DIRECTORY, FILE)))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(";");

                if (tokens.length >= 2 && tokens[1].equals(description)) {
                    eventFound = true;
                    continue;
                }
                remainingEvents.add(line);
            }
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(new File(DIRECTORY, FILE), false))) {
            for (String event : remainingEvents) {
                writer.write(event);
                writer.newLine();
            }
        }

        if (eventFound) {
            notice = "Event removed successfully.";
        } else {
            notice = "Event not found.";
        }
        notifyViews();
    }

    /**
     * Reads a {@link SchedulerEvent} saved in disk with name {@link #FILE}.
     * @return List of lists (matrix) of the events
     */
    public Vector<Vector<Object>> getEvents() {
        Vector<Vector<Object>> response = new Vector<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File(DIRECTORY, FILE)));
            String line = reader.readLine();

            while (line != null) {
                Vector<Object> eventInfo = new Vector<Object>();
                String[] tokens = line.split(";");

                eventInfo.add(tokens[0]);
                eventInfo.add(tokens[1]);
                eventInfo.add(tokens[3]);

                response.add(eventInfo);
                line = reader.readLine();
            }
            reader.close();
        } catch (FileNotFoundException fnfe) {
            notice = "File not found";
            notifyViews();
        } catch (Exception ex) {
            notice = "There was a problem reading the event file";
            notifyViews();
        }
        return response;
    }
}