package controllers;

import javax.swing.JOptionPane;

import core.Controller;
import models.NewEventModel;
import models.SchedulerEvent;
import views.EventListView;
import views.NewEventView;


/**
 * Responsible for {@link NewEventView} behavior.
 */
public class NewEventController extends Controller {
    //-----------------------------------------------------------------------
    //		Attributes
    //-----------------------------------------------------------------------
    private NewEventView newEventView;

    //-----------------------------------------------------------------------
    //		Methods
    //-----------------------------------------------------------------------
    @Override
    public void run()
    {
        newEventView = new NewEventView(this);
    }

    /**
     * Creates a new {@link SchedulerEvent} and puts it on {@link EventListView}.
     *
     * @param event Event to be added
     */
    public void addEvent(SchedulerEvent event)
    {
        Object[] metadata = new Object[5];
        metadata[0] = event.getDate();
        metadata[1] = event.getEventDesc();
        metadata[2] = event.getFrequency();
        metadata[3] = event.getFwdEmail();
        metadata[4] = event.getAlarm() ? "ON" : "OFF";

        try {
            NewEventModel newEventModel = new NewEventModel();
            newEventModel.attach(newEventView);
            newEventModel.saveEvent(event); //MODEL
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR", e.getMessage(), JOptionPane.ERROR_MESSAGE);
        }
    }


    //-----------------------------------------------------------------------
    //		Getters
    //-----------------------------------------------------------------------
    /**
     * Gets the {@link NewEventView view associated with this controller}.
     *
     * @return View associated with this controller
     */
    public NewEventView getView()
    {
        return newEventView;
    }
}
