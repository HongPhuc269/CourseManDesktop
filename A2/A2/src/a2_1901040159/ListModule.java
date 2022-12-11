package a2_1901040159;



import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.util.Vector;

public class ListModule {
    JFrame frame;
    JPanel titlePanel;
    Connection connection;
    Statement stm;

    ResultSet rs;
    JLabel text;
    JTable table;

    public ListModule() throws SQLException {
        frame = new JFrame("Module List");

        titlePanel = new JPanel();
        titlePanel.setBackground(Color.green);
        text = new JLabel();
        text.setText("The List of Modules");
        text.setFont(text.getFont().deriveFont(Font.BOLD, 15f));
        titlePanel.add(text);
        frame.add(titlePanel, BorderLayout.NORTH);
        frame.setSize(800,200);

        connection = DBConnect.getConnection();
        stm = connection.createStatement();
        rs = stm.executeQuery("SELECT * FROM modules");

        table = new JTable(buildTableModel(rs));
        JScrollPane sp = new JScrollPane(table);
        frame.add(sp);
        frame.setVisible(true);

    }
    public void display(){
        frame.setVisible(true);
    }

    public static DefaultTableModel buildTableModel(ResultSet rs)
            throws SQLException {

        ResultSetMetaData metaData = rs.getMetaData();

        // names of columns
        Vector<String> columnNames = new Vector<String>();
        int columnCount = metaData.getColumnCount();
        for (int column = 1; column <= columnCount; column++) {
            columnNames.add(metaData.getColumnName(column));
        }

        // data of the table
        Vector<Vector<Object>> data = new Vector<Vector<Object>>();
        while (rs.next()) {
            Vector<Object> vector = new Vector<Object>();
            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                vector.add(rs.getObject(columnIndex));
            }
            data.add(vector);
        }

        return new DefaultTableModel(data, columnNames);

    }

}
