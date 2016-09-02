package com.xunhui.gateway.service.session;

import com.xunhui.gateway.dao.UserDao;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.session.ExpiringSession;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.session.data.redis.RedisOperationsSessionRepository;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Sean.W on 2016/8/31.
 * 操作redis中session service
 */
@Service
@Getter
@Slf4j
public class SessionService {
    @Autowired
    private JedisConnectionFactory jedisConnectionFactory;
    @Autowired
    private UserDao userDao;
    private FindByIndexNameSessionRepository sessionRepository;

    @PostConstruct
    private void initSessionRepository(){
        this.sessionRepository = new RedisOperationsSessionRepository(jedisConnectionFactory);
    }

    public String findUserRedisSession(){
        JSONObject returnJson = new JSONObject();
        try{
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication() .getPrincipal();
            returnJson.put("name",userDetails.getUsername());
            returnJson.put("role",userDetails.getAuthorities());
            Map<String, ExpiringSession> sessionIdToSession = this.sessionRepository
                    .findByIndexNameAndIndexValue(
                            FindByIndexNameSessionRepository.PRINCIPAL_NAME_INDEX_NAME,
                            userDetails.getUsername());
            System.out.println("sessionIdToSession = " + sessionIdToSession);
            int session_size = sessionIdToSession.size();
            if(session_size>2){
                Iterator<String> iterator = sessionIdToSession.keySet().iterator();
                int temp_count = 1;
                while(iterator.hasNext()) {
                    if(temp_count==session_size){//保留最后一个最新session
                        break;
                    }
                    String session_id = iterator.next();
                    System.out.println("del = " + session_id);
                    sessionRepository.delete(session_id);
                    temp_count++;
                }
                returnJson.put("info","已注销其他登录设备！");
            }
        }catch (Exception e){
            log.error("",e);
        }
        return returnJson.toString();
    }
}
