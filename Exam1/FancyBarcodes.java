//Problem 2. Fancy Barcodes
//
//Your first task is to determine if the given sequence of characters is a valid barcode or not.
//Each line must not contain anything else but a valid barcode. A barcode is valid when:
//Is surrounded with a "@" followed by one or more "#"
//Is at least 6  characters long (without the surrounding "@" or "#")
//Starts with a capital letter
//Contains only letters (lower and upper case) and digits
//Ends with a capital letter
//Examples of valid barcodes: @#FreshFisH@#, @###Brea0D@###, @##Che46sE@##, @##Che46sE@###
//Examples of invalid barcodes: ##InvaliDiteM##, @InvalidIteM@, @#Invalid_IteM@#
//Next you have to determine the product group of the item from the barcode. The product group is obtained by concatenating all the digits found in the barcode. If there are no digits present in the barcode, the default product group is "00".
//Examples:
//@#FreshFisH@# -> product group: 00
//@###Brea0D@### -> product group: 0
//@##Che4s6E@## -> product group: 46
//Input
//On the first line you will be given an integer n – the count of barcodes that you will be receiving next.
//On the next n lines, you will receive different strings.
//Output
//For each barcode that you process, you need to print a message.
//If the barcode is invalid:
//"Invalid barcode"
//If the barcode is valid:
//"Product group: {product group}"
//Constraints
//
//Examples
//Input                                                     	Output
//3                                                             Product group: 00
//@#FreshFisH@#                                                 Product group: 0
//@###Brea0D@###                                                Product group: 46
//@##Che4s6E@##

//Examples
//Input                                                     	Output
//6                                                             Product group: 11
//@###Val1d1teM@###                                             Product group: 00
//@#ValidIteM@#                                                 Invalid barcode
//##InvaliDiteM##                                               Invalid barcode
//@InvalidIteM@                                                 Invalid barcode
//@#Invalid_IteM@#                                              Product group: 00
//@#ValiditeM@#
package Exam1;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FancyBarcodes {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());

        String regex = "(@#{1,})(?<barCode>[A-Z]{1}[A-Za-z0-9]{4,}[A-Z]{1})\\1";
        Pattern pattern = Pattern.compile(regex);

        for (int i = 0; i <=n-1; i++) {
            String line = scanner.nextLine();
            Matcher matcher = pattern.matcher(line);

            StringBuilder sb = new StringBuilder();

            if(matcher.find()){
                String str = matcher.group("barCode");

                for (int j = 0; j <=str.length()-1; j++) {
                    if(str.charAt(j)- '0'>=0 && str.charAt(j) - '0' <=9){
                     sb.append(str.charAt(j));
                    }
                }
                if(sb.toString().equals("")){
                    sb.append("00");
                }
                System.out.printf("Product group: %s%n", sb.toString());

            }else{
                System.out.println("Invalid barcode");
            }


        }

    }
}
