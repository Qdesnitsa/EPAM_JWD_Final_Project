package by.sidina.it_team.dao.exception;

import java.io.Serializable;

public class DAOException extends Exception implements Serializable {
    private static final long serialVersionUID = 1L;

    public DAOException() {
        super();
    }

    public DAOException(String message, Throwable cause) {
        super(message, cause);
    }

    public DAOException(String message) {
        super(message);
    }

    public DAOException(Throwable cause) {
        super(cause);
    }
}
