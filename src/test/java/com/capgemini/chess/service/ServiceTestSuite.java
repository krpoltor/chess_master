package com.capgemini.chess.service;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.capgemini.rest.ChallengeRestServiceTest;

@RunWith(Suite.class)
// dodac klasy do testow
@SuiteClasses({ UserServiceTest.class , UserChallengeServiceTest.class, ChallengeRestServiceTest.class})
public class ServiceTestSuite {

}
