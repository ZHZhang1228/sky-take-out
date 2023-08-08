package com.sky.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SetmealDishMapper {
    /*
    根据id查询套餐菜品信息
     */


    List<Long> getSetmealidsByDishIds(List<Long> dishIds);


}
