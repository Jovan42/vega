package vega.it.praksa.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import vega.it.praksa.model.enums.Role;

import java.util.ArrayList;
import java.util.Collection;

@Getter
@Setter
public class UserDetailsImpl implements UserDetails {
    private static final long serialVersionUID = 5197941260523577515L;

    private TeamMember teamMember;


    public UserDetailsImpl(TeamMember teamMember) {
        this.teamMember = teamMember;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("WORKER" ));
        if(teamMember.getRole() != null && teamMember.getRole().equals(Role.ADMIN))
            authorities.add(new SimpleGrantedAuthority("ADMIN"));

        return authorities;

    }

    public boolean isAdmin() {
        return teamMember.getRole().equals(Role.ADMIN);
    }

    @Override
    public String getPassword() {
        return teamMember.getPassword();
    }

    @Override
    public String getUsername() {
        return teamMember.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}