package arrays2d.KnightsTour;
import java.io.File;

import java.util.ArrayList;
import java.util.Random;

import processing.core.PApplet;
import processing.core.PImage;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;



public class TourDisplay extends PApplet {
    //if you download then change where this display is located
    public static void main(String[] args) {
        PApplet.main("arrays2d.KnightsTour.TourDisplay");
    }

    public void settings() {
        size(WIDTH, HEIGHT);

    }

    PImage knightImage;

    Knight knight;
    final int WIDTH = 800;
    final int HEIGHT = 800;
    final int BLOCKX = WIDTH / 8;
    final int BLOCKY = HEIGHT / 8;
    Random randy = new Random();
    int startR = randy.nextInt(8);
    int startC = randy.nextInt(8);
    ArrayList<Square> answer;
    PImage XImage;

    public void setup() {
        // MUSIC - playSound();
        knightImage = loadImage("arrays2d/KnightsTour/Images/knight.png");
        XImage = loadImage("arrays2d/KnightsTour/Images/X.png");
        XImage.resize(width/8, height/8);
        knightImage.resize(width/8, height/8);
        knight = new Knight(new Square(startR, startC, 0), 8, 8);
        answer = knight.solve();

        drawBoard();
        frameRate(2);

    }
    int index = 0;
    //INDEX 2 USED TO TRACK HOW MANY TIMES IT DRAWS SO THAT I KNOW WHEN TO STOP MUSIC
    int Index2 = 0;
    Square prevSquare = null;
ArrayList<Square> possibleSquares = new ArrayList<Square>();
    public void draw() {

        if(index < answer.size()) {
            Square s = answer.get(index);
            Square q = null;
            Knight newKnight = new Knight(s, 8, 8);
            possibleSquares = newKnight.getPossibleSquares();

            //MAKING LINES AND DRAWING KNIGHT

            image(knightImage, s.getRow() * BLOCKX, s.getColumn() * BLOCKY);
            if(index > 0){
                q = answer.get(index - 1);
                prevSquare = q;

                line(q.getRow()*BLOCKX + BLOCKX/2, q.getColumn()*BLOCKY + BLOCKY/2,
                        s.getRow()*BLOCKX + BLOCKX/2, s.getColumn()*BLOCKY + BLOCKY/2);
                Index2++;
                //System.out.println(Index2);
                //image(XImage, q.getRow()*BLOCKX, q.getColumn()*BLOCKY);



            }
            if(Index2 == 63){
                clip.stop();
            }

        }
        index++;
    }
    //update board
    public void drawBoard(){
        //background() type of function just in case.

        for (int row = 0; row < 8; row ++) {
            for (int col = 0; col < 8; col ++) {
                if ((row + col + 1) % 2 == 0) {
                    fill(255, 255, 255); // white
                } else {
                    fill(0, 120, 255); // blue
                }

                rect(row * BLOCKX, col * BLOCKY, (row + 1) * BLOCKX, (col + 1) * BLOCKY);
            }
        }


    }
    Clip clip;

//music - if you want
//    public  void playSound(){
//            File lol = new File("file path");
//            try{
//                clip = AudioSystem.getClip();
//                clip.open(AudioSystem.getAudioInputStream(lol));
//                clip.start();
//                clip.loop(999);
//
//
//            } catch (Exception e){
//                e.printStackTrace();
//            }
//
//        }
}
