package com.grizzly.jsearch;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

class IntegrationTest {

    @Test
    void fullPipelineShouldReturnClosestDocumentFirst() throws Exception {

        String path = getClass()
                .getClassLoader()
                .getResource("sample")
                .getPath();

        Crawler crawler = new Crawler(path);
        Indexer index = crawler.crawl();

        Search search = new Search(index);
        search.search("java coding fun");

        Ranker ranker = new Ranker(search);
        ranker.rank();

        List<Rank> results = ranker.get();

        assertFalse(results.isEmpty());
        assertTrue(results.get(0).path().contains("doc1"));
    }
}
