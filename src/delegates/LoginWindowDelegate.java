package delegates;

import java.io.FileNotFoundException;
import java.sql.SQLException;

public interface LoginWindowDelegate {
    void login(String username, String password) throws FileNotFoundException, SQLException;
}
