package controllers;

import core.Controller;
import models.EventListModel;
import views.EventListView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.Vector;

public class EventListController extends Controller
{
    //-----------------------------------------------------------------------
    //		Attributes
    //-----------------------------------------------------------------------
    private EventListView eventListView;
    private JTable table;

    //-----------------------------------------------------------------------
    //		Methods
    //-----------------------------------------------------------------------
    @Override
    public void run()
    {
        table = new JTable(getDataColumns(), getNameColumns());
        eventListView = new EventListView(this, table);
    }

    /**
     * Update the table with the last row in a {@link JTable} with the values informed.
     *
     */

    public void getEventList()
    {
        ((DefaultTableModel) table.getModel()).addRow(getDataColumns().lastElement());
    }

    //-----------------------------------------------------------------------
    //		Getters
    //-----------------------------------------------------------------------
    /**
     * Gets the {@link EventListView view associated with this controller}.
     *
     * @return View associated with this controller
     */
    public EventListView getView()
    {
        return eventListView;
    }

    /**
     * Returns the names of the columns of the events list.
     *
     * @return Table metadata in a list
     */
    public Vector<String> getNameColumns()
    {
        // Aquí puede ser lectura de base datos o leer un archivo texto y llevarlo a un modelo
        Vector<String> nameColumns = new Vector<String>();

        nameColumns.add("Date");
        nameColumns.add("Description");
        nameColumns.add("Frequency");
        nameColumns.add("E-mail");
        nameColumns.add("Alarm");

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
            EventListModel eventListModel = new EventListModel();
            eventListModel.attach(eventListView);
            dataColumns = eventListModel.getEvents();
        } catch (Exception ex) { }

        return dataColumns;
    }
}