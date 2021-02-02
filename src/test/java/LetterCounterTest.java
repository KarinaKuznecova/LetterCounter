import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LetterCounterTest {

    @Spy
    private LetterCounter victim;

    @Mock
    private BufferedReader mockReader;


    @Test
    public void testCountLettersInLine_lettersOnly() {
        victim.countLettersInLine("abbcccc");
        assertEquals(3, victim.getLetterMap().size());

        int actual = victim.getLetterMap().get('a');
        assertEquals(1, actual);
        actual = victim.getLetterMap().get('b');
        assertEquals(2, actual);
        actual = victim.getLetterMap().get('c');
        assertEquals(4, actual);

        Integer nonExistingLetterCount = victim.getLetterMap().get('x');
        assertNull(nonExistingLetterCount);
    }

    @Test
    public void testCountLettersInLine_testOtherSymbols() {
        victim.countLettersInLine("a$ !4 ** @#$%^&*()_");
        assertEquals(1, victim.getLetterMap().size());
    }

    @Test
    public void testCountLettersInLine_upperCaseAndLowerCase() {
        victim.countLettersInLine("aAAA");
        assertEquals(2, victim.getLetterMap().size());

        int actual = victim.getLetterMap().get('a');
        assertEquals(1, actual);
        actual = victim.getLetterMap().get('A');
        assertEquals(3, actual);
    }

    @Test
    public void testLoadFile() {
        assertNotNull(victim.loadFile("somefile.txt"));
    }

    @Test(expected = NullPointerException.class)
    public void testLoadFile_pathIsNull() {
        victim.loadFile(null);
    }

    @Test
    public void testReadFile_invalidPath() {
        victim.readFile(new File(""));
        Mockito.verify(victim, Mockito.times(0)).countLettersInLine(anyString());
    }

    @Test
    public void testReadFile_validPath() throws IOException {
        doReturn(mockReader).when(victim).getReader(any());
        when(mockReader.readLine()).thenReturn("abc", null);
        victim.readFile(new File(""));
        Mockito.verify(victim, Mockito.atLeastOnce()).countLettersInLine(anyString());
    }
}