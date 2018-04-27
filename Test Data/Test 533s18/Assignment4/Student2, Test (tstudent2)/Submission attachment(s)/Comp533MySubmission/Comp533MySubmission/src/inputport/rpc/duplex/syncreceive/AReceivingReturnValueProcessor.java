package inputport.rpc.duplex.syncreceive;

import inputport.rpc.duplex.MaybeProcessReturnValue;
import inputport.rpc.duplex.RPCReturnValue;

public class AReceivingReturnValueProcessor implements MaybeProcessReturnValue {

	@Override
	public boolean maybeProcessReturnValue(String source, Object message) {
		return  message instanceof RPCReturnValue;
	}
	

}
