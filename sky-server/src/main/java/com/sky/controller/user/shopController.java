package com.sky.controller.user;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import com.sky.result.Result;



@RestController("usershopController")
@Slf4j
@RequestMapping("/user/shop")
@Api(tags = "店铺状态接口")
public class shopController {
    @Autowired
    private RedisTemplate redisTemplate;
    public static final String KEY = "SHOP_Status";



    @GetMapping("/status")
    @ApiOperation("查询店铺营业状态")
    public Result<Integer> getStatus(){
        Integer status = (Integer) redisTemplate.opsForValue().get(KEY);
        log.info("获取店铺营业状态,{}",status ==1?"营业中":"打烊了");


        return Result.success(status);
    }
}
