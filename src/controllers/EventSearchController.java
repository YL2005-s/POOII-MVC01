package controllers;

import core.Controller;
import models.EventSearchModel;
import models.RemoveEventModel;
import models.SchedulerEvent;
import views.EventSearchView;

import javax.swing.*;

public class EventSearchController extends Controller
{
    //-----------------------------------------------------------------------
    //		Attributes
    //-----------------------------------------------------------------------
    private EventSearchView eventSearchView;

    //-----------------------------------------------------------------------
    //		Methods
    //-----------------------------------------------------------------------
    @Override
    public void run()
    {
        eventSearchView = new EventSearchView(this);
    }

    /**

     */
    public SchedulerEvent searchEvent(String description)
    {
        try {
            EventSearchModel eventSearchModel = new EventSearchModel();
            eventSearchModel.attach(eventSearchView);

            return eventSearchModel.findEventByDescription(description);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR", e.getMessage(), JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    //-----------------------------------------------------------------------
    //		Getters
    //-----------------------------------------------------------------------
    /**
     * Gets the {@link EventSearchView view associated with this controller}.
     *
     * @return View associated with this controller
     */
    public EventSearchView getView()
    {
        return eventSearchView;
    }
}
