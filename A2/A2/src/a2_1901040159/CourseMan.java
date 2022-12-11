package a2_1901040159;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class CourseMan implements ActionListener {
    public static final int WIDTH = 700;
    public static final int HEIGHT = 300;

    JMenuBar mb;
    JFrame gui;
    JMenu fileMenu, studentMenu, moduleMenu, enrolmentMenu;

    JMenuItem fileExit, addStudent, displayStudents,
            newModule, displayModules, addEnrolment,
            showInitialReport, showFullReport;

    static JLabel label;
    static JTextField textField;

    static JTextArea area;

    public CourseMan() {
        gui = new JFrame("Course Management");
        gui.setDefaultCloseOperation(EXIT_ON_CLOSE);
        gui.setSize(WIDTH, HEIGHT);
//        Menu Bar
        mb = new JMenuBar();
        fileMenu = new JMenu("File");
        studentMenu = new JMenu("Student");
        moduleMenu = new JMenu("Module");
        enrolmentMenu = new JMenu("Enrolment");

//        Menu Item
        fileExit = new JMenuItem("Exit");
        addStudent = new JMenuItem("New");
        displayStudents = new JMenuItem("Show All");
        newModule = new JMenuItem("New");
        displayModules = new JMenuItem("Show All");
        addEnrolment = new JMenuItem("New");
        showInitialReport = new JMenuItem("Initial Report");
        showFullReport = new JMenuItem("Full Report");

        fileExit.addActionListener(this);
        addStudent.addActionListener(this);
        displayStudents.addActionListener(this);

        newModule.addActionListener(this);

        addEnrolment.addActionListener(this);
        displayModules.addActionListener(this);

        showInitialReport.addActionListener(this);
        showFullReport.addActionListener(this);


        fileMenu.add(fileExit);
        studentMenu.add(addStudent);
        studentMenu.add(displayStudents);
        moduleMenu.add(newModule);
        moduleMenu.add(displayModules);
        enrolmentMenu.add(addEnrolment);
        enrolmentMenu.add(showInitialReport);
        enrolmentMenu.add(showFullReport);

        mb.add(fileMenu);
        mb.add(studentMenu);
        mb.add(moduleMenu);
        mb.add(enrolmentMenu);


        gui.setJMenuBar(mb);

        textField = new JTextField("Sorry for the inconvenience." +
                "Please change the DBName path in DBConnect class because I can only add the data through full path");
//        textField2 = new JTextField("Please change the DBName path because I can only add the data through full path");
        gui.add(textField);
//        gui.add(textField2);
        gui.setVisible(true);
    }

    public void display() {
        gui.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == fileExit) {
            System.exit(0);
        }
        if (e.getSource() == addStudent) {
            AddStudent addStudent = new AddStudent();
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    addStudent.display();
                }
            });
        }
        if (e.getSource() == displayStudents) {
            ListStudent studentList = null;
            try {
                studentList = new ListStudent();
                studentList.display();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }

        if (e.getSource() == newModule) {
            AddModule addModule = new AddModule();
            addModule.display();
        }

        if (e.getSource() == displayModules) {
            try {
                ListModule moduleTest = new ListModule();
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        moduleTest.display();
                    }
                });
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

//            ModuleList moduleList = new ModuleList();
//            moduleList.displayWindow();
        }

        if (e.getSource() == addEnrolment) {
            AddEnrolment enrolmentDemo = new AddEnrolment();
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    enrolmentDemo.display();
                }
            });
        }
        if (e.getSource() == showInitialReport) {
            try {
                InitialReport initialReport = new InitialReport();
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        initialReport.display();
                    }
                });
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
        if (e.getSource() == showFullReport) {
            try {
                FullReport initialReport = new FullReport();
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        initialReport.display();
                    }
                });
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
//            EnrolmentList enrol = new EnrolmentList();
//            AddEnrolment addEnrolment = new AddEnrolment(enrol, gui);
//            addEnrolment.dislay();


    }

}
