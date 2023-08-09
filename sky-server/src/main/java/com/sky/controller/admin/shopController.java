package com.sky.controller.admin;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import com.sky.result.Result;



@RestController("adminshopController")
@Slf4j
@RequestMapping("/admin/shop")
@Api(tags = "店铺状态接口")
public class shopController {
    @Autowired
    private RedisTemplate redisTemplate;
    public static final String KEY = "SHOP_Status";
    @PutMapping("/{status}")
    @ApiOperation("设置店铺营业状态")
    public Result setStatus(@PathVariable Integer status){
        log.info("设置店铺营业状态：{}",status ==1 ? "营业中":"打烊了");
        redisTemplate.opsForValue().set(KEY,status);
        return Result.success();
    }

    @GetMapping("/status")
    @ApiOperation("查询店铺营业状态")
    public Result<Integer> getStatus(){
        Integer status = (Integer) redisTemplate.opsForValue().get(KEY);
        log.info("获取店铺营业状态,{}",status ==1?"营业中":"打烊了");


        return Result.success(status);
    }
}
