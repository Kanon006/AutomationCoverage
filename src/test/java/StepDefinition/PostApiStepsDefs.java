package StepDefinition;

import Core.ApiCall;
import Core.FileHandleHelper;
import Core.FilePathHelper;
import Core.HeaderFormatHelper;
import com.google.gson.Gson;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import repository.remoteRepo.requestRepo.UserPostRequestModel;
import repository.remoteRepo.responseRepo.UserPostApiResponSeModel;

import static Core.CoreConstrainHelper.base_url;
import static Core.FilePathHelper.postApiPath;
import static org.aspectj.weaver.tools.cache.SimpleCacheFactory.path;

public class PostApiStepsDefs {

    private String requestModel;
    String url;
    UserPostRequestModel userPostRequestModel;

   // UserPostApiResponSeModel userPostApiResponseModel;

    Response postApiResponse;

    Gson gson = new Gson();
    @Given("user has the api {string}")
    public void userHasTheApiPath(String path) {
        url = base_url + path;

    }

    @When("user hit {string} and {string}")
    public void userHitNameAndJob(String name,String job) {

        JSONObject requestBody = new FileHandleHelper().readJsonFile(postApiPath);
        userPostRequestModel = new Gson().fromJson(requestBody.toJSONString(), UserPostRequestModel.class);
        userPostRequestModel.setName(name);
        userPostRequestModel.setJob(job);
        requestModel= gson.toJson(userPostRequestModel);

    }

    @And("call the api with body")
    public void callTheApiWithBody() {
        postApiResponse = ApiCall.postCall(HeaderFormatHelper.commonHeaders(),requestModel,url);
        System.out.println(postApiResponse.body().asString());
    }

    @Then("it will return created date data")
    public void itWillReturnCreatedDateData() {
        UserPostApiResponSeModel postApiResponSeModel = gson.fromJson(postApiResponse.getBody().asString(),UserPostApiResponSeModel.class);
        System.out.println(postApiResponSeModel.getCreatedAt());
    }
}
