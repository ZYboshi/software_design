import cn.hutool.core.lang.Assert;
import com.controller.TushuOrderController;
import com.controller.UsersController;
import com.controller.YonghuController;
import com.entity.UsersEntity;
import com.ershoushujiaoyipingtaiApplication;
import com.utils.R;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest(classes = ershoushujiaoyipingtaiApplication.class)
public class AdminLoginTest {

    @Autowired
    public TushuOrderController tushuOrderController;

    public HttpServletRequest request = new MockHttpServletRequest();
    public Map<String, Object> params = new HashMap<>();


    @BeforeEach
    public void setup(){
        request.getSession().setAttribute("userId",1);
        params.put("addressId", 1);
        params.put("tushuOrderPaymentTypes", 1);
    }

    @Test
    public void testAddOrderBuyNumber(){
        params.put("tushus", "[{\"tushuId\" : 2," +
                "\"buyNumber\" : 20}]");
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class,()->{
            tushuOrderController.add(params, request);
        });
        Assertions.assertEquals("库存不足", exception.getMessage());
    }

    /*
    * 购买自己的商品
    * */
    @Test
    public void testAddOrderBuySelf(){
        params.put("tushus", "[{\"tushuId\" : 4," +
                "\"buyNumber\" : 5}]");
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class,()->{
            tushuOrderController.add(params, request);
        });
        Assertions.assertEquals("购买商品中有自己发布的商品（不可购买自己发布的商品）", exception.getMessage());
    }

    /*
     * 购买自己的商品
     * */
    @Test
    public void testAddOrderBuyIsMoneyEnough(){
        params.put("tushus", "[{\"tushuId\" : 2," +
                "\"buyNumber\" : 10}]");
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class,()->{
            tushuOrderController.add(params, request);
        });
        Assertions.assertEquals("余额不足,请充值！！！", exception.getMessage());
    }


}
