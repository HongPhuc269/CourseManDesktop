package a2_1901040159;



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class AddEnrolment implements ActionListener {
    JFrame frame;
    JPanel buttons, titlePanel;
    JLabel title;
    JButton ok, cancel;
    ResultSet rs;
    JComboBox students, modules;
    Statement stm;
    JTextField tfInternal, tfExamination;

    public AddEnrolment() {
        frame = new JFrame("Add new Module");

        titlePanel = new JPanel();
        titlePanel.setBackground(Color.white);
        title = new JLabel();
        title.setText("Provide all the details");
        titlePanel.add(title);
        frame.add(titlePanel, BorderLayout.NORTH);

        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 5, 5));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(15, 20, 2, 2));
        inputPanel.add(new JLabel("Student:"));
        students = new JComboBox();
        initStudentData();
        inputPanel.add(students);

        inputPanel.add(new JLabel("Module: "));
        modules = new JComboBox();
        initModuleData();
        inputPanel.add(modules);

        inputPanel.add(new JLabel("Internal Mark: "));
        tfInternal = new JTextField(19);
        inputPanel.add(tfInternal);

        inputPanel.add(new JLabel("Exam Mark: "));
        tfExamination = new JTextField(19);
        inputPanel.add(tfExamination);

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
            addEnrollment();
            JOptionPane.showMessageDialog(null, "Successfully Inserted");
        } else if (click.equals("Cancel")) {
            frame.dispose();
//            NewEnrolment newStudent = new NewEnrolment();
        }
    }

    private void initStudentData() {
        try {
            Connection connection = DBConnect.getConnection();
            stm = connection.createStatement();
            String s = "select id from students";
            rs = stm.executeQuery(s);
            while (rs.next()) {
                String name = rs.getString(1);
                students.addItem(rs.getString(1));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR");
        } finally {
            try {
                //st.close();
                rs.close();
                //con.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "ERROR CLOSE");
            }
        }
    }

    private void initModuleData() {
        try {
//            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DBConnect.getConnection();
//            con = DriverManager.getConnection("jdbc:mysql://localhost/d02", "root", "");
            stm = connection.createStatement();
            String s = "select code from modules";
            rs = stm.executeQuery(s);
            while (rs.next()) {
                String name = rs.getString(1);
                modules.addItem(rs.getString(1));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR");
        } finally {
            try {
                //st.close();
                rs.close();
                //con.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "ERROR CLOSE");
            }
        }
    }

    public void addEnrollment() {
        EnrolmentManager em = new EnrolmentManager();
        Connection connection = DBConnect.getConnection();

        String s_id = students.getSelectedItem().toString();
        String m_code = modules.getSelectedItem().toString();
        double internal = Double.parseDouble(tfInternal.getText());
        double examination = Double.parseDouble(tfExamination.getText());

        Student student = em.findByStudentId(em.getStudentList(), s_id);
        Module module = em.findByModuleCode(em.getModuleList(), m_code);
        Enrolment enrolment = em.createEnrolment(student, module);

        try {
//            String query = "INSERT INTO enrolments(id,s_id,m_code,internal_grade,exam_grade,final_grade) values(?,?,?,?,?,?)";
            String query = "INSERT INTO enrollments(student_id,module_code,intern_grade,exam_grade,final_grade) values(?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, s_id);
            ps.setString(2, m_code);
            ps.setDouble(3, internal);
            ps.setDouble(4, examination);
            ps.setObject(5, enrolment.getFinal(internal,examination));
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
//            JOptionPane.showMessageDialog(null, "Error message");
            throw new RuntimeException(e);
        }
    }

}