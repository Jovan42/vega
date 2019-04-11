package vega.it.praksa.services.jpa;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import vega.it.praksa.model.Employee;
import vega.it.praksa.model.Work;
import vega.it.praksa.repositories.EmployeeRepository;
import vega.it.praksa.repositories.WorkRepository;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Service
public class MailingServiceImpl {


    private WorkRepository workRepository;
    private EmployeeRepository employeeRepository;
    private JavaMailSender emailSender;

    public MailingServiceImpl(WorkRepository workRepository, EmployeeRepository employeeRepository, JavaMailSender emailSender) {
        this.workRepository = workRepository;
        this.employeeRepository = employeeRepository;
        this.emailSender = emailSender;
    }

    @Scheduled(cron = "0 59 23 ? * SUN")
    //@Scheduled(cron = "0 0/1 * 1/1 * ?")
    public  void checkHours(){
        employeeRepository.findAll().forEach(teamMember -> {
            double timeWorked = 0D;
            LocalDate start = LocalDate.now();
            start = start.minusDays(7);
            Date date = Date.from(start.atStartOfDay(ZoneId.systemDefault()).toInstant());

            for (Work work:  workRepository.findAllByProject_LeadAndDateBetween(teamMember, date, new Date())) {
                timeWorked += (work.getTime() != null) ? work.getTime() : 0;
                timeWorked += (work.getOvertime() != null) ? work.getOvertime() : 0;
            }
            if(timeWorked <40) sendMail(timeWorked, teamMember);
        });
    }

    private void sendMail(Double timeWorked, Employee employee) {
        System.out.println("Send mail to " + employee.getUsername());
        SimpleMailMessage message = new SimpleMailMessage();
        //message.setTo("jovan0042@gmail.com");
        message.setTo(employee.getEmail());
        message.setSubject("Less then 40hrs of work");
        message.setText("You worked " + timeWorked + " last week");
        emailSender.send(message);

    }
}