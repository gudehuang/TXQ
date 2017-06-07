package com.hzg.txq.test;

import com.hzg.txq.ProjectDetailBean;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by hzg on 2017/6/7.
 */
public class ProjectDetailsTestTest {
    private  ProjectDetailsTest projectDetailsTest;
        ProjectDetailBean.Project project;
    @Before
    public void setUp() throws Exception {
        projectDetailsTest=new ProjectDetailsTest();
        project=new ProjectDetailBean.Project();
        project.setFollowNum(1);
    }



    @Test
    public void jsonToProject() throws Exception {
        assertEquals(project.followNum,projectDetailsTest.jsonToProject().followNum);
    }

}