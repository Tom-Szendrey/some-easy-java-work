import java.io.Serializable;

/**
 * This class is used to store information on a specific pizza order.
 * <p>
 * The stored information is what is on the pizza in the form of a Pizza object, 
 * and how many of these specific pizzas are ordered
 * <p> 
 * NOTE TO MARKER: Im assuming that you want us to reuse IllegaePizza for our LineItem
 * @author Tom Szendrey, 14TJS5, 10187030
 * @version 1.0
*/
public class LineItem implements Serializable{

	private static final long serialVersionUID = 1078413470469503298L;
	//set class attributes
	private Pizza typeOfPizza;
	private int amountOfPizzas;
	
	/**
	 * Full parameter constructor. 
	 * @param typeOfPizza a type of pizza given as an object of type Pizza
	 * @param amount an integer, containing how many of these pizza are ordered.
	 * @throws IllegalPizza if the arguments are invalid.
	 */
	public LineItem(int amount, Pizza typeOfPizza) throws IllegalPizza{
		setAmount(amount);
	}//LineItem
	
	/**
	 * Single parameter constructor 
	 * @param typeOfPizza a type of pizza given as an object of type Pizza
	 * @throws IllegalPizza if the argument are invalid
	 */
	public LineItem(Pizza typeOfPizza) throws IllegalPizza{
		this(1,typeOfPizza);
	}//end default constructor
	
	/**
	 * Sets the amount of pizza's ordered
	 * @param amount the amount of pizza's ordered
	 * @throws IllegalPizza if the amount is not 1-100 inclusive
	 */
	public void setAmount(int amount) throws IllegalPizza{
		if (amount < 1 | amount > 100){
			throw new IllegalPizza("Illegal amount of order's " + amount);		
		}//end if
		amountOfPizzas = amount;	
	}//end setAmount

	/**
	 * returns the current pizza object to the user.
	 * @return object of type Pizza
	 */
	public Pizza getPizza(){
		return typeOfPizza;
	}//end getPizza
	
	/**
	 * returns the current amount of pizzas ordered to the user
	 * @return an integer containing the amount of pizzas ordered.
	 */
	public int getAmount(){
		return amountOfPizzas;
	}//end getAmount
	
	/**
	 * Returns the total order cost of the pizza, and the amount of pizzas bought.
	 * @return a double containing the amount that the customer much pay for their order
	 */
	public double orderCost(){
		double cost = (typeOfPizza.getCost())*amountOfPizzas;
		return cost;
	}//end orderCost
	
	/**
	 * A String representation of the current object.
     * @return A String representation of the contents of the object containing the values of object
	 */
	@Override
	public String toString(){
		String orderInfo = amountOfPizzas + " orders, " + typeOfPizza.toString();
		return orderInfo;
	}//ends toSring
	
	/**
	 * Compares 2 objects of type LineItem based on how many pizzas have been ordered
	 * @param otherLI is the other object of type LineItem
	 * @return returns a double containing the difference between the current object's total price, and the supplied arguement's total price. 
	 */
	public double compareTo(LineItem otherLI){
		return this.orderCost() - otherLI.orderCost();
	}//end compareTo
}
