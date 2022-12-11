package a2_1901040159;



import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.util.Vector;

public class ListStudent {
    JFrame frame;
    JPanel titlePanel;
    Connection connection;
    Statement stm;

    ResultSet rs;
    JLabel text;
    JTable table;
    public ListStudent() throws SQLException {

        frame = new JFrame("Student List");

        titlePanel = new JPanel();
//        titlePanel.setBackground(Color.yellow);
        text = new JLabel();
        text.setText("The List of Students");
        text.setFont(text.getFont().deriveFont(Font.BOLD, 15f));
        titlePanel.add(text);
        frame.add(titlePanel, BorderLayout.NORTH);
        frame.setSize(800,200);

        connection = DBConnect.getConnection();
        stm = connection.createStatement();
        rs = stm.executeQuery("SELECT * FROM students");

        table = new JTable(buildTableModel(rs));
        JScrollPane sp = new JScrollPane(table);
        frame.add(sp);
        frame.setVisible(true);
    }
//    public void data() throws  SQLException {
//
//        Connection conn = DBConnect.getConnection();
//        Statement st = conn.createStatement();
//        String qr = "SELECT * FROM Student";
//        ResultSet rs = st.executeQuery(qr);
//        ResultSetMetaData rsmd = rs.getMetaData();
//        DefaultTableModel model = (DefaultTableModel) table.getModel();
//
//        int col = rsmd.getColumnCount();
//
//        String[] colName = new String[col];
//        for (int i = 0; i < col; i++) {
//            colName[i] = rsmd.getColumnName(i + 1);
//            model.setColumnIdentifiers(colName);
//            String s_id, s_name, dob, address, email;
//            while (rs.next()) {
//                s_id = rs.getString(1);
//                s_name = rs.getString(2);
//                dob = rs.getString(3);
//                address = rs.getString(4);
//                email = rs.getString(5);
//                String[] row = {s_id, s_name, dob, address, email};
//                model.addRow(row);
//            }
//        }
//
//    }
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
    public void display(){
        frame.setVisible(true);

    }
}
