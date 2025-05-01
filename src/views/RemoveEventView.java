package views;

import controllers.RemoveEventController;
import core.Model;
import core.View;

import javax.swing.*;
import java.awt.*;

public class RemoveEventView extends JPanel implements View
{
    //-----------------------------------------------------------------------
    //		Attributes
    //-----------------------------------------------------------------------
    private final RemoveEventController removeEventController;
    private final JTable table;

    private JTextField tf_eventDesc;

    //-----------------------------------------------------------------------
    //		Constructor
    //-----------------------------------------------------------------------
    /**
     * @param removeEventController Controller of this view
     */
    public RemoveEventView(RemoveEventController removeEventController, JTable table)
    {
        this.removeEventController = removeEventController;
        this.table = table;

        make_frame();
        make_field_deleteEvent();
        make_btn_delete();
    }

    //-----------------------------------------------------------------------
    //		Methods
    //-----------------------------------------------------------------------
    @Override
    public void update(Model model, Object data)
    {
        if (data != null) {
            String notice = (String) data;
            JOptionPane.showMessageDialog(null, notice);
        }
    }

    /**
     * Reset all fields.
     */
    private void cleanFields()
    {
        tf_eventDesc.setText("");			// Erases event description field
    }

    /**
     * Creates view's frame.
     */
    private void make_frame()
    {
        setLayout(null);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(150, 15, 500, 225);
        add(scrollPane);
    }

    /**
     * Creates event delete field.
     */
    private void make_field_deleteEvent()
    {
        JLabel lbl_eventDesc = new JLabel("Event description:");
        lbl_eventDesc.setFont(new Font("Tahoma", Font.BOLD, 11));
        lbl_eventDesc.setBounds(230, 250, 120, 25);
        add(lbl_eventDesc);

        tf_eventDesc = new JTextField(20);
        tf_eventDesc.setBounds(350, 250, 180, 25);
        add(tf_eventDesc);
    }

    /**
     * Creates event delete button.
     */
    private void make_btn_delete()
    {
        JButton btn_delete = new JButton("Delete");
        btn_delete.setBounds(330, 290, 100, 25);
        add(btn_delete);

        btn_delete.addActionListener(arg0 -> {
            removeEventController.removeEvent(tf_eventDesc.getText().trim());
            cleanFields();
        });
    }

}
