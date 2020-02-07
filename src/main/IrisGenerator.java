package main;

import java.util.Random;

public enum IrisGenerator {
    instance;
    public int[][] irisCreate(){
        Random zufall = new Random();
        int[][] iris = new int[10][10];
        for (int i = 0; i <iris.length ; i++) {
            for (int j = 0; j < iris[i].length ; j++) {
                iris[i][j] = zufall.nextInt(3000);
            }
        }
        return iris;
    }
}
