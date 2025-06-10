import java.util.*;

public class HuffmanCoding {
    public static void main(String[] args) {
        Scanner scanUserInput = new Scanner(System.in);

        System.out.println("===== Huffman Coding - A Greedy Algorithm =====");
        System.out.println("Enter the string to encode:");

        String strText = scanUserInput.nextLine();

        Map<Character, Integer> letterCount = LetterCounter.countLetters(strText);

        ArrayList<Node> treeNodes = TreeNodeCreator.createTreeNodes(letterCount);

        Node root = TreeNodeBuilder.buildHuffmanTree(treeNodes);

        Map<Character, String> letterCodes = HuffmanCodeGenerator.generateHuffmanCodes(root, letterCount);

        String codedText = TextEncoder.encodeText(strText, letterCodes);

        ResultsDisplay.showResults(strText, codedText);
        
        scanUserInput.close();
    }

    // Node class to represent the nodes in the Huffman tree
    static class Node {
        int intFrequency;
        char charData;
        Node left;
        Node right;

        // Constructor for leaf nodes
        Node(int intFrequency, char charData) {
            this.intFrequency = intFrequency;
            this.charData = charData;
            this.left = null;
            this.right = null;
        }

        // Constructor for internal nodes
        Node(int intFrequency, Node left, Node right) {
            this.intFrequency = intFrequency;
            this.charData = '\0';
            this.left = left;
            this.right = right;
        }

        // Method to check if the node is a leaf node
        boolean hasNoChildren() {
            return left == null && right == null;
        }
    }

    // Method to count the frequency of each letter in the input string
    static class LetterCounter {
        public static Map<Character, Integer> countLetters(String strText) {

            System.out.println("\n===== STEP 1: Counting letters in the input string... =====");

            // Create a HashMap to store the frequency of each letter
            HashMap<Character, Integer> letterCount = new HashMap<>();
            for (int i = 0; i < strText.length(); i++) {
                char currentChar = strText.charAt(i);
                letterCount.put(currentChar, letterCount.getOrDefault(currentChar, 0) + 1);
            }

            System.out.println("Frequency of letters in the input string:");
            for (Map.Entry<Character, Integer> entry : letterCount.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }

            return letterCount;
        }
    }

    // Method to create tree nodes from the letter frequencies
    static class TreeNodeCreator {
        public static ArrayList<Node> createTreeNodes(Map<Character, Integer> letterCount) {
            System.out.println("\n===== STEP 2: Creating tree nodes from the letter frequencies... ====="); 
            ArrayList<Node> treeNodes = new ArrayList<>();

            // Iterate through the letterCount map and create a Node for each character
            for (Character charKey : letterCount.keySet()) {
                int intFrequency = letterCount.get(charKey);
                Node newNode = new Node(intFrequency, charKey);
                treeNodes.add(newNode);

                System.out.println("Created node for character '" + charKey + "' with frequency " + intFrequency);
            }

            return treeNodes;
        }
    }

    // Method to build the Huffman tree from the list of nodes
    static class TreeNodeBuilder {
        public static Node buildHuffmanTree(ArrayList<Node> treeNodes) {
            System.out.println("\n===== STEP 3: Building the Huffman tree... =====");
            int intStep = 1;

            // Handle edge case: single character
            if (treeNodes.size() == 1) {
                Node singleNode = treeNodes.get(0);
                // Create a dummy parent node to ensure the single character gets a code
                Node dummyParent = new Node(singleNode.intFrequency, singleNode, null);
                System.out.println("Single character detected. Creating dummy parent node.");
                return dummyParent;
            }

            while (treeNodes.size() > 1) {
                // Find the two nodes with the lowest frequency
                Node nodeLowestFrequency = findAndRemoveLowestFrequency(treeNodes);
                Node nodeSecondFrequency = findAndRemoveLowestFrequency(treeNodes);

                // Create a new internal node with these two nodes as children
                int intFrequencySum = nodeLowestFrequency.intFrequency + nodeSecondFrequency.intFrequency;
                Node newNode = new Node(intFrequencySum, nodeLowestFrequency, nodeSecondFrequency);

                // Print the merging step
                String leftChar = (nodeLowestFrequency.charData == '\0') ? "internal" : String.valueOf(nodeLowestFrequency.charData);
                String rightChar = (nodeSecondFrequency.charData == '\0') ? "internal" : String.valueOf(nodeSecondFrequency.charData);
                
                System.out.println("Step " + intStep + ": Merging nodes '" + leftChar + "' and '" 
                                   + rightChar + "' with frequencies " 
                                   + nodeLowestFrequency.intFrequency + " and " 
                                   + nodeSecondFrequency.intFrequency + " to create a new node with frequency " 
                                   + intFrequencySum);

                treeNodes.add(newNode);
                intStep++;
            }
            // Return the root node of the Huffman tree
            return treeNodes.get(0);
        }

