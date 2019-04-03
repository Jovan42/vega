package vega.it.praksa.services.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import vega.it.praksa.model.TeamMember;
import vega.it.praksa.model.UserDetailsImpl;
import vega.it.praksa.repositories.TeamMemberRepository;


@Component
public class UserDetailsServiceImpl implements UserDetailsService {
    private TeamMemberRepository teamMemberRepository;
    private UserDetailsImpl userDetail;

    @Autowired
    public UserDetailsServiceImpl(TeamMemberRepository teamMemberRepository) {
        this.teamMemberRepository = teamMemberRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        TeamMember teamMember = teamMemberRepository.getByUsername(username);
        userDetail = new UserDetailsImpl(teamMember);
        return userDetail;
    }
}
