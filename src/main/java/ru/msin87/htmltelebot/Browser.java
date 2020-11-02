package ru.msin87.htmltelebot;


import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class Browser {
    private WebClient webClient;
    private HtmlPage page;

    public Browser() {
        this.webClient = new WebClient(BrowserVersion.FIREFOX_68);
    }

    public void setPage(String url) throws IOException {
        this.page = webClient.getPage(url);
        //setChatId(chatId);
    }

    public void clickOnId(String id) throws IOException {
        if (this.page == null)
            return;
        this.page.getElementById(id).click();
    }
    public void setChatId(String chatId){
        List<HtmlDivision> chat = this.page.getByXPath("//div[@class='chat']");
        if (!chat.isEmpty())
            chat.get(0).setId(chatId);
    }
    public void setSentMessageId(String elementId, String sentId) {
        this.page.getElementById(elementId).setAttribute("data-sentId", sentId);
    }
}
