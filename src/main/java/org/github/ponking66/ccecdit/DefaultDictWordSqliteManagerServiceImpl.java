package org.github.ponking66.ccecdit;

import com.intellij.openapi.diagnostic.Logger;
import com.intellij.util.ResourceUtil;
import org.github.ponking66.ccecdit.util.SqliteUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author pony
 * @date 2023/7/14
 */
public class DefaultDictWordSqliteManagerServiceImpl implements DictWordSqliteManagerService {

    private final Logger LOG = Logger.getInstance(DefaultDictWordSqliteManagerServiceImpl.class);

    private Connection connection = null;

    protected String connectionUrl() {
        String path = ResourceUtil.getResource(DictWordManagerService.class.getClassLoader(), "dict", "10k.db").getPath();
        return SqliteUtil.SQLITE_RESOURCE_JAR + path;
    }

    @Override
    public Connection connection() {
        try {
            synchronized (this) {
                if (connection == null || connection.isClosed()) {
                    try {
                        connection = SqliteUtil.create(connectionUrl());
                    } catch (Exception e) {
                        LOG.error(e);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

    @Override
    public void reset() {
        synchronized (this) {
            try {
                connection = SqliteUtil.create(connectionUrl());
            } catch (Exception e) {
                LOG.error(e);
            }
        }
    }

    @Override
    public List<Word> searchWords(String prefix) {
        return searchWords(prefix, 20);
    }

    @Override
    public List<Word> searchWords(String prefix, int limit) {
        String sql = "SELECT * FROM main.stardict WHERE word LIKE '%s' LIMIT %s";
        List<Word> words = new ArrayList<>();
        try {
            ResultSet resultSet = connection().createStatement().executeQuery(String.format(sql, prefix + "%", limit));
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

            String exclude = String.format("SELECT * FROM main.stardict WHERE word LIKE '%s' and word not in (%s) LIMIT %s", prefix + "%", in, limit);
            String include = String.format("SELECT * FROM main.stardict WHERE word in (%s)", in);

            List<Word> list = new ArrayList<>();
            try {
                parseResultSet(list, connection().createStatement().executeQuery(exclude));
                parseResultSet(list, connection().createStatement().executeQuery(include));
            } catch (Exception e) {
                LOG.error(e);
            }
            return list;
        }
    }

    private void parseResultSet(List<Word> words, ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
//            int id = resultSet.getString("id");
            String word = resultSet.getString("word");
//            String sw = resultSet.getString("sw");
            String phonetic = resultSet.getString("phonetic");
//            String definition = resultSet.getString("definition");
            String translation = resultSet.getString("translation");
//            String pos = resultSet.getString("pos");
//            int collins = resultSet.getInt("collins");
//            int oxford = resultSet.getInt("oxford");
//            String tag = resultSet.getString("tag");
//            int bnc = resultSet.getInt("bnc");
//            int frq = resultSet.getInt("frq");
            String exchange = resultSet.getString("exchange");
//            String detail = resultSet.getString("detail");
//            String audio = resultSet.getString("audio");
            words.add(new Word(word, phonetic, translation, exchange));
        }
    }
}
