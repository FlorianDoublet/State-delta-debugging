# State-delta-debugging
Don't forget that you need Maven to run the project.
All the work made is just some exploration / suposition. Feel free about talking with @FlorianDoublet to know more about it. There is still some BIG issues like how to retrieve vars value in runtime, because Spoon seems to only be a static analyser ... 
Currently, Main launch the MarkupChallenge, and we only see that with the first input it's a fail and with the second one it's good.
Then Spoon will print all the variable he found in our class, and their type. But their is no correlation with the input given and the Spoon print, beause Spoon only analyse our static code and compile it, but never run the challenge.
