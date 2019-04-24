import java.net.URI;

public class Main {
	public static void main(String args[]) throws Exception {
		Editor e = new Editor();
		
		// Init client websocket
		String clientPort = args[0];
//    	String peer1Port = args[1];
//    	String peer2Port = args[2];
    	
        final Messaging clientEndPoint = new Messaging(new URI("ws://localhost:" + clientPort + "/Server/endpoint"));
//        clientEndPoint.addMessageHandler(new Messaging.MessageHandler() {
//            public void handleMessage(String message) {
//            	clientEndPoint.sendMessage(message);
//            }
//        });
//        final Messaging peer1EndPoint = new Messaging(new URI("ws://localhost:" + peer1Port + "/Server/endpoint"));
//        final Messaging peer2EndPoint = new Messaging(new URI("ws://localhost:" + peer2Port + "/Server/endpoint"));
 
        while (true) {
            clientEndPoint.sendMessage("Hi to myself!! (" + clientPort + ")");
//            peer1EndPoint.sendMessage("Hi to peer1 from " + clientPort + "!! (" + peer1Port + ")");
//            peer2EndPoint.sendMessage("Hi to peer2 from " + clientPort + "!! (" + peer2Port + ")");
            Thread.sleep(5000);
        }
	}
}
