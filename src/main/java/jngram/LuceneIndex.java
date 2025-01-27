package jngram;

import org.apache.commons.lang3.time.StopWatch;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.*;
import org.apache.lucene.search.*;
import org.apache.lucene.search.spans.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.RAMDirectory;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public abstract class LuceneIndex {

    protected static int NUM_OF_RESULTS = 1500;
    private Directory directory;
    private IndexSearcher indexSearcher;
    private IndexWriter writer;
    private static final String ROOT_DIRECTORY = "./tools/luceneIndex/";

    abstract protected String getOutputIndexDirectory();
    abstract protected void createIndex() throws Exception;
    /**
     * To clean the directory, just remove its directory. If the directory
     * exists, the directory is not recreated.
     * @throws Exception
     */
    public LuceneIndex() {
        Path indexPath = Paths.get(ROOT_DIRECTORY + getOutputIndexDirectory());
        initializeSearcher(indexPath);
    }

    //To use when the directory is already exist and not solution relative (example- spark)
    public LuceneIndex(String indexPathString) {
        Path indexPath= Paths.get(indexPathString);
        initializeSearcher(indexPath);
    }

    private void initializeSearcher(Path indexPath) {
        IndexReader reader = null;
        StopWatch timer = new StopWatch();
        try {
            timer.start();
            System.out.println("START BUILDING INDEX " + timer.toString());
            directory = FSDirectory.open(indexPath);
            // didn't see significant improvement...
            //directory = new RAMDirectory();
            reader = DirectoryReader.open(directory);
        } catch (Exception e) {
            IndexWriterConfig config = new IndexWriterConfig(new StandardAnalyzer());
            try {
                writer = new IndexWriter(directory, config);
                createIndex();
                reader = DirectoryReader.open(directory);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        indexSearcher = new IndexSearcher(reader);
        System.out.println("FINISH BUILDING INDEX " + timer.toString());
    }




    protected IndexWriter getWriter() {
        return writer;
    }

    // see https://stackoverflow.com/questions/18100233/lucene-fuzzy-search-on-a-phrase-fuzzyquery-spanquery
    private Query generateQuery(String[] words, int startWord, int minimalPhrase) {
        SpanQuery[] clauses = new SpanQuery[minimalPhrase];
        for (int i = 0; i < minimalPhrase; i++) {
            clauses[i] = new SpanMultiTermQueryWrapper<>(new FuzzyQuery(new Term("text", words[startWord + i]), 2));
        }
        return new SpanNearQuery(clauses, 0, true);
    }

    /**
     * Get a string phrase and returns the Documents
     * that contain that phrase <b>exactly</b>.
     * @param phrase
     * @return
     */
    protected List<Document> searchExact(String field, String phrase) throws IOException {
        List<Document> result;
        PhraseQuery.Builder builder = new PhraseQuery.Builder();
        String[] terms = phrase.split(" ");
        for (String term : terms)
            builder.add(new Term(field, term));

        builder.setSlop(0);
        PhraseQuery q = builder.build();
        TopDocs docs = indexSearcher.search(q, NUM_OF_RESULTS);
        ScoreDoc[] hits = docs.scoreDocs;

        return getTextList(hits);
    }



    private List<Document> getTextList(ScoreDoc[] hits) throws IOException {
        List<Document> result = new ArrayList<>();
        for (int k = 0; k < hits.length; k++) {
            int docId = hits[k].doc;
            Document d = indexSearcher.doc(docId);
            result.add(d);
        }

        return result;
    }

    protected List<Document> searchFuzzy(String field, String phrase, int maxEdits) throws IOException {
        String[] terms = phrase.split(" ");
        SpanQuery[] clauses = new SpanQuery[terms.length];
        for (int i = 0; i < terms.length; i++) {
            clauses[i] = new SpanMultiTermQueryWrapper<>(new FuzzyQuery(new Term(field, terms[i]), maxEdits));
        }

        SpanNearQuery q = new SpanNearQuery(clauses, 0, true);
        TopDocs docs = indexSearcher.search(q, NUM_OF_RESULTS);
        ScoreDoc[] hits = docs.scoreDocs;
        return getTextList(hits);
    }

    protected List<Document> searchFuzzy(String field, String phrase, List<Integer> maxEdits) throws IOException {
        String[] terms = phrase.split(" ");
        SpanQuery[] clauses = new SpanQuery[terms.length];
        for (int i = 0; i < terms.length; i++) {
            clauses[i] = new SpanMultiTermQueryWrapper<>(new FuzzyQuery(new Term(field, terms[i]), maxEdits.get(i)));
        }

        SpanNearQuery q = new SpanNearQuery(clauses, 0, true);
        TopDocs docs = indexSearcher.search(q, NUM_OF_RESULTS);
        ScoreDoc[] hits = docs.scoreDocs;
        return getTextList(hits);
    }

//    protected List<NgramDocument> searchFuzzy2(String field, String phrase, int maxEdits) throws IOException {
//        //String[] terms = phrase.split(" ");
//        FuzzyQuery clauses = new FuzzyQuery(new Term(field, phrase),maxEdits);
//        //for (int i = 0; i < terms.length; i++) {
//         //   clauses[i] = new SpanMultiTermQueryWrapper<>(new FuzzyQuery(new Term(field, terms[i]), maxEdits));
//        //}
//
//        SpanNearQuery q = new SpanNearQuery(clauses, 0, true);
//        TopDocs docs = indexSearcher.search(q, NUM_OF_RESULTS);
//        ScoreDoc[] hits = docs.scoreDocs;
//        return getTextList(hits);
//    }

    protected void searchFuzzy(String field, String phrase) throws IOException {
        String[] terms = phrase.split(" ");
        SpanQuery[] clauses = new SpanQuery[terms.length];
        for (int i = 0; i < terms.length; i++) {
            clauses[i] = new SpanMultiTermQueryWrapper<>(new FuzzyQuery(new Term(field, terms[i]), 1));
        }

        SpanNearQuery q = new SpanNearQuery(clauses, 0, true);
        TopDocs docs = indexSearcher.search(q, NUM_OF_RESULTS);
        ScoreDoc[] hits = docs.scoreDocs;
        printHits(hits);
    }

    private void printHits(ScoreDoc[] hits) throws IOException {
        for (int k = 0; k < hits.length; ++k) {
            int docId = hits[k].doc;
            Document d = indexSearcher.doc(docId);
            System.out.println((k + 1) + ". " + d.get("uri") + "\t" + d.get("text"));
        }
    }

    public void printDocs(List<Document> docs) {
        for (Document d : docs)
            System.out.println(d.get("uri") + "\t" + d.get("text"));
    }

    public List<Document> searchFuzzyRestriction(String field, String phrase, int maxEdits, int minEditedWordLength) throws IOException{
        String[] terms = phrase.split(" ");
        SpanQuery[] clauses = new SpanQuery[terms.length];
        for (int i = 0; i < terms.length; i++) {
            int edits= terms[i].length() >=minEditedWordLength ? maxEdits: 0;
            clauses[i] = new SpanMultiTermQueryWrapper<>(new FuzzyQuery(new Term(field, terms[i]), edits));
        }

        SpanNearQuery q = new SpanNearQuery(clauses, 0, true);
        TopDocs docs = indexSearcher.search(q, NUM_OF_RESULTS);
        ScoreDoc[] hits = docs.scoreDocs;
        return getTextList(hits);
    }

    public void printSpans() throws IOException {
        // Do a search using SpanQuery
        SpanTermQuery fleeceQ = new SpanTermQuery(new Term("text", "בראשית"));
        TopDocs results = indexSearcher.search(fleeceQ, 10);
        for (int i = 0; i < results.scoreDocs.length; i++) {
            ScoreDoc scoreDoc = results.scoreDocs[i];
            System.out.println("Score Doc: " + scoreDoc);
        }

        System.out.println(fleeceQ.getTerm().text());

    }
}
