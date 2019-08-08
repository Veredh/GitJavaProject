import java.util.LinkedList;
import java.util.List;

public class Folder extends GitObjectsBase {
    private List<FileDetails> filesList = new LinkedList<>();

    void addFile(FileDetails newFile)
    {
        filesList.add(newFile);
    }

    List<FileDetails> getFilesList(){
        return filesList;
    }

    @Override
    public boolean isFolder() {
        return true;
    }

    @Override
    public String toString() {
        String content = "";
        for (FileDetails child : filesList){
            content = content.concat(child.toString());
            content = content.concat("\r\n");
        }
        return content;
    }

    @Override
    void getDataFromFile(String filePath){
        String data = MagitUtils.unZipAndReadFile(filePath);
        if (data != null) {
            String[] childsData = data.split("\r\n");
            for (String child : childsData) {
                String[] childDetails = child.split(MagitUtils.DELIMITER);
                FileDetails newDetails = new FileDetails(childDetails[0], childDetails[1], childDetails[2],
                        childDetails[3], childDetails[4]);
                addFile(newDetails);

            }
        }
    }
}