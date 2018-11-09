public class MazeTraversalTest {

    public static void main(String[] args){
        MazeTraversal map = new MazeTraversal();

        map.sanityCheck();
        map.mazeTraversal(map.getMap(),2,0);
        map.printMap();

    }
}
