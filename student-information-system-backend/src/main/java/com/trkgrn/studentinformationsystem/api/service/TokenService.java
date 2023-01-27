package com.trkgrn.studentinformationsystem.api.service;

import com.trkgrn.studentinformationsystem.api.model.entity.Token;
import com.trkgrn.studentinformationsystem.api.repository.TokenRepository;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

    private final TokenRepository tokenRepository;

    public TokenService(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    public Token save(Token token, Long expiredTime) {
        return this.tokenRepository.save(token,expiredTime);
    }

    public Token findTokenByTckNo(String tckNo) {
        return this.tokenRepository.findTokenByTckNo(tckNo);
    }

    public String delete(String tcNo) {
        return this.tokenRepository.delete(tcNo);
    }
}
