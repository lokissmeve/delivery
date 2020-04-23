package warehousepf;

import javax.persistence.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.util.MimeTypeUtils;
import warehousepf.external.WarehouseService;

import java.util.List;

@Entity
@Table(name="Delivery_table")
public class Delivery {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String product;
    private Integer qty;

    @PostPersist
    public void onPostPersist(){
        Delivered reserved = new Delivered();
        BeanUtils.copyProperties(this, reserved);
        reserved.publish();

        warehousepf.external.Warehouse payProcessing = new  warehousepf.external.Warehouse();
        Application.applicationContext.getBean(warehousepf.external.WarehouseService.class)
                .stockRequest(payProcessing);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }
    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }
}
