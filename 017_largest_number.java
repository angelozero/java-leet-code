/**
    Largest Number
 
    Given a list of non-negative integers nums, arrange them such that they form the largest number and return it.
    Since the result may be very large, so you need to return a string instead of an integer.

    Example 1:
    Input: nums = [10,2]
    Output: "210"
    
    Example 2:
    Input: nums = [3,30,34,5,9]
    Output: "9534330"

    Example 3:
    Input: nums = [700000000, 500000000]
    Output: "700000000500000000"
 */

class Solution {
    public String largestNumber(int[] nums) {
        var aux = 0;
        List<String> auxList = new ArrayList<>();


        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] % 10 == 0 && (nums[j] / 10) == nums[i]) {
                    aux = nums[j];
                    nums[j] = nums[i];
                    nums[i] = aux;
                
                } 
            }

            auxList.add(String.valueOf(nums[i]));
        }

        return finalResponse(auxList);
    }

    private String finalResponse(List<String> list){
        Collections.reverse(list);
        return String.join("", list); 
    }
}