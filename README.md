# jSearch
A local search engine that scans a directory, indexes files, and lets users search keywords lightning-fast from the command line multiple times.

---

## Overview

**jSearch** is a multi-threaded, positional inverted-index search engine built entirely in pure Java (no frameworks).

It recursively scans a directory, indexes text-based files, and enables fast repeated keyword and phrase queries with proximity-based ranking.

The project focuses on:
- Concurrency
- Efficient data structures
- Positional indexing
- Proximity-aware ranking

---

## Features

### Recursive Directory Crawling
- BFS-based file traversal
- Skips unreadable and hidden files
- Handles large datasets (tested up to 200MB+ corpus)

### Multi-threaded Indexing
- Fixed thread pool for concurrent file processing
- Thread-safe inverted index updates
- Benchmarked and tuned for optimal thread count

### Positional Inverted Index
Stores: 
```
word → document → list of positions
```
This enables:

- Fast keyword lookup
- Phrase queries
- Proximity-based ranking

### Proximity-Based Ranking

Query example: 
```
java coding fun
```

Documents are ranked based on:

- **75%** → word closeness (minimum span algorithm)
- **20%** → query word coverage
- **5%** → term frequency (log-scaled)

Documents where query words appear closer together rank higher.

### Fast Repeated Queries
Index built once → multiple searches executed without rescanning.

---

## Architecture

Crawler → Indexer → Search → Ranker


- **Crawler**: Traverses directory and delegates indexing  
- **Indexer**: Builds positional inverted index  
- **Search**: Maps query words to document-position data  
- **Ranker**: Computes weighted score using proximity model  

---

## Testing

The project includes:

- Unit tests for:
  - Word parsing
  - Index structure correctness
  - Ranking logic
- Integration test covering:
  - Directory crawl
  - Index creation
  - Query execution
  - Ranking validation

Run tests:
```
mvn test
```

---

## Build Instructions

### Prerequisites
- Java 21
- Maven 3.9+

### Build
```
mvn clean package
```

### Run
```
java -jar target/jsearch-1.0.0.jar <directory-path> [maxResults]
```

Example:
```
java -jar target/jsearch-1.0.0.jar ./sample 5
```

---

## Benchmark (Warm Runs)

| Dataset Size | Files | Threads | Index Time |
|--------------|--------|----------|------------|
| 65MB         | ~1.5k | 6        | ~6 sec     |
| 200MB        | ~5k   | 6        | ~30 sec    |


---


Each merge to `main` generates a versioned release with the runnable JAR attached.

---

## Design Decisions

- Avoided external libraries to demonstrate core Java fundamentals
- Used synchronized index updates instead of `ConcurrentHashMap` for controlled consistency
- Applied logarithmic scaling to prevent frequency dominance
- Balanced thread count to avoid GC thrashing

---

 > I am open to collaborations, contributions. You can send crash reports or requests by creating a new Issue