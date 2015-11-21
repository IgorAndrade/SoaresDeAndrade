package br.com.soaresdeandrade.advocacia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import br.com.soaresdeandrade.advocacia.model.Perfil;
public interface PerfilRepository extends JpaRepository<Perfil, Long>{

}
