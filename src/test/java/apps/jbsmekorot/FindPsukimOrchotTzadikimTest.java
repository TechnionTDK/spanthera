package apps.jbsmekorot;

import org.junit.*;
import jngram.NgramDocument;
import jngram.manipulations.MergeToMaximalNgrams;
import jngram.manipulations.RemoveTagsInContainedNgrams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by omishali on 06/09/2017.
 */
public class FindPsukimOrchotTzadikimTest {
    NgramDocument doc;
    private String text = "האמת. הנשמה נבראת ממקום רוח הקודש, שנאמר (בראשית ב ד): \"ויפח באפיו נשמת חיים\"; ונחצבה ממקום טהרה, ונבראת מזוהר העליון מכסא הכבוד. ואין למעלה במקום קודשי הקודשים שקר, אלא הכל אמת, שנאמר (ירמיהו י י): \"ויי אלהים אמת\". ומצאתי כי כתיב: \"אהיה אשר אהיה\" (שמות ג יד), וכתיב: \"ויי אלהים אמת, הוא אלהים חיים ומלך עולם\" (ירמיהו שם). ועתה יש להודיעך שהקדוש ברוך הוא אלהים אמת: כי תמצא עשרים ואחת פעמים \"אהיה\" שהוא בגימטריא \"אמת\", וגם כן \"אהיה\" בגימטריא עשרים ואחת.";

    @Before
    public void before() {
        // we create a document with spans of size 2-8
        doc = new NgramDocument(text, JbsMekorot.MINIMAL_PASUK_LENGTH, JbsMekorot.MAXIMAL_PASUK_LENGTH);
    }

    @Test
    public void testNumberOfWords() {
        assertEquals(81, doc.getWords().size());
    }

    @Test
    public void testTagsSpansLength2() {
        doc.format(new JbsNgramFormatter());
        doc.add(new PsukimTagger());

        assertTrue(doc.getNgram(10, 11).getSortedTags().contains("jbr:text-tanach-1-2-7"));
        assertEquals(getList("jbr:text-tanach-1-2-7"), doc.getNgram(11, 12).getSortedTags());
        assertEquals(getList("jbr:text-tanach-1-2-7"), doc.getNgram(12, 13).getSortedTags());

        assertTrue(doc.getNgram(36, 37).getSortedTags().contains("jbr:text-tanach-13-10-10"));

        assertEquals(getList("jbr:text-tanach-2-3-14"), doc.getNgram(41, 42).getSortedTags());
        assertEquals(getList("jbr:text-tanach-2-3-14"), doc.getNgram(42, 43).getSortedTags());

        assertEquals(getList("jbr:text-tanach-13-10-10", "jbr:text-tanach-35-8-26"), doc.getNgram(50, 51).getSortedTags());

        assertEquals(getList("jbr:text-tanach-13-10-10", "jbr:text-tanach-13-23-36", "jbr:text-tanach-5-5-23", "jbr:text-tanach-8-17-26", "jbr:text-tanach-8-17-36"),
                doc.getNgram(52, 53).getSortedTags());

        assertEquals(getList("jbr:text-tanach-31-2-20", "jbr:text-tanach-4-22-12"),
                doc.getNgram(62, 63).getSortedTags());


        assertEquals(getList("jbr:text-tanach-13-10-10", "jbr:text-tanach-27-100-3", "jbr:text-tanach-39-20-6", "jbr:text-tanach-6-2-11"),
                doc.getNgram(63, 64).getSortedTags());
        assertEquals(getList("jbr:text-tanach-13-10-10"),
                doc.getNgram(64, 65).getSortedTags());

        assertEquals(getList("jbr:text-tanach-9-16-19"), doc.getNgram(76, 77).getSortedTags());
    }

    @Test
    public void testTagsAfterMerge() {
        doc.format(new JbsNgramFormatter());
        doc.add(new PsukimTagger());
        doc.add(new MergeToMaximalNgrams());

        assertEquals(getList("jbr:text-tanach-1-2-7"), doc.getNgram(10, 13).getSortedTags());
        assertEquals(getEmptyList(), doc.getNgram(11, 12).getSortedTags());
        assertEquals(getEmptyList(), doc.getNgram(12, 13).getSortedTags());
        assertEquals(getEmptyList(), doc.getNgram(10, 12).getSortedTags());
        assertEquals(getEmptyList(), doc.getNgram(11, 13).getSortedTags());

        assertTrue(doc.getNgram(35, 37).getSortedTags().contains("jbr:text-tanach-13-10-10"));
        assertEquals(getEmptyList(), doc.getNgram(35, 36).getSortedTags());
        assertEquals(getEmptyList(), doc.getNgram(36, 37).getSortedTags());

        assertEquals(getList("jbr:text-tanach-13-10-10"), doc.getNgram(48, 55).getSortedTags());
        assertEquals(getEmptyList(), doc.getNgram(48, 49).getSortedTags());
        assertEquals(getEmptyList(), doc.getNgram(49, 50).getSortedTags());
        assertEquals(getList("jbr:text-tanach-35-8-26"), doc.getNgram(50, 51).getSortedTags());
        assertEquals(getList("jbr:text-tanach-27-100-3", "jbr:text-tanach-39-20-6", "jbr:text-tanach-6-2-11"), doc.getNgram(51, 52).getSortedTags());
        assertEquals(getList("jbr:text-tanach-13-23-36", "jbr:text-tanach-5-5-23", "jbr:text-tanach-8-17-26", "jbr:text-tanach-8-17-36"), doc.getNgram(52, 53).getSortedTags());
        assertEquals(getEmptyList(), doc.getNgram(53, 54).getSortedTags());
        assertEquals(getEmptyList(), doc.getNgram(54, 55).getSortedTags());

        assertEquals(getList("jbr:text-tanach-36-10-2"), doc.getNgram(58, 59).getSortedTags());

        assertEquals(getList("jbr:text-tanach-31-2-20", "jbr:text-tanach-4-22-12"), doc.getNgram(62, 63).getSortedTags());

        assertEquals(getList("jbr:text-tanach-27-100-3", "jbr:text-tanach-39-20-6", "jbr:text-tanach-6-2-11"), doc.getNgram(63, 64).getSortedTags());
        assertEquals(getEmptyList(), doc.getNgram(64, 65).getSortedTags());
        // note: this span is not really a quote from the pasuk 13-10-10!
        // but an interesting case of applying the merge since both spans of size 2 are from the pasuk but not subsequent.
        assertEquals(getList("jbr:text-tanach-13-10-10"), doc.getNgram(63, 65).getSortedTags());

        assertEquals(getList("jbr:text-tanach-11-4-29"), doc.getNgram(66, 67).getSortedTags());

        assertEquals(getList("jbr:text-tanach-9-16-19"), doc.getNgram(76, 77).getSortedTags());
    }

