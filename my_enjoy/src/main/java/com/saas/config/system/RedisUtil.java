package com.saas.config.system;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.saas.system.exception.RedisException;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Component
public class RedisUtil {
    private static Logger logger = LoggerFactory.getLogger(RedisUtil.class);
    @Resource
    private RedisTemplate<String, String> redisTemplate;
    @Getter
    @Setter
    private String isExpire;
    @Getter
    @Setter
    private String expireDays;

    /**
     *  添加String 类型 key
     * @param key key
     * @param entity value
     * @param needExpire 是否设置过期时间
     * @param day 多久过期
     * @param <T> 参数类型
     * @throws RedisException 异常
     */
    public <T> void stringAdd(String key, T entity, Boolean needExpire,Integer day) throws RedisException {
        try {
            Gson gson = new Gson();
            redisTemplate.opsForValue().set(key, gson.toJson(entity), 60000, TimeUnit.MILLISECONDS);
            logger.info(String.format("redis string add:key [%s] value [%s] ", key, JSONObject.toJSONString(entity)));
            expire(key, needExpire,day);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(String.format("调用 redis string add 异常：%s", e.getMessage()));
            throw new RedisException(e.getMessage());
        }

    }

    /**
     *  get String  类型 value
     * @param key   key
     * @param clazz 类型
     * @throws RedisException 异常
     */
    public <T> T queryString(String key, Class<T> clazz) throws RedisException {
        T entity;
        try {
            Gson gson = new Gson();
            String json = redisTemplate.opsForValue().get(key);
            entity = gson.fromJson(json, clazz);
            logger.info(String.format("redis string get:key [%s] value [%s] ", key, JSONObject.toJSONString(entity)));
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            logger.error(String.format("调用 redis string get 异常：%s", e.getLocalizedMessage()));
            throw new RedisException(e.getLocalizedMessage());
        }

        return entity;
    }

    /**
     *  添加 set 类型 key
     * @param key key
     * @param value value
     * @param needExpire 是否设置过期时间
     * @param day 多久过期
     * @throws RedisException 异常
     */
    public void setAdd(String key,Boolean needExpire,Integer day, String... value) throws RedisException {
        try {
            redisTemplate.opsForSet().add(key, value);
            logger.info(String.format("redis set add:key [%s] value [%s] ", key, JSONObject.toJSONString(value)));
            expire(key, needExpire, day);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(String.format("调用 redis set add 异常：%s", e.getLocalizedMessage()));
            throw new RedisException(e.getLocalizedMessage());
        }
    }

    /**
     *  根据key获取set
     * @param key   key
     * @throws RedisException 异常
     */
    public Set<String> querySet(String key) throws RedisException {

        try {
            return redisTemplate.opsForSet().members(key);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(String.format("调用 redis set members 异常：%s", e.getLocalizedMessage()));
            throw new RedisException(e.getLocalizedMessage());
        }
    }


    /**
     *  查询set中是否包含某个值
     * @param key key
     * @param value value
     * @throws RedisException 异常
     */
    public Boolean setIsMember(String key, String value) throws RedisException {
        try {
            return redisTemplate.opsForSet().isMember(key, value);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(String.format("调用 redis set isMember 异常：%s", e.getLocalizedMessage()));
            throw new RedisException(e, e.getMessage());
        }
    }

    /**
     *  从list中随机取出多少数据
     * @param key key
     * @param count count
     * @throws RedisException 异常
     */
    public List<String> getRandomValueFromSet(String key,long count) throws RedisException {
        try {
            return redisTemplate.opsForSet().randomMembers(key, count);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(String.format("调用 redis set randomMembers 异常：%s", e.getLocalizedMessage()));
            throw new RedisException(e, e.getMessage());
        }
    }

    /**
     *  从list中随机取出多少数据
     * @param key key
     * @throws RedisException 异常
     */
    public String randomFromSet(String key) throws RedisException {
        try {
            return redisTemplate.opsForSet().randomMember(key);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(String.format("调用 redis set randomMember 异常：%s", e.getLocalizedMessage()));
            throw new RedisException(e, e.getMessage());
        }
    }
    /**
     *  从set中删除value
     * @param key key
     * @param value value
     * @throws RedisException 异常
     */
    public void removeValueFormSet(String key,Object... value) throws RedisException {
        try {
            redisTemplate.opsForSet().remove(key,value);
            logger.info(String.format("redis set remove:key [%s] value [%s] ", key, JSONObject.toJSONString(value)));
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(String.format("调用 redis set remove 异常：%s", e.getLocalizedMessage()));
            throw new RedisException(e, e.getMessage());
        }
    }


    /**
     *  向list 添加值
     * @param key key
     * @param value value
     * @param needExpire 是否设置过期时间
     * @param day 多久过期
     * @throws RedisException 异常
     */
    public void listPush(String key,Boolean needExpire,Integer day, String... value) throws RedisException {
        try {
            redisTemplate.opsForList().leftPushAll(key, value);
            logger.info(String.format("redis list leftPushAll:key [%s] value [%s] ", key, JSONObject.toJSONString(value)));
            expire(key, needExpire, day);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(String.format("调用 redis list leftPushAll 异常：%s", e.getLocalizedMessage()));
            throw new RedisException(e, e.getMessage());
        }
    }

