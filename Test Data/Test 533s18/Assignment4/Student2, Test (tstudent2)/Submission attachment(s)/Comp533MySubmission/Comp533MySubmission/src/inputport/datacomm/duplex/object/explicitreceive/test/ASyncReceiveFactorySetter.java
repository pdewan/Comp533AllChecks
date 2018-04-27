package inputport.datacomm.duplex.object.explicitreceive.test;

import inputport.datacomm.duplex.object.DuplexObjectInputPortSelector;
import inputport.datacomm.duplex.object.explicitreceive.AnInheritingDuplexIObjectInputPortWithSyncReceiveFactory;
import inputport.datacomm.duplex.object.explicitreceive.AnObjectDuplexInputPortWithSyncReceiveFactory;
import inputport.datacomm.simplex.object.AnObjectInputPortFactory;
import examples.gipc.counter.customization.ACustomDuplexObjectInputPortFactory;
import examples.gipc.counter.customization.FactorySetter;

public class ASyncReceiveFactorySetter implements FactorySetter {

	@Override
	public void setFactories() {
		 
			DuplexObjectInputPortSelector.setDuplexInputPortFactory(
					new AnInheritingDuplexIObjectInputPortWithSyncReceiveFactory());
		
	}

}
