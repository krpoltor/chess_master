package com.capgemini.chess.service;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.capgemini.rest.ChallengeRestServiceTest;

@RunWith(Suite.class)
@SuiteClasses({ UserServiceTest.class, ChallengeServiceTest.class, ChallengeRestServiceTest.class })
public class ServiceTestSuite {

}
