// Fig. 28.10: ClientTest.java
// Class that tests the Client.

import javax.swing.*;

public class ClientTest {
    public static void main(String[] args) {
        Client application = new Client(); // create client
        application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        application.waitForPackets(); // run client application
    }
}  // end class ClientTest

