package vega.it.praksa.services.jpa;

//import org.springframework.security.crypto.password.PasswordEncoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.actuate.endpoint.SecurityContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import vega.it.praksa.exceptions.NotFoundException;
import vega.it.praksa.mappers.DtoMapper;
import vega.it.praksa.model.TeamMember;
import vega.it.praksa.model.UserDetailsImpl;
import vega.it.praksa.model.dtos.TeamMemberDto;
import vega.it.praksa.model.dtos.TeamMemberListDto;
import vega.it.praksa.repositories.TeamMemberRepository;
import vega.it.praksa.services.TeamMemberService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
@Slf4j
public class TeamMemberServiceImpl implements TeamMemberService {
    private TeamMemberRepository teamMemberRepository;
    private DtoMapper mapper;
   private PasswordEncoder passwordEncoder;


    public TeamMemberServiceImpl(TeamMemberRepository teamMemberRepository, DtoMapper mapper, PasswordEncoder passwordEncoder) {
        this.teamMemberRepository = teamMemberRepository;
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public TeamMemberListDto get() {
        List<TeamMember> teamMembers =  teamMemberRepository.findAll();
        teamMembers.forEach(teamMember -> teamMember.setPassword(""));

        List<TeamMemberDto> teamMembersDto = teamMembers.stream()
                .map(mapper::teamMemberToTeamMemberDto)
                .collect(Collectors.toList());

        return new TeamMemberListDto(teamMembersDto);

    }

    @Override
    //TODO preko lambda izraza
    public TeamMemberDto get(Long id) {
        TeamMemberDto teamMember = teamMemberRepository.findById(id)
                .map(mapper::teamMemberToTeamMemberDto)
                .orElseThrow(()-> new NotFoundException("Team member with id '" + id +"' is not found"));

        teamMember.setPassword("");
        return teamMember;
    }

    @Override
    public TeamMemberDto add(TeamMemberDto teamMemberDto) {
        teamMemberDto.setId(null);
        teamMemberDto.setPassword(passwordEncoder.encode(teamMemberDto.getPassword()));
        return mapper.teamMemberToTeamMemberDto(teamMemberRepository.save(
                mapper.teamMemberDtoToTeamMember(teamMemberDto))
        );

    }

    @Override
    public TeamMemberDto update(TeamMemberDto teamMemberDto) {
        String oldPassword = get(teamMemberDto.getId()).getPassword();
        teamMemberDto.setPassword(oldPassword);
        return mapper.teamMemberToTeamMemberDto(teamMemberRepository.save(
                mapper.teamMemberDtoToTeamMember(teamMemberDto))
        );
    }

    @Override
    public void delete(Long id) {
        teamMemberRepository.deleteById(id);
    }

    @Override
    public TeamMemberDto changePassword(Long id, String newPassword) {
        TeamMemberDto teamMemberDto = get(id);
        teamMemberDto.setPassword(passwordEncoder.encode(newPassword));

        return mapper.teamMemberToTeamMemberDto(teamMemberRepository.save(
                mapper.teamMemberDtoToTeamMember(teamMemberDto))
        );
    }

    @Override
    public TeamMemberDto get(String username) {
        TeamMemberDto teamMember = teamMemberRepository.getByUsername(username)
                .map(mapper::teamMemberToTeamMemberDto)
                .orElseThrow(()-> new NotFoundException("Team member with usernanme '" + username +"' is not found"));

        teamMember.setPassword("");
        return teamMember;
    }

    @Override
    public Boolean login(String username, String password) {
        TeamMemberDto teamMember = teamMemberRepository.getByUsername(username)
                .map(mapper::teamMemberToTeamMemberDto)
                .orElseThrow(()-> new NotFoundException("Team member with usernanme '" + username +"' is not found"));


        if (passwordEncoder.matches(password, teamMember.getPassword())) {
            UserDetailsImpl userDetails = new UserDetailsImpl(mapper.teamMemberDtoToTeamMember(teamMember));
            Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null,
                    userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return true;
        } else
            return false;
    }

    @Override
    public void logout() {
        SecurityContextHolder.getContext().setAuthentication(null);
    }

    @Override
    public String getLoggedIn() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return  auth.getName();

    }
}
