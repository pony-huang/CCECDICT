package org.github.ponking66.ccecdit;

import java.sql.Connection;

public interface DictWordSqliteManagerService extends DictWordManagerService {
    Connection connection();
    void reset();
}