        // Method to find and remove the node with the lowest frequency
        private static Node findAndRemoveLowestFrequency(ArrayList<Node> treeNodes) {
            Node nodeLowestFrequency = treeNodes.get(0);
            int lowestIndex = 0;

            // Find the node with the lowest frequency
            for (int i = 1; i < treeNodes.size(); i++) {
                Node currentNode = treeNodes.get(i);
                if (currentNode.intFrequency < nodeLowestFrequency.intFrequency) {
                    nodeLowestFrequency = currentNode;
                    lowestIndex = i;
                }
            }

            // Remove and return the node with the lowest frequency
            treeNodes.remove(lowestIndex);
            return nodeLowestFrequency;
        }
    }

    // Method to generate the Huffman codes for each character
    static class HuffmanCodeGenerator {
        public static Map<Character, String> generateHuffmanCodes(Node root, Map<Character, Integer> letterCount) {
            System.out.println("\n===== STEP 4: Generating Huffman codes... =====");
            
            Map<Character, String> huffmanCodes = new HashMap<>();
            
            // Handle edge case: single character
            if (letterCount.size() == 1) {
                Character singleChar = letterCount.keySet().iterator().next();
                huffmanCodes.put(singleChar, "0");
                System.out.println("Single character '" + singleChar + "' assigned code: 0");
                return huffmanCodes;
            }
            
            generateCodesRecursive(root, "", huffmanCodes);
            return huffmanCodes;
        }

        // Recursive method to generate Huffman codes
        private static void generateCodesRecursive(Node node, String code, Map<Character, String> huffmanCodes) {
            if (node == null) return;
            
            if (node.hasNoChildren()) {
                // If it's a leaf node, store the code for the character
                huffmanCodes.put(node.charData, code.isEmpty() ? "0" : code);
                System.out.println("Character '" + node.charData + "' has Huffman code: " + (code.isEmpty() ? "0" : code));
                return;
            }
            // Traverse left and right children
            if (node.left != null) {
                generateCodesRecursive(node.left, code + "0", huffmanCodes);
            }
            if (node.right != null) {
                generateCodesRecursive(node.right, code + "1", huffmanCodes);
            }
        }
    }

    // Class to handle Step 5: Converting text to codes
    static class TextEncoder {
        public static String encodeText(String userText, Map<Character, String> letterCodes) {
            System.out.println("\n=== STEP 5: CONVERTING TEXT TO CODES ===");
            StringBuilder codedText = new StringBuilder();
            
            System.out.println("Original text: " + userText);
            System.out.println("Converting each letter:");
            
            // Convert each letter to its code
            for (int i = 0; i < userText.length(); i++) {
                char currentLetter = userText.charAt(i);
                String codeForLetter = letterCodes.get(currentLetter);
                codedText.append(codeForLetter);
                
                String displayLetter = (currentLetter == ' ') ? "SPACE" : String.valueOf(currentLetter);
                System.out.println("  " + displayLetter + " → " + codeForLetter);
            }
            
            System.out.println("\nFinal coded text: " + codedText.toString());
            return codedText.toString();
        }
    }

    // Class to show the final results
    static class ResultsDisplay {
        public static void showResults(String userText, String codedText) {
            System.out.println("\n=== RESULTS ===");
            
            // Calculate sizes
            int originalSize = userText.length() * 8; // Each letter normally uses 8 bits
            int compressedSize = codedText.length();
            
            // Show the comparison
            System.out.println("Original text: \"" + userText + "\"");
            System.out.println("Coded text: " + codedText);
            System.out.println("Original text size: " + originalSize + " bits (" + userText.length() + " letters × 8 bits each)");
            System.out.println("Compressed text size: " + compressedSize + " bits");
            
            if (compressedSize < originalSize) {
                int spaceSaved = originalSize - compressedSize;
                double percentSaved = (double) spaceSaved / originalSize * 100;
                System.out.println("Space saved: " + spaceSaved + " bits (" + String.format("%.1f", percentSaved) + "%)");
            } else {
                System.out.println("No space saved (text too short for compression)");
            }
        }
    }
}