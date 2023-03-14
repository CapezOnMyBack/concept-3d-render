import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

public class UserInput {

    private static final Map<Integer, Boolean> pressedKeysMap = new HashMap<>();

    static {
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(event -> {
            synchronized (UserInput.class) {
                if (event.getID() == KeyEvent.KEY_PRESSED) pressedKeysMap.put(event.getKeyCode(), true);
                else if (event.getID() == KeyEvent.KEY_RELEASED) pressedKeysMap.put(event.getKeyCode(), false);
                return false;
            }
        });
    }

    public static boolean isKeyPressed(int keyCode) {
        return pressedKeysMap.getOrDefault(keyCode, false);
    }
}
