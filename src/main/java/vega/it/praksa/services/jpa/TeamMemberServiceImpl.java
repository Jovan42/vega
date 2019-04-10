package vega.it.praksa.services.jpa;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import vega.it.praksa.exceptions.NotFoundException;
import vega.it.praksa.mappers.DtoMapper;
import vega.it.praksa.model.TeamMember;
import vega.it.praksa.model.UserDetailsImpl;
import vega.it.praksa.model.dtos.TeamMemberInputDto;
import vega.it.praksa.model.dtos.TeamMemberListDto;
import vega.it.praksa.model.dtos.TeamMemberOutputDto;
import vega.it.praksa.repositories.TeamMemberRepository;
import vega.it.praksa.services.TeamMemberService;

import java.util.List;
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
        return new TeamMemberListDto(teamMemberRepository.findAll().stream()
                .map(mapper::teamMemberToTeamMemberOutputDto)
                .collect(Collectors.toList()));    }

    @Override
    //TODO preko lambda izraza
    public TeamMemberOutputDto get(Long id) {
       return teamMemberRepository.findById(id)
                .map(mapper::teamMemberToTeamMemberOutputDto)
                .orElseThrow(()-> new NotFoundException("Team member with id '" + id +"' is not found"));
    }

    @Override
    public TeamMemberOutputDto add(TeamMemberInputDto teamMemberInputDto) {
        teamMemberInputDto.setId(null);
        teamMemberInputDto.setPassword(passwordEncoder.encode(teamMemberInputDto.getPassword()));
        return mapper.teamMemberToTeamMemberOutputDto(teamMemberRepository.save(
                mapper.teamMemberDtoToTeamMember(teamMemberInputDto))
        );

    }

    @Override
    public TeamMemberOutputDto update(TeamMemberInputDto teamMemberInputDto) {
        String oldPassword =  teamMemberRepository.findById(teamMemberInputDto.getId())
                .orElseThrow(()-> new NotFoundException("Team member with id '" + teamMemberInputDto.getId()
                        +"' is not found")).getPassword();

        teamMemberInputDto.setPassword(oldPassword);
        return mapper.teamMemberToTeamMemberOutputDto(teamMemberRepository.save(
                mapper.teamMemberDtoToTeamMember(teamMemberInputDto))
        );
    }

    @Override
    public void delete(Long id) {
        teamMemberRepository.deleteById(id);
    }

    @Override
    public Boolean changePassword(Long id, String newPassword) {
        TeamMember teamMember = teamMemberRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Team member with id '" + id +"' is not found"));
        teamMember.setPassword(passwordEncoder.encode(newPassword));

        teamMemberRepository.save(teamMember);
        return true;

    }

    @Override
    public TeamMemberOutputDto get(String username) {
        return teamMemberRepository.getByUsername(username)
                .map(mapper::teamMemberToTeamMemberOutputDto)
                .orElseThrow(()-> new NotFoundException("Team member with username '" + username +"' is not found"));
    }

    @Override
    public Boolean login(String username, String password) {
        TeamMember teamMember = teamMemberRepository.getByUsername(username)
                .orElseThrow(()-> new NotFoundException("Team member with usernanme '" + username +"' is not found"));


        if (passwordEncoder.matches(password, teamMember.getPassword())) {
            UserDetailsImpl userDetails = new UserDetailsImpl(teamMember);
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
