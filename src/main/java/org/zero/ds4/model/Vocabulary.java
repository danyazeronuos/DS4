package org.zero.ds4.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import lombok.Getter;
import lombok.Setter;

public class Vocabulary {
    private SimpleIntegerProperty id;
    private SimpleStringProperty word;
    private SimpleStringProperty translate;
    private SimpleStringProperty speechPart;

    public Vocabulary() {}

    public Vocabulary(Integer id, String word, String translate, String speechPart) {
        this.id = new SimpleIntegerProperty(id);
        this.word = new SimpleStringProperty(word);
        this.translate = new SimpleStringProperty(translate);
        this.speechPart = new SimpleStringProperty(speechPart);
    }

    public int getId() {
        return id.get();
    }

    public boolean isId() {
        return id != null;
    }

    public String getWord() {
        return word.get();
    }

    public String getTranslate() {
        return translate.get();
    }

    public String getSpeechPart() {
        return speechPart.get();
    }

    public static Vocabulary builder() {
        return new Vocabulary();
    }

    public Vocabulary id(int id) {
        this.id = new SimpleIntegerProperty(id);

        return this;
    }

    public Vocabulary word(String word) {
        this.word = new SimpleStringProperty(word);

        return this;
    }

    public Vocabulary translate(String translate) {
        this.translate = new SimpleStringProperty(translate);

        return this;
    }

    public Vocabulary speechPart(String speechPart) {
        this.speechPart = new SimpleStringProperty(speechPart);

        return this;
    }
}