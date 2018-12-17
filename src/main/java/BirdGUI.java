import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

public class BirdGUI extends JFrame{
    private JTable mediaTable;
    private JPanel mediaPanel;
    private JTextField nameTextField;
    private JTextField priceTextField;
    private JButton addButton;
    private JButton deleteSelectedButton;
    private JButton searchByNameButton;
    private JTextField searchbyName;
    private JTextField descriptionTextField;
    private JComboBox mediaComboBox;
    private JSpinner conditionSpinner;
    private JButton showAllMoviesButton;
    private JButton showAllBooksButton;
    private JButton showGamesButton;
    private JButton showAll;
    private DefaultTableModel tableModel;
    static final String movie = "Movie";
    static final String book = "Book";
    static final String game = "Game";
    public MediaDB callit = new MediaDB();
    ArrayList <Integer> idCounter = new ArrayList<>();

    BirdGUI(){


        // constructer, populating combo box, setting condition spinner, shows data from DB

        setContentPane(mediaPanel);
        pack();
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mediaComboBox.addItem(movie);
        mediaComboBox.addItem(book);
        mediaComboBox.addItem(game);
        setListeners();
        int min = 0;
        int max = 10;
        int step= 1;
        int inital = 0;
        SpinnerModel model = new SpinnerNumberModel(inital,min,max,step);
        conditionSpinner.setModel(model);
        initializeTable();

    }
    protected void initializeTable(){
        Vector<String> columns = callit.getColumns();
        Vector <Vector> data = callit.showMedia();
        for(Vector v : data){
            v.remove(0);
        }
        tableModel = new DefaultTableModel(data,columns) {
            @Override
            public void setValueAt(Object value, int row, int col) {

                int id = idCounter.get(row);
                if (col==0){
                    String name = (String) value;
                    callit.updateName(name,id);
                }
                if (col==1){
                    String condo = (String) value;
                    int condition = Integer.valueOf(condo);
                    callit.updateCondition(condition,id);
                }
                if (col==2){
                    String description = (String) value;
                    callit.updateDescription(description,id);
                }
                if (col==3){
                    String media = (String) value;
                    callit.updateMedia(media,id);
                }
                if (col==4){
                    String priced = (String) value;
                    double price = Double.valueOf(priced);
                    callit.updatePrice(price,id);
                }





                showIt();


            }

        };
        mediaTable.setModel(tableModel);
        showIt();
    }



    private void showIt() {
        // method to show DB data in J Table
        Vector columns = callit.getColumns();
        Vector <Vector> data;
        data = callit.showMedia();
        idCounter.clear();
        for(Vector v : data){
            idCounter.add((int) v.get(0));
            v.remove(0);

        }
        columns.remove(0);

        tableModel.setDataVector(data,columns);

    }

    private void setListeners() {
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // add button
                try{


                String name = nameTextField.getText();
                if (name.isEmpty()){
                    showMessageDialog("Enter a valid name");

                }else {
                    int condition = (Integer) conditionSpinner.getValue();
                    String description = descriptionTextField.getText();
                    String media = (String) mediaComboBox.getSelectedItem();
                    double price = Double.valueOf(priceTextField.getText());

                    if (media == movie) {
                        Media.Movie another = new Media.Movie(name, condition, description, media, price);
                        boolean updated = callit.addMedia(another);

                    }
                    if (media == book) {
                        Book another = new Book(name, condition, description, media, price);
                        boolean updated = callit.addMedia(another);

                    }
                    if (media == game) {
                        Game another = new Game(name, condition, description, media, price);
                        boolean updated = callit.addMedia(another);

                    }

                    showIt();
                }

            }catch (NumberFormatException npe){
                    showMessageDialog("Enter a valid price");
                }
            }

        });


        searchByNameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Vector columns = callit.getColumns();
                String searchText = searchbyName.getText();
                Vector <Vector> data;
                data = callit.searchName(searchText);
                idCounter.clear();
                for(Vector v : data){
                    idCounter.add((int) v.get(0));
                    v.remove(0);

                }
                columns.remove(0);

                tableModel.setDataVector(data,columns);




            }
        });
        showGamesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Vector columns = callit.getColumns();
                Vector <Vector> data;
                data = callit.showGames();
                idCounter.clear();
                for(Vector v : data){
                    idCounter.add((int) v.get(0));
                    v.remove(0);

                }
                columns.remove(0);

                tableModel.setDataVector(data,columns);




            }
        });
        deleteSelectedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // delete button


                int rowSelected = mediaTable.getSelectedRow();
                int id = idCounter.get(rowSelected);
                System.out.println(id);
                callit.delete(id);
                showIt();








            }
        });
        showAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showIt();
            }
        });
        showAllMoviesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Vector columns = callit.getColumns();
                Vector <Vector> data;
                data = callit.showMovies();
                idCounter.clear();
                for(Vector v : data){
                    idCounter.add((int) v.get(0));
                    v.remove(0);

                }
                columns.remove(0);

                tableModel.setDataVector(data,columns);

            }
        });
        showAllBooksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Vector columns = callit.getColumns();
                Vector <Vector> data;
                data = callit.showBooks();
                idCounter.clear();
                for(Vector v : data){
                    idCounter.add((int) v.get(0));
                    v.remove(0);

                }
                columns.remove(0);

                tableModel.setDataVector(data,columns);

            }


        });

}
    protected void showMessageDialog(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}
