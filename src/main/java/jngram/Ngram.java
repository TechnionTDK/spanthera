package jngram;

import java.util.*;

/**
 * A contiguous sequence of words.
 * Created by omishali on 30/04/2017.
 */
public class Ngram {
    private Map<String, String> stringExtras = new HashMap<>();
    private Map<String, Integer> intExtras = new HashMap<>();
    private Map<String, Boolean> booleanExtras = new HashMap<>();
    private Map<String, List<String>> listExtras = new HashMap<>();
    private List<String> history = new ArrayList<>();
    private String textFormatted;
    private NgramDocument doc;
    private int start, end;
    private List<String> tags = new ArrayList<String>();

    public void setDoc(NgramDocument doc) {
        this.doc = doc;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public void setEnd(int end){
        this.end = end;
    }

    public Ngram(NgramDocument ngramDocument, int start, int end) {
        setDoc(ngramDocument);
        setStart(start);
        setEnd(end);
    }

    public String getTextFormatted() {
        return textFormatted;
    }

    public void setTextFormatted(String textFormatted) {
        this.textFormatted = textFormatted;
    }

    public void putExtra(String key, String value) {
        stringExtras.put(key, value);
    }

    public String getStringExtra(String key) {
        return stringExtras.get(key);
    }

    public void putExtra(String key, int value) {
        intExtras.put(key, value);
    }

    public Integer getIntExtra(String key) {
        return intExtras.get(key);
    }

    public void putExtra(String key, Boolean value) {
        booleanExtras.put(key, value);
    }

    public Boolean getBooleanExtra(String key) {
        if (booleanExtras.get(key) == null)
            return false;
        else
            return true;
    }

    public void putExtra(String key, List<String> value) {
        listExtras.put(key, value);
    }

    public List<String> getListExtra(String key) {
        return listExtras.get(key);
    }

    /**
     * Ngram's size in words
     * @return
     */
    public int size() {
        return end - start +1;
    }

    public Word getWord(int index) {
        if (index < 0 || index >= size())
            throw new DocumentException("Illegal word index");

        return doc.getWord(start + index);
    }
    public String getText() {
        StringBuffer result = new StringBuffer();

        for (int i = start; i <= end; i++)
            result.append(doc.getWord(i).getText() + " ");

        return result.toString().trim();
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    public List<String> getTags() {
        return tags;
    }

    public List<String> getSortedTags() {
        Collections.sort(tags);
        return tags;
    }

    public void clearTags() {
        tags.clear();
    }

    public void addTags(List<String> tags) {
        this.tags.addAll(tags);
    }

    public void removeTags(List<String> tags) {
        this.tags.removeAll(tags);
    }

    public void removeTag(String tag) {
        this.tags.remove(tag);
    }

    public void addToHistory(String source, String message) {
        history.add(message + " (" + source + ")");
    }

    @Override
    public String toString() {
        StringBuffer result = new StringBuffer();
        result.append("[" + getStart() + ", " + getEnd() + "]" + "\n");
        result.append("(" + getText() + ")" + "\n");
        //result.append("(" + getTextFormatted() + ")" + "\n");
        if (tags.size() > 0) {
            result.append("tags: ");
            for (String tag : tags) {
                result.append(tag + ", ");
            }
        }
        return result.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ngram ngram = (Ngram) o;

        if (start != ngram.start) return false;
        if (end != ngram.end) return false;
        return doc != null ? doc.equals(ngram.doc) : ngram.doc == null;

    }

    @Override
    public int hashCode() {
        int result = doc != null ? doc.hashCode() : 0;
        result = 31 * result + start;
        result = 31 * result + end;
        return result;
    }

    public boolean hasNoTags() {
        return getTags().size() == 0;
    }

    public boolean hasTags() {
        return getTags().size() > 0;
    }

    public void printDebugInfo() {
        System.out.println(toString());
//        System.out.println("Extras:");
//        System.out.println("String: " + stringExtras);
//        System.out.println("Int: " + intExtras);
//        System.out.println("Boolean: " + booleanExtras);
//        System.out.println("List: " + listExtras);
        System.out.println("History:");
        for (String item : history)
            System.out.println(item);
    }
}
