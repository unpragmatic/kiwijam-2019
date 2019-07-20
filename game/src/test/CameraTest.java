package test;
import game.Camera;
import org.junit.*;

public class CameraTest {


    @Test
    public void camera_convert_world_to_pixel_x_returns_correct_value(){
        Camera c = new Camera(0, 0, 1000, 1000);

        assert (c.world_to_pixel_x(0 ,400) == 0);
        assert (c.world_to_pixel_x(1000 ,400) == 400);
        assert (c.world_to_pixel_x(500 ,400) == 200);
    }

    @Test
    public void camera_convert_world_to_pixel_y_returns_correct_value(){
        Camera c = new Camera(0, 0, 1000, 1000);

        assert (c.world_to_pixel_y(0 ,400) == 0);
        assert (c.world_to_pixel_y(1000 ,400) == 400);
        assert (c.world_to_pixel_y(500 ,400) == 200);
    }

    @Test
    public void camera_convert_pixel_to_world_x_returns_correct_value(){
        Camera c = new Camera(0, 0, 1000, 1000);

        assert (c.pixel_to_world_x(0 ,400) == 0);
        System.out.println(c.pixel_to_world_x(400, 400));
        assert (c.pixel_to_world_x(400 ,400) == 1000);
    }

    @Test
    public void camera_convert_pixel_to_world_y_returns_correct_value(){
        Camera c = new Camera(0, 0, 1000, 1000);

        assert (c.pixel_to_world_y(0 ,400) == 0);
        System.out.println(c.pixel_to_world_y(400, 400));
        assert (c.pixel_to_world_y(400 ,400) == 1000);
    }
}
