package controllers;

import core.Controller;
import guest.impl.Invitado;
import models.GuestModel;
import models.NewEventModel;
import models.SchedulerEvent;
import views.EventListView;
import views.GuestView;
import views.NewEventView;

import javax.swing.*;

public class GuestController extends Controller
{
    //-----------------------------------------------------------------------
    //		Attributes
    //-----------------------------------------------------------------------
    private GuestView guestView;

    //-----------------------------------------------------------------------
    //		Methods
    //-----------------------------------------------------------------------
    @Override
    public void run() {
        guestView = new GuestView(this);
    }

    /**
     *
     * @param invitado Event to be added
     */
    public void addGuest(Invitado invitado)
    {
        try {
            GuestModel guestModel = new GuestModel();
            guestModel.attach(guestView);
            guestModel.saveGuest(invitado);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR", e.getMessage(), JOptionPane.ERROR_MESSAGE);
        }
    }


    //-----------------------------------------------------------------------
    //		Getters
    //-----------------------------------------------------------------------
    /**
     * Gets the {@link GuestView view associated with this controller}.
     *
     * @return View associated with this controller
     */
    public GuestView getView()
    {
        return guestView;
    }
}
