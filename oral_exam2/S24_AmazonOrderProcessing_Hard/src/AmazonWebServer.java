import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * AmazonWebServer is a class that converts a csv file to orders and sends orders to the correct distribution center.\
 * @see Runnable
 */
public class AmazonWebServer implements Runnable {

        private final BlockingBuffer webServerToShippingCenter1; // reference to shared object
        private final BlockingBuffer webServerToShippingCenter2;

        private final File file = new File("./oral_exam2/S24_AmazonOrderProcessing_Hard/S24_AmazonOrderProcessing_OrdersFile.csv");
        private final String [] locations = new String[]{"Los Angeles","San Fransisco", "Seattle", "Denver"};
        private final List shippingCenter1Locations = new ArrayList(Arrays.asList(locations));

    /**
     * Constructor for AmazonWebServer
     * @param webServerToShippingCenter1 BlockingBuffer for shipping center 1
     * @param webServerToShippingCenter2 BlockingBuffer for shipping center 2
     */
    public AmazonWebServer(BlockingBuffer webServerToShippingCenter1,BlockingBuffer webServerToShippingCenter2) {
            this.webServerToShippingCenter1 = webServerToShippingCenter1;
            this.webServerToShippingCenter2 = webServerToShippingCenter2;
    }

    /**
     * Sends order to blocking buffer
     * @param buffer BlockingBuffer to send to
     * @param order AmazonOrder to send
     */

    public void sendOrder(BlockingBuffer buffer,AmazonOrder order){
        try
        {
            buffer.blockingPut(order); // set value in buffer
        } catch (InterruptedException exception) {
            Thread.currentThread().interrupt();
        }

    }

    /**
     * Converts csv into AmazonOrders and sends order to shipping center
     * based on shipping location.
     */
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

                    //Send terminating key in a fake order
                    AmazonOrder terminate = new AmazonOrder(order.orderAsArray());
                    terminate.setTerminatingKey();
                    sendOrder(webServerToShippingCenter1,terminate);
                    sendOrder(webServerToShippingCenter2,terminate);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            //System.out.println("\nWeb Server Terminating\n");
        }

    }

