// Fig. 28.8: ServerTest.java
// Class that tests the Server.

import javax.swing.*;

public class ServerTest {
    public static void main(String[] args) {
        Server application = new Server(); // create server
        application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        application.waitForPackets(); // run server application
    }
}

