package error;

import java.sql.SQLException;

public class FileExcepetion extends RuntimeException {
    public FileExcepetion(String message, Throwable throwable) {
        super(message, throwable);
    }
}
