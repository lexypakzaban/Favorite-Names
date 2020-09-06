import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FavoriteNamesFrame extends JFrame implements ActionListener
{
    private JTextField nameField;
    private JButton addButton;
    private JButton removeButton;
    private JButton clearButton;
    private FavoriteNamesPanel mainPanel;

    public FavoriteNamesFrame()
    {
        super("Favorite Names");
        setSize(1000, 800);
        setResizable(false);
        setupLayout();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    public void setupLayout()
    {
        getContentPane().setLayout(new BorderLayout());
        JPanel southPanel = new JPanel();
        nameField = new JTextField();
        nameField.setColumns(20);
        addButton = new JButton("Add this Name");
        addButton.addActionListener(this);
        removeButton = new JButton("Remove this Name");
        removeButton.addActionListener(this);
        clearButton = new JButton ("Clear all Names");
        clearButton.addActionListener(this);
        southPanel.add(new JLabel("Name:"));
        southPanel.add(nameField);
        southPanel.add(addButton);
        southPanel.add(clearButton);
        getContentPane().add(southPanel,BorderLayout.SOUTH);

        mainPanel = new FavoriteNamesPanel();
        getContentPane().add(mainPanel,BorderLayout.CENTER);
    }

    public void actionPerformed(ActionEvent actEvt)
    {
        if (actEvt.getSource() == addButton)
        {
            handleAddPressed();
        }
        if (actEvt.getSource() == removeButton)
        {
            handleRemovePressed();
        }
        if (actEvt.getSource() == clearButton)
        {
            handleClearPressed();
        }
    }

    /**
     * Response to the add button, by way of actionPerformed():
     * send the contents of the text field to the mainPanel's
     * addName() method. Then clear the text field.
     */
    public void handleAddPressed()
    {
        System.out.println("Add pressed. Sending "+nameField.getText());
        mainPanel.addName(nameField.getText());
        nameField.setText("");
    }
    /**
     * Response to the remove button, by way of actionPerformed():
     * send the contents of the text field to the mainPanel's
     * removeName() method. Then clear the text field.
     */
    public void handleRemovePressed()
    {
        System.out.println("Remove pressed. Sending "+nameField.getText());
        mainPanel.removeName(nameField.getText());
        nameField.setText("");
    }

    /**
     * Response to the clear button, by way of actionPerformed:
     * call the mainPanel's "clearNames()" method.
     */
    public void handleClearPressed()
    {
        System.out.println("Clear pressed.");
        mainPanel.clearNames();
    }
}
