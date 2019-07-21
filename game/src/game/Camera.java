package game;

public class Camera {

    public float width;
    public float height;

    public float translate_x;
    public float translate_y;

    public float dx;
    public float dy;

    public Camera(float translate_x, float translate_y, float width, float height){
        this.translate_x = translate_x;
        this.translate_y = translate_y;
        this.width = width;
        this.height = height;
    }

    public int world_to_pixel_width(float world_width, int screen_width){
        return Math.round(((world_width) / width) * screen_width);
    }

    public int world_to_pixel_x(float world_x, int screen_width){
        //System.out.println("World value is " + world_x + "translation is " + translate_x);
        int result =  (int)(((world_x - translate_x) / width) * screen_width);
        //System.out.println("X Pixel value of " + result);
        return result;
    }

    public int world_to_pixel_y(float world_y, int screen_height){
        return Math.round(((world_y - translate_y) / height) * screen_height);
    }

    public float pixel_to_world_x(int pixel_x, int screen_width){
        return ((pixel_x * width) / screen_width) + translate_x;
    }

    public float pixel_to_world_y(int pixel_y, int screen_height){
        return ((pixel_y * height) / screen_height) + translate_y;
    }

}
