package game;

public class Camera {

    public float width;
    public float height;

    public float translate_x;
    public float translate_y;

    public Camera(float translate_x, float translate_y, float width, float height){
        this.translate_x = translate_x;
        this.translate_y = translate_y;
        this.width = width;
        this.height = height;
    }

    public int world_to_pixel_x(float world_x, float pixel_width){
        return Math.round(((world_x - translate_x) / width) * pixel_width);
    }

    public int world_to_pixel_y(float world_y, float pixel_height){
        return Math.round(((world_y - translate_x) / height) * pixel_height);
    }

    public float pixel_to_world_x(int pixel_x, float pixel_width){
        return ((pixel_x / pixel_width) * width) + translate_x;
    }

    public float pixel_to_world_y(int pixel_y, float pixel_height){
        return ((pixel_y / pixel_height) * height) + translate_y;
    }

}
