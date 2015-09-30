package com.searchtype.domain;

/**
 * Created with IntelliJ IDEA.
 * User: shriram
 */
public enum SearchType {
    LessThan(0),  LessThanEquals(1), Equals(2),  GreaterThanEquals(3), GreaterThan(4);

    private final int id;
    SearchType(int id) { this.id = id; }
    public int getValue() { return id; }
}
