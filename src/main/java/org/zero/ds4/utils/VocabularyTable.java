package org.zero.ds4.utils;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.Getter;
import org.zero.ds4.model.Vocabulary;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class VocabularyTable {
    private static final VocabularyTable INSTANCE = new VocabularyTable();
    private ObservableList<Vocabulary> vocabularies = FXCollections.observableArrayList();
    @Getter
    private final TableView<Vocabulary> table;

    public static VocabularyTable getInstance() {
        return INSTANCE;
    }

    private VocabularyTable() {
        table = new TableView<>(vocabularies);

        TableColumn<Vocabulary, String> idColumn = new TableColumn<>("Id");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        table.getColumns().add(idColumn);


        TableColumn<Vocabulary, String> wordColumn = new TableColumn<>("Word");
        wordColumn.setCellValueFactory(new PropertyValueFactory<Vocabulary, String>("word"));
        table.getColumns().add(wordColumn);

        TableColumn<Vocabulary, String> translateColumn = new TableColumn<>("Translate");
        translateColumn.setCellValueFactory(new PropertyValueFactory<Vocabulary, String>("translate"));
        table.getColumns().add(translateColumn);

        TableColumn<Vocabulary, String> speechPartColumn = new TableColumn<>("Speech Part");
        speechPartColumn.setCellValueFactory(new PropertyValueFactory<>("speechPart"));
        table.getColumns().add(speechPartColumn);
    }

    public void setWidth(double width) {
        table.setPrefWidth(width);
    }

    public void setSelection(SelectionMode selectionMode) {
        table.getSelectionModel().setSelectionMode(selectionMode);
    }

    public Integer getSelectedIndex() {
        return table.getSelectionModel().getSelectedItems().stream()
                .map(Vocabulary::getId)
                .findFirst()
                .orElse(null);
    }

    public void selectionListiner(Consumer<Vocabulary> consumer) {
        var selectedItems = table.getSelectionModel().getSelectedItems();

        selectedItems.addListener(
                new ListChangeListener<Vocabulary>() {
                    @Override
                    public void onChanged(
                            Change<? extends Vocabulary> change) {
                        var selectedItemId = change.getList().stream().findFirst().orElse(null);
                        if (selectedItemId != null) consumer.accept(selectedItemId);

                    }
                });
    }

    public void refill(List<Vocabulary> vocabularies) {
        this.vocabularies = FXCollections.observableArrayList(vocabularies);
        table.getItems().clear();
        table.getItems().addAll(vocabularies);
    }

    public void setHeight(double height) {
        table.setPrefHeight(height);
    }

    public void addVocabulary(Vocabulary vocabulary) {
        vocabularies.add(vocabulary);
    }

    public void addAllVocabularies(Vocabulary... list) {
        this.addAllVocabularies(Arrays.asList(list));
    }

    public void addAllVocabularies(List<Vocabulary> list) {
        vocabularies.addAll(list);
    }
}
