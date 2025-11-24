package com.example.busstation.repository;

import com.example.busstation.model.BaseEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Clasa generică pentru persistența în fișiere JSON.
 * Implementează logica CRUD centralizată.
 */
public class InFileRepository<T extends BaseEntity, ID> implements IRepository<T, ID> {

    protected List<T> entities = new ArrayList<>();
    private final ObjectMapper objectMapper;
    private final File file;
    private final Class<T[]> entityTypeArray; // Ex: Bus[].class

    public InFileRepository(String filePath, Class<T[]> entityTypeArray) {
        this.entityTypeArray = entityTypeArray;
        this.objectMapper = new ObjectMapper();
        // Înregistrează modulul pentru a lucra cu LocalDate/Time
        this.objectMapper.registerModule(new JavaTimeModule());

        try {
            // Caută fișierul în "src/main/resources/"
            this.file = new ClassPathResource(filePath).getFile();
            loadData();
        } catch (IOException e) {
            throw new RuntimeException("Nu s-a putut încărca fișierul de date: " + filePath, e);
        }
    }

    /**
     * Încarcă datele din fișierul JSON în lista 'entities'.
     */
    private void loadData() {
        if (!file.exists() || file.length() == 0) {
            System.out.println("Fișierul de date nu a fost găsit sau este gol: " + file.getPath());
            entities = new ArrayList<>();
            return;
        }
        try (InputStream inputStream = Files.newInputStream(file.toPath())) {
            T[] array = objectMapper.readValue(inputStream, entityTypeArray);
            entities = new ArrayList<>(List.of(array));
            System.out.println("Date încărcate cu succes din: " + file.getPath() + " (" + entities.size() + " înregistrări)");
        } catch (IOException e) {
            throw new RuntimeException("Eroare la citirea datelor din: " + file.getPath(), e);
        }
    }

    /**
     * Salvează lista curentă 'entities' înapoi în fișierul JSON.
     */
    private void saveData() {
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, entities);
        } catch (IOException e) {
            throw new RuntimeException("Eroare la salvarea datelor în: " + file.getPath(), e);
        }
    }

    @Override
    public List<T> findAll() {
        return new ArrayList<>(entities); // Returnează o copie
    }

    @Override
    public Optional<T> findById(ID id) {
        return entities.stream()
                .filter(entity -> entity.getId().equals(id))
                .findFirst();
    }

    @Override
    public T save(T entity) {
        if (entity.getId() == null || entity.getId().isEmpty()) {
            //  Creare
            entity.setId(UUID.randomUUID().toString()); // Generează un ID unic
            entities.add(entity);

        } else {
            // Actualizare

            // 1. Găsim indexul obiectului existent după ID
            int indexToUpdate = -1;
            for (int i = 0; i < entities.size(); i++) {
                if (entities.get(i).getId().equals(entity.getId())) {
                    indexToUpdate = i;
                    break;
                }
            }

            // 2. Înlocuim obiectul la acel index
            if (indexToUpdate != -1) {
                // Am găsit obiectul, îl înlocuim
                entities.set(indexToUpdate, entity);
            } else {
                // Dacă nu-l găsim (deși nu ar trebui), îl tratăm ca pe o adăugare
                entities.add(entity);
            }
        }

        saveData(); // Salvează modificările în fișier
        return entity;
    }

    @Override
    public void delete(ID id) {
        boolean removed = entities.removeIf(entity -> entity.getId().equals(id));
        if (removed) {
            saveData(); // Salvează modificările în fișier
        }
    }
}