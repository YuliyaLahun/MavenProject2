package people;

import org.junit.Test;

import static org.junit.Assert.*;

public class ManualEngineerTest {

    Engineer manualEngineer = new ManualEngineer(25,3);

    @Test
    public void AgeTest() {
        assertEquals(25, manualEngineer.getAge());
    }

    @Test
    public void ExperienceTest() {
        assertEquals(3, manualEngineer.getExperience());
    }

    @Test
    public void SkillTest() {
        assertEquals(6, manualEngineer.getSkill());
    }
}