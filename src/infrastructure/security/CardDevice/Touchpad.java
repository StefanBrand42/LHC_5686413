package infrastructure.security.CardDevice;

import infrastructure.security.CardDevice.ITouchpad;

import java.util.Scanner;

public class Touchpad implements ITouchpad {
    @Override
    public String passwortEingabe() {
        Scanner scanner = new Scanner (System.in);
        System.out.print("******* Bitte geben Sie ihr Passwort ein ************");
        return scanner.nextLine();


    }
}
