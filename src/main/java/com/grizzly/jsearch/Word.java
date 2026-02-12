package com.grizzly.jsearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Word {
    
    private final Set<String> STOP_WORDS;

    Word(){
        STOP_WORDS =  new HashSet<>(Arrays.asList(
        // english words 
        "a", "an", "the", "and", "or", "but", "if", "because", "as", "what",
        "when", "where", "how", "who", "is", "are", "was", "were", "be", "been",
        "being", "have", "has", "had", "do", "does", "did", "i", "you", "he",
        "she", "it", "we", "they", "me", "him", "her", "us", "them", "my", "your",
        "his", "its", "our", "their", "this", "that", "these", "those", "to", "of",
        "in", "on", "at", "by", "for", "with", "about", "against", "between",
        "into", "through", "during", "before", "after", "above", "below", "from",
        "up", "down", "out", "off", "over", "under", "again", "further", "then",
        "once", "here", "there", "why", "how", "all", "any", "both", "each", "few",
        "more", "most", "other", "some", "such", "no", "nor", "not", "only", "own",
        "same", "so", "than", "too", "very", "s", "t", "can", "will", "just", "don",
        "should", "now",
        // java keywords
        "abstract", "assert", "boolean", "break", "byte", "case", "catch", "char", 
        "class", "const", "continue", "default", "do", "double", "else", "enum", 
        "extends", "final", "finally", "float", "for", "goto", "if", "implements", 
        "import", "instanceof", "int", "interface", "long", "native", "new", 
        "package", "private", "protected", "public", "return", "short", "static", 
        "strictfp", "super", "switch", "synchronized", "this", "throw", "throws", 
        "transient", "try", "void", "volatile", "while",
        // html tags
        "html", "body", "head", "div", "span", "p", "br", "hr", "table", "tr", 
        "td", "th", "a", "img", "script", "style", "meta", "link", "title", "li", "ul"
    ));
    }

    private boolean isAllowedWord(String word) {return word.length()>2 && !STOP_WORDS.contains(word);}

    public List<String> parse(String line){
        line = line.toLowerCase();
        List<String> list = new ArrayList<>();
        int len = line.length();
        StringBuilder constructedWord = new StringBuilder();
        for(int i=0; i<len; ++i){
            char ch = line.charAt(i);
            if(ch>='a' && ch<='z') constructedWord.append(ch);
            else{
                String newString = new String(constructedWord);
                if(isAllowedWord(newString)) list.add(newString);
                constructedWord = new StringBuilder();
            }
        }
        String newString = new String(constructedWord);
        if(isAllowedWord(newString)) list.add(newString);
        return list;
    }
}
