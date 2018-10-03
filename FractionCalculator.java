
/**
 * Program Name: FractionCalculator.java
 * Purpose: This is a GUI Calculator which does operations on Fractions
 * Coder: Sandeep Singh (ID: 0869722), Mihyeong (Sue) Koh (ID: 0863496)
 * Date: Jul 27, 2018
 */

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;

public class FractionCalculator extends JFrame {

	//Members of the class
	private JLabel numLabel;
	private JLabel denLabel;
	private JTextField numField;
	private JTextField denField;
	private JButton buildFracBtn;
	private JButton startOverBtn;
	private JTextArea fractionArea1;
	private JComboBox operationsBox;
	private JTextArea fractionArea2;
	private JMenuBar menuBar;
	private JMenu about;
	String[] operations = { "Decimal", "Reciprocal", "Fraction1 + Fraction2", "Fraction1 x Fraction2",
			"Is Fraction1 = Fraction2", "Is Fraction 1 > Fraction2", "LowestTerms", "SortList" };

	private final int TEXT_FIELD_WIDTH = 18;
	private final int WIDTH = 900;
	private final int HEIGHT = 500;
	//ArrayList to store the fractions created
	ArrayList<Fraction> listOfFrac = new ArrayList<Fraction>();

	/**
	 * Constructor FranctionCalculator
	 */
	public FractionCalculator() {
		super("Fraction Calculator");

		// Common Code in every GUI app
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(WIDTH, HEIGHT); // Width, height.....change it if you need to
		this.setLocationRelativeTo(null);// center of the screen
		this.setLayout(new GridLayout(1, 4, 10, 0));

		// create components
		CreateTextField();
		CreateButtons();
		CreateFirstPanel();
		CreateTextArea();
		CreateSecondPanel();
		CreateDropDown();
		CreateThirdPanel();
		CreateFourthPanel();
		CreateJMenu();

		// last line of constructor
		this.setVisible(true); // default visibility is false

	}// end constructor

	/**
	 * CreateTextField creates 2 textfields for user input of numerator &
	 * denominator
	 */
	public void CreateTextField() {
		numLabel = new JLabel("Numerator:");
		numField = new JTextField(TEXT_FIELD_WIDTH);

		denLabel = new JLabel("Denominator:");
		denField = new JTextField(TEXT_FIELD_WIDTH);

	}

	/**
	 * CreateButtons creates the buttons to build fraction or to start over
	 */
	public void CreateButtons() {
		buildFracBtn = new JButton("Build Fraction");
		startOverBtn = new JButton("Start Over!");

		buildFracBtn.addActionListener(new BuildFraction());
		startOverBtn.addActionListener(new StartOver());
	}

	/**
	 * CreateFirstPanel creating the first panel to contain the text fields and the
	 * buttons
	 */
	public void CreateFirstPanel() {
		JPanel firstPanel = new JPanel();
		firstPanel.setLayout(new FlowLayout());

		TitledBorder border = new TitledBorder("Enter a Fraction");
		border.setTitlePosition(TitledBorder.ABOVE_TOP);

		firstPanel.setBorder(border);

		//Adding Components to the panel
		firstPanel.add(numLabel);
		firstPanel.add(numField);
		firstPanel.add(denLabel);
		firstPanel.add(denField);
		firstPanel.add(buildFracBtn);
		firstPanel.add(startOverBtn);

		add(firstPanel);
	}

	/**
	 * CreateTextArea creating and setting the text area that will show the
	 * fractions that have been made
	 */
	public void CreateTextArea() {

		fractionArea1 = new JTextArea(20, 15);
		fractionArea1.setEditable(false);

		fractionArea2 = new JTextArea(20, 15);
		fractionArea2.setEditable(false);

		LineBorder border = new LineBorder(Color.BLACK);
		fractionArea1.setBorder(border);
		fractionArea2.setBorder(border);
	}

	/**
	 * CreateSecondPanel creating and adding the text area to show the fractions
	 * that are built
	 * 
	 * @return the panel containing all the fractions being made
	 */
	public void CreateSecondPanel() {
		JPanel secondPanel = new JPanel();
		secondPanel.setLayout(new FlowLayout());
		TitledBorder border = new TitledBorder("Here is your fraction: ");
		border.setTitlePosition(TitledBorder.ABOVE_TOP);

		secondPanel.setBorder(border);

		secondPanel.add(fractionArea1);
		add(secondPanel);
	}

	/**
	 * CreateDropDown create combo box for the operations
	 * 
	 */
	public void CreateDropDown() {
		operationsBox = new JComboBox(operations);

		//ADding Action Listeners
		String selected = (String) operationsBox.getSelectedItem();
		if (selected.equals("Decimal")) {
			operationsBox.addActionListener(new Operations());
		}

	}

	/**
	 * CreateThirdPanel create the panel to contain the combo box of the options of
	 * operations you can do
	 * 
	 * @return combo box of operations
	 */
	public void CreateThirdPanel() {
		JPanel thirdPanel = new JPanel();
		thirdPanel.setLayout(new FlowLayout());
		TitledBorder border = new TitledBorder("Select an operation: ");
		border.setTitlePosition(TitledBorder.ABOVE_TOP);
		thirdPanel.setBorder(border);

		thirdPanel.add(operationsBox);
		add(thirdPanel);
	}

