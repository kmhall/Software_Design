public class MazeTraversalTest {

    public static void main(String[] args) throws InterruptedException {
        MazeTraversal map = new MazeTraversal();

        map.sanityCheck();
        map.mazeTraversal(map.getMap(),2,0);
        map.printMap();

    }
}
