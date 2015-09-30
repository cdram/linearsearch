Search an array of Objects
----------------------------------
 * items    : An array of sorted ints, with no duplicates
 * n_items  : Number of elements in the items array
 * ascending: non-zero if the array is sorted in ascending order
 * key      : the key to search for
 * type     : the type of match to find

 This function finds the element in the array
 that best fits the search criteria. It returns
 the match type and the index of the matching item.


 LessThan
 --------
  Finds the largest item which is less than the key.
  It returns FoundLess if a match is found, NotFound
  if no match is found.


 LessThanEquals
 --------------
  Finds the item which is equal to the key, or the
  largest item which is less than the key. Returns
  FoundExact if an item that exactly matches the key
  is found, FoundLess if a non-exact match is found
  and NotFound if no match is found.

 Equals
 ------
  Finds an item which is equal to the key. Returns
  FoundExact if an item if found, NotFound otherwise.

 GreaterThanEquals
 -----------------
  Finds the item which is equal to the key, or the
  smallest item which is greater than the key. Returns
  FoundExact if an item that exactly matches the key
  is found, FoundGreater if a non-exact match is found
  and NotFound if no match is found.

 GreaterThan
 -----------
  Finds the smallest item which is greater than the
  key. Returns FoundGreater if a match if found, NotFound
  if no match is found.

 Assumptions
 -----------
  The items are sorted
  Items will be non-NULL
  There are no duplicate items
  n_items will be > 0

 Project Structure
 ------------------
    * Java Based Maven Project
    * Requires JDK 1.8
    * Search Class is the starter Object where we need to pass an array of objects
