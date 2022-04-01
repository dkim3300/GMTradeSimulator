package ui;

import model.Event;
import model.EventLog;

// Referencing Alarm System
public class ScreenPrinter implements LogPrinter {

    public ScreenPrinter() {
    }

    // MODIFIES: this
    // EFFECTS: prints out each event in console
    @Override
    public void printLog(EventLog el) {
        for (Event next : el) {
            System.out.println(next.toString());
        }
    }

}
