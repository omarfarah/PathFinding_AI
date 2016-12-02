import java.util.Comparator;

public class NodeComparator implements Comparator<Node>
{
    @Override
    public int compare(Node x, Node y){
        if (x.getDistance() < y.getDistance()){
            return -1;
        } else if (x.getDistance() > y.getDistance()){
            return 1;
        }
        return 0;
    }
}