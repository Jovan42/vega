package vega.it.praksa.services.jpa;

import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import vega.it.praksa.exceptions.NotFoundException;
import vega.it.praksa.model.Employee;
import vega.it.praksa.model.Vacation;
import vega.it.praksa.repositories.EmployeeRepository;
import vega.it.praksa.repositories.WorkRepository;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Slf4j
@Service
public class MailingServiceImpl {

    private WorkRepository workRepository;
    private EmployeeRepository employeeRepository;
    private JavaMailSender emailSender;

    public MailingServiceImpl(
            WorkRepository workRepository,
            EmployeeRepository employeeRepository,
            JavaMailSender emailSender) {
        this.workRepository = workRepository;
        this.employeeRepository = employeeRepository;
        this.emailSender = emailSender;
    }

    @Scheduled(cron = "0 59 23 ? * SUN")
    // @Scheduled(cron = "0 0/1 * 1/1 * ?")
    public void checkHours() {
        LocalDate start = LocalDate.now();
        start = start.minusDays(7);
        Date date = Date.from(start.atStartOfDay(ZoneId.systemDefault()).toInstant());

        workRepository
                .getEmployeesForMailing(date, new Date())
                .forEach(
                        id -> {
                            try {
                                sendMail(
                                        employeeRepository
                                                .findById(id)
                                                .orElseThrow(
                                                        () ->
                                                                new NotFoundException(
                                                                        "Employee with id '"
                                                                                + id
                                                                                + "' is not found")));
                            } catch (Exception e) {
                                log.error(e.getMessage());
                            }
                        });
    }

    private void sendMail(Employee employee) {
        System.out.println("Send mail to " + employee.getUsername());
        SimpleMailMessage message = new SimpleMailMessage();
        // message.setTo("jovan0042@gmail.com");
        message.setTo(employee.getEmail());
        message.setSubject("Less then 40hrs of work");
        message.setText("Less then 40hrs of work");
        emailSender.send(message);
    }

    void sendMailForVacation(Vacation vacation) {
        vacation.getEmployee()
                .getTeams()
                .forEach(
                        team -> {
                            System.out.println(
                                    "Send mail to " + team.getTeamLeader().getUsername());
                            SimpleMailMessage message = new SimpleMailMessage();
                            message.setTo("jovan0042@gmail.com");
                            // message.setTo( team.getTeamLeader().getEmail());
                            message.setSubject("Vacation mail");
                            message.setText(
                                    "Employee "
                                            + vacation.getEmployee().getName()
                                            + " wants to go to vacation from\n"
                                            + vacation.getStartDate()
                                            + " to \n"
                                            + vacation.getEndDate()
                                            + "\nTo approve or deny go to link: "
                                            + "http://localhost:8080/vacation?vacation="
                                            + vacation.getId());
                            emailSender.send(message);
                        });
    }
}
