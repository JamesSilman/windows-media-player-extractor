package extraction;

import ui.MainFrame;
import ui.NorthPanel;

import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.HashMap;

public class MP3 extends Extraction{

    String fileLocation = "";
    private String fileHeaderStartPoint = "f2";
    private String fileHeaderRemaining = "72530555";

    private int fileSizeOffsetJump = 39;
    private int fileNameOffsetJump = 285;
    private int dateAddedJUmp =  115;
    private int lastViewedJump = 123;
    private int timesViewedJump = 205;

    private long currentFileOffset;

    private ArrayList<Long> fileLocationOffsets = new ArrayList<Long>();

    private RandomAccessFile fa;

    private HashMap<String, String> entry = new HashMap<>();

    private MainFrame mf;

    public MP3(String fileLocation, MainFrame mf){

        this.fileLocation = fileLocation;

        try{
            fileLocationOffsets = findFileEntries(this.fileLocation, fileHeaderStartPoint, fileHeaderRemaining);
            if(fileLocationOffsets.size() > 0){

                for (long flo: fileLocationOffsets) {
                    currentFileOffset = flo;
                    extractFileNames();
                    extractFileSize();
                    extractDateAdded();
                    extractLastViewed();
                    //timeViewed();
                    addAdditionalInfoToEntry();
                    updateUI();
                }
            }

        }catch (Exception ioe ){ }

        for (HashMap.Entry<String, String> entry : entry.entrySet()) {
            //System.out.println("********************************");
            //System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue());
        }

    }

    private void extractFileNames(){
        try{
            extractPartialFileEntryUntil(this.fileLocation, this.currentFileOffset, fileNameOffsetJump, "0");
            entry.put("fileName", getFileEntry());
            extractFileLocation(getFilePositionOffset());
        }catch (Exception ioe ){ }

    }

    private void extractFileLocation(long offset){

        try{
            extractPartialFileEntryUntil(this.fileLocation, offset, 0, "0");
            entry.put("fileLocation", getFileEntry());
        }catch (Exception ioe ){ }

    }

    private void extractFileSize(){

        try{
            extractPartialFileEntryByLength(this.fileLocation, this.currentFileOffset, fileSizeOffsetJump, 3);
            entry.put("fileSize", getFileSize());
        }catch (Exception e){

        }
    }

    private void extractDateAdded(){
        try{
            String dateAdded = extractPartialFileEntryByLengthByte(this.fileLocation, this.currentFileOffset, dateAddedJUmp, 8);
            entry.put("dateAdded", convertDateAndTime(dateAdded));
        }catch (Exception e){

        }
    }

    private void extractLastViewed(){
        try{
            String lastViewed = extractPartialFileEntryByLengthByte(this.fileLocation, this.currentFileOffset, lastViewedJump, 8);
            entry.put("lastViewed", convertDateAndTime(lastViewed));
        }catch(Exception e){

        }
    }

    private void timeViewed(){
        try{
            String timesPlayed = extractPartialFileEntryByLengthByte(this.fileLocation, this.currentFileOffset, timesViewedJump, 1);
            entry.put("timeViewed", "999");
        }catch (Exception e){

        }
    }

    private void addAdditionalInfoToEntry(){
        entry.put("entryType", "MP3");
    }

    private void updateUI(){
        NorthPanel.addRows(entry);
    }

}
