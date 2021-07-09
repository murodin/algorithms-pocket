import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FindDuplicateFileInSystem {

    /*
        Given a list paths of directory info, including the directory path, and all the files with contents in this directory,
        return all the duplicate files in the file system in terms of their paths. You may return the answer in any order.

        A group of duplicate files consists of at least two files that have the same content.
        A single directory info string in the input list has the following format:

        "root/d1/d2/.../dm f1.txt(f1_content) f2.txt(f2_content) ... fn.txt(fn_content)"
        It means there are n files (f1.txt, f2.txt ... fn.txt) with content (f1_content, f2_content ... fn_content) respectively in the directory
        "root/d1/d2/.../dm". Note that n >= 1 and m >= 0. If m = 0, it means the directory is just the root directory.

        The output is a list of groups of duplicate file paths. For each group, it contains all the file paths of the files that have the same content.
        A file path is a string that has the following format:

        "directory_path/file_name.txt"
     */

    public static void main(String[] args) {
        String[] testPaths = {"root/a 1.txt(abcd) 2.txt(efgh)","root/c 3.txt(abcd)","root/c/d 4.txt(efgh)","root 4.txt(efgh)"};
        System.out.println("Duplicated content with files:" + Solution.findDuplicate(testPaths));

    }

    static class Solution {
        public static List<List<String>> findDuplicate(String[] paths) {
            Map<String, List<String>> store = new HashMap<>();

            for(String path: paths) {
                String[] arr = path.split(" ");
                String dir = arr[0];

                for(int i=1; i<arr.length; i++) {
                    String fileName = arr[i].substring(0, arr[i].indexOf("("));
                    String content = arr[i].substring(arr[i].indexOf("(")+1, arr[i].length()-1);
                    List<String> filePaths = store.getOrDefault(content, new ArrayList<>());
                    filePaths.add(dir + "/" + fileName);
                    store.put(content, filePaths);
                }
            }

            store.entrySet().removeIf(entry -> entry.getValue().size() < 2);
            return store.values().stream().collect(Collectors.toList()); // new ArrayList<>(store.values());
        }
    }
}
