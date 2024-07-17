/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;

import bean.ItemDashboard;
import bean.Registration;
import bean.Subject;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class RegistrationDAOTest {

    public RegistrationDAOTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }


    /**
     * Test of getRegistrationById method, of class RegistrationDAOImpl.
     */
    @Test
    public void testGetRegistrationById() throws Exception {
        System.out.println("getRegistrationById");
        int registrationId = 1;
        RegistrationDAOImpl instance = new RegistrationDAOImpl();
        Registration result = instance.getRegistrationById(registrationId);
        assertEquals(2, result.getUserId());
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addRegistration method, of class RegistrationDAOImpl.
     */
    @Test
    public void testAddRegistration() throws Exception {
        System.out.println("addRegistration");
        Registration newRegistration = null;
        RegistrationDAOImpl instance = new RegistrationDAOImpl();
        int expResult = 0;
        int result = instance.addRegistration(newRegistration);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of editRegistration method, of class RegistrationDAOImpl.
     */
    @Test
    public void testEditRegistration() throws Exception {
        System.out.println("editRegistration");
        int registrationId = 0;
        Registration editedRegistration = null;
        RegistrationDAOImpl instance = new RegistrationDAOImpl();
        int expResult = 0;
        int result = instance.editRegistration(registrationId, editedRegistration);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }


    /**
     * Test of getRegistedSubject method, of class RegistrationDAOImpl.
     */
    @Test
    public void testGetRegistedSubject() throws Exception {
        System.out.println("getRegistedSubject");
        int userId = 1;
        RegistrationDAOImpl instance = new RegistrationDAOImpl();
        int expResult = 0;
        ArrayList<Subject> result = instance.getRegistedSubject(userId);
        assertTrue(expResult <= result.size());
    }

    /**
     * Test of getRegistedSubjectbyUserId method, of class RegistrationDAOImpl.
     */
    @Test
    public void testGetRegistedSubjectbyUserId() throws Exception {
        System.out.println("getRegistedSubjectbyUserId");
        int userId = 8;
        RegistrationDAOImpl instance = new RegistrationDAOImpl();
        ArrayList<Subject> expResult = null;
        ArrayList<Subject> result = instance.getRegistedSubjectbyUserId(userId);
        assertEquals(11, result.size());
    }





}
