package Database;

import java.sql.ResultSet;

public interface IDatabaseConnection {

    public boolean execute(String sqlScript);

    public Boolean[] execute(String[] sqlScriptsList);

    public ResultSet executeQuery(String sqlScript);
}
