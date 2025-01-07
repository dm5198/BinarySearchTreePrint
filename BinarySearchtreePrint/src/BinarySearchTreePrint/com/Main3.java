package hw3.com;

public class Main3 {
    public static void main(String[] args) {
        String[] inputs = {
            "CBDAE",
            "DACBEFMLGHJK",
            "JABCDEFISN"
        };
        
        for (int k = 0; k < inputs.length; k++) {
            MyAVLTreeMap<String, Integer> mytree = new MyAVLTreeMap<>();
            
            // Populate the tree
            for (int i = 0; i < inputs[k].length(); i++) {
                mytree.put(inputs[k].substring(i, i + 1), 1);
            }
            
            System.out.println("Input: " + inputs[k]);
            
            // Call the printTree method
            boolean verbose = false;
            mytree.printTree(verbose);
            
            System.out.println();
        }
    }
}


