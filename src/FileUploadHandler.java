import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;





@WebServlet("/FileUploadHandler")
@MultipartConfig
public class FileUploadHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ArrayList<String> city_list = new ArrayList<String>();
	int cityValueToaAssigned = 0;
	private final String UPLOAD_DIRECTORY = "/home/hdalali/workspace/termProject2/src";

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {


		
		HttpSession	session = request.getSession();
		String id = session.getId();
		Part filePart = request.getPart("file");
		request.setAttribute("filePart", filePart);

		String stringLine = null;
		
		HashSet<String> city_list = new HashSet<String>();
		String[] lineArray = new String[3];
		HashMap<String, ArrayList<String[]> > map = new HashMap<String, ArrayList<String[]> >();
		int edge = 0;
		ObjectNode obNode=null;

		String fileName = null;
		if(filePart == null)
			System.out.println("why file part is null?");
		
		String partHeader = filePart.getHeader("content-disposition");
		for (String cd : partHeader.split(";")) {
			if (cd.trim().startsWith("filename")) {
				fileName = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
			}
		}

		if(fileName == null)   
			fileName = id;
		else{
			fileName += "-"+id +".csv";
		}
		BufferedReader bufferedReader;

		CsvMapper csvMapper = new CsvMapper();	

		ArrayNode arrayNode = csvMapper.createArrayNode();

		ArrayNode sourceNode = csvMapper.createArrayNode();

		try{
			bufferedReader = new BufferedReader(new InputStreamReader(filePart.getInputStream()));
			bufferedReader.readLine();
			while ((stringLine = bufferedReader.readLine()) != null) {
				edge++;
				ObjectNode jNode = csvMapper.createObjectNode();
				lineArray = stringLine.split(",");

				String src = lineArray[0] = lineArray[0].trim();
				String dest = lineArray[1] = lineArray[1].trim();
				String weight = lineArray[2] = lineArray[2].trim();

				src = src.trim();
				dest = dest.trim();
				weight = weight.trim();
				jNode.put("id", src+dest);
				if(request.getParameter("gtype").equalsIgnoreCase("dig")){
					jNode.put("type", "arrow");
				}
             
				jNode.put("source", src);
				jNode.put("target", dest);

				int cityValue = checkCityValueAssigned(src);

				if (!city_list.contains(src) ) {
					city_list.add(src); 
					map.put(src, new ArrayList<String[]>());

					ObjectNode xNode = csvMapper.createObjectNode();

					xNode.put("id", src);
					xNode.put("label", src);
					xNode.put("x", Math.random()*4+1);
					xNode.put("y", Math.random()*4+1);
					xNode.put("size",1);
					xNode.put("color", "#ff0000");
					sourceNode.add(xNode);


				}

				int val = checkCityValueAssigned(dest);
				if (!city_list.contains(dest)) {
					city_list.add(dest);
					map.put(dest, new ArrayList<String[]>());

					ObjectNode xNode = csvMapper.createObjectNode();

					xNode.put("id", dest);
					xNode.put("label", dest);
					xNode.put("x", Math.random()*4+1);
					xNode.put("y", Math.random()*4+1);
					xNode.put("size",(int) Math.random()*2 + 1);
					xNode.put("color", "#ff0000");
					sourceNode.add(xNode);

				}


				map.get(lineArray[0]).add(new String[]{lineArray[1], lineArray[2]});
				map.get(lineArray[1]).add(new String[]{lineArray[0], lineArray[2]});
				arrayNode.add(jNode);

			}

			obNode =(ObjectNode) csvMapper.createObjectNode().set("edges", arrayNode);
			obNode.set("nodes", sourceNode);

			System.out.println(obNode);
		}
		catch(IOException e){
			System.out.println("Error while printing.");

		}

		
		String str = (obNode == null)?null:obNode.toString();
		session.setAttribute("json", str);
		session.setAttribute("data", obNode);
		
		
		request.setAttribute("fs","true");

		
		request.setAttribute("message", "File Uploaded Successfully");
		request.setAttribute("fileName", fileName);
		
		request.setAttribute("message",
				"Sorry this Servlet only handles file upload request");
		


		request.getRequestDispatcher("Calculate").forward(request, response);

	}

	private int checkCityValueAssigned(String string) {
		if(city_list.contains(string))
			return 1;
		return -1;
	}
}