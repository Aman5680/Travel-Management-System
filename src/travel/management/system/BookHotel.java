package travel.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class BookHotel extends JFrame implements ActionListener {

    JLabel lblusername, labelusername, lbldays, lblac, lblfood, lblpackage, lblpersons, lblid, lblnumber, labelnumber,labelid, labelphone, lblphone, lbltotal, labelprice;
    Choice cPackage, cac, cfood;
    JTextField tfpersons, tfdays;
    String username;
    JButton checkprice, bookpackage, back;

    BookHotel(String username) {
        this.username = username;
        setBounds(150, 120, 1100, 600);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        JLabel text = new JLabel("BOOK HOTEL");
        text.setBounds(100, 10, 200, 30);
        text.setFont(new Font("Tahoma", Font.BOLD, 20));
        add(text);

        lblusername = new JLabel("Username");
        lblusername.setBounds(40, 70, 100, 20);
        lblusername.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblusername);

        labelusername = new JLabel();
        labelusername.setBounds(250, 70, 200, 20);
        labelusername.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(labelusername);

        lblpackage = new JLabel("Select Hotel");
        lblpackage.setBounds(40, 110, 150, 20);
        lblpackage.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblpackage);

        cPackage = new Choice();
        cPackage.setBounds(250, 110, 200, 20);
        add(cPackage);

//        Get data from Database

        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("SELECT * FROM hotel");
            while (rs.next()) {
                cPackage.add(rs.getString("name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        lblpersons = new JLabel("Total Persons");
        lblpersons.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblpersons.setBounds(40, 150, 150, 25);
        add(lblpersons);

        tfpersons = new JTextField("1");
        tfpersons.setBounds(250, 150, 200, 25);
        add(tfpersons);

        lbldays = new JLabel("No. of Days");
        lbldays.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lbldays.setBounds(40, 190, 150, 25);
        add(lbldays);

        tfdays = new JTextField("1");
        tfdays.setBounds(250, 190, 200, 25);
        add(tfdays);

        lblac = new JLabel("AC / Non-AC");
        lblac.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblac.setBounds(40, 230, 150, 25);
        add(lblac);

        cac = new Choice();
        cac.add("AC");
        cac.add("Non-AC");
        cac.setBounds(250, 230, 200, 20);
        add(cac);

        lblfood = new JLabel("Food Included");
        lblfood.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblfood.setBounds(40, 270, 150, 25);
        add(lblfood);

        cfood = new Choice();
        cfood.add("Yes");
        cfood.add("No");
        cfood.setBounds(250, 270, 200, 20);
        add(cfood);

        lblid = new JLabel("Id");
        lblid.setBounds(40, 310, 150, 20);
        lblid.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblid);

        labelid = new JLabel();
        labelid.setBounds(250, 310, 200, 25);
        add(labelid);

        lblnumber = new JLabel("Number");
        lblnumber.setBounds(40, 350, 150, 25);
        lblnumber.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblnumber);

        labelnumber = new JLabel();
        labelnumber.setBounds(250, 350, 150, 25);
        add(labelnumber);

        lblphone = new JLabel("Phone");
        lblphone.setBounds(40, 390, 200, 20);
        lblphone.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblphone);

        labelphone = new JLabel();
        labelphone.setBounds(250, 390, 200, 25);
        add(labelphone);

        lbltotal = new JLabel("Total Prize");
        lbltotal.setBounds(40, 430, 150, 25);
        lbltotal.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lbltotal);

        labelprice = new JLabel();
        labelprice.setBounds(250, 430, 150, 25);
        add(labelprice);

        try {
            Conn conn = new Conn();
            String query = "SELECT * FROM customer WHERE username = '"+username+"'";
            ResultSet rs = conn.s.executeQuery(query);
            while (rs.next()) {
                labelusername.setText(rs.getString("username"));
                labelid.setText(rs.getString("id"));
                labelnumber.setText(rs.getString("Number"));
                labelphone.setText(rs.getString("Phone"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        checkprice = new JButton("Check Price");
        checkprice.setBackground(Color.BLACK);
        checkprice.setForeground(Color.WHITE);
        checkprice.setBounds(60, 490, 120, 25);
        checkprice.addActionListener(this);
        add(checkprice);

        bookpackage = new JButton("Book Hotel");
        bookpackage.setBackground(Color.BLACK);
        bookpackage.setForeground(Color.WHITE);
        bookpackage.setBounds(200, 490, 120, 25);
        bookpackage.addActionListener(this);
        add(bookpackage);

        back = new JButton("Back");
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.setBounds(340, 490, 120, 25);
        back.addActionListener(this);
        add(back);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/book.jpg"));
        Image i2 = i1.getImage().getScaledInstance(600, 400, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l12 = new JLabel(i3);
        l12.setBounds(550, 50, 600, 400);
        add(l12);

        setVisible(true);
    }

    public void actionPerformed (ActionEvent ae) {
        if (ae.getSource() == checkprice) {
            try {
                Conn c = new Conn();
                ResultSet rs = c.s.executeQuery(" SELECT * FROM hotel WHERE name = '" + cPackage.getSelectedItem() + "'");
                while (rs.next()) {
                    int cost = Integer.parseInt(rs.getString("costperperson"));
                    int food = Integer.parseInt(rs.getString("foodincluded"));
                    int ac = Integer.parseInt(rs.getString("acroom"));

                    int persons = Integer.parseInt(tfpersons.getText());
                    int days = Integer.parseInt(tfdays.getText());

                    String acselected = cac.getSelectedItem();
                    String foodselected = cfood.getSelectedItem();

                    if (persons * days > 0) {
                        int total = 0;
                        total += acselected.equals("AC") ? ac : 0;
                        total += foodselected.equals("Yes") ? food : 0;
                        total += cost;
                        total = total * persons * days;
                        labelprice.setText("Rs " + total);
                    } else {
                        JOptionPane.showMessageDialog(null, "Please Enter A Valid Number!!!!");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == bookpackage) {
            try {
                Conn c = new Conn();
                c.s.executeUpdate("insert into bookhotel values ('"+ labelusername.getText() +"', '"+ cPackage.getSelectedItem() +"', '"+ tfpersons.getText() +"', '"+ tfdays.getText() +"', '"+ cac.getSelectedItem() +"', '"+ cfood.getSelectedItem() +"', '"+ labelid.getText() +"', '"+ labelnumber.getText() +"', '"+ labelphone.getText() +"', '"+ labelprice.getText() +"')");

                JOptionPane.showMessageDialog(null, "Hotel Booked Successfully");
                setVisible(false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new BookHotel("ss");
    }
}
