import org.omg.CORBA.CODESET_INCOMPATIBLE;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.Vector;

public class MediaDB {
    final String URL = "jdbc:sqlite:/Users/Tony/IdeaProjects/Bird/src/main/resources/media.db";
    protected boolean addMedia(Media media){
        String prepStatInsertSQL ="INSERT INTO media VALUES ( ? , ? , ? , ? , ? )";
        try(Connection connection = DriverManager.getConnection(URL)){
            Statement statement = connection.createStatement();
                try(PreparedStatement psInsert = connection.prepareStatement(prepStatInsertSQL)){
                psInsert.setString(1,media.getName());
                psInsert.setDouble(2,media.getCondition());
                psInsert.setString(3,media.getDescription());
                psInsert.setString(4,media.getMedia());
                psInsert.setDouble(5,media.getPrice());
                psInsert.executeUpdate();
                return true;
            }
        }catch(SQLException e ){
            System.out.println(e);
        }return false;
    }

    protected Vector showMedia (){
        try{
            Connection connection = DriverManager.getConnection(URL);
            Statement statement = connection.createStatement();
            String getAllData =  "SELECT * FROM media ORDER BY media ASC ";
            ResultSet allData = statement.executeQuery(getAllData);
            Vector data = new Vector();



            while (allData.next()){
                Vector thisData = new Vector();
                String name = allData.getString("name");
                int condition = allData.getInt("condition");
                String description = allData.getString("description");
                String media = allData.getString("media");
                int price = allData.getInt("price");
                thisData.add(name);
                thisData.add(condition);
                thisData.add(description);
                thisData.add(media);
                thisData.add(price);
                data.add(thisData);
                }

        return data;


            }

        catch (SQLException q ){
            System.out.println(q);
        }
        return null;
    }
    protected Vector getColumns(){
        Vector columns = new Vector();
        columns.add("name");
        columns.add("condition");
        columns.add("description");
        columns.add("media");
        columns.add("price");
        return columns;
    }
    protected void delete(String name){
        final String deleteSQL = "delete from media where name = ? ";
        try (Connection connection = DriverManager.getConnection(URL);
             PreparedStatement ps = connection.prepareStatement(deleteSQL)){
            ps.setString(1,name);

            int rowsUpdated = ps.executeUpdate();
            if (rowsUpdated==0) {
                System.out.println("deleted");
            }
        }catch (SQLException e ){
            System.out.println(e);
            System.out.println("Error eh?");
        }





    }
    protected void updateDB(Object value,int row, int col){
        String update = null;
        if (col == 0) {
             update = "name";
            String name=(String) value;
        } else if (col == 1) {
             update = "condition";
            int condition = (Integer) value;
        } else if (col == 2) {
             update = "description";
            String description = (String) value;
        } else if (col == 3) {
             update = "media";
            String media = (String) value;
        } else if (col == 4) {
             update = "price";
            double price = (Double) value;
        }
        final String updateSQL = "UPDATE media SET ? = ? WHERE ? = ?";
        try (Connection connection = DriverManager.getConnection(URL);
             PreparedStatement preparedStatement = connection.prepareStatement(updateSQL)) {
            preparedStatement.setString(1,update);
            preparedStatement;
        }
    }

}
