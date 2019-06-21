package dfsp.models.raport;


import lombok.*;

/** Klasa modelowa pliku lokalnego */

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class LocalFile {

    private String name;
    private String owner;
    private String creationTime;
    private String lastModified;
    private Long size;
    private String downloadUri;
    private String fileDeleteUri;
    private String fileType;
}
