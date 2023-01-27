package com.trkgrn.studentinformationsystem.api.repository;

import com.trkgrn.studentinformationsystem.api.model.entity.Token;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

@Repository
public class TokenRepository {
    private final RedisTemplate template;

    public TokenRepository(RedisTemplate template) {
        this.template = template;
    }

    public Token save(Token token, Long expiredTime) {
        template.opsForValue().set(token.getTckNo(), token.getJwt(), expiredTime, TimeUnit.HOURS);
        return token;
    }

    public Token findTokenByTckNo(String tckNo) {
        String jwt = template.opsForValue().get(tckNo).toString();
        return new Token(tckNo, jwt);
    }

    public String delete(String username) {
        template.opsForValue().getAndDelete(username);
        return "token removed";
    }
}
