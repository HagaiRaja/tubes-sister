import java.io.IOException;
import java.util.*;
import java.util.Map;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;;

@ServerEndpoint("/endpoint/{owner}")
public class Server {
	
	static Map<String, Session> sessions = new HashMap<String, Session>();
	String to = null;
	
	@OnOpen
	public void onOpen(Session session, @PathParam("owner") String owner) {
		System.out.println("onOpen::" + session.getId());
		sessions.put(session.getId(), session);
        session.getUserProperties().put("owner", owner);
        System.out.println("open done");
	}
	
	@OnClose
	public void onClose(Session session) {
		System.out.println("onClose::" + session.getId());
		sessions.remove(session.getId());
	}
	
	@OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("onMessage::From=" + session.getId() + " Message=" + message);
        
        if (message.startsWith("to")) {
        	String []s = message.split("\\s+");
        	this.to = s[1];
        }
        else {
            for (Session s:sessions.values()) {
                if (s.isOpen() && this.to.equals(s.getUserProperties().get("owner"))) {
                    s.getAsyncRemote().sendText(message);
                }
            }
        }
    }
	
	@OnError
    public void onError(Throwable t) {
        System.out.println("onError::" + t.getMessage());
    }
}
