package gui;

import java.util.ArrayList;
import java.util.Set;

import org.overture.codegen.runtime.VDMMap;
import org.overture.codegen.runtime.VDMSet;

import StackOverflow.Question;
import StackOverflow.Answer;
import StackOverflow.Comment;
import StackOverflow.StackOverflow;
import StackOverflow.User;

public class Main {
	
	public static StackOverflow stack = new StackOverflow();
	
	public static Number questionShown = 0;

    public static void main(String[] args) throws Exception {
    	Graphics g = new Graphics();
		g.init();
    }
    
    public static VDMMap getQuestions() {
    	return stack.questions;
    }
    
    public static Question getQuestion(String title) {
    	Question q = null;
    	VDMMap questions = getQuestions();
    	for(int i = 0; i < questions.size(); i++) {
    		if(questions.containsKey(i)) {
    			Question temp = (Question)questions.get(i);
    			if(temp.text.equals(title)) {
    			questionShown = i;
    				return temp;
    			}
    		}
    	}
    	
    	return q;
    }
    
    public static ArrayList<Comment> getQuestionComments(Number questionID){
    	ArrayList<Comment> c = new ArrayList<>();
    	Number[] commentsIDs = (Number[])((Question)stack.questions.get(questionID)).comments.toArray();
    	for(Number n : commentsIDs) {
    		c.add((Comment)stack.comments.get(n));
    	}
    	return c;
    }
    
    public static ArrayList<Comment> getAnswerComments(Number answerID){
    	ArrayList<Comment> c = new ArrayList<>();
    	Number[] commentsIDs = (Number[])((Answer)stack.answers.get(answerID)).comments.toArray();
    	for(Number n : commentsIDs) {
    		c.add((Comment)stack.comments.get(n));
    	}
    	return c;
    }
    
    public static ArrayList<Answer> getQuestionAnswers(Number questionID){
    	ArrayList<Answer> c = new ArrayList<>();
    	Number[] commentsIDs = (Number[])((Answer)stack.questions.get(questionID)).comments.toArray();
    	for(Number n : commentsIDs) {
    		c.add((Answer)stack.answers.get(n));
    	}
    	return c;
    }
    
    public static User getUser(Number title) {
    	VDMMap users = stack.users;
    	return (User)users.get(title);
    }

}
