package extraction;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Date;

public class Extraction {

    private RandomAccessFile fa;
    private String fileEntry;
    private String fileSize;

    private long filePositionOffset;
    private long originalFilePointPosition;

    private ArrayList<Long> fileLocationOffsets = new ArrayList<Long>();


    private String hexToASCII(int hex){

        String s = Integer.toHexString(hex);
        if(s.length() < 2){
            s = "0" + Integer.toHexString(hex);
        }

        return s;
    }

    private void convertFileSize(String size) throws Exception {

        //	BufferedReader buffRead = new BufferedReader(new InputStreamReader(System.in));
        String temp = size;
        long convertedSize = Long.parseLong(temp,16);
        String cs = String.valueOf(convertedSize);
        this.fileSize = cs;
    }

    private String hexConvertToString(String entry) throws Exception{

        String hex = entry;

        StringBuilder output = new StringBuilder();
        for (int i = 0; i < hex.length(); i+=2){
            String str = hex.substring(i, i+2);
            output.append((char)Integer.parseInt(str, 16));
        }

        String string = output.toString();

        return string;
    }

    public String convertDateAndTime(String date) throws IOException, Exception {

        long dateNumber = Long.parseLong(date,16);
        Date convertedDate = new Date((dateNumber - 116444736000000000l) / 10000);
        return String.valueOf(convertedDate);
    }

    public void extractPartialFileEntryUntil(String fileLocation, long fileLocationOffset, long offsetJump, String searchUntil) throws Exception {

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

        this.fileEntry = hexConvertToString(temp);
        this.filePositionOffset = fa.getFilePointer();

        //System.out.println(getFilePositionOffset());
    }

    public String extractPartialFileEntryByLength(String fileLocation, long fileLocationOffset, long offsetJump, int readLength) throws Exception {

        fa = new RandomAccessFile(fileLocation, "r");

        fa.seek(fileLocationOffset + offsetJump);

        String temp = "";
        int tempByte;

        for(int i = 0; i < readLength; i++){
            tempByte = fa.readByte();
            temp += hexToASCII(tempByte);
        }

        return temp;

    }

    public String extractPartialFileEntryByLengthByte(String fileLocation, long fileLocationOffset, long offsetJump, int readLength) throws Exception {

        fa = new RandomAccessFile(fileLocation, "r");

        fa.seek(fileLocationOffset + offsetJump);

        String temp = "";

        for(int i = 0; i < readLength; i++){
            temp = hexToASCII(fa.read()) + temp;
        }

        convertFileSize(temp);

        return temp;

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

    public String getFileSize(){
        return this.fileSize;
    }
}
