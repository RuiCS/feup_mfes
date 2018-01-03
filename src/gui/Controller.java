package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class Controller extends JFrame  {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel homePanel = new JPanel();
    private final JPanel mainPanel = new JPanel();
    private final JPanel headerPanel = new JPanel();
    private final JPanel questionPanel = new JPanel();
    private final JTextField textField = new JTextField();
    private final JPanel question = new JPanel();
    private final JPanel bodyPanel = new JPanel();
    private final JPanel likes = new JPanel();
    private final Question text = new Question();
    private final JButton upvote = new JButton("Like");
    private final JLabel numLikes = new JLabel("0");
    private final JButton downvote = new JButton("Dislike");
    private final JPanel searchPanel = new JPanel();
    private final JPanel accountPanel = new JPanel();
    private final JPanel visitorPanel = new JPanel();
    private final JPanel userPanel = new JPanel();
    private final JTextField usernameText = new JTextField();
    private final JTextField passwordText = new JTextField();
    private final JButton registerButton = new JButton("Register");
    private final JButton loginButton = new JButton("Login");
    private final JButton createQuestionButton = new JButton("Create question");
    private final JTextField answerQuestion = new JTextField();
    private final JButton answerButton = new JButton("Answer question");
    private final JScrollPane scrollPane = new JScrollPane();
    private final JButton btnLogout = new JButton("Logout");
    private final JTextField newQuestionCategory = new JTextField();
    private final JTextField newQuestionDescription = new JTextField();
    private final JLabel lblAddDescription = new JLabel("Add description");
    private final JLabel lblAddCategory = new JLabel("Add category");
    private final JPanel createQuestionPanel = new JPanel();
    private final JScrollPane questionList = new JScrollPane();
    public static final JLabel lblOutput = new JLabel("");
    private final JButton btnNewQuestion = new JButton("New Question");


    public Controller() {
    	btnNewQuestion.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent arg0) {
    			CardLayout cl = (CardLayout)(bodyPanel.getLayout());
        	    cl.show(bodyPanel, "homePanel");
    		}
    	});
    	newQuestionDescription.setText("Description");
    	newQuestionDescription.setColumns(10);
    	newQuestionCategory.setColumns(10);
    	passwordText.setColumns(10);
    	usernameText.setColumns(10);
    	textField.setColumns(10);
    	setSize(1280, 720);  
        init();
        setVisible(true);  
    }

    public void init() {
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      layOutComponents();
      // Connect model and view
   }
    
    private void layOutComponents() {
        getContentPane().setLayout(new BorderLayout());
        
        getContentPane().add(mainPanel, BorderLayout.CENTER);
        mainPanel.setLayout(new BorderLayout(0, 0));
        
        JButton btnSearch = new JButton("Search");
        btnSearch.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		String search = textField.getText();
        		StackOverflow.Question q = Main.getQuestion(search);
        		if(q != null) {
        			lblOutput.setText("Found a question.");
        			
        			
        			CardLayout cl = (CardLayout)(bodyPanel.getLayout());
            	    cl.show(bodyPanel, "questionPanel");
            	    
            	    text.setQuestion(q);
        		}
        		
        	}
        });
        
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        headerPanel.setLayout(new BorderLayout(0, 0));
        
        headerPanel.add(searchPanel, BorderLayout.CENTER);
        GroupLayout gl_searchPanel = new GroupLayout(searchPanel);
        gl_searchPanel.setHorizontalGroup(
        	gl_searchPanel.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_searchPanel.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(btnNewQuestion)
        			.addGap(225)
        			.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        			.addGap(5)
        			.addComponent(btnSearch))
        );
        gl_searchPanel.setVerticalGroup(
        	gl_searchPanel.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_searchPanel.createSequentialGroup()
        			.addGap(6)
        			.addGroup(gl_searchPanel.createParallelGroup(Alignment.BASELINE)
        				.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(btnNewQuestion)))
        		.addGroup(gl_searchPanel.createSequentialGroup()
        			.addGap(5)
        			.addComponent(btnSearch))
        );
        searchPanel.setLayout(gl_searchPanel);
        
        headerPanel.add(accountPanel, BorderLayout.EAST);
        accountPanel.setLayout(new CardLayout(0, 0));
        
        accountPanel.add(visitorPanel, "visitorPanel");
        
        visitorPanel.add(usernameText);
        
        visitorPanel.add(passwordText);
        registerButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		try {
        			String user = usernameText.getText();
        			String pass = passwordText.getText();
        			Main.stack.signup(user, pass);
        			lblOutput.setText("Registered successfully. Please login with your new account");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	}
        });
        
        visitorPanel.add(registerButton);
        loginButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		try {
        			String user = usernameText.getText();
        			String pass = passwordText.getText();
        			Main.stack.login(user, pass);
        			if(Main.stack.getLoggedID() != null) {
        				CardLayout cl = (CardLayout)(accountPanel.getLayout());
                	    cl.show(accountPanel, "userPanel");
                	    lblOutput.setText("You are now logged in. Welcome " + user);
            			createQuestionPanel.setVisible(true);
        			}
        		}catch(IllegalArgumentException e) {
        			e.printStackTrace();
        		}
    				
        	}
        });
        
        visitorPanel.add(loginButton);
        
        accountPanel.add(userPanel, "userPanel");
        btnLogout.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		Main.stack.logout();
        		if(Main.stack.loggedUser == null) {
    				CardLayout cl = (CardLayout)(accountPanel.getLayout());
            	    cl.show(accountPanel, "visitorPanel");
            	    lblOutput.setText("Logged out");
        			createQuestionPanel.setVisible(false);
        		}
        	}
        });
        
        userPanel.add(btnLogout);
        
        Component horizontalStrut = Box.createHorizontalStrut(250);
        mainPanel.add(horizontalStrut, BorderLayout.WEST);
        
        Component horizontalStrut_1 = Box.createHorizontalStrut(250);
        mainPanel.add(horizontalStrut_1, BorderLayout.EAST);
    	lblOutput.setHorizontalAlignment(SwingConstants.CENTER);
    	
    	mainPanel.add(lblOutput, BorderLayout.SOUTH);
    	
    	mainPanel.add(bodyPanel, BorderLayout.CENTER);
    	bodyPanel.setLayout(new CardLayout(0, 0));
    	bodyPanel.add(homePanel, "homePanel");
    	homePanel.setLayout(new BoxLayout(homePanel, BoxLayout.PAGE_AXIS));
    	homePanel.add(createQuestionPanel);
    	createQuestionPanel.setVisible(false);
    	
    	JLabel lblNewQuestion = new JLabel("New question");
    	
    	JTextArea newQuestionText = new JTextArea();
    	newQuestionText.setText("Insert question here");
    	GroupLayout gl_createQuestionPanel = new GroupLayout(createQuestionPanel);
    	gl_createQuestionPanel.setHorizontalGroup(
    		gl_createQuestionPanel.createParallelGroup(Alignment.LEADING)
    			.addGroup(gl_createQuestionPanel.createSequentialGroup()
    				.addGap(22)
    				.addGroup(gl_createQuestionPanel.createParallelGroup(Alignment.TRAILING)
    					.addComponent(lblAddDescription)
    					.addComponent(lblAddCategory)
    					.addComponent(lblNewQuestion))
    				.addGap(18)
    				.addGroup(gl_createQuestionPanel.createParallelGroup(Alignment.LEADING)
    					.addComponent(newQuestionDescription, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
    					.addComponent(newQuestionCategory, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
    					.addGroup(gl_createQuestionPanel.createParallelGroup(Alignment.TRAILING)
    						.addComponent(createQuestionButton)
    						.addComponent(newQuestionText, GroupLayout.PREFERRED_SIZE, 523, GroupLayout.PREFERRED_SIZE)))
    				.addGap(111))
    	);
    	gl_createQuestionPanel.setVerticalGroup(
    		gl_createQuestionPanel.createParallelGroup(Alignment.LEADING)
    			.addGroup(gl_createQuestionPanel.createSequentialGroup()
    				.addGroup(gl_createQuestionPanel.createParallelGroup(Alignment.LEADING)
    					.addGroup(gl_createQuestionPanel.createSequentialGroup()
    						.addGap(29)
    						.addComponent(lblNewQuestion))
    					.addGroup(gl_createQuestionPanel.createSequentialGroup()
    						.addContainerGap()
    						.addComponent(newQuestionText, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)))
    				.addPreferredGap(ComponentPlacement.UNRELATED)
    				.addGroup(gl_createQuestionPanel.createParallelGroup(Alignment.BASELINE)
    					.addComponent(lblAddDescription)
    					.addComponent(newQuestionDescription, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
    				.addGap(18)
    				.addGroup(gl_createQuestionPanel.createParallelGroup(Alignment.BASELINE)
    					.addComponent(lblAddCategory)
    					.addComponent(newQuestionCategory, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
    				.addPreferredGap(ComponentPlacement.RELATED)
    				.addComponent(createQuestionButton)
    				.addContainerGap(472, Short.MAX_VALUE))
    	);
    	createQuestionPanel.setLayout(gl_createQuestionPanel);
    	homePanel.add(questionList);
    	createQuestionButton.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent arg0) {
    			String question = newQuestionText.getText();
    			String description = newQuestionDescription.getText();
    			String category = newQuestionCategory.getText();
    			if(Main.stack.loggedUser != null) {
    				Main.stack.askQuestion(question, description, category);
    				Main.stack.printQuestions();
    				lblOutput.setText("Question created successfully");
    			}else
    				lblOutput.setText("You need to have an account to ask a question");
    		}
    	});
    	bodyPanel.add(questionPanel, "questionPanel");
    	likes.setAlignmentY(Component.TOP_ALIGNMENT);
    	likes.setLayout(new BoxLayout(likes, BoxLayout.PAGE_AXIS));
    	upvote.setAlignmentX(Component.CENTER_ALIGNMENT);
    	
    	likes.add(upvote);
    	
    	likes.add(numLikes);
    	downvote.setAlignmentX(Component.CENTER_ALIGNMENT);
    	
    	likes.add(downvote);
    	GroupLayout gl_questionPanel = new GroupLayout(questionPanel);
    	gl_questionPanel.setHorizontalGroup(
    		gl_questionPanel.createParallelGroup(Alignment.LEADING)
    			.addGroup(gl_questionPanel.createSequentialGroup()
    				.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 762, GroupLayout.PREFERRED_SIZE)
    				.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    			.addGroup(Alignment.TRAILING, gl_questionPanel.createSequentialGroup()
    				.addContainerGap(525, Short.MAX_VALUE)
    				.addComponent(answerButton)
    				.addGap(110))
    			.addGroup(Alignment.TRAILING, gl_questionPanel.createSequentialGroup()
    				.addContainerGap(105, Short.MAX_VALUE)
    				.addComponent(answerQuestion, GroupLayout.PREFERRED_SIZE, 571, GroupLayout.PREFERRED_SIZE)
    				.addGap(86))
    			.addGroup(gl_questionPanel.createSequentialGroup()
    				.addComponent(question, GroupLayout.PREFERRED_SIZE, 762, GroupLayout.PREFERRED_SIZE)
    				.addContainerGap())
    	);
    	gl_questionPanel.setVerticalGroup(
    		gl_questionPanel.createParallelGroup(Alignment.LEADING)
    			.addGroup(gl_questionPanel.createSequentialGroup()
    				.addComponent(question, GroupLayout.PREFERRED_SIZE, 319, GroupLayout.PREFERRED_SIZE)
    				.addGap(204)
    				.addComponent(answerQuestion, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
    				.addGap(18)
    				.addComponent(answerButton)
    				.addGap(32)
    				.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
    	);
    	GroupLayout gl_question = new GroupLayout(question);
    	gl_question.setHorizontalGroup(
    		gl_question.createParallelGroup(Alignment.LEADING)
    			.addGroup(gl_question.createSequentialGroup()
    				.addContainerGap()
    				.addComponent(likes, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
    				.addPreferredGap(ComponentPlacement.RELATED)
    				.addComponent(text, GroupLayout.DEFAULT_SIZE, 674, Short.MAX_VALUE))
    	);
    	gl_question.setVerticalGroup(
    		gl_question.createParallelGroup(Alignment.LEADING)
    			.addGroup(gl_question.createSequentialGroup()
    				.addContainerGap()
    				.addComponent(likes, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
    				.addContainerGap(240, Short.MAX_VALUE))
    			.addComponent(text, GroupLayout.PREFERRED_SIZE, 319, Short.MAX_VALUE)
    	);
    	question.setLayout(gl_question);
    	questionPanel.setLayout(gl_questionPanel);

        this.setVisible(true);  

    }
}
