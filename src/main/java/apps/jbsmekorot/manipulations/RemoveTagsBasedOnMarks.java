package apps.jbsmekorot.manipulations;

import jngram.*;

/**
 * Here we remove tags from short ngrams (2-3) based on marks
 * inserted in previous stages
 */
public class RemoveTagsBasedOnMarks extends NgramManipulation {
    @Override
    protected boolean isCandidate(Ngram ng) {
        return (ng.size() == 2 || ng.size() == 3) && ng.hasTags();
    }

    /**
     * MarkCertainBySize: keep all tags.
     * MarkCertainByProximity: we remove all tags besides those marked to keep
     * MarkCertainByHintWords: keep all tags only for trigrams.
     * ELSE: remove all tags.
     * @param doc
     * @param ng
     */
    @Override
    protected void manipulate(NgramDocument doc, Ngram ng) {
        if (hasMark(ng, MarkCertainBySize.MARK)) {
            ng.addToHistory(getName(), "Keep all tags (certain by size).");
            return;
        } else if (hasMark(ng, MarkCertainByProximity.MARK)) {
            ng.addToHistory(getName(), "Certain by proximity, retain only tags to keep.");
            ng.clearTags();
            ng.addTags(ng.getListExtra(MarkCertainByProximity.TAGS_TO_KEEP));
        } else if (ng.size() >= 3 && (hasMark(ng, MarkCertainByHintWords.MARK_BEFORE)
                    || hasMark(ng, MarkCertainByHintWords.MARK_AFTER))) {
            ng.addToHistory(getName(), "Keep all tags (trigram, certain by hint words).");
            return;
        } else {
            ng.addToHistory(getName(), "Remove all tags.");
            ng.clearTags();
        }
    }

    private boolean hasMark(Ngram ng, String mark) {
        return ng.getBooleanExtra(mark);
    }
}
