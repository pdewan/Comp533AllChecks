package nio.manager.simulation;

import nio.manager.simulation.server.AnNIOManagerSimulationServer;
import util.annotations.Tags;
import util.tags.DistributedTags;
import util.trace.bean.BeanTraceUtility;
import util.trace.factories.FactoryTraceUtility;
import util.trace.port.nio.NIOTraceUtility;
@Tags({DistributedTags.SERVER})
public class NIOManagerSimulationServerLauncher {
	public static void main(String[] args) {
		FactoryTraceUtility.setTracing();
		BeanTraceUtility.setTracing();
		NIOTraceUtility.setTracing();
		AnNIOManagerSimulationServer.launch(args);
	}

}
