package vega.it.praksa.services.jpa;

import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import vega.it.praksa.model.Employee;
import vega.it.praksa.repositories.EmployeeRepository;
import vega.it.praksa.repositories.WorkRepository;

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
        workRepository
                .getEmployeesForMailing(new Date())
                .forEach(
                        id -> {
                            try {
                                sendMail(employeeRepository.findById(id).get());

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
}
