package exercises;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

public class Leetcode1242 {
    public static void main(String[] args) {

    }
}

/**
 * // This is the HtmlParser's API interface.
 * // You should not implement it, or speculate about its implementation
 * interface HtmlParser {
 *     public List<String> getUrls(String url) {}
 * }
 */

interface HtmlParser {
    public List<String> getUrls(String url);
}
class Solution {
    private ConcurrentHashMap<String, Boolean> map = new ConcurrentHashMap<>();
    public List<String> crawl(String startUrl, HtmlParser htmlParser) {
        List<String> ans = new ArrayList<>(helper(startUrl, htmlParser));
        return ans;
    }
    private Set<String> helper(String url, HtmlParser htmlParser) {
        Set<String> set = new HashSet<>();
        set.add(url);
        map.put(url, true);
        String hostname = getHostname(url);
        List<CompletableFuture<Set<String>>> futures = new ArrayList<>();
        for (String u : htmlParser.getUrls(url)) {
            if (!map.containsKey(u) && hostname.compareTo(getHostname(u))==0) {
                CompletableFuture<Set<String>> future = CompletableFuture.supplyAsync(()->{
                    return helper(u, htmlParser);
                });
                futures.add(future);
            }
        }
        for (CompletableFuture<Set<String>> future : futures) {
            try {
                set.addAll(future.get());
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
        return set;
    }

    private String getHostname(String url) {
        url = url.substring(7);
        for (int i = 0; i < url.length(); i++) {
            if (url.charAt(i) == '/') {
                return url.substring(0,i);
            }
        }
        return url;
    }
}