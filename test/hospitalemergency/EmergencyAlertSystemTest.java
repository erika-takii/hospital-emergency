/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package hospitalemergency;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author erika
 * This class is for testing the EmergencyAlertSystem.
 * We want to check if hospitals and ambulances are working correctly,
 * and if patients can be moved and treated.
 */
public class EmergencyAlertSystemTest {
    
    private Patient p1;
    private Patient p2;
    private Patient p3;
    
    private Hospital h1;
    private Hospital h2;
    private Hospital h3;
    
    private Ambulance a1;
    private Ambulance a2;
    
    private EmergencyAlertSystem emergencyAlertSystem;
    private EmergencyAlertSystem emptyEmergencyAlertSystem;
    
    /**
     * This method runs before every test.
     * It sets up the objects we need to test.
     */
    @Before
    public void init() {
        p1 = new Patient("p1", 10);
        p2 = new Patient("p2", 5);
        p3 = new Patient("p3", 1);
        
        h1 = new Hospital("h1", 1);
        h2 = new Hospital("h2", 2);
        h3 = new Hospital("h3", 3);
       
        a1 = new Ambulance("a1");
        a2 = new Ambulance("a2");
        
        emergencyAlertSystem = new EmergencyAlertSystem();
        emptyEmergencyAlertSystem = new EmergencyAlertSystem();
        
        emergencyAlertSystem.addHospital(h1);
        emergencyAlertSystem.addHospital(h2);
        emergencyAlertSystem.addHospital(h3);
        
        for(Hospital h: emergencyAlertSystem.getHospitals()){
            h.registerDoctor(new Doctor("d1", 5));
            h.registerDoctor(new Doctor("d2", 5));
            h.registerDoctor(new Doctor("d3", 5));
        }
        
        emergencyAlertSystem.addAmbulance(a1);
        emergencyAlertSystem.addAmbulance(a2);
        
        emergencyAlertSystem.addPatient(p1);
        emergencyAlertSystem.addPatient(p2);
        emergencyAlertSystem.addPatient(p3);   
    }
    
    /**
     *  This test checks if the system can find hospitals that still have space.
     */
    @Test
    public void testFindAvailableHospital() {
        Hospital expected1 = h1;
        assertEquals(expected1, emergencyAlertSystem.findAvailableHospital());
        h1.registerPatient(p1);
        Hospital expected2 = h2;
        assertEquals(expected2, emergencyAlertSystem.findAvailableHospital());
        assertNull(emptyEmergencyAlertSystem.findAvailableHospital());
    }
    
    /**
     * This test checks if the system can find ambulances that are free.
     */
    @Test
    public void testFindAvailableAmbulance() {
        Ambulance expected1 = a1;
        assertEquals(expected1, emergencyAlertSystem.findAvailableAmbulance());
        a1.setStatus(false);
        Ambulance expected2 = a2;
        assertEquals(expected2, emergencyAlertSystem.findAvailableAmbulance());
        a2.setStatus(false);
        assertNull(emergencyAlertSystem.findAvailableAmbulance());
        assertNull(emptyEmergencyAlertSystem.findAvailableAmbulance());
    }
    
    /**
     * This test checks if all patients are transported with enough number of ambulances.
     */
    @Test
    public void testTransportAllPatients(){
        emergencyAlertSystem.transportAllPatients();
        for(Ambulance a : emergencyAlertSystem.getAmbulances()){
            assertFalse(a.isStatus());
        }
        assertTrue(p1.isTransported());
        assertTrue(p2.isTransported());
        assertFalse(p3.isTransported());
        
        emptyEmergencyAlertSystem.transportAllPatients();
        for(Ambulance a : emptyEmergencyAlertSystem.getAmbulances()){
            assertTrue(a.isStatus());
        }
    }
    
    /**
     * This test checks if all patients who are transported are treated
     * and if ambulances are released after starting treatment.
     */
    @Test
    public void testStartAllTreatments(){
        emergencyAlertSystem.transportAllPatients();
        assertFalse(a1.isStatus());
        assertFalse(a2.isStatus());
        emergencyAlertSystem.startAllTreatments();
        assertTrue(p1.isRecovered());
        assertTrue(p2.isRecovered());
        assertFalse(p3.isRecovered());
        assertTrue(a1.isStatus());
        assertTrue(a2.isStatus());
  
        
        emergencyAlertSystem.transportAllPatients();
        emergencyAlertSystem.startAllTreatments();
        assertTrue(p3.isRecovered());
    }
    
    /**
     * This test checks if patients recover after being treated.
     */
    @Test
    public void testAllPatientsIsRecovered() {
        assertFalse(emergencyAlertSystem.allPatientsIsRecovered());
        assertTrue(emptyEmergencyAlertSystem.allPatientsIsRecovered());

        emergencyAlertSystem.transportAllPatients();
        emergencyAlertSystem.startAllTreatments();
        assertFalse(emergencyAlertSystem.allPatientsIsRecovered());
        
        emergencyAlertSystem.transportAllPatients();
        emergencyAlertSystem.startAllTreatments();
        assertTrue(emergencyAlertSystem.allPatientsIsRecovered());
    }
}
