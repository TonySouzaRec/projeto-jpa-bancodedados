package br.com.bancodedadosfuctura.service;

import br.com.bancodedadosfuctura.entity.Pessoa;
import br.com.bancodedadosfuctura.util.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import java.util.List;

public class PessoaService {

    public void criar(Pessoa pessoa) {
        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(pessoa);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public Pessoa buscarUm(Integer id) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            return em.find(Pessoa.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            em.close();
        }
    }

    public List<Pessoa> buscarTodos() {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            return em.createQuery("SELECT p FROM Pessoa p", Pessoa.class)
                     .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            em.close();
        }
    }

    public void editar(Pessoa pessoa) {
        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(pessoa);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public void excluir(Integer id) {
        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            Pessoa pessoa = em.find(Pessoa.class, id);
            if (pessoa == null) {
                System.out.println("Pessoa não encontrada para id: " + id);
                return;
            }
            tx.begin();
            em.remove(pessoa);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}