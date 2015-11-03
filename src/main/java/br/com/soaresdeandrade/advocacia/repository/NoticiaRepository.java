package br.com.soaresdeandrade.advocacia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.soaresdeandrade.advocacia.model.noticia.Noticia;

@Repository
public interface NoticiaRepository extends JpaRepository<Noticia, Long>{

}
