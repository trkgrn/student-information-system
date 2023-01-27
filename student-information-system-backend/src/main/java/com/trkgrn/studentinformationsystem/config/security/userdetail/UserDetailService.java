package com.trkgrn.studentinformationsystem.config.security.userdetail;

import com.trkgrn.studentinformationsystem.api.model.entity.User;
import com.trkgrn.studentinformationsystem.api.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailService implements UserDetailsService {
    private final UserRepository userRepository;

    public UserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public CustomUserDetails loadUserByUsername(String tckNo) throws UsernameNotFoundException {
        User user = this.userRepository.findByTckNo(tckNo);
        if (user==null)
            throw new UsernameNotFoundException("Kullanıcı bulunamadı!");

        return new CustomUserDetails(user);
    }
}
