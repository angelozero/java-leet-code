/**
    Given an integer n, return the number of prime numbers that are strictly less than n.

    Example 1:
    Input: n = 10
    Output: 4
    Explanation: There are 4 prime numbers less than 10, they are 2, 3, 5, 7.
    
    Example 2:
    Input: n = 0
    Output: 0
    
    Example 3:
    Input: n = 1
    Output: 0
 */
class Solution {
    public int countPrimes(int n) {

        var auxPrimo = 0;
        var auxNum = 0;

        List <Integer> primosList = new ArrayList<>();

        for (int x = 1; x < n; x++) {
            auxPrimo = 1;
            auxNum = 1;

            while (auxNum < x) {
                if (x % auxNum == 0) {
                    auxPrimo += 1;
                }

                if (auxPrimo > 2) {
                    break;
                }

                auxNum++;
            }

            if (auxPrimo == 2) {
                primosList.add(x);
            }
        }

        return primosList.size();
    }
}