package gradingTools.comp533s18.assignment3.testcases;

import java.util.regex.Pattern;

import util.pipe.AnAbstractInputGenerator;
import util.trace.Tracer;

public class OneClientCorrectConnectionTestInputGenerator extends AnAbstractInputGenerator {
private static final String TRACER_PREFIX = "I***";
	
	private static final String MAIN_THREAD = "\\{main\\}";
	private static final String SELECT_THREAD = "\\{.*?[sS][eE][lL][eE][cC][tT].*?\\}";
	private static final String GIPC_THREAD = "\\{Asynchronous Received Call Invoker\\}";
	
	
	private static final String SERVER_NAME = "Server";
	private static final String CLIENT_NAME = "Client";
	
	private int serverNIOSetupStage = 0;
	private int serverRMISetupStage = 0;
	private int serverGIPCSetupStage = 0;
	private int clientNIOConnectStage = 0;
	private int clientRMIConnectStage = 0;
	private int clientGIPCConnectStage = 0;
	private int serverNIOAcceptStage = 0;
	private int serverGIPCAcceptStage = 0;
	
	private boolean serverRegisteredGIPCObject = false;

	private boolean[] hasNIOListeners = {false, false};
	private boolean[] hasRMIListeners = {false};
	private boolean[] hasGIPCListeners = {false, false, false};
	
	private final boolean doNIO;
	private final boolean doRMI;
	private final boolean doGIPC;
	
	private boolean quitSubmitted = false;

	private static final Pattern[] serverNIOSetupStages = {
			checkStr(MAIN_THREAD, "SelectorFactorySet"),
			checkStr(MAIN_THREAD, "SocketChannelBound"),
			checkStr(MAIN_THREAD, "ListenableAcceptsEnabled"),
			checkStr(MAIN_THREAD, "SelectorRequestNextInterestOp"),
			checkStr(MAIN_THREAD, "SelectorRequestEnqueued"),
			checkStr(MAIN_THREAD, "SelectorWokenUp"),
			checkStr(SELECT_THREAD, "SelectorRequestDequeued"),
			checkStr(SELECT_THREAD, "SocketChannelBlockingConfigured"),
			checkStr(SELECT_THREAD, "SocketChannelRegistered"),
			checkStr(SELECT_THREAD, "SelectCalled")
	};
	
	private static final Pattern[] serverRMISetupStages = {
			checkStr(MAIN_THREAD, "RMIRegistryLocated"),
			checkStr(MAIN_THREAD, "RMIObjectRegistered")
	};
	
	private static final Pattern[] serverGIPCSetupStages = {
			checkStr(MAIN_THREAD, "GIPCRegistryCreated"),
			checkStr(MAIN_THREAD, "SelectorFactorySet"),
			checkStr(MAIN_THREAD, "ListenableAcceptsEnabled"),
			checkStr(MAIN_THREAD, "SelectorRequestNextInterestOp"),
			checkStr(MAIN_THREAD, "SelectorRequestEnqueued"),
			checkStr(MAIN_THREAD, "SelectorWokenUp"),
			checkStr(SELECT_THREAD, "SelectorRequestDequeued"),
			checkStr(SELECT_THREAD, "SocketChannelBlockingConfigured"),
			checkStr(SELECT_THREAD, "SocketChannelRegistered"),
			checkStr(SELECT_THREAD, "SelectCalled")
	};
	
	// PropertyChangeListener, ReadListener
	private static final Pattern[] clientNIOConnectStages = {
			checkStr(MAIN_THREAD, "SelectorFactorySet"),
			checkStr(MAIN_THREAD, "SocketChannelConnectRequested"),
			checkStr(MAIN_THREAD, "SelectorRequestNextInterestOp"),
			checkStr(MAIN_THREAD, "SelectorRequestEnqueued"),
			checkStr(MAIN_THREAD, "SelectorWokenUp"),
			checkStr(SELECT_THREAD, "SelectorRequestDequeued"),
			checkStr(SELECT_THREAD, "SocketChannelBlockingConfigured"),
			checkStr(SELECT_THREAD, "SocketChannelConnectInitiated"),
			checkStr(SELECT_THREAD, "SocketChannelRegistered"),
			checkStr(SELECT_THREAD, "SelectCalled"),
			checkStr(SELECT_THREAD, "SocketChannelConnected"),
			checkStr(SELECT_THREAD, "SocketChannelInterestOp"),
			checkStr(SELECT_THREAD, "SelectCalled")
	};
	
