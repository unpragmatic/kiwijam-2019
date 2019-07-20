package game;

public class Camera {

    public float VIEWPORT_WIDTH;
    public float VIEWPORT_HEIGHT;

    public float translate_x;
    public float translate_y;

    public Camera(float translate_x, float translate_y, float VIEWPORT_WIDTH, float VIEWPORT_HEIGHT){
        this.translate_x = translate_x;
        this.translate_y = translate_y;
        this.VIEWPORT_WIDTH = VIEWPORT_WIDTH;
        this.VIEWPORT_HEIGHT = VIEWPORT_HEIGHT;
    }

    public int world_to_pixel_x(float world_x, float pixel_width){
        return Math.round(((world_x - translate_x) / VIEWPORT_WIDTH) * pixel_width);
    }

    public int world_to_pixel_y(float world_y, float pixel_height){
        return Math.round(((world_y - translate_x) / VIEWPORT_HEIGHT) * pixel_height);
    }

    public float pixel_to_world_x(int pixel_x, float pixel_width){
        return ((pixel_x / pixel_width) * VIEWPORT_WIDTH) + translate_x;
    }

    public float pixel_to_world_y(int pixel_y, float pixel_height){
        return ((pixel_y / pixel_height) * VIEWPORT_HEIGHT) + translate_y;
    }

}
