package com.assigment.Guardrails.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor 
public class CommentRequest {
	private Long authorId;
	private String content;
	private Integer depthlevel;
	private boolean botComment;
	private Long humanId;
	private Long botId;
	public Long getAuthorId() {
		return authorId;
	}
	public void setAuthorId(Long authorId) {
		this.authorId = authorId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getDepthlevel() {
		return depthlevel;
	}
	public void setDepthlevel(Integer depthlevel) {
		this.depthlevel = depthlevel;
	}
	public boolean isBotComment() {
		return botComment;
	}
	public void setBotComment(boolean botComment) {
		this.botComment = botComment;
	}
	public Long getHumanId() {
		return humanId;
	}
	public void setHumanId(Long humanId) {
		this.humanId = humanId;
	}
	public Long getBotId() {
		return botId;
	}
	public void setBotId(Long botId) {
		this.botId = botId;
	}
	
}