	// PropertyChangeListener
	private static final Pattern[] clientRMIConnectStages = {
			checkStr(MAIN_THREAD, "RMIRegistryLocated"),
			checkStr(MAIN_THREAD, "RMIObjectLookedUp")
	};
	
	// PropertyChangeListener, ReadListener, WriteListener
	private static final Pattern[] clientGIPCConnectStages = {
			checkStr(MAIN_THREAD, "GIPCRegistryLocated"),
			checkStr(MAIN_THREAD, "SelectorFactorySet"),
			checkStr(MAIN_THREAD, "SelectorFactorySet"),
			checkStr(MAIN_THREAD, "SocketChannelConnectRequested"),
			checkStr(MAIN_THREAD, "SelectorRequestNextInterestOp"),
			checkStr(MAIN_THREAD, "SelectorRequestEnqueued"),
			checkStr(MAIN_THREAD, "SelectorWokenUp"),
			checkStr(SELECT_THREAD, "SelectorRequestDequeued"),
			checkStr(SELECT_THREAD, "SocketChannelBlockingConfigured"),
			checkStr(SELECT_THREAD, "SocketChannelConnectInitiated"),
			checkStr(SELECT_THREAD, "SocketChannelRegistered"),
			checkStr(SELECT_THREAD, "SelectCalled"),
			checkStr(SELECT_THREAD, "SocketChannelConnected"),
			checkStr(SELECT_THREAD, "SocketChannelWriteRequested"),
			checkStr(SELECT_THREAD, "WriteRequestEnqueued"),
			checkStr(SELECT_THREAD, "WriteRequestEnqueued"),
			checkStr(SELECT_THREAD, "SelectorWokenUp"),
			checkStr(SELECT_THREAD, "SocketChannelInterestOp"),
			checkStr(SELECT_THREAD, "SocketChannelInterestOp"),
			checkStr(SELECT_THREAD, "SelectCalled"),
			checkStr(SELECT_THREAD, "SelectCalled"),
			checkStr(SELECT_THREAD, "SocketChannelWritten"),
			checkStr(SELECT_THREAD, "WriteRequestDequeued"),
			checkStr(SELECT_THREAD, "SocketChannelWritten"),
			checkStr(SELECT_THREAD, "WriteRequestDequeued"),
			checkStr(SELECT_THREAD, "SocketChannelInterestOp"),
			checkStr(SELECT_THREAD, "WriteBufferIsEmpty"),
			checkStr(SELECT_THREAD, "ReadsEnabled"),
			checkStr(SELECT_THREAD, "SocketChannelInterestOp"),
			checkStr(SELECT_THREAD, "SelectCalled"),
			checkStr(MAIN_THREAD, "GIPCObjectLookedUp"),
			checkStr(MAIN_THREAD, "SocketChannelWriteRequested"),
			checkStr(MAIN_THREAD, "WriteRequestEnqueued"),
			checkStr(MAIN_THREAD, "WriteRequestEnqueued"),
			checkStr(MAIN_THREAD, "SelectorWokenUp"),
			checkStr(SELECT_THREAD, "SocketChannelInterestOp"),
			checkStr(SELECT_THREAD, "SelectCalled"),
			checkStr(SELECT_THREAD, "SocketChannelWritten"),
			checkStr(SELECT_THREAD, "WriteRequestDequeued"),
			checkStr(SELECT_THREAD, "SocketChannelWritten"),
			checkStr(SELECT_THREAD, "WriteRequestDequeued"),
			checkStr(SELECT_THREAD, "SocketChannelInterestOp"),
			checkStr(SELECT_THREAD, "WriteBufferIsEmpty"),
			checkStr(SELECT_THREAD, "ReadsEnabled"),
			checkStr(SELECT_THREAD, "SocketChannelInterestOp"),
			checkStr(SELECT_THREAD, "SelectCalled")
	};
	
