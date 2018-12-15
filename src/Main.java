import extraction.MP3;
import ui.MainFrame;

public class Main {

    private String v;

    public static void main(String[] args){

        MainFrame mf = new MainFrame();
        mf.setVisible(true);
        new MP3("/Users/james/Desktop/CurrentDatabase_372.wmdb", mf);



        /**

        Thread one = new Thread() {
            public void run() {
                try {

                    new MP3("/Users/james/Desktop/CurrentDatabase_372.wmdb", mf);
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
