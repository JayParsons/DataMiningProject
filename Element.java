/*class Element
  Written by: Jason Parsons and Alex Gordon
  This class contains fields and methods for creating an element object
  and setting and getting its' fields. This is used for storing all of the
  elements read in from a file in the main program.
 */

public class Element {
	private String elementName;
	private String attributeName;
	private int rowNumber;
	private int columnNumber;

	public Element() {}  //default constructor

	//constructor
	public Element(String i, String a, int r, int c) {
		elementName = i;
		attributeName = a;
		rowNumber = r;
		columnNumber = c;
	}

	//set methods
	public void setElementName(String i) {elementName = i;}
	public void setAttributName(String a) {attributeName = a;}
	public void setRowNumber(int r) {rowNumber = r;}
	public void setColumnNumber(int c) {columnNumber = c;}

	//get methods
	public String getElementName() {return elementName;}
	public String getAttributeName() {return attributeName;}
	public int getRowNumber() {return rowNumber;}
	public int getColumnNumber() {return columnNumber;}
}
