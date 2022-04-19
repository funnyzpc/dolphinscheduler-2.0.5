package com.dolphinscheduler.test;

import org.apache.dolphinscheduler.plugin.task.datax.DataxTask;

public class GetPythonHomeTest {

    public static void main(String[] args) {

        DataxTask dataxTask = new DataxTask(null);
        String pythonCommand = dataxTask.getPythonCommand("/usr/bin/python");
//        String pythonCommand = dataxTask.getPythonCommand("/usr/bin");
        System.out.println(pythonCommand);
    }
}
