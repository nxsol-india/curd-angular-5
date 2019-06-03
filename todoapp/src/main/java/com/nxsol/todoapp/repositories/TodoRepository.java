package com.nxsol.todoapp.repositories;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.nxsol.todoapp.models.Todo;

@Transactional
public interface TodoRepository  extends JpaRepository<Todo, Long>, JpaSpecificationExecutor<Long>{

}