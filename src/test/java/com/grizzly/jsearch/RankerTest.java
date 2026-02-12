package com.grizzly.jsearch;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

class RankerTest {

    @Test
    void closerWordsShouldRankHigher() {

        Indexer indexer = new Indexer();

        // docA (close words)
        indexer.add("java", "docA", List.of(1));
        indexer.add("coding", "docA", List.of(2));
        indexer.add("fun", "docA", List.of(3));

        // docB (far apart words)
        indexer.add("java", "docB", List.of(1));
        indexer.add("coding", "docB", List.of(100));
        indexer.add("fun", "docB", List.of(200));

        Search search = new Search(indexer);
        search.search("java coding fun");

        Ranker ranker = new Ranker(search);
        ranker.rank();

        List<Rank> results = ranker.get();

        assertFalse(results.isEmpty());
        assertEquals("docA", results.get(0).path());
    }
}
