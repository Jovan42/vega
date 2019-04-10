package vega.it.praksa.services;

import vega.it.praksa.model.dtos.TeamMemberInputDto;
import vega.it.praksa.model.dtos.TeamMemberListDto;
import vega.it.praksa.model.dtos.TeamMemberOutputDto;

public interface TeamMemberService extends GenericService<TeamMemberInputDto, TeamMemberOutputDto, TeamMemberListDto, Long>{
    Boolean changePassword(Long id, String newPassword);
    TeamMemberOutputDto get(String username);
    Boolean login(String username, String password);
    void logout();
    String getLoggedIn();
}
