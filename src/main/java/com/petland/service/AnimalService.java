package com.petland.service;

import com.petland.model.entity.AnimalEntity;
import com.petland.repository.AnimalRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnimalService {

    private final AnimalRepository animalRepository;

    public AnimalService(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    public List<AnimalEntity> listarTodos() {
        return animalRepository.findAll();
    }

    public Optional<AnimalEntity> buscarPorId(Integer id) {
        return animalRepository.findById(id);
    }

    public AnimalEntity criar(AnimalEntity animal) {
        return animalRepository.save(animal);
    }

    public Optional<AnimalEntity> atualizar(Integer id, AnimalEntity animal) {
        return animalRepository.findById(id)
                .map(existente -> {
                    existente.setNome(animal.getNome());
                    existente.setAniversario(animal.getAniversario());
                    existente.setEspecie(animal.getEspecie());
                    return animalRepository.save(existente);
                });
    }

    public boolean deletar(Integer id) {
        return animalRepository.findById(id)
                .map(animal -> {
                    animalRepository.delete(animal);
                    return true;
                })
                .orElse(false);
    }
}