	/**
	 * CreateFourthPanel create the panel to include the text area that will show
	 * the operations
	 */
	public void CreateFourthPanel() {
		JPanel fourthPanel = new JPanel();
		fourthPanel.setLayout(new FlowLayout());
		TitledBorder border = new TitledBorder("Here is your operation: ");
		border.setTitlePosition(TitledBorder.ABOVE_TOP);

		fourthPanel.setBorder(border);

		fourthPanel.add(fractionArea2);
		add(fourthPanel);
	}

	/**
	 * CreateJMenu create a menu that will show various information about the
	 * operations or the developers
	 * 
	 * @return menu bar with menus
	 */
	public void CreateJMenu() {
		menuBar = new JMenuBar();
		about = new JMenu("About");
		JMenu operationsMenu = new JMenu("Operations");
		for (int i = 0; i < operations.length; i++) {
			operationsMenu.add(operations[i]);
		}
		JMenuItem developers = new JMenuItem("Developers");

		//Adding Action Listeners
		developers.addActionListener(new PopupDevelopers());
		about.add(developers);
		menuBar.add(operationsMenu);
		menuBar.add(about);

		this.setJMenuBar(menuBar);

	}

	/**
	 * BuildFraction builds fractions with the user given numerator & denominator
	 * when the build fraction button is clicked
	 * 
	 * @return fractions in the text area
	 */
	class BuildFraction extends Fraction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e)
				throws EmptyOperandException, DenominatorOfZeroException, LongOperandException {

			//Try to do the different operations and if failed then catches the thrown exceptions
			try {
				if (numField.getText() == null || numField.getText().isEmpty() || denField.getText() == null
						|| denField.getText().isEmpty()) {
					throw new EmptyOperandException();
				}

				else if (Long.parseLong(numField.getText()) > Integer.MAX_VALUE
						|| Long.parseLong(denField.getText()) > Integer.MAX_VALUE) {
					throw new LongOperandException();
				} else if (Integer.parseInt(denField.getText()) == 0) {
					throw new DenominatorOfZeroException();
				}

				else {
					int num = Integer.parseInt(numField.getText());

					int den = Integer.parseInt(denField.getText());

					Fraction frac = new Fraction(num, den);
					listOfFrac.add(frac);

					fractionArea1.append(frac.toString() + "\n");
					numField.setText("");
					denField.setText("");
					numField.requestFocus();
				}

				//Catching Various Exceptions
			} catch (DenominatorOfZeroException ex1) {
				JOptionPane.showMessageDialog(null, "You cannot have a denominator of zero!!");
				denField.requestFocus(); //Returns the focus to the field where wrong data is entered
				denField.selectAll();
			} catch (LongOperandException ex3) {
				JOptionPane.showMessageDialog(null, "You have exeeded Max value of int!!");
				if (Long.parseLong(numField.getText()) > Integer.MAX_VALUE) {
					numField.requestFocus();
					numField.selectAll();
				} else if (Long.parseLong(denField.getText()) > Integer.MAX_VALUE) {
					denField.requestFocus();
					numField.selectAll();
				}

			} catch (NumberFormatException ex4) {
				JOptionPane.showMessageDialog(null, "Please Enter an integer!");
				numField.requestFocus();
				numField.selectAll();

			} catch (EmptyOperandException ex) {
				JOptionPane.showMessageDialog(null, "Either the numerantor or denominator box has been left empty!");
				if (numField.getText() == null || numField.getText().isEmpty()) {
					numField.requestFocus();
				} else if (denField.getText() == null || denField.getText().isEmpty()) {
					denField.requestFocus();
				}

			} // end catch
		}// end actionPerformed
	}// end BuildFraction class

	/**
	 * Operations listening for the user's option to perform the operation
	 * 
	 * @return resulting fraction in the textarea in panel 4
	 */
	class Operations extends Fraction implements ActionListener {
		@Override

		public void actionPerformed(ActionEvent e) {

			Object c = (Object) e.getSource();
			int n = listOfFrac.size() - 1;
			if (c == operationsBox) {
				JComboBox cb = (JComboBox) e.getSource();
				String passed = (String) cb.getSelectedItem();
				//Validating
				if (listOfFrac.isEmpty()) {
					JOptionPane.showMessageDialog(null, "You must enter a Fraction to use this operation!");
					numField.requestFocus();

				}

				else if (passed == "Decimal") {

					String result = Double.toString(listOfFrac.get(n).convertToDecimal());
					fractionArea2.append(listOfFrac.get(n) + " is " + result + "\n");
				} // end else if

				else if (passed == "Reciprocal") {

					Fraction tempFrac = listOfFrac.get(n).convertToResiprocal();
					fractionArea2.append("Reciprocal of " + listOfFrac.get(n) + " is " + tempFrac.toString() + "\n");
				} // end else if

				else if (passed == "Fraction1 + Fraction2") {
					//Validating
					if (listOfFrac.size() < 2) {
						JOptionPane.showMessageDialog(null,
								"You must have atleast two Fractions to use this operation!");
						numField.requestFocus();
						operationsBox.setSelectedIndex(0);
						return;
					}

					for (int i = n; i > n - 1; i--) {
						Fraction tempf = listOfFrac.get(i).add(listOfFrac.get(i - 1));
						fractionArea2.append(
								listOfFrac.get(i) + " + " + listOfFrac.get(i - 1) + " = " + tempf.toString() + "\n");
					}
				} // end else if

				else if (passed == "Fraction1 x Fraction2") {
					//Validating
					if (listOfFrac.size() < 2) {
						JOptionPane.showMessageDialog(null,
								"You must have atleast two Fractions to use this operation!");
						numField.requestFocus();
						operationsBox.setSelectedIndex(0);
						return;
					}
					
					for (int i = n; i > n - 1; i--) {
						Fraction tempf = listOfFrac.get(i).multiply(listOfFrac.get(i - 1));
						fractionArea2.append(
								listOfFrac.get(i) + " x " + listOfFrac.get(i - 1) + " = " + tempf.toString() + "\n");
					}
				} // end else if

				else if (passed == "Is Fraction1 = Fraction2") {
					//Validating
					if (listOfFrac.size() < 2) {
						JOptionPane.showMessageDialog(null,
								"You must have atleast two Fractions to use this operation!");
						numField.requestFocus();
						operationsBox.setSelectedIndex(0);
						return;
					}
					
					for (int i = n; i > n - 1; i--) {
						boolean tempf = listOfFrac.get(i).equals(listOfFrac.get(i - 1));
						if (tempf == true) {
							fractionArea2.append(listOfFrac.get(i) + " is equal to " + listOfFrac.get(i - 1) + "\n");
						} else {
							fractionArea2
									.append(listOfFrac.get(i) + " is not equal to " + listOfFrac.get(i - 1) + "\n");
						}

					}
				} // end else if

				else if (passed == "Is Fraction 1 > Fraction2") {
					//Validating
					if (listOfFrac.size() < 2) {
						JOptionPane.showMessageDialog(null,
								"You must have atleast two Fractions to use this operation!");
						numField.requestFocus();
						operationsBox.setSelectedIndex(0);
						return;
					}
					
					for (int i = n; i > n - 1; i--) {
						boolean tempf = listOfFrac.get(i - 1).greaterThan(listOfFrac.get(i));
						if (tempf == true) {
							fractionArea2
									.append(listOfFrac.get(i - 1) + " is not greater than " + listOfFrac.get(i) + "\n");
						} else {
							fractionArea2
									.append(listOfFrac.get(i - 1) + " is greater than " + listOfFrac.get(i) + "\n");
						}

					}
				} // end else if

				else if (passed == "LowestTerms") {

					fractionArea1.setText("");
					for (int i = 0; i < listOfFrac.size(); i++) {

						Fraction tempFrac = listOfFrac.get(i).lowestTerms();
						fractionArea1.append(tempFrac.toString() + "\n");
						fractionArea2.append(listOfFrac.get(i) + " in lowest terms is " + tempFrac.toString() + "\n");
					}
				} // end else if

				else if (passed == "SortList") {
					//Validating
					if (listOfFrac.size() < 2) {
						JOptionPane.showMessageDialog(null,
								"You must have atleast two Fractions to use this operation!");
						numField.requestFocus();
						operationsBox.setSelectedIndex(0);
						return;
					}
					
					ArrayList listOfFrac2 = (ArrayList) listOfFrac.clone();
					Collections.sort(listOfFrac2); //Sort the list
					fractionArea1.setText("");
					fractionArea2.append("Sorted List: \n");
					for (int i = 0; i < listOfFrac2.size(); i++) {
						fractionArea1.append(listOfFrac2.get(i).toString() + "\n");
						fractionArea2.append(listOfFrac2.get(i).toString() + "\n" + "");
					}

				} // end else if
			} // end outer most if
			operationsBox.setSelectedIndex(0); //Make default option of combo box to decimal
		}// end action performed
	}// end Class Operations

	/**
	 * PopupDevelopers using JOptionPane, display the developers
	 * 
	 * @return JOptionPane - names of developers
	 */
	public class PopupDevelopers implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(null,
					"Developers of This Fraction GUI Calculator are: \n     Sandeep Singh and Mihyeong Koh(Sue)");

		}

	}

	/**
	 * StartOver when the start over button is created, the fractions are reset
	 * 
	 * @return empty text fields and area
	 */
	class StartOver implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			numField.setText("");
			denField.setText("");
			fractionArea1.setText("");
			fractionArea2.setText("");
			listOfFrac.clear();
			numField.requestFocus();
		}// end actionPerformed
	}// end AddInterestListener class

	
	
	/**
	 * Entry point for our program
	 */
	public static void main(String[] args) {
		FractionCalculator template = new FractionCalculator();
	}
	// end main
}
// end class