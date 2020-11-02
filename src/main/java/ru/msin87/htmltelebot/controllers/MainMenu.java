package ru.msin87.htmltelebot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.model.ITemplateEnd;
import org.thymeleaf.spring5.SpringTemplateEngine;
import ru.msin87.htmltelebot.Browser;

import java.io.IOException;

@Controller
public class MainMenu {
    private Browser browser;
    private SpringTemplateEngine templateEngine;

    @Autowired
    public void setTemplateEngine(SpringTemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    @Autowired
    private void setBrowser(Browser browser){
        this.browser = browser;
    }
    @GetMapping("/mainmenu/{chatId}")
    @ResponseStatus(HttpStatus.OK)
    public String setData(@PathVariable(value = "chatId") String chatId, Model model){
        try {
            model.addAttribute("chatId", chatId);
            Context context = new Context();
            context.setVariable("chatId", chatId);
            String result = templateEngine.process("mainmenu",context);
            browser.setPage(result);

            return "mainmenu";
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build().toString();
        }

    }
}