    /**
     *  从list中 出栈一个元素
     * @param key key
     * @throws RedisException 异常
     */
    public String listPop(String key) throws RedisException {
        try {
            return redisTemplate.opsForList().leftPop(key);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(String.format("调用 redis list leftPop 异常：%s", e.getLocalizedMessage()));
            throw new RedisException(e, e.getMessage());
        }
    }
    /**
     *  查询list
     * @param key key
     * @throws RedisException 异常
     */
    public List<String> queryList(String key) throws RedisException{
        try {
            if(hasKey(key)){
                Long size=redisTemplate.opsForList().size(key);
                if(size>0){
                    return redisTemplate.opsForList().range(key,0,size);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            logger.error(String.format("调用 redis list range 异常：%s", e.getLocalizedMessage()));
            throw new RedisException(e, e.getMessage());
        }
        return null;
    }

    /**
     *  添加 hash 类型 hkey hvalue
     * @param key key
     * @param hKey hKey
     * @param hValue hValue
     * @param needExpire 是否设置过期时间
     * @param day 多久过期
     * @throws RedisException 异常
     */
    public void hashAdd(String key, String hKey, String hValue,Boolean needExpire,Integer day) throws RedisException {
        try {
            redisTemplate.opsForHash().put(key, hKey, hValue);
            logger.info(String.format("redis hash put:key [%s] hKey [%s] value [%s] ", key, hKey,hValue));
            expire(key, needExpire, day);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(String.format("调用 redis hash put 异常：%s", e.getLocalizedMessage()));
            throw new RedisException(e, e.getMessage());
        }
    }

    /**
     *  添加 hash 类型 hkey hvalue
     * @param key key
     * @param map 数据
     * @param needExpire 是否设置过期时间
     * @param day 多久过期
     * @throws RedisException 异常
     */
    public void hashAddAll(String key, Map<String,String> map,Boolean needExpire,Integer day) throws RedisException {
        try {
            redisTemplate.opsForHash().putAll(key, map);
            logger.info(String.format("redis hash put:key [%s] value [%s] ", key, map));
            expire(key, needExpire, day);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(String.format("调用 redis hash put 异常：%s", e.getLocalizedMessage()));
            throw new RedisException(e, e.getMessage());
        }
    }
    /**
     *  hash get
     * @param key key
     * @throws RedisException 异常
     */
    public String hashGet(String key, String hKey) throws RedisException {
        try {
            return (String) redisTemplate.opsForHash().get(key, hKey);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(String.format("调用 redis hash get 异常：%s", e.getLocalizedMessage()));
            throw new RedisException(e, e.getMessage());
        }
    }

    /**
     *  hash delete
     * @param key key
     * @param hKey hKey
     * @throws RedisException 异常
     */
    public Long hashRevmove(String key, String... hKey) throws RedisException {
        try {
            return redisTemplate.opsForHash().delete(key, hKey);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(String.format("调用 redis hash delete 异常：%s", e.getLocalizedMessage()));
            throw new RedisException(e, e.getMessage());
        }
    }

    public Set<Object> hashKeys(String key) throws RedisException {
        try {
            return redisTemplate.opsForHash().keys(key);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(String.format("调用 redis hash keys 异常：%s", e.getLocalizedMessage()));
            throw new RedisException(e, e.getMessage());
        }
    }
    /**
     *  hash getAll
     * @param key key
     * @throws RedisException 异常
     */
    public  Map<String, String> hashGetAll(String key) throws RedisException {
        try {
            Map<String, String> map=new HashMap<>();
            Map<Object,Object> objectMap=redisTemplate.opsForHash().entries(key);
//            objectMap.keySet();
            for(Object key1:objectMap.keySet()){
                if(objectMap.get(key1) instanceof String){
                    map.put((String)key1,(String)objectMap.get(key1));
                }else {
                    map.put((String)key1,JSONObject.toJSONString(objectMap.get(key1)));
                }
            }
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(String.format("调用 redis hash entries 异常：%s", e.getLocalizedMessage()));
            throw new RedisException(e, e.getMessage());
        }
    }
    /**
     *  hasKey
     * @param key key
     * @throws RedisException 异常
     */
    public Boolean hasKey(String key) throws RedisException {
        try {
            return redisTemplate.hasKey(key);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(String.format("调用 redis hasKey 异常：%s", e.getLocalizedMessage()));
            throw new RedisException(e, e.getMessage());
        }
    }

    /**
     *  delete
     * @param key key

     * @throws RedisException 异常
     */
    public void deleteKey(String key) throws RedisException {
        try {
            redisTemplate.delete(key);
            logger.error(String.format("调用 redis delete ：key [%s] ",key));
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(String.format("调用 redis delete 异常：%s", e.getLocalizedMessage()));
            throw new RedisException(e, e.getMessage());
        }
    }

    public  void deleteKey(Collection<String>  keys) throws RedisException {
        try {
            redisTemplate.delete(keys);
            logger.error(String.format("调用 redis delete ：keys [%s] ",keys));
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(String.format("调用 redis delete 异常：%s", e.getLocalizedMessage()));
            throw new RedisException(e, e.getMessage());
        }
    }
    /**
     * 设置key过期
     * @param key key
     * @param needExpire 是否过期
     * @param day 过期时间
     */
    private void expire(String key, Boolean needExpire,Integer day) throws RedisException {
        try {
            if (needExpire!=null&&needExpire) {
                    redisTemplate.expire(key, (day!=null&&day>0?day:7) * 24 * 60 * 60 * 1000, TimeUnit.MILLISECONDS);
                    logger.error(String.format("调用 redis expire ：key [%s] time 【%s】天",key,day!=null&&day>0?day:7));
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(String.format("调用 redis expire 异常：%s", e.getLocalizedMessage()));
            throw new RedisException(e, e.getMessage());
        }
    }

    public Set<String> AllKeys() throws RedisException {
        return queryKeys("*");
    }

    public Set<String> queryKeys(String key) throws RedisException {
        try {

            return redisTemplate.keys(key);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(String.format("调用 redis keys 异常：%s", e.getLocalizedMessage()));
            throw new RedisException(e, e.getMessage());
        }
    }
}
