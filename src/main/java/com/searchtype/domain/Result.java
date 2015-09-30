package com.searchtype.domain;

/**
 * Created with IntelliJ IDEA.
 * User: shriram
 */
public class Result {

    SearchResult searchResult = null;
    int index = -1;
    Object value = null;

    public Result(SearchResult searchResult) {
        this.searchResult = searchResult;
    }

    public <T> Result(int index, SearchResult searchResult, T value) {
        this.index = index;
        this.searchResult = searchResult;
        this.value = value;
    }

    public <T> void setValue(T value) {
        this.value = value;
    }

    public SearchResult getSearchResult() {
        return searchResult;
    }

    @Override
    public String toString() {
        return "Result{" +
                "index=" + index +
                ", searchResult=" + searchResult +
                ", value=" + value +
                '}';
    }
}
