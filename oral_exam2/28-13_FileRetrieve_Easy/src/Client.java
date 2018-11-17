// Fig. 28.9: Client.java
// Client side of connectionless client/server computing with datagrams.

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 * Client is a class that represents a client connecting to a server.
 * This class has a GUI that displays a JTextField to ask for a file and
 * a JTextaArea to display the retrieved file.
 * @see JFrame
 */
public class Client extends JFrame {
    private JTextField enterField; // for entering messages
    private JTextArea displayArea; // for displaying messages
    private DatagramSocket socket; // socket to connect to server

    /**
     * Constructs a Client. Adds components to the JFrame, adds an action listener to the text field,
     * and create a DatagramSocket for sending and receiving packets.
     */
    public Client() {
        super("Client");

        enterField = new JTextField("textFile.txt");
        enterField.addActionListener(
                new ActionListener() {

                    /**
                     * actionPerformed attempts to create and send a packet.
                     * @param event ActionEvent that handles the enter key being pressed in the JTextField
                     */
                    public void actionPerformed(ActionEvent event) {
                        try // create and send packet
                        {
                            // get message from textfield
                            String message = event.getActionCommand();
                            displayArea.append("\nSending packet containing: " +
                                    message + "\n");

                            byte[] data = message.getBytes(); // convert to bytes

                            // create sendPacket
                            DatagramPacket sendPacket = new DatagramPacket(data,
                                    data.length, InetAddress.getLocalHost(), 5000);

                            socket.send(sendPacket); // send packet
                            displayArea.append("Packet sent\n");
                            displayArea.setCaretPosition(
                                    displayArea.getText().length());
                        } catch (IOException ioException) {
                            displayMessage(ioException + "\n");
                            ioException.printStackTrace();
                        }
                    }
                }
        );

        add(enterField, BorderLayout.NORTH);

        displayArea = new JTextArea();
        add(new JScrollPane(displayArea), BorderLayout.CENTER);

        setSize(400, 300); // set window size
        setVisible(true); // show window

        try // create DatagramSocket for sending and receiving packets
        {
            socket = new DatagramSocket();
        } catch (SocketException socketException) {
            socketException.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * waitForPackets waits for packets to arrive from Server, displays packet contents
     */
    public void waitForPackets() {
        while (true) {
            try // receive packet and display contents
            {
                byte[] data = new byte[60000]; // set up packet
                DatagramPacket receivePacket = new DatagramPacket(
                        data, data.length);

                socket.receive(receivePacket); // wait for packet

                // display packet contents
                displayMessage("\nPacket received:" +
                        "\nFrom host: " + receivePacket.getAddress() +
                        "\nHost port: " + receivePacket.getPort() +
                        "\nLength: " + receivePacket.getLength() +
                        "\nContaining:\n\t" + new String(receivePacket.getData(),
                        0, receivePacket.getLength()));
            } catch (IOException exception) {
                displayMessage(exception + "\n");
                exception.printStackTrace();
            }
        }
    }

    /**
     * displayMessage manipulates displayArea in the even dispatch thread.
     * This is done in a separate thread asynchronously.
     * @param messageToDisplay the message to be displayed on the JTextArea
     */
    private void displayMessage(final String messageToDisplay) {
        //Add updating the display area to the AWT event dispatching thread.
        SwingUtilities.invokeLater(
                new Runnable() {
                    /**
                     * The contents of run are added to the event dispatch thread within an instance of runnable.
                     */
                    public void run() // updates displayArea
                    {
                        displayArea.append(messageToDisplay);
                    }
                }
        );
    }
}