	private static final Pattern readListenerPattern = multipleCheckStr(MAIN_THREAD, "ReadListenerAdded", SELECT_THREAD, "ReadListenerAdded");
	private static final Pattern writeListenerPattern = multipleCheckStr(MAIN_THREAD, "WriteListenerAdded", SELECT_THREAD, "WriteListenerAdded");
	private static final Pattern propertyChangeListenerPattern = multipleCheckStr(MAIN_THREAD, "AddedPropertyChangeListener", SELECT_THREAD, "AddedPropertyChangeListener");
	
	private static final Pattern gipcObjectRegisteredPattern = checkStr(MAIN_THREAD, "GIPCObjectRegistered");
	
	private static final Pattern[] serverNIOAcceptStages = {
			checkStr(SELECT_THREAD, "SocketChannelAccepted"),
			checkStr(SELECT_THREAD, "ReadListenerAdded"),
			checkStr(SELECT_THREAD, "SocketChannelRegistered"),
			checkStr(SELECT_THREAD, "SelectCalled")
	};
	
	private static final Pattern[] serverGIPCAcceptStages = {
			checkStr(SELECT_THREAD, "SocketChannelAccepted"),
			checkStr(SELECT_THREAD, "ReadListenerAdded"),
			checkStr(SELECT_THREAD, "WriteListenerAdded"),
			checkStr(SELECT_THREAD, "SocketChannelRegistered"),
			checkStr(SELECT_THREAD, "SelectCalled"),
			checkStr(SELECT_THREAD, "SocketChannelRead"),
			checkStr(SELECT_THREAD, "SocketChannelHeaderRead"),
			checkStr(SELECT_THREAD, "SocketChannelFullMessageRead"),
			checkStr(SELECT_THREAD, "SelectCalled"),
			checkStr(SELECT_THREAD, "SocketChannelRead"),
			checkStr(SELECT_THREAD, "SocketChannelHeaderRead"),
			checkStr(SELECT_THREAD, "SocketChannelFullMessageRead"),
			checkStr(GIPC_THREAD, "ReceivedCallInitiated")
	};
	
	private static final Pattern checkStr(String thread, String check) {
		return Pattern.compile(".*?" + thread + ".*?" + check + ".*", Pattern.DOTALL);
	}
	
	private static final Pattern multipleCheckStr(String thread1, String check1, String thread2, String check2) {
		return Pattern.compile(".*?(" + thread1 + ".*?" + check1 + "|" + thread2 + ".*?" + check2 + ").*", Pattern.DOTALL);
	}
	
	public OneClientCorrectConnectionTestInputGenerator(boolean doNIO, boolean doRMI, boolean doGIPC) {
		this.doNIO = doNIO;
		this.doRMI = doRMI;
		this.doGIPC = doGIPC;
	}
	
