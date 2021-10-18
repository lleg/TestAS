package ru.digitalspirit.asaka.fileuploader.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ru.digitalspirit.asaka.fileuploader.entity.UploadFile;
import ru.digitalspirit.asaka.fileuploader.repository.UploadRepository;

import javax.xml.bind.DatatypeConverter;


@Transactional(transactionManager = "fileUploaderTransactionManager")
public class UploadService {

    @Autowired
    private UploadRepository repository;

    /**
     * Save file with data
     *
     * @param lob     File info (see {@link UploadFile})
     * @param message File data
     * @return Saved file (see {@link UploadFile})
     */
    public UploadFile save(UploadFile lob, String message) {
        lob.setBlob(DatatypeConverter.parseBase64Binary(message));
        return repository.save(lob);
    }

    /**
     * Save file
     *
     * @param lob File (see {@link UploadFile})
     * @return Saved file (see {@link UploadFile})
     */
    public UploadFile save(UploadFile lob) {
        return repository.save(lob);
    }

    /**
     * Get file
     *
     * @param id File id
     * @return File (see {@link UploadFile})
     */
    public UploadFile findById(String id) {
        return repository.getOne(id);
    }

}
