package edu.gatech.seclass.prj1;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AvgSentenceLengthTest {
    
    private AvgSentenceLength asl;
    private String fileDir;
    
    @Before
    public void setUp() throws Exception {
        asl = new AvgSentenceLength();
        fileDir = "test" + File.separator + "inputfiles" + File.separator;
    }
    @After
    public void tearDown() throws Exception {
        asl = null;
        fileDir = null;
    }
    
    @Test
    public void testComputeAverageSentenceLength1() {
        String comment = "Testing sentences that span multiple lines";
        asl.setFile(new File(fileDir + "input.txt"));
        assertEquals(comment, 7, asl.computeAverageSentenceLength(), 0);
    }
    @Test
    public void testComputeAverageSentenceLength2() {
        String comment = "Testing customized delimiters";
        asl.setFile(new File(fileDir + "input.txt"));
        asl.setSentenceDelimiters("%.");
        assertEquals(comment, 3, asl.computeAverageSentenceLength(), 0);
    }
    @Test
    public void testComputeAverageSentenceLength3() {
        String comment = "Testing customized minimal word length";
        asl.setFile(new File(fileDir + "input.txt"));
        asl.setMinWordLength(5);
        assertEquals(comment, 3, asl.computeAverageSentenceLength(), 0);
    }
    
    @Test
    public void testSetDocument1() {
        asl.setDocument("");
        assertEquals( "", asl.Document[0].getSentence());
    }
    
    @Test
    public void testSetDocument2() {
        asl.setDocument(".This is the second sentence!");
        assertEquals("This is the second sentence", asl.Document[1].getSentence());
    }
    
    @Test
    public void testSetDocument3() {
    	asl.setSentenceDelimiters(",");
        asl.setDocument(",this is the second sentence!");
        assertEquals("this is the second sentence", asl.Document[1].getSentence());
    }
    
    @Test
    public void testSetMinWordLength1() {
        String comment = "Testing set minus min word length";
        asl.setMinWordLength(-1);
        assertEquals(comment, 0, asl.minLength, 0);
    }
    
    @Test
    public void testSetMinWordLength2() {
        String comment = "Testing set 0 min word length";
        asl.setMinWordLength(0);
        assertEquals(comment, 0, asl.minLength, 0);
    }
    
    @Test
    public void testSetMinWordLength3(){
        String comment = "Testing set default min word length";
        asl.setMinWordLength(3);
        assertEquals(comment, 3, asl.minLength, 0);
    }
    
    @Test
    public void testSetSentenceDelimiters1() {
        fail("Not yet implemented");
    }
    
    @Test
    public void testSetSentenceDelimiters2() {
        fail("Not yet implemented");
    }
    
    @Test
    public void testSetSentenceDelimiters3() {
        fail("Not yet implemented");
    }
    
    @Test
    public void testSetFile1() {
        fail("Not yet implemented");
    }
    
    @Test
    public void testSetFile2()  {
        fail("Not yet implemented");
    }
    
    @Test
    public void testSetFile3() {
        fail("Not yet implemented");
    }
}
