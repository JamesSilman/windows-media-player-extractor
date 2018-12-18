package ui;

import extraction.Audio;
import extraction.Image;
import extraction.Video;

public class Extract {

    private static String fileLocation;
    private Thread audioThread, imagesThread, videoThread;

    public Extract(String fileLocation){

        this.fileLocation = fileLocation;

        extract();

    }

    private void extract(){

        updateUI("Starting extraction on file " + fileLocation);

        extractAudio();
        extractImages();
        extractVideo();

    }

    private void updateUI(String message){
        SouthPanel.updateTextArea(message);
    }

    private void extractAudio(){

         audioThread = new Thread() {
            public void run() {
                try {
                    new Audio(fileLocation);

                } catch(Exception e) {
                    System.out.println(e);
                }finally {

                }
            }
        };

        audioThread.start();
    }

    private void extractImages(){

        imagesThread = new Thread() {
            public void run() {
                try {
                    new Image(fileLocation);

                } catch(Exception e) {
                    System.out.println(e);
                }
            }
        };

        imagesThread.start();
    }

    private void extractVideo(){
        videoThread = new Thread() {
            public void run() {
                try {
                    new Video(fileLocation);

                } catch(Exception e) {
                    System.out.println(e);
                }
            }
        };

        videoThread.start();
    }

}