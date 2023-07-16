package org.github.ponking66.ccecdit;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.diagnostic.Logger;
import org.github.ponking66.ccecdit.settings.CodeCompletionSettings;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author pony
 * @date 2023/7/14
 */
public class WordManagerServiceImpl implements WordManagerService {

    private final Logger LOG = Logger.getInstance(WordManagerServiceImpl.class);

    private Connection connection = null;

    static {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                synchronized (this) {
                    if (connection == null || connection.isClosed()) {
                        try {
                            CodeCompletionSettings settings = ApplicationManager.getApplication().getService(CodeCompletionSettings.class);
                            connection = DriverManager.getConnection("jdbc:sqlite:" + settings.getSqliteDictPath());
                        } catch (Exception e) {
                            LOG.error(e);
                        }
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

    @Override
    public List<Word> searchWords(String prefix) {
        return searchWords(prefix, 20);
    }

    @Override
    public List<Word> searchWords(String prefix, int limit) {
        String sql = "SELECT *,rowid 'NAVICAT_ROWID' FROM main.stardict WHERE word LIKE '%s' LIMIT %s";
        List<Word> words = new ArrayList<>();
        try {
            ResultSet resultSet = getConnection().createStatement().executeQuery(String.format(sql, prefix + "%", limit));
            parseResultSet(words, resultSet);
        } catch (Exception e) {
            LOG.error(e);
        }
        return words;
    }

    @Override
    public List<Word> searchWords(String prefix, List<String> words, int limit) {
        if (words == null || words.size() == 0) {
            return searchWords(prefix, limit);
        } else {
            StringBuilder params = new StringBuilder("");
            for (String w : words) {
                params.append("'").append(w).append("'").append(",");
            }
            int i = params.lastIndexOf(",");
            String in = params.substring(0, i);

            String exclude = String.format("SELECT *,rowid 'NAVICAT_ROWID' FROM main.stardict WHERE word LIKE '%s' and word not in (%s) LIMIT %s", prefix + "%", in, limit);
            String include = String.format("SELECT *,rowid 'NAVICAT_ROWID' FROM main.stardict WHERE word in (%s)", in);

            List<Word> list = new ArrayList<>();
            try {
                parseResultSet(list, getConnection().createStatement().executeQuery(exclude));
                parseResultSet(list, getConnection().createStatement().executeQuery(include));
            } catch (Exception e) {
                LOG.error(e);
            }
            return list;
        }
    }

    private void parseResultSet(List<Word> words, ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            String id = resultSet.getString("id");
            String word = resultSet.getString("word");
            String sw = resultSet.getString("sw");
            String phonetic = resultSet.getString("phonetic");
            String definition = resultSet.getString("definition");
            String translation = resultSet.getString("translation");
            String pos = resultSet.getString("pos");
            int collins = resultSet.getInt("collins");
            int oxford = resultSet.getInt("oxford");
            String tag = resultSet.getString("tag");
            int bnc = resultSet.getInt("bnc");
            int frq = resultSet.getInt("frq");
            String exchange = resultSet.getString("exchange");
            String detail = resultSet.getString("detail");
            String audio = resultSet.getString("audio");
            words.add(new Word(word, phonetic, translation, exchange));
        }
    }
}
