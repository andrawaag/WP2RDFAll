
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


import org.apache.commons.io.FileUtils;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFList;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.sparql.vocabulary.FOAF;
import com.hp.hpl.jena.vocabulary.DC;
import com.hp.hpl.jena.vocabulary.DCTerms;
import com.hp.hpl.jena.vocabulary.OWL;
import com.hp.hpl.jena.vocabulary.RDF;
import com.hp.hpl.jena.vocabulary.RDFS;
import com.hp.hpl.jena.vocabulary.VCARD;
import com.hp.hpl.jena.vocabulary.XSD;


public class Waagmeester2RDF {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Reader reader = new InputStreamReader(new FileInputStream("/tmp/FamilyMembers.tsv"), "utf-8");
			BufferedReader readbuffer = new BufferedReader(reader);
		    Model model = ModelFactory.createDefaultModel();
		    String strRead;
		    while ((strRead=readbuffer.readLine())!=null){
		    	String splitarray[] = strRead.split("\t");
		    	String fullName = splitarray[0];
		    	String namearray[] = fullName.split(", ");
		    	String lastName = namearray[0];
		    	String firstName = namearray[1];
		    	String dobString = splitarray[1];
		    	Date dob;
		    	if (dobString.equals("zz-zz-zzzz")){
		    		dob = null;
		    	} else {
		    	    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy"); //please notice the capital M
		    	    dob = formatter.parse(dobString);
		    	}
		    	

		    	String place = splitarray[2];
		    	String position = splitarray[3];
		    	position = position.replace("℗", "P");
		    	System.out.println(fullName + " " + dob + " " + place + " " + position);

		    	String[]  familyPosition = position.split("");

		    	
		    	Resource familyMember = model.createResource("http://www.waagmeester.net/Familie/"+position+"/");
		    	//System.out.println(familyPosition[familyPosition.length-1]);
		    	if (familyPosition[familyPosition.length-1].equals("x")){
		    		
		    	} else
		    	   if (familyPosition[familyPosition.length-1].equals("P")) {
		    		String RelatedPartner = position.substring(0, familyPosition.length-2);
		    		System.out.println(RelatedPartner);
		    		Resource partnerResource = model.createResource("http://www.waagmeester.net/Familie/"+RelatedPartner);
		    		familyMember.addProperty(relations.spouseOf, partnerResource);
		    	}
		    	else {
		    	String parent = position.substring(0, familyPosition.length-2); 
		    	Resource parentResource = model.createResource("http://www.waagmeester.net/Familie/"+parent);
		    	Resource parent2Resource = model.createResource("http://www.waagmeester.net/Familie/"+parent+"P");
		    	familyMember.addProperty(relations.childOf, parentResource);
		    	familyMember.addProperty(relations.childOf, parent2Resource);
		    	System.out.println(parent);
		    	}
		    	
		    	
		    	familyMember.addProperty(RDF.type, FOAF.Person);
		    	familyMember.addLiteral(FOAF.firstName, firstName);
		    	familyMember.addLiteral(FOAF.family_name, lastName);
		    	
		    	

		    	if (dob != null) {
		    		familyMember.addLiteral(FOAF.birthday, dob);
		    		familyMember.addLiteral(VCARD.BDAY, dob);
		    	}
		    	}

		    readbuffer.close();
			FileOutputStream fout;
			fout = new FileOutputStream("/tmp/Waagmeester" + ".rdf");
			model.write(fout);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
