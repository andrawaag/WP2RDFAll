/* CVS $Id: $ */
 
import com.hp.hpl.jena.rdf.model.*;
 
/**
 * Vocabulary definitions from http://geovocab.org/geometry.rdf 
 * @author Auto-generated by schemagen on 11 Jan 2012 16:07 
 */
public class Geometry {
    /** <p>The RDF model that holds the vocabulary terms</p> */
    private static Model m_model = ModelFactory.createDefaultModel();
    
    /** <p>The namespace of the vocabulary as a string</p> */
    public static final String NS = "http://geovocab.org/geometry#";
    
    /** <p>The namespace of the vocabulary as a string</p>
     *  @see #NS */
    public static String getURI() {return NS;}
    
    /** <p>The namespace of the vocabulary as a resource</p> */
    public static final Resource NAMESPACE = m_model.createResource( NS );
    
    /** <p>Represents a bounding box composed by four line segments.</p> */
    public static final Resource BoundingBox = m_model.createResource( "http://geovocab.org/geometry#BoundingBox" );
    
    /** <p>Super-class grouping all geometrical representations.</p> */
    public static final Resource Geometry = m_model.createResource( "http://geovocab.org/geometry#Geometry" );
    
    /** <p>Super-class grouping all composite geometrical representations.</p> */
    public static final Resource GeometryCollection = m_model.createResource( "http://geovocab.org/geometry#GeometryCollection" );
    
    /** <p>Represents a series of points connected by straight lines.</p> */
    public static final Resource LineString = m_model.createResource( "http://geovocab.org/geometry#LineString" );
    
    /** <p>Represents a series of points connected by straight lines, which form a closed 
     *  shape. Last point must be the same as the first point.</p>
     */
    public static final Resource LinearRing = m_model.createResource( "http://geovocab.org/geometry#LinearRing" );
    
    /** <p>Describes a geometric shape composed of several LineString resources.</p> */
    public static final Resource MultiLineString = m_model.createResource( "http://geovocab.org/geometry#MultiLineString" );
    
    /** <p>Describes a collection of Point resources, which define a resource's geometric 
     *  representation.</p>
     */
    public static final Resource MultiPoint = m_model.createResource( "http://geovocab.org/geometry#MultiPoint" );
    
    /** <p>Describes a geometric shape composed of several Polygon resources.</p> */
    public static final Resource MultiPolygon = m_model.createResource( "http://geovocab.org/geometry#MultiPolygon" );
    
    /** <p>A closed area defined by an exterior boundary, and optionally one or more 
     *  interior boundaries.</p>
     */
    public static final Resource Polygon = m_model.createResource( "http://geovocab.org/geometry#Polygon" );
    
}
