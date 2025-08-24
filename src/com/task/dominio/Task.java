package com.task.dominio;

import java.time.LocalDateTime;

import com.task.enums.Status;

public class Task {
	private static int contador = 0;
	private Long id;
	private String description;
	private Status status;
	private LocalDateTime createdAt;
	private LocalDateTime updateAt;
	
	public Task(String description, Status status, LocalDateTime createdAt, LocalDateTime updateAt) {
	
		this.id = (long) ++contador;
		this.description = description;
		this.status = status;
		this.createdAt = createdAt;
		this.updateAt = updateAt;
	}
	


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdateAt() {
		return updateAt;
	}

	public void setUpdateAt(LocalDateTime updateAt) {
		this.updateAt = updateAt;
	}

	@Override
	public String toString() {
		return "Task [id=" + id + ", description=" + description + ", status=" + status + ", createdAt=" + createdAt
				+ ", updateAt=" + updateAt + "]";
	}
	
	
	
	

}
