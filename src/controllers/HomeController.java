package controllers;

import core.Controller;
import views.*;


/**
 * Main controller. It will be responsible for program's main screen behavior.
 */
public class HomeController extends Controller {
    private final EventListController eventListController = new EventListController();
    private final NewEventController newEventController = new NewEventController();
    private final RemoveEventController removeEventController = new RemoveEventController();
    private final GuestController guestController = new GuestController();
    private final EventSearchController eventSearchController = new EventSearchController();

    //-----------------------------------------------------------------------
    //		Methods
    //-----------------------------------------------------------------------
    @Override
    public void run() {
        // Initializes others controllers
        eventListController.run();
        newEventController.run();
        removeEventController.run();
        guestController.run();
        eventSearchController.run();

        // Initializes HomeView
        //-----------------------------------------------------------------------
        //		Attributes
        //-----------------------------------------------------------------------
        HomeView homeView = new HomeView(this, mainFrame);
        addView("HomeView", homeView);

        // Displays the program window
        mainFrame.setVisible(true);
    }

    //-----------------------------------------------------------------------
    //		Getters
    //-----------------------------------------------------------------------

    public NewEventView getNewEventView() {
        return newEventController.getView();
    }

    public EventListView getEventListView() {
        return eventListController.getView();
    }

    public RemoveEventView getRemoveEventView() {
        return removeEventController.getView();
    }

    public GuestView getGuestView() {
        return guestController.getView();
    }

    public EventSearchView getEventSearchView() {
        return eventSearchController.getView();
    }

    public void listEvents() {
        eventListController.getEventList();
    }

}
