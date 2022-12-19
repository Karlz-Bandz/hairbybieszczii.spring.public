package pl.hairbybieszczii.hair_bieszczii;


import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.hairbybieszczii.hair_bieszczii.model.MailModel;
import pl.hairbybieszczii.hair_bieszczii.service.mail.MailSenderService;

@RestController
@RequestMapping("/mail")
@AllArgsConstructor
public class MailController {

    private MailSenderService mailService;

    @PostMapping("/test")
    public ResponseEntity<MailModel> sendMail(@RequestBody MailModel mail){
        this.mailService.sendMail(mail);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
