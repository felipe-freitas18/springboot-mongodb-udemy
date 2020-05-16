package com.freitasfelipe.workshopmongo.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.freitasfelipe.workshopmongo.domain.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {

	/*
	 * Usando query methods - SpringData gera a consulta pra gente
	 * 
	 * Referências:
	 * https://docs.spring.io/spring-data/mongodb/docs/current/reference/html/
	 * https://docs.spring.io/spring-data/data-document/docs/current/reference/html/
	 * 
	 * "Buscar posts contendo um dado string no título"
	 */
	List<Post> findByTitleContainingIgnoreCase(String text);
	
	/*
	 *	https://docs.spring.io/spring-data/mongodb/docs/current/reference/html/
	 *	https://docs.spring.io/spring-data/data-document/docs/current/reference/html/
	 *	https://docs.mongodb.com/manual/reference/operator/query/regex/
	 * 	
	 * nome do campo, ?0 == primeiro parametro, i == insensitivity
	 */
	@Query("{ 'title': { $regex: ?0, $options: 'i' } }")
	List<Post> searchTitle(String text);
	
	@Query("{ $and: [ {date: {$gte: ?1} }, { date: { $lte: ?2} }, { $or: [ { 'title': { $regex: ?0, $options: 'i' } }, { 'body': { $regex: ?0, $options: 'i' } }, { 'comments.text': { $regex: ?0, $options: 'i' } } ] } ] }")
	List<Post> fullSearch(String text, Date minDate, Date maxDate);
}
