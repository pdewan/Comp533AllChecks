package gradingTools.comp533s18.assignment1.testcases;

import java.util.regex.Pattern;

import util.pipe.AnAbstractInputGenerator;

public class TwoClientCorrectConnectionTestInputGenerator extends AnAbstractInputGenerator {
	private static final String TRACER_PREFIX = "I***";
	
	private static final String MAIN_THREAD = "\\{main\\}";
	private static final String SELECT_THREAD = "\\{.*?[sS][eE][lL][eE][cC][tT].*?\\}";
	
	private static final String SERVER_NAME = "Server";
	private static final String CLIENT_0_NAME = "Client_0";
	private static final String CLIENT_1_NAME = "Client_1";
	private int enableAcceptStage = 0;
	private int connect0Stage = 0;
	private int connect1Stage = 0;
	private int accept0Stage = 0;
	private int accept1Stage = 0;
	
	private boolean quitSubmitted = false;

	private static final Pattern[] enableAcceptStages = {
			checkStr(MAIN_THREAD, "SelectorFactorySet"),
			checkStr(MAIN_THREAD, "SocketChannelBound"),
			checkStr(MAIN_THREAD, "ListenableAcceptsEnabled"),
//			multipleCheckStr(SELECT_THREAD, "SelectCalled", MAIN_THREAD, "SelectorRequestNextInterestOp"),
//			multipleCheckStr(SELECT_THREAD, "SelectCalled", MAIN_THREAD, "SelectorRequestNextInterestOp"),
//			checkStr(SELECT_THREAD, "SelectCalled"),
			checkStr(MAIN_THREAD, "SelectorRequestNextInterestOp"),
			checkStr(MAIN_THREAD, "SelectorRequestEnqueued"),
			checkStr(MAIN_THREAD, "SelectorWokenUp"),
//			checkStr(SELECT_THREAD, "SelectUnblocked"),
			checkStr(SELECT_THREAD, "SelectorRequestDequeued"),
			checkStr(SELECT_THREAD, "SocketChannelBlockingConfigured"),
			checkStr(SELECT_THREAD, "SocketChannelRegistered"),
			checkStr(SELECT_THREAD, "SelectCalled")
	};
	
	// SelectCalled seems to vary some
	private static final Pattern[] connectStages = {
			checkStr(MAIN_THREAD, "SelectorFactorySet"),
			checkStr(MAIN_THREAD, "AddedPropertyChangeListener"),
//			checkStr(MAIN_THREAD, "AddedPropertyChangeListener"),
			checkStr(MAIN_THREAD, "ReadListenerAdded"),
//			multipleCheckStr(SELECT_THREAD, "SelectCalled", MAIN_THREAD, "SocketChannelConnectRequested"),
//			multipleCheckStr(SELECT_THREAD, "SelectCalled", MAIN_THREAD, "SocketChannelConnectRequested"),
//			checkStr(SELECT_THREAD, "SelectCalled"),
			checkStr(MAIN_THREAD, "SocketChannelConnectRequested"),
			checkStr(MAIN_THREAD, "SelectorRequestNextInterestOp"),
			checkStr(MAIN_THREAD, "SelectorRequestEnqueued"),
			checkStr(MAIN_THREAD, "SelectorWokenUp"),
//			checkStr(SELECT_THREAD, "SelectUnblocked"),
			checkStr(SELECT_THREAD, "SelectorRequestDequeued"),
			checkStr(SELECT_THREAD, "SocketChannelBlockingConfigured"),
			checkStr(SELECT_THREAD, "SocketChannelConnectInitiated"),
			checkStr(SELECT_THREAD, "SocketChannelRegistered"),
//			checkStr(SELECT_THREAD, "SelectCalled"),
//			checkStr(SELECT_THREAD, "SelectUnblocked"),
			checkStr(SELECT_THREAD, "SocketChannelConnected"),
			checkStr(SELECT_THREAD, "SocketChannelInterestOp"),
			checkStr(SELECT_THREAD, "SelectCalled")
	};
	
