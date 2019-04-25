import java.net.URI;
 
import javax.websocket.ClientEndpoint;
import javax.websocket.CloseReason;
import javax.websocket.ContainerProvider;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

@ClientEndpoint
public class Messaging {
    Session userSession = null;
 
    public Messaging(URI endpointURI) {
        try {
            WebSocketContainer container = ContainerProvider
                    .getWebSocketContainer();
            container.connectToServer(this, endpointURI);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
 
    @OnOpen
    public void onOpen(Session userSession) {
        this.userSession = userSession;
    }
 
    @OnClose
    public void onClose(Session userSession, CloseReason reason) {
        this.userSession = null;
    }

    @OnMessage
    public void onMessage(String message) {
    	Controller.receivedMessage(message);
    }

    public void sendMessage(String message, String to) {
    	if (this.userSession.isOpen()) {
    		this.userSession.getAsyncRemote().sendText("to " + to);
            this.userSession.getAsyncRemote().sendText(message);
    	}
    }
}