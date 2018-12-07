package org.shoper.contacts.filter;

import org.shoper.contacts.bean.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Component
public class UserSession {
    @Value("${session.timeout}")
    private long sessionTimeout;
    @Value("${session.timeoutUnit}")
    private TimeUnit sessionTimeoutUnit;
    private Map<String, Long> expireMap = new HashMap<>();
    private Map<String, User> userSessionMap = new HashMap<>();
    private Map<String, String> usernameToken = new HashMap<>();

    public void setUserSession(String token, User user) {
        if (Objects.nonNull(token) && Objects.nonNull(user)) {
            //检查用户重复登录
            if (usernameToken.containsKey(user.getUsername())) {
                String oldToken = usernameToken.get(user.getUsername());
                removeSession(oldToken);
                usernameToken.remove(user.getUsername());
            }
            userSessionMap.put(token, user);
            expireMap.put(token, System.currentTimeMillis());
        }
    }

    public User getUserSession(String token) {
        if (expireMap.containsKey(token)) {
            Long time = expireMap.get(token);
            if (time < System.currentTimeMillis() - sessionTimeoutUnit.toMillis(sessionTimeout)) {
                expireMap.remove(token);
                userSessionMap.remove(token);
                return null;
            } else {
                //刷新过期时间
                expireMap.put(token, System.currentTimeMillis());
                return userSessionMap.get(token);
            }
        } else
            return null;
    }

    public void removeSession(String token) {
        if (!StringUtils.isEmpty(token)) {
            userSessionMap.remove(token);
            expireMap.remove(token);
        }
    }
}
