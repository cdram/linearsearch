package com.searchtype;

import com.searchtype.domain.Result;
import com.searchtype.domain.Search;
import com.searchtype.domain.SearchResult;
import com.searchtype.domain.SearchType;
import org.junit.BeforeClass;
import org.junit.Test;
import static  org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: shriram
 */
public class SearchTest {
    static List<Integer> asc = null;
    static List<Integer> desc = null;
    static List<String> ascString = null;
    static Search searchAsc = null;
    static Search searchDesc = null;
    static Search searchStr = null;
    static Result result = null;

    @BeforeClass
    public static void setUp(){
        asc = Arrays.asList(0, 2, 4, 6, 8);
        desc = Arrays.asList(8, 6, 4, 2, 0);
        ascString = Arrays.asList("apple", "facebook", "microsoft");

        searchAsc  = new Search(asc, asc.size(), Boolean.TRUE);
        searchDesc = new Search(desc, desc.size(), Boolean.FALSE);
        searchStr  = new Search(ascString, ascString.size(), Boolean.TRUE);

    }

    @Test
    public void isInBoundsTest(){
        assertTrue(searchAsc.isInBounds(5));
        assertFalse(searchDesc.isInBounds(9));
    }


    @Test
    public void ascSearchTest(){

        System.out.println("Tests for Ascending Array of Integers =>" );
        System.out.println("-----------------------------------------" );

        System.out.println("Less Than Equals -1");
        result = searchAsc.search(SearchType.LessThanEquals, -1);
        System.out.println("Result : " + result.toString() + "\n");
        assertTrue(result.getSearchResult().compareTo(SearchResult.NotFound) == 0);

        System.out.println("Less Than 0");
        result = searchAsc.search(SearchType.LessThan, 0);
        System.out.println("Result : " + result.toString() + "\n");
        assertTrue(result.getSearchResult().compareTo(SearchResult.NotFound) == 0);

        System.out.println("Equals 0");
        result = searchAsc.search(SearchType.Equals, 0);
        System.out.println("Result : " + result.toString() + "\n");
        assertTrue(result.getSearchResult().compareTo(SearchResult.FoundExact) == 0);

        System.out.println("Greater Than Equals 2");
        result = searchAsc.search(SearchType.GreaterThanEquals, 2);
        System.out.println("Result : " + result.toString() + "\n");
        assertTrue(result.getSearchResult().compareTo(SearchResult.FoundExact) == 0);

        System.out.println("Greater Than 2");
        result = searchAsc.search(SearchType.GreaterThan, 2);
        System.out.println("Result : " + result.toString() + "\n");
        assertTrue(result.getSearchResult().compareTo(SearchResult.FoundGreater) == 0);

    }



    @Test
    public void descSearchTest(){

        System.out.println("Tests for Descending Array of Integers =>" );
        System.out.println("-----------------------------------------" );

        System.out.println("Less Than  -1");
        result = searchDesc.search(SearchType.LessThan, -1);
        System.out.println("Result : " + result.toString() + "\n");
        assertTrue(result.getSearchResult().compareTo(SearchResult.NotFound) == 0);

        System.out.println("Less Than 0");
        result = searchDesc.search(SearchType.LessThan, 0);
        System.out.println("Result : " + result.toString() + "\n");
        assertTrue(result.getSearchResult().compareTo(SearchResult.NotFound) == 0);

        System.out.println("Less Than Equals 4");
        result = searchDesc.search(SearchType.LessThanEquals, 4);
        System.out.println("Result : " + result.toString() + "\n");
        assertTrue(result.getSearchResult().compareTo(SearchResult.FoundExact) == 0);

        System.out.println("Equals 8");
        result = searchDesc.search(SearchType.Equals, 8);
        System.out.println("Result : " + result.toString() + "\n");
        assertTrue(result.getSearchResult().compareTo(SearchResult.FoundExact) == 0);

        System.out.println("Greater Than Equals 5");
        result = searchDesc.search(SearchType.GreaterThanEquals, 5);
        System.out.println("Result : " + result.toString() + "\n");
        assertTrue(result.getSearchResult().compareTo(SearchResult.FoundGreater) == 0);

        System.out.println("Greater Than Equals 2");
        result = searchDesc.search(SearchType.GreaterThanEquals, 2);
        System.out.println("Result : " + result.toString() + "\n");
        assertTrue(result.getSearchResult().compareTo(SearchResult.FoundExact) == 0);

        System.out.println("Greater Than 8");
        result = searchDesc.search(SearchType.GreaterThan, 8);
        System.out.println("Result : " + result.toString() + "\n");
        assertTrue(result.getSearchResult().compareTo(SearchResult.NotFound) == 0);

        System.out.println("Greater Than 9");
        result = searchDesc.search(SearchType.GreaterThan, 9);
        System.out.println("Result : " + result.toString() + "\n");
        assertTrue(result.getSearchResult().compareTo(SearchResult.NotFound) == 0);

    }


    @Test
    public void stringTest(){
        System.out.println("Tests for Ascending Array of Strings =>" );
        System.out.println("-----------------------------------------" );

        System.out.println("Equals google");
        result = searchStr.search(SearchType.Equals, "google");
        System.out.println("Result : " + result.toString() + "\n");
        assertTrue(result.getSearchResult().compareTo(SearchResult.NotFound) == 0);

        System.out.println("Greater Than google");
        result = searchStr.search(SearchType.GreaterThan, "google");
        System.out.println("Result : " + result.toString() + "\n");
        assertTrue(result.getSearchResult().compareTo(SearchResult.FoundGreater) == 0);

        System.out.println("Lesser Than Aero");
        result = searchStr.search(SearchType.LessThan, "aero");
        System.out.println("Result : " + result.toString() + "\n");
        assertTrue(result.getSearchResult().compareTo(SearchResult.NotFound) == 0);

        System.out.println("Less Than Equals Microsoft");
        result = searchStr.search(SearchType.LessThanEquals, "microsoft");
        System.out.println("Result : " + result.toString() + "\n");
        assertTrue(result.getSearchResult().compareTo(SearchResult.FoundExact) == 0);

    }


}
