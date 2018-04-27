package common.controller;

import java.util.Scanner;

import common.commandprocessor.CommandProcessorFactory;
import stringProcessors.HalloweenCommandProcessor;
import util.annotations.Visible;
import util.misc.ThreadSupport;
import util.remote.ARelayerClientController;

public class ATrickOrTreatClientController extends ARelayerClientController implements TrickOrTreatClientController{
	public static final int NUM_Y_MOVES = 40;
	public static final int NUM_X_MOVES = 40;
	
//	public static final int MOVE_OFFSET = 3;

	public static final int NUM_DOS_UNDOS = 2;
	int interCommandTime = 0;
	
	long commandExecutionTime;
	protected boolean markCommands = true;
//	String lastSerializedObject = "";
//	String lastDeSerializedObject = "";


	
	@Visible(false)
	public void doTimedCommands() {
//		

		long startTime = System.currentTimeMillis();
		doCommands();
		long endTime = System.currentTimeMillis();
		setCommandExecutionTime ((endTime - startTime));
		System.out.println("Loop execution time:" + (endTime - startTime));
	}
	public void doCommands() {
//		System.out.println("Executing do commands");
		HalloweenCommandProcessor aCommandProcessor = CommandProcessorFactory.getCommandProcessor(); 

//		System.out.println("start time" + System.currentTimeMillis());
//		aCommandProcessor.processCommand("move 50 -50");
//		System.out.println("end time after one command" + System.currentTimeMillis());
		for (int i=0; i < NUM_X_MOVES; i++) {
//			aCommandProcessor.processCommand("move 1 -1");
			aCommandProcessor.setInputString("move 3 0");
			if (interCommandTime != 0)
				ThreadSupport.sleep(interCommandTime);


		}
		for (int i=0; i < NUM_Y_MOVES; i++) {
//			aCommandProcessor.processCommand("move 1 -1");
			aCommandProcessor.setInputString("move 0 -3");
			if (interCommandTime != 0)
				ThreadSupport.sleep(interCommandTime);
		}
		
//		System.out.println("Loop execution time:" + (endTime - startTime));
	}
	public void forwardYCommands() {
//		System.out.println("Executing do commands");
		HalloweenCommandProcessor aCommandProcessor = CommandProcessorFactory.getCommandProcessor(); 

//		System.out.println("start time" + System.currentTimeMillis());
//		aCommandProcessor.processCommand("move 50 -50");
//		System.out.println("end time after one command" + System.currentTimeMillis());
		
		for (int i=0; i < NUM_Y_MOVES; i++) {
//			aCommandProcessor.processCommand("move 1 -1");
			aCommandProcessor.setInputString("move 0 -3");
			if (interCommandTime != 0)
				ThreadSupport.sleep(interCommandTime);
		}
		
//		System.out.println("Loop execution time:" + (endTime - startTime));
	}
	public void forwardXCommands() {
//		System.out.println("Executing do commands");
		HalloweenCommandProcessor aCommandProcessor = CommandProcessorFactory.getCommandProcessor(); 

//		System.out.println("start time" + System.currentTimeMillis());
//		aCommandProcessor.processCommand("move 50 -50");
//		System.out.println("end time after one command" + System.currentTimeMillis());
		for (int i=0; i < NUM_X_MOVES; i++) {
//			aCommandProcessor.processCommand("move 1 -1");
			aCommandProcessor.setInputString("move 3 0");
			if (interCommandTime != 0)
				ThreadSupport.sleep(interCommandTime);


		}
		
//		System.out.println("Loop execution time:" + (endTime - startTime));
	}

	@Override
	public void undoTimedCommands() {


		long startTime = System.currentTimeMillis();
		System.out.println("start time" + System.currentTimeMillis());
		undoCommands();
		
		long endTime = System.currentTimeMillis();
		setCommandExecutionTime ((endTime - startTime));


		System.out.println("Loop execution time:" + (commandExecutionTime));
	
		
	}
	
	 void undoCommands() {

		HalloweenCommandProcessor aCommandProcessor = CommandProcessorFactory.getCommandProcessor(); 

//		long startTime = System.currentTimeMillis();
//		System.out.println("start time" + System.currentTimeMillis());
//		aCommandProcessor.processCommand("move 50 -50");
//		System.out.println("end time after one command" + System.currentTimeMillis());
		for (int i=0; i < NUM_Y_MOVES; i++) {
//			aCommandProcessor.processCommand("move 1 -1");
			aCommandProcessor.setInputString("move 0  3");


		}
		for (int i=0; i < NUM_X_MOVES; i++) {
//			aCommandProcessor.processCommand("move 1 -1");
			aCommandProcessor.setInputString("move -3 0");


		}
		
//		long endTime = System.currentTimeMillis();
//		commandExecutionTime = (endTime - startTime);
//
//		System.out.println("Loop execution time:" + (commandExecutionTime));
	
		
	}
	@Override
	public boolean isMarkCommands() {
		return markCommands;
	}
	@Override
	public void setMarkCommands(boolean markCommands) {
		this.markCommands = markCommands;
	}
	@Override
	@Visible(false)
	public long getCommandExecutionTime() {
		return commandExecutionTime;
	}
	@Override
	@Visible(false)
	public void setCommandExecutionTime(long commandExecutionTime) {
		this.commandExecutionTime = commandExecutionTime;
		propertyChangeSupport.firePropertyChange("CommandExecutionTime", 0, commandExecutionTime);
	}
	public void executeTimedCommands() {
		long startTime = System.currentTimeMillis();
		for (int i = 0; i < NUM_DOS_UNDOS; i++) {
			doCommands();
			undoCommands();
		}
		long endTime = System.currentTimeMillis();
		setCommandExecutionTime ((endTime - startTime));


//		System.out.println("Loop execution time:" + (commandExecutionTime));
	}
	protected static Scanner scanner = new Scanner(System.in);
	@Override
	public void executeCommandLoop() {
//		AScatterGatherSelectionManager.setMAX_OUTSTANDING_WRITES(1000);

		while (true) {
			System.out.println("Please input, u, d, e, x, y or a simulation command");
			String userInput = scanner.nextLine();
			if (userInput.equals(DO_NAME))
				doTimedCommands();
			else if (userInput.equals(UNDO_NAME))
				undoTimedCommands();
			else if (userInput.equals(EXECUTE_NAME))
				executeTimedCommands();
			else if (userInput.equals(FORWARD_X)) {
				forwardXCommands();
			} else if (userInput.equals(FORWARD_Y)) {
				forwardYCommands();
			} else
			{
				CommandProcessorFactory.getCommandProcessor().setInputString(userInput);
			}
			
		}
		
	}
	
	@Override
	public int getInterCommandTime() {
		return interCommandTime;
	}
	@Override
	public void setInterCommandTime(int interCommandTime) {
		this.interCommandTime = interCommandTime;
		propertyChangeSupport.firePropertyChange("InterCommandTime", 
				null, interCommandTime);
	}

}
