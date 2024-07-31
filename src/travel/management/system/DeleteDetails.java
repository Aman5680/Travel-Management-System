package travel.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class DeleteDetails extends JFrame implements ActionListener {

    JLabel lblusername, labelusername, lblid, labelid, lblnumber, labelnumber,lblgender, labelgender, lblname, labelname, lblcountry, labelcountry, lblphone, labelphone, lblemail, labelemail, lbladdress, labeladdress;
    JButton back, delete;
    String username;

    DeleteDetails(String username) {
        this.username = username;
        setBounds(150, 120, 870, 625);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        lblusername = new JLabel("Username");
        lblusername.setBounds(30, 50, 150, 25);
        add(lblusername);

        labelusername = new JLabel();
        labelusername.setBounds(220, 50, 150, 25);
        add(labelusername);

        lblid = new JLabel("Id");
        lblid.setBounds(30, 110, 150, 25);
        add(lblid);

        labelid = new JLabel();
        labelid.setBounds(220, 110, 150, 25);
        add(labelid);

        lblnumber = new JLabel("Number");
        lblnumber.setBounds(30, 170, 150, 25);
        add(lblnumber);

        labelnumber = new JLabel();
        labelnumber.setBounds(220, 170, 150, 25);
        add(labelnumber);

        lblname = new JLabel("Name");
        lblname.setBounds(30, 230, 150, 25);
        add(lblname);

        labelname = new JLabel();
        labelname.setBounds(220, 230, 150, 25);
        add(labelname);

        lblgender = new JLabel("Gender");
        lblgender.setBounds(30, 290, 150, 25);
        add(lblgender);

        labelgender = new JLabel();
        labelgender.setBounds(220, 290, 150, 25);
        add(labelgender);

        lblcountry = new JLabel("Country");
        lblcountry.setBounds(500, 50, 150, 25);
        add(lblcountry);

        labelcountry = new JLabel();
        labelcountry.setBounds(690, 50, 150, 25);
        add(labelcountry);

        lbladdress = new JLabel("Address");
        lbladdress.setBounds(500, 110, 150, 25);
        add(lbladdress);

        labeladdress = new JLabel();
        labeladdress.setBounds(690, 110, 150, 25);
        add(labeladdress);

        lblphone = new JLabel("Phone");
        lblphone.setBounds(500, 170, 150, 25);
        add(lblphone);

        labelphone = new JLabel();
        labelphone.setBounds(690, 170, 150, 25);
        add(labelphone);

        lblemail = new JLabel("Email");
        lblemail.setBounds(500, 230, 150, 25);
        add(lblemail);

        labelemail = new JLabel();
        labelemail.setBounds(690, 230, 150, 25);
        add(labelemail);

        back = new JButton("Back");
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.setBounds(250, 350, 100, 25);
        back.addActionListener(this);
        add(back);

        delete = new JButton("Delete");
        delete.setBackground(Color.BLACK);
        delete.setForeground(Color.WHITE);
        delete.setBounds(450, 350, 100, 25);
        delete.addActionListener(this);
        add(delete);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/viewall.jpg"));
        Image i2 = i1.getImage().getScaledInstance(600, 200, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(20, 400, 600, 200);
        add(image);

        ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("icons/viewall.jpg"));
        Image i5 = i4.getImage().getScaledInstance(600, 200, Image.SCALE_DEFAULT);
        ImageIcon i6 = new ImageIcon(i5);
        JLabel image1 = new JLabel(i6);
        image1.setBounds(600, 400, 600, 200);
        add(image1);

        try {
            Conn conn = new Conn();
            String query = "SELECT * FROM customer WHERE username = '"+username+"'";
            ResultSet rs = conn.s.executeQuery(query);
            while (rs.next()) {
                labelusername.setText(rs.getString("username"));
                labelid.setText(rs.getString("id"));
                labelnumber.setText(rs.getString("Number"));
                labelname.setText(rs.getString("Name"));
                labelgender.setText(rs.getString("Gender"));
                labelcountry.setText(rs.getString("Country"));
                labeladdress.setText(rs.getString("Address"));
                labelphone.setText(rs.getString("Phone"));
                labelemail.setText(rs.getString("email"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == delete) {
            try {
                Conn c = new Conn();
                c.s.executeUpdate("DELETE FROM account WHERE username = '"+ username +"'");
                c.s.executeUpdate("DELETE FROM customer WHERE username = '"+ username +"'");
                c.s.executeUpdate("DELETE FROM bookpackage WHERE username = '"+ username +"'");
                c.s.executeUpdate("DELETE FROM bookhotel WHERE username = '"+ username +"'");

                JOptionPane.showMessageDialog(null, "Data Deleted Successfully. . . .");

                System.exit(0);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            setVisible(false);
        }
    }

    public static void main(String [] args) {
        new DeleteDetails("username");
    }
}