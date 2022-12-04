package people;

import org.junit.Test;

import static org.junit.Assert.*;

public class AutomatedEngineerTest {

    Engineer automatedEngineer = new AutomatedEngineer(30, 10);

    @Test
    public void ageTest() {
        assertEquals(30, automatedEngineer.getAge());
    }

    @Test
    public void experienceTest(){
        assertEquals(10, automatedEngineer.getExperience());
    }

    @Test
    public void skillTest(){
        assertEquals(30, automatedEngineer.getSkill());
    }

}