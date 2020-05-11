
/*
 * Name: Yin Lam Lai
 * PID:  A15779757
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Search Engine implementation.
 * 
 * @author Yin Lam Lai
 * @since  7/5/2020
 */
public class SearchEngine {
    public static final int ARG_STARTER = 2;

    /**
     * Populate BSTrees from a file
     * 
     * @param movieTree  - BST to be populated with actors
     * @param studioTree - BST to be populated with studios
     * @param ratingTree - BST to be populated with ratings
     * @param fileName   - name of the input file
     * @returns false if file not found, true otherwise
     */
    public static boolean populateSearchTrees(BSTree<String> movieTree, BSTree<String> studioTree,
            BSTree<String> ratingTree, String fileName) {
        File file = new File(fileName);
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String movieName = scanner.nextLine().toLowerCase();
                String actors = scanner.nextLine().toLowerCase();
                String studio = scanner.nextLine().toLowerCase();
                String rating = scanner.nextLine();
                scanner.nextLine();

                String[] actorArray = actors.split(" ");
                String[] studioArray = studio.split(" ");

                for (String n : actorArray) {
                    movieTree.insert(n);
                    movieTree.insertData(n, movieName);
                }

                for (String n : studioArray) {
                    studioTree.insert(n);
                    studioTree.insertData(n, movieName);
                }
                for (String n : actorArray) {
                    if (!ratingTree.findKey(n)) {
                        ratingTree.insert(n);
                        if (!ratingTree.findDataList(n).contains(rating)) {
                            ratingTree.insertData(n, rating);
                        }
                    } else {
                        if (!ratingTree.findDataList(n).contains(rating)) {
                            ratingTree.insertData(n, rating);
                        }
                    }
                }
            }
            scanner.close();

            return true;

        } catch (FileNotFoundException e) {
            return false;
        }
    }

    /**
     * Search a query in a BST
     * 
     * @param searchTree - BST to be searched
     * @param query      - query string
     */
    public static void searchMyQuery(BSTree<String> searchTree, String query) {
        String[] queryArray = query.toLowerCase().split(" ");
        if (queryArray.length == 1) {
            if (!searchTree.findKey(query)) {
                print(query, null);
            } else {
                print(query, searchTree.findDataList(query));

            }
        } else {
            LinkedList<String> inter =  new LinkedList<>();
            if (searchTree.findKey(queryArray[0])) {
                inter.addAll(searchTree.findDataList(queryArray[0]));
                for (int n = 1; n < queryArray.length; n++) {
                    inter.retainAll(searchTree.findDataList(queryArray[n]));
                }
            }
            print(query, inter);
            LinkedList<String> totalOut = new LinkedList<>();
            totalOut.addAll(inter);
            for (int n = 0; n < queryArray.length; n++) {
                if (!searchTree.findKey(queryArray[n])) {
                    print(queryArray[n], null);
                } else {
                    LinkedList<String> temp = searchTree.findDataList(queryArray[n]);
                    temp.removeIf(inter::contains);
                    temp.removeIf(totalOut::contains);
                    if (temp.isEmpty()) {
                        continue;
                    }
                    print(queryArray[n], temp);
                    totalOut.addAll(temp);
                }
            }
        }
    }

    /**
     * Print output of query
     * 
     * @param query     Query used to search tree
     * @param documents Output of documents from query
     */
    public static void print(String query, LinkedList<String> documents) {
        if (documents == null || documents.isEmpty()) {
            System.out.println("The search yielded no results for " + query);
        } else {
            Object[] converted = documents.toArray();
            Arrays.sort(converted);
            System.out.println("Documents related to " + query + " are: " + Arrays.toString(converted));
        }
    }

    /**
     * Main method that processes and query the given arguments
     * 
     * @param args command line arguments
     */
    public static void main(String[] args) {
        try {

            BSTree movieTree = new BSTree();
            BSTree studioTree = new BSTree();
            BSTree ratingTree = new BSTree();
            populateSearchTrees(movieTree, studioTree, ratingTree, args[0]);

            if (args[1].equals("0")) {
                String query = "";
                for (int n = ARG_STARTER; n < args.length; n++) {
                    if (n == args.length - 1) {
                        query += args[n];
                    } else {
                        query += args[n] + " ";
                    }

                }
                searchMyQuery(movieTree, query);

            } else if (args[1].equals("1")) {
                String query = "";
                for (int n = ARG_STARTER; n < args.length; n++) {
                    if (n == args.length - 1) {
                        query += args[n];
                    } else {
                        query += args[n] + " ";
                    }

                }
                searchMyQuery(studioTree, query);
            } else if (args[1].equals("2")) {
                String query = "";
                for (int n = ARG_STARTER; n < args.length; n++) {
                    if (n == args.length - 1) {
                        query += args[n];
                    } else {
                        query += args[n] + " ";
                    }

                }
                searchMyQuery(ratingTree, query);
            }

        } catch (IllegalArgumentException e) {
            System.out.print(e.getMessage());
        }
    }
}
