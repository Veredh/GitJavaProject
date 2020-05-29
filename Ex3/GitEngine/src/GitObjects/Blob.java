package GitObjects;

import Utils.MagitUtils;

import java.io.IOException;

public class Blob extends  GitObjectsBase{
    private String fileContent;

    public void setFileContent(String content){
        fileContent = content;
    }

    @Override
    public void getDataFromFile(String filePath) throws IOException {
        setFileContent(MagitUtils.unZipAndReadFile(filePath));
    }

    @Override
    public String toString() {
        return fileContent;
    }
}
