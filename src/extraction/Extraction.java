package extraction;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

public class Extraction {

    private RandomAccessFile fa;
    private String fileEntry;
    private long filePositionOffset;
    private long originalFilePointPosition;
    private ArrayList<Long> fileLocationOffsets = new ArrayList<Long>();


    public String hexToASCII(int hex){

        String s = Integer.toHexString(hex);
        if(s.length() < 2){
            s = "0" + Integer.toHexString(hex);
        }

        return s;
    }

    public String hexConvertToString(String entry) throws Exception{

        String hex = entry;

        StringBuilder output = new StringBuilder();
        for (int i = 0; i < hex.length(); i+=2){
            String str = hex.substring(i, i+2);
            output.append((char)Integer.parseInt(str, 16));
        }

        String string = output.toString();

        return string;
    }

    public void extractPartialFileEntry(String fileLocation, long fileLocationOffset, long offsetJump, String searchUntil) throws Exception {

        fa = new RandomAccessFile(fileLocation, "r");

        fa.seek(fileLocationOffset + offsetJump);

        String temp = "";
        int tempByte;

        do{
            tempByte = fa.readByte();

            if(!Integer.toHexString(tempByte).equalsIgnoreCase(searchUntil)){
                temp += hexToASCII(tempByte);
            }

        }while (tempByte !=0);

        System.out.println(hexConvertToString(temp));

        this.fileEntry = hexConvertToString(temp);
        this.filePositionOffset = fa.getFilePointer();

        System.out.println(getFilePositionOffset());
    }

    public ArrayList<Long> findFileEntries(String fileLocation, String fileHeaderStartPoint, String fileHeaderRemaining) throws IOException {

        fa = new RandomAccessFile(fileLocation, "r");

        int bytesIn;

        for(int i = 0; i < fa.length(); i++) {

            bytesIn = fa.read();

            if (fileHeaderStartPoint.equalsIgnoreCase(hexToASCII(bytesIn))) {
                originalFilePointPosition = fa.getFilePointer();

                String temp = "";
                for (int h = 0; h < fileHeaderRemaining.length()/2; h++) {
                    bytesIn = fa.read();
                    temp = temp + hexToASCII(bytesIn);
                }

                if (temp.length() > 0 && temp.equalsIgnoreCase(fileHeaderRemaining)) {
                    fileLocationOffsets.add(fa.getFilePointer());
                }
            }
        }
        return fileLocationOffsets;
    }

    public String getFileEntry(){
        return this.fileEntry;
    }

    public long getFilePositionOffset(){
        return this.filePositionOffset;
    }
}
