/**
    You are given two non-empty linked lists representing two non-negative integers. 
    The digits are stored in reverse order, and each of their nodes contains a single digit. 
    Add the two numbers and return the sum as a linked list.

    You may assume the two numbers do not contain any leading zero, except the number 0 itself.

    Example 1:

    Input: l1 = [2,4,3], l2 = [5,6,4]
    Output: [7,0,8]
    Explanation: 342 + 465 = 807.
    Example 2:

    Input: l1 = [0], l2 = [0]
    Output: [0]
    Example 3:

    Input: l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
    Output: [8,9,9,9,0,0,0,1]

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

    private String aux = "";
    private ListNode listNodeAux = null;

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        Long result1 = Long.parseLong(getLastNumber(l1));
        aux = "";
        Long result2 = Long.parseLong(getLastNumber(l2));
        int finalValues = 0;
        
        try {
            finalValues = Math.toIntExact(result1 + result2);
        
        } catch (Exception ex){
            System.out.println("ERROR 01 ---> " + result1);
            System.out.println("ERROR 02 ---> " + result2);
        }

        return generateFinalResult(finalValues);
    }

    private String getLastNumber(ListNode ln){

        if (ln != null && ln.next != null) {
            getLastNumber(ln.next);
            aux = aux + String.valueOf(ln.val);
            return aux;
        } 

        aux = aux + String.valueOf(ln.val);
        return aux;
    }

    private ListNode generateFinalResult(int value){

        String[] valuesText = String.valueOf(value).split("");
        int[] valuesNum = new int[valuesText.length];
        
        for (int x = 0; x <= (valuesText.length - 1); x++) {
            valuesNum[x] = Integer.parseInt(valuesText[((valuesText.length - 1)) - x]);
        }

        return generateListNode(valuesNum.length, valuesNum);
    }

    private ListNode generateListNode(int count, int[] valuesNum){
        if(count == 0){
            return listNodeAux;
        }

        listNodeAux = new ListNode(valuesNum[valuesNum.length - count], generateListNode(count -1, valuesNum));
        return listNodeAux;
    }
}