package br.com.b2ml.restful_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.b2ml.restful_api.entity.ClassT;

@Repository
public interface ClassRepository extends JpaRepository<ClassT, Long> { }