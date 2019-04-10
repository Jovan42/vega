package vega.it.praksa.services.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import vega.it.praksa.exceptions.NotFoundException;
import vega.it.praksa.mappers.DtoMapper;
import vega.it.praksa.model.TeamMember;
import vega.it.praksa.model.UserDetailsImpl;
import vega.it.praksa.model.dtos.TeamMemberDto;
import vega.it.praksa.repositories.TeamMemberRepository;


@Component
public class UserDetailsServiceImpl implements UserDetailsService {
    private TeamMemberRepository teamMemberRepository;
    private DtoMapper mapper;

    public UserDetailsServiceImpl(TeamMemberRepository teamMemberRepository, DtoMapper mapper) {
        this.teamMemberRepository = teamMemberRepository;
        this.mapper = mapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        TeamMemberDto teamMember = teamMemberRepository.getByUsername(username)
                .map(mapper::teamMemberToTeamMemberDto)
                .orElseThrow(()-> new NotFoundException("Team member with usernanme '" + username +"' is not found"));

        UserDetailsImpl userDetail = new UserDetailsImpl(mapper.teamMemberDtoToTeamMember(teamMember));
        return userDetail;
    }
}
