package test;
import game.Camera;
import org.junit.*;

public class CameraTest {

    @Test
    public void camera_is_created_with_correct_value(){
        Camera c = new Camera(400, 400);

        assert (c.width == 400);
        assert (c.height == 400);
    }

    @Test
    public void camera_convert_world_to_local_x_returns_correct_value(){
        Camera c = new Camera(400, 400);

        assert (c.world_to_local_x(0f) == 0);
        assert (c.world_to_local_x(Camera.scalar_x) == 400);
        assert (c.world_to_local_x(Camera.scalar_x/2) == 200);
    }

    @Test
    public void camera_convert_world_to_local_y_returns_correct_value(){
        Camera c = new Camera(400, 400);

        assert (c.world_to_local_y(0f) == 0);
        assert (c.world_to_local_y(Camera.scalar_y) == 400);
        assert (c.world_to_local_y(Camera.scalar_y/2) == 200);
    }

    @Test
    public void camera_convert_local_to_world_x_returns_correct_value(){
        Camera c = new Camera(400, 400);

        assert (c.local_to_world_x(0) == 0f);
        assert (c.local_to_world_x(400) == Camera.scalar_x);
        assert (c.local_to_world_x(200) == Camera.scalar_x/2);
    }

    @Test
    public void camera_convert_local_to_world_y_returns_correct_value(){
        Camera c = new Camera(400, 400);

        assert (c.local_to_world_y(0) == 0f);
        assert (c.local_to_world_x(400) == Camera.scalar_y);
        assert (c.local_to_world_x(200) == Camera.scalar_y/2);
    }

}
