package com.yookos.jiveblogpostcommentsmigration;

public class BlogPost {

	private long blogPostId;
	private long blogId;
	private long userId;
	private String subject;
	private String permalink;
	private String body;
	private String status;
	private String commentstatus;
	private long publishdate;
	private long creationdate;
	private long modificationdate;
	private short minoredit;
	
	public long getBlogPostId() {
		return blogPostId;
	}
	public void setBlogPostId(long blogPostId) {
		this.blogPostId = blogPostId;
	}
	public long getBlogId() {
		return blogId;
	}
	public void setBlogId(long blogId) {
		this.blogId = blogId;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getPermalink() {
		return permalink;
	}
	public void setPermalink(String permalink) {
		this.permalink = permalink;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCommentstatus() {
		return commentstatus;
	}
	public void setCommentstatus(String commentstatus) {
		this.commentstatus = commentstatus;
	}
	public long getPublishdate() {
		return publishdate;
	}
	public void setPublishdate(long publishdate) {
		this.publishdate = publishdate;
	}
	public long getCreationdate() {
		return creationdate;
	}
	public void setCreationdate(long creationdate) {
		this.creationdate = creationdate;
	}
	public long getModificationdate() {
		return modificationdate;
	}
	public void setModificationdate(long modificationdate) {
		this.modificationdate = modificationdate;
	}
	public short getMinoredit() {
		return minoredit;
	}
	public void setMinoredit(short minoredit) {
		this.minoredit = minoredit;
	}
	
	
}
