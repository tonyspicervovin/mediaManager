import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private JButton showButton;

    static final String movie = "Movie";
    static final String book = "Book";
    static final String game = "Game";
    public MediaDB callit = new MediaDB();

    BirdGUI(){




        setContentPane(mediaPanel);
        pack();
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mediaComboBox.addItem(movie);
        mediaComboBox.addItem(book);
        mediaComboBox.addItem(game);
        setListeners();

    }

    private void setListeners() {
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameTextField.getText();
                int condition = (Integer) conditionSpinner.getValue();
                String description = descriptionTextField.getText();
                String media = (String) mediaComboBox.getSelectedItem();
                int price = Integer.valueOf(priceTextField.getText());
                if (media == movie){
                    Movie another = new Movie(name,condition,description,media,price);
                    callit.addMedia(another);
                }
                if (media == book){
                    Book another = new Book(name,condition,description,media,price);
                    callit.addMedia(another);
                }
                if (media == game){
                    Game another = new Game(name,condition,description,media,price);
                    callit.addMedia(another);
                }




            }
        });

        showButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Vector<String> columns = callit.getColumns();
                Vector data = callit.showMedia();

                DefaultTableModel table = new DefaultTableModel(data,columns);
                mediaTable.setModel(table);
            }
        });





}}
