package vega.it.praksa.services.jpa;

//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import vega.it.praksa.exceptions.NotFoundException;
import vega.it.praksa.mappers.DtoMapper;
import vega.it.praksa.model.TeamMember;
import vega.it.praksa.model.dtos.TeamMemberDto;
import vega.it.praksa.model.dtos.TeamMemberListDto;
import vega.it.praksa.repositories.TeamMemberRepository;
import vega.it.praksa.services.TeamMemberService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
//TODO password encryption
@Service
public class TeamMemberServiceImpl implements TeamMemberService {
    private TeamMemberRepository teamMemberRepository;
    private DtoMapper mapper;
   // private PasswordEncoder passwordEncoder;


    public TeamMemberServiceImpl(TeamMemberRepository teamMemberRepository, DtoMapper mapper) {//, PasswordEncoder passwordEncoder) {
        this.teamMemberRepository = teamMemberRepository;
        this.mapper = mapper;
        //this.passwordEncoder = passwordEncoder;
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
    public TeamMemberDto get(Long id) {
        Optional<TeamMember> teamMember = teamMemberRepository.findById(id);
        if(teamMember.isPresent()) {
            TeamMemberDto teamMemberDto = mapper.teamMemberToTeamMemberDto(teamMember.get());
            teamMemberDto.setPassword("");
            return teamMemberDto;
        }
        else
            throw new NotFoundException("Country with id '" + id +"' is not found");
    }

    @Override
    public TeamMemberDto add(TeamMemberDto teamMemberDto) {
        teamMemberDto.setId(null);
        //teamMemberDto.setPassword(passwordEncoder.encode(teamMemberDto.getPassword()));
        TeamMember teamMember = teamMemberRepository.save(mapper.teamMemberDtoToTeamMember(teamMemberDto));
        return mapper.teamMemberToTeamMemberDto(teamMember);

    }

    @Override
    public TeamMemberDto update(TeamMemberDto teamMemberDto) {
        String oldPassword = get(teamMemberDto.getId()).getPassword();
        teamMemberDto.setPassword(oldPassword);
        TeamMember teamMember = teamMemberRepository.save(mapper.teamMemberDtoToTeamMember(teamMemberDto));
        return mapper.teamMemberToTeamMemberDto(teamMember);
    }

    @Override
    public void delete(Long id) {
        teamMemberRepository.deleteById(id);
    }
}
