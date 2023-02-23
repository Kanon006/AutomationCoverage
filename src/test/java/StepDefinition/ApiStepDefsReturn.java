package StepDefinition;

import Core.ApiCall;
import Core.HeaderFormatHelper;
import com.google.gson.Gson;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import repository.remoteRepo.responseRepo.GetApiResponseModel;

import static Core.CoreConstrainHelper.base_url;

public class ApiStepDefsReturn {
    String url;
    private Response responseGetApi;

    private Gson gson = new Gson();
    @Given("user has the base api")
    public void userHasTheBaseApi() {
        url =base_url + "user";

    }

    @When("user call the{string} and {string}")
    public void userCallThePramsNameAndPramsValue(String pramName,String pramValue) {
        url= url+pramName +"=" +pramValue;

        System.out.println(url);
        responseGetApi= ApiCall.getCall(HeaderFormatHelper.commonHeaders(),url);
        System.out.println(responseGetApi.body().asString());

    }

    @Then("it will return valid data")
    public void itWillReturnValidData() {
        System.out.println(url);
        GetApiResponseModel getApiResponseModel = gson.fromJson(responseGetApi.getBody().asString(),GetApiResponseModel.class);
        System.out.println(getApiResponseModel.getData().get(0).getFirst_name());
    }

}
