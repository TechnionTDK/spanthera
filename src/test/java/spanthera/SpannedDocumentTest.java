package spanthera;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by omishali on 30/04/2017.
 */
public class SpannedDocumentTest {
    private SpannedDocument doc;
    private String text = "ויאמר משה אל רבי יהודה הנשיא";
    @Before
    public void before() {
        doc = new SpannedDocument(text, 1, 6);
    }

    @Test
    public void testLength() {
        assertEquals(6, doc.length());
    }

    @Test
    public void testGetWord() {
        assertEquals("יהודה", doc.getWord(4).text());
    }

    @Test
    public void testSpan() {
        Span span = doc.getSpan(3, 5);
        assertEquals(3, span.size());
        assertEquals("רבי יהודה הנשיא", span.text());
        assertEquals("רבי", span.getWord(0).text());
        assertEquals("יהודה", span.getWord(1).text());
        assertEquals("הנשיא", span.getWord(2).text());
    }

    /**
     * Phase 1: match between getSpans in the document to URIs.
     * Each span may point to a set of URIs.
     */

    @Test
    public void testSpans() {
        // iterate all getSpans of 1 word length
        List<Span> spans = doc.getSpans(1);
        assertEquals(6, spans.size());
        assertEquals("ויאמר", spans.get(0).text());

        // iterate all getSpans of 2 word length
        spans = doc.getSpans(2);
        assertEquals(5, spans.size());
        assertEquals("ויאמר משה", spans.get(0).text());
        assertEquals("אל רבי", spans.get(2).text());

        // iterate all getSpans of 3 word length
        spans = doc.getSpans(3);
        assertEquals(4, spans.size());
        assertEquals("ויאמר משה אל", spans.get(0).text());

        // iterate all getSpans of 5 word length
        spans = doc.getSpans(5);
        assertEquals(2, spans.size());
        assertEquals("ויאמר משה אל רבי יהודה", spans.get(0).text());

        // iterate all getSpans of 6 word length
        spans = doc.getSpans(6);
        assertEquals(1, spans.size());
        assertEquals("ויאמר משה אל רבי יהודה הנשיא", spans.get(0).text());
    }

    @Test
    public void testSpansOfSize4() {
        // we create a document with spans of one size: 4
        SpannedDocument doc = new SpannedDocument(text, 4, 4);
        assertEquals(3, doc.getAllSpans().size());
        assertEquals("אל רבי יהודה הנשיא", doc.getSpan(2,5).text());
    }

    @Test
    public void testGetAllSpans() {
        SpannedDocument sd = new SpannedDocument(text, 1, 4);
        assertEquals(18, sd.getAllSpans().size());

        sd = new SpannedDocument(text, 1, 6);
        assertEquals(21, sd.getAllSpans().size());

        sd = new SpannedDocument(text, 4, 6);
        assertEquals(6, sd.getAllSpans().size());
    }

    @Test
    public void testAddMatchers() {
        SpannedDocument sd = new SpannedDocument(text, 1, 3);

        sd.add(new SpanTagger() {
            public List<String> tag(Span s) {
                List<String> result = new ArrayList<String>();
                if (s.text().equals("משה"))
                    result.add("jbr:person-moshe");
                else if (s.text().equals("רבי"))
                    result.add("jbr:person-rabbi");

                return result;
            }

            // matching
            public boolean isCandidate(Span s) {
                return s.size() == 1;
            }
        })
        .add(new SpanTagger() {
            public List<String> tag(Span s) {
                List<String> result = new ArrayList<String>();
                if (s.text().equals("רבי יהודה הנשיא"))
                    result.add("jbr:person-rabbi");

                return result;
            }

            // matching
            public boolean isCandidate(Span s) {
                return s.size() == 3;
            }
        })
        .tag();

        Span s = sd.getSpan(1, 1);
        assertEquals(getList("jbr:person-moshe"), s.getTags());
        s = sd.getSpan(3, 3);
        assertEquals(getList("jbr:person-rabbi"), s.getTags());
        s = sd.getSpan(3, 5);
        assertEquals(getList("jbr:person-rabbi"), s.getTags());
    }

    @Test
    public void testGetContainingSpans() {
        Span s = doc.getSpan(0, 1);
        assertEquals(4, doc.getContainingSpans(s).size());

        //System.out.println(doc.getContainingSpans(s));

        s = doc.getSpan(1, 2);
        assertEquals(7, doc.getContainingSpans(s).size());
    }

    private List<String> getList(String... args) {
        return Arrays.asList(args);
    }

}