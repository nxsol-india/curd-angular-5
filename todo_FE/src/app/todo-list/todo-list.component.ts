import { Component, OnInit, TemplateRef } from '@angular/core';
import { Todo } from '../todo/todo';
import {NgForm} from '@angular/forms';
import { TodoService } from './todo.service';
import { BsModalService, BsModalRef } from 'ngx-bootstrap/modal';


@Component({
  selector: 'app-todo-list',
  templateUrl: './todo-list.component.html',
  styleUrls: ['./todo-list.component.css']
})

export class TodoListComponent implements OnInit {
  todos: Todo[];
  newTodo: Todo = new Todo();
  editing: boolean = false;
  editingTodo: Todo = new Todo();
  todoForm : NgForm;

  modalRef: BsModalRef;

  constructor(
    private todoService: TodoService,
    private modalService: BsModalService
  ) {}

  openModal(template: TemplateRef<any>, todo: Todo) {
    this.modalRef = this.modalService.show(template);
    this.editing = true;
    todo.createdAt = new Date(todo.createdAt);
    Object.assign(this.editingTodo, todo);
  }

  ngOnInit(): void {
    this.getTodos();
  }

  getTodos(): void {
    this.todoService.getTodos()
      .then(todos => this.todos = todos );    
  }

  createTodo(todoForm: NgForm): void {
    this.todoService.createTodo(this.newTodo)
      .then(createTodo => {        
        todoForm.reset();
        this.newTodo = new Todo();
        this.todos.unshift(createTodo)
      });
  }

  deleteTodo(id: string): void {
    this.todoService.deleteTodo(id)
    .then(() => {
      this.todos = this.todos.filter(todo => todo.id != id);
    });
  }

  selectedTodo : Todo[];

  deleteTodoSelected() {
    this.selectedTodo= this.todos.filter(_ => _.selected);  
    for (var todo1 in this.selectedTodo) {
      this.todoService.deleteTodos(this.selectedTodo[todo1].id)
        .subscribe(data =>{
         console.log(data)
        }   
        )
        this.todos = this.todos.filter(todo => todo.id != this.selectedTodo[todo1].id ); 
     }
  }

  updateTodo(todoData: Todo): void {
    console.log(todoData);
    this.todoService.updateTodo(todoData)
    .then(updatedTodo => {
      let existingTodo = this.todos.find(todo => todo.id === updatedTodo.id);
      Object.assign(existingTodo, updatedTodo);
      this.clearEditing();
    });
  }

  editTodo(todoData: Todo): void {

    this.editing = true;
    todoData.createdAt = new Date(todoData.createdAt);
    Object.assign(this.editingTodo, todoData);
  }

  clearEditing(): void {
    this.editingTodo = new Todo();
    this.editing = false;
  }  
}

