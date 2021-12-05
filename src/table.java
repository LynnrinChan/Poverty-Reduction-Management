import javax.swing.*;
import java.sql.*;
import java.util.Vector;

public class table {
    public static Vector getRows(String item, String SQLName, String type, String searchvalve){
        Connection conn;
        PreparedStatement preparedStatement = null;

        Vector rows = null;
        Vector columnHeads = null;

        try {
            conn = new SQLConnection().connection();
            if (type.equals("all"))
                preparedStatement = conn.prepareStatement("select "+item+" from "+SQLName);
            if (type.equals("poorusername"))
                preparedStatement = conn.prepareStatement("select "+item+" from "+SQLName+" where "+type+"="+searchvalve);
            if (type.equals("id"))
                preparedStatement = conn.prepareStatement("select "+item+" from "+SQLName+" where "+type+"="+searchvalve);
            ResultSet result1 = preparedStatement.executeQuery();
            rows = new Vector();
            ResultSetMetaData rsmd = result1.getMetaData();
            while(result1.next()){
                rows.addElement(getNextRow(result1,rsmd));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rows;
    }

    public static Vector getHead(String item, String SQLName, String type, String searchvalve) {
        Connection conn;
        PreparedStatement preparedStatement = null;
        Vector columnHeads = null;
        try {
            conn = new SQLConnection().connection();
            if (type.equals("all"))
                preparedStatement = conn.prepareStatement("select "+item+" from "+SQLName);
            if (type.equals("poorusername"))
                preparedStatement = conn.prepareStatement("select "+item+" from "+SQLName+" where "+type+"="+searchvalve);
            if (type.equals("id"))
                preparedStatement = conn.prepareStatement("select "+item+" from "+SQLName+" where "+type+"="+searchvalve);
            ResultSet result1 = preparedStatement.executeQuery();
            boolean moreRecords = result1.next();
            if(!moreRecords)
                JOptionPane.showMessageDialog(null, "结果集中无记录");
            columnHeads = new Vector();
            ResultSetMetaData rsmd = result1.getMetaData();
            for(int i = 1; i <= rsmd.getColumnCount(); i++)
                columnHeads.addElement(rsmd.getColumnName(i));
        }  catch (SQLException e) {
            e.printStackTrace();
        }
        return columnHeads;
    }

    private static Vector getNextRow(ResultSet rs,ResultSetMetaData rsmd) throws SQLException {
        Vector currentRow = new Vector();
        for(int i = 1; i <= rsmd.getColumnCount(); i++){
            currentRow.addElement(rs.getString(i));
        }
        return currentRow;
    }
}
