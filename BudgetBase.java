// base code for student budget assessment
// Students do not need to use this code in their assessment, fine to junk it and do something different!
//
// Your submission must be a maven project, and must be submitted via Codio, and run in Codio
//
// user can enter in wages and loans and calculate total income
//
// run in Codio 
// To see GUI, run with java and select Box Url from Codio top line menu
//
// Layout - Uses GridBag layout in a straightforward way, every component has a (column, row) position in the UI grid
// Not the prettiest layout, but relatively straightforward
// Students who use IntelliJ or Eclipse may want to use the UI designers in these IDEs , instead of GridBagLayout
package Budget;

// Swing imports
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

// class definition
public class BudgetBase extends JPanel {    // based on Swing JPanel

    // high level UI stuff
    JFrame topLevelFrame;  // top-level JFrame
    GridBagConstraints layoutConstraints = new GridBagConstraints(); // used to control layout

    // widgets which may have listeners and/or values
    private JButton calculateButton;   // Calculate button
    private JButton exitButton;        // Exit button
    private JTextField wagesField;     // Wages text field
    private JTextField loansField;     // Loans text field
    private JTextField otherField;     // Other text field
    private JTextField foodField;      // Food text field
    private JTextField rentField;      // Rent text field
    private JTextField otherSpendField; // Other Spending field
    private JTextField totalIncomeField; // Total Income field
    private JTextField plusDefField;    // surplus/ deficit field
    private String options[] = {"Per week", "Per month", "Per year"};

    // constructor - create UI  (dont need to change this)
    public BudgetBase(JFrame frame) {
        topLevelFrame = frame; // keep track of top-level frame
        setLayout(new GridBagLayout());  // use GridBag layout
        initComponents();  // initalise components
    }

    private JTextField makeTextField(String text, int col, boolean editable){
        JTextField textField = new JTextField(text, col);
        textField.setHorizontalAlignment((JTextField.RIGHT));
        textField.setEditable(editable);
        return textField;
    }

    // initialise componenents
    // Note that this method is quite long.  Can be shortened by putting Action Listener stuff in a separate method
    // will be generated automatically by IntelliJ, Eclipse, etc
    private void initComponents() { 

        // --- Top row (0) ---
        //INCOME label and SPENDING label
        JLabel incomeLabel = new JLabel("INCOME");
        addComponent(incomeLabel, 0, 0);

        JLabel spendingLabel = new JLabel("SPENDING");
        addComponent(spendingLabel, 0, 3);

        // --- Row 1 ---
        // Wages label followed by wages textbox
        JLabel wagesLabel = new JLabel("Wages");
        addComponent(wagesLabel, 1, 0);

        // set up text field for entering wages
        // have made make text field method
        wagesField = makeTextField("", 10, true);
        addComponent(wagesField,1,1);

        JComboBox wageOptions = new JComboBox(options);
        addComponent(wageOptions, 1, 2);

        // food label follwed by food textbox
        JLabel foodLabel = new JLabel("Food");
        addComponent(foodLabel, 1, 3);

        //set up text field for enetring food cost
        foodField = makeTextField("", 10, true);
        addComponent(foodField,1,4);

        // set up drop down list (combo box) for the food cost field
        JComboBox foodOptions = new JComboBox(options);
        addComponent(foodOptions, 1, 5); 

        // --- Row 2 --- 
        //Loans label followed by loans textbox
        JLabel loansLabel = new JLabel("Loans");
        addComponent(loansLabel, 2, 0);

        // set up text box for entering loans
        loansField = makeTextField("", 10, true);
        addComponent(loansField, 2, 1); 

        // set up drop down list (combo box) for the loans field
        JComboBox loanOptions = new JComboBox(options);
        addComponent(loanOptions, 2, 2); 

        // rent label followed by rent textbox
        JLabel rentLabel = new JLabel("Rent");
        addComponent(rentLabel, 2, 3);

        //set up text field for enetring rent cost
        rentField = makeTextField("", 10, true);
        addComponent(rentField,2,4);

        // set up drop down list (combo box) for the rent field
        JComboBox rentOptions = new JComboBox(options);
        addComponent(rentOptions, 2, 5); 

        // --- Row 3 ---
        //other label followed by other textbox
        JLabel otherLabel = new JLabel("Other");
        addComponent(otherLabel, 3, 0);

        // set up text box for entering other incomes
        otherField = makeTextField("", 10, true);
        addComponent(otherField, 3, 1); 

        // set up drop down list (combo box) for the other field
        JComboBox otherOptions = new JComboBox(options);
        addComponent(otherOptions, 3, 2); 

        // other spending label followed by other spending text box
        JLabel OtherSpendLabel = new JLabel("Other");
        addComponent(OtherSpendLabel, 3, 3);

        //set up text field for enetring other spending
        otherSpendField = makeTextField("", 10, true);
        addComponent(otherSpendField,3,4);

        // set up drop down list (combo box) for the other spending field
        JComboBox otherSpendOptions = new JComboBox(options);
        addComponent(otherSpendOptions, 3, 5); 

        // --- Row 4 ---
        //Total Income label followed by total income field
        JLabel totalIncomeLabel = new JLabel("Total Income");
        addComponent(totalIncomeLabel, 4, 0);

        // set up text box for displaying total income.  Users cam view, but cannot directly edit it
        totalIncomeField = makeTextField("0", 10, false);
        addComponent(totalIncomeField, 4, 1);  
       
        // --- Row 5 ---
        //Total Income label followed by total income field
        JLabel plusDeflLabel = new JLabel("Surplus/ Deficit  ");
        addComponent(plusDeflLabel, 5, 0);
        
        // set up text box for displaying the surplus or deficit.  Users cam view, but cannot directly edit it
        plusDefField = makeTextField("0", 5, false);
        addComponent(plusDefField, 5, 1);  

        // --- Row 6 --- 
        //Calculate Button
        calculateButton = new JButton("Calculate");
        addComponent(calculateButton, 6, 0);  


        // ---Row 7 --- 
        //Exit Button
        exitButton = new JButton("Exit");
        addComponent(exitButton, 7, 0);  

        // set up  listeners (in a spearate method)
        initListeners();
    }

