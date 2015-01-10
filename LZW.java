import java.util.*;
//test changes

public class LZW {
    /** Compress a string to a list of output symbols. */
    public static List<Integer> compress(String uncompressed) {
        // Build the dictionary.
        int dictSize = 256;
        Map<String,Integer> dictionary = new HashMap<String,Integer>();
        for (int i = 0; i < 256; i++){
        	
        	dictionary.put("" + (char)i, i);
        }
            
//test change 2 
        String w = "";
        List<Integer> result = new ArrayList<Integer>();
        Integer i=0;
        for (char c : uncompressed.toCharArray()) {
        	i=i+1;
        	//System.out.println(c);
            String wc = w + c;
            //System.out.println("wc:"+wc);
            if (dictionary.containsKey(wc)){
            	//System.out.println("contains the key:"+wc);
                w = wc;
            }
            else {
                result.add(dictionary.get(w));
                // Add wc to the dictionary.
                dictionary.put(wc, dictSize++);
                w = "" + c;
            }
        }
 
        // Output the code for w.
        if (!w.equals(""))
            result.add(dictionary.get(w));
        //System.out.println("i:"+i);
        return result;
    }
 
    /** Decompress a list of output ks to a string. */
    public static String decompress(List<Integer> compressed) {
        // Build the dictionary.
        int dictSize = 256;
        Map<Integer,String> dictionary = new HashMap<Integer,String>();
        for (int i = 0; i < 256; i++){
        	
            dictionary.put(i, "" + (char)i);
            if(i==255){ System.out.println("hello dictionary.get(256):"+dictionary.get(255));}
        }
        
    
        String w = "" + (char)(int)compressed.remove(0);
        String result = w;
        for (int k : compressed) {
            String entry;
            if (dictionary.containsKey(k)){
            	System.out.println("k:"+k);
                entry = dictionary.get(k);
                System.out.println("entry 1:"+entry);
                }
            else if (k == dictSize){
                entry = w + w.charAt(0);
                System.out.println("entry 2:"+entry);
                }
            else
                throw new IllegalArgumentException("Bad compressed k: " + k);
 
            result += entry;
 
            // Add w+entry[0] to the dictionary.
            System.out.println("entry:"+entry);
            System.out.println("entry.charAt(0):"+entry.charAt(0));
            System.out.println("w + entry.charAt(0):"+w + entry.charAt(0));
            dictionary.put(dictSize++, w + entry.charAt(0));
 
            w = entry;
        }
        return result;
    }
 
    public static void main(String[] args) {
        List<Integer> compressed = compress("TOBEORNOTO");
        System.out.println(compressed);
        String decompressed = decompress(compressed);
        System.out.println(decompressed);
    }
}