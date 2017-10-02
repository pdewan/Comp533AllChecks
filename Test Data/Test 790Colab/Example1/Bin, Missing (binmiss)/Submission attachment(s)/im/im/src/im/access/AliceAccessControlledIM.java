package im.access;

import im.AliceIM;
import im.AnIMComposerAndLauncher;
import trace.im.aware.AwareIMTracerSetter;
import util.session.Communicator;
import util.trace.TraceableInfo;

public class AliceAccessControlledIM extends AliceIM{	
	public static void main (String[] args) {
//		Tracer.showInfo(true);
		TraceableInfo.setPrintSource(true);
		AwareIMTracerSetter.traceAwareIM();
		String[] launcherArgs = {SESSION_SERVER_HOST, SESSION_NAME, USER_NAME,  APPLICATION_NAME, Communicator.DIRECT};
		(new AnAccessControlledIMComposerAndLauncher()).composeAndLaunch(launcherArgs);
	}	
}
