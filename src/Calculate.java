

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

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
@MultipartConfig
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

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	 private String extractFileName(Part part) {
	        String contentDisp = part.getHeader("content-disposition");
	        String[] items = contentDisp.split(";");
	        for (String s : items) {
	            if (s.trim().startsWith("filename")) {
	                return s.substring(s.indexOf("=") + 2, s.length()-1);
	            }
	        }
	        return "";
	    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String fileName = "/home/hdalali/workspace/termProject2/src/macha.csv";
		
		HttpSession session = request.getSession();
		Part filePart = request.getPart("file");
		File file = new File(fileName);
		if(filePart == null)
			System.out.println("somehing is fishy");
		File input = new File("/home/hdalali/workspace/termProject2/src/macha.csv");
        File output = new File("/home/hdalali/workspace/termProject2/WebContent/myGraph1.json");
        HashSet<String> cities = new HashSet<>();
      
        String fName = extractFileName(filePart);
       
        fName = new File(fName).getName();
        filePart.write("/home/hdalali/workspace/termProject2/src/macha.csv");
        
		Map<Integer,ArrayList<Integer>> hm = new HashMap<>();
        int keyMax=0,valueMax=0,totalMax=0;
        ArrayList<String> ans = new ArrayList<>();
		try {
			Scanner sc = new Scanner(input);
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
			if(request.getParameter("gtype").equalsIgnoreCase("ug")){
				my_graph[right-1][left-1] = 1;
			}
		}
		
		for(int i=0;i<my_graph.length;i++){
			for(int j=0;j<my_graph[0].length;j++){
				System.out.print(my_graph[i][j]+ " ");
			}
			System.out.println();
		}
		session.setAttribute("graphSize", totalMax);
		session.setAttribute("generatedGraph", my_graph);
		System.out.println("Graph Type selected is"+request.getParameter("gtype"));
		request.getRequestDispatcher("/index.jsp").forward(request, response);
		
}
}
