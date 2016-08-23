package com.capgemini.chess;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.capgemini.chess.service.ServiceTestSuite;

@RunWith(Suite.class)
@SuiteClasses({ServiceTestSuite.class})
public class AllTests {
	
}
