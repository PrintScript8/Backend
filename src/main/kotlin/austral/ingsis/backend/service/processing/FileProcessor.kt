package austral.ingsis.backend.service.processing

import org.springframework.web.multipart.MultipartFile


interface FileProcessor {
    fun process(file: MultipartFile)
}

/*

Key Methods of MultipartFile:

- byte[] getBytes(): Returns the contents of the file as an array of bytes.
- InputStream getInputStream(): Returns an InputStream to read the contents of the file.
- String getName(): Returns the name of the parameter in the multipart form.
- String getOriginalFilename(): Returns the original filename in the client's filesystem.
- String getContentType(): Returns the content type of the file.
- boolean isEmpty(): Checks if the file is empty.
- long getSize(): Returns the size of the file in bytes.
- void transferTo(File dest): Transfers the received file to the given destination file.

 */