	private static final Pattern[] acceptStages = {
//			checkStr(SELECT_THREAD, "SelectUnblocked"),
			checkStr(SELECT_THREAD, "SocketChannelAccepted"),
			checkStr(SELECT_THREAD, "ReadListenerAdded"),
			checkStr(SELECT_THREAD, "SocketChannelRegistered"),
			checkStr(SELECT_THREAD, "SelectCalled")
	};
	
	private static final Pattern multipleCheckStr(String thread1, String check1, String thread2, String check2) {
		return Pattern.compile(".*?(" + thread1 + ".*?" + check1 + "|" + thread2 + ".*?" + check2 + ").*", Pattern.DOTALL);
	}
	
	private static final Pattern checkStr(String thread, String check) {
		return Pattern.compile(".*?" + thread + ".*?" + check + ".*", Pattern.DOTALL);
	}
	
	
	public TwoClientCorrectConnectionTestInputGenerator() {
	}
	@Override
	public void newOutputLine(String aProcessName, String anOutputLine) {
		if (aProcessName.equals(SERVER_NAME)) {
			if (!isEnableAcceptComplete()) {
				checkEnableAccept(anOutputLine);
			} else if (canProcessAccept()) {
				// each output can only be from 1 accept
				boolean accepted = false;
				if (!isAccepted0Complete()) {
					accepted = checkAccept0(anOutputLine);
				}
				if (!accepted && !isAccepted1Complete()) {
					checkAccept1(anOutputLine);
				}
			}
		} else if (aProcessName.equals(CLIENT_0_NAME)) {
			if (isEnableAcceptComplete() && !isConnect0Complete()) {
				checkConnect0(anOutputLine);
			}
		} else if (aProcessName.equals(CLIENT_1_NAME)) {
			if (isEnableAcceptComplete() && !isConnect1Complete()) {
				checkConnect1(anOutputLine);
			}
		}
		if (!quitSubmitted && areAcceptsComplete()) {
			notifyNewInputLine(CLIENT_0_NAME, "q 0");
			notifyNewInputLine(CLIENT_1_NAME, "q 0");
			notifyNewInputLine(SERVER_NAME, "q 0");
			quitSubmitted = true;
		}
	}
	
	public boolean isEnableAcceptComplete() {
		return enableAcceptStage == enableAcceptStages.length;
	}
	
	public boolean isConnect0Complete() {
		return connect0Stage == connectStages.length;
	}
	
	public boolean isConnect1Complete() {
		return connect1Stage == connectStages.length;
	}
	
	public boolean areConnectsComplete() {
		return isConnect0Complete() && isConnect1Complete();
	}
	

	public boolean canProcessAccept() {
		return connect0Stage >= 10 || connect1Stage >= 10; // was 17
	}
	
	public boolean isAccepted0Complete() {
		return accept0Stage == acceptStages.length;
	}
	
	public boolean isAccepted1Complete() {
		return accept1Stage == acceptStages.length;
	}
	
	public boolean areAcceptsComplete() {
		return isAccepted0Complete() && isAccepted1Complete();
	}
	
	public boolean checkEnableAccept(String line) {
		if (line.startsWith(TRACER_PREFIX) && enableAcceptStages[enableAcceptStage].matcher(line).matches()) {
			enableAcceptStage++;
			return true;
		}
		return false;
	}
	
	public boolean checkConnect0(String line) {
		if (line.startsWith(TRACER_PREFIX) && connectStages[connect0Stage].matcher(line).matches()) {
			connect0Stage++;
			return true;
		}
		return false;
	}
	
	public boolean checkConnect1(String line) {
		if (line.startsWith(TRACER_PREFIX) && connectStages[connect1Stage].matcher(line).matches()) {
			connect1Stage++;
			return true;
		}
		return false;
	}
	
	public boolean checkAccept0(String line) {
		if (line.startsWith(TRACER_PREFIX) && acceptStages[accept0Stage].matcher(line).matches()) {
			accept0Stage++;
			return true;
		}
		return false;
	}
	
	public boolean checkAccept1(String line) {
		if (line.startsWith(TRACER_PREFIX) && acceptStages[accept1Stage].matcher(line).matches()) {
			accept1Stage++;
			return true;
		}
		return false;
	}
}
