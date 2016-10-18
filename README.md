# Decision-Tree-Parsing
Generating individual class paths from Decision Tree model

###Usage:
1. Create a Decision Tree model using 'DT_ImageClassification' scala class. Decision tree model will be saved to 'data/DT.txt'.

2. Then using 'PathGeneration' class, set desired label/class for the path generation. All the paths in Decision Tree are saved to 'data/output.txt'.

3. Replace the content of 'model' method inside Testing class. Then use a Training data point to test.


###Implementation:
Modified 'Node' and 'DecisionTreeModel' scala classes for the generated model output to be compatiable with Java syntax. Then based on the leaf nodes which are predictors implemented logic to get only the desired class paths
