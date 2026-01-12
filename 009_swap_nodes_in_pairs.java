/**
 * 
 *  Swap Nodes in Pairs
    Given a linked list, swap every two adjacent nodes and return its head. 
    You must solve the problem without modifying the values in the list's nodes (i.e., only nodes themselves may be changed.)
    
    Example 1:
    Input: head = [1,2,3,4]
    Output: [2,1,4,3]

    Example 2:
    Input: head = []
    Output: []

    Example 3:
    Input: head = [1]
    Output: [1]

    Example 4:
    Input: head = [1,2,3]
    Output: [2,1,3]

 */

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    private List<Integer> valAuxList = new ArrayList<>();
    private ListNode finalNode;
    
    public ListNode swapPairs(ListNode node) {

        getNodeVal(node);

        int sizeNodeList = valAuxList.size() % 2 == 0 ? valAuxList.size() : valAuxList.size() - 1;

        if (sizeNodeList > 1){
            int auxValue = 0;
            int nextAuxValue = 0;
            
            for (int x = 0; x < sizeNodeList; x++){
                if (x % 2 == 0 && (x + 1) <= sizeNodeList){
                    auxValue = valAuxList.get(x);
                    valAuxList.set(x, valAuxList.get(x + 1));
                    valAuxList.set((x + 1), auxValue);
                }
            }
        }

        return generateFinalNode(valAuxList.size(), valAuxList);
    }

    private void getNodeVal(ListNode node){
        if (node != null){
            valAuxList.add(node.val);
            
            if (node.next != null){
                getNodeVal(node.next); 
            }   
        }
    }

    private ListNode generateFinalNode(int count, List<Integer> valList){
        if ( count > 0 ){
            finalNode = new ListNode(valList.get(valList.size() - count), generateFinalNode((count - 1), valList));
        }
        
        return finalNode;
    }
}