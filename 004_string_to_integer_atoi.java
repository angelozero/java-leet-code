/**
    Implement the myAtoi(string s) function, which converts a string to a 32-bit signed integer.

    The algorithm for myAtoi(string s) is as follows:

    Whitespace: Ignore any leading whitespace (" ").
    Signedness: Determine the sign by checking if the next character is '-' or '+', assuming positivity if neither present.
    Conversion: Read the integer by skipping leading zeros until a non-digit character is encountered or the end of the string is reached. If no digits were read, then the result is 0.
    Rounding: If the integer is out of the 32-bit signed integer range [-231, 231 - 1], then round the integer to remain in the range. Specifically, integers less than -231 should be rounded to -231, and integers greater than 231 - 1 should be rounded to 231 - 1.
    Return the integer as the final result.
 */

class Solution {
    public int myAtoi(String s) {

        var value = s.trim();
        var list = value.split("");
        String aux = "";
        Boolean ignore = false;

        if (list.length == 0) {
            return 0;
        }

        if (list.length == 1) {
            try {
                return Integer.parseInt(s);
            
            } catch( Exception ex){
                return 0;
            }
        }

        for (int x = 1; x < list.length; x++){
            if (x == 1){
                aux = aux + list[0];
            }

            if (!ignore){
                try {
                    aux = aux + String.valueOf(Integer.parseInt(list[x]));
                
                } catch( Exception ex){
                    ignore = true;
                }
            } 
        }

        aux = aux.replaceAll("[^\\d-]", "");

        if(aux == ""){
            return 0;
        }
        
        try {
            return Integer.parseInt(aux);
        
        } catch (Exception ex){
            System.out.println("ERRO ---> " + aux);
            return 0;
        }
    }
}