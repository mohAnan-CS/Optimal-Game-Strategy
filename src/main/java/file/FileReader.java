package file;

import controllers.GameController;
import exception.Exceptions;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileReader {

    public static void read(String path) throws FileNotFoundException {

        Exceptions.checkFileIsChosen(path);
        Exceptions.checkFileNameContainSpace(path);

        File fileCoin = new File(path);
        Scanner scanner = new Scanner(fileCoin);



        while (scanner.hasNext()) {

            String line = scanner.nextLine();
            String[] lineSplit = line.split(",");
            Exceptions.checkNumberCoinValidate(lineSplit.length);
            Exceptions.checkIsNumber(lineSplit);

            int[] coinArr = new int[lineSplit.length];
            for (int i = 0; i < lineSplit.length; i++) {
                coinArr[i] = Integer.parseInt(lineSplit[i]);
            }
            GameController.COIN_ARRAY = coinArr ;

        }


    }

}
