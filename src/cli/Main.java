package cli;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

import org.overture.codegen.runtime.Utils;
import org.overture.codegen.runtime.VDMMap;

import StackOverflow.Answer;
import StackOverflow.Comment;
import StackOverflow.Printer;
import StackOverflow.Question;
import StackOverflow.StackOverflow;
import StackOverflow.User;

public class Main {

	private static Scanner scanner = new Scanner(System.in);
	public static StackOverflow stack = new StackOverflow();
		
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Welcome to Stack Overflow. Your available options are listed below. To select an option please input the number.");
		init();
	}
	
	public static void init() {
		while(true) {
			if(stack.loggedUser == null) {
				if(visitorPrompt()) return;
			}else {
				if(userPrompt()) return;
			}
		}
	}
	
	public static boolean visitorPrompt() {
		String selected;

		System.out.println("1. Login");
		System.out.println("2. Register");
		System.out.println("3. Search question");
		System.out.println("4. Exit");
		selected = scanner.nextLine();
		switch(selected) {
			case "1":
				login();
				break;
			case "2":
				register();
				break;
			case "3":
				getQuestion();
				break;
			case "4":
				return true;
			default:
				break;
		}
		return false;
	}
	
	public static boolean userPrompt() {
		String selected;

		System.out.println("1. Logout");
		System.out.println("2. Search question");
		System.out.println("3. Ask question");
		System.out.println("4. Exit");
		selected = scanner.nextLine();
		switch(selected) {
			case "1":
				stack.logout();
				break;
			case "2":
				getQuestion();
				break;
			case "3":
				askQuestion();
				break;
			case "4":
				return true;
			default:
				break;
		}
		return false;
	}	
    
	public static void questionPrompt(Question temp, Number questionID) {
		while(true) {
			if(stack.loggedUser == null) {
				if(questionPromptVisitor(temp, questionID)) return;
			}else {
				if(questionPromptLogged(temp, questionID)) return;
			}
		}
}
	
	public static boolean questionPromptVisitor(Question temp, Number questionID) {
		String selected;
		
		System.out.println("1. Check question with the comments");
		System.out.println("2. Check answers to the question");
		System.out.println("3. Back");
		selected = scanner.nextLine();
		
		switch(selected) {
			case "1":
				printQuestion(temp, questionID);
				break;
			case "2":
				printAnswers(temp, questionID);
				break;
			case "3":
				return true;
		}		
		return false;
	}
	
	public static boolean questionPromptLogged(Question temp, Number questionID) {
		String selected;
		
		System.out.println("1. Check comments to the question");
		System.out.println("2. Check answers to the question");
		System.out.println("3. Add answer");
		System.out.println("4. Comment");
		System.out.println("5. Edit");
		System.out.println("6. Vote");
		System.out.println("7. Back");
		selected = scanner.nextLine();
		
		switch(selected) {
			case "1":
				printQuestion(temp, questionID);
				break;
			case "2":
				printAnswers(temp, questionID);
				break;
			case"3":
				answerQuestion(questionID);
				break;
			case "4":
				comment(questionID);
				break;
			case "5":
				edit(questionID);
				break;
			case "6":
				vote(questionID);
				break;
			case"7":
				return true;
		}	
		return false;
	}
	
	public static void printQuestion(Question q, Number questionID) {
		
		System.out.println( "Q"
              + questionID
              + ":\nCategory: "
              + q.category
              + "\nQuestion: "
              + q.text
              + "\nDescription: "
              + q.description
              + "\nDate: "
              + q.date
              + "\nVotes: "
              + stack.getVotes(0, 0, questionID)
              + " Upvotes & "
              + stack.getVotes(1, 0, questionID)
              + " Downvotes\n"
              + "\nComments:\n");
		if(q.comments.isEmpty()) return;
		ArrayList<Comment> answersComments = getAnswerComments(questionID);
		Object[] commentsIDs = q.comments.toArray();
		
		for (int j = 0; j < answersComments.size(); j++) {
			Number cID = (Number) commentsIDs[j];
			Comment c = answersComments.get(j);

			Printer.Out(
		              "\tC"
		                  + Printer.natToString(cID)
		                  + "\n\t\tcomment by "
		                  + ((User) stack.users.get(c.userID)).username
		                  + "\n\t\tcomment: "
		                  + c.text
		                  + "\n\t\tdate: "
		                  + c.date
		                  + "\nVotes: "
		                  + stack.getVotes(0L, 2L, cID)
		                  + " Upvotes & "
		                  + stack.getVotes(1L, 2L, cID)
		                  + " Downvotes\n"
		                  + "\n");
		}
	}
	
	public static void printAnswers(Question q, Number questionID) {
		if(q.answers.isEmpty()) return;

		ArrayList<Answer> questionAnswers = getQuestionAnswers(questionID);
		Object[] answersIDs = q.answers.toArray();
		for (int i = 0; i < questionAnswers.size(); i++) {
			Number aID = (Number) answersIDs[i];
			Answer a = questionAnswers.get(i);

			System.out.println( "\n\n\tA"
	                + Printer.natToString(aID)
	                + "\n\tanswered by "
	                + ((User)stack.users.get(a.userID)).username
	                + "\n\tanswer: "
	                + a.text
	                + "\n\tdate: "
	                + a.date
	                + "\nVotes: "
	                + stack.getVotes(0L, 1L, aID)
	                + " Upvotes & "
	                + stack.getVotes(1L, 1L, aID)
	                + " Downvotes\n"
	                + "\n\tComments:\n");
			if(a.comments.isEmpty()) continue;
			ArrayList<Comment> answersComments = getAnswerComments(aID);
			Object[] commentsIDs = a.comments.toArray();
			
			for (int j = 0; j < answersComments.size(); j++) {
				Number cID = (Number) commentsIDs[j];
				Comment c = answersComments.get(j);

				Printer.Out(
			              "\tC"
			                  + Printer.natToString(cID)
			                  + "\n\t\tcomment by "
			                  + ((User) stack.users.get(c.userID)).username
			                  + "\n\t\tcomment: "
			                  + c.text
			                  + "\n\t\tdate: "
			                  + c.date
			                  + "\nVotes: "
			                  + stack.getVotes(0L, 2L, cID)
			                  + " Upvotes & "
			                  + stack.getVotes(1L, 2L, cID)
			                  + " Downvotes\n"
			                  + "\n");
			}
		}

	}

	public static void vote(Number questionID) {
		System.out.println("1. Vote the question");
		System.out.println("2. Vote an answer");
		System.out.println("3. Vote a comment");
		System.out.println("4. Back");

		String selected = scanner.nextLine(), answerID, commentID, question, desc;

		switch(selected) {
			case "1":
				System.out.println("1. Upvote");
				System.out.println("2. Downvote");
				System.out.println("3. Back");
				question = scanner.nextLine();
				switch(question) {
					case "1":
						stack.upvoteQuestion(questionID);
						break;
					case "2":
						stack.downvoteQuestion(questionID);
				}
				break;
			case "2":
				System.out.println("Insert the answerID");
				answerID = scanner.nextLine();
				System.out.println("1. Upvote");
				System.out.println("2. Downvote");
				System.out.println("3. Back");
				question = scanner.nextLine();
				switch(question) {
					case "1":
						stack.upvoteAnswer(Integer.parseInt(answerID));
						break;
					case "2":
						stack.downvoteAnswer(Integer.parseInt(answerID));
				}
				break;
			case "3":
				System.out.println("Insert the commentID");
				commentID = scanner.nextLine();
				System.out.println("1. Upvote");
				System.out.println("2. Downvote");
				System.out.println("3. Back");
				question = scanner.nextLine();
				switch(question) {
					case "1":
						stack.upvoteComment(Integer.parseInt(commentID));
						break;
					case "2":
						stack.downvoteComment(Integer.parseInt(commentID));
				}
			default:break;
		}
	}
	
	
	
	public static void edit(Number questionID) {
		System.out.println("1. Edit the question");
		System.out.println("2. Edit an answer");
		System.out.println("3. Edit a comment");
		System.out.println("4. Back");

		String selected = scanner.nextLine(), answerID, commentID, question, desc;

		switch(selected) {
			case "1":
				System.out.println("Insert the new question");
				question = scanner.nextLine();
				System.out.println("Insert the new description");
				desc = scanner.nextLine();
				stack.editQuestion(questionID, question, desc);
				break;
			case "2":
				System.out.println("Insert the answerID");
				answerID = scanner.nextLine();
				System.out.println("Insert the new answer");
				selected = scanner.nextLine();
				stack.editAnswer(Integer.parseInt(answerID), selected);
				break;
			case "3":
				System.out.println("Insert the commentID");
				commentID = scanner.nextLine();
				System.out.println("Insert the new comment");
				selected = scanner.nextLine();
				stack.editComment(Integer.parseInt(commentID), selected);
			default:break;
		}
	}
	
	public static void comment(Number questionID) {
		System.out.println("1. Comment the question");
		System.out.println("2. Comment a question");
		System.out.println("3. Back");
		String selected = scanner.nextLine(), answerID;

		switch(selected) {
			case "1":
				System.out.println("Insert the comment");
				selected = scanner.nextLine();
				stack.commentQuestion(questionID, selected);
				break;
			case "2":
				System.out.println("Insert the answerID");
				answerID = scanner.nextLine();
				System.out.println("Insert the comment");
				selected = scanner.nextLine();
				stack.commentAnswer(questionID, Integer.parseInt(answerID), selected);
				break;
			default: break;
		}
	}
	
    public static void askQuestion() {
    	String q, des, cat;
		System.out.println("Ask the question.");
		q = scanner.nextLine();
		System.out.println("Please insert the description.");
		des = scanner.nextLine();
		System.out.println("Please insert the category.");
		cat = scanner.nextLine();
		stack.askQuestion(q, des, cat);
    }
    
    public static void answerQuestion(Number questionID) {
    	String a;
		System.out.println("Answer the question.");
		a = scanner.nextLine();
		stack.answerQuestion(questionID, a);
    }
    
	public static void login() {
		String user, pass;
		System.out.println("Please insert the username.");
		user = scanner.nextLine();
		System.out.println("Please insert the password.");
		pass = scanner.nextLine();
		
		stack.login(user, pass);
		if(stack.loggedUser == null) {
			System.out.println("Error in logging in.");
		}else {
			System.out.println("You are now logged in.");
		}
	}
	
	public static void register() {
		String user, pass;
		System.out.println("Please insert the username.");
		user = scanner.nextLine();
		System.out.println("Please insert the password.");
		pass = scanner.nextLine();
		stack.signup(user, pass);
	}

    public static ArrayList<Comment> getQuestionComments(Number questionID){
    	ArrayList<Comment> c = new ArrayList<>();
    	Object[] commentsIDs = ((Question)stack.questions.get(questionID)).comments.toArray();
    	for(Object o : commentsIDs) {
    		c.add((Comment)stack.comments.get(((Number) o).intValue()));
    	}
    	return c;
    }
    
    public static ArrayList<Comment> getAnswerComments(Number answerID){
    	ArrayList<Comment> c = new ArrayList<>();
    	Object[] commentsIDs = ((Answer)stack.answers.get(answerID)).comments.toArray();
    	for(Object o : commentsIDs) {
    		c.add((Comment)stack.comments.get(((Number) o).intValue()));
    	}
    	return c;
    }
    
    public static ArrayList<Answer> getQuestionAnswers(Number questionID){
    	ArrayList<Answer> c = new ArrayList<>();
    	Object[] commentsIDs = ((Question)stack.questions.get(questionID)).answers.toArray();
    	for(Object o : commentsIDs) {
    		Answer a = (Answer) stack.answers.get(((Number) o).intValue());
    		c.add(a);
    	}
    	return c;
    }

    public static VDMMap getQuestions() {
    	return stack.questions;
    }
	
    public static Question getQuestion() {
    	String title;
		System.out.println("Please insert the question title.");
		title = scanner.nextLine();
    	
    	Question q = null;
    	VDMMap questions = getQuestions();
    	for(int i = 0; i < questions.size(); i++) {
    		if(questions.containsKey(i)) {
    			Question temp = (Question)questions.get(i);
    			if(temp.text.equals(title)) {
    				questionPrompt(temp, i);
    				return temp;
    			}
    		}
    	}
    	
    	return q;
    }
	
}
