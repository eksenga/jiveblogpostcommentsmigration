package com.yookos.jiveblogpostcommentsmigration;

public class Comment {

	private long commentId;
	private long parentCommentId;
	private long objecttype;
	private long objectid;
	private String parentObjectType;
	private long parentObjectId;
	private long userId;
	private String name;
	private String email;
	private String url;
	private String ip;
	private String body;
	private String creationdate;
	private String modificationdate;
	private String status;
	public long getCommentId() {
		return commentId;
	}
	public void setCommentId(long commentId) {
		this.commentId = commentId;
	}
	public long getParentCommentId() {
		return parentCommentId;
	}
	public void setParentCommentId(long parentCommentId) {
		this.parentCommentId = parentCommentId;
	}
	public long getObjecttype() {
		return objecttype;
	}
	public void setObjecttype(long objecttype) {
		this.objecttype = objecttype;
	}
	public long getObjectid() {
		return objectid;
	}
	public void setObjectid(long objectid) {
		this.objectid = objectid;
	}
	public String getParentObjectType() {
		return parentObjectType;
	}
	public void setParentObjectType(String parentObjectType) {
		this.parentObjectType = parentObjectType;
	}
	public long getParentObjectId() {
		return parentObjectId;
	}
	public void setParentObjectId(long parentObjectId) {
		this.parentObjectId = parentObjectId;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getCreationdate() {
		return creationdate;
	}
	public void setCreationdate(String creationdate) {
		this.creationdate = creationdate;
	}
	public String getModificationdate() {
		return modificationdate;
	}
	public void setModificationdate(String modificationdate) {
		this.modificationdate = modificationdate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