	@Override
	public void newOutputLine(String aProcessName, String anOutputLine) {
		if (aProcessName.equals(SERVER_NAME)) {
			if (!isServerSetupComplete()) {
				if (!isServerNIOSetupComplete() && checkServerNIOSetup(anOutputLine)) {
				} else if (!isServerRMISetupComplete() && checkServerRMISetup(anOutputLine)) {
				} else if (!isServerGIPCSetupComplete() && checkServerGIPCSetup(anOutputLine)) {}
			} else if (!isServerAcceptComplete()) {
				if (!isServerNIOAcceptComplete() && checkServerNIOAccept(anOutputLine)) {
				} else if (!isServerGIPCAcceptComplete() && checkServerGIPCAccept(anOutputLine)) {}
			}
		} else if (aProcessName.equals(CLIENT_NAME)) {
			if (!isClientConnectComplete()) {
				if (!isClientNIOConnectComplete() && checkClientNIOConnect(anOutputLine)) {
				} else if (!isClientRMIConnectComplete() && checkClientRMIConnect(anOutputLine)) {
				} else if (!isClientGIPCConnectComplete() && checkClientGIPCConnect(anOutputLine)) {}
			}
		}
		if (!quitSubmitted && isServerAcceptComplete()) {
			notifyNewInputLine(CLIENT_NAME, "q 0");
			notifyNewInputLine(SERVER_NAME, "q 0");
			quitSubmitted = true;
		}
	}
	
	public boolean isServerNIOSetupComplete() {
		return !doNIO || serverNIOSetupStage == serverNIOSetupStages.length;
	}

	public boolean isServerRMISetupComplete() {
		return !doRMI || serverRMISetupStage == serverRMISetupStages.length;
	}
	
	public boolean isServerGIPCSetupFSMComplete() {
		return serverGIPCSetupStage == serverGIPCSetupStages.length;
	}
	
	public boolean isServerGIPCSetupComplete() {
		return !doGIPC || isServerGIPCSetupFSMComplete();
	}
	
	public boolean isServerSetupComplete() {
		return isServerNIOSetupComplete() && isServerRMISetupComplete() && isServerGIPCSetupComplete();
	}
	
	public boolean isClientNIOConnectFSMComplete() {
		return clientNIOConnectStage == clientNIOConnectStages.length;
	}
	
	public boolean isClientNIOConnectComplete() {
		return !doNIO || (isClientNIOConnectFSMComplete() && hasNIOListeners[0] && hasNIOListeners[1]);
	}

	public boolean isClientRMIConnectFSMComplete() {
		return clientRMIConnectStage == clientRMIConnectStages.length;
	}
	
	public boolean isClientRMIConnectComplete() {
		return !doRMI || (isClientRMIConnectFSMComplete() && hasRMIListeners[0]);
	}
	
	public boolean isClientGIPCConnectFSMComplete() {
		return clientGIPCConnectStage == clientGIPCConnectStages.length;
	}
	
	public boolean isClientGIPCConnectComplete() {
		return !doGIPC || (isClientGIPCConnectFSMComplete() && hasGIPCListeners[0] && hasGIPCListeners[1] && hasGIPCListeners[2]);
	}
	
	public boolean isClientConnectComplete() {
		return isClientNIOConnectComplete() && isClientRMIConnectComplete() && isClientGIPCConnectComplete();
	}

	public boolean canProcessAccept() {
		return true;//connectStage >= 7;
	}
	
	public boolean isServerAcceptComplete() {
		return isServerNIOAcceptComplete() && isServerGIPCAcceptComplete();
	}
	
	public boolean isServerNIOAcceptComplete() {
		return !doNIO || serverNIOAcceptStage == serverNIOAcceptStages.length;
	}
	
	public boolean isServerGIPCAcceptComplete() {
		return !doGIPC || serverGIPCAcceptStage == serverGIPCAcceptStages.length;
	}
	
	public boolean checkServerNIOSetup(String line) {
		Tracer.info(this, "Checking for line matching: " + serverNIOSetupStages[serverNIOSetupStage]);
		if (line.startsWith(TRACER_PREFIX) && serverNIOSetupStages[serverNIOSetupStage].matcher(line).matches()) {
			serverNIOSetupStage++;
			return true;
		}
		return false; 
	}
	
	public boolean checkServerRMISetup(String line) {
		Tracer.info(this, "Checking for line matching: " + serverRMISetupStages[serverRMISetupStage]);
		if (line.startsWith(TRACER_PREFIX) && serverRMISetupStages[serverRMISetupStage].matcher(line).matches()) {
			serverRMISetupStage++;
			return true;
		}
		return false; 
	}
	
