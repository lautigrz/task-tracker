# 📝 Task Tracker CLI (Java)

Un gestor de tareas simple hecho en **Java puro** que funciona desde la terminal.  
Permite crear, actualizar, listar y gestionar tareas con diferentes estados: **Pending**, **In Progress** y **Done**.

Practica backend sacada de [Roadmap.sh](https://roadmap.sh/projects/task-tracker "Task Tracker")


---

## ⚙️ Instalación

1. Clonar el repositorio:

```bash
git clone https://github.com/lautigrz/task-tracker.git
cd task-tracker
```
## ⚙️ Compilar proyecto

```bash
javac -d bin src\com\task\main\App.java src\com\task\dominio\*.java src\com\task\enums\*.java
```
## 🚀 Ejecutar
```bash
java -cp bin com.task.main.App <comando> [opciones]
```
## 💡 Recomendación: crear un alias en tu terminal para no escribir todo el comando cada vez:

```bash
alias task="java -cp bin com.task.main.App"
```

Luego podrás ejecutar solo con:
```bash
task <comando>
```
## 📌 Comandos disponibles
➕ Agregar una tarea
```bash
task add "Comprar leche"
```
## Agrega una nueva tarea con estado PENDING.

✏️ Actualizar una tarea
``` bash
task update <id> "Nueva descripción"
```

Ejemplo:

``` bash
task update 1 "Comprar pan"
```
## 🗑️ Eliminar una tarea
``` bash
task delete <id>
```

Ejemplo:
``` bash
task delete 1

```
## ✅ Marcar como hecha
``` bash
task mark-done <id>
```

Ejemplo:
``` bash
task mark-done 2
```

## ⏳ Marcar como pendiente
``` bash
task mark-pending <id>
```
## 🚧 Marcar como en progreso
``` bash
task mark-in-progress <id>
```

## 📋 Listar todas las tareas
``` bash
task list
```

## 📋 Listar tareas en progreso
``` bash
task list-in-progress
```

## 📋 Listar tareas completadas
``` bash
task list-done
```

## 📋 Listar tareas pendientes
``` bash
task list-peding
```

## 📂 Ejemplo de salida
``` bash
ID: 2
Descripcion: Comprar leche
Estado: PENDING
Creado: 2025-08-24 18:58:24
Actualizado: Sin actualizar

```

