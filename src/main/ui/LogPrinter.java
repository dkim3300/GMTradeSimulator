package ui;

import exception.LogException;
import model.EventLog;

// Referencing Alarm System
public interface LogPrinter {
    /**
     * Prints the log
     * @param el  the event log to be printed
     * @throws LogException when printing fails for any reason
     */
    void printLog(EventLog el) throws LogException;
}
