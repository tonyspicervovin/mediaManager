import java.sql.*;
import java.util.Vector;

public class BirdDatabase {

    private static final String DB_CONNECTION_URL = "jdbc:sqlite:bird.sqlite";
    private static final String ID_COLUMN = "id";
    private static final String NAME_COLUMN = "name";
    private static final String DESCRIPTION_COLUMN = "description";

    private static final String CREATE_MEDIA_TABLE = "CREATE TABLE media (id INTEGER PRIMARY KEY, name TEXT, description TEXT, price number, condition number )";
    private static final String GET_ALL_MOVIES = "SELECT * FROM birds";

    BirdDatabase(){
        createTable();
    }

    private void createTable() {

        try (Connection connection = DriverManager.getConnection(DB_CONNECTION_URL);
             Statement statement = connection.createStatement()){

            statement.executeUpdate(CREATE_MEDIA_TABLE);



        }catch (SQLException e){
            if (e.getMessage().contains("(table media already exists")){

            }else{
                throw new RuntimeException(e);
            }
        }
    }
    Vector<Vector> getAllBirds(){
        try (Connection connection = DriverManager.getConnection(DB_CONNECTION_URL);
        Statement statement = connection.createStatement()){
            ResultSet rs = statement.executeQuery(GET_ALL_MOVIES);

            Vector <Vector> vectors = new Vector<>();

            int id;
            String name, description;

            while (rs.next()){

                id = rs.getInt(ID_COLUMN);
                name = rs.getString(NAME_COLUMN);
                description = rs.getString(DESCRIPTION_COLUMN);

                Vector v = new Vector();
                v.add(id); v.add(name); v.add(description);
                vectors.add(v);
            }
            return vectors;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }

    }
    Vector getColumnNames(){
        Vector colNames = new Vector();
        colNames.add("ID");
        colNames.add("Name");
        colNames.add("Description");

        return colNames;
    }
}
