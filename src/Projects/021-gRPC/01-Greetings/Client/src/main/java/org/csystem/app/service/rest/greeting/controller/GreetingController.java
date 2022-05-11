package org.csystem.app.service.rest.greeting.controller;

import org.csystem.app.service.rest.greeting.dto.GreetingResponseDTO;
import org.csystem.app.service.rest.greeting.service.GreetingAppService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/greeting/hello")
public class GreetingController {
    private GreetingAppService m_greetingAppService;

    public GreetingController(GreetingAppService greetingAppService)
    {
        m_greetingAppService = greetingAppService;
    }

    @GetMapping("tr")
    public GreetingResponseDTO getGreetingTR(@RequestParam("fn") String firstName,
                                             @RequestParam("ln")String lastName, @RequestParam(value = "m", defaultValue = "false")boolean married)
    {
        return m_greetingAppService.receiveMessageTR(firstName, lastName, married);
    }

    @GetMapping("en")
    public GreetingResponseDTO getGreetingEN(@RequestParam("fn") String firstName,
                                             @RequestParam("ln")String lastName, @RequestParam(value = "m", defaultValue = "false")boolean married)
    {
        return m_greetingAppService.receiveMessageEN(firstName, lastName, married);
    }
}
