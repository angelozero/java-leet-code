/**
    Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays.

    The overall run time complexity should be O(log (m+n)).

    Example 1:

    Input: nums1 = [1,3], nums2 = [2]
    Output: 2.00000
    Explanation: merged array = [1,2,3] and median is 2.
    Example 2:

    Input: nums1 = [1,2], nums2 = [3,4]
    Output: 2.50000
    Explanation: merged array = [1,2,3,4] and median is (2 + 3) / 2 = 2.5.
 */

class Solution {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        int[] numsConcat = new int[nums1.length + nums2.length];

        for (int x = 0; x < nums1.length; x++){
            numsConcat[x] = nums1[x];
        }

        for (int x = 0; x < nums2.length; x++){
            numsConcat[x + nums1.length] = nums2[x];
        }

        int temp = 0;
        int sumValues = 0;
        int divAux = 0;

        for (int x = 0; x < numsConcat.length; x++){
            for (int y = x + 1; y < numsConcat.length; y++){
                if (numsConcat[y] == numsConcat[x] && y < numsConcat.length - 1){
                    numsConcat[y] = 0;
                    numsConcat[x] = 0;
                
                } else if (numsConcat[y] < numsConcat[x]){
                    temp = numsConcat[y];
                    numsConcat[y] = numsConcat[x];
                    numsConcat[x] = temp;
                }
            }

            if (numsConcat.length > 3 && (x == 0 || (x == numsConcat.length - 1)) ) {
                numsConcat[x] = 0;
                divAux = divAux + 1;
            }

            sumValues = sumValues + numsConcat[x];
        }

        for (int x : numsConcat){
            System.out.println(x);
        }

        return (double) sumValues / ( numsConcat.length - divAux );
    }
}