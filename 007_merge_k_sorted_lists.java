/**
Merge k Sorted Lists
    You are given an array of k linked-lists lists, each linked-list is sorted in ascending order.

    Merge all the linked-lists into one sorted linked-list and return it.

    Example 1:

    Input: lists = [[1,4,5],[1,3,4],[2,6]]
    Output: [1,1,2,3,4,4,5,6]
    Explanation: The linked-lists are:
    [
    1->4->5,
    1->3->4,
    2->6
    ]
    merging them into one sorted linked list:
    1->1->2->3->4->4->5->6
    Example 2:

    Input: lists = []
    Output: []
    Example 3:

    Input: lists = [[]]
    Output: []
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

    private List<Integer> valueList = new ArrayList<>();
    private ListNode listNodeAux = null;

    public ListNode mergeKLists(ListNode[] lists) {
        if ( (lists.length == 0) || (lists.length == 1 && lists[0] == null) ) {
            return null;
        }

        for(ListNode node : lists){
            getValue(node);
        }

        int temp = 0;
        for (int x = 0; x < valueList.size(); x++){
            for (int y = (x + 1); y < valueList.size(); y++){
                if (valueList.get(y) < valueList.get(x)) {
                    temp = valueList.get(y);
                    valueList.set(y, valueList.get(x));
                    valueList.set(x, temp);
                }
            }
        }

        return generateListNode(valueList.size(), valueList);
    }

    private void getValue(ListNode node){
        if (node != null && node.next != null ) {
            getValue(node.next);
        } 

        if (node != null) {
            valueList.add(node.val);
        }
    }

    private ListNode generateListNode(int count, List<Integer> valuesNum){
        if(count == 0){
            return listNodeAux;
        }

        listNodeAux = new ListNode(valuesNum.get(valuesNum.size() - count), generateListNode(count -1, valuesNum));
        return listNodeAux;
    }
}