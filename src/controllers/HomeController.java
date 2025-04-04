package controllers;

import core.Controller;
import views.EventListView;
import views.HomeView;
import views.NewEventView;


/**
 * Main controller. It will be responsible for program's main screen behavior.
 */
public class HomeController extends Controller {
    private final EventListController eventListController = new EventListController();
    private final NewEventController newEventController = new NewEventController();

    //-----------------------------------------------------------------------
    //		Methods
    //-----------------------------------------------------------------------
    @Override
    public void run() {
        // Initializes others controllers
        eventListController.run();
        newEventController.run();

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
    public EventListController getEventListController() {
        return eventListController;
    }

    public EventListView getEventListView() {
        return eventListController.getView();
    }

    public NewEventView getNewEventView() {
        return newEventController.getView();
    }


}
