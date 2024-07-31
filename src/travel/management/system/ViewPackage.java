package travel.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class ViewPackage extends JFrame implements ActionListener {

    JLabel text, lblusername, labelusername, lblpackage, labelpackage, lblpersons,
            labelpersons, lblid, labelid, lblnumber, labelnumber, lblphone, labelphone,
            lblprice, labelprice;

    JButton back;

    ViewPackage(String username) {
        setBounds(200, 150, 900, 450);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        text = new JLabel("VIEW PACKAGE DETAILS");
        text.setFont(new Font("Tahoma", Font.BOLD, 20));
        text.setBounds(60, 0, 300, 30);
        add(text);

        lblusername = new JLabel("Username");
        lblusername.setBounds(30, 50, 150, 25);
        add(lblusername);

        labelusername = new JLabel();
        labelusername.setBounds(220, 50, 150, 25);
        add(labelusername);

        lblpackage = new JLabel("Package");
        lblpackage.setBounds(30, 90, 150, 25);
        add(lblpackage);

        labelpackage = new JLabel();
        labelpackage.setBounds(220, 90, 150, 25);
        add(labelpackage);

        lblpersons = new JLabel("Total Persons");
        lblpersons.setBounds(30, 130, 150, 25);
        add(lblpersons);

        labelpersons = new JLabel();
        labelpersons.setBounds(220, 130, 150, 25);
        add(labelpersons);

        lblid = new JLabel("Id");
        lblid.setBounds(30, 170, 150, 25);
        add(lblid);

        labelid = new JLabel();
        labelid.setBounds(220, 170, 150, 25);
        add(labelid);

        lblnumber = new JLabel("Number");
        lblnumber.setBounds(30, 210, 150, 25);
        add(lblnumber);

        labelnumber = new JLabel();
        labelnumber.setBounds(220, 210, 150, 25);
        add(labelnumber);

        lblphone = new JLabel("Phone");
        lblphone.setBounds(30, 250, 150, 25);
        add(lblphone);

        labelphone = new JLabel();
        labelphone.setBounds(220, 250, 150, 25);
        add(labelphone);

        lblprice = new JLabel("Price");
        lblprice.setBounds(30, 290, 150, 25);
        add(lblprice);

        labelprice = new JLabel();
        labelprice.setBounds(220, 290, 150, 25);
        add(labelprice);

        back = new JButton("Back");
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.setBounds(130, 360, 100, 25);
        back.addActionListener(this);
        add(back);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/bookedDetails.jpg"));
        Image i2 = i1.getImage().getScaledInstance(500, 400, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(450, 20, 500, 400);
        add(image);

        try {
            Conn conn = new Conn();
            String query = "SELECT * FROM bookpackage WHERE username = '"+username+"'";
            ResultSet rs = conn.s.executeQuery(query);
            while (rs.next()) {
                labelusername.setText(rs.getString("username"));
                labelid.setText(rs.getString("id"));
                labelnumber.setText(rs.getString("Number"));
                labelpackage.setText(rs.getString("package"));
                labelprice.setText(rs.getString("price"));
                labelphone.setText(rs.getString("Phone"));
                labelpersons.setText(rs.getString("persons"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        setVisible(false);
    }

    public static void main(String [] args) {
        new ViewPackage("ss");
    }
}
