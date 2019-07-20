package game;

public class Camera {

    public static float scalar_x = 1000f;
    public static float scalar_y = 1000f;

    public float width;
    public float height;

    public float translate_x;
    public float translate_y;

    public Camera(int width, int height){
        this.width = width;
        this.height = height;

        this.translate_x = 0;
        this.translate_y = 0;
    }

    public float getTranslate_x() {
        return translate_x;
    }

    public float getTranslate_y() {
        return translate_y;
    }

    public int world_to_local_x(float world){
        return Math.round((world / scalar_x) * width);
    }

    public int world_to_local_y(float world){
        return Math.round((world / scalar_y) * height);
    }

    public float local_to_world_x(int local){
        return (local / width) * scalar_x;
    }

    public float local_to_world_y(int local){
        return (local / height) * scalar_y;
    }
}
