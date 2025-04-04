package views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

import controllers.HomeController;
import core.Model;
import core.View;

/**
 * View associated with {@link HomeController}. It will be responsible for program's
 * main screen view.
 *
 * Created by Kevin Durand
 */
@SuppressWarnings("serial")
public class HomeView extends JPanel implements View
{
    //-----------------------------------------------------------------------
    //		Attributes
    //-----------------------------------------------------------------------
    @SuppressWarnings("unused")
    private final HomeController homeController;
    private final JFrame mainFrame;
    private final static int MAIN_FRAME_WIDTH = 800;
    private final static int MAIN_FRAME_HEIGHT = 350;
    private final static int MAIN_FRAME_X = 100;
    private final static int MAIN_FRAME_Y = 100;


    //-----------------------------------------------------------------------
    //		Constructor
    //-----------------------------------------------------------------------
    /**
     * Initializes the view responsible for program's main screen.
     *
     * @param homeController Controller responsible for this view
     * @param mainFrame Main frame / window of the application
     */
    public HomeView(HomeController homeController, JFrame mainFrame)
    {
        this.homeController = homeController;
        this.mainFrame = mainFrame;

        make_mainFrame();
        make_tabs();
    }


    //-----------------------------------------------------------------------
    //		Methods
    //-----------------------------------------------------------------------
    @Override
    public void update(Model model, Object data)
    {
        //TODO
        //Por ejemplo si queremos cargar una lista de datos al iniciar el aplicativo y existe un error

    }

    /**
     * Configures the main frame of the application.
     */
    private void make_mainFrame()
    {
        mainFrame.setOpacity(1.0f);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setBounds(MAIN_FRAME_X, MAIN_FRAME_Y, MAIN_FRAME_WIDTH, MAIN_FRAME_HEIGHT);
        mainFrame.setMinimumSize(new Dimension(MAIN_FRAME_WIDTH, MAIN_FRAME_HEIGHT));


        setBorder(new EmptyBorder(5, 5, 5, 5));
        setLayout(new BorderLayout(0, 0));
    }

    /**
     * Creates tab navigation.
     */
    private void make_tabs()
    {
        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.addTab("New event", homeController.getNewEventView());
        tabbedPane.addTab("Event list", homeController.getEventListView());
        tabbedPane.addChangeListener(e -> {
            int tabbedIndex = tabbedPane.getSelectedIndex();
            String tabbedTitle = tabbedPane.getTitleAt(tabbedIndex);
            if ("Event list".equals(tabbedTitle)) {
                homeController.getEventListController().reloadTable();
            }
        });
        add(tabbedPane, BorderLayout.CENTER);
    }
}
