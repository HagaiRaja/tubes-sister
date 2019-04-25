import java.net.URI;

public class Controller {
	Messaging clientEndPoint = null;
	String clientPort = null;
	Messaging peer1EndPoint = null;
	String peer1Port = null;
	Messaging peer2EndPoint = null;
	String peer2Port = null;
	
	public Controller(String clientPort, String peer1Port, String peer2Port) throws Exception {    	
        this.clientPort = clientPort;
        this.peer1Port = peer1Port;
        this.peer2Port = peer2Port;
		clientEndPoint = new Messaging(new URI("ws://localhost:" + clientPort + "/Server/endpoint/" + clientPort));
		peer1EndPoint = new Messaging(new URI("ws://localhost:" + peer1Port + "/Server/endpoint/" + clientPort));
		peer2EndPoint = new Messaging(new URI("ws://localhost:" + peer2Port + "/Server/endpoint/" + clientPort));
		
        while (true) {
		    broadcastMessage("Hi from " + clientPort);
            Thread.sleep(5000);
        }
	}
	
	public static void receivedMessage(String message) {
		System.out.println(message);
		
	}
	
	private void broadcastMessage(String message) {
	    peer1EndPoint.sendMessage(message, peer1Port);
	    peer2EndPoint.sendMessage(message, peer2Port);
	}
}
