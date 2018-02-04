

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Servlet implementation class ControlerServlet
 */
@WebServlet("/ControlerServlet")
public class ControlerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String zipcode = request.getParameter("zipcode");

		String apiurl = "http://zipcloud.ibsnet.co.jp/api/search?zipcode=" + zipcode	;
		URLConnection urlConnection = null;
		URL url = new URL(apiurl);

		urlConnection =  url.openConnection();
		urlConnection.connect();

		 InputStream content = (InputStream) urlConnection.getContent();
		 BufferedReader reader = new BufferedReader(new InputStreamReader(content));

		 String line ;
		 StringBuilder builder = new StringBuilder();

		 while((line = reader.readLine())!=null){
			 builder.append(line);
		 }


		 String jsonString = builder.toString();

		 System.out.println(jsonString.toString());
		// JsonFactory jFactory = new JsonFactory();
		//com.fasterxml.jackson.core.JsonParser parser = jFactory.createParser(jsonString);
		//ObjectMapper mapper = new ObjectMapper();
		//JsonNode json = null;
		//json = mapper.readTree(parser);
		// System.out.println(json.textValue());
		 ObjectMapper mapper = new ObjectMapper();
		 ZipcodeDTO dto =  mapper.readValue(jsonString, ZipcodeDTO.class);
		 System.out.println(dto.getResults().get(0).getAddress1());


	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
