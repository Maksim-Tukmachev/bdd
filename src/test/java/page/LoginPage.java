package page;

import com.codeborne.selenide.SelenideElement;
import data.DataHelper;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    private final SelenideElement loginField = $("");
    private final SelenideElement passwordField = $("");
    private final SelenideElement loginButton = $("");

    public DataHelper.VerificationCode validLogin(DataHelper.AuthInfo info){
        loginField.setValue(info.getLogin());
        passwordField.setValue(info.getPassword());
        loginButton.click();
        return new VerificationPage();
    }
}
