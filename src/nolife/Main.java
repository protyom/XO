package nolife;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        try {
            Game gm = new Game();
            gm.playGame();
        }
        catch(IOException io){
            System.out.println(io.getMessage());
        }
    }
}
