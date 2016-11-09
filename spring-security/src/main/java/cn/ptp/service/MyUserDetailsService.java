package cn.ptp.service;


import cn.ptp.MyUserDetails;
import cn.ptp.entity.Role;
import cn.ptp.entity.User;
import cn.ptp.service.RoleService;
import cn.ptp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Transactional
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MyUserDetailsService implements UserDetailsService
{

    private final UserService userService;
    private final RoleService roleService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user;
        try {
            user = userService.findByUsername(username);
        } catch (Exception e) {
            throw new UsernameNotFoundException("user select fail");
        }
        if(user == null){
            throw new UsernameNotFoundException("no user found");
        } else {
            try {
                List<Role> roles = roleService.findByUser(user);
                return new MyUserDetails(user, roles);
            } catch (Exception e) {
                throw new UsernameNotFoundException("user role select fail");
            }
        }
    }
}
