package chapters.common

import java.util.stream.Collectors

class WebPagination {
    fun fetchItemsToDisplay2(
        items: List<List<String>>,
        sortParameter: Int, sortOrder: Int,
        itemsPerPage: Int, pageNumber: Int
    ): List<String>? {
        // System.out.println(sortParameter + " -- " + sortOrder + " -- " + itemsPerPage + " -- " + pageNumber);
        val start = if (pageNumber == 0) 0 else pageNumber * itemsPerPage
        return items.stream()
            .sorted { l1: List<String>, l2: List<String> ->
                if (sortOrder == 0) {
                    if (sortParameter == 0) {
                        return@sorted l1[sortParameter].compareTo(l2[sortParameter])
                    } else {
                        return@sorted Integer.valueOf(l1[sortParameter])
                            .compareTo(Integer.valueOf(l2[sortParameter]))
                    }
                } else {
                    if (sortParameter == 0) {
                        return@sorted l2[sortParameter].compareTo(l1[sortParameter])
                    } else {
                        return@sorted Integer.valueOf(l2[sortParameter])
                            .compareTo(Integer.valueOf(l1[sortParameter]))
                    }
                }
            }
            .map { s: List<String> ->
                s[0]
            }
            .skip(start.toLong())
            .limit(itemsPerPage.toLong())
            .collect(Collectors.toList())
    }
}