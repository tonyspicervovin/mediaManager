import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
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
    private JButton searchByDescriptionButton1;
    private JTextField searchDescription;
    private JTextField descriptionTextField;
    private JComboBox mediaComboBox;
    private JSpinner conditionSpinner;
    private JLabel addLabel;
    private DefaultTableModel tableModel;
    static final String movie = "Movie";
    static final String book = "Book";
    static final String game = "Game";
    public MediaDB callit = new MediaDB();


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
        ArrayList <Integer> idCounter = new ArrayList<>();
        Vector<String> columns = callit.getColumns();
        Vector <Vector> data = callit.showMedia();
        for(Vector v : data){
            idCounter.add((int) v.get(0));
            v.remove(0);
        }
        DefaultTableModel tableModel = new DefaultTableModel(data,columns) {
            @Override
            public void setValueAt(Object value, int row, int col) {
                String name = (String) getValueAt(row,0);
                int condition = (int) getValueAt(row,1);
                String description = (String) getValueAt(row,2);
                String media = (String) getValueAt(row,3);
                int price = (int) getValueAt(row, 4);
                System.out.println("Once");


                int id = idCounter.get(row);

                callit.updateDB(name,condition,description,media,price, id);


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
        for(Vector v : data){
            v.remove(0);
        }
        columns.remove(0);

        System.out.println(data);
        System.out.println(columns);
        tableModel.setDataVector(data,columns);

    }

    private void setListeners() {
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // add button
                try{


                String name = nameTextField.getText();
                if (name==""){
                    showMessageDialog("Enter a valid name");

                }else {
                    int condition = (Integer) conditionSpinner.getValue();
                    String description = descriptionTextField.getText();
                    String media = (String) mediaComboBox.getSelectedItem();
                    double price = Double.valueOf(priceTextField.getText());
                    if (media == movie) {
                        Media.Movie another = new Media.Movie(name, condition, description, media, price);
                        boolean updated = callit.addMedia(another);
                        if (updated) {
                            addLabel.setText("Added!");
                        } else {
                            addLabel.setText("Oops!");
                        }
                    }
                    if (media == book) {
                        Book another = new Book(name, condition, description, media, price);
                        boolean updated = callit.addMedia(another);
                        if (updated) {
                            addLabel.setText("Added!");
                        } else {
                            addLabel.setText("Oops!");
                        }
                    }
                    if (media == game) {
                        Game another = new Game(name, condition, description, media, price);
                        boolean updated = callit.addMedia(another);
                        if (updated) {
                            addLabel.setText("Added!");
                        } else {
                            addLabel.setText("Oops!");
                        }
                    }

                    showIt();
                }

            }catch (NumberFormatException npe){
                    showMessageDialog("Enter a valid price");
                }
            }

        });



        deleteSelectedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // delete button


                int rowSelected = mediaTable.getSelectedRow();
                System.out.println(rowSelected);
                String name = (String) mediaTable.getValueAt(rowSelected, 2);
                callit.delete(name);
                showIt();


                mediaTable.getModel().addTableModelListener(new TableModelListener() {
                    @Override
                    // update listener
                    public void tableChanged(TableModelEvent e) {

                        int rowselected = e.getFirstRow();

                        String changed = (String) mediaTable.getValueAt(e.getFirstRow(),e.getColumn());
                        System.out.println(changed);
                    }
                });




            }
        });


}
    protected void showMessageDialog(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}
