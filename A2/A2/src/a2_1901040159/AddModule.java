package a2_1901040159;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddModule implements ActionListener {
    JFrame frame;
    JPanel buttons, titlePanel;
    JLabel title, depart;
    JButton ok, cancel;
    JTextField tfName, tfSemester, tfCredit, tfDepartment;
    JComboBox modules;
    Module newModule ;
    boolean f = true;
    int comp = -1;

    public AddModule() {
        frame = new JFrame("Add Module");

        titlePanel = new JPanel();
        titlePanel.setBackground(Color.yellow);
        title = new JLabel();
        title.setText("Necessary Information is needed");
        titlePanel.add(title);
        frame.add(titlePanel, BorderLayout.NORTH);

        JPanel inputPanel = new JPanel(new GridLayout(5, 2, 5, 5));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(15, 20, 2, 2));
        inputPanel.add(new JLabel("Name:"));
        tfName = new JTextField(19);
        inputPanel.add(tfName);

        inputPanel.add(new JLabel("Semester: "));
        tfSemester = new JTextField(19);
        inputPanel.add(tfSemester);

        inputPanel.add(new JLabel("Credit: "));
        tfCredit = new JTextField(19);
        inputPanel.add(tfCredit);

        String[] moduleTypes = {"Compulsory", "Elective"};
        inputPanel.add(new JLabel("Module Type: "));
        modules = new JComboBox(moduleTypes);
        modules.setSelectedIndex(0);
        modules.setEditable(true);

        depart = new JLabel("Department: ");
        tfDepartment = new JTextField(19);

        modules.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JComboBox comboBox = (JComboBox) e.getSource();
                Object selected = comboBox.getSelectedItem();
                if (selected.toString().equals("Elective")) {
                    {
                        inputPanel.add(depart);
                        inputPanel.add(tfDepartment);
                    }
                } else {
                    inputPanel.remove(depart);
                    inputPanel.remove(tfDepartment);

                }
                display();
            }
        });
        inputPanel.add(modules);
        frame.add(inputPanel);

        buttons = new JPanel();
        frame.add(buttons, BorderLayout.SOUTH);
        ok = new JButton("Save");
        ok.addActionListener(this);
        cancel = new JButton("Cancel");
        cancel.addActionListener(this);
        buttons.add(ok);
        buttons.add(cancel);

        frame.pack();
        frame.setVisible(true);
    }

    public void display() {
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String click = e.getActionCommand();
        if (click.equals("Save")) {
            addModule();
            JOptionPane.showMessageDialog(null, "Successfully Inserted");
        } else if (click.equals("Cancel")) {
            f = true;
            comp = -1;
            frame.dispose();
            AddModule newStudent = new AddModule();
        }
    }

    public void addModule() {
//        Connection conn = DriverManager.getConnection("jdbc:sqlite:CourseMan.sqlite3");
        String name = tfName.getText();
        int semester = Integer.parseInt(tfSemester.getText());
//        int sem = Integer.parseInt(semester);
        int credit = Integer.parseInt(tfCredit.getText());
//        int cred = Integer.parseInt(credit);
        String type = modules.getSelectedItem().toString();
        String department = tfDepartment.getText();

        if (type.equalsIgnoreCase("Compulsory")) {
//            newModule = new CompulsoryModule(name, sem, cred);
            System.out.println(name);
            newModule = new CompulsoryModule(name, semester, credit);
        } else if (type.equalsIgnoreCase("Elective")) {
            newModule = new ElectiveModule(name, semester, credit, department);
        }
        try {
            Connection connection = DBConnect.getConnection();
            String sq = "INSERT INTO modules(code,name,semester,credits,type,department) VALUES (?,?,?,?,?,?)";
            PreparedStatement pr = connection.prepareStatement(sq);
            pr.setString(1, newModule.getCode());
            pr.setString(2, newModule.getName());
            pr.setInt(3, newModule.getSemester());
            if(comp == -1) {
                comp = newModule.getSemester();
            }
            pr.setInt(4, newModule.getCredits());
            pr.setString(5, newModule.getType());
            pr.setString(6, newModule.getDepartName());

            String query = "SElECT COUNT(code) FROM modules where code LIKE \'m"+newModule.getSemester()+"%\'";
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet updated = ps.executeQuery();
            a: while(updated.next()){
                int count = updated.getInt(1);
                System.out.println(count);
                if (count == 0) {
                    newModule.setId(1);
                    newModule.generateModuleCode(newModule.getSemester());
                    pr.setString(1, newModule.getCode());
                    f = false;
                    break a;
                }
                if (f) {
                    newModule.setId(++count);
                    newModule.generateModuleCode(newModule.getSemester());
                    System.out.println(newModule.getCode());
                    pr.setString(1, newModule.getCode());
                    f = false;
                }
                // 1 v 1 -> skip 1 2 -> chay-> 2 2
                if ( comp != newModule.getSemester()) {
                    newModule.setId(++count);
                    newModule.generateModuleCode(newModule.getSemester());
                    System.out.println(newModule.getCode());
                    pr.setString(1, newModule.getCode());
                }
                comp = newModule.getSemester();
            }


            pr.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
