package domain;

import org.force66.beantester.BeanTester;
import org.junit.Test;

public class AddAbonnementDTODTOTest {
    @Test
    public void getterAndSetterCorrectness() throws Exception {
        new BeanTester().testBean(Abonnementen.class);
    }
}
