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

    public int world_to_local_x(float world_x){
        return Math.round((world_x / scalar_x) * width);
    }

    public int world_to_local_y(float world_y){
        return Math.round((world_y / scalar_y) * height);
    }

    public float local_to_world_x(int local_x){
        return (local_x / width) * scalar_x;
    }

    public float local_to_world_y(int local_y){
        return (local_y / height) * scalar_y;
    }
}
