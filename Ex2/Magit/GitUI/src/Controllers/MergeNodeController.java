package Controllers;

import GitObjects.Branch;
import UIUtils.CommonUsed;
import Utils.MagitUtils;
import Utils.MergeResult;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.util.Optional;

public class MergeNodeController {

    @FXML private GridPane firstGridPane;
    @FXML private Label fileInCurrentBranch;
    @FXML private Label fileInAncestor;
    @FXML private Label fileInSecondBranch;
    @FXML private TextArea currentBranchTextArea;
    @FXML private TextArea ancestorTextArea;
    @FXML private TextArea secondBranchTextArea;
    @FXML private GridPane resGridPane;
    @FXML private Label finalResultLabel;
    @FXML private TextArea finalResultTextArea;
    @FXML private Button submitChangesButton;
    private String filePath;
    private Stage stage;
    private MainController mainController;

    void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    void setStage(Stage stage) {
        this.stage = stage;
    }

    void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    @FXML
    void changeRepositoryToFinalResult(ActionEvent event) {
        try {
            String finalData = finalResultTextArea.getText();
            File newFile = new File(filePath);
            if (newFile.delete()) {
                if(!finalData.isEmpty()) {
                    if (newFile.createNewFile()) {
                        MagitUtils.writeToFile(filePath, finalData);
                    }
                }
            }
            stage.close();
            CommonUsed.showSuccess("File changed successfully!");
            mainController.createNewCommit();

        } catch (IOException e) {
            CommonUsed.showError(e.getMessage());
        }
    }

    void resolveConflicts(MergeResult res) {
        currentBranchTextArea.setText(res.getFirstContent());
        ancestorTextArea.setText(res.getAncestorContent());
        secondBranchTextArea.setText(res.getSecondContent());

        currentBranchTextArea.setEditable(false);
        ancestorTextArea.setEditable(false);
        secondBranchTextArea.setEditable(false);

        finalResultTextArea.setEditable(true);
    }

}
