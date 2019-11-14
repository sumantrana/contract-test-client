package com.sumant.boot.learning.contracttestclient;

import org.assertj.core.api.BDDAssertions;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.junit.StubRunnerRule;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@RunWith(SpringRunner.class)
@AutoConfigureStubRunner( ids = { "com.sumant.boot.learning:contract-test:+:stubs:8100"}, stubsMode = StubRunnerProperties.StubsMode.LOCAL)
class ContractTestClientApplicationTests {

//    @Rule
//    public StubRunnerRule stubRunnerRule = new StubRunnerRule()
//            .downloadStub("com.sumant.boot.learning", "contract-test", "0.0.1-SNAPSHOT", "stubs")
//            .withPort(8100)
//            .stubsMode(StubRunnerProperties.StubsMode.LOCAL);

    @Test
    void get_book_from_service_contract() {

        //given:
        RestTemplate restTemplate = new RestTemplate();

        //when:
        ResponseEntity<Book> bookResponseEntity = restTemplate.getForEntity("http://localhost:8100/book", Book.class);

        //then:
        BDDAssertions.then(bookResponseEntity.getStatusCodeValue()).isEqualTo(200);
        BDDAssertions.then(bookResponseEntity.getBody().getName()).isEqualTo("TestBook");
        BDDAssertions.then(bookResponseEntity.getBody().getValue()).isEqualTo(10);



    }

}
