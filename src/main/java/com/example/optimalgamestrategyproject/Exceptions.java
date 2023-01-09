package com.example.optimalgamestrategyproject;

public class Exceptions extends Throwable {

    public static void checkFileNameContainSpace(String path){

        String[] split = path.split(" ");
        if (split.length > 1)
            throw new IllegalArgumentException("The File Name must be without any spaces");

    }

    public static void checkNumberCoinValidate(int numberOfCoins){

        if ( (numberOfCoins % 2) != 0)
            throw new IllegalArgumentException("The number of coin must be even and more than one");

    }

    public static void checkFileIsChosen(String path){

        if (path.trim().equalsIgnoreCase(""))
            throw new IllegalArgumentException("Please Choose A File");

    }

    public static void checkIsNumber(String[] numbers){

        for (int i = 0; i < numbers.length; i++) {
            System.out.println(numbers[i]);
        }

        for (String number : numbers) {
            if (Character.isDigit(Integer.parseInt(number.trim()))) {
                System.out.println(number);

            }else{
                throw new IllegalArgumentException("Pleas Enter just numbers");
            }
        }

    }

    public static void containsNumber(String input){

        if (!input.matches("^\\d+$")) {
            throw new IllegalArgumentException("Pleas Enter just numbers");
        }

    }

    public static void checkTextFiledEmpty(String text){

        if (text.trim().equalsIgnoreCase(""))
            throw new IllegalArgumentException("Please Enter Coins");

    }

}
