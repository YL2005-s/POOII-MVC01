package controllers;

import core.Controller;
import models.EventListModel;
import models.NewEventModel;
import models.RemoveEventModel;
import models.SchedulerEvent;
import views.EventListView;
import views.RemoveEventView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.Vector;

public class RemoveEventController extends Controller {
    //-----------------------------------------------------------------------
    //		Attributes
    //-----------------------------------------------------------------------
    private RemoveEventView removeEventView;
    private JTable table;

    //-----------------------------------------------------------------------
    //		Methods
    //-----------------------------------------------------------------------
    @Override
    public void run()
    {
        table = new JTable(getDataColumns(), getNameColumns());
        removeEventView = new RemoveEventView(this, table);
    }


    /**
     * Update the table in  {@link JTable} with the values informed.
     *
     */
    public void updateTable()
    {
        ((DefaultTableModel) table.getModel()).setRowCount(0);

        if (table.getModel().getColumnCount() == 0) {
            getNameColumns().forEach(column -> ((DefaultTableModel) table.getModel()).addColumn(column));
        }

        getDataColumns().forEach(row -> ((DefaultTableModel) table.getModel()).addRow(row));
    }

    /**

     */
    public void removeEvent(String event)
    {
        try {
            RemoveEventModel removeEventModel = new RemoveEventModel();
            removeEventModel.attach(removeEventView);
            removeEventModel.removeEvent(event);
            updateTable();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR", e.getMessage(), JOptionPane.ERROR_MESSAGE);
        }
    }


    //-----------------------------------------------------------------------
    //		Getters
    //-----------------------------------------------------------------------
    /**
     * Gets the {@link RemoveEventView view associated with this controller}.
     *
     * @return View associated with this controller
     */
    public RemoveEventView getView()
    {
        return removeEventView;
    }

    /**
     * Returns the names of the columns of the events list.
     *
     * @return Table metadata in a list
     */
    public Vector<String> getNameColumns()
    {
        Vector<String> nameColumns = new Vector<String>();

        nameColumns.add("Date");
        nameColumns.add("Description");
        nameColumns.add("E-mail");

        return nameColumns;
    }

    /**
     * Returns events list data.
     *
     * @return Table data in a list of lists (matrix)
     */
    public Vector<Vector<Object>> getDataColumns()
    {
        Vector<Vector<Object>> dataColumns = null;

        try {
            RemoveEventModel removeEventModel = new RemoveEventModel();
            removeEventModel.attach(removeEventView);
            dataColumns = removeEventModel.getEvents();
        } catch (Exception ex) { }

        return dataColumns;
    }
}
