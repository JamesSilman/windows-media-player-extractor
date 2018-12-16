package extraction;

import ui.NorthPanel;
import ui.SouthPanel;

import java.util.ArrayList;
import java.util.HashMap;

public class Video extends Extraction{

    private ArrayList<Long> fileLocationOffsets = new ArrayList<Long>();

    private HashMap<String, String> entry = new HashMap<>();

    private String fileLocation;
    private long currentFileOffset;
    private String[][] fileSignatures = new String[][]{
            {"3a","000000FC3F12090E03B0FC"},
            {"10","F813084E03B0FC"}
    };

    public Video(String fileLocation){
        updateMessageTextArea("Starting VIDEO extraction");

        this.fileLocation = fileLocation;

        try{
            for(int i = 0; i < fileSignatures.length; i++) {

                int fileSignatureCounter = i + 1;
                updateMessageTextArea("Starting VIDEO file header " + fileSignatureCounter + " of " + fileSignatures.length);

                fileLocationOffsets = findFileEntries(this.fileLocation, fileSignatures[i][0], fileSignatures[i][1]);

                updateMessageTextArea("Found " +fileLocationOffsets.size() + " VIDEO entries using header " + fileSignatureCounter + " of " + fileSignatures.length);

                if (fileLocationOffsets.size() > 0) {

                    for (long flo : fileLocationOffsets) {
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
            }
        }catch (Exception ioe ){ }

        /**
         for (HashMap.Entry<String, String> entry : entry.entrySet()) {
         System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue());
         }
         */
    }

    private void extractFileNames(){
        try{
            extractPartialFileEntryUntil(this.fileLocation, this.currentFileOffset, 265, "0");
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
            extractPartialFileEntryByLength(this.fileLocation, this.currentFileOffset, 89, 4);
            entry.put("fileSize", getFileSize());
        }catch (Exception e){

        }
    }

    private void extractDateAdded(){
        try{
            String dateAdded = extractPartialFileEntryByLengthByte(this.fileLocation, this.currentFileOffset, 133, 8);
            entry.put("dateAdded", convertDateAndTime(dateAdded));
        }catch (Exception e){

        }
    }

    private void extractLastViewed(){
        try{
            String lastViewed = extractPartialFileEntryByLengthByte(this.fileLocation, this.currentFileOffset, 141, 8);
            entry.put("lastViewed", convertDateAndTime(lastViewed));
        }catch(Exception e){

        }
    }

    private void timeViewed(){
        try{
            String timesPlayed = extractPartialFileEntryByLengthByte(this.fileLocation, this.currentFileOffset, 205, 1);
            entry.put("timeViewed", "999");
        }catch (Exception e){

        }
    }

    private void addAdditionalInfoToEntry(){
        entry.put("entryType", "Video");
    }

    private void updateUI(){
        NorthPanel.addRows(entry);
    }

    private void updateMessageTextArea(String message){
        SouthPanel.updateTextArea(message);
    }
}
