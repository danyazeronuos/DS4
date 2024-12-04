package org.zero.ds4.service;

import org.zero.ds4.model.Vocabulary;
import org.zero.ds4.repository.VocabularyRepository;

import java.sql.SQLException;
import java.util.List;

public class VocabularyService {
    private final VocabularyRepository vocabularyRepository = new VocabularyRepository();

    public List<Vocabulary> getdAll() throws SQLException {
        return vocabularyRepository.getAll();
    }

    public void deleteById(Integer id) throws SQLException {
        vocabularyRepository.delete(id);
    }

    public void addOrUpdate(Vocabulary vocabulary) throws SQLException {
        if (vocabulary.isId()) {
            vocabularyRepository.update(vocabulary);
        } else {
            vocabularyRepository.add(vocabulary);
        }
    }
}
