//Programming Fundamentals Final Exam 04.04.2020
//Problem 1. Password Reset
//Yet again you have forgotten your password... Naturally it`s not the first time this has happened. Actually you got so tired of it that you decided to help yourself with a smart solution.
//Write a password reset program that performs a series of commands upon a predefined string. First, you will receive a string and afterwards, until the command "Done" is given, you will be receiving strings with commands split by a single space. The commands will be the following:
//TakeOdd
//o Takes only the characters at odd indices and concatenates them together to
//obtain the new raw password and then prints it.
//Cut {index} {length}
//oGets the substring with the given length starting from the given index from the password and removes its first occurrence of it, then prints the password on the console.
//oThe given index and length will always be valid.
//Substitute {substring} {substitute}
//oIf the raw password contains the given substring, replaces all of its
//occurrences with the substitute text given and prints the result.
//oIf it doesn’t, prints "Nothing to replace!"
//Input
//You will be receiving strings until the "Done" command is given.
//Output
//After the "Done" command is received, print:
//o"Your password is: {password}"
//Constraints
//The indexes from the "Cut {index} {length}" command will always be valid.

//Examples
//Input                                                         Output
//Siiceercaroetavm!:?:ahsott.:i:nstupmomceqr                    icecream::hot::summer
//TakeOdd                                                       icecream::hot::mer
//Cut 15 3                                                      icecream-hot-mer
//Substitute :: -                                               Nothing to replace!
//Substitute | ^                                                Your password is: icecream-hot-mer
//Done

//Comments
//TakeOdd
//Siiceercaroetavm!:?:ahsott.:i:nstupmomceqr -> icecream::hot::summer
//We only take the chars at odd indices 1, 3, 5 etc.
//Cut 15  3 -> icecream::hot::summer -> sum
//icecream::hot::mer
//We cut a substring starting at index 15 with length 3,
//remove it from the raw password and print it.
//Then, on a new line we print the resulting new raw password.
//Substitute :: - -> icecream::hot::summer -> icream-hot-summer
//We replace "::" with "-".
//Substitute | ^ -> Nothing to replace!
//"|" is not found anywhere in the raw password.
//Finally, after receiving the "Done" command, we print the resulting password in the proper format.

//Examples
//Input                                                         Output
//up8rgoyg3r1atmlmpiunagt!-irs7!1fgulnnnqy                      programming!is!funny
//TakeOdd                                                       programming!is!fun
//Cut 18 2                                                      programming***is***fun
//Substitute ! ***                                              Nothing to replace!
//Substitute ? .!.                                              Your password is: programming***is***fun
//Done

package Exam1;

import java.util.Scanner;

public class PasswordReset01 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String password = scanner.nextLine();

        String input = scanner.nextLine();

        while(!input.equals("Done")){
            String[] tockens = input.split(" ");
            //TakeOdd
            //o Takes only the characters at odd indices and concatenates them together to
            //obtain the new raw password and then prints it.
            if(tockens[0].equals("TakeOdd")){
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i <=password.length()-1; i++) {
                    if(i%2==1){
                        sb.append(password.charAt(i));
                    }
                }
                password = sb.toString();
                System.out.println(password);
            }
            //Cut {index} {length}
            //oGets the substring with the given length starting from the given index from the password and removes its first occurrence of it, then prints the password on the console.
            //oThe given index and length will always be valid.
            if(tockens[0].equals("Cut")){
                int index = Integer.parseInt(tockens[1]);
                int lengthh = Integer.parseInt(tockens[2]);
                String text = password.substring(index,index + lengthh);

                password = password.replaceFirst(text,"");
                System.out.println(password);
            }
            //Substitute {substring} {substitute}
            //oIf the raw password contains the given substring, replaces all of its
            //occurrences with the substitute text given and prints the result.
            //oIf it doesn’t, prints "Nothing to replace!"
            if(tockens[0].equals("Substitute")){
                String substringg = tockens[1];
                String substitute = tockens[2];

                if(password.contains(substringg)){
                    password = password.replace(substringg, substitute);
                    System.out.println(password);
                }else{
                    System.out.println("Nothing to replace!");
                }

            }


            input = scanner.nextLine();
        }

        System.out.printf("Your password is: %s%n", password);

    }
}
