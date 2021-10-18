package ru.digitalspirit.asaka.fileuploader.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.digitalspirit.asaka.fileuploader.entity.UploadFile;

@Repository("commonLobTableRepository")
public interface UploadRepository extends JpaRepository<UploadFile, String> {

}
