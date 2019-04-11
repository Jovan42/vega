package vega.it.praksa.services.jpa;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import vega.it.praksa.exceptions.NotFoundException;
import vega.it.praksa.mappers.DtoMapper;
import vega.it.praksa.model.Employee;
import vega.it.praksa.model.UserDetailsImpl;
import vega.it.praksa.model.dtos.EmployeeInputDto;
import vega.it.praksa.model.dtos.EmployeeOutputDto;
import vega.it.praksa.model.dtos.EmployeeListDto;
import vega.it.praksa.repositories.EmployeeRepository;
import vega.it.praksa.services.EmployeeService;

import java.util.stream.Collectors;
@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepository employeeRepository;
    private DtoMapper mapper;
   private PasswordEncoder passwordEncoder;


    public EmployeeServiceImpl(EmployeeRepository employeeRepository, DtoMapper mapper, PasswordEncoder passwordEncoder) {
        this.employeeRepository = employeeRepository;
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public EmployeeListDto get() {
        return new EmployeeListDto(employeeRepository.findAll().stream()
                .map(mapper::employeeToEmployeeOutputDto)
                .collect(Collectors.toList()));
    }


    @Override
    //TODO preko lambda izraza
    public EmployeeOutputDto get(Long id) {
       return employeeRepository.findById(id)
                .map(mapper::employeeToEmployeeOutputDto)
                .orElseThrow(()-> new NotFoundException("Team member with id '" + id +"' is not found"));
    }

    @Override
    public EmployeeOutputDto add(EmployeeInputDto employeeInputDto) {
        employeeInputDto.setId(null);
        employeeInputDto.setPassword(passwordEncoder.encode(employeeInputDto.getPassword()));
        return mapper.employeeToEmployeeOutputDto(employeeRepository.save(
                mapper.employeeDtoToEmployee(employeeInputDto))
        );

    }

    @Override
    public EmployeeOutputDto update(EmployeeInputDto employeeInputDto) {
        String oldPassword =  employeeRepository.findById(employeeInputDto.getId())
                .orElseThrow(()-> new NotFoundException("Team member with id '" + employeeInputDto.getId()
                        +"' is not found")).getPassword();

        employeeInputDto.setPassword(oldPassword);
        return mapper.employeeToEmployeeOutputDto(employeeRepository.save(
                mapper.employeeDtoToEmployee(employeeInputDto))
        );
    }

    @Override
    public void delete(Long id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public Boolean changePassword(Long id, String newPassword) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Team member with id '" + id +"' is not found"));
        employee.setPassword(passwordEncoder.encode(newPassword));

        employeeRepository.save(employee);
        return true;

    }

    @Override
    public EmployeeOutputDto get(String username) {
        return employeeRepository.getByUsername(username)
                .map(mapper::employeeToEmployeeOutputDto)
                .orElseThrow(()-> new NotFoundException("Team member with username '" + username +"' is not found"));
    }

    @Override
    public Boolean login(String username, String password) {
        Employee employee = employeeRepository.getByUsername(username)
                .orElseThrow(()-> new NotFoundException("Team member with usernanme '" + username +"' is not found"));


        if (passwordEncoder.matches(password, employee.getPassword())) {
            UserDetailsImpl userDetails = new UserDetailsImpl(employee);
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

    @Override
    public EmployeeListDto getAllWithoutProject() {
        return new EmployeeListDto(employeeRepository.findAllByProjectMember_IdIsNull().stream()
                .map(mapper::employeeToEmployeeOutputDto)
                .collect(Collectors.toList()));
    }
}
