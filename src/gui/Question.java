package gui;

import javax.swing.AbstractListModel;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class Question extends JPanel {
	
    private final JTextArea questionText = new JTextArea();
    private final JList listComments = new JList();
    private final JTextField newComment = new JTextField();
    private final JButton btnNewButton = new JButton("Add a comment");
    private JTextField textField;
    private JLabel label;
    private Number questionID;
	
	public Question() {
		super();
	    questionText.setText("j0kyujnjtrh");
	    listComments.setVisibleRowCount(4);
	    listComments.setModel(new AbstractListModel() {
	    	JTextField[] values = new JTextField[] {};
	    	public int getSize() {
	    		return values.length;
	    	}
	    	public Object getElementAt(int index) {
	    		return values[index];
	    	}
	    });
	    listComments.setEnabled(false);
	    
	    textField = new JTextField();
	    textField.setColumns(10);
	    
	    label = new JLabel("New label");
	    
	    JButton btnNewButton_1 = new JButton("Edit question");
	    btnNewButton_1.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
	    		Main.stack.editQuestion(Main.questionShown, questionText.getText(), textField.getText());
	    		Controller.lblOutput.setText("Edited question");
	    	}
	    });
	    GroupLayout groupLayout = new GroupLayout(this);
	    groupLayout.setHorizontalGroup(
	    	groupLayout.createParallelGroup(Alignment.TRAILING)
	    		.addGroup(groupLayout.createSequentialGroup()
	    			.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
	    				.addGroup(groupLayout.createSequentialGroup()
	    					.addContainerGap()
	    					.addComponent(btnNewButton))
	    				.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
	    					.addGroup(groupLayout.createSequentialGroup()
	    						.addContainerGap()
	    						.addComponent(newComment, GroupLayout.PREFERRED_SIZE, 327, GroupLayout.PREFERRED_SIZE))
	    					.addGroup(groupLayout.createSequentialGroup()
	    						.addGap(50)
	    						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
	    							.addGroup(groupLayout.createSequentialGroup()
	    								.addComponent(textField, GroupLayout.PREFERRED_SIZE, 198, GroupLayout.PREFERRED_SIZE)
	    								.addPreferredGap(ComponentPlacement.UNRELATED)
	    								.addComponent(label, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE)
	    								.addPreferredGap(ComponentPlacement.RELATED)
	    								.addComponent(btnNewButton_1))
	    							.addComponent(questionText, GroupLayout.PREFERRED_SIZE, 379, GroupLayout.PREFERRED_SIZE)
	    							.addComponent(listComments, GroupLayout.PREFERRED_SIZE, 378, GroupLayout.PREFERRED_SIZE)))))
	    			.addGap(73))
	    );
	    btnNewButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
	    		
	    	}
	    });
	    groupLayout.setVerticalGroup(
	    	groupLayout.createParallelGroup(Alignment.LEADING)
	    		.addGroup(groupLayout.createSequentialGroup()
	    			.addContainerGap()
	    			.addComponent(questionText, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)
	    			.addPreferredGap(ComponentPlacement.UNRELATED)
	    			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
	    				.addComponent(textField, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
	    				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
	    					.addComponent(label, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
	    					.addComponent(btnNewButton_1)))
	    			.addPreferredGap(ComponentPlacement.UNRELATED)
	    			.addComponent(listComments, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)
	    			.addGap(18)
	    			.addComponent(newComment, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
	    			.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	    			.addComponent(btnNewButton)
	    			.addContainerGap())
	    );
	    setLayout(groupLayout);
	}
	
	public void setQuestion(StackOverflow.Question question) {
		questionText.setText(question.text);
		textField.setText(question.description);
		label.setText("made on " + question.date + " by " + Main.getUser(question.userID).username);
		
		ArrayList<StackOverflow.Comment> c = Main.getQuestionComments(Main.questionShown);
		for(int i = 0; i < c.size(); i++) {
			Comment com = new Comment();
			com.setValues(c.get(i));
			listComments.add(com);
		}
		if(Main.stack.loggedUser != null && Main.stack.getLoggedID() == question.userID) {
			questionText.setEditable(true);
			textField.setEditable(true);
		}else {
			questionText.setEditable(false);
			textField.setEditable(false);
		}
	}
}
