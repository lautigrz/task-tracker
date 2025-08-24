package com.task.main;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.task.dominio.Task;
import com.task.dominio.Tracker;
import com.task.enums.Status;

public class App {

	public static void main(String[] args) throws IOException {
	
		Tracker tracker = new Tracker();
		
		tracker.cargarHistorial();
		
		String comando = args[0];
		
		switch(comando) {
			
		case "add":
			addTask(tracker,args[1]);
			break;
		case "update":
			update(tracker, args[1], args[2]);
			break;
		case "delete":
			delete(tracker, args[1]);
			break;
		case "mark-done":
			markDone(tracker, args[1]);
			break;
		case "mark-pending":
			markPending(tracker, args[1]);
			break;
		case "mark-in-progress":
			markInProgress(tracker, args[1]);
			break;
		case "list":
			listAll(tracker);
			break;
		case "list-in-progress":
			listInProgress(tracker);
			break;
		case "list-done":
			listDone(tracker);
			break;
		case "list-peding":
			listPeding(tracker);
			break;
		default:
			mostrarPorPantalla("El comando " + comando + " no es correcto");
			break;
		}

	}
	
	
	private static void delete(Tracker tracker, String id) throws IOException {
		Long idLong = Long.parseLong(id); 
		Boolean seElimino = tracker.deleteTask(idLong);
		
		if(!seElimino) {
			mostrarPorPantalla("No se pudo eliminar la tarea, verifique el ID");
		}
	}


	private static void listInProgress(Tracker tracker) {
		List<Task> tasks = tracker.taskInProgress();
		
		if(tasks.isEmpty()) {
			mostrarPorPantalla("No se encontro ninguna tarea en progreso.");
		}else {
			for(Task task : tasks) {
				mostrarPorPantalla("ID:" + task.getId());
				mostrarPorPantalla("Descripcion:" + task.getDescription());
				mostrarPorPantalla("Estado:" + task.getStatus());
				mostrarPorPantalla("Creado:" + task.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
				mostrarPorPantalla("Actualizado:" + tracker.notUpdate(task.getUpdateAt()));
			}
		}
	}
	
	private static void listDone(Tracker tracker) {
		List<Task> tasks = tracker.taskDone();
		if(tasks.isEmpty()) {
			mostrarPorPantalla("No se encontro ninguna tarea hecha.");
		}else {
			for(Task task : tasks) {
				mostrarPorPantalla("ID:" + task.getId());
				mostrarPorPantalla("Descripcion:" + task.getDescription());
				mostrarPorPantalla("Estado:" + task.getStatus());
				mostrarPorPantalla("Creado:" + task.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
				mostrarPorPantalla("Actualizado:" + tracker.notUpdate(task.getUpdateAt()));
			}
		}
	}
	
	private static void listPeding(Tracker tracker) {
		List<Task> tasks = tracker.taskPending();
		if(tasks.isEmpty()) {
			mostrarPorPantalla("No se encontro ninguna tarea pendiente.");
		}else {
			for(Task task : tasks) {
				mostrarPorPantalla("ID:" + task.getId());
				mostrarPorPantalla("Descripcion:" + task.getDescription());
				mostrarPorPantalla("Estado:" + task.getStatus());
				mostrarPorPantalla("Creado:" + task.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
				mostrarPorPantalla("Actualizado:" + tracker.notUpdate(task.getUpdateAt()));
			}
		}
	}


	private static void listAll(Tracker tracker) {
		// TODO Auto-generated method stub
		List<Task> tasks = tracker.allTask();
		
		if(tasks.isEmpty()) {
			mostrarPorPantalla("No se encontro ninguna tarea en la lista.");
		}else {
			for(Task task : tasks) {
				mostrarPorPantalla("ID:" + task.getId());
				mostrarPorPantalla("Descripcion:" + task.getDescription());
				mostrarPorPantalla("Estado:" + task.getStatus());
				mostrarPorPantalla("Creado:" + task.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
				mostrarPorPantalla("Actualizado:" + tracker.notUpdate(task.getUpdateAt()));
			}
		}
		
	}


	private static void mostrarPorPantalla(String algo) {
		// TODO Auto-generated method stub
		System.out.println(algo);
		
	}


	private static void markInProgress(Tracker tracker, String id) throws IOException {
		Long idLong = Long.parseLong(id);
		
		Boolean seMarco = tracker.markTask(idLong, Status.IN_PROGRESS);
		
		if(!seMarco) {
			mostrarPorPantalla("No se encontro tarea para marcar como EN PROGRESO, verifique el ID.");
		}
		
	}


	private static void markPending(Tracker tracker, String id) throws IOException {
		Long idLong = Long.parseLong(id);
		
		Boolean seMarco = tracker.markTask(idLong, Status.PENDING);
		if(!seMarco) {
			mostrarPorPantalla("No se encontro tarea para marcar como PENDIENTE, verifique el ID.");
		}
		
	}


	private static void markDone(Tracker tracker, String id) throws IOException {
		Long idLong = Long.parseLong(id);
		
		Boolean seMarco = tracker.markTask(idLong, Status.DONE);
		
		if(!seMarco) {
			mostrarPorPantalla("No se encontro tarea para marcar como HECHA, verifique el ID.");
		}
		
	}


	private static void update(Tracker tracker, String id, String description) throws IOException {
		Task task = null;
		if(!description.isEmpty()) {
			Long idLong = Long.parseLong(id);
			task = tracker.updateTask(idLong, description);
		}
		
		if(task != null) {
			mostrarPorPantalla("Tarea actualizada");
			mostrarPorPantalla("");
			mostrarPorPantalla("ID:" + task.getId());
			mostrarPorPantalla("Descripcion:" + task.getDescription());
			mostrarPorPantalla("Estado:" + task.getStatus());
			mostrarPorPantalla("Creado:" + task.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
			mostrarPorPantalla("Actualizado:" + tracker.notUpdate(task.getUpdateAt()));
		}else {
			mostrarPorPantalla("No se encontro la tarea, verifique ID.");
		}
		
	}


	private static void addTask(Tracker tracker, String description) throws IOException {
		if(!description.isEmpty()) {
			Task task = new Task(description, Status.PENDING,LocalDateTime.now(),null);
			tracker.addTask(task);
		}
	}

}
