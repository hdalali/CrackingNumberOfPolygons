

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

/**
 * Servlet implementation class Calculate
 */
@WebServlet("/Calculate")
public class Calculate extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static int siz=4;
	public static int siz1=5;
	public static int siz2=6;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Calculate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		int[][] graph = {{0, 1, 1, 0},{1, 0, 1, 1},{1, 1, 0, 1},{0, 1, 1, 0}};
//		int[][] graphDirected = {{0, 0, 1, 0},{1, 0, 1, 1},{0, 0, 0, 0},{0, 0, 1, 0}};
//		int[][] graphQuad = {{0, 1, 1, 0, 1},{1, 0, 1, 1,1},{1, 1, 0, 1,0},{0, 1, 1, 0,1},{1, 1, 0, 1,0}};
//		int[][] graphPent = {{0, 1, 0, 0, 1, 0},{1, 0, 1, 0, 1, 0},{0, 1, 0, 1, 0, 1},{0, 0, 1, 0, 1, 1},{1, 1, 0, 1, 0, 0},{0, 0, 1, 1, 0, 0}};
//		int[][] graphPent1 = {{0, 1, 0, 0, 1, 0},{1, 0, 1, 0, 0, 0},{0, 1, 0, 1, 0, 1},{0, 0, 1, 0, 1, 1},{1, 0, 0, 1, 0, 0},{0, 0, 1, 1, 0, 0}};
//		
		/*System.out.println("Total number of Triangle in Undirected-Graph: "+ countTrianglesUndir(graph));
		System.out.println("Total number of Triangle in Directed-Graph: "+ countTrianglesDir(graphDirected));
		System.out.println("Total number of Quadrilateral in Undirected-Graph: "+ countQuad(graphQuad));
		System.out.println("Total number of Pentagon in Undirected-Graph Example 1: "+ countPent(graphPent));
		System.out.println("Total number of Pentagon in Undirected-Graph Example 2: "+ countPent(graphPent1));*/
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String fileName = "/home/hdalali/workspace/TermProject/src/ex1.csv";
		//System.out.println(getServletContext().getRealPath("/home/hdalali/workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp1/wtpwebapps/TermProject/fileName"));
		File file = new File(fileName);
		
		
		File input = new File("/home/hdalali/workspace/TermProject/src/ex1.csv");
        File output = new File("/home/hdalali/workspace/TermProject/WebContent/myGraph1.json");
        HashSet<String> cities = new HashSet<>();

        ObjectNode data = readObjectsFromCsv(input, cities);
        writeAsJson(data, output);
		
		Map<Integer,ArrayList<Integer>> hm = new HashMap<>();
        int keyMax=0,valueMax=0,totalMax=0;
        ArrayList<String> ans = new ArrayList<>();
		try {
			Scanner sc = new Scanner(file);
			sc.next();
			while (sc.hasNext()) {
				String mydatas = sc.next();
				String[] values = mydatas.split(",");
				int key = Integer.parseInt(values[0]);
				int value = Integer.parseInt(values[1]);
				hm.putIfAbsent(key, new ArrayList<Integer>());
				hm.get(key).add(value);
			}
			sc.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		for(Entry<Integer, ArrayList<Integer>> entry:hm.entrySet()){
			int key = entry.getKey();
		    ArrayList<Integer> values = entry.getValue();
		    if(key>keyMax) keyMax=key;
		    for(int i=0;i<values.size();i++){
		    	if(values.get(i)>valueMax) valueMax=values.get(i);
		    	ans.add(key+","+values.get(i));
		    }
		}
		
		totalMax=Math.max(keyMax,valueMax);
		System.out.println("max is "+ totalMax);
		int[][] my_graph=new int[totalMax][totalMax];
		
		for(int i=0;i<ans.size();i++){
			String[] lr = ans.get(i).split(",");
			//System.out.println(lr[0]+",,,"+lr[1]);
			int left= Integer.parseInt(lr[0]);
			int right= Integer.parseInt(lr[1]);
			my_graph[left-1][right-1] = 1; 
			my_graph[right-1][left-1] = 1;
		}
		
		for(int i=0;i<my_graph.length;i++){
			for(int j=0;j<my_graph[0].length;j++){
				System.out.print(my_graph[i][j]+ " ");
			}
			System.out.println();
		}
		
		//request.getRequestDispatcher("/ans.jsp").include(request, response);
		
		String res = "", answer = "";
		System.out.println(request.getParameter("ind"));
		if(request.getParameter("ind")!= null){
			 res=request.getParameter("opt");
			 answer="";
			 //System.out.println(res);
			if(res.equals("dit")){
				siz=totalMax;
				request.setAttribute("answer", "Total number of Triangle in Directed-Graph: "+ countTrianglesDir(my_graph));
			}
			
			if(res.equals("ut")){
				siz=totalMax;
				request.setAttribute("answer","Total number of Triangle in Un-Directed-Graph: "+ countTrianglesUndir(my_graph));	
			}
			
			if(res.equals("quad")){
				siz1=totalMax;
				request.setAttribute("answer","Total number of Quadrilateral in Directed-Graph: "+ countQuad(my_graph));	
			}
			
			if(res.equals("pent")){
				siz2=totalMax;
				request.setAttribute("answer","Total number of Pentagon in Directed-Graph: "+ countPent(my_graph));
			}
		}
		System.out.println(answer);
		request.getRequestDispatcher("/ans.jsp").forward(request, response);
	}
	
	public static int countTrianglesDir(int matrix[][]){
	    int count = 0;
	    for(int i = 0; i < siz; i++){
	        for(int j = 0; j < siz; j++){
	                for(int k = 0; k < siz; k++){
	                    if(matrix[i][j]==1 && matrix[j][k]==1 && matrix[k][i]==1){
	                        count++;
	                    }
	                }
	            }
	        }
	    return count/3;
	    }
	
	public static int countTrianglesUndir(int matrix[][]){
	    int count = 0;
	    for(int i = 0; i < siz; i++){
	        for(int j = 0; j < siz; j++){
	                for(int k = 0; k < siz; k++){
	                    if(matrix[i][j]==1 && matrix[j][k]==1 && matrix[k][i]==1){
	                        count++;
	                    }
	                }
	            }
	        }
	    return count/6;
	    }
	
	public static int countQuad(int matrix[][]){
	    int count = 0;
	    for(int i = 0; i < siz1; i++){
	        for(int j = 0; j < siz1; j++){
	                for(int k = 0; k < siz1; k++){
	                	for(int l=0; l<siz1; l++){
	                    if(matrix[i][j]==1 && matrix[j][k]==1 && matrix[k][l]==1 && matrix[l][i]==1){
	                        count++;
	                    }
	                	}
	                }
	            }
	        }
	    return count/24;
	    }
	
	public static int countPent(int matrix[][]){
	    int count = 0;
	    for(int i = 0; i < siz2; i++){
	        for(int j = 0; j < siz2; j++){
	                for(int k = 0; k < siz2; k++){
	                	for(int l=0; l<siz2; l++){
	                		for(int m=0; m<siz2; m++){
	                			if(matrix[i][j]==1 && matrix[j][k]==1 && matrix[k][l]==1 && matrix[l][m]==1 && matrix[m][i]==1){
	                				count++;
	                			}
	                		}
	                	}
	                }
	            }
	        }
	    return count/60;
	    }
	
	public static ObjectNode readObjectsFromCsv(File file, HashSet<String> cities) throws IOException {

        CsvSchema bootstrap = CsvSchema.emptySchema().withHeader();
        CsvMapper csvMapper = new CsvMapper();
        ArrayNode arrayNode = csvMapper.createArrayNode();
        ArrayNode sourceNode = csvMapper.createArrayNode();
        MappingIterator<Map<?, ?>> mappingIterator = csvMapper.reader(Map.class).with(bootstrap).readValues(file);
  
        while(mappingIterator.hasNext()){
            ObjectNode jNode = csvMapper.convertValue(mappingIterator.next(), ObjectNode.class);
//            objectNode.put(csvMapper.convertValue(mappingIterator.next(), JsonNode.class));
        System.out.println(jNode.toString()); 
           String src = jNode.get("source").toString();
           String dest = jNode.get("destination").toString();

           src = src.substring(1, src.length()-1);
           dest = dest.substring(1, dest.length()-1);
           jNode.put("target", dest);
           jNode.put("id", src+dest);
         //  jNode.put("label", src+dest);
           jNode.put("type", "arrow" );
           jNode.put("size", 50);
            if(!cities.contains(src)){
                ObjectNode xNode = csvMapper.createObjectNode();

                xNode.put("id", src);
                xNode.put("label", src);
                xNode.put("x", 4 + (int)(Math.random() * ((20 - 4) + 1)));
                xNode.put("y", 4 + (int)(Math.random() * ((16) + 1)));
                xNode.put("size", 1);
                xNode.put("color","#ff000000" );
                cities.add(src);

//                sourceNode.add(csvMapper.convertValue(jNode.get("source"), JsonNode.class));
                sourceNode.add(xNode);

            }
            if(!cities.contains(dest)){
                ObjectNode xNode = csvMapper.createObjectNode();

                xNode.put("id", dest);
                xNode.put("label", dest);
                xNode.put("x", 4 + (int)(Math.random() * ((20 - 4) + 1)));
                xNode.put("y", 4 + (int)(Math.random() * ((16) + 1)));
                cities.add(dest);

               // sourceNode.add(csvMapper.convertValue(jNode.get("destination"), JsonNode.class));
                sourceNode.add(xNode);

            }
            jNode.remove("destination");
            arrayNode.add(jNode);
//            Object[] s =  map.get(map.size()-1).values().toArray();
          /*  if(!cities.contains(s[0].toString()))
                cities.add(s[0].toString());*/
        }
        ObjectNode obNode =(ObjectNode) csvMapper.createObjectNode().set("edges", arrayNode);
        obNode.set("nodes", sourceNode);

        System.out.println(obNode);
        /*
        mappingIterator = csvMapper.reader(Map.class).with(bootstrap).readValues(file);

        return mappingIterator.readAll();
        */
        new ObjectMapper().writeValue(new File("/home/hdalali/workspace/TermProject/WebContent/myGraph1.json"), obNode);
        return obNode;
    }

    public static void writeAsJson(ObjectNode data, File file) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(file, data);
    }
}
