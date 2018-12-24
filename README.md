# Windows Media Database Extractor 

Forensic application to extract intelligence from Windows Media Player databases on Windows 7, 8 and 10

The Windows Media Player Database Extractor (WMPDE) extracts the following information from the Windows Media Player databse

![WMP Extractor Screenshot](https://github.com/JamesSilman/windows-media-player-extractor/blob/master/wmpdb-extractor.png)

### Audio
- File Name
- File Location
- File Size (in bytes on disk)
- File Date Added 
- File Date Last Viewed
- Times Viewed (not currently implemented)
- Additional Information (not currently implemented)

### Image
- File Name
- File Location
- File Size (in bytes on disk)
- File Date Added 
- File Date Last Viewed
- Year File Created (not currently implemented)
- Width in Pixels not currently implemented
- Additional Information (not currently implemented)

### Video 
- File Name
- File Location
- File Size (in bytes on disk)
- File Date Added 
- File Date Last Viewed
- Additional Information (not currently implemented)

## FAQ

### Why do I get multiple results for the same file?
The Windows Media Player database records several entires within itself for each file. It's not know why this is the case as there's no documentation from Microsoft.

### Where I can find more information about the research behind how the Windows Media Player database works?
You can find a copy of the original research by James Silman in the repo named *The Identification of Media Viewed Using Windows Media Player within Windows 7 Through the use of Cluster Analysis*

### What do I do if I have issues/feature requests?
Please open an issue on this repo. 

### I have questions, who do I contact?
Please email: james c t silman (at) gmail . com
