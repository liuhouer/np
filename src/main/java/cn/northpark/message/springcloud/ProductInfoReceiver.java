package cn.northpark.message.springcloud;
//package cn.northpark.message;
//
//import cn.merryyou.order.utils.JsonUtil;
//import cn.merryyou.product.common.ProductInfoOutput;
//import com.fasterxml.jackson.core.type.TypeReference;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.amqp.rabbit.annotation.Queue;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//
///**
// * Created on 2018/4/13 0013.
// *
// * @author zlf
// * @email i@merryyou.cn
// * @since 1.0
// */
//@Slf4j
//@Component
//public class ProductInfoReceiver {
//
//    private static final String PRODUCT_STOCK_TEMPLATE = "product_stock_%s";
//
//    @Autowired
//    private StringRedisTemplate stringRedisTemplate;
//
//    @RabbitListener(queuesToDeclare = @Queue("productInfo"))
//    public void process(String message) {
//        //message 转成  ProductInfoOutput对象
//        List<ProductInfoOutput> productInfoOutputList = ( List<ProductInfoOutput>) JsonUtil.fromJson(message,
//                new TypeReference<List<ProductInfoOutput>>(){});
//        log.info("从队列【{}】接收到消息:{}", "productInfo", productInfoOutputList);
//
//        //储存到redis中
//        for(ProductInfoOutput productInfoOutput:productInfoOutputList){
//            stringRedisTemplate.opsForValue().set(String.format(PRODUCT_STOCK_TEMPLATE,productInfoOutput.getProductId()),
//                    String.valueOf(productInfoOutput.getProductStock()));
//        }
//    }
//}
