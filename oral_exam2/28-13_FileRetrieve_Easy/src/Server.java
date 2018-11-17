// Fig. 28.7: Server.java
// Server side of connectionless client/server computing with datagrams.

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Scanner;

/**
 * Server is a class that waits for packets from the client and sends packets back to the client.
 * @see JFrame
 */
public class Server extends JFrame {
    private JTextArea displayArea; // displays packets received
    private DatagramSocket socket; // socket to connect to client

    /**
     * Constructs a Server. Sets up the GUI and Datagram socket connection.
     */
    public Server() {
        super("Server");

        displayArea = new JTextArea(); // create displayArea
        add(new JScrollPane(displayArea), BorderLayout.CENTER);
        setSize(400, 300); // set size of window
        setVisible(true); // show window

        try // create DatagramSocket for sending and receiving packets
        {
            socket = new DatagramSocket(5000);
        } catch (SocketException socketException) {
            socketException.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * waitForPackets tries to receive packets from the client, display it,
     * create a file from file name sent, and send the contents of the file
     * back to the client.
     */
    public void waitForPackets() {
        while (true) {
            try // receive packet, display contents, return copy to client
            {

                byte[] data = new byte[100]; // set up packet
                DatagramPacket receivePacket =
                        new DatagramPacket(data, data.length);

                socket.receive(receivePacket); // wait to receive packet

                // display information from received packet
                displayMessage("\nPacket received:" +
                        "\nFrom host: " + receivePacket.getAddress() +
                        "\nHost port: " + receivePacket.getPort() +
                        "\nLength: " + receivePacket.getLength() +
                        "\nContaining:\n\t" + new String(receivePacket.getData(),
                        0, receivePacket.getLength()));


                //Path to the file the client wants
                String path = new String(receivePacket.getData(), 0, receivePacket.getLength());

                String basePath = "./oral_exam2/28-13_FileRetrieve_Easy/textFiles/";
                //Create a file with the client's path
                System.out.println(basePath+path);
                File file = new File(basePath+path);


                String fileContents = "";
                if (file.isFile()) {
                    System.out.println("Correct file path");

                    //Read the data from the file
                    Scanner reader = new Scanner(file);

                    while (reader.hasNextLine()) {
                        fileContents += reader.nextLine() + "\n";
                    }
                    System.out.println(fileContents);

                } else {
                    System.out.println("Incorrect file path");

                    fileContents = "INVALID FILE: IT DOES NOT EXIST";
                }
                byte[] fileData = fileContents.getBytes();


                sendPacketToClient(receivePacket,fileData); // send packet to client


            } catch (IOException ioException) {
                displayMessage(ioException + "\n");
                ioException.printStackTrace();
            }
        }
    }

    /**
     * sendPacketToClient takes the DatagramPacket received and replaces the contents with
     * the contents of the file. It then sends the data back to the client and displays a message in the server window.
     *
     * @param receivePacket The DatagramPacket received from the client
     * @param newByte The file contents in a byte of data.
     * @throws IOException Exception thrown if data cannot be sent.
     */
    private void sendPacketToClient(DatagramPacket receivePacket,byte[] newByte)
            throws IOException {
        displayMessage("\n\nEcho data to client...");

        // create packet to send
        DatagramPacket sendPacket = new DatagramPacket(
                newByte, newByte.length,
                receivePacket.getAddress(), receivePacket.getPort());

        socket.send(sendPacket); // send packet to client
        displayMessage("Packet sent\n");
    }

    /**
     * displayMessage manipulates displayArea in the even dispatch thread.
     * This is done in a separate thread asynchronously.
     * @param messageToDisplay the message to be displayed on the JTextArea
     */
    private void displayMessage(final String messageToDisplay) {
        SwingUtilities.invokeLater(
                new Runnable() {
                    public void run() // updates displayArea
                    {
                        displayArea.append(messageToDisplay); // display message
                    }
                }
        );
    }
}