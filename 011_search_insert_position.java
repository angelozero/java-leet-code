/**
 *  Search Insert Position
  
    Given a sorted array of distinct integers and a target value, return the index if the target is found. 
    If not, return the index where it would be if it were inserted in order.

    You must write an algorithm with O(log n) runtime complexity.

    
    Example 1:

    Input: nums = [1,3,5,6], target = 5
    Output: 2
    Example 2:

    Input: nums = [1,3,5,6], target = 2
    Output: 1
    Example 3:

    Input: nums = [1,3,5,6], target = 7
    Output: 4
 */

class Solution {
    public int searchInsert(int[] nums, int target) {

        if (nums.length == 1){
          return nums[0] >= target ? 0 : 1;
        } 
        
        int aux = 0;
        for (int x = 0; x < nums.length; x++){
            if (x == 0 && target < nums[x]) {
                return 0;
            }

            if ( nums[x] == target ){
                return x;
            }

            if( (x + 1) < nums.length){
                if ( nums[x] < target && target < nums[x + 1]){
                    return x + 1;
                }

            } else if ( ( x + 1 ) == nums.length ){
                return x + 1;
            }
            
            aux = x;
        }

        return nums.length + (Math.abs(target - nums[aux]) - 1);
    }
}