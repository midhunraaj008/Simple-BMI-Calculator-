package bmi;

import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import javax.swing.*;
public class BMI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JTextField heightField;
	JTextField weightField;
	DecimalFormat fmt = new DecimalFormat("0.00");
	JLabel result,category;

	public BMI() {
		super("BMI Calculator");
		setLayout(new FlowLayout());
		JLabel heightLable = new JLabel("Height (cm) :");
		add(heightLable);
		heightField = new JTextField(4);
		add(heightField);
		JLabel weightLable = new JLabel("Weight (kg) :");
		add(weightLable);
		weightField = new JTextField(4);
		add(weightField);
		final JButton submitButton = new JButton("  Calculate BMI  ");
		submitButton.setForeground(Color.BLUE);
		submitButton.addActionListener(new Compute());
		submitButton.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyChar() == KeyEvent.VK_ENTER) {
					submitButton.doClick();
				}
			}

			public void keyReleased(KeyEvent e) { /* ... */
			}

			public void keyTyped(KeyEvent e) { /* ... */
			}
		});
		add(submitButton);

		final JButton reset = new JButton("    Reset    ");
		reset.setForeground(Color.RED);
		reset.addActionListener(new Reset());
		reset.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					reset.doClick();
				}
			}
			public void keyReleased(KeyEvent e) { /* ... */
			}

			public void keyTyped(KeyEvent e) { /* ... */
			}
		});
		add(reset);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(180, 250);
		setForeground(Color.DARK_GRAY);
		setVisible(true);
		setLocation(420, 200);
		result = new JLabel("");
		add(result);
		category = new JLabel("");
		add(category);

	}

	class Reset implements ActionListener {
		public void actionPerformed(ActionEvent ave) {
			resetFields();
			result.setText("");
			heightField.grabFocus();
		}
	}

	public void resetFields() {
		weightField.setText("");
		heightField.setText("");
	}

	class Compute implements ActionListener {
		public void actionPerformed(ActionEvent ave) {
			StringBuilder S = new StringBuilder();
			double bmi;
			if (weightField.getText().trim().equals("")) {
				S.append("Enter Weight \n");
			}
			if (heightField.getText().trim().equals("")) {
				S.append("Enter Height \n");
			}
			if (S.toString().trim().equals("")) {
				double weightFieldValue = Double.parseDouble(weightField.getText());
				double heightFieldValue = Double.parseDouble(heightField.getText());
				bmi = Math.round(weightFieldValue/ (heightFieldValue / 100 * heightFieldValue / 100));
				result.setText("Calculated BMI: " + bmi);
				category.setText(getCategory(bmi));
			} else {
				resetFields();
				result.setText(S.toString());
			}

		}
		
		public String getCategory (double result) {
			String category;
			if (result < 15) {
				category = "Very Severely Underweight";
			} else if (result >=15 && result <= 16) {
				category = "Severely Underweight";
			} else if (result >16 && result <= 18.5) {
				category = "Underweight";
			} else if (result >18.5 && result <= 25) {
				category = "Normal Weight";
			} else if (result >25 && result <= 30) {
				category = "Overweight";
			} else if (result >30 && result <= 35) {
				category = "Moderately Obese";
			} else if (result >35 && result <= 40) {
				category = "Severely Obese";
			} else {
				category ="Very Severely Obese";
			}
			return category;
		}
	}

	public static void main(String[] args) {
		new BMI();
	}

}
