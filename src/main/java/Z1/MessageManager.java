package Z1;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public final class MessageManager {

    private static MessageManager messageManagerInstance;

    private MessageManager() {
    }

    public static MessageManager getInstance() {
        if (messageManagerInstance == null) {
            messageManagerInstance = new MessageManager();
        }

        return messageManagerInstance;
    }


    public void printMessage(String stringToPrint) {
        System.out.println(stringToPrint);


        }




}
