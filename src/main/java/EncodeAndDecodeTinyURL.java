import java.util.HashMap;
import java.util.Map;

public class EncodeAndDecodeTinyURL {
    /*
        Note: This is a companion problem to the System Design problem: Design TinyURL.
        TinyURL is a URL shortening service where you enter a URL such as https://leetcode.com/problems/design-tinyurl and
        it returns a short URL such as http://tinyurl.com/4e9iAk. Design a class to encode a URL and decode a tiny URL.

        There is no restriction on how your encode/decode algorithm should work. You just need to ensure that a URL can be encoded to a tiny URL and the tiny URL can be decoded to the original URL.

        Implement the Solution class:

        Solution() Initializes the object of the system.
        String encode(String longUrl) Returns a tiny URL for the given longUrl.
        String decode(String shortUrl) Returns the original long URL for the given shortUrl. It is guaranteed that the given shortUrl was encoded by the same object.

        Example 1.
        Input: url = "https://leetcode.com/problems/design-tinyurl"
        Output: "https://leetcode.com/problems/design-tinyurl"

        Explanation:
        Solution obj = new Solution();
        string tiny = obj.encode(url); // returns the encoded tiny url.
        string ans = obj.decode(tiny); // returns the original url after decoding it.


        Constraints:

        1 <= url.length <= 104
        url is guaranteed to be a valid URL.
     */

    public static void main(String[] args) {
        String url = "https://leetcode.com/problems/design-tinyurl";
        System.out.println("Encode: " + Solution.encode(url));
        System.out.println("Decode: " + Solution.decode(Solution.encode(url)));
    }

    // Time: O(1)
    // Space: O(N)
    static public class Solution {
        static Map<Integer, String> map1=new HashMap<Integer, String>();
        static Map<String, Integer> map2=new HashMap<String, Integer>();
        static String s="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

        // Encodes a URL to a shortened URL.
        public static String encode(String longUrl) {
            if(!map2.containsKey(longUrl)) {
                map1.put(map1.size()+1,longUrl);
                map2.put(longUrl, map2.size()+1);
            }
            int n=map2.get(longUrl);
            StringBuilder sb=new StringBuilder();
            while(n>0) {
                int r=n%62;
                n/=62;
                sb.insert(0,s.charAt(r));
            }
            return sb.toString();
        }

        // Decodes a shortened URL to its original URL.
        public static String decode(String shortUrl) {
            int val=0;
            int n=shortUrl.length();
            for(int i=0;i<n;i++) {
                val=val*62+s.indexOf(shortUrl.charAt(i));
            }
            return map1.get(val);
        }
    }

}
