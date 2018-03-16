import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class AgentDelete extends JDialog {
	private boolean isDelete;
	private JTextField textField;
	/**
	 * Create the dialog.
	 */
	public AgentDelete(Agent agnt) {
		isDelete = false;
		setResizable(false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setAlwaysOnTop(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		{
			JButton btnDelete = new JButton("Delete");
			btnDelete.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					isDelete = true;
					dispose();
				}
			});
			btnDelete.setBounds(115, 227, 89, 23);
			getContentPane().add(btnDelete);
		}
		{
			JButton btnCancel = new JButton("Cancel");
			btnCancel.setBounds(224, 227, 89, 23);
			getContentPane().add(btnCancel);
		}
		
		JTextArea txtrWarningYouAre = new JTextArea();
		txtrWarningYouAre.setOpaque(false);
		txtrWarningYouAre.setEditable(false);
		txtrWarningYouAre.setWrapStyleWord(true);
		txtrWarningYouAre.setLineWrap(true);
		txtrWarningYouAre.setText("WARNING! You are about to delete the following Agent:");
		txtrWarningYouAre.setBounds(62, 11, 302, 45);
		getContentPane().add(txtrWarningYouAre);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(115, 67, 175, 20);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JTextArea txtrDeletingThisAgent = new JTextArea();
		txtrDeletingThisAgent.setOpaque(false);
		txtrDeletingThisAgent.setEditable(false);
		txtrDeletingThisAgent.setWrapStyleWord(true);
		txtrDeletingThisAgent.setLineWrap(true);
		txtrDeletingThisAgent.setText("Deleting this Agent cannot be undone. This action is final. Click \"Delete\" to confirm. If you do not wish to delete this agent, click \"Cancel\". ");
		txtrDeletingThisAgent.setBounds(62, 101, 302, 84);
		getContentPane().add(txtrDeletingThisAgent);
	}
	public boolean deleted() {
		return isDelete;
	}

}
