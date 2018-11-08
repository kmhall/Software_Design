
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class AmazonTest {

    public static void main(String[] args) throws InterruptedException {

        // create new thread pool with two threads
        ExecutorService executorService = Executors.newCachedThreadPool();

        // create BlockingBuffer to store ints
        BlockingBuffer webServerToShippingCenter1 = new BlockingBuffer();
        BlockingBuffer webServerToShippingCenter2 = new BlockingBuffer();

        BlockingBuffer shippingCenter1ToSection1 = new BlockingBuffer();
        BlockingBuffer shippingCenter1ToSection2 = new BlockingBuffer();

        BlockingBuffer shippingCenter2ToSection1 = new BlockingBuffer();
        BlockingBuffer shippingCenter2ToSection2 = new BlockingBuffer();

        BlockingBuffer sectionsToShippingDock1 = new BlockingBuffer();
        BlockingBuffer sectionsToShippingDock2 = new BlockingBuffer();

        BlockingBuffer dock1ToDeliveryTruck1 = new BlockingBuffer();
        BlockingBuffer dock1ToDeliveryTruck2 = new BlockingBuffer();
        BlockingBuffer dock2ToDeliveryTruck1 = new BlockingBuffer();
        BlockingBuffer dock2ToDeliveryTruck2 = new BlockingBuffer();



        executorService.execute(new AmazonWebServer(webServerToShippingCenter1,webServerToShippingCenter2));

        executorService.execute(new ShippingCenter(webServerToShippingCenter1,shippingCenter1ToSection1,shippingCenter1ToSection2,1));
        executorService.execute(new ShippingCenter(webServerToShippingCenter2,shippingCenter2ToSection1,shippingCenter2ToSection2,2));

        executorService.execute(new ShippingCenterSection(shippingCenter1ToSection1,sectionsToShippingDock1,1,1));
        executorService.execute(new ShippingCenterSection(shippingCenter1ToSection2,sectionsToShippingDock1,1,2));

        executorService.execute(new ShippingCenterSection(shippingCenter2ToSection1,sectionsToShippingDock2,2,1));
        executorService.execute(new ShippingCenterSection(shippingCenter2ToSection2,sectionsToShippingDock2,2,2));

        executorService.execute(new ShippingDock(sectionsToShippingDock1,dock1ToDeliveryTruck1,dock1ToDeliveryTruck2,1));
        executorService.execute(new ShippingDock(sectionsToShippingDock2,dock2ToDeliveryTruck1,dock2ToDeliveryTruck2,2));

        executorService.execute(new DeliveryTruck(dock1ToDeliveryTruck1,1,1));
        executorService.execute(new DeliveryTruck(dock1ToDeliveryTruck2,2,1));
        executorService.execute(new DeliveryTruck(dock2ToDeliveryTruck1,3,2));
        executorService.execute(new DeliveryTruck(dock2ToDeliveryTruck2,4,2));


        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.MINUTES);
    }
}
