package people;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class BaseEngineerTest {

    private Engineer en;

    int skill;

    public BaseEngineerTest(Engineer en, int skill) {
        this.en = en;
        this.skill = skill;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> primeNumbers(){
        return Arrays.asList(new Object[][] {
            {new ManualEngineer(25,3), 6},
            {new AutomatedEngineer(30,10), 30}
        });
    }

    @Test
    public void SkillTest() {
        assertEquals(skill, en.getSkill());
    }
}
