package vega.it.praksa.services.jpa;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import vega.it.praksa.exceptions.NotFoundException;
import vega.it.praksa.model.Employee;
import vega.it.praksa.model.UserDetailsImpl;
import vega.it.praksa.repositories.EmployeeRepository;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {
    private EmployeeRepository employeeRepository;

    public UserDetailsServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employee employee =
                employeeRepository
                        .getByUsername(username)
                        .orElseThrow(
                                () ->
                                        new NotFoundException(
                                                "Team member with usernanme '"
                                                        + username
                                                        + "' is not found"));

        return new UserDetailsImpl(employee);
    }
}
