package sync.models;

import graphics.HalloweenSimulation;
import stringProcessors.AHalloweenCommandProcessor;
import util.annotations.Visible;
import util.models.AListenableVector;
import util.models.ListenableVector;
import util.models.VectorChangeEvent;
import util.models.VectorListener;

public class AReplicatableHalloweenCommandProcessor extends AHalloweenCommandProcessor implements VectorListener {
	ListenableVector<String> history = new AListenableVector();
	
	public AReplicatableHalloweenCommandProcessor() {
		history.addVectorListener(this);
	}
	@Override
	public void init (HalloweenSimulation aSimulation) {
		super.init(aSimulation);
		for (String string:history){
			super.setInputString(string);
		}
	}
	
	@Override
	public void setInputString(String newVal) {
//		super.setInputString(newVal);
//		System.out.println("set input string:" + newVal);
		history.add(newVal);
	}
	@Visible(false)
	public ListenableVector<String> getHistory() {
		return history;
	}
	
	public void setHistory(ListenableVector<String> newVal) {
		 history = newVal;
	}

	@Override
	public void updateVector(VectorChangeEvent evt) {
		String newVal = (String) evt.getNewValue();
		super.setInputString(newVal);
	}
	
	@Override
	public void initSerializedObject() {
		super.initSerializedObject();
		history.addVectorListener(this);

	}
 
 

}
