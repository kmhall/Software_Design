
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



        executorService.execute(new AmazonWebServer(webServerToShippingCenter1,webServerToShippingCenter2));

        executorService.execute(new ShippingCenter(webServerToShippingCenter1,shippingCenter1ToSection1,shippingCenter1ToSection2,1));
        executorService.execute(new ShippingCenter(webServerToShippingCenter2,shippingCenter2ToSection1,shippingCenter2ToSection2,2));

        executorService.execute(new ShippingCenterSection(shippingCenter1ToSection1,1,1));
        executorService.execute(new ShippingCenterSection(shippingCenter1ToSection2,1,2));

        executorService.execute(new ShippingCenterSection(shippingCenter2ToSection1,2,1));
        executorService.execute(new ShippingCenterSection(shippingCenter2ToSection2,2,2));



        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.MINUTES);
    }
}
