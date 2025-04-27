/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package hospitalemergency;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author erika
 * Unit tests for the Hospital class.
 * Tests include registering patients and checking hospital capacity.
 */
public class HospitalTest {
    private Hospital h1;
    private Patient p1;
    private Patient p2;
    private Doctor d1;
    private Doctor d2;
   
    /**
     * Set up test fixtures before each test.
     */
    @Before
    public void init() {
        h1 = new Hospital("h1", 1); 
        p1 = new Patient("p1", 1);
        p2 = new Patient("p2", 2);
        d1 = new Doctor("d1", 1);
        d2 = new Doctor("d2", 2);
    }
    
    /**
     * Tests registering patients to the hospital and checks capacity limits.
     */
    @Test
    public void testRegisterPatient() {
        assertTrue(h1.registerPatient(p1));
        h1.registerPatient(p1);
        assertFalse(h1.registerPatient(p2));
    }
    
    /**
     * Tests whether the hospital correctly reports space availability.
     */
    @Test
    public void testHasSpace() {
        assertTrue(h1.hasSpace());
        h1.registerPatient(p1);
        assertFalse(h1.hasSpace());
    }
    
    /**
     * Tests whether the hospital correctly treats patients who are transported.
     */ 
    @Test
    public void testTrearPatients(){
        h1.registerPatient(p1);
        h1.registerPatient(p2);
        h1.registerDoctor(d1);
        h1.registerDoctor(d2);
        h1.treatPatients();
        assertTrue(p1.isRecovered());
        assertFalse(p2.isRecovered());
    }
}
