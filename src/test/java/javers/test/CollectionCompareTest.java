package javers.test;

import at.Company;
import at.EntityComparator;
import at.Person;
import org.javers.core.Javers;
import org.javers.core.JaversBuilder;
import org.javers.core.diff.Diff;
import org.javers.core.diff.ListCompareAlgorithm;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class CollectionCompareTest {

    @Test
    public void testAsSet() {
        Javers javers = JaversBuilder.javers()
                .registerCustomComparator(new EntityComparator(), Person.class)
                .withListCompareAlgorithm(ListCompareAlgorithm.AS_SET)
                .build();

        equalEntityClientTest(javers);
    }

    @Test
    public void testSimple() {
        Javers javers = JaversBuilder.javers()
                .registerCustomComparator(new EntityComparator(), Person.class)
                .withListCompareAlgorithm(ListCompareAlgorithm.SIMPLE)
                .build();

        equalEntityClientTest(javers);
    }

    @Test
    public void testLevenstein() {
        Javers javers = JaversBuilder.javers()
                .registerCustomComparator(new EntityComparator(), Person.class)
                .withListCompareAlgorithm(ListCompareAlgorithm.LEVENSHTEIN_DISTANCE)
                .build();

        equalEntityClientTest(javers);
    }

    private void equalEntityClientTest(Javers javers) {
        Person e1 = new Person("james", "ignore this");
        List<Person> clients1 = new ArrayList<Person>();
        clients1.add(e1);
        Company le1 = new Company("1", null, clients1, null);

        Person e2 = new Person("james", "");
        List<Person> clients2 = new ArrayList<Person>();
        clients2.add(e2);
        Company le2 = new Company("1", null, clients2, null);
        Diff diff = javers.compare(le1, le2);

        System.out.println(diff);
        assertEquals(0, diff.getChanges().size());
    }

}
