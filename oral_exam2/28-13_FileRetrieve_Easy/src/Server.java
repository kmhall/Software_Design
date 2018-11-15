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

public class Server extends JFrame {
    private JTextArea displayArea; // displays packets received
    private DatagramSocket socket; // socket to connect to client

    // set up GUI and DatagramSocket
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

    // wait for packets to arrive, display data and echo packet to client
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

                /**
                 * ./oral_exam2/28-13_FileRetrieve_Easy/textFile.txt
                 */


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

    // echo packet to client
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

    // manipulates displayArea in the event-dispatch thread
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