package org.zero.ds4.repository;

import lombok.RequiredArgsConstructor;
import org.zero.ds4.model.Vocabulary;
import org.zero.ds4.utils.Database;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VocabularyRepository {
    private final Connection con = Database.getInstance().getConnection();
    private final SpeechPartRepository speechPartRepository = new SpeechPartRepository();

    public List<Vocabulary> getAll() throws SQLException {
        String request = """
                select
                	v.id,
                	v.word,
                	v.translate,
                	sp.title
                from vocabulary v, speech_part sp
                where sp.id = v.speech_part_id
                order by v.id asc ;
                """;
        var statement = con.createStatement();
        var requestResult = statement.executeQuery(request);

        List<Vocabulary> vocabularyList = new ArrayList<>();
        while (requestResult.next()) {
            var newVocabulary = Vocabulary.builder()
                    .id(requestResult.getInt(1))
                    .word(requestResult.getString(2))
                    .translate(requestResult.getString(3))
                    .speechPart(requestResult.getString(4));

            vocabularyList.add(newVocabulary);
        }

        return vocabularyList;
    }

    public void delete(Integer id) throws SQLException {
        String request = """
                delete from vocabulary where id = ?;
                """;
        var statement = con.prepareStatement(request);
        statement.setInt(1, id);
        statement.execute();
    }

    public void add(Vocabulary vocabulary) throws SQLException {
        var speechPart = speechPartRepository.getByTitle(vocabulary.getSpeechPart());
        String request = """
                insert into vocabulary(word, translate, speech_part_id)
                values(?, ?, ?);
                """;
        var statement = con.prepareStatement(request);
        statement.setString(1, vocabulary.getWord());
        statement.setString(2, vocabulary.getTranslate());
        statement.setInt(3, speechPart.id());
        statement.execute();
    }

    public void update(Vocabulary vocabulary) throws SQLException {
        var speechPart = speechPartRepository.getByTitle(vocabulary.getSpeechPart());
        String request = """
                update vocabulary set word = ?, translate = ?, speech_part_id = ? where id = ?;
                """;
        var statement = con.prepareStatement(request);
        statement.setString(1, vocabulary.getWord());
        statement.setString(2, vocabulary.getTranslate());
        statement.setInt(3, speechPart.id());
        statement.setInt(4, vocabulary.getId());
        statement.execute();
    }

}
