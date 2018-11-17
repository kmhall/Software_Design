// Fig. 28.8: ServerTest.java
// Class that tests the Server.

import javax.swing.*;


/**
 * ServerTest is a class that creates an instance of Server, sets the default closing operation,
 * and calls the waitForPackets() which waits for packets to be received from the client.
 */
public class ServerTest {
    public static void main(String[] args) {
        Server application = new Server(); // create server
        application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        application.waitForPackets(); // run server application
    }
}

