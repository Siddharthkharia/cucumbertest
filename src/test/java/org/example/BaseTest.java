package org.example;

import driver.ExecutionNode;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

import java.util.HashMap;
import java.util.Map;

public class BaseTest {
    public Map<Integer,String> scenarios = new HashMap<Integer, String>();

    public void addScenario(String scenario){
        int threadId = Thread.currentThread().hashCode();
        scenarios.put(threadId,scenario);
    }

}
