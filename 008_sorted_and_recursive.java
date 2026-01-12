/**
 * 1 - get all node values
 * 2 - sort node values
 * 3 - return a single node with all values
 */

// MainNode

import com.angelozero.task.management.node.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * 1 - get all node values
 * 2 - sort node values
 * 3 - return a single node with all values
 */

public class MainNode {

    private static final List<Integer> nodeValuesList = new ArrayList<>();
    private static Node nodeAux = null;

    public static void main(String[] args) {

        var nodeList = Node.getNodeList();

        // 1
        for (Node node : nodeList) {
            getNodeValue(node);
        }

        // 2
        int temp = 0;
        for (int x = 0; x < nodeValuesList.size(); x++) {
            for (int y = (x + 1); y < nodeValuesList.size(); y++) {
                if (nodeValuesList.get(x) > nodeValuesList.get(y)) {
                    temp = nodeValuesList.get(x);
                    nodeValuesList.set(x, nodeValuesList.get(y));
                    nodeValuesList.set(y, temp);
                }
            }
        }

        // 3
        var count = nodeValuesList.size() - 1;
        var finalNode = getFinalNode(count, nodeValuesList);

        System.out.println(finalNode);
    }


    private static void getNodeValue(Node node) {
        if (node != null) {
            nodeValuesList.add(node.val);
            getNodeValue(node.next);
        }
    }

    private static Node getFinalNode(int count, List<Integer> nodeValuesList) {

        if (count > 0) {
            nodeAux = new Node(nodeValuesList.get((nodeValuesList.size() - 1) - count), getFinalNode(count - 1, nodeValuesList));
        }

        return nodeAux;
    }
}

// Node
public class Node {

    public int val;
    public Node next;

    public Node() {
    }

    public Node(int val) {
        this.val = val;
    }

    public Node(int val, Node next) {
        this.val = val;
        this.next = next;
    }

    public static Node[] getNodeList() {
        /** [[1,4,5], [1,3,4], [2,6]] **/

        Node[] list = new Node[3];

        var n05 = new Node(5);
        var n04 = new Node(4, n05);
        var n01 = new Node(1, n04);

        var n14 = new Node(4);
        var n13 = new Node(3, n14);
        var n11 = new Node(1, n13);

        var n26 = new Node(6);
        var n22 = new Node(2, n26);

        list[0] = n01;
        list[1] = n11;
        list[2] = n22;

        return list;
    }

    @Override
    public String toString() {
        return "Node{" +
                "val: " + val +
                ", next: " + next +
                "}";
    }
}