package com.saas.system.annotation;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.lang.annotation.Inherited;

/**
 * Title:.
 * Description:
 * User: 丁鹏飞
 * Date: 2018-04-12
 * Time: 13:10
 */
@Inherited
@Mapper
@Repository
public @interface BppDao {
}
