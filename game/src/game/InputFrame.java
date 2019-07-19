package game;

import java.util.BitSet;

public class InputFrame {
    private final BitSet keyBitSet = new BitSet();

    public boolean keyPressed(int keycode) {
        return keyBitSet.get(keycode);
    }

    public void setKey(int keycode, boolean value) {
        keyBitSet.set(keycode, value);
    }
}
