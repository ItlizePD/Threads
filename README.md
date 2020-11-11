# Threads

- Create two threads, one of which outputs 1-52 and the other outputs A-Z.Output format requirements: 12A 34B 56C 78D
- Please use the synchronized block, wait(), notify() and notifyAll() methods provided by Java to implement three concurrently executing threads 1, 2, and 3 to output a string sequence such as ABCABC...ABC (ABC repeats 10 in the sequence Times), where thread 1 is responsible for printing out letter A, thread 2 is responsible for printing out letter B, and thread 3 is responsible for printing out letter C.
- Assuming that the garage has 3 parking spaces (the garage can be represented by the boolean[] array), write a program to simulate the effect of multiple users driving away and parking into the garage.Note: You cannot park when there is a car in the parking space.
(hint: each thread represents a car, and use main funciton to create new threads and run them, you can limit the total number of cars to be created)
