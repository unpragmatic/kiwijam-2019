package game.input;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InputFrame {
    public final Map<Integer, Boolean> onKeys = new ConcurrentHashMap<>();

    public final static int UP = 38;
    public final static int DOWN = 40;
    public final static int W = 87;
    public final static int S = 83;

    private final List<MouseDragListener> mouseDragListeners = new ArrayList<>();

    public void addMouseDragListener(MouseDragListener listener) {
        mouseDragListeners.add(listener);
    }

    public void broadcastMouseDrag(MouseDragEvent event) {
        mouseDragListeners.forEach(l -> l.onDrag(event));
    }

    public boolean keyPressed(int keycode) {
        Boolean val = onKeys.get(keycode);
        return val == null ? false : val;
    }

    public void setKey(int keycode, boolean value) {
        onKeys.put(keycode, value);
    }
}

