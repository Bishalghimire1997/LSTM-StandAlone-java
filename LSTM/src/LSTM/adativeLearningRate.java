package LSTM;
//Objective: to decide learning rate for our LSTM 
//Currently used: BGD(  Batch Gradient Descent) i.e. newWeight=newWeight-learningRate*Gradient.
//The range of value to consider for learning rate is [1,10^-6]----------> Best Practice
//smaller rate requires more training epoch and vice versa. 
//Best practice---------> to gradually decrease learning rate over time. (THIS IS IN CONTRARY TO WHAT WE HAVE IMPLEMENTED)

//EXPERIMENT 1----------------->Updating Learning over epochs only


public class adativeLearningRate {
	
	public double scheduler(double perc)
	{  double learningRate=1;
		if (perc < 15) {
			
            learningRate = learningRate / 100;
        } else if (15 < perc && perc <30) {
            learningRate = learningRate / 200;
        } else if (30 < perc && perc < 45) {
            learningRate = learningRate / 400;
        } else if (45 < perc && perc < 60) {
            learningRate = learningRate / 500;
        } else if (60 < perc && perc < 75) {
            learningRate = learningRate / 600;
            }
            else {
            learningRate = learningRate / 700;
        }
		return learningRate;
		
	}
double adagradOptimizer(double globalLearningRate,double localLearningRate,double gradient,int i)
{
	//The idea is to record all the gradients---------> sum the square of gradients---> divide a global learning rate by square root of this sum
	//Here, we will not record the gradient but compute the squared sum of gradient by dividing the global learning rate by previous local learning rate and square this ratio.
	//learning rate=globalLearningRate/(sqrt(i**2) for i in gradients)
	//we need current gradient, current learning rate,and global learning Rate
	
	
	if (i==0) {
	localLearningRate=globalLearningRate/Math.pow(Math.pow(gradient, 2),0.5);
	//System.out.println(localLearningRate);
	return localLearningRate; // This should return local learning rate
	
	}
	
	else
	{
		double temp1=globalLearningRate/localLearningRate;
		double temp2=Math.pow(temp1, 2);
		double temp3=temp2+Math.pow(gradient, 2);
		if (temp3==0) {
			return localLearningRate; // This should return local learning rate
		}
		else {
		localLearningRate=globalLearningRate/Math.pow(temp3, 0.5);
		//System.out.println(localLearningRate);
		return localLearningRate;  // This should return local learning rate
		}
		
	}
	
	
	
	
	
}
private void admmOptimizer()
{
	
}
}
