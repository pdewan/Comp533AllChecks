package editor.ot;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import util.misc.Common;
import util.session.ASentMessage;
import util.session.MessageProcessor;
import util.session.SentMessage;
import util.session.SentMessageType;
import util.session.ServerMessageFilter;
import util.trace.Tracer;
import widgets.Edit;
// A multicaster sender that puts the message in multiple queues, one for each client
// Each queue is a separate OT Manager
public class AnOTServerMessageQueuer implements ServerMessageFilter{
	Map<String, OTManager> clientToOTManager = new HashMap();
	MessageProcessor<SentMessage> sentMessageQueue;	
	@Override
	public synchronized void userJoined(String aSessionName, String anApplicationName, String aUserName) {
		OTManager otManager = new AnOTManager(true);
		clientToOTManager.put(aUserName, otManager);
		//otManager.setSentMessageQueue(sentMessageQueue);
		
	}

	@Override
	public void userLeft(String aSessionName, String anApplicationName, String aUserName) {
		// TODO Auto-generated method stub
		
	}
	/*
	 message sent by user u is a message received by all users, U, other than u.	 
	 so it must be treated as a received message by server representing U -u
	 transform this message with respect to all messages sent to u by  the server representing itself as U - u.
	 these messages are stored in u's ot Manager.
	 send transformed message to U - u.
	 store this message, with appropriate time stamp, as sent message in otManagers of U - u
	 each ot Manager will time stamp differently
	 this means message must be unicast
	 */
		
	@Override
	public synchronized void filterMessage(SentMessage message) {		
		if (!message.isUserMessage() || message.getSentMessageType() != SentMessageType.Others) {
			sentMessageQueue.processMessage(message);
			return;
		}
		Tracer.info(this, "START PUT in OTSERVER QUEUE");
		EditWithOTTimeStamp receivedEditWithOTTimeStamp = (EditWithOTTimeStamp) message.getUserMessage();
		OTManager sentOTManager = clientToOTManager.get(message.getSendingUser());
		Tracer.info(this, "OT Manager:" + message.getSendingUser());
		//transform  message with respect to all messages sent to u by  the server representing itself as U - u
		sentOTManager.processTimeStampedEdit(receivedEditWithOTTimeStamp);
		// this will be the edit sent to U - u
		Edit sentEdit = receivedEditWithOTTimeStamp.getTransformableEdit().getEdit();
		//iterate thru U - u and ot Managers of U - u.		
		Set<String> allUsers = clientToOTManager.keySet();
		Tracer.info("All Users n OTSERVER QUEUE:" + allUsers);
		for (String user:allUsers) {
			if (user.equals(message.getSendingUser()))
				continue;
			OTManager receivingOtManager = clientToOTManager.get(user);
			// transform the headers in the message which will include transformed edit
			SentMessage unicastMessage = ASentMessage.toSpecificUser(message, user);			
			// this is the server's sent edit to each U - u
			//Edit clonedEdit = (Edit) Misc.deepCopy(sentEdit);
			// sent edit does not have to be cloned in the sentMessage output buffer
			Tracer.info(this, "OT Manager:" + user);
			EditWithOTTimeStamp sentEditWithOTTimeStamp = receivingOtManager.processSentEdit(sentEdit);
			unicastMessage.setUserMessage(sentEditWithOTTimeStamp);
			Tracer.info(this, "Unicasting message to: " + user);
			sentMessageQueue.processMessage(unicastMessage);
			// store a different copy in the buffer
			SentMessage clonedUnicastMessage = (SentMessage) Common.deepCopy(unicastMessage);
			//Message.info("OT Manager:" + user);
			receivingOtManager.storeSentMessage(clonedUnicastMessage);
			//otManager.put(receivedMessage);
		}
		Tracer.info(this, "END PUT in OTSERVER QUEUE");		
	}

	@Override
	public void setMessageProcessor(MessageProcessor<SentMessage> theMesssageProcessor) {
		sentMessageQueue = theMesssageProcessor;
		
	}

}
