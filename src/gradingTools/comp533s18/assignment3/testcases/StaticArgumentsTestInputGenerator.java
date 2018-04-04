package gradingTools.comp533s18.assignment3.testcases;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import util.pipe.AnAbstractInputGenerator;

public class StaticArgumentsTestInputGenerator extends AnAbstractInputGenerator {
	private static final String TRACER_PREFIX = "I***";
	
	private static final String SERVER_NAME = "Server";
	private static final String CLIENT_NAME = "Client";
	
	private final boolean doNIO;
	private final boolean doRMI;
	private final boolean doGIPC;
	
	// match SocketChannelBound trace
	// capture group 1 = local addr
	// capture group 2 = local addr
	// capture group 3 = port
	private static final Pattern NIO_SERVER_LINE_TO_INFO = Pattern.compile(".*?\\{main\\}\\(SocketChannelBound\\).*?\\)[ ]*([\\w\\d\\.]*)/?([\\w\\d\\.]*)?:(\\d*).*", Pattern.DOTALL);

	// match SocketChannelBound trace
	// capture group 1 = remote addr
	// capture group 2 = remote addr
	// capture group 3 = port
	private static final Pattern NIO_CLIENT_LINE_TO_INFO = Pattern.compile(".*?\\{main\\}\\(SocketChannelConnectRequested\\).*?\\)[ ]*\\(([\\w\\d\\.]*)/?([\\w\\d\\.]*)?:(\\d*).*", Pattern.DOTALL);

	// match RMIRegistryLocated trace
	// capture group 1 = remote addr
	// capture group 2 = port
	private static final Pattern RMI_LINE_TO_INFO = Pattern.compile(".*?\\{main\\}\\(RMIRegistryLocated\\).*?\\)[ ]*([\\w\\d\\.]*):(\\d*).*", Pattern.DOTALL);

	// match GIPCRegistryCreated trace
	// capture group 1 = port
	private static final Pattern GIPC_SERVER_LINE_TO_INFO = Pattern.compile(".*?\\{main\\}\\(GIPCRegistryCreated\\).*?\\)[ ]*(\\d*).*", Pattern.DOTALL);

	// match GIPCRegistryLocated trace
	// capture group 1 = remote addr
	// capture group 2 = port
	// capture group 3 = local name
	private static final Pattern GIPC_CLIENT_LINE_TO_INFO = Pattern.compile(".*?\\{main\\}\\(GIPCRegistryLocated\\).*?\\)[ ]*([\\w\\d\\.]*):(\\d*)<-(.*?)$.*", Pattern.DOTALL);
	
	private static final String HEADLESS_LINE = "Headless program, not generating UI";
	
	private boolean quitSubmitted = false;
	
	private boolean foundServerNIOInfo = false;
	private boolean foundServerRMIInfo = false;
	private boolean foundServerGIPCInfo = false;
	
	private boolean foundClientNIOInfo = false;
	private boolean foundClientRMIInfo = false;
	private boolean foundClientGIPCInfo = false;
	
	private boolean isHeadless = false;
	
	private String serverNIOLocalAddr1;
	private String serverNIOLocalAddr2;
	private String serverNIOLocalPort;
	private String serverRMIRemoteAddr;
	private String serverRMIRemotePort;
	private String serverGIPCLocalPort;
	
	private String clientNIORemoteAddr1;
	private String clientNIORemoteAddr2;
	private String clientNIORemotePort;
	private String clientRMIRemoteAddr;
	private String clientRMIRemotePort;
	private String clientGIPCRemoteAddr;
	private String clientGIPCRemotePort;
	private String clientGIPCLocalName;
	
	public StaticArgumentsTestInputGenerator(boolean doNIO, boolean doRMI, boolean doGIPC) {
		this.doNIO = doNIO;
		this.doRMI = doRMI;
		this.doGIPC = doGIPC;
	}
	
	@Override
	public void newOutputLine(String aProcessName, String anOutputLine) {
		if (aProcessName.equals(SERVER_NAME)) {
			if (doNIO && !foundServerNIOInfo() && checkForServerNIOInfo(anOutputLine)) {
			} else if (doRMI && !foundServerRMIInfo() && checkForServerRMIInfo(anOutputLine)) {
			} else if (doGIPC && !foundServerGIPCInfo() && checkForServerGIPCInfo(anOutputLine)) {
			}
		} else if (aProcessName.equals(CLIENT_NAME)) {
			if (checkForHeadless(anOutputLine)) {
			} else if (doNIO && !foundClientNIOInfo() && checkForClientNIOInfo(anOutputLine)) {
			} else if (doRMI && !foundClientRMIInfo() && checkForClientRMIInfo(anOutputLine)) {
			} else if (doGIPC && !foundClientGIPCInfo() && checkForClientGIPCInfo(anOutputLine)) {
			}
		}
		if (!quitSubmitted && foundServerInfo() && foundClientInfo()) {
			notifyNewInputLine(CLIENT_NAME, "q 0");
			notifyNewInputLine(SERVER_NAME, "q 0");
			quitSubmitted = true;
		}
	}
	
