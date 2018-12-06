import org.omg.CORBA.CODESET_INCOMPATIBLE;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.Vector;

public class MediaDB {
    final String URL = "jdbc:sqlite:/Users/Tony/IdeaProjects/Bird/src/main/resources/media.db";
    protected boolean addMedia(Media media){
        String prepStatInsertSQL ="INSERT INTO media VALUES ( ? , ? , ? , ? , ? ,?)";
        try(Connection connection = DriverManager.getConnection(URL)){
            Statement statement = connection.createStatement();
                try(PreparedStatement psInsert = connection.prepareStatement(prepStatInsertSQL)){
                    psInsert.setString(1,null);
                    psInsert.setString(2,media.getName());
                psInsert.setDouble(3,media.getCondition());
                psInsert.setString(4,media.getDescription());
                psInsert.setString(5,media.getMedia());
                psInsert.setDouble(6,media.getPrice());
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
    protected void updateDB(Object value,int row, int col) {

    }


}
