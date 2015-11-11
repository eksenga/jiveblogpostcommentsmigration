package com.yookos.jiveblogpostcommentsmigration;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

import static com.yookos.jiveblogpostcommentsmigration.PostgresUtils.*;

public class Main {
	
	static final String KEYSPACE = "socialgraph";
	
	static final String YOOKOS = "jdbc:postgresql://192.168.10.225:5432/yookos";
	static final String YOOKOS_PASSWORD = "postgres";
	static final String YOOKOS_USERNAME = "postgres";
	//
	static final String UAA = "jdbc:postgresql://10.10.10.227:5432/uaa";
	static final String UAA_PASSWORD = "postgres";
	static final String UAA_USERNAME = "postgres";
	
	static Statement statement;
	static Connection connection;
	static Session session;
	
	static Connection uaaConnection;
	static Statement uaaStatement;
	
	int[] blogPostIds = {27163};
	List<Comment> comments = new ArrayList<Comment>();
    		
	void process() {
		
		for (int i = 0; i < blogPostIds.length; i++) {
//			BlogPost blogPost = getBlogPost(blogPostIds[i]);
//			List<Comment> blogPostComments = getBlogComments(blogPost.getBlogId());
//			BlogPost blogPost = getBlogPost(blogPostIds[i]);
			List<Comment> blogPostComments = getBlogComments(blogPostIds[i]);
			insert(blogPostComments);
		}
	}
	
	void insert(List<Comment> blogPostComments) {
		
		for (Comment blogPostComment : blogPostComments) {
			System.out.println(String.format("Comment ID: %s %s", blogPostComment.getCommentId()
															 , blogPostComment.getBody()));
		}
	}
	
	List<Comment> getBlogComments(long objectId) {
		
		if (connection == null) {
			if (isPostgresDriverLoaded()) { 
				connection = openPostgresConnection(YOOKOS, YOOKOS_USERNAME, YOOKOS_PASSWORD);
				try {
					statement = connection.createStatement();
				} 
				catch (SQLException e) {
					e.printStackTrace();
				}
			}
			else {
				throw new AssertionError("Failed to load POSTGRES Driver.");
			}
		}
		try {
			java.sql.ResultSet rs = statement.executeQuery(String.format("SELECT * FROM jivecomment WHERE objectid=%s LIMIT 50;", objectId));
			while (rs.next()) {
				if (!isExistingUser(rs.getLong("userId"))) {
					continue;
				}
				Comment comment = new Comment();
				//TODO 
				comment.setBody(rs.getString("body"));
				comment.setCommentId(rs.getLong("commentId"));
				//comment.setCreationdate(rs.getLong("creationdate"));
				comment.setEmail(rs.getString("email"));
				comment.setIp(rs.getString("ip"));
				//comment.setModificationdate(rs.getLong("modificationdate"));
				comment.setName(rs.getString("name"));
				comment.setObjectid(rs.getLong("objectid"));
				comments.add(comment);
	        }
	        //rs.close();
		}    
		catch (SQLException e) {
			e.printStackTrace();
		} 
		return comments;
	}
	
	static Connection openPostgresConnection(String url, String username, String password) {
			
		try {
			connection = DriverManager.getConnection(url, username, password);
			return connection;
		} 
		catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return null;
		}
	}
	
	BlogPost getBlogPost(long blogPostId) {
		
		ResultSet rs = getSession().execute("");
		BlogPost blogPost = new BlogPost();
		for (Row row : rs) {
			blogPost.setBlogId(row.getLong("blogid"));
			blogPost.setBlogPostId(row.getLong("firstname"));
			blogPost.setBody(row.getString("body"));
			//TODO blah blah blah
		}
		return blogPost;
	}

	boolean isExistingUser(long userId) {
		
		if (uaaConnection == null) {
			if (isPostgresDriverLoaded()) { 
				uaaConnection = openPostgresConnection(UAA, UAA_USERNAME, UAA_PASSWORD);
				try {
					uaaStatement = uaaConnection.createStatement();
				} 
				catch (SQLException e) {
					e.printStackTrace();
				}
			}
			else {
				throw new AssertionError("Failed to load POSTGRES Driver.");
			}
		}
		try {
			java.sql.ResultSet rs = uaaStatement.executeQuery(String.format("SELECT * FROM legacyusers WHERE userid= %s;", userId));
			while (rs.next()) {
				return true;
	        }
			rs.close();
		}    
		catch (SQLException e) {
			e.printStackTrace();
		} 
		return false;
	}
	
	Session getSession() {
		
		if (session != null) {
			return session;
		}
		try {
			Cluster cluster = Cluster.builder().addContactPoint("192.168.10.201")
						                       .withCredentials("cassandra", "cassandra")
						                       .build();
			Session session = cluster.connect(KEYSPACE);
			return session;
		} 
		catch (Exception e) {
			e.printStackTrace();
		}	
		return null;
	}
	
	public static void main(String[] args) {
		
		new Main().process();
	}
}