	public boolean checkServerGIPCSetup(String line) {
		if (!isServerGIPCSetupFSMComplete()) {
			Tracer.info(this, "Checking for line matching: " + serverGIPCSetupStages[serverGIPCSetupStage]);
			if (line.startsWith(TRACER_PREFIX) && serverGIPCSetupStages[serverGIPCSetupStage].matcher(line).matches()) {
				serverGIPCSetupStage++;
				return true;
			}
		}
		if (!serverRegisteredGIPCObject && checkForGIPCObjectRegistered(line)) {
			serverRegisteredGIPCObject = true;
			return true;
		}
		return false; 
	}
	
	public boolean checkClientNIOConnect(String line) {
		if (!isClientNIOConnectFSMComplete()) {
			Tracer.info(this, "Checking for line matching: " + clientNIOConnectStages[clientNIOConnectStage]);
			if (line.startsWith(TRACER_PREFIX) && clientNIOConnectStages[clientNIOConnectStage].matcher(line).matches()) {
				clientNIOConnectStage++;
				return true;
			}
		}
		if (!hasNIOListeners[0] && checkForPropertyChangeListener(line)) {
			hasNIOListeners[0] = true;   
			return true;
		} else if (!hasNIOListeners[1] && checkForReadListener(line)) {
			hasNIOListeners[1] = true;
			return true;
		}
		return false; 
	}
	
	public boolean checkClientRMIConnect(String line) {
		if (!isClientRMIConnectFSMComplete()) {
			Tracer.info(this, "Checking for line matching: " + clientRMIConnectStages[clientRMIConnectStage]);
			if(line.startsWith(TRACER_PREFIX) && clientRMIConnectStages[clientRMIConnectStage].matcher(line).matches()) {
				clientRMIConnectStage++;
				return true;
			}
		}
		if (!hasRMIListeners[0] && checkForPropertyChangeListener(line)) {
			hasRMIListeners[0] = true;
			return true;
		}
		return false; 
	}
	
	public boolean checkClientGIPCConnect(String line) {
		if (!isClientGIPCConnectFSMComplete()) {
			Tracer.info(this, "Checking for line matching: " + clientGIPCConnectStages[clientGIPCConnectStage]);
			if (line.startsWith(TRACER_PREFIX) && clientGIPCConnectStages[clientGIPCConnectStage].matcher(line).matches()) {
				clientGIPCConnectStage++;
				return true;
			}
		}
		if (!hasGIPCListeners[0] && checkForPropertyChangeListener(line)) {
			hasGIPCListeners[0] = true;   
			return true;
		} else if (!hasGIPCListeners[1] && checkForReadListener(line)) {
			hasGIPCListeners[1] = true;
			return true;
		} else if (!hasGIPCListeners[2] && checkForWriteListener(line)) {
			hasGIPCListeners[2] = true;
			return true;
		}
		return false; 
	}
	
	public boolean checkServerNIOAccept(String line) {
		Tracer.info(this, "Checking for line matching: " + serverNIOAcceptStages[serverNIOAcceptStage]);
		if (line.startsWith(TRACER_PREFIX) && serverNIOAcceptStages[serverNIOAcceptStage].matcher(line).matches()) {
			serverNIOAcceptStage++;
			return true;
		}
		return false;
	}
	
	public boolean checkServerGIPCAccept(String line) {
		Tracer.info(this, "Checking for line matching: " + serverGIPCAcceptStages[serverGIPCAcceptStage]);
		if (line.startsWith(TRACER_PREFIX) && serverGIPCAcceptStages[serverGIPCAcceptStage].matcher(line).matches()) {
			serverGIPCAcceptStage++;
			return true;
		}
		return false;
	}
	
	public boolean checkForReadListener(String line) {
		Tracer.info(this, "Checking for line matching: " + readListenerPattern);
		return line.startsWith(TRACER_PREFIX) && readListenerPattern.matcher(line).matches();
	}
	
