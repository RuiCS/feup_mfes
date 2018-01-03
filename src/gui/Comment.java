package gui;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;

public class Comment extends JPanel {
	private JTextField textField;
	private JLabel lbl = new JLabel();
	private JButton btnEditComment = new JButton("Edit comment");
	private Number questionID, answerID;
	public Comment() {
		
		textField = new JTextField();
		add(textField);
		textField.setColumns(10);
		add(lbl); 
		add(btnEditComment);
	}
	
	public void setValues(StackOverflow.Comment comment) {
		textField.setText(comment.text);
		lbl.setText("made on " + comment.date + " by " + Main.getUser(comment.userID).username);
		questionID = comment.questionID;
		answerID = comment.answerID;
	}
}
