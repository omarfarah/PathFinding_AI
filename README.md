# PathFinding_AI
###Path Finding 

Using the A* algorithm we are tasked to find the shortest path from one, or more robots to a final rendezvous point on a 2D map. The room will be a static environment where the robot must traverse through the map and find the final point without disturbing the surrounding obstacles.  

##Problem Formulation
Initial state: Robot’s starting position given by an (x,y) coordinate.

Actions: Robot moves left, right, up, down.

Transition Model: Depending on action, robot coordinate will change.

Goal Test: Check if robot is at the rendezvous point.

Path Cost: f(n)=h(n)+g(n). 

##Input Example
>8 10                 // the room has dimensions 8 by 10

>2                 // there are N = 2 robots

>2 1                 // 1st robot initial position: point (2,1)

>8 2                 // 2nd robot initial position: point (8,2) 

>4 7                // the rendezvous point R has coordinates (4,7)

>1000000001            // room points (0,7), (1,7), ... (9,7)

>1100000011

>0000000000

>1000110001

>1001111001

>0001111000

>0000110000             // room points (0,1), (1,1), ... (9,1)
>1100000011             // room points (0,0), (1,0), ... (9,0)

##A* Algorithm
The A* algorithm is a popular algorithm for pathfinding and graph traversal. It avoids expanding all possible paths which can be expensive. It uses the total estimated solution cost to determine each move towards the end node. The estimated solution cost is defined as:
 f(n)=g(n)+h(n) 
 
Where g(n) is the cost to reach the current node from the start node, h(n) is the estimated cost to get from the current node to the end node and f(n) is the estimated total cost of the cheapest solution. 
Path Finding Robot

For our artificial intelligence final project, we decided to implement a program to help a robot find a path to get to its desired destination. We have decided to use the A* algorithm and the language we will use to implement the project is Java. Currently we have 4 different classes:

Node class: A node is a position for the robot. It contains the h and g values from that node to the end node. In addition it stores the x and y positions of the maze board and if the node is distinguished as an obstacle or not.

Robot class: This class defines the robot’s current position 

Maze class: Defines the maze that the robot will traverse 

PathFinding class: Uses the Node, Robot and Maze class to see if the given maze has a solution for all the robots specified or not. 

