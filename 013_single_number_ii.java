/**
    Single Number II
    Attempted
    Medium
    Topics
    premium lock icon
    Companies
    Given an integer array nums where every element appears three times except for one, which appears exactly once. Find the single element and return it.

    You must implement a solution with a linear runtime complexity and use only constant extra space.

    

    Example 1:

    Input: nums = [2,2,3,2]
    Output: 3
    Example 2:

    Input: nums = [0,1,0,1,0,1,99]
    Output: 99
    

    Constraints:

    1 <= nums.length <= 3 * 104
    -231 <= nums[i] <= 231 - 1
    Each element in nums appears exactly three times except for one element which appears once.
 */
class Solution {
    public int singleNumber(int[] nums) {
        int aux = 0;
        var excludedNumbers = new ArrayList<Integer>();

        var numsList = Arrays.stream(nums)
            .boxed()
            .collect(Collectors.toList());

        
        for (int x = 0; x < nums.length; x++){
            for (int y = (x+1); y < nums.length; y++){
                
                if( nums[x] == nums[y] ){
                    if(!excludedNumbers.contains(nums[x])){
                        excludedNumbers.add(nums[x]);
                    } 
                }
            }

            if(!excludedNumbers.contains(nums[x])){
                aux = nums[x];
            }
        }

        return aux;
    }
}