import java.io.File;
import java.io.FileNotFoundException;
import java.security.SecureRandom;
import java.util.Scanner;

public class AmazonWebServer implements Runnable {

        private static final SecureRandom generator = new SecureRandom();
        private final Buffer sharedLocation; // reference to shared object

        // constructor
        public AmazonWebServer(Buffer sharedLocation) {
            this.sharedLocation = sharedLocation;
        }

        public void run() {
            AmazonOrder order = null;

            File file = new File("./oral_exam2/S24_AmazonOrderProcessing_Hard/S24_AmazonOrderProcessing_OrdersFile.csv");

            try {
                Scanner inputStream = new Scanner(file);
                inputStream.useDelimiter("\n");

                if(inputStream.hasNext()) {
                    //Skips the first line
                    String line = inputStream.next();
                    while(inputStream.hasNext()) {
                        line = inputStream.next();
                        String[] splitLine = line.split(",");

                         order = new AmazonOrder(splitLine);
                        try // sleep 0 to 3 seconds, then place value in Buffer
                        {
                            Thread.sleep(generator.nextInt(500)); // random sleep
                            sharedLocation.blockingPut(order); // set value in buffer
                        } catch (InterruptedException exception) {
                            Thread.currentThread().interrupt();
                        }
                    }
                    try // sleep 0 to 3 seconds, then place value in Buffer
                    {
                        order.setTerminatingKey();
                        Thread.sleep(generator.nextInt(500)); // random sleep
                        sharedLocation.blockingPut(order); // set value in buffer
                    } catch (InterruptedException exception) {
                        Thread.currentThread().interrupt();
                    }

                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }



            System.out.println("Web Server Terminating");
        }
    } // end class Producer

