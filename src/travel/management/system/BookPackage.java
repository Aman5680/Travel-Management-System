package travel.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class BookPackage extends JFrame implements ActionListener {

    JLabel lblusername, labelusername, lblpackage, lblpersons, lblid, lblnumber, labelnumber,labelid, labelphone, lblphone, lbltotal, labelprice;
    Choice cPackage;
    JTextField tfpersons;
    String username;
    JButton checkprice, bookpackage, back;

    BookPackage(String username) {
        this.username = username;
        setBounds(150, 120, 1100, 500);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        JLabel text = new JLabel("BOOK PACKAGE");
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

        lblpackage = new JLabel("Select Package");
        lblpackage.setBounds(40, 110, 150, 20);
        lblpackage.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblpackage);

        cPackage = new Choice();
        cPackage.add("Gold Package");
        cPackage.add("Silver Package");
        cPackage.add("Bronze Package");
        cPackage.setBounds(250, 110, 200, 20);
        add(cPackage);

        lblpersons = new JLabel("Total Persons");
        lblpersons.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblpersons.setBounds(40, 150, 150, 25);
        add(lblpersons);

        tfpersons = new JTextField("1");
        tfpersons.setBounds(250, 150, 200, 25);
        add(tfpersons);

        lblid = new JLabel("Id");
        lblid.setBounds(40, 190, 150, 20);
        lblid.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblid);

        labelid = new JLabel();
        labelid.setBounds(250, 190, 200, 25);
        add(labelid);

        lblnumber = new JLabel("Number");
        lblnumber.setBounds(40, 230, 150, 25);
        lblnumber.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblnumber);

        labelnumber = new JLabel();
        labelnumber.setBounds(250, 230, 150, 25);
        add(labelnumber);

        lblphone = new JLabel("Phone");
        lblphone.setBounds(40, 270, 200, 20);
        lblphone.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblphone);

        labelphone = new JLabel();
        labelphone.setBounds(250, 270, 200, 25);
        add(labelphone);

        lbltotal = new JLabel("Total Prize");
        lbltotal.setBounds(40, 310, 150, 25);
        lbltotal.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lbltotal);

        labelprice = new JLabel();
        labelprice.setBounds(250, 310, 150, 25);
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
        checkprice.setBounds(60, 380, 120, 25);
        checkprice.addActionListener(this);
        add(checkprice);

        bookpackage = new JButton("Book Package");
        bookpackage.setBackground(Color.BLACK);
        bookpackage.setForeground(Color.WHITE);
        bookpackage.setBounds(200, 380, 120, 25);
        bookpackage.addActionListener(this);
        add(bookpackage);

        back = new JButton("Back");
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.setBounds(340, 380, 120, 25);
        back.addActionListener(this);
        add(back);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/bookpackage.jpg"));
        Image i2 = i1.getImage().getScaledInstance(500, 300, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l12 = new JLabel(i3);
        l12.setBounds(550, 50, 500, 300);
        add(l12);

        setVisible(true);
    }

    public void actionPerformed (ActionEvent ae) {
        if (ae.getSource() == checkprice) {
            String pack = cPackage.getSelectedItem();
            int cost = 0;
            if (pack.equals("Gold Package")) {
                cost += 12000;
            } else if (pack.equals("Silver Package")) {
                cost += 25000;
            } else {
                cost += 35000;
            }

            int persons = Integer.parseInt(tfpersons.getText());
            cost *= persons;
            labelprice.setText("Rs " + cost);
        } else if (ae.getSource() == bookpackage) {
            try {
                Conn c = new Conn();
                c.s.executeUpdate("insert into bookpackage values ('"+ labelusername.getText() +"', '"+ cPackage.getSelectedItem() +"', '"+ tfpersons.getText() +"', '"+ labelid.getText() +"', '"+ labelnumber.getText() +"', '"+ labelphone.getText() +"', '"+ labelprice.getText() +"')");

                JOptionPane.showMessageDialog(null, "Package Booked Successfully");
                setVisible(false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new BookPackage("ss");
    }
}
