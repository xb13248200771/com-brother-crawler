import com.tongji.bean.Request;
import com.tongji.enums.RequestMethod;
import com.tongji.utils.JsonUtils;
import org.junit.Test;

/**
 * Created by 13248 on 2016/11/12.
 */
public class JsonUtilsTest {

    @Test
    public void jsonUtilsTest() {
        Request request = new Request();
        request.setMethod(RequestMethod.GET);
        System.out.println(JsonUtils.toString(request));
    }
}
