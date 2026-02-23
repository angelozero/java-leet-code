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
        var response = "";
        List<AuxClass> auxClassList = new ArrayList<>();

        for (int num : nums) {
            if (num < 10) {
                auxClassList.add(new AuxClass(num + "0", true));

            } else if (String.valueOf(num).contains("0")) {
                var auxValue = String.valueOf(num).split("");

                for (String value : auxValue) {
                    auxClassList.add(new AuxClass(value, false));
                }

            } else {
                auxClassList.add(new AuxClass(String.valueOf(num), false));
            }
        }

        auxClassList.sort(Comparator.comparingInt((AuxClass a) -> Integer.parseInt(a.value())).reversed());

        for (AuxClass auxClass : auxClassList) {
            if (auxClass.delete) {
                response += auxClass.value.replace("0", "");
            } else {
                response += auxClass.value;
            }
        }

        return response;
    }

    record AuxClass(String value, boolean delete) {}
}