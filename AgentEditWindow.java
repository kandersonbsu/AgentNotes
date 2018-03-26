import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class AgentEditWindow extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private AgentEditWindow frame;

	/**
	 * Create the frame.
	 */
	public AgentEditWindow(Agent agnt) {
		setTitle("Edit Agent");
		setResizable(false);
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 241, 258);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblFirstName = new JLabel("First Name:");
		lblFirstName.setBounds(29, 45, 72, 14);
		contentPane.add(lblFirstName);
		
		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setBounds(29, 70, 72, 14);
		contentPane.add(lblLastName);
		
		JLabel lblLead = new JLabel("Lead:");
		lblLead.setBounds(29, 94, 72, 14);
		contentPane.add(lblLead);
		
		textField = new JTextField(agnt.getFirstName());
		textField.setBounds(111, 42, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField(agnt.getLastName());
		textField_1.setBounds(111, 67, 86, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JComboBox comboBox = new JComboBox(Lead.values());
		comboBox.setBounds(111, 91, 86, 20);
		contentPane.add(comboBox);
		
		JButton btnSave = new JButton("Save");
		btnSave.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			try {
				agnt.setFirstName(textField.getText());
				agnt.setLastName(textField_1.getText());
				agnt.setLead(Lead.valueOf(comboBox.getSelectedItem().toString()));
				dispose();
				
			}catch(NullPointerException npe) {
				
			}
			}
		});
		btnSave.setBounds(10, 195, 89, 23);
		contentPane.add(btnSave);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				dispose();
			}
		});
		btnCancel.setBounds(136, 195, 89, 23);
		contentPane.add(btnCancel);
	}
	
	public void editAgent(Main m, String fn, String ln, String newFirst, String newLast, Lead lead) {
		m.editAgent(fn, ln, newFirst, newLast, lead);
	}
}
