package extraction;

import ui.NorthPanel;
import ui.SouthPanel;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by James Silman
 * To Do:
 *      - Add extra details
 */

public class Audio extends Extraction{

    private ArrayList<Long> fileLocationOffsets = new ArrayList<Long>();

    private HashMap<String, String> entry = new HashMap<>();

    private String fileLocation;
    private long currentFileOffset;
    private String[][] fileSignatures = new String[][]{
            {"f2","72530555"}
    };

    public Audio(String fileLocation){
        updateMessageTextArea("Starting AUDIO extraction");

        this.fileLocation = fileLocation;

        try{
            for(int i = 0; i < fileSignatures.length; i++) {

                int fileSignatureCounter = i + 1;
                updateMessageTextArea("Starting AUDIO file header " + fileSignatureCounter + " of " + fileSignatures.length);

;               fileLocationOffsets = findFileEntries(this.fileLocation, fileSignatures[i][0], fileSignatures[i][1]);

                updateMessageTextArea("Found " +fileLocationOffsets.size() + " AUDIO entries using header " + fileSignatureCounter + " of " + fileSignatures.length);

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
            extractPartialFileEntryUntil(this.fileLocation, this.currentFileOffset, 285, "0");
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
            extractPartialFileEntryByLength(this.fileLocation, this.currentFileOffset, 39, 3);
            entry.put("fileSize", getFileSize());
        }catch (Exception e){

        }
    }

    private void extractDateAdded(){
        try{
            String dateAdded = extractPartialFileEntryByLengthByte(this.fileLocation, this.currentFileOffset, 115, 8);
            entry.put("dateAdded", convertDateAndTime(dateAdded));
        }catch (Exception e){

        }
    }

    private void extractLastViewed(){
        try{
            String lastViewed = extractPartialFileEntryByLengthByte(this.fileLocation, this.currentFileOffset, 123, 8);
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
        entry.put("entryType", "Audio");
    }

    private void updateUI(){
        NorthPanel.addRows(entry);
    }

    private void updateMessageTextArea(String message){
        SouthPanel.updateTextArea(message);
    }

}
