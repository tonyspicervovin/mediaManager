import com.sun.xml.internal.bind.v2.model.core.ID;
import org.omg.CORBA.CODESET_INCOMPATIBLE;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.Vector;

public class MediaDB {
    final String URL = "jdbc:sqlite:/Users/Tony/IdeaProjects/Bird/src/main/resources/media.db";
    // method to add media in to our DB, takes a Media object but will later be modified
    // to take a movie, game or book
    protected boolean addMedia(Media media){
        String prepStatInsertSQL ="INSERT INTO media (name, condition, description, media, price) VALUES ( ? , ? , ? , ? , ? )";
        try(Connection connection = DriverManager.getConnection(URL)){
            Statement statement = connection.createStatement();
                try(PreparedStatement psInsert = connection.prepareStatement(prepStatInsertSQL)){
                    psInsert.setString(1,media.getName());
                psInsert.setDouble(2,media.getCondition());
                psInsert.setString(3,media.getDescription());
                psInsert.setString(4,media.getMedia());
                psInsert.setDouble(5,media.getPrice());
                psInsert.executeUpdate();
                psInsert.close();
                return true;
            }
        }catch(SQLException e ){
            System.out.println(e);
        }return false;
    }


    protected Vector showMedia (){
        // this updates our table with current DB data
        try{
            Connection connection = DriverManager.getConnection(URL);
            Statement statement = connection.createStatement();
            String getAllData =  "SELECT * FROM media ORDER BY media ASC ";
            ResultSet allData = statement.executeQuery(getAllData);
            Vector data = new Vector();


            ArrayList<Integer> idCounter = new ArrayList<>();
            while (allData.next()){
                Vector thisData = new Vector();
                int id = allData.getInt("ID");
                String name = allData.getString("name");
                int condition = allData.getInt("condition");
                String description = allData.getString("description");
                String media = allData.getString("media");
                int price = allData.getInt("price");
                thisData.add(id);
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
        //populating vector
        Vector columns = new Vector();
        columns.add("ID");
        columns.add("name");
        columns.add("condition");
        columns.add("description");
        columns.add("media");
        columns.add("price");
        return columns;
    }
    protected void delete(String name){
        // for deleting media
        final String deleteSQL = "delete from media where name = ? ";
        try (Connection connection = DriverManager.getConnection(URL);
             PreparedStatement ps = connection.prepareStatement(deleteSQL)){
            ps.setString(1,name);

            int rowsUpdated = ps.executeUpdate();
            if (rowsUpdated!=0) {
                System.out.println("deleted");
            }
        }catch (SQLException e ){
            System.out.println(e);
            System.out.println("Error eh?");
        }





    }


    public void updateDB(String name, int condition, String description, String media, int price , int id) {
        final String updateSQL = "UPDATE media set name = ?, condition = ?, description = ?, media = ?, price = ? WHERE ID = ? ";
        try (Connection connection = DriverManager.getConnection(URL);
             PreparedStatement preparedStatement = connection.prepareStatement(updateSQL)) {
            preparedStatement.setString(1,name);
            preparedStatement.setInt(2,condition);
            preparedStatement.setString(3,description);
            preparedStatement.setString(4,media);
            preparedStatement.setInt(5,price);
            preparedStatement.setInt(6,id);
            preparedStatement.executeUpdate();
            System.out.println("I did it");
            preparedStatement.close();



        }catch(SQLException sq){
            System.out.println(sq);
        }


    }
}
