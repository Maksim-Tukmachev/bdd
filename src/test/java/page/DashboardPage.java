package page;

import com.codeborne.selenide.SelenideElement;
import data.DataHelper;


import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;


public class DashboardPage {
    private final String balanceStart  = "баланс: ";
    private final String balanceFinish = "p. "  ;
    private final SelenideElement heading = $("[data-test-id=dashboard]");
    private final SelenideElement cards = (SelenideElement) $$(".list__item div");

    public DashboardPage(){
        heading.shouldBe(visible);
    }

    public int getCardBalance(DataHelper.CardInfo cardInfo) {
        var text = cards.findBy(text(cardInfo.getCardNumber().substring(15))).getText;
        return extractBalance(text);
    }

    public int getCardBalance(int index){
        var text = cards.get(index).getText();
        return extractBalance(text);
    }

    public TransferPage selectCardToTransfer(DataHelper.CardInfo cardInfo){
        cards.findBy(attribute("data-test-id", cardInfo.getTestId())).$("button").click();
        return new TransferPage();
    }

    private int extractBalance(String text){
        var start = text.indexOf(balanceStart);
        var finish = text.indexOf(balanceFinish);
        var value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }

}