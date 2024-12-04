package org.zero.ds4;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.zero.ds4.model.Vocabulary;
import org.zero.ds4.service.VocabularyService;
import org.zero.ds4.utils.Input;
import org.zero.ds4.utils.VocabularyTable;

import java.io.IOException;
import java.sql.SQLException;

public class HelloApplication extends Application {
    private final VocabularyService vocabularyService = new VocabularyService();

    @Override
    public void start(Stage stage) throws IOException, SQLException {

        double width = 650;
        double height = 500;

        Button delete = new Button("Delete selected");

        HBox form = new HBox();
        form.setSpacing(10);
        form.setPadding(new Insets(10, 10, 10, 10));
        form.setAlignment(Pos.BOTTOM_LEFT);

        var idField = new Input("Id");
        var wordField = new Input("Word");
        var translateField = new Input("Translate");
        var speechField = new Input("Speech Part");

        Button submit = new Button("Submit");
        Button clear = new Button("Clear");

        form.getChildren().addAll(
                idField.get(),
                wordField.get(),
                translateField.get(),
                speechField.get(),
                submit,
                clear
        );

        clear.setOnMouseClicked(event -> {
            idField.setText("");
            wordField.setText("");
            translateField.setText("");
            speechField.setText("");
        });

        VocabularyTable table = VocabularyTable.getInstance();
        table.setWidth(width);
        table.setHeight(height);
        var vocabularyList = vocabularyService.getdAll();
        table.addAllVocabularies(vocabularyList);

        submit.setOnMouseClicked(event -> {
            var vocabulary = Vocabulary.builder()
                    .word(wordField.getText())
                    .translate(translateField.getText())
                    .speechPart(speechField.getText());

            var id = idField.getText();
            if (!id.isEmpty()) {
                vocabulary = vocabulary.id(Integer.parseInt(id));
            }

            try {
                vocabularyService.addOrUpdate(vocabulary);
                var updatedVocabularyList = vocabularyService.getdAll();
                table.refill(updatedVocabularyList);
            } catch (SQLException e) {
                throw new RuntimeException("Error adding vocabulary", e);
            }
        });

        delete.setOnMouseClicked(event -> {
            var selectedId = table.getSelectedIndex();
            try {
                vocabularyService.deleteById(selectedId);
                var updatedVocabularyList = vocabularyService.getdAll();
                table.refill(updatedVocabularyList);
                idField.setText("");
            } catch (SQLException e) {
                throw new RuntimeException("Error deleting selected vocabulary", e);
            }
        });

        table.selectionListiner(
                vocabulary -> {
                    idField.setText(String.valueOf(vocabulary.getId()));
                    wordField.setText(vocabulary.getWord());
                    translateField.setText(vocabulary.getTranslate());
                    speechField.setText(vocabulary.getSpeechPart());
                }
        );

        VBox root = new VBox(delete, form, table.getTable());
        Scene scene = new Scene(root, width, height);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}