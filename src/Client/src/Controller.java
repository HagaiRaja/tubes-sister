import java.net.URI;

public class Controller {
	Messaging clientEndPoint = null;
	String clientPort = null;
	
	public Controller(String clientPort/*, String peer1Port, String peer2Port*/) throws Exception {    	
        this.clientPort = clientPort;
		clientEndPoint = new Messaging(new URI("ws://localhost:" + clientPort + "/Server/endpoint"));
//		clientEndPoint.addMessageHandler(new Messaging.MessageHandler() {
//			public void handleMessage(String message) {
//		    	clientEndPoint.sendMessage(message);
//		    }
//		});
//		final Messaging peer1EndPoint = new Messaging(new URI("ws://localhost:" + peer1Port + "/Server/endpoint"));
//		final Messaging peer2EndPoint = new Messaging(new URI("ws://localhost:" + peer2Port + "/Server/endpoint"));
 
        while (true) {
            clientEndPoint.sendMessage("Hi to myself!! (" + clientPort + ")");
//		    peer1EndPoint.sendMessage("Hi to peer1 from " + clientPort + "!! (" + peer1Port + ")");
//		    peer2EndPoint.sendMessage("Hi to peer2 from " + clientPort + "!! (" + peer2Port + ")");
            Thread.sleep(5000);
        }
	}
	
	public static void receivedMessage(String message) {
		System.out.println("Received message from messaging : " + message);
	}
	
	public static void sendMessage(String message) {
		
	}
}
