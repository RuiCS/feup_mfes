package gui;


import java.io.Serializable;

import org.overture.interpreter.runtime.ValueException;
import org.overture.interpreter.values.Value;
import org.overture.interpreter.values.VoidValue;



public class Graphics implements Serializable {
	private static final long serialVersionUID = 1L;
	transient Controller ctrl;
    //Model model;
   
    public Value init() {
    	ctrl = new Controller();
    	//model = ctrl.getModel();
        return new VoidValue();
    }

}
