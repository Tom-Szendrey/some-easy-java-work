import java.io.Serializable;
/**
 * a class to hold information for a pizza.
 * 
 * @author Tom Szendrey, 14TJS5, 10187030
 * @version 2.0
 */
public class Pizza implements Serializable{

	private static final long serialVersionUID = 1106552350974423638L;
	private String size; 
	private int cheese;
	private int ham;
	private int pepperoni;
	/**
	 * Full parameter constructor.
	 * @param size The size of the pizza. (small, medium, large)
	 * @param cheese The amount of cheese put onto the pizza, (single, double, triple given as 1,2,3)
	 * @param ham The amount of ham put onto the pizza (non, single, double, triple given as 0,1,2,3)
	 * @param pepperoni The amount of pepperoni put onto the pizza (non, single, double, triple given as 0,1,2,3)
	 * @throws IllegalPizza If args are not legal.
	 */
	public Pizza(String size, int cheese, int ham, int pepperoni) throws IllegalPizza{
		setSize(size);
		setCheese(cheese);
		setMeats(ham, pepperoni);
	}//end Pizza (main constructor)
	
	/**
	 * Single parameter constructor.
	 * @param size The size of the pizza. (small, medium, large)
	 * @throws IllegalPizza if size is not legal.
	 */
	public Pizza(String size) throws IllegalPizza{
		this(size,1,0,1);
	}//end Pizza (default constructor)
	
	/**
	 * Sets the size of the pizza.
	 * @param size The size of the pizza. (small, medium, large)
	 * @throws IllegalPizza if the size is not legal
	 */
	private void setSize(String size) throws IllegalPizza {
		if (size.equalsIgnoreCase("small") | size.equalsIgnoreCase("medium") | size.equalsIgnoreCase("large")){
			this.size = size;
		}
		else{
			throw new IllegalPizza("Illegal size: " + size);
		}
	}//ends setSize
	
	/**
	 * Sets the amount of cheese on the pizza.
	 * @param cheese The amount of cheese put onto the pizza, (single, double, triple given as 1,2,3)
	 * @throws IllegalPizza if the cheese amount is not legal.
	 */
	private void setCheese(int cheese) throws IllegalPizza{
		 if (cheese < 0 || cheese > 3){
			 throw new IllegalPizza("Illegal cheese count: " + cheese);
		 }
	     this.cheese = cheese;
	}//ends setCheese
	
	/**
	 * Sets the amount of pepperoni, and ham on the pizza.
	 * Together ham and pepperoni cannot be more than 3, and neither can be less than 0.
	 * @param ham ham The amount of ham put onto the pizza (non, single, double, triple given as 0,1,2,3)
	 * @param pepperoni The amount of pepperoni put onto the pizza (non, single, double, triple given as 0,1,2,3)
	 * @throws IllegalPizza If the amounts of pepperoni, or ham, or pepperoni + ham are not legal.
	 */
	private void setMeats(int ham, int pepperoni) throws IllegalPizza{
		 if ((ham < 0) | (pepperoni < 0) | (ham + pepperoni > 3)){
			 throw new IllegalPizza("Illegal meat counts");
		 }
	     this.ham = ham;
	}//ends setMeats
	
	/**
	 * used to get the cost of the pizza.
	 * @return the cost.
	 */
	public double getCost(){
		double price;
		if (this.size.equalsIgnoreCase("small")){
			price = 7.0;
		}
		else if(this.size.equalsIgnoreCase("medium")){
			price = 9.0;
		}
		else{
			price = 11.0;
		}
		price = price + (1.5 * (this.cheese - 1)) + (1.5 * this.ham) + (1.5 * this.pepperoni);
		return price;
	}//ends getCost()
	
	/**
	 * A string representation of the current object.
	 * @return String which represents the current object.
	 */
	public String toString(){
		String pizzaInfo = "";
		//Following adds info on size
		if (size.equalsIgnoreCase("small")){
			pizzaInfo += "small pizza";
		}else if(size.equalsIgnoreCase("medium")){
			pizzaInfo += "medium pizza";
		}else{
			pizzaInfo += "large pizza";
		}
		//Following for pizza's with only cheese:
		if (ham == 0 && pepperoni == 0){
			if (cheese == 1){
				pizzaInfo += "single cheese only";
			}else if (cheese == 2){
				pizzaInfo += "double cheese only";
			}else{
				pizzaInfo += "triple cheese only";
			}
		}else{
			//Following adds info about cheese
			if (cheese == 1){
				pizzaInfo += ", single cheese";
			}else if (cheese == 2){
				pizzaInfo += ", double cheese";
			}else{
				pizzaInfo += ", triple cheese";
			}
			//Following adds info on ham
			if (ham == 1){
				pizzaInfo += ", single ham";
			}
			if (ham == 2){
				pizzaInfo += ", double ham";
			}
			if (ham == 3){
				pizzaInfo += ", triple ham";
			}
			//Following adds info on pepperoni
			if (pepperoni == 1){
				pizzaInfo += ", single pepperoni";
			}
			if (pepperoni == 2){
				pizzaInfo += ", double pepperoni";
			}
			if (pepperoni == 3){
				pizzaInfo += ", triple pepperoni";
			}
		}
		//Following adds the cost text
		pizzaInfo += ".  Cost: $" + getCost() + "0 each.";
		return pizzaInfo;
	}//ends toString
	
	
	
	/**
	 * Used to check if a pizza object is the same as another object.
	 * Being the same is defined as having all the same attributes.
	 * @param otherObject is the other object given to compare with the current object.
	 * @return true or false depending on if the given object is the same as the current object.
	 */
	@Override 
	public boolean equals(Object otherObject){
		//Make sure that the object supplied is type Pizza
		if (otherObject instanceof Pizza){
			Pizza otherPizza = (Pizza)otherObject;
			if (size.equalsIgnoreCase(otherPizza.size) && (cheese == otherPizza.cheese) && (ham == otherPizza.ham) && (pepperoni == otherPizza.pepperoni)){
				return true; //if all of the attributes are the same between the 2 classes return true
			}
			return false; //if its of type Pizza, but all the attributes are not the same return false.
		}
		return false; //if type is not Pizza, return false
	}//ends equal()
	
	/**
	 * Returns a copy of the of the current Pizza object.
     * @return A copy of the current object.
	 */
	@Override
	public Pizza clone(){
		Pizza pizzaCopy = null;
        try {
            pizzaCopy = new Pizza(size, cheese, ham, pepperoni);
        } catch (IllegalPizza e) {
        	System.out.println("HOW DID YOU GET HERE?!?!?!?!?! YOURE IMPRESSIVE GOLD STAR"); //TESTING
        	return null;
        } // end try/catch
        return pizzaCopy;
	}//end clone
}// class end

