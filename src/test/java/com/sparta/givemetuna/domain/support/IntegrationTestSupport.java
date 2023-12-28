package com.sparta.givemetuna.domain.support;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest
@Rollback
public abstract class IntegrationTestSupport {

}
