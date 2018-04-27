package inputport.rpc.duplex.syncreceive;

import inputport.rpc.duplex.DuplexRPCInputPort;
import inputport.rpc.duplex.DuplexSentCallCompleter;
import inputport.rpc.duplex.DuplexSentCallCompleterFactory;
import inputport.rpc.duplex.LocalRemoteReferenceTranslator;

public class ASyncReceiveSentCallCompleterCallFactory  
             implements DuplexSentCallCompleterFactory {

	@Override
	public DuplexSentCallCompleter 
	    createDuplexSentCallCompleter(DuplexRPCInputPort aPort, LocalRemoteReferenceTranslator aTranslator) {
		
 
		return new ASyncReceiveSentCallCompleter(aPort, aTranslator);
	}
}


