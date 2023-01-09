package com.example.optimalgamestrategyproject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Runner {

    public static void readFileCoin(String path) throws FileNotFoundException {

        Exceptions.checkFileIsChosen(path);
        Exceptions.checkFileNameContainSpace(path);

        File fileCoin = new File(path);
        Scanner scanner = new Scanner(fileCoin);


            while (scanner.hasNext()) {

                String line = scanner.nextLine();
                String[] lineSplit = line.split(",");
                Exceptions.checkNumberCoinValidate(lineSplit.length);
                Exceptions.checkIsNumber(lineSplit);

            }

    }


}
