package ch.thp.proto.config.domain;


import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Thierry
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor()
@NamedQuery(name="configEntry.getByKey",query="Select x from ConfigEntry x where x.configKey =:key")
public class ConfigEntry implements Serializable{
    
    @Id
    private String uuId; 
    private String configKey; 
    private String configValue; 
    private String description;

}