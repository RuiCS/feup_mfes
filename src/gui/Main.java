package gui;

import org.overture.codegen.runtime.VDMMap;

import StackOverflow.Question;
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
    
    public static User getUser(Number title) {
    	VDMMap users = stack.users;
    	return (User)users.get(title);
    }

}
