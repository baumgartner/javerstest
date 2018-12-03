package javers.test;

import at.Company;
import at.EntityComparator;
import at.Person;
import org.javers.core.Javers;
import org.javers.core.JaversBuilder;
import org.javers.core.diff.Diff;
import org.javers.core.diff.ListCompareAlgorithm;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class ListCompareTest {
    @Test
    public void testAsSet() {
        Javers javers = JaversBuilder.javers()
                .registerCustomComparator(new EntityComparator(), Person.class)
                .withListCompareAlgorithm(ListCompareAlgorithm.AS_SET)
                .build();

        equalEntityPartnerTest(javers);
    }

    @Test
    public void testSimple() {
        Javers javers = JaversBuilder.javers()
                .registerCustomComparator(new EntityComparator(), Person.class)
                .withListCompareAlgorithm(ListCompareAlgorithm.SIMPLE)
                .build();

        equalEntityPartnerTest(javers);
    }

    @Test
    public void testLevenstein() {
        Javers javers = JaversBuilder.javers()
                .registerCustomComparator(new EntityComparator(), Person.class)
                .withListCompareAlgorithm(ListCompareAlgorithm.LEVENSHTEIN_DISTANCE)
                .build();

        equalEntityPartnerTest(javers);
    }
    
    
    private void equalEntityPartnerTest(Javers javers) {
        Person e1 = new Person("james", "ignore this");
        Company le1 = new Company("1", e1, null, Arrays.asList(e1));

        Person e2 = new Person("james", "");
        Company le2 = new Company("1", e2, null, Arrays.asList(e2));
        Diff diff = javers.compare(le1, le2);

        System.out.println(diff);
        assertEquals(0, diff.getChanges().size());
    }
    
}
