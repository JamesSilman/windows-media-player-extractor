package extraction;

import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MP3 extends Extraction{

    String fileLocation = "";
    private String fileHeaderStartPoint = "f2";
    private String fileHeaderRemaining = "72530555";

    private int fileNameOffsetJump = 285;

    private ArrayList<Long> fileLocationOffsets = new ArrayList<Long>();

    private RandomAccessFile fa;

    private Map<String, String> entry = new HashMap<>();


    public MP3(String fileLocation){

        this.fileLocation = fileLocation;

        try{
            fileLocationOffsets = findFileEntries(this.fileLocation, fileHeaderStartPoint, fileHeaderRemaining);
            //System.out.println(fileLocationOffsets.size());
            if(fileLocationOffsets.size() > 0){
                extractFileNames();
            }

        }catch (Exception ioe ){ }

        for (Map.Entry<String, String> entry : entry.entrySet()) {
            System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue());
        }

    }

    private void extractFileNames(){
        try{
            for (long flo: fileLocationOffsets) {
                extractPartialFileEntry(this.fileLocation, flo, fileNameOffsetJump, "0");
                entry.put("File Name", getFileEntry());
                long temp = getFilePositionOffset();
                extractFileLocation(temp);
            }
        }catch (Exception ioe ){ }

    }

    private void extractFileLocation(long fileOffsetLocation){
        System.out.println("" + fileOffsetLocation);

        try{
            extractPartialFileEntry(this.fileLocation, fileOffsetLocation, 0, "0");
            entry.put("File Location", getFileEntry());
        }catch (Exception ioe ){ }

    }



}
