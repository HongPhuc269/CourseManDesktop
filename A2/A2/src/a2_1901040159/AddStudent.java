package a2_1901040159;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddStudent extends WindowAdapter implements ActionListener {
    private JFrame gui;
    private boolean check = true;
    private int comp = -1;

    private JTextField txtName, txtDob, txtAddress, txtEmail;


    public AddStudent() {
        gui = new JFrame("Add Student");
        gui.addWindowListener(this);
        // center panel
        JPanel pnlMiddle = new JPanel(new GridLayout(4, 4, 5, 10));
        pnlMiddle.setBorder(BorderFactory.createEmptyBorder(15, 20, 10, 20));
        pnlMiddle.add(new JLabel("Name"));
        txtName = new JTextField(15);
        pnlMiddle.add(txtName);
        pnlMiddle.add(new JLabel("Dob"));
        txtDob = new JTextField(15);
        pnlMiddle.add(txtDob);
        pnlMiddle.add(new JLabel("Address"));
        txtAddress = new JTextField(15);
        pnlMiddle.add(txtAddress);
        pnlMiddle.add(new JLabel("Email"));
        txtEmail = new JTextField(15);
        pnlMiddle.add(txtEmail);

        gui.add(pnlMiddle);

        // bottom
        JPanel pnlBottom = new JPanel();

        JButton btnAddContact = new JButton("Add");
        btnAddContact.addActionListener(this);
        pnlBottom.add(btnAddContact);

        pnlBottom.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

        gui.add(pnlBottom, BorderLayout.SOUTH);
        gui.pack();
        gui.setVisible(true);
    }

    public void display() {
        gui.setVisible(true);
        System.out.println("Add Student GUI displayed...");
    }

    @Override
    public void windowClosing(WindowEvent e) {
        disposeGUI();
    }

    private void disposeGUI() {
        txtName.setText("");
        txtDob.setText("");
        txtAddress.setText("");
        txtEmail.setText("");
        // chua clear thg student
        check = false;
        gui.dispose();
        System.out.println("Add Student GUI disposed...");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.equalsIgnoreCase("Add")) {
            addStudent();
            JOptionPane.showMessageDialog(null, "Successful inserted");
        } else {
            JOptionPane.showMessageDialog(null, "Error");
        }
    }

    public void addStudent() {
        String sName = txtName.getText();
        String sDob = txtDob.getText();
        String sAddress = txtAddress.getText();
        String sEmail = txtEmail.getText();
        Student student = new Student(sName, sDob, sAddress, sEmail);

        try {
            Connection connection = DBConnect.getConnection();
            String query = "SELECT COUNT(id) FROM students";
            PreparedStatement ps1 = connection.prepareStatement(query);
            ResultSet updated = ps1.executeQuery();
            System.out.println(updated);
            a:
            while (updated.next()) {
                int count = updated.getInt(1);

                if (count == 0) {
                    check = false;
                    break a;
                } else if (check) {
//                    if (comp == student.getYear()) {
//                        check = false;
//                        break a;
//                    }
                    student.setYear(student.getYear() + updated.getInt(1) - 1);
                    student.generateStudentID();
                    comp = student.getYear();
                    check = false;
                    // lay mot lan duy nhat tuy nhien khi tat di, -> check lai = true
                    // tat di-> check
                }
                // count !=0
                // thoat ra mo lai
                System.out.println(check);
            }
            String sql = "INSERT INTO students(id,name,dob,address,email) values(?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(sql);


            ps.setString(1, student.getId());
            ps.setString(2, student.getName());
            ps.setString(3, student.getDob());
            ps.setString(4, student.getAddress());
            ps.setString(5, student.getEmail());
            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
