package lab9;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.StringTokenizer;

import net.datastructures.AdjacencyMapGraph;
import net.datastructures.Edge;
import net.datastructures.Graph;
import net.datastructures.Map;
import net.datastructures.Vertex;

import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Iterator;

import java.util.*;


public class SimpleGraph {
  Graph<String,String> sGraph;  
  

  /** 
   * Create a SimpleGraph from file
   */
  public SimpleGraph( String fileName ) 
    throws Exception, IOException {
    sGraph = new AdjacencyMapGraph<String,String>(false);
    read( fileName );
  }


  /**
   * Read a list of edges from file
   */
  protected void read( String fileName ) 
    throws Exception, IOException {
    BufferedReader graphFile = 
      new BufferedReader( new FileReader(fileName));
    
    Hashtable<String,Vertex> vertices = new Hashtable<String, Vertex>();
    // Create a hash map to store all the vertices read
  
    
    // Read the edges and insert
    String line;
    while( ( line = graphFile.readLine( ) ) != null ) {
      StringTokenizer st = new StringTokenizer( line );
      if( st.countTokens() != 2 ) 
	throw new IOException("Incorrect input file at line " 
				    + line );
      String source = st.nextToken( );
      String dest = st.nextToken( );
      Vertex<String> sv = vertices.get( source );
      if ( sv == null ) {
	// Source vertex not in graph -- insert
	sv = sGraph.insertVertex(source); 
	vertices.put( source, sv );
      } 
      Vertex<String> dv = vertices.get( dest );
      if ( dv == null ) {
	// Destination vertex not in graph -- insert
	dv = sGraph.insertVertex(dest); 
	vertices.put( dest, dv );
      }
      // check if edge is already in graph
      if ( sGraph.getEdge( sv, dv )==null) {
	// edge not in graph -- add 
	sGraph.insertEdge(sv, dv, source + " to " + dest ); 
      }
    }
  }

  /**
   * Helper routine to get a Vertex (Position) from a string naming
   * the vertex
   */
  protected Vertex<String> getVertex( String vert ) {
    // Go through vertex list to find vertex -- why is this not a map
    for( Vertex<String> vs : sGraph.vertices() ) {
      if ( vs.getElement().equals( vert )) {
	return vs;
      }
    }
    return null;
  }


  Hashtable<String,Boolean> visited;

  public void printDFS( String vert  ) {
    Vertex<String> vt = getVertex( vert );
    visited = new Hashtable<>();
    DFS(this.sGraph,vt);
    return;
  }
	
	
  private void DFS(Graph<String,String> graph, Vertex<String> v ) {
	  
    /***** please implement depth-first search here ************/
	  if(visited.get(v.getElement())!=null)
		  return;
	  visited.put(v.getElement(), Boolean.TRUE);
	  startVisit(v);
	  for(Edge<String> e : graph.outgoingEdges(v)) {
		  Vertex<String> v6 = graph.opposite(v,e);
		  DFS(graph, v6);
	  }
	  finishVisit(v);
	  return;
  }
  
  private void startVisit( Vertex<String> v ) {
    System.out.println( v.getElement() );
  }
  private void finishVisit( Vertex<String> v ) {
    System.out.println( "---" +  v.getElement() );
  }

  /**
   * Printing all the vertices in the list, followed by printing all
   * the edges
   */
  void print() {
    System.out.println( "Vertices: " + sGraph.numVertices() + 
			" Edges: " + sGraph.numEdges()); 
    
    for( Vertex<String> vs : sGraph.vertices() ) {
      System.out.println( vs.getElement() );
    }
    for( Edge<String> es : sGraph.edges() ) {
      System.out.println( es.getElement() );
    }
    return;
  }


  /** Helper method:
   * Read a String representing a vertex from the console
   */
  public static String readVertex() throws IOException {
    System.out.print( "[Input] Vertex: " );
    BufferedReader reader = 
      new BufferedReader(new InputStreamReader ( System.in ));
    return reader.readLine();
  }

  /**
   * Generate a Graph from File and prints the vertices visited
   * by a DepthFirstSearch
   */
  public static void main( String[] argv ) {
    if ( argv.length < 1 ) {
      System.err.println( "Usage: java SimpleGraph fileName" );
      System.exit(-1);
    }
    try {
      SimpleGraph sGraph = new SimpleGraph( argv[0] );
      sGraph.print();
      // Ask for vertex to start
      System.out.println( "Start Vertex for DFS:");
      sGraph.printDFS(readVertex());
    }catch ( Exception except ) {
      System.err.println(except);
      except.printStackTrace();
    }
  }
}
