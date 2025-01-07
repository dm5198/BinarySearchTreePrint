# AVL Tree Visualization Project 

## Project Overview
This project involves constructing and visualizing an **AVL Tree**, a self-balancing binary search tree, using Java. The goal is to represent the tree structure textually on the console while maintaining clarity and accuracy. The project emphasizes understanding recursion, debugging, and adapting provided code to achieve the desired output.

The task requires implementing a `printTree` method in the `MyAVLTreeMap` class, which outputs a clear textual representation of the tree, including node positions and symbolic relationships such as arrows (`\` and `/`) for branches.

---

## Key Features
1. **Tree Construction**:
   - Uses a provided main class (`ProgProject4.java`) to build and manipulate AVL Trees.
   - The `MyAVLTreeMap` class is responsible for handling tree logic and visualization.

2. **Tree Visualization**:
   - Outputs the tree structure to the console, displaying nodes at their appropriate levels.
   - Uses arrows (`\` and `/`) to represent relationships between parent and child nodes.
   - Ensures no collisions (overlapping nodes) in the visual output.

3. **Predefined and Custom Trees**:
   - Includes three predefined trees as part of the starter code.
   - Adds at least two additional custom trees created by the student to demonstrate flexibility and testing.

4. **Encapsulation of Logic**:
   - Tree construction and printing are modularized, making it easy to test and debug specific components.

---

## Objectives
The primary goals of this project are:
- To gain hands-on experience with **AVL Tree** data structures and their operations.
- To master recursion and iterative logic for solving tree-related problems.
- To develop debugging skills by ensuring accurate node placement in the visualization.
- To practice analyzing problems and implementing efficient solutions within existing code frameworks.

---

## Implementation Details

### **Tree Representation**
- Nodes in the AVL Tree have:
  - A **key**, represented by a letter from the input.
  - A **value**, which is always `1` (irrelevant for this project).
- The structure is balanced automatically by the AVL Tree logic provided in the starter code.

### **Tree Printing**
The `printTree` method is implemented in the `MyAVLTreeMap` class and follows these principles:
1. **2D Array Representation**:
   - A 2D array (e.g., `100 x 100`) is used to temporarily hold node keys for output.
   - Each cell represents a character in the final textual representation of the tree.

2. **Recursive Placement**:
   - A helper method (`printTree1`) is used to place nodes recursively into the array.
   - Nodes are placed at their respective levels, and arrows (`\` and `/`) are added to indicate branches.

3. **Console Output**:
   - After populating the 2D array, the tree is printed to the console row by row, creating a textual visualization of the AVL Tree.

### **Example Workflow**
1. **Input**:
   - The program receives input defining the tree, e.g., `P1(A);P2(B);P3(B,C);P4(C);P5(A,B,C)`.

2. **Tree Construction**:
   - The main class builds the AVL Tree from the input and calls the `printTree` method.

3. **Output**:
   - The console displays a textual representation of the tree:
     ```
        A
       / \
      B   C
         / \
        D   E
     ```

---

## Approach and Methodology
### **Suggested Approach**
1. **printTree Method**:
   - Initialize a 2D array to store node keys.
   - Call the `printTree1` method with parameters for the root node and starting position.
   - After populating the array, iterate through it row by row to print the tree.

2. **printTree1 Method**:
   - Store the current node's key in the array at the given position.
   - Recursively call `printTree1` for the left and right children, adjusting their positions based on their depth and relationship to the parent.

---

## Running the Program
1. Import the provided project files into your Java IDE (e.g., Eclipse).
2. Use the `ProgProject4.java` class to run the program.
3. The program will:
   - Build three predefined AVL Trees.
   - Construct and display two additional custom trees.
   - Output the trees to the console using the `printTree` method.

---

## Example Outputs
**Tree Input**: `P1(A);P2(B);P3(B,C);P4(C);P5(A,B,C)`
**Tree Visualization**:
```
        A
       / \
      B   C
         / \
        D   E
```

---

## Notes and Recommendations
- The program relies on modifying the `printTree` method and supporting methods in the `MyAVLTreeMap` class.
- Use recursion effectively to manage node placement and avoid overlapping nodes in the output.
- Test the program with various inputs to ensure the tree visualization remains accurate for all cases.
- Ensure that all project files are zipped and submitted as required.

This project offers a deep understanding of AVL Trees and recursive programming while honing debugging and problem-solving skills.
