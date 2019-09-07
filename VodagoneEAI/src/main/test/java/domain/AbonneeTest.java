package domain;

import org.force66.beantester.BeanTester;
import org.junit.Test;

public class AbonneeTest {

        @Test
        public void getterAndSetterCorrectness() throws Exception {
            new BeanTester().testBean(Abonnee.class);
        }
}
