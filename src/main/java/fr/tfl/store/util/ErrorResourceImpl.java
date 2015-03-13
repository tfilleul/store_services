package fr.tfl.store.util;


public class ErrorResourceImpl  {
    private final int status;
    private final String message;

    public ErrorResourceImpl(int s, String m) {
        status = s;
        message = m;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}