    // set up listeners
    // initially just for buttons, can add listeners for text fields
    private void initListeners() {

        // exitButton - exit program when pressed
        exitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // calculateButton - call calculateTotalIncome() when pressed
        calculateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                calculateTotalIncome();
                calculateDeficitOrPlus();
            }
        });
    }
    // add a component at specified row and column in UI.  (0,0) is top-left corner
    private void addComponent(Component component, int gridrow, int gridcol) {
        layoutConstraints.fill = GridBagConstraints.HORIZONTAL;   // always use horixontsl filll
        layoutConstraints.gridx = gridcol;
        layoutConstraints.gridy = gridrow;
        add(component, layoutConstraints);

    }

    // update totalIncomeField (eg, when Calculate is pressed)
    // use double to hold numbers, so user can type fractional amounts such as 134.50
    public double calculateTotalIncome() {

        // get values from income text fields.  valie is NaN if an error occurs
        double wages = getTextFieldValue(wagesField);
        double loans = getTextFieldValue(loansField);
        double other =  getTextFieldValue(otherField);

        // clear total field and return if any value is NaN (error)
        if (Double.isNaN(wages) || Double.isNaN(loans) || Double.isNaN(other)) {
            totalIncomeField.setText("");  // clear total income field
            wages = 0.0;
            return wages;   // exit method and do nothing
        }


        // otherwise calculate total income and update text field
        double totalIncome = wages + loans + other;
        totalIncomeField.setText(String.format("%.2f",totalIncome));  // format with 2 digits after the .
        return totalIncome;
    }

    public double calculateDeficitOrPlus(){
        double food = getTextFieldValue(foodField);
        double rent = getTextFieldValue(rentField);
        double other = getTextFieldValue(otherSpendField);

        // clear total field and return if any value is NaN (error)
        if (Double.isNaN(food) || Double.isNaN(rent) || Double.isNaN(other)) {
            totalIncomeField.setText("");  // clear total income field
            food = 0.0;
            return food;   // exit method and do nothing
        }

        double profit = calculateTotalIncome()-(food+rent+other);
        plusDefField.setText(String.format("%.2f", profit)); // format with 2 digits after the .
        return profit;

    }

    // return the value if a text field as a double
    // --return 0 if field is blank
    // --return NaN if field is not a number
    private double getTextFieldValue(JTextField field) {

        // get value as String from field
        String fieldString = field.getText();  // get text from text field

        if (fieldString.isBlank()) {   // if text field is blank, return 0
            return 0;
        }

        else {  // if text field is not blank, parse it into a double
            try {
                return Double.parseDouble(fieldString);  // parse field number into a double
             } catch (java.lang.NumberFormatException ex) {  // catch invalid number exception
                JOptionPane.showMessageDialog(topLevelFrame, "Please enter a valid number");  // show error message
                return Double.NaN;  // return NaN to show that field is not a number
            }
        }
    }


// below is standard code to set up Swing, which students shouldnt need to edit much
    // standard mathod to show UI
    private static void createAndShowGUI() {
 
        //Create and set up the window.
        JFrame frame = new JFrame("Budget Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        //Create and set up the content pane.
        BudgetBase newContentPane = new BudgetBase(frame);
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);
 
        //Display the window.
        frame.pack();
        frame.setSize(1000, 500); // have default size thats not too small
        frame.setLocationRelativeTo(null); // so it's in the centre of the screen
        frame.setVisible(true);
    }
 
    // standard main class to set up Swing UI
    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI(); 
            }
        });
    }


}