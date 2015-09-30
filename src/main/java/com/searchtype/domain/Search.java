package com.searchtype.domain;

import com.searchtype.utils.Constants;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * Created with IntelliJ IDEA.
 * User: shriram
 */
public class Search {

    List list = null;
    int nItems;
    Boolean ascending;


    public <T extends Comparable<T>> Search(List<T> list, int nItems, Boolean ascending) {
        this.list = list;
        this.nItems = nItems;
        this.ascending = ascending;
    }

    /**
     * Checks whether a particular object is within the bounds of the list
     * @param key
     * @param <T>
     * @return
     */
    public <T extends Comparable<T>> Boolean isInBounds(T key){
        T low = (ascending)?(T) list.get(0):(T) list.get(nItems-1);
        T high = (ascending)?(T) list.get(nItems-1):(T) list.get(0);
        return (key.compareTo(low) >= 0 && key.compareTo(high) <=0);
    }

    /**
     *
     * @param indexItem the item in the input list
     * @param key the item which was to be searched in the list
     * @param <T> SearchResult
     * @return
     */
    private <T extends Comparable<T>> SearchResult getSearchResult(T indexItem, T key){
        int compareValue = key.compareTo(indexItem);
        return (compareValue == 0)?SearchResult.FoundExact:(compareValue < 0)?SearchResult.FoundGreater:SearchResult.FoundLess;
    }

    /**
     * Helper Method to set the helper map with the search constant and the decrement index
     * @param map
     * @param searchConstant
     * @param decrementIndex
     * @return
     */
    private Map setMap(Map map, int searchConstant, int decrementIndex){
        map.put(Constants.SEARCH_CONSTANT, searchConstant);
        map.put(Constants.DECREMENT_INDEX, decrementIndex);
        return map;
    }


    /**
     *
     * @param searchType
     * @param <T>
     * @return Map
     *          comparator => Returns 0/-1 based on the various search types
     *          subtract => Returns 0/1, based on various SearchTypes the required index might be the end of loop or
     *          it might be the previous index
     */
    private <T extends Comparable<T>> Map getParameters(SearchType searchType){
        Map map = new HashMap<String, Object>();
        Comparator<T> comparator = null;


        switch (searchType){
            case LessThan:
                comparator = (o1, o2) -> {return  (o1.compareTo(o2) < 0)?0:-1; };
                map = (ascending)?setMap(map, 0,1):setMap(map, -1,0);
                break;

            case LessThanEquals:
                comparator = (o1, o2) -> {return  (o1.compareTo(o2) <= 0)?0:-1; };
                map = (ascending)?setMap(map, 0,1):setMap(map, -1,0);
                break;

            case Equals:
                comparator = (o1, o2) -> {return  (o1.compareTo(o2) == 0)?0:-1; };
                map = (ascending)?setMap(map, -1,0):setMap(map, -1,0);
                break;

            case GreaterThanEquals:
                comparator = (o1, o2) -> {return  (o1.compareTo(o2) >= 0)?0:-1; };
                map = (ascending)?setMap(map, -1,0):setMap(map, 0,1);
                break;

            case GreaterThan:
                comparator = (o1, o2) -> {return  (o1.compareTo(o2) > 0)?0:-1; };
                map = (ascending)?setMap(map, -1,0):setMap(map, 0,1);
                break;

            default:
                System.out.println("Illegal Search Type!");
                break;
        }

        map.put(Constants.COMPARATOR, comparator);
        return map;
    }

    /*
     * Perform the Linear Search based on the SearchType and the Key
     * @param searchType
     * @param key
     * @param <T>
     * @return
     */
    public <T extends Comparable<T>> Result search(SearchType searchType, T key){
        Result result = null;
        int i = 0;

        //get the parameter map which we will use for making search decisions
        Map map = getParameters(searchType);

        //fetch the comparator for the corresponding SearchType
        Comparator<T> comparator = (Comparator<T>) map.get(Constants.COMPARATOR);
        //initialize the searchConstant which is used as the terminating condition
        int searchConstant = (int) map.get(Constants.SEARCH_CONSTANT);

        //perform linear search
        while(i < nItems && comparator.compare((T) list.get(i), key) == searchConstant){
            i++;
        }

        //DECREMENT the index returned, based on the decrement index value set for the corresponding search type
        i = i -(int) map.get(Constants.DECREMENT_INDEX);

        //if index is zero or greater than the amount of items in the list, return NotFound
        if(i == nItems || i < 0){
            result =  new Result(SearchResult.NotFound);
        }else{
            //else get the search result by comparing the list index item and the key items value
            result = new Result(i, getSearchResult((T)list.get(i), key), list.get(i));
        }

        return result;
    }


}