	public boolean checkForWriteListener(String line) {
		Tracer.info(this, "Checking for line matching: " + writeListenerPattern);
		return line.startsWith(TRACER_PREFIX) && writeListenerPattern.matcher(line).matches();
	}
	
	public boolean checkForPropertyChangeListener(String line) {
		Tracer.info(this, "Checking for line matching: " + propertyChangeListenerPattern);
		return line.startsWith(TRACER_PREFIX) && propertyChangeListenerPattern.matcher(line).matches();
	}
	
	public boolean checkForGIPCObjectRegistered(String line) {
		Tracer.info(this, "Checking for line matching: " + gipcObjectRegisteredPattern);
		return line.startsWith(TRACER_PREFIX) && gipcObjectRegisteredPattern.matcher(line).matches();
	}
	
	public String getListNotFoundSource() {
		if (!isServerSetupComplete()) {
			if (!isServerNIOSetupComplete()) {
				return "Server enabling NIO";
			} else if (!isServerRMISetupComplete()) {
				return "Server enabling RMI";
			} else if (!isServerGIPCSetupComplete()) {
				return "Server enabling GIPC";
			}
		} else if (!isClientConnectComplete()) {
			if (!isClientNIOConnectComplete()) {
				return "Client connecting with NIO";
			} else if (!isClientRMIConnectComplete()) {
				return "Client connecting with RMI";
			} else if (!isClientGIPCConnectComplete()) {
				return "Client connecting with GIPC";
			}
		} else if (!isServerAcceptComplete()) {
			if (!isServerNIOAcceptComplete()) {
				return "Server accepting NIO connection";
			} else if (!isServerGIPCAcceptComplete()) {
				return "Server accepting GIPC connection";
			} 
		}
		return "";
	}
	
	public String getLastNotFound() {
		if (!isServerSetupComplete()) {
			if (!isServerNIOSetupComplete()) {
				return serverNIOSetupStages[serverNIOSetupStage].pattern();
			} else if (!isServerRMISetupComplete()) {
				return serverRMISetupStages[serverRMISetupStage].pattern();
			} else if (!isServerGIPCSetupComplete()) {
				if (!isServerGIPCSetupFSMComplete()) {
					return serverGIPCSetupStages[serverGIPCSetupStage].pattern();
				} else if (!serverRegisteredGIPCObject) {
					return gipcObjectRegisteredPattern.pattern();
				} 
			}
		} else if (!isClientConnectComplete()) {
			if (!isClientNIOConnectComplete()) {
				if (!isClientNIOConnectFSMComplete()) {
					return clientNIOConnectStages[clientNIOConnectStage].pattern();
				} else if (!hasNIOListeners[0]) {
					return propertyChangeListenerPattern.pattern();
				} else if (!hasNIOListeners[1]) {
					return readListenerPattern.pattern();
				}
			} else if (!isClientRMIConnectComplete()) {
				if (!isClientRMIConnectFSMComplete()) {
					return clientRMIConnectStages[clientRMIConnectStage].pattern();
				} else if (!hasRMIListeners[0]) {
					return propertyChangeListenerPattern.pattern();
				}
			} else if (!isClientGIPCConnectComplete()) {
				if (!isClientGIPCConnectFSMComplete()) {
					return clientGIPCConnectStages[clientGIPCConnectStage].pattern();
				} else if (!hasGIPCListeners[0]) {
					return propertyChangeListenerPattern.pattern();
				} else if (!hasGIPCListeners[1]) {
					return readListenerPattern.pattern();
				} else if (!hasGIPCListeners[2]) {
					return writeListenerPattern.pattern();
				}
			}
		} else if (!isServerAcceptComplete()) {
			if (!isServerNIOAcceptComplete()) {
				return serverNIOAcceptStages[serverNIOAcceptStage].pattern();
			} else if (!isServerGIPCAcceptComplete()) {
				return serverGIPCAcceptStages[serverGIPCAcceptStage].pattern();
			} 
		}
		return "";
	}
}
