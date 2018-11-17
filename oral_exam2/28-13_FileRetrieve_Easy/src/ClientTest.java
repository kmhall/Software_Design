// Fig. 28.10: ClientTest.java
// Class that tests the Client.

import javax.swing.*;

/**
 * ClientTest is a class that creates an instance of Client, sets the default closing operation,
 * and calls the waitForPackets() which waits for packets to be received from the server.
 */
public class ClientTest {
    public static void main(String[] args) {
        Client application = new Client(); // create client
        application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        application.waitForPackets(); // run client application
    }
}  // end class ClientTest

