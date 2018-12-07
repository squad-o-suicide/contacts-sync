package org.shoper.contacts.filter;

import org.shoper.commons.core.StringUtil;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class UploadToken {
    private Map<String, String> token = new HashMap<>();
    public String createToken(String userToken) {
        if (StringUtil.isEmpty(userToken)) return null;
        String uploadToken = UUID.randomUUID().toString().replace("-", "");
        token.put(userToken, uploadToken);
        return uploadToken;
    }

    public void removeToken(String userToken) {
        if (StringUtil.isEmpty(userToken)) return;
        token.remove(userToken);
    }

    public boolean checkToken(String token, String token1) {
        if (token1 == null) return false;
        return token1.equals(this.token.get(token));
    }
}
