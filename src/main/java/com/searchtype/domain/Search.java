package com.searchtype.domain;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
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
                if(ascending){
                    map.put("searchConstant", 0);
                    map.put("subtract", 1);
                }else{
                    map.put("searchConstant", -1);
                    map.put("subtract", 0);
                }

                break;

            case LessThanEquals:
                comparator = (o1, o2) -> {return  (o1.compareTo(o2) <= 0)?0:-1; };
                if(ascending){
                    map.put("searchConstant", 0);
                    map.put("subtract", 1);
                }else{
                    map.put("searchConstant", -1);
                    map.put("subtract", 0);
                }
                break;

            case Equals:
                comparator = (o1, o2) -> {return  (o1.compareTo(o2) == 0)?0:-1; };
                if(ascending){
                    map.put("searchConstant", -1);
                    map.put("subtract", 0);
                }else{
                    map.put("searchConstant", -1);
                    map.put("subtract", 0);
                }
                break;

            case GreaterThanEquals:
                comparator = (o1, o2) -> {return  (o1.compareTo(o2) >= 0)?0:-1; };
                if(ascending){
                    map.put("searchConstant", -1);
                    map.put("subtract", 0);
                }else{
                    map.put("searchConstant", 0);
                    map.put("subtract", 1);
                }
                break;

            case GreaterThan:
                comparator = (o1, o2) -> {return  (o1.compareTo(o2) > 0)?0:-1; };
                if(ascending){
                    map.put("searchConstant", -1);
                    map.put("subtract", 0);
                }else{
                    map.put("searchConstant", 0);
                    map.put("subtract", 1);
                }
                break;

            default:
                System.out.println("Illegal Search Type!");
                break;
        }

        map.put("comparator", comparator);
        return map;
    }

    public <T extends Comparable<T>> Result search(SearchType searchType, T key){
        Result result = null;
        int i = 0;

        //get the parameter map which we will use for making search decisions
        Map map = getParameters(searchType);

        Comparator<T> comparator = (Comparator<T>) map.get("comparator");
        int searchConstant = (int) map.get("searchConstant");



        while(i < nItems && comparator.compare((T) list.get(i), key) == searchConstant){
            i++;
        }

        i = i -(int) map.get("subtract");
        if(i == nItems || i < 0){
            result =  new Result(SearchResult.NotFound);
        }else{
            result = new Result(i, getSearchResult((T)list.get(i), key), list.get(i));
        }

        return result;
    }


}
