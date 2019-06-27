package dfsp.security;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;


@Service
public class CustomUserService implements UserDetailsService {

    private UserAppRepository userAppRepository;
    private PasswordEncoder bcryptEncoder;

    public CustomUserService(UserAppRepository userAppRepository, PasswordEncoder bcryptEncoder) {
        this.userAppRepository = userAppRepository;
        this.bcryptEncoder = bcryptEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userAppRepository
                .findUserAppByName(username)
                .map(CustomUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not find!"));
    }


    /**
     * metoda dodająca nowego Usera
     */

    public UserApp save(LoginUser loginUser) {
        return userAppRepository.save(UserApp
                .builder()
                .active(1)
                .password(bcryptEncoder.encode(loginUser.getPassword()))
                .username(loginUser.getUsername())
                .roles(new HashSet<>())
                .build());
    }

    public List<UserApp> findAll() {
        return userAppRepository.findAll();
    }

}
