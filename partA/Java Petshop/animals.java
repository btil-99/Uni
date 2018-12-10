package petshop;

public class animals {
	private String givenName;
	private String commonName;
	private String price;
	private String sex;
	private String mainColour;
	private String arrivalDate;
	private String sellingDate;
	
	animals(String givenName, String commonName, String price, String sex, String mainColour, String arrivalDate, String sellingDate){
		this.givenName = givenName;
		this.commonName = commonName;
		this.price = price;
		this.sex = sex;
		this.mainColour = mainColour;
		this.arrivalDate = arrivalDate;
		this.sellingDate = sellingDate;
	}

	animals(String[] animalDetails){
		this(animalDetails[0], animalDetails[1], animalDetails[2], animalDetails[3], animalDetails[4], animalDetails[5], animalDetails[6]);
	}
	
	public String getGivenName() {
		return givenName;
	}
	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}
	
	public String getCommonName() {
		return commonName;
	}
	public void setCommonName(String commonName) {
		this.commonName = commonName;
	}
	
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	public String getMainColour() {
		return mainColour;
	}
	public void setMainColour(String mainColour) {
		this.mainColour = mainColour;
	}
	
	public String getArrivalDate() {
		return arrivalDate;
	}
	public void setArrivalDate(String arrivalDate) {
		this.arrivalDate = arrivalDate;
	}
	
	public String getSellingDate() {
		return sellingDate;
	}
	public void setSellingDate(String sellingDate) {
		this.sellingDate = sellingDate;
	}
}
