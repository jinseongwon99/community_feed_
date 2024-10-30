package com.jinsungwon99.auth.ui;

import com.jinsungwon99.auth.application.EmailService;
import com.jinsungwon99.auth.application.dto.SendEmailRequestDto;
import com.jinsungwon99.common.ui.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/signup")
@RequiredArgsConstructor
public class SignUpController {

    private final EmailService emailService;

    @PostMapping("/send-verification-email")
    public Response<Void> sendEmail(@RequestBody SendEmailRequestDto dto){
        emailService.sendEmail(dto);
        return Response.ok(null);
    }
}
