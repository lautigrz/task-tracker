package com.task.dominio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.stream.Collectors;

import com.task.enums.Status;

public class Tracker {
	private static final String ARCHIVO = "historial.json";
	private static ArrayList<Task> history;
	private final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	
	public Tracker() {
		history = new ArrayList<>();
	}
	
	public void addTask(Task task) throws IOException {
		history.add(task);
		saveHistory();
	}
	
	public Task updateTask(Long id, String description) throws IOException {
	
		for(Task task : history) {
			
			if(task.getId().equals(id)){
				task.setDescription(description);
				task.setUpdateAt(LocalDateTime.now());
				saveHistory();
				return task;
			}
			
		}
		
		return null;
	}
	
	public Boolean deleteTask(Long id) throws IOException {
		Boolean seElimino = history.removeIf(task -> task.getId().equals(id));
		
		if(seElimino) {
			saveHistory();
		}
		
		return seElimino;
		
	}
	
	public Boolean markTask(Long id,Status status) throws IOException {
		Boolean mark = false;
		for(Task task : history) {
			
			if(task.getId().equals(id)){
				task.setStatus(status);
				task.setUpdateAt(LocalDateTime.now());
				saveHistory();
				mark = true;
				break;
			}
			
		}
		
		return mark;
	}
	
	public ArrayList<Task> allTask(){
		return history;
	}
	
	
		
	public ArrayList<Task> taskDone(){
		ArrayList<Task> doneTasks = history.stream()
                .filter(task -> task.getStatus().equals(Status.DONE))
                .collect(Collectors.toCollection(ArrayList::new));

		
		return doneTasks;
	}
	
	public ArrayList<Task> taskPending(){
		ArrayList<Task> pendingTasks = history.stream()
                .filter(task -> task.getStatus().equals(Status.PENDING))
                .collect(Collectors.toCollection(ArrayList::new));

		
		return pendingTasks;
	}
	
	public ArrayList<Task> taskInProgress(){
		ArrayList<Task> inProgressTasks = history.stream()
                .filter(task -> task.getStatus().equals(Status.IN_PROGRESS))
                .collect(Collectors.toCollection(ArrayList::new));

		return inProgressTasks;
	}
	
	public void cargarHistorial() {
		File file = new File(ARCHIVO);
		if(!file.exists()) return;
		
		try(BufferedReader reader = new BufferedReader(new FileReader(file))) {
			String linea = "";
			Long id = 0L;
			String description = "";
			Status status = null;
			LocalDateTime createdAt = null;
			LocalDateTime updateAt = null;
			int control = 0;
			
			while((linea = reader.readLine()) != null) {
				
				
				linea = linea.trim();
				
				if(linea.startsWith("\"id\"")) {
					String valor = linea.split(":")[1].trim();  // "123",
							valor = valor.replace(",", "").replace("\"", ""); // quita comillas y coma final
							id = Long.parseLong(valor);
				} else if(linea.startsWith("\"description\"")) {
					String valor = linea.split(":")[1].trim();  // "123",
					valor = valor.replace(",", "").replace("\"", "");
					description = valor;
					
				}else if(linea.startsWith("\"status\"")) {
					String valor = linea.split(":")[1].trim();  // "123",
					valor = valor.replace(",", "").replace("\"", "");
					status = inStatus(valor);
				}else if(linea.startsWith("\"created\"")) {
					String valor = linea.split(":", 2)[1].trim(); // toma **todo después del primer ":"**
					valor = valor.replace(",", "").replace("\"", "");
					createdAt = convert(valor);

					
				}else if(linea.startsWith("\"update\"")) {
					String valor = linea.split(":", 2)[1].trim(); // toma **todo después del primer ":"**
					valor = valor.replace(",", "").replace("\"", "");
					updateAt = convert(valor);
					
					
						history.add(new Task(description,status,createdAt,updateAt));
					}
				}
				
				
			
			
			
			
		}catch(Exception e) {
			  System.out.println("Error al cargar historial: " + e.getMessage());
		}
	}
	
	
	
	private void saveHistory() throws IOException {
		
		try(BufferedWriter writer = new BufferedWriter(new FileWriter(ARCHIVO))) {
			
			writer.write("[\n"); 
			int i = 0;
			for (Task task : history) {
			    writer.write("{\n");
			    writer.write("    \"id\": \"" + task.getId() + "\",\n");
			    writer.write("    \"description\": \"" + task.getDescription().replace("\"", "\\\"") + "\",\n");
			    writer.write("    \"status\": \"" + task.getStatus().toString().replace("\"", "\\\"") + "\",\n");
			    writer.write("    \"created\": \"" + task.getCreatedAt().format(FORMATTER) + "\",\n");
			    writer.write("    \"update\": \"" + notUpdate(task.getUpdateAt()) + "\"\n"); // sin coma final
			    writer.write("}");
			    i++;
			    if (i < history.size()) {
			        writer.write(",\n"); 
			    } else {
			        writer.write("\n");
			    }
			}
			writer.write("]"); 

		}catch(Exception e){
			System.out.println("Error al guardar historial: " + e.getMessage());
		}
		
		
	}
	
	public String notUpdate(LocalDateTime localDateTime) {
		
		if(localDateTime == null) {
			return "Sin actualizar";
		}
		
		return localDateTime.format(FORMATTER);
		
	}
	
	
	private Status inStatus(String text) {
		
		return Status.valueOf(text.toUpperCase());
	}
	
	public LocalDateTime convert(String localDateTime) {
		
		if(localDateTime.equals("Sin actualizar")) {
			return null;
		}
		return LocalDateTime.parse(localDateTime, FORMATTER);
	}
	


}
