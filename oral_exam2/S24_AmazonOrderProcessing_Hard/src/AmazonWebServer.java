import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class AmazonWebServer implements Runnable {

        private final BlockingBuffer webServerToShippingCenter1; // reference to shared object
        private final BlockingBuffer webServerToShippingCenter2;

        private final File file = new File("./oral_exam2/S24_AmazonOrderProcessing_Hard/S24_AmazonOrderProcessing_OrdersFile.csv");
        private final String [] locations = new String[]{"Los Angeles","San Francisco", "Seattle", "Denver"};
        private final List shippingCenter1Locations = new ArrayList(Arrays.asList(locations));

        // constructor
        public AmazonWebServer(BlockingBuffer webServerToShippingCenter1,BlockingBuffer webServerToShippingCenter2) {
            this.webServerToShippingCenter1 = webServerToShippingCenter1;
            this.webServerToShippingCenter2 = webServerToShippingCenter2;
        }

    public void sendOrder(BlockingBuffer buffer,AmazonOrder order){
        try
        {
            buffer.blockingPut(order); // set value in buffer
        } catch (InterruptedException exception) {
            Thread.currentThread().interrupt();
        }

    }

        public void run() {
            AmazonOrder order = null;

            try {
                Scanner inputStream = new Scanner(file);
                inputStream.useDelimiter("\n");

                if(inputStream.hasNext()) {
                    String line = inputStream.next(); //Skips the first line

                    while(inputStream.hasNext()) {
                        line = inputStream.next();
                        String[] splitLine = line.split(",");

                        order = new AmazonOrder(splitLine);

                        if(shippingCenter1Locations.contains(order.getCity())){
                            sendOrder(webServerToShippingCenter1,order);
                        }
                        else{
                            sendOrder(webServerToShippingCenter2,order);
                        }
                    }



                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }



            System.out.println("\nWeb Server Terminating\n");

            /**
             * Ship terminating key through process
             *
             * Termination is happening too soon, therefore the order gets sent down the incorrect process.
             */
//            order.setTerminatingKey();
//            sendOrder(webServerToShippingCenter1,order);
//            sendOrder(webServerToShippingCenter2,order);

        }

    } // end class Producer

