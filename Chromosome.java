/*class Chromosome
  Written by: Jason Parsons and Alex Gordon
  This class contains fields and methods for creating a Chromosome
  object. A Chromosome will contain any number of genes which
  represent mined or randomly inserted rules as well as a class label.
 */
import java.util.ArrayList;

public class Chromosome {
	private ArrayList<String> geneList;
	private String classLabel;
	
	public Chromosome() {}  //default constructor

	//constructor
	public Chromosome(String cl) {
		classLabel = cl;
	}

	//set methods
	public void setGeneList(String gene) {geneList.add(gene);}
	public void setClassLabel(String cl) {classLabel = cl;}
	
	//get methods
	public ArrayList<String> getGeneList() {return geneList;}
	public String getClassLabel() {return classLabel;}
}
