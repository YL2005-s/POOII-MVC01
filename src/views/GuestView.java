package views;

import controllers.GuestController;
import core.Model;
import core.View;
import guest.impl.Invitado;
import models.SchedulerEvent;

import javax.swing.*;
import java.awt.*;
import java.util.Calendar;
import java.util.Date;

public class GuestView extends JPanel implements View
{
    //-----------------------------------------------------------------------
    //		Attributes
    //-----------------------------------------------------------------------
    private final GuestController guestController;

    private JTextField tf_guestName;
    private JTextField tf_guestCellphone;
    private JRadioButton rbtn_male;
    private JRadioButton rbtn_female;
    private JComboBox<Integer> cb_day;
    private JComboBox<String> cb_month;
    private JComboBox<Integer> cb_year;
    private JTextField tf_guestAddress;
    private JCheckBox cbx_terms;

    //-----------------------------------------------------------------------
    //		Constructor
    //-----------------------------------------------------------------------
    /**
     * @param guestController Controller of this view
     */
    public GuestView(GuestController guestController)
    {
        this.guestController = guestController;

        make_frame();
        make_field_guestName();
        make_field_guestCellphone();
        make_field_gender();
        make_field_birthDate();
        make_field_guestAddress();
        make_field_terms();
        make_btn_register();
        make_btn_reset();
    }


    //-----------------------------------------------------------------------
    //		Methods
    //-----------------------------------------------------------------------
    @Override
    public void update(Model model, Object data) {
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
        tf_guestName.setText("");			// Erases guest name field
        tf_guestCellphone.setText("");	    // Erases guest cellphone field
        cbx_terms.setSelected(false);		// Unchecks check box
        tf_guestAddress.setText("");		// Erases guest address field
        rbtn_male.setSelected(false);
        rbtn_female.setSelected(false);
    }

    /**
     * Creates view's frame.
     */
    private void make_frame() { setLayout(null); }

    /**
     * Creates guest name field.
     */
    private void make_field_guestName()
    {
        // Makes label
        JLabel lbl_guestName = new JLabel("Ingrese Nombre:");
        lbl_guestName.setFont(new Font("Tahoma", Font.BOLD, 11));
        lbl_guestName.setBounds(29, 29, 134, 14);
        add(lbl_guestName);

        // Makes text field
        tf_guestName = new JTextField();
        tf_guestName.setBounds(169, 26, 196, 20);
        add(tf_guestName);
        tf_guestName.setColumns(10);
    }

    /**
     * Creates guest cellphone field.
     */
    private void make_field_guestCellphone()
    {
        // Makes label
        JLabel lbl_guestCellphone = new JLabel("Ingrese número celular:");
        lbl_guestCellphone.setFont(new Font("Tahoma", Font.BOLD, 11));
        lbl_guestCellphone.setBounds(29, 71, 196, 14);
        add(lbl_guestCellphone);

        // Makes text field
        tf_guestCellphone = new JTextField();
        tf_guestCellphone.setBounds(169, 68, 196, 20);
        add(tf_guestCellphone);
        tf_guestCellphone.setColumns(10);
    }

    /**
     * Creates gender field.
     */
    private void make_field_gender()
    {
        final ButtonGroup btng_periodicity = new ButtonGroup();

        // Frequency label
        JLabel lbl_gender = new JLabel("Género:");
        lbl_gender.setFont(new Font("Tahoma", Font.BOLD, 11));
        lbl_gender.setBounds(29, 113, 78, 14);
        add(lbl_gender);

        // Male option
        rbtn_male = new JRadioButton("Masculino");
        btng_periodicity.add(rbtn_male);
        rbtn_male.setBounds(169, 109, 86, 23);
        add(rbtn_male);

        // Female option
        rbtn_female = new JRadioButton("Femenino");
        btng_periodicity.add(rbtn_female);
        rbtn_female.setBounds(253, 109, 86, 23);
        add(rbtn_female);
    }

    /**
     * Creates birth date field.
     */
    private void make_field_birthDate() {
        // Label
        JLabel lbl_birthDate = new JLabel("Fecha de Nacimiento:");
        lbl_birthDate.setFont(new Font("Tahoma", Font.BOLD, 11));
        lbl_birthDate.setBounds(29, 151, 134, 14);
        add(lbl_birthDate);

        // Day (1 to 31)
        Integer[] days = new Integer[31];
        for (int i = 0; i < 31; i++) {
            days[i] = i + 1;
        }
        cb_day = new JComboBox<>(days);
        cb_day.setBounds(169, 148, 50, 20);
        add(cb_day);

        // Months
        String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun",
                "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        cb_month = new JComboBox<>(months);
        cb_month.setBounds(225, 148, 60, 20);
        add(cb_month);

        // Years
        Integer[] years = new Integer[76];
        for (int i = 0; i < 76; i++) {
            years[i] = 1950 + i;
        }
        cb_year = new JComboBox<>(years);
        cb_year.setBounds(290, 148, 75, 20);
        add(cb_year);
    }


    /**
     * Creates guest address field.
     */
    private void make_field_guestAddress()
    {
        // Makes label
        JLabel lbl_guestAddress = new JLabel("Dirección:");
        lbl_guestAddress.setFont(new Font("Tahoma", Font.BOLD, 11));
        lbl_guestAddress.setBounds(29, 197, 196, 14);
        add(lbl_guestAddress);

        // Makes text field
        tf_guestAddress = new JTextField();
        tf_guestAddress.setBounds(169, 194, 196, 20);
        add(tf_guestAddress);
        tf_guestAddress.setColumns(10);
    }

    /**
     * Creates terms check box.
     */
    private void make_field_terms()
    {
        // Makes check box
        cbx_terms = new JCheckBox("Acepta Términos y Condiciones");
        cbx_terms.setFont(new Font("Tahoma", Font.BOLD, 10));
        cbx_terms.setBounds(25, 230, 200, 23);
        add(cbx_terms);
    }

    /**
     * Creates register button.
     */
    private void make_btn_register()
    {
        // Makes button
        JButton btn_register = new JButton("Registrar");
        btn_register.setBounds(127, 272, 89, 23);
        add(btn_register);

        // Add action listener
        btn_register.addActionListener(arg0 -> {
            String name = tf_guestName.getText();
            String cellphone = tf_guestCellphone.getText();
            String address = tf_guestAddress.getText();
            boolean terms = cbx_terms.isSelected();

            Object dayObject = cb_day.getSelectedItem();
            Object yearObject = cb_year.getSelectedItem();
            int month = cb_month.getSelectedIndex();

            if (dayObject == null || yearObject == null) {
                JOptionPane.showMessageDialog(this, "Por favor, selecciona una fecha válida.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int day = (Integer) dayObject;
            int year = (Integer) yearObject;

            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month);
            calendar.set(Calendar.DAY_OF_MONTH, day);
            Date fechaNacimiento = calendar.getTime();

            String genero = rbtn_male.isSelected() ? "Masculino" : "Femenino";

            Invitado invitado = new Invitado(name, cellphone, genero, fechaNacimiento, address, terms);
            if (!invitado.isAceptarTerminano()) {
                JOptionPane.showMessageDialog(this, "Por favor, acepte los terminos y condiciones.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            guestController.addGuest(invitado);
            cleanFields();
        });
    }

    /**
     * Creates reset button.
     */
    private void make_btn_reset()
    {
        // Makes button
        JButton btn_reset = new JButton("Resetear");
        btn_reset.setBounds(253, 272, 89, 23);
        add(btn_reset);

        // Add action listener
        btn_reset.addActionListener(arg0 -> cleanFields());
    }
}
