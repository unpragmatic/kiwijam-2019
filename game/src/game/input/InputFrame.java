package game.input;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class InputFrame {
    public final Map<Integer, Boolean> onKeys = new ConcurrentHashMap<>();
    public final Queue<MouseDragEvent> mouseDragEventQueue = new ConcurrentLinkedQueue<>();
    public final static int UP = 38;
    public final static int DOWN = 40;
    public final static int W = 87;
    public final static int S = 83;

    //TODO - Remove After Testing
    public final static int T = 84;
    public final static int Y = 89;
    public final static int G = 71;
    public final static int H = 72;
    public final static int P = 80;
    public final static int Q = 81;
    public final static int R = 82;
    public final static int K = 75;

    private final List<MouseDragListener> mouseDragListeners = new ArrayList<>();
    public void addMouseDragListener(MouseDragListener listener) {
        mouseDragListeners.add(listener);
    }

    public void broadcastMouseDrag(MouseDragEvent event) {
        mouseDragEventQueue.add(event);
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

