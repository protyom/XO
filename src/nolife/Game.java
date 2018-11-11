package nolife;

import java.io.IOException;
import java.util.Scanner;

public class Game {
    private char field[][];
    private int moves;
    private boolean isPlaying;
    private Scanner sc;
    Game(){
        isPlaying = true;
        sc=new Scanner(System.in);
        moves=0;
        field = new char[3][3];
        for (int i=0;i<3;i++) {
            for (int j=0;j<3;j++) {
                field[i][j]=' ';
            }
        }
    }

    public void drawField(){
        System.out.println("  123");
        System.out.println("  ___");
        System.out.print("a|");
        for (char a:field[0]) {
            System.out.print(a);
        }
        System.out.print("|\nb|");
        for (char a:field[1]) {
            System.out.print(a);
        }
        System.out.print("|\nc|");
        for (char a:field[2]) {
            System.out.print(a);
        }
        System.out.print("|\n");
        System.out.println("  ___");
    }

    public void makeMove()throws IOException {
        if(moves%2==0){
            System.out.println("X makes the move");
        }else{
            System.out.println("0 makes the move");
        }
        String str= sc.nextLine();
        int y = str.charAt(0)-'1';
        int x = Character.toLowerCase(str.charAt(1))-'a';
        if(x<0 || x>2 || y<0 || y>2){
            throw new IOException("Wrong input");
        }
        if(moves%2==0) {
            field[x][y] ='X';
        }else{
            field[x][y]='0';
        }
        moves++;
    }

    public void playGame()throws IOException{
        while(isPlaying) {
            drawField();
            makeMove();
            char res = checkField();
            if (res != ' ') {
                System.out.println(res + "'s won. New game?(y/n)");
                char next = sc.nextLine().charAt(0);
                if(Character.toLowerCase(next)=='y'){
                    newGame();
                }else if(Character.toLowerCase(next)=='n'){
                    isPlaying=false;
                }
            }
            if(res==' ' && moves==9){
                System.out.println("Finished in draw. New game?(y/n)");
                char next = sc.nextLine().charAt(0);
                if(Character.toLowerCase(next)=='y'){
                    newGame();
                }else if(Character.toLowerCase(next)=='n'){
                    isPlaying=false;
                }
            }
        }
    }

    public void newGame(){
        moves=0;
        field = new char[3][3];
        for (int i=0;i<3;i++) {
            for (int j=0;j<3;j++) {
                field[i][j]=' ';
            }
        }
    }

    public char checkField(){
        char res = checkHorizontal();
        if(res!=' '){
            return res;
        }
        res = checkVertical();
        if(res!=' '){
            return res;
        }
        res = checkDiagonal();
        if(res!=' '){
            return res;
        }
        return ' ';
    }
    public char checkHorizontal(){
        for(int i=0;i<3;i++){
            char first=field[i][0];
            if(first==' '){
                continue;
            }
            boolean hasWon=true;
            for(int j=1;j<3;j++){
                if(field[i][j]!=first){
                    hasWon=false;
                    break;
                }
            }
            if(hasWon){
                return first;
            }
        }
        return ' ';
    }
    public char checkVertical(){
        for(int i=0;i<3;i++){
            char first=field[0][i];
            if(first==' '){
                continue;
            }
            boolean hasWon=true;
            for(int j=1;j<3;j++){
                if(field[j][i]!=first){
                    hasWon=false;
                    break;
                }
            }
            if(hasWon){
                return first;
            }
        }
        return ' ';
    }
    public char checkDiagonal(){
        char first=field[0][0];
        if(first!=' '){
            boolean hasWon=true;
            for(int i=1;i<3;i++){
                if(field[i][i]!=first){
                    hasWon=false;
                    break;
                }
            }
            if(hasWon){
                return first;
            }
        }

        first=field[0][2];
        if(first!=' '){
            boolean hasWon=true;
            for(int i=1;i<3;i++){
                if(field[i][2-i]!=first){
                    hasWon=false;
                    break;
                }
            }
            if(hasWon){
                return first;
            }
        }
        return ' ';
    }

}
