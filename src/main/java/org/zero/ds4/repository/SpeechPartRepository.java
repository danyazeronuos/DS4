package org.zero.ds4.repository;

import org.zero.ds4.model.SpeechPart;
import org.zero.ds4.utils.Database;

import java.sql.Connection;
import java.sql.SQLException;

public class SpeechPartRepository {
    private final Connection con = Database.getInstance().getConnection();

    public SpeechPart getByTitle(String title) throws SQLException {
        var request = """
                select
                    sp.id,
                    sp.title
                from speech_part sp
                where lower(sp.title) like lower(?)
                """;
        var statement = con.prepareStatement(request);
        statement.setString(1, title);
        var result = statement.executeQuery();

        if (result.next()) {
            return SpeechPart.builder()
                    .id(result.getInt("id"))
                    .title(result.getString("title"))
                    .build();
        }

        return null;
    }
}