	public boolean didNIO() {
		return doNIO;
	}
	
	public boolean didRMI() {
		return doRMI;
	}
	
	public boolean didGIPC() {
		return doGIPC;
	}
	
	public boolean foundClientInfo() {
		return (!doNIO || foundClientNIOInfo()) && (!doRMI || foundClientRMIInfo()) && (!doGIPC || foundClientGIPCInfo());
	}

	public boolean foundServerInfo() {
		return (!doNIO || foundServerNIOInfo()) && (!doRMI || foundServerRMIInfo()) && (!doGIPC || foundServerGIPCInfo());
	}
	
	public boolean foundServerNIOInfo() {
		return foundServerNIOInfo;
	}
	public boolean foundServerRMIInfo() {
		return foundServerRMIInfo;
	}
	public boolean foundServerGIPCInfo() {
		return foundServerGIPCInfo;
	}
	
	public boolean foundClientNIOInfo() {
		return foundClientNIOInfo;
	}
	public boolean foundClientRMIInfo() {
		return foundClientRMIInfo;
	}
	public boolean foundClientGIPCInfo() {
		return foundClientGIPCInfo;
	}
	
	public boolean isHeadless() {
		return isHeadless;
	}
	
	public String[] getServerNIOInfo() {
		return new String[] {serverNIOLocalAddr1, serverNIOLocalAddr2, serverNIOLocalPort};
	}
	
	public String[] getClientNIOInfo() {
		return new String[] {clientNIORemoteAddr1, clientNIORemoteAddr2, clientNIORemotePort};
	}

	public String[] getServerRMIInfo() {
		return new String[] {serverRMIRemoteAddr, serverRMIRemotePort};
	}
	
	public String[] getClientRMIInfo() {
		return new String[] {clientRMIRemoteAddr, clientRMIRemotePort};
	}
	
	public String[] getServerGIPCInfo() {
		return new String[] {serverGIPCLocalPort};
	}
	
	public String[] getClientGIPCInfo() {
		return new String[] {clientGIPCRemoteAddr, clientGIPCRemotePort, clientGIPCLocalName};
	}
	
	private boolean checkForClientNIOInfo(String line) {
		if (line.startsWith(TRACER_PREFIX)) {
			Matcher match = NIO_CLIENT_LINE_TO_INFO.matcher(line);
			if (match.matches()) {
				clientNIORemoteAddr1 = match.group(1);
				clientNIORemoteAddr2 = match.group(2);
				clientNIORemotePort = match.group(3);
				foundClientNIOInfo = true;
				return true;
			}
		}
		return false;
	}
	
	private boolean checkForServerNIOInfo(String line) {
		if (line.startsWith(TRACER_PREFIX)) {
			Matcher match = NIO_SERVER_LINE_TO_INFO.matcher(line);
			if (match.matches()) {
				serverNIOLocalAddr1 = match.group(1);
				serverNIOLocalAddr2 = match.group(2);
				serverNIOLocalPort = match.group(3);
				foundServerNIOInfo = true;
				return true;
			}
		}
		return false;
	}
	
	private boolean checkForClientRMIInfo(String line) {
		if (line.startsWith(TRACER_PREFIX)) {
			Matcher match = RMI_LINE_TO_INFO.matcher(line);
			if (match.matches()) {
				clientRMIRemoteAddr = match.group(1);
				clientRMIRemotePort = match.group(2);
				foundClientRMIInfo = true;
				return true;
			}
		}
		return false;
	}
	
	private boolean checkForServerRMIInfo(String line) {
		if (line.startsWith(TRACER_PREFIX)) {
			Matcher match = RMI_LINE_TO_INFO.matcher(line);
			if (match.matches()) {
				serverRMIRemoteAddr = match.group(1);
				serverRMIRemotePort = match.group(2);
				foundServerRMIInfo = true;
				return true;
			}
		}
		return false;
	}
	
	private boolean checkForClientGIPCInfo(String line) {
		if (line.startsWith(TRACER_PREFIX)) {
			Matcher match = GIPC_CLIENT_LINE_TO_INFO.matcher(line);
			if (match.matches()) {
				clientGIPCRemoteAddr = match.group(1);
				clientGIPCRemotePort = match.group(2);
				clientGIPCLocalName = match.group(3);
				foundClientGIPCInfo = true;
				return true;
			}
		}
		return false;
	}
	
	private boolean checkForServerGIPCInfo(String line) {
		if (line.startsWith(TRACER_PREFIX)) {
			Matcher match = GIPC_SERVER_LINE_TO_INFO.matcher(line);
			if (match.matches()) {
				serverGIPCLocalPort = match.group(1);
				foundServerGIPCInfo = true;
				return true;
			}
		}
		return false;
	}
	
	private boolean checkForHeadless(String line) {
		if (HEADLESS_LINE.equals(line)) {
			isHeadless = true;
			return true;
		}
		return false;
	}
}
