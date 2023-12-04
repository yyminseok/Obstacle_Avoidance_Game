package Dodge;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.sql.*;
import java.util.Scanner;
import com.sun.net.httpserver.*;


public class Server {
    private final String DEFAULT_HOSTNAME = "0.0.0.0";
    private final int DEFAULT_PORT = 8080;
    private final int DEFAULT_BACKLOG = 0;
    private HttpServer server = null;
    
    /**
     * 생성자
     */
    public Server() throws IOException {
    	
        Server Server = null;
        
        
        Server = new Server("localhost", 3000);
        Server.start(Server);
     
    }
    public Server(int port) throws IOException {
        createServer(DEFAULT_HOSTNAME, port);
    }
    public Server(String host, int port) throws IOException {
        createServer(host, port);
    }
    public static ResultSet data() {
		Connection conn;
	      Statement stmt = null;
	      Scanner scanner =new Scanner(System.in);
	      try {
	         Class.forName("com.mysql.cj.jdbc.Driver");
	         conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ranking", "root", "arche");
	         System.out.println("DB 연결 완료");
	         stmt = conn.createStatement();
	         
	         String sql = "select * from game_rank order by score desc limit 10;";
	         ResultSet srs = stmt.executeQuery(sql);
	         return srs;
	      } catch (ClassNotFoundException e) {
		         System.out.println("JDBC 드라이버 로드 에러");
		      } catch (SQLException e) {
		         System.out.println("SQL 실행 에러");
		      }
		return null;
	}
    /**
     * 서버 생성
     */
    private void createServer(String host, int port) throws IOException {
        // HTTP Server 생성
        this.server = HttpServer.create(new InetSocketAddress(host, port), DEFAULT_BACKLOG);
        // HTTP Server Context 설정
        server.createContext("/", new RootHandler());
    }
    
    /**
     * 서버 실행
     */
    public void start(Server a) {
        a.server.start();
    }
    
    /**
     * 서버 중지
     */
    public void stop(int delay) {
        server.stop(delay);
    }
    
    public static void main(String[] args) throws IOException {
        
    }
 
    /**
     * Sub Class
     */
    class RootHandler implements HttpHandler {    // 웹에서 실행되는 시각적인 것을 위한 클래스
    	Connection conn; // 데이터베이스 연결
		Statement stmt = null; // 상태
        @Override
        public void handle(HttpExchange exchange) throws IOException { // 웹이 켜지면 자동 실행
            
            // Initialize Response Body
            OutputStream respBody = exchange.getResponseBody();
            ResultSet srs=data();
            int last_score=0;
            int rank_num=0;
            int same_rank=1;

            try {
            	
				String sql = "select gameid from game_rank;";
                // Write Response Body
                StringBuilder sb = new StringBuilder();
                sb.append("<!DOCTYPE html>");
                sb.append("<html>");
                sb.append("   <head>");
                sb.append("<style>");
                sb.append("p {background: linear-gradient(to right, #ffa7a3, #5673bd); padding: 0.43em 1em; font-size: 19px; border-radius: 3px; color: #ffffff;}");
                sb.append("table {width: 800px; border-top: 1px solid #444444; border-collapse: separate;}");
                sb.append("td,th { padding:10px; text-align: center; border-bottom:1px solid #444444; }");
                sb.append("th {width: 150px; padding: 10px; font-weight: bold; vertical-align: top; color: #fff; background: #e7708d; margin: 20px 10px;}");            
                sb.append("td {background-color: #ffffff;}"); //홀수 일 시
                sb.append("th.a,td.a {background-color: #fdf3f5;}"); // 짝 수면 바꾸기 색상
                sb.append("</style>");
                sb.append("       <meta charset=\"UTF-8\">");
                sb.append("       <meta name=\"author\" content=\"Dochi\">");
                sb.append("       <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
                sb.append("       <title>Game Rank</title>");
                sb.append("   </head>");
                sb.append("   <body>");
                sb.append(" <span><p>랭킹 Top 10</p></span>");
                sb.append("<div>");
                sb.append("<table>");
                sb.append("<tr><th> 랭킹 </th> <th> id </th> <th> 점수 </th> <th> 레벨 </th> </tr>");
                while(srs.next()) {
                    int class_num=srs.getInt(1);
                    String id=srs.getString(2);
                    int score=srs.getInt(3);
                    int level=srs.getInt(4);
                    
                    if(last_score != score) { //점수가 이전과 다를 떄
                       rank_num+=same_rank;
                       System.out.print(last_score);
                       same_rank=1;
                    }
                    else { //점수가 같을 때
                       same_rank+=1;
                    }
                    
                    if(rank_num%2==0) {
                       sb.append("<tr> <td class=\"a\" >"+rank_num+"</td class=\"a\">"+"<td class=\"a\">"+id+"</td class=\"a\">"+"<td class=\"a\">"+Integer.toString(score)+"</td class=\"a\">"+"<td class=\"a\" >"+Integer.toString(level)+"</td class=\"a\"></tr>");
                    }
                    else {
                       sb.append("<tr><td>"+rank_num+"</td>"+"<td>"+id+"</td>"+"<td>"+Integer.toString(score)+"</td>"+"<td>"+Integer.toString(level)+"</td> </tr>");
                    }
                    last_score=score;
                    }

                sb.append("</table>");
                sb.append("</div>");
                sb.append("   </body>");
                sb.append("</html>");
                
                // Encoding to UTF-8
                ByteBuffer bb = Charset.forName("UTF-8").encode(sb.toString()); // sb안에 것들을 string으로 바꾼 후 UTF-8 적용하여 bytebuffer에 저장
                int contentLength = bb.limit();
                byte[] content = new byte[contentLength];
                bb.get(content, 0, contentLength);
                
                // Set Response Headers
                Headers headers = exchange.getResponseHeaders();
                headers.add("Content-Type", "text/html;charset=UTF-8");
                headers.add("Content-Length", String.valueOf(contentLength));
                
                // Send Response Headers
                exchange.sendResponseHeaders(200, contentLength);
                respBody.write(content);
                
                // Close Stream
                // 반드시, Response Header를 보낸 후에 닫아야함
                respBody.close();
                
            } catch ( Exception e ) {
                e.printStackTrace();
                
                if( respBody != null ) {
                    respBody.close();
                }
            } finally {
                exchange.close();
            }
        }
    }
}