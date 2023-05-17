package LSTM;
public class Calculations {
    
    public Calculations() {
        // Constructor
    }
    
    public double tanh(double val) {
        double e = 2.718281828459045;
       
        return (Math.pow(e, val) - Math.pow(e, -val)) / (Math.pow(e, val) + Math.pow(e, -val));
    }
    
    public double sigmoid(double val) {
        double e = 2.718281828459045;
        return 1 / (1 + Math.pow(e, -val));
    }
    
    public double sigmoidDer(double val) {
        double val1 = sigmoid(val);
        return val1 * (1 - val1);
    }
    
    public double tanhDer(double val) {
        double temp = tanh(val);
        return 1 - Math.pow(temp, 2);
    }

	
}