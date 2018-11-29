package com.sic777.test;

import com.sic777.rmdb.bean.Order;
import com.sic777.rmdb.bean.Query;
import com.sic777.rmdb.bean.RmdbSearch;
import com.sic777.rmdb.bean.type.Operation;
import com.sic777.rmdb.bean.type.Sort;

import java.util.*;

/**
 * <p>
 *
 * @author sic777
 * @version 0.0.1
 * @since 0.0.1
 */
public class RmdbSearchGen {
    public static void main(String[] args) {
        int pageNum = 10;
        int pageSize = 1;
        Set<String> filters = new HashSet<>(Arrays.asList(new String[]{"a,", "b", "c"}));
        List<Query> query = Arrays.asList(new Query[]{new Query("a", Operation.GT, 1), new Query("b", Operation.LT, 10)});
        List<Order> orders = Arrays.asList(new Order[]{new Order("a", Sort.ASC), new Order("b", Sort.DESC)});
        RmdbSearch search = new RmdbSearch(pageNum, pageSize, filters, query, orders);
        System.out.println(search);
    }
}
