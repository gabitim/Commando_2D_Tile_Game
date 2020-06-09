package com.commando.game.util;

import javax.xml.parsers.ParserConfigurationException;
import java.sql.SQLException;

/**
 * @author Timofti Gabriel
 */
public interface ClickedEvent {
    void action(int MouseButton) throws ParserConfigurationException, SQLException;
}
