/**
 * The WebShop class is the main class of the program.
 * Refactoring a web shop program with Design Patterns (clean code)
 * *
 * Design Patterns and Algorithms used in this program are as follows (VG requirements):
 *
 * 01. Builder Pattern on Customer class 
 * (Customer object is created in the Database class using Builder pattern)
 * 02. Proxy Pattern on DatabaseProxy class　
 * 03. Command Pattern on WebShop class (No Switch statements or else-if statements). 
 *    - Command interface, -CommandHandler class
 *    - Command classes: all kinds of commands (e.g. SortMenuDisplayCommand, RegisterActionCommand)　
 * 04. Merge Sort algorithm on mergeSort method in WebShop class 
 *
 * Info: There seemed to be some logical errors or typos here and there in the original code which took me a while to figure out.
 * One of them is that the purchase menu can be interchanged with wares menu, so I replaced purchase menu with wares menu.
 * @author Megumi Kogo
 */
