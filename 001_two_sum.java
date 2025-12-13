/**
    1. Two Sum
    
    Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.

    You may assume that each input would have exactly one solution, and you may not use the same element twice.

    You can return the answer in any order.

    

    Example 1:

    Input: nums = [2,7,11,15], target = 9
    Output: [0,1]
    Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].
    Example 2:

    Input: nums = [3,2,4], target = 6
    Output: [1,2]
    Example 3:

    Input: nums = [3,3], target = 6
    Output: [0,1]
 */

class Solution {
    public int[] twoSum(int[] nums, int target) {
        
        //System.out.println("target == " + target);
        int[] finalList = new int[2];
        boolean auxFinalListPos0 = false;
        int [] auxList = new int[]{0};

        for (int x = 0; x < nums.length; x++){

            if (auxList[0] == 0) {
                auxList = getNewIntArray(nums);
            
            } else {
                auxList = getNewIntArray(auxList);
            }
            
            for(int value : auxList){
                //System.out.println(nums[x] + " + " + value + " == " + (nums[x] + value));
                if (value + nums[x] == target ){
                    if(!auxFinalListPos0){
                        finalList[0] = x;
                        auxFinalListPos0 = true;
                    }
                    
                    finalList[1] = findIndex(nums, value);
                }
            }
        }
        
        return finalList;
    }

    public int findIndex(int[] nums, int value){

        int aux = 0;

        for (int x = 1; x < nums.length; x++){
            if ( nums[x] == value ){
                aux = x;
            }
        }
        return aux;
    }

    public int[] getNewIntArray(int[] originalArray){
        
        int newSizeArray = originalArray.length - 1;
        int[] newArray = new int[newSizeArray];

        System.arraycopy(originalArray, 1, newArray, 0, newSizeArray);

        return newArray;
    }
}