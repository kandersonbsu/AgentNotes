import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JTabbedPane;
import javax.swing.JLayeredPane;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Scanner;

import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import java.awt.Choice;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import java.awt.Font;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;
import java.awt.Desktop;

/**
 * @author kyanderson
 *
 */
public class Main extends JFrame {
	private JPanel contentPane;
	private JTextField agentFirstName;
	private JTextField agentLastName;
	private Agent agent;
	private ArrayList<Agent> agentArray = new ArrayList<Agent>();
	private ArrayList<Note> noteArray;
	private File agentSave = new File("AgentSave.ser");
	private JTextField txtMm;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField agentTxtField;
	private JTextField actionTxtField;
	private JTextField dateOfNoteTxtField;
	private JComboBox<Object> comboBox = new JComboBox<Object>();
	private JComboBox <Object> viewNoteAgentBox = new JComboBox<Object>();
	private DefaultListModel<String> model = new DefaultListModel<>();
	private ImageIcon refresh = new ImageIcon("gtk_refresh.png");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		
		});

	}

	/**
	 * Create the frame.
	 */
	public Main() {
		agentArray = initialize();
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 731, 616);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		JMenuItem mntmReadme = new JMenuItem("README");
		mntmReadme.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				openReadMe();
			}
		});
		mnHelp.add(mntmReadme);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 695, 475);
		contentPane.add(tabbedPane);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBackground(Color.WHITE);
		tabbedPane.addTab("Add Note", null, layeredPane, null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 690, 447);
		layeredPane.add(panel);
		panel.setLayout(null);
		
		comboBox.setBounds(45, 36, 174, 26);
		panel.add(comboBox);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(45, 73, 635, 329);
		panel.add(textArea);
		
		JLabel lblNewLabel = new JLabel("Please Select an Agent");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setBounds(74, 22, 110, 14);
		lblNewLabel.setVisible(false);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Make sure the dates are in the correct format");
		lblNewLabel_1.setForeground(Color.RED);
		lblNewLabel_1.setBounds(406, 22, 274, 14);
		panel.add(lblNewLabel_1);
		lblNewLabel_1.setVisible(false);
		
		JComboBox<Note> comboBox_3 = new JComboBox(NoteType.values());
		comboBox_3.setBounds(292, 36, 100, 26);
		panel.add(comboBox_3);
		
		JButton btnAddNote = new JButton("Add Note");
		btnAddNote.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
				NoteType noteType = NoteType.valueOf(comboBox_3.getSelectedItem().toString());
				agent = findAgent(comboBox.getSelectedItem().toString());
				agent.addNote(new Note(textArea.getText(),noteType,new Date(Integer.parseInt(txtMm.getText()),Integer.parseInt(textField_1.getText()), Integer.parseInt(textField_2.getText()))));
				System.out.println(agent.getNote(0));
				textField_2.setText("YYYY");
				textField_2.setForeground(Color.gray);
				textField_1.setText("DD");
				textField_1.setForeground(Color.gray);
				txtMm.setText("MM");
				txtMm.setForeground(Color.gray);
				textArea.setText(null);
				lblNewLabel.setVisible(false);
				lblNewLabel_1.setVisible(false);
				}catch(NullPointerException n) {
					lblNewLabel.setVisible(true);
				}
				catch(NumberFormatException e) {
					lblNewLabel_1.setVisible(true);
				}
			}
		});
		btnAddNote.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				
				textArea.setText(null);
			}
		});
		btnAddNote.setBounds(243, 413, 89, 23);
		panel.add(btnAddNote);
		
		JButton btnClearNote = new JButton("Clear Note");
		btnClearNote.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textArea.setText(null);
			}
		});
		btnClearNote.setBounds(363, 413, 100, 23);
		panel.add(btnClearNote);
		
		JLabel lblMonth = new JLabel("Month:");
		lblMonth.setBounds(406, 42, 46, 14);
		panel.add(lblMonth);
		
		txtMm = new JTextField();
		txtMm.setHorizontalAlignment(SwingConstants.CENTER);
		if(txtMm.getText().length() == 0) {
			txtMm.setText("MM");
			txtMm.setForeground(Color.GRAY);
		}
		txtMm.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent arg0) {
				txtMm.setText(null);
				txtMm.setForeground(Color.BLACK);
				
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				if(txtMm.getText().length() == 0) {
					txtMm.setText("MM");
					txtMm.setForeground(Color.gray);
				}
				
			}
		});
		txtMm.setBounds(443, 39, 34, 20);
		panel.add(txtMm);
		txtMm.setColumns(10);
		
		JLabel lblDay = new JLabel("Day:");
		lblDay.setBounds(487, 42, 46, 14);
		panel.add(lblDay);
		
		textField_1 = new JTextField();
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setBounds(511, 39, 34, 20);
		panel.add(textField_1);
		textField_1.setColumns(10);
		if(textField_1.getText().length() == 0) {
			textField_1.setText("DD");
			textField_1.setForeground(Color.GRAY);
		}
		textField_1.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent arg0) {
				textField_1.setText(null);
				textField_1.setForeground(Color.BLACK);
				
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				if(textField_1.getText().length() == 0) {
					textField_1.setText("DD");
					textField_1.setForeground(Color.gray);
				}
				
			}
		});
		
		JLabel lblYear = new JLabel("Year:");
		lblYear.setBounds(551, 42, 46, 14);
		panel.add(lblYear);
		
		textField_2 = new JTextField();
		textField_2.setHorizontalAlignment(SwingConstants.CENTER);
		textField_2.setBounds(581, 39, 46, 20);
		panel.add(textField_2);
		textField_2.setColumns(10);

		
		if(textField_2.getText().length() == 0) {
			textField_2.setText("YYYY");
			textField_2.setForeground(Color.GRAY);
		}
		textField_2.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent arg0) {
				textField_2.setText(null);
				textField_2.setForeground(Color.BLACK);
				
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				if(textField_2.getText().length() == 0) {
					textField_2.setText("YYYY");
					textField_2.setForeground(Color.gray);
				}
				
			}
		});
		
		JComboBox dateBox = new JComboBox();
		dateBox.setBounds(403, 36, 156, 28);
		

		
		JPanel panel_1 = new JPanel();
		panel_1.add(dateBox);
		tabbedPane.addTab("View Notes", null, panel_1, null);
		panel_1.setLayout(null);
		
		
		JTextPane textPane = new JTextPane();
		textPane.setEditable(false);
		textPane.setBounds(113, 221, 448, 172);
		panel_1.add(textPane);

		viewNoteAgentBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
				agent = findAgent(viewNoteAgentBox.getSelectedItem().toString());
				}catch(NullPointerException np){
			
				}
				dateBox.removeAllItems();
				try {
				for(Note note : agent.getNoteArray()) {
					dateBox.addItem(note.getDate());
				}
				}catch(NullPointerException np) {
					
				}
				agentTxtField.setText(null);
				actionTxtField.setText(null);
				dateOfNoteTxtField.setText(null);
				textPane.setText(null);
			}
		});
		viewNoteAgentBox.setBounds(113, 36, 188, 28);
		panel_1.add(viewNoteAgentBox);
		
		actionTxtField = new JTextField();
		actionTxtField.setEditable(false);
		actionTxtField.setBounds(113, 146, 86, 20);
		panel_1.add(actionTxtField);
		actionTxtField.setColumns(10);
		
		JButton btnShowNotes = new JButton("Show Notes");
		btnShowNotes.addActionListener(new ActionListener() {
		
			public void actionPerformed(ActionEvent e) {
				try {
				agent = findAgent(viewNoteAgentBox.getSelectedItem().toString());
				Note note = agent.getNoteObject(dateBox.getSelectedItem().toString());
				agentTxtField.setText(viewNoteAgentBox.getSelectedItem().toString());
				dateOfNoteTxtField.setText(dateBox.getSelectedItem().toString());
				actionTxtField.setText(note.getNoteType().toString());
				textPane.setText(agent.getNote(dateBox.getSelectedItem().toString()));
				}catch(NullPointerException npe) {
					
				}
			}
		});
		btnShowNotes.setBounds(222, 413, 108, 23);
		panel_1.add(btnShowNotes);

		
		JLabel lblSelectTheDate = new JLabel("Select the Date");
		lblSelectTheDate.setBounds(311, 43, 108, 14);
		panel_1.add(lblSelectTheDate);
		
		JLabel lblSelectTheAgent = new JLabel("Select the Agent");
		lblSelectTheAgent.setBounds(10, 43, 93, 14);
		panel_1.add(lblSelectTheAgent);
		
		JLabel lblAgentName = new JLabel("Agent Name:");
		lblAgentName.setBounds(10, 124, 79, 14);
		panel_1.add(lblAgentName);
		
		agentTxtField = new JTextField();
		agentTxtField.setEditable(false);
		agentTxtField.setBounds(113, 121, 86, 20);
		panel_1.add(agentTxtField);
		agentTxtField.setColumns(10);
		
		JLabel lblAction = new JLabel("Action:");
		lblAction.setBounds(10, 149, 57, 14);
		panel_1.add(lblAction);
		
		JLabel lblDateOfNote = new JLabel("Date of Note:");
		lblDateOfNote.setBounds(10, 174, 79, 14);
		panel_1.add(lblDateOfNote);
		
		dateOfNoteTxtField = new JTextField();
		dateOfNoteTxtField.setEditable(false);
		dateOfNoteTxtField.setBounds(113, 171, 86, 20);
		panel_1.add(dateOfNoteTxtField);
		dateOfNoteTxtField.setColumns(10);
		
		JButton btnNewButton = new JButton("+");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Font font = textPane.getFont();
				float textSize = font.getSize();
				textSize ++;
				textPane.setFont(font.deriveFont(textSize));
			}
		});
		btnNewButton.setBounds(398, 413, 42, 23);
		panel_1.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("-");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Font font = textPane.getFont();
				float textSize = font.getSize();
				textSize --;
				textPane.setFont(font.deriveFont(textSize));
			}
		});
		btnNewButton_1.setBounds(450, 413, 42, 23);
		panel_1.add(btnNewButton_1);
		
		JLabel lblFontSize = new JLabel("Font Size:");
		lblFontSize.setBounds(337, 417, 57, 14);
		panel_1.add(lblFontSize);

		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Manage Agents", null, panel_2, null);
		panel_2.setLayout(null);
		
		JLabel lblFirstName = new JLabel("First Name:");
		lblFirstName.setBounds(36, 42, 81, 14);
		panel_2.add(lblFirstName);
		
		JLabel lblLastName = new JLabel("Last Name:");
		lblLastName.setBounds(36, 67, 81, 14);
		panel_2.add(lblLastName);
		
		JLabel lblLead = new JLabel("Lead:");
		lblLead.setBounds(36, 92, 46, 14);
		panel_2.add(lblLead);
		
		agentFirstName = new JTextField();
		agentFirstName.setBounds(110, 39, 86, 20);
		panel_2.add(agentFirstName);
		agentFirstName.setColumns(10);
		
		agentLastName = new JTextField();
		agentLastName.setBounds(110, 64, 86, 20);
		panel_2.add(agentLastName);
		agentLastName.setColumns(10);
		
		JComboBox comboBox_2 = new JComboBox(Lead.values());
		comboBox_2.setBounds(110, 89, 86, 20);
		panel_2.add(comboBox_2);
		
		JList<String> agentList = new JList<String>(model);
		agentList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		agentList.setBounds(392, 41, 270, 302);
		panel_2.add(agentList);
		
		JButton btnCreateAgent = new JButton("Create Agent");
		btnCreateAgent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(agentFirstName.getText().isEmpty() || agentLastName.getText().isEmpty()) {
					System.out.println("Test");
				}
				else {
				Lead lead = Lead.valueOf(comboBox_2.getSelectedItem().toString());
				agentArray.add(new Agent(agentFirstName.getText(), agentLastName.getText(),lead));
				agentFirstName.setText(null);
				agentLastName.setText(null);
				comboBox.removeAllItems();
				viewNoteAgentBox.removeAllItems();
				model.removeAllElements();
				comboBox.addItem("Select an Agent");
				viewNoteAgentBox.addItem("Select an Agent");
				for(Agent agnt: agentArray) {
					comboBox.addItem(agnt.getFullName());
					viewNoteAgentBox.addItem(agnt.getFullName());
					model.addElement(agnt.getFullName());
				}
				System.out.println(agentArray);
				}
			}
		});
		btnCreateAgent.setBounds(28, 152, 127, 23);
		panel_2.add(btnCreateAgent);
		
		JButton btnEditAgent = new JButton("Edit");
		btnEditAgent.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try{
				Agent agnt = findAgent(agentList.getSelectedValue());
				AgentEditWindow window = new AgentEditWindow(agnt);
				window.setVisible(true);
				window.setLocation(300,300);
				}catch(NullPointerException np) {
					
				}
			}
		});
		btnEditAgent.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnEditAgent.setBounds(435, 361, 86, 23);
		panel_2.add(btnEditAgent);
		
		JButton btnRemoveAgent = new JButton("Delete");
		btnRemoveAgent.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					Agent agnt = findAgent(agentList.getSelectedValue());
					int i = findAgentPosition(agnt.getFullName());
					JDialog agntDelete = new JDialog();
					agntDelete.setResizable(false);
					agntDelete.setVisible(true);
					agntDelete.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					agntDelete.setAlwaysOnTop(true);
					agntDelete.setBounds(100, 100, 450, 300);
					agntDelete.getContentPane().setLayout(null);
					{
						JButton btnDelete = new JButton("Delete");
						btnDelete.addMouseListener(new MouseAdapter() {
							@Override
							public void mouseClicked(MouseEvent e) {
								agentArray.remove(i);
								agntDelete.dispose();
							}
						});
						btnDelete.setBounds(115, 227, 89, 23);
						agntDelete.getContentPane().add(btnDelete);
					}
					{
						JButton btnCancel = new JButton("Cancel");
						btnCancel.addMouseListener(new MouseAdapter() {
							@Override
							public void mouseClicked(MouseEvent e) {
								agentArray.remove(i);
								agntDelete.dispose();
							}
						});
						btnCancel.setBounds(224, 227, 89, 23);
						agntDelete.getContentPane().add(btnCancel);
					}
					JTextArea txtrWarningYouAre = new JTextArea();
					txtrWarningYouAre.setOpaque(false);
					txtrWarningYouAre.setEditable(false);
					txtrWarningYouAre.setWrapStyleWord(true);
					txtrWarningYouAre.setLineWrap(true);
					txtrWarningYouAre.setText("WARNING! You are about to delete the following Agent:");
					txtrWarningYouAre.setBounds(62, 11, 302, 45);
					agntDelete.getContentPane().add(txtrWarningYouAre);
					
					JTextField textField = new JTextField(agnt.getFullName());
					textField.setEditable(false);
					textField.setBounds(115, 67, 175, 20);
					agntDelete.getContentPane().add(textField);
					textField.setColumns(10);
					
					JTextArea txtrDeletingThisAgent = new JTextArea();
					txtrDeletingThisAgent.setOpaque(false);
					txtrDeletingThisAgent.setEditable(false);
					txtrDeletingThisAgent.setWrapStyleWord(true);
					txtrDeletingThisAgent.setLineWrap(true);
					txtrDeletingThisAgent.setText("Deleting this Agent cannot be undone. This action is final. Click \"Delete\" to confirm. If you do not wish to delete this agent, click \"Cancel\". ");
					txtrDeletingThisAgent.setBounds(62, 101, 302, 84);
					agntDelete.getContentPane().add(txtrDeletingThisAgent);
					
				}catch(NullPointerException npe) {
					
				}
			}
		});
		btnRemoveAgent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnRemoveAgent.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnRemoveAgent.setBounds(546, 361, 89, 23);
		panel_2.add(btnRemoveAgent);
		panel_2.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{agentFirstName, agentLastName, lblFirstName, lblLastName, lblLead, comboBox_2, btnCreateAgent, btnEditAgent, btnRemoveAgent, agentList}));
		
		JButton btnSaveAndClose = new JButton("Save and Close");
		btnSaveAndClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				save(agentArray);
				System.out.println("Saved");
				System.exit(0);
			}
		});
		btnSaveAndClose.setBounds(200, 522, 127, 23);
		contentPane.add(btnSaveAndClose);
		
		JButton btnExitWithoutSaving = new JButton("Exit");
		btnExitWithoutSaving.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
			}
		});
		btnExitWithoutSaving.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnExitWithoutSaving.setBounds(358, 522, 102, 23);
		contentPane.add(btnExitWithoutSaving);
		
		JButton btnRefresh = new JButton(new ImageIcon("gtk_refresh.png"));
		btnRefresh.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				comboBox.removeAllItems();
				viewNoteAgentBox.removeAllItems();
				model.removeAllElements();
				comboBox.addItem("Select an Agent");
				viewNoteAgentBox.addItem("Select an Agent");
				for(Agent agnt: agentArray) {
					comboBox.addItem(agnt.getFullName());
					viewNoteAgentBox.addItem(agnt.getFullName());
					model.addElement(agnt.getFullName());
				}
			}
		});
		btnRefresh.setBounds(470, 522, 89, 23);
		contentPane.add(btnRefresh);
	}
	
	/**
	 * Takes a string of an agent's full name and returns the Agent object
	 * in the ArrayList of agents. 
	 * @param a
	 * @return
	 */
	public Agent findAgent(String a) {
		Agent agent = null;
		for(Agent agnt : agentArray) {
			if(agnt.getFullName().equals(a)) {
				agent = agnt;
			}
		}
		return agent;
	}
	/**
	 * Takes an agent Full Name and returns an int value
	 * for their position in the array.
	 * @param a
	 * @return
	 */
	public int findAgentPosition(String a) {
		int i = 0;
		int found = 0;
		for(Agent agnt : agentArray) {
			if(agnt.getFullName().equals(a)) {
				found = i;
			}
			i++;
		}
		return found;
	}
	/**
	 * Method loops through the ArrayList of agents saving
	 * each agent object into the AgentSave.ser file. This 
	 * method is called when the user wishes to save their
	 * work. 
	 */
	public void save(ArrayList<Agent> a){
		try {
			ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream("AgentSave.ser"));
			for(Agent agnt : a) {
				o.writeObject(agnt);
			}
			o.close();
		}catch(FileNotFoundException e) {
			System.out.println("File Not Found");
			
		}
		catch(IOException e) {
			e.printStackTrace();
			
		}
	}
	/**
	 * Initializes the ArrayList of Agents based on the saved
	 * information in AgentSave.ser. This method is called when
	 * the program is run. 
	 * @return
	 */
	public ArrayList<Agent> initialize() {
		try {
			Agent agent;
			agentArray.removeAll(agentArray);
			ObjectInputStream oi = new ObjectInputStream(new FileInputStream("AgentSave.ser"));
			
			while((agent = (Agent) oi.readObject()) != null) {
				agentArray.add(agent);
			}
			oi.close();
		}catch(FileNotFoundException e) {
			
		}catch(IOException e) {
			
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		comboBox.removeAllItems();
		viewNoteAgentBox.removeAllItems();
		model.removeAllElements();
		comboBox.addItem("Select an Agent");
		viewNoteAgentBox.addItem("Select an Agent");
		for(Agent agnt: agentArray) {
			comboBox.addItem(agnt.getFullName());
			viewNoteAgentBox.addItem(agnt.getFullName());
			model.addElement(agnt.getFullName());
		}
		return agentArray;
		
	}
	public void openReadMe() {
		try {
			if(Desktop.getDesktop().isSupported(Desktop.Action.OPEN)) {
				Desktop.getDesktop().open(new File("README"));
			}
		} catch(IOException io) {
			io.printStackTrace();
		}
	}
	public ArrayList<Agent> getAgentArray(){
		return this.agentArray;
	}
	public void setAgentArray(ArrayList<Agent> a) {
		agentArray = a;
	}
	/**
	 * @param fn - First name to search the array with
	 * @param ln - Last name to search the array with
	 * @param newFirst - The new first name to replace in the array
	 * @param newLast - The new last name to replace in the array
	 * @param lead - the Enum lead to change in the array. 
	 * Searches the array using the first name and last name entered and changes
	 * that agent with the second set of first name and last name entered as well
	 * as the lead entered. 
	 */
	public void editAgent(String fn, String ln, String newFirst, String newLast, Lead lead) {
		int i = findAgentPosition(fn + " " + ln);
		agentArray.get(i).setFirstName(newFirst);
		agentArray.get(i).setLastName(newLast);
		agentArray.get(i).setLead(lead);
		save(agentArray);
	}
}
