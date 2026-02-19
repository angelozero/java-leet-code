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
 */

class Solution {
    public String largestNumber(int[] nums) {

        var strAux = "";
        var intAux = 0;
        var strAuxFinal = "";

        for(int x = 0; x < nums.length; x++){
            strAux += String.valueOf(nums[x]);
        }
        
        String[] strList = strAux.split("");

        int[] intList = new int[strList.length];

        for(int x = 0; x < strList.length; x++){
            intList[x] = getValue(strList[x]);
        }

        for(int x = 0; x < intList.length; x++){
            for(int z = x + 1; z < intList.length; z++){
                if(intList[x] < intList[z]){
                    intAux = intList[x];
                    intList[x] = intList[z];
                    intList[z] = intAux;
                }
            }
            strAuxFinal += String.valueOf(intList[x]);
        }

        for(int x = 0; x < intList.length; x++){
            System.out.println(strList[x]);
        }

        return strAuxFinal;
    }

    private int getValue(String value){
        try {
            return Integer.parseInt(value);

        } catch (Exception ex){
            throw new RuntimeException("Falha ao converter numero");
        }
    }
}