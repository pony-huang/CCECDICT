package org.github.ponking66.ccecdit;


import java.util.List;

public interface WordManagerService {

    List<Word> searchWords(String prefix);

    List<Word> searchWords(String prefix, int limit);

    List<Word> searchWords(String prefix, List<String> words, int limit);
}
