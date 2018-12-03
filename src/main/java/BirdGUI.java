import javax.swing.*;

public class BirdGUI extends JFrame{
    private JTable birdDataTable;
    private JPanel mediaPanel;
    private JTextField nameTextField;
    private JTextField descriptionTextField;
    private JComboBox mediaType;
    private JSpinner spinner1;
    private JTextField priceTextField;
    private JButton addGameButton;
    private JButton deleteSelectedButton;
    private JButton searchByDescriptionButton1;
    private JTextField textField1;

    private BirdDatabase db;

    BirdGUI(){




        setContentPane(mediaPanel);
        pack();
        setTitle("Media Manager");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        configureTable();
        setVisible(true);
    }

    private void configureTable() {
        //Vector columnNames = db.getColumnNames();
        //Vector data= db.getAllBirds();

        //DefaultTableModel tableModel = new DefaultTableModel(data,columnNames);


       // birdDataTable.setModel(tableModel);

    }


    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
