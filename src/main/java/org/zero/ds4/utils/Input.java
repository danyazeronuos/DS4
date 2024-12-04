package org.zero.ds4.utils;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import lombok.Getter;

public class Input {
    private final VBox inputBox;
    private final TextField textField;

    public Input(String title) {
        inputBox = new VBox();
        inputBox.setSpacing(2);

        Label titleLabel = new Label(title);

        textField = new TextField();
        textField.setPrefWidth(120);

        inputBox.getChildren().addAll(titleLabel, textField);
    }

    public String getText() {
        return textField.getText();
    }

    public void setText(String text) {
        textField.setText(text);
    }

    public VBox get() {
        return inputBox;
    }
}
