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