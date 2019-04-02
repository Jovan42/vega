package vega.it.praksa.services.jpa;

import org.springframework.stereotype.Service;
import vega.it.praksa.exceptions.NotFoundException;
import vega.it.praksa.mappers.DtoMapper;
import vega.it.praksa.model.TeamMember;
import vega.it.praksa.model.dtos.TeamMemberDto;
import vega.it.praksa.model.dtos.TeamMemberListDto;
import vega.it.praksa.repositories.TeamMemberRepositry;
import vega.it.praksa.services.TeamMemberService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TeamMemberServiceImpl implements TeamMemberService {
    private TeamMemberRepositry teamMemberRepositry;
    private DtoMapper mapper;

    public TeamMemberServiceImpl(TeamMemberRepositry teamMemberRepositry, DtoMapper mapper) {
        this.teamMemberRepositry = teamMemberRepositry;
        this.mapper = mapper;
    }

    @Override
    public TeamMemberListDto get() {
        List<TeamMemberDto> countries =  teamMemberRepositry.findAll()
                .stream()
                .map(mapper::teamMemberToTeamMemberDto)
                .collect(Collectors.toList());
        return new TeamMemberListDto(countries);

    }

    @Override
    public TeamMemberDto get(Long id) {
        Optional<TeamMember> teamMember = teamMemberRepositry.findById(id);
        if(teamMember.isPresent())
            return mapper.teamMemberToTeamMemberDto(teamMember.get());
        else
            throw new NotFoundException("Country with id '" + id +"' is not found");
    }

    @Override
    public TeamMemberDto add(TeamMemberDto teamMemberDto) {
        teamMemberDto.setId(null);
        TeamMember teamMember = teamMemberRepositry.save(mapper.teamMemberDtoToTeamMember(teamMemberDto));
        return mapper.teamMemberToTeamMemberDto(teamMember);

    }

    @Override
    public TeamMemberDto update(TeamMemberDto teamMemberDto) {
        TeamMember teamMember = teamMemberRepositry.save(mapper.teamMemberDtoToTeamMember(teamMemberDto));
        return mapper.teamMemberToTeamMemberDto(teamMember);
    }

    @Override
    public void delete(Long id) {
        teamMemberRepositry.deleteById(id);
    }
}