    @Test
    public void testRemoveMatchesInContainedSpans() {
        doc.format(new JbsNgramFormatter());
        doc.add(new PsukimTagger());
        doc.add(new MergeToMaximalNgrams());
        doc.add(new RemoveTagsInContainedNgrams());

        // after applying the Remove manipulation we expext the URIs matched
        // for span [51,52] [52,53] to be totally removed!
        assertEquals(getEmptyList(), doc.getNgram(51, 52).getSortedTags());
        assertEquals(getEmptyList(), doc.getNgram(52, 53).getSortedTags());
        // matches in containing span should be preserved:
        assertEquals(getList("jbr:text-tanach-13-10-10"), doc.getNgram(48, 55).getSortedTags());

        // now we simply perform the previous assertions to make sure nothing was broken:
        assertEquals(getList("jbr:text-tanach-1-2-7"), doc.getNgram(10, 13).getSortedTags());
        assertEquals(getEmptyList(), doc.getNgram(10, 11).getSortedTags());
        assertEquals(getEmptyList(), doc.getNgram(11, 12).getSortedTags());
        assertEquals(getEmptyList(), doc.getNgram(12, 13).getSortedTags());
        assertEquals(getEmptyList(), doc.getNgram(10, 12).getSortedTags());
        assertEquals(getEmptyList(), doc.getNgram(11, 13).getSortedTags());

        assertTrue(doc.getNgram(35, 37).getSortedTags().contains("jbr:text-tanach-13-10-10"));
        assertEquals(getEmptyList(), doc.getNgram(35, 36).getSortedTags());
        assertEquals(getEmptyList(), doc.getNgram(36, 37).getSortedTags());

        assertEquals(getList("jbr:text-tanach-13-10-10"), doc.getNgram(48, 55).getSortedTags());
        assertEquals(getEmptyList(), doc.getNgram(48, 49).getSortedTags());
        assertEquals(getEmptyList(), doc.getNgram(49, 50).getSortedTags());
        assertEquals(getEmptyList(), doc.getNgram(50, 51).getSortedTags());
        //assertEquals(getList("jbr:text-tanach-27-100-3", "jbr:text-tanach-39-20-6", "jbr:text-tanach-6-2-11"), doc.getNgram(51, 52).getSortedTags());
        //assertEquals(getList("jbr:text-tanach-13-23-36", "jbr:text-tanach-5-5-25", "jbr:text-tanach-8-17-26", "jbr:text-tanach-8-17-36"), doc.getNgram(52, 53).getSortedTags());
        assertEquals(getEmptyList(), doc.getNgram(53, 54).getSortedTags());
        assertEquals(getEmptyList(), doc.getNgram(54, 55).getSortedTags());

        assertTrue(doc.getNgram(58, 59).getSortedTags().contains("jbr:text-tanach-36-10-2"));

        assertEquals(getList("jbr:text-tanach-31-2-20", "jbr:text-tanach-4-22-12"), doc.getNgram(62, 63).getSortedTags());

        assertEquals(getEmptyList(), doc.getNgram(63, 64).getSortedTags());
        assertEquals(getEmptyList(), doc.getNgram(64, 65).getSortedTags());
        // note: this span is not really a quote from the pasuk 13-10-10!
        // but an interesting case of applying the merge since both spans of size 2 are from the pasuk but not subsequent.
        // idea: at the end perform validation of the identified psukim against the Index
        assertEquals(getList("jbr:text-tanach-13-10-10"), doc.getNgram(63, 65).getSortedTags());

        assertEquals(getList("jbr:text-tanach-11-4-29"), doc.getNgram(66, 67).getSortedTags());

        assertEquals(getList("jbr:text-tanach-9-16-19"), doc.getNgram(76, 77).getSortedTags());
    }

    @Test
    public void testFinal() {
        JbsMekorot.findPsukim(doc);

        // test that span-2 are all empty
        assertEquals(getEmptyList(), doc.getNgram(58, 59).getSortedTags());
        assertEquals(getEmptyList(), doc.getNgram(62, 63).getSortedTags());
        assertEquals(getEmptyList(), doc.getNgram(66, 67).getSortedTags());
        assertEquals(getEmptyList(), doc.getNgram(68, 69).getSortedTags());
        assertEquals(getEmptyList(), doc.getNgram(76, 77).getSortedTags());
        assertEquals(getEmptyList(), doc.getNgram(79, 80).getSortedTags());
    }

    /**
     * Returns a list, sorted.
     * @param args
     * @return
     */
    private List<String> getList(String... args) {
        List<String> result = Arrays.asList(args);
        Collections.sort(result);
        return result;
    }
    private List<String> getEmptyList() {
        return new ArrayList<>();
    }




}
