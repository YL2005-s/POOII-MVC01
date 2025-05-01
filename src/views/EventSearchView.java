package views;

import controllers.EventSearchController;
import core.Model;
import core.View;
import guest.impl.Invitado;
import models.SchedulerEvent;

import javax.swing.*;
import java.awt.*;

public class EventSearchView extends JPanel implements View
{
    //-----------------------------------------------------------------------
    //		Attributes
    //-----------------------------------------------------------------------
    private final EventSearchController eventSearchController;

    private JTextField tf_eventDescp;
    private JTextArea ta_output;

    //-----------------------------------------------------------------------
    //		Constructor
    //-----------------------------------------------------------------------
    public EventSearchView(EventSearchController eventSearchController)
    {
        this.eventSearchController = eventSearchController;

        make_frame();
        make_field_searchEvent();
        make_btn_search();
        make_area_output();
    }

    //-----------------------------------------------------------------------
    //		Methods
    //-----------------------------------------------------------------------
    @Override
    public void update(Model model, Object data)
    {
        if (data != null) {
            String notice = (String) data;
            JOptionPane.showMessageDialog(this, notice);
        }
    }

    /**
     * Reset all fields.
     */
    private void cleanFields()
    {
        tf_eventDescp.setText("");			// Erases event description field
    }

    /**
     * Creates view's frame.
     */
    private void make_frame() { setLayout(null); }

    /**
     * Creates event search field.
     */
    private void make_field_searchEvent() {
        JLabel lbl_eventDesc = new JLabel("Event description:");
        lbl_eventDesc.setFont(new Font("Tahoma", Font.BOLD, 11));
        lbl_eventDesc.setBounds(180, 20, 120, 25);
        add(lbl_eventDesc);

        tf_eventDescp = new JTextField(20);
        tf_eventDescp.setBounds(300, 20, 180, 25);
        add(tf_eventDescp);
    }

    /**
     * Creates event search button.
     */
    private void make_btn_search() {
        JButton btn_search = new JButton("Search");
        btn_search.setBounds(500, 20, 100, 25);
        add(btn_search);

        btn_search.addActionListener(e -> {
            String desc = tf_eventDescp.getText();
            SchedulerEvent event = eventSearchController.searchEvent(desc);

            if (event != null) {
                ta_output.setText(event.getDescription());
            } else {
                ta_output.setText("No se encontró el evento.");
            }

            cleanFields();
        });
    }

    /**
     * Creates area output.
     */
    private void make_area_output() {
        ta_output = new JTextArea();
        ta_output.setBounds(140, 60, 500, 100);
        ta_output.setEditable(false);
        add(ta_output);
    }
}
