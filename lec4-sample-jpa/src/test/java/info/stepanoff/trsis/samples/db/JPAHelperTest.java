package info.stepanoff.trsis.samples.db;

import info.stepanoff.trsis.samples.db.model.School;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Pavel
 */
@Slf4j
public class JPAHelperTest {

    public JPAHelperTest() {
    }

    @Test
    public void testGetAllSchools() throws Exception {
        System.out.println("getAllSchools");
        List<School> result = JPAHelper.getAllSchools();
        Set<String> names = result.stream().map(x -> x.getName()).collect(Collectors.toSet());
        Set<Integer> numbers = result.stream().map(x -> x.getNumber()).collect(Collectors.toSet());
        Set<String> expectedNames = new HashSet<>();
        expectedNames.add("Факультет 1");
        expectedNames.add("Факультет 2");
        expectedNames.add("Факультет 3");
        expectedNames.add("Факультет 4");
        expectedNames.add("Факультет 5");
        assertEquals("Ошибка при проверке списка возвращенных названий", expectedNames, names);
        Set<Integer> expectedNumbers = new HashSet<>();
        expectedNumbers.add(1);
        expectedNumbers.add(2);
        expectedNumbers.add(3);
        expectedNumbers.add(4);
        expectedNumbers.add(5);
        assertEquals("Ошибка при проверке списка возвращенных номеров", expectedNumbers, numbers);
    }

    @Test
    public void testAddDeleteSchool() throws Exception {
        log.info("addSchool and deleteSchool");

        Integer id = JPAHelper.addSchool(100, "test");
        assertEquals(Integer.valueOf(100), JPAHelper.getSchool(id).getNumber());
        assertEquals("test", JPAHelper.getSchool(id).getName());
        JPAHelper.deleteSchool(id);
        assertEquals(null, JPAHelper.getSchool(id));
    }

}
