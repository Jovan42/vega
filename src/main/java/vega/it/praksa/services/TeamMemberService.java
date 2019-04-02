package vega.it.praksa.services;

import vega.it.praksa.model.dtos.TeamMemberDto;
import vega.it.praksa.model.dtos.TeamMemberListDto;

public interface TeamMemberService extends GenericService<TeamMemberDto, TeamMemberListDto, Long>{
    TeamMemberDto changePassword(Long id, String newPassword);
}
