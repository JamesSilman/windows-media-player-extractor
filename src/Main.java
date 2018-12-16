import extraction.Audio;
import extraction.Image;
import extraction.Video;
import ui.MainFrame;

public class Main {

    private String v;

    public static void main(String[] args){

        MainFrame mf = new MainFrame();
        mf.setVisible(true);
        new Audio("/Users/james/Desktop/wmpdb/CurrentDatabase_372.wmdb");
        new Image("/Users/james/Desktop/wmpdb/CurrentDatabase_372.wmdb");
        new Video("/Users/james/Desktop/wmpdb/CurrentDatabase_372.wmdb");




        /**

        Thread one = new Thread() {
            public void run() {
                try {

                    new Audio("/Users/james/Desktop/CurrentDatabase_372.wmdb", mf);
                    System.out.println("Does it work?");

                    Thread.sleep(1000);


                } catch(InterruptedException v) {
                    System.out.println(v);
                }
            }
        };

        one.start();
        */

    }


}
