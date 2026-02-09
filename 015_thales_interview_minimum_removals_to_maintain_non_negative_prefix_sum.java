/**
    Minimum Removals to Maintain Non-negative Prefix Sum
 
    A company has a list of expected revenues and payments for the upcoming year in chronological order. 
    The problem is that at some moments in time the sum of previous payments can be larger than the total previous revenue. 
    This would put the company in debt. To avoid this problem the company takes a very simple approach. 
    It reschedules some expenses to the end of the year.

    You are given an array of integers, where positive numbers represent revenues and negative numbers represent expenses, all in chronological order. 
    In one move you can relocate any expense (negative number) to the end of the array. 
    What is the minimum number of such relocations to make sure that the company never falls into debt? 
    In other words: you need to make sure that there is no consecutive sequence of elements starting from the beginning of the array, that sums up to a negative number.
    You can assume that the sum of all elements in A is nonnegative.

    Write a function:
    class Solution { public int solution(int[] A); }

    that, given an array A of N integers, returns the minimum number of relocations, so that company never falls into debt.

    Examples:
    Given A = [10, -10, -1, -1, 10], the function should return 1. It is enough to move -10 to the end of the array.
    Given A = [-1, -1, -1, 1, 1, 1, 1], the function should return 3. The negative elements at the beginning must be moved to the end to avoid debt at the start of the year.
    Given A = [5, -2, -3, 1], the function should return 0. The company balance is always nonnegative.

    Write an efficient algorithm for the following assumptions:
    N is an integer within the range [1..100,000];
    each element of array A is an integer within the range [-1,000,000,000..1,000,000,000];
    sum of all elements in A is greater than or equal to 0.
 */

// you can also use imports, for example:
// import java.util.*;

// you can write to stdout for debugging purposes, e.g.
// System.out.println("this is a debug message");

class Solution {
    public int solution(int[] A) {

        // Crie uma variável para o saldo_atual, um contador_de_remocoes e uma fila para guardar numeros processados.
        var balance = 0;
        var removedValue = 0;
        int minValues[] = new int[A.length];

        for (int x = 0; x < A.length; x++){
            balance = balance + A[x];

            // Se o valor for negativo, coloque-o na sua Fila de Prioridade.
            if (A[x] < 0) {
                minValues[x] = A[x];
            }

            // Se o saldo_atual ficar negativo:
            if (balance < 0) {

                // Pegue o menor valor (o mais negativo) de dentro da sua Fila de Prioridade.
                int min = getMinValue(minValues);

                // "Remova" esse valor do seu saldo
                balance = balance - min;  

                // Aumente o seu contador_de_remocoes.        
                removedValue = removedValue + 1;
            }
        }

        // retorne a quantidade de vezes que o valor foi removido
        return removedValue;
    }

    private int getMinValue(int[] minValueList){
        var auxVal = minValueList[0];
        
        for (int val : minValueList) {
        
            if (auxVal > val) {
                auxVal = val;
            }
        }

        return auxVal;
    }
}