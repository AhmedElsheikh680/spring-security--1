package com.spring.service;

import com.spring.model.TokenInfo;
import com.spring.repo.TokenInfoRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TokenInfoService {
    private final TokenInfoRepo tokenInfoRepo;

    public TokenInfo findById(Long id) {

        return tokenInfoRepo.findById(id).orElse(null);
    }

    public Optional<TokenInfo> findByRefreshToken(String refreshToken) {

        return tokenInfoRepo.findByRefreshToken(refreshToken);
    }

    public TokenInfo save(TokenInfo entity) {

        return tokenInfoRepo.save(entity);
    }

    public void deleteById (Long id) {

        tokenInfoRepo.deleteById(id);
    }
}